/**
 * ******************************************************************************************
 * Copyright (C) 2013 - Food and Agriculture Organization of the United Nations (FAO).
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:
 *
 *    1. Redistributions of source code must retain the above copyright notice,this list
 *       of conditions and the following disclaimer.
 *    2. Redistributions in binary form must reproduce the above copyright notice,this list
 *       of conditions and the following disclaimer in the documentation and/or other
 *       materials provided with the distribution.
 *    3. Neither the name of FAO nor the names of its contributors may be used to endorse or
 *       promote products derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY
 * EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT
 * SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,PROCUREMENT
 * OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION)
 * HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT,STRICT LIABILITY,OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE,
 * EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 * *********************************************************************************************
 */
package org.sola.services.ejb.bulkload.businesslogic;

import java.io.File;
import java.text.DateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import org.sola.common.DateUtility;
import org.sola.common.FileMetaData;
import org.sola.common.FileUtility;
import org.sola.common.NetworkFolder;
import org.sola.common.StringUtility;
import org.sola.common.logging.LogUtility;
import org.sola.services.common.ejbs.AbstractEJB;
import org.sola.services.common.faults.FaultUtility;
import org.sola.services.common.repository.CommonSqlProvider;
import org.sola.services.ejb.bulkload.repository.BulkLoadSqlProvider;
import org.sola.services.ejb.bulkload.repository.entities.BulkDocument;
import org.sola.services.ejb.bulkload.repository.entities.BulkSource;

/**
 * EJB to manage data in the Bulk Loading of documents into SOLA. Singleton
 * EJB's are subject to READ and WRITE concurrency control to manage access to
 * the EJB methods. To avoid unnecessary blocking of EJB methods, BEAN
 * concurrency is used instead of the default Container Concurrency.
 */
@ConcurrencyManagement(ConcurrencyManagementType.BEAN)
@Singleton
@EJB(name = "java:global/SOLA/BulkLoadEJBLocal", beanInterface = BulkLoadEJBLocal.class)
public class BulkLoadEJB extends AbstractEJB implements BulkLoadEJBLocal {

    // Volalite variables accessed by multiple methods
    volatile String progressMessage;
    volatile int count;
    volatile int skipCount;
    volatile long startTime;
    volatile int totalCount;
    volatile boolean cancelLoad = false;

    /**
     * Sets the entity package for the EJB to
     * BulkSource.class.getPackage().getName(). This is used to restrict the
     * save and retrieval of Code Entities.
     *
     * @see AbstractEJB#getCodeEntity(java.lang.Class, java.lang.String,
     * java.lang.String) AbstractEJB.getCodeEntity
     * @see AbstractEJB#getCodeEntityList(java.lang.Class, java.lang.String)
     * AbstractEJB.getCodeEntityList
     * @see
     * AbstractEJB#saveCodeEntity(org.sola.services.common.repository.entities.AbstractCodeEntity)
     * AbstractEJB.saveCodeEntity
     */
    @Override
    protected void postConstruct() {
        setEntityPackage(BulkSource.class.getPackage().getName());
    }

    /**
     * Returns the current progressMessage. Avoid using any transaction so that
     * the progress message can be obtained at any state of the bulk load
     * process
     */
    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public String getProgressMessage() {
        progressMessage += System.lineSeparator() + count
                + " documents loaded. Est. time remaining " + calcTimeRemaining();
        return progressMessage;
    }

    public void setProgressMessage(String progressMessage) {
        this.progressMessage = progressMessage;
    }

    /**
     * Sets a flag that will cancel the load process
     */
    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public void cancelLoad() {
        cancelLoad = true;
    }

    /**
     * Process the document file name to produce a reference number for the
     * document.
     */
    private String getRefNumber(String fileName) {
        String result = "";
        if (!StringUtility.isEmpty(fileName)) {
            result = FileUtility.getFileNameWithoutExtension(fileName);
            result = result.replaceAll("DSL-", "");
            result = result.replaceAll("DL", "");
            result = result.replaceAll("_A1b", "");
            result = result.substring(result.lastIndexOf(";") + 1);
        }
        return result;
    }
    
    private String getFileName(String fileName) {
        String result = "";
        if (!StringUtility.isEmpty(fileName)) {
            result = fileName.substring(fileName.lastIndexOf(";") + 1);
        }
        return result;
    }

    /**
     * Determines the amount of time remaining before the load operation is
     * completed.
     */
    private String calcTimeRemaining() {
        String result = "-";
        if (count > 0 && totalCount > 0 && startTime > 0) {
            long elapsed = (System.currentTimeMillis() - startTime) / 1000;
            double avgTime = elapsed / count;
            int timeRemaining = (int) (avgTime * (totalCount - count - skipCount));
            int hours = 0;
            int minutes = 0;
            result = "";
            if (timeRemaining > 3600) {
                hours = timeRemaining / 3600;
                timeRemaining = timeRemaining - (hours * 3600);
                result += hours + "h ";
            }
            if (timeRemaining > 60) {
                minutes = timeRemaining / 60;
                timeRemaining = timeRemaining - (minutes * 60);
                result += minutes + "m ";
            }
            result += timeRemaining + "s...";
        }
        return result;
    }

    /**
     * Loads documents from the specified a folder on the computer hosting the
     * SOLA Services. Documents in the subfolders are also loaded. Every
     * document is given the same document type.
     *
     * Documents are loaded independently of a transaction to prevent rollback
     * of load if an exception occurs.
     *
     * @param docType The type of document to load. Must be a value from
     * source.adminstrative_source_type
     * @param sourceFolder The source folder to load the documents from. Must be
     * a folder on the computer hosting the SOLA Services. This version of the
     * method does not currently support loading documents from a shared folder
     * on another computer
     * @return The final set of progress messages.
     */
    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public String loadDocuments(String docType, String sourceFolder) {
        cancelLoad = false;
        count = 0;
        skipCount = 0;
        totalCount = 0;
        progressMessage = "";
        FileMetaData current = null;
        startTime = System.currentTimeMillis();
        try {
            progressMessage += System.lineSeparator() + "Loading Documents with parameters;"
                    + " Document Type = " + (docType == null ? "null" : docType)
                    + ", Source Folder = " + (sourceFolder == null ? "null" : sourceFolder);
            progressMessage += System.lineSeparator() + "Load started: "
                    + DateUtility.getDateTimeString(DateUtility.now(), DateFormat.MEDIUM, DateFormat.LONG);

            NetworkFolder loadFolder = new NetworkFolder(sourceFolder);
            if (!loadFolder.exists()) {
                progressMessage += System.lineSeparator() + "Source Folder does not exist. Load aborted";
                return progressMessage;
            }
            List<FileMetaData> files = loadFolder.getAllFiles(null);
            totalCount = files.size();
            progressMessage += System.lineSeparator() + totalCount + " to be loaded";
            // Set the max file size to load to 500Mb
            FileUtility.setMaxFileSizeBytes(500 * 1024 * 1024);

            // Prepare the sql parameters
            Map params = new HashMap<String, Object>();
            params.put(CommonSqlProvider.PARAM_QUERY, BulkLoadSqlProvider.buildDocumentLoadedSql());
            for (FileMetaData file : files) {
                if (cancelLoad) {
                    progressMessage += System.lineSeparator() + "*** Load cancelled by user ***";
                    break;
                }
                // Check if the document already exists in the database based on the file name. 
                params.remove(BulkLoadSqlProvider.QUERY_PARAM_DOCUMENT_NR);
                params.put(BulkLoadSqlProvider.QUERY_PARAM_DOCUMENT_NR, getFileName(file.getName()));
                if (getRepository().getScalar(String.class, params) == null) {
                    // Document does not already exist in the database, so load it
                    BulkDocument doc = new BulkDocument();
                    doc.setNr("");// SOLA Insert Mapper does not insert null values
                    doc.setDescription(getFileName(file.getName()));
                    doc.setExtension(FileUtility.getFileExtension(file.getName()));
                    // Determine the file name
                    String fileName = file.getName().replaceAll(File.pathSeparator, "/");
                    doc.setBody(FileUtility.getFileBinary(loadFolder.getPath() + fileName));

                    // Create a basic source record for the Document
                    BulkSource source = new BulkSource();
                    source.setLaNr("");// SOLA Insert Mapper does not insert null values
                    source.setDocumentId(doc.getId());
                    source.setTypeCode(docType);
                    source.setRecordation(file.getModificationDate());
                    source.setReferenceNr(getRefNumber(file.getName()));

                    // Save the document and the source
                    getRepository().saveEntity(doc);
                    getRepository().saveEntity(source);
                    count++;
                    if (count % 50 == 0) {
                        progressMessage += System.lineSeparator() + count
                                + " documents loaded. Est. time remaining " + calcTimeRemaining();
                    }
                } else {
                    skipCount++;
                    progressMessage += System.lineSeparator() + file.getName()
                            + " already loaded - skipping...";
                }
            }

        } catch (Exception ex) {
            progressMessage += System.lineSeparator() + "Processed " + count + " records";
            if (current != null) {
                progressMessage += System.lineSeparator() + "Current Document = "
                        + current.getName();
            }
            progressMessage += System.lineSeparator() + "EXCEPTION > " + FaultUtility.getStackTraceAsString(ex);
        }
        long elapsed = (System.currentTimeMillis() - startTime) / 1000;
        progressMessage += System.lineSeparator() + count + " documents loaded in " + elapsed + "s";
        progressMessage += System.lineSeparator() + "Load completed!";
        LogUtility.log(progressMessage);
        String result = progressMessage;
        progressMessage = "";
        count = 0;
        startTime = 0;
        totalCount = 0;
        skipCount =0;
        return result;
    }
}

/**
 * ******************************************************************************************
 * Copyright (C) 2012 - Food and Agriculture Organization of the United Nations
 * (FAO). All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice,this
 * list of conditions and the following disclaimer. 2. Redistributions in binary
 * form must reproduce the above copyright notice,this list of conditions and
 * the following disclaimer in the documentation and/or other materials provided
 * with the distribution. 3. Neither the name of FAO nor the names of its
 * contributors may be used to endorse or promote products derived from this
 * software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT,STRICT LIABILITY,OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING
 * IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 * *********************************************************************************************
 */
package org.sola.services.ejb.bulkload.repository;

import static org.apache.ibatis.jdbc.SqlBuilder.*;

/**
 * Provides SQL generation methods for the Bulk Load EJB
 *
 * @author solaDev
 */
public class BulkLoadSqlProvider {

    public static final String QUERY_PARAM_DOCUMENT_NR = "documentNr";

    /**
     * Disables all triggers on the specified table.
     */
    public static String buildDisableTriggerSql(String tableName) {
        return "ALTER TABLE " + tableName + " DISABLE TRIGGER ALL";
    }

    /**
     * Enables all triggers on the specified table.
     */
    public static String buildEnableTriggerSql(String tableName) {
        return "ALTER TABLE " + tableName + " ENABLE TRIGGER ALL";
    }

    /**
     * Checks the document.document table to determine if the document has already
     * been loaded into the database or not based on the Nr. 
     */
    public static String buildDocumentLoadedSql() {
        String result;
        BEGIN();
        SELECT("d.id");
        FROM("document.document d");
        WHERE("d.description = #{" + QUERY_PARAM_DOCUMENT_NR + "}");
        result = SQL();
        return result;
    }
}

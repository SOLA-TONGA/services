/**
 * ******************************************************************************************
 * Copyright (C) 2014 - Food and Agriculture Organization of the United Nations (FAO).
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
package org.sola.services.ejb.search.repository.entities;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Id;
import org.sola.services.common.repository.entities.AbstractReadOnlyEntity;

/**
 *
 * @author Admin
 */
public class MinisterInwardSearchResult extends AbstractReadOnlyEntity{
    public static final String QUERY_PARAM_ID = "id";
    public static final String QUERY_PARAM_SUBJECT = "subject";
    public static final String QUERY_PARAM_DATE_IN_FROM = "dateInFrom";
    public static final String QUERY_PARAM_DATE_IN_TO = "dateInTo";
    public static final String QUERY_PARAM_FILE_NUMBER = "fileNumber";
    public static final String QUERY_PARAM_DATE_OUT_FROM = "dateOutFrom";
    public static final String QUERY_PARAM_DATE_OUT_TO = "dateOutTo";
    public static final String QUERY_PARAM_DIRECTED_DIVISION = "directedDivision";
    public static final String QUERY_PARAM_DIRECTED_OFFICER = "directedOfficer";
    public static final String QUERY_PARAM_CEO_DIRECTION = "ceoDirection";
    public static final String QUERY_PARAM_MINISTER_DIRECTION = "ministerDirection";
    public static final String QUERY_PARAM_FROM_WHOM = "fromWhom";
    public static final String QUERY_PARAM_REMARK = "remark";
    public static final String QUERY_ORDER_BY = "a.file_number";

    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "subject")
    private String subject;
    @Column(name = "date_in")
    private Date dateIn;
    @Column(name = "file_number")
    private String fileNumber;
    @Column(name = "date_out")
    private Date dateOut;
    @Column(name = "directed_division")
    private String directedDivision;
    @Column(name = "directed_officer")
    private String directedOfficer;
    @Column(name = "ceo_direction")
    private String ceoDirection;
    @Column(name = "minister_direction")
    private String ministerDirection;
    @Column(name = "from_whom")
    private String fromWhom;
    @Column(name = "remark")
    private String remark;
    
    public MinisterInwardSearchResult(){
        super();
    }
    
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Date getDateIn() {
        return dateIn;
    }

    public void setDateIn(Date dateIn) {
        this.dateIn = dateIn;
    }

    public String getFileNumber() {
        return fileNumber;
    }

    public void setFileNumber(String fileNumber) {
        this.fileNumber = fileNumber;
    }

    public Date getDateOut() {
        return dateOut;
    }

    public void setDateOut(Date dateOut) {
        this.dateOut = dateOut;
    }

    public String getDirectedDivision() {
        return directedDivision;
    }

    public void setDirectedDivision(String directedDivision) {
        this.directedDivision = directedDivision;
    }

    public String getDirectedOfficer() {
        return directedOfficer;
    }

    public void setDirectedOfficer(String directedOfficer) {
        this.directedOfficer = directedOfficer;
    }

    public String getCeoDirection() {
        return ceoDirection;
    }

    public void setCeoDirection(String ceoDirection) {
        this.ceoDirection = ceoDirection;
    }

    public String getMinisterDirection() {
        return ministerDirection;
    }

    public void setMinisterDirection(String ministerDirection) {
        this.ministerDirection = ministerDirection;
    }

    public String getFromWhom() {
        return fromWhom;
    }

    public void setFromWhom(String fromWhom) {
        this.fromWhom = fromWhom;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
    
}

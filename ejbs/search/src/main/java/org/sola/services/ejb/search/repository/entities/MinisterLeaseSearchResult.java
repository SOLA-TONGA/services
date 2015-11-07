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
public class MinisterLeaseSearchResult extends AbstractReadOnlyEntity{
    public static final String QUERY_PARAM_ID = "id";
    public static final String QUERY_PARAM_DATE_RECEIVED_FROM = "dateReceivedFrom";
    public static final String QUERY_PARAM_DATE_RECEIVED_TO = "dateReceivedTo";
    public static final String QUERY_PARAM_NAME = "name";
    public static final String QUERY_PARAM_PURPOSE = "purpose";
    public static final String QUERY_PARAM_LOCATION = "location";
    public static final String QUERY_PARAM_NOBLE = "noble";
    public static final String QUERY_PARAM_LAND_TYPE = "landType";
    public static final String QUERY_PARAM_TOTAL_AREA = "totalArea";
    public static final String QUERY_PARAM_LEASE_AREA = "leaseArea";
    public static final String QUERY_PARAM_TERM = "term";
    public static final String QUERY_PARAM_RENT = "rent";
    public static final String QUERY_PARAM_SURVEY_FEE = "surveyFee";
    public static final String QUERY_PARAM_RECEIPT_NUMBER = "receiptNumber";
    public static final String QUERY_PARAM_PAY_DATE_FROM = "payDateFrom";
    public static final String QUERY_PARAM_PAY_DATE_TO = "payDateTo";
    public static final String QUERY_PARAM_CEO_DIRECTION = "ceoDirection";
    public static final String QUERY_PARAM_DIRECTED_DIVISION = "directedDivision";
    public static final String QUERY_PARAM_REMARK = "remark";
    public static final String QUERY_ORDER_BY = "a.name";

    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "date_received")
    private Date dateReceived;
    @Column(name = "name")
    private String name;
    @Column(name = "purpose")
    private String purpose;
    @Column(name = "location")
    private String location;
    @Column(name = "noble")
    private String noble;
    @Column(name = "land_type")
    private String landType;
    @Column(name = "total_area")
    private String totalArea;
    @Column(name = "lease_area")
    private String leaseArea;
    @Column(name = "term")
    private String term;
    @Column(name = "rent")
    private String rent;
    @Column(name = "survey_fee")
    private String surveyFee;
    @Column(name = "receipt_number")
    private String receiptNumber;
    @Column(name = "pay_date")
    private Date payDate;
    @Column(name = "ceo_direction")
    private String ceoDirection;
    @Column(name = "directed_division")
    private String directedDivision;
    @Column(name = "remark")
    private String remark;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDateReceived() {
        return dateReceived;
    }

    public void setDateReceived(Date dateReceived) {
        this.dateReceived = dateReceived;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getNoble() {
        return noble;
    }

    public void setNoble(String noble) {
        this.noble = noble;
    }

    public String getLandType() {
        return landType;
    }

    public void setLandType(String landType) {
        this.landType = landType;
    }

    public String getTotalArea() {
        return totalArea;
    }

    public void setTotalArea(String totalArea) {
        this.totalArea = totalArea;
    }

    public String getLeaseArea() {
        return leaseArea;
    }

    public void setLeaseArea(String leaseArea) {
        this.leaseArea = leaseArea;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getRent() {
        return rent;
    }

    public void setRent(String rent) {
        this.rent = rent;
    }

    public String getSurveyFee() {
        return surveyFee;
    }

    public void setSurveyFee(String surveyFee) {
        this.surveyFee = surveyFee;
    }

    public String getReceiptNumber() {
        return receiptNumber;
    }

    public void setReceiptNumber(String receiptNumber) {
        this.receiptNumber = receiptNumber;
    }

    public Date getPayDate() {
        return payDate;
    }

    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }

    public String getCeoDirection() {
        return ceoDirection;
    }

    public void setCeoDirection(String ceoDirection) {
        this.ceoDirection = ceoDirection;
    }

    public String getDirectedDivision() {
        return directedDivision;
    }

    public void setDirectedDivision(String directedDivision) {
        this.directedDivision = directedDivision;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
     
}

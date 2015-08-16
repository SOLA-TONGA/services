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
import org.sola.services.common.repository.entities.AbstractEntity;

/**
 *
 * @author Admin
 */
public class DraftingSearchParams extends AbstractEntity{
    private String id;
    private String serviceId;
    private String natureOfSurvey;
    private String traceBy;
    private Date traceDate;
    private String sentTo;
    private Date sendDate;
    private Date returnDate;
    private String drawDeed;
    private String deedNumber;
    private String plottingBy;
    private Date plottingDate;
    private String referInfo;
    private String comment;
    private String itemNumber;
    private String firstName;
    private String lastName;
    private String planNumber;
    private String location;
    private Date dateReceivedFrom;
    private Date dateReceivedTo;
    
    public DraftingSearchParams(){
        super();
    }

    public String getItemNumber() {
        return itemNumber;
    }

    public void setItemNumber(String itemNumber) {
        this.itemNumber = itemNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPlanNumber() {
        return planNumber;
    }

    public void setPlanNumber(String planNumber) {
        this.planNumber = planNumber;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getDateReceivedFrom() {
        return dateReceivedFrom;
    }

    public void setDateReceivedFrom(Date dateReceivedFrom) {
        this.dateReceivedFrom = dateReceivedFrom;
    }

    public Date getDateReceivedTo() {
        return dateReceivedTo;
    }

    public void setDateReceivedTo(Date dateReceivedTo) {
        this.dateReceivedTo = dateReceivedTo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getNatureOfSurvey() {
        return natureOfSurvey;
    }

    public void setNatureOfSurvey(String natureOfSurvey) {
        this.natureOfSurvey = natureOfSurvey;
    }

    public String getTraceBy() {
        return traceBy;
    }

    public void setTraceBy(String traceBy) {
        this.traceBy = traceBy;
    }

    public Date getTraceDate() {
        return traceDate;
    }

    public void setTraceDate(Date traceDate) {
        this.traceDate = traceDate;
    }
    
    public String getSentTo() {
        return sentTo;
    }

    public void setSentTo(String sentTo) {
        this.sentTo = sentTo;
    }
    
    public Date getSendDate() {
        return sendDate;
    }

    public void setSendDate(Date sendDate) {
        this.sendDate = sendDate;
    }
    
    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public String getDrawDeed() {
        return drawDeed;
    }

    public void setDrawDeed(String drawDeed) {
        this.drawDeed = drawDeed;
    }

    public String getDeedNumber() {
        return deedNumber;
    }

    public void setDeedNumber(String deedNumber) {
        this.deedNumber = deedNumber;
    }

    public String getPlottingBy() {
        return plottingBy;
    }

    public void setPlottingBy(String plottingBy) {
        this.plottingBy = plottingBy;
    }

    public Date getPlottingDate() {
        return plottingDate;
    }

    public void setPlottingDate(Date plottingDate) {
        this.plottingDate = plottingDate;
    }

    public String getReferInfo() {
        return referInfo;
    }

    public void setReferInfo(String referInfo) {
        this.referInfo = referInfo;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

}

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
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sola.services.ejb.application.repository.entities;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import org.sola.services.common.repository.entities.AbstractVersionedEntity;

/**
 *
 * @author Admin
 */
@Table(name = "drafting", schema = "application")
public class Drafting extends AbstractVersionedEntity{
    
    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "service_id")
    private String serviceId;
    @Column(name = "item_number")
    private String itemNumber;
    @Column(name = "date_received")
    private Date receiveDate;
    @Column(name = "item_firstname")
    private String firstName;
    @Column(name = "item_lastname")
    private String lastName;
    @Column(name = "nature_of_survey")
    private String natureOfSurvey;
    @Column(name = "location")
    private String location;
    @Column(name = "trace_by")
    private String traceBy;
    @Column(name = "trace_date")
    private Date traceDate;
    @Column(name = "sent_to")
    private String sentTo;
    @Column(name = "send_date")
    private Date sendDate;
    @Column(name = "return_date")
    private Date returnDate;
    @Column(name = "draw_deed")
    private String drawDeed;
    @Column(name = "deed_number")
    private String deedNumber;
    @Column(name = "plotting_by")
    private String plottingBy;
    @Column(name = "plotting_date")
    private Date plottingDate;
    @Column(name = "plan_number")
    private String planNumber;
    @Column(name = "refer_info")
    private String referInfo;
    @Column(name = "comment")
    private String comment;
    
    public Drafting(){
        super();
    }

    public String getId() {
        id = id == null ? generateId() : id;
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

    public String getItemNumber() {
        return itemNumber;
    }

    public void setItemNumber(String itemNumber) {
        this.itemNumber = itemNumber;
    }

    public Date getReceiveDate() {
        return receiveDate;
    }

    public void setReceiveDate(Date receiveDate) {
        this.receiveDate = receiveDate;
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

    public String getNatureOfSurvey() {
        return natureOfSurvey;
    }

    public void setNatureOfSurvey(String natureOfSurvey) {
        this.natureOfSurvey = natureOfSurvey;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
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

    public String getPlanNumber() {
        return planNumber;
    }

    public void setPlanNumber(String planNumber) {
        this.planNumber = planNumber;
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

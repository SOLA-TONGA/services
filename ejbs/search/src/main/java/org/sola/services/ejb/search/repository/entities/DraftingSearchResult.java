/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
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
public class DraftingSearchResult extends AbstractReadOnlyEntity{
    
    public static final String QUERY_PARAM_ID = "id";
    public static final String QUERY_PARAM_SERVICE_ID = "serviceId";
    public static final String QUERY_PARAM_ITEM_NUMBER = "itemNumber";
    public static final String QUERY_PARAM_FIRST_NAME = "firstName";
    public static final String QUERY_PARAM_LAST_NAME = "lastName";
    public static final String QUERY_PARAM_DATE_RECEIVED_FROM = "dateReceivedFrom";
    public static final String QUERY_PARAM_DATE_RECEIVED_TO = "dateReceivedTo";
    public static final String QUERY_PARAM_LOCATION = "location";
    public static final String QUERY_PARAM_PLAN_NUMBER = "planNumber";
    public static final String QUERY_PARAM_NATURE_OF_SURVEY = "natureOfSurvey";
    public static final String QUERY_PARAM_TRACE_BY = "traceBy";
    public static final String QUERY_PARAM_TRACE_DATE = "traceDate";
    public static final String QUERY_PARAM_DRAW_DEED = "drawDeed";
    public static final String QUERY_PARAM_DEED_NUMBER = "deedNumber";
    public static final String QUERY_PARAM_PLOTTING_BY = "plottingBy";
    public static final String QUERY_PARAM_PLOTTING_DATE = "plottingDate";
    public static final String QUERY_PARAM_REFER_INTO = "referInfo";
    public static final String QUERY_PARAM_COMMENT = "comment";
    public static final String QUERY_ORDER_BY = "a.item_number";
    
    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "service_id")
    private String serviceId;
    @Column(name = "item_number")
    private String itemNumber;
    @Column(name = "date_received")
    private Date dateReceived;
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
    
    public DraftingSearchResult(){
        super();
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

    public String getItemNumber() {
        return itemNumber;
    }

    public void setItemNumber(String itemNumber) {
        this.itemNumber = itemNumber;
    }

    public Date getDateReceived() {
        return dateReceived;
    }

    public void setDateReceived(Date dateReceived) {
        this.dateReceived = dateReceived;
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

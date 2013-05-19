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
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sola.services.ejb.application.repository.entities;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import org.sola.services.common.repository.AccessFunctions;
import org.sola.services.common.repository.entities.AbstractVersionedEntity;

/**
 *
 * @author Admin
 */
@Table(name = "service_checklist_item", schema = "application")
public class ServiceChecklistItem extends AbstractVersionedEntity {

    public static final String QUERY_PARAMETER_SERVICE_ID = "serviceId";
    public static final String QUERY_WHERE_BYSERVICEID = "service_id = #{" + QUERY_PARAMETER_SERVICE_ID + "} ";
    @Id
    @Column(name = "service_id")
    private String serviceId;
    @Id
    @Column(name = "checklist_item_code")
    private String checklistItemCode;
    @Column(name = "result")
    private String result;
    @Column(name = "comment")
    private String comment;
    @AccessFunctions(onSelect = "(SELECT ci.display_value "
            + " FROM application.checklist_item ci"
            + " WHERE ci.code = checklist_item_code)")
    @Column(name = "display_value", insertable = false, updatable = false)
    private String checklistItemDisplayValue;
    @AccessFunctions(onSelect = "(SELECT ci.description "
            + " FROM application.checklist_item ci "
            + " WHERE ci.code = checklist_item_code)")
    @Column(name = "description", insertable = false, updatable = false)
    private String checklistItemDescription;

    public ServiceChecklistItem() {
        super();
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getChecklistItemCode() {
        return checklistItemCode;
    }

    public void setChecklistItemCode(String checklistItemCode) {
        this.checklistItemCode = checklistItemCode;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getChecklistItemDisplayValue() {
        return checklistItemDisplayValue;
    }

    public void setChecklistItemDisplayValue(String checklistItemDisplayValue) {
        this.checklistItemDisplayValue = checklistItemDisplayValue;
    }

    public String getChecklistItemDescription() {
        return checklistItemDescription;
    }

    public void setChecklistItemDescription(String checklistItemDescription) {
        this.checklistItemDescription = checklistItemDescription;
    }
    
}

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
package org.sola.services.ejb.search.repository.entities;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Id;
import org.sola.services.common.repository.entities.AbstractReadOnlyEntity;

public class BaUnitSearchResult extends AbstractReadOnlyEntity {

    public static final String QUERY_PARAM_NAME_FIRSTPART = "nameFirstPart";
    public static final String QUERY_PARAM_NAME_LASTPART = "nameLastPart";
    public static final String QUERY_PARAM_OWNER_NAME = "ownerName";
    public static final String QUERY_PARAM_PARCEL_NAME = "parcelName";
    public static final String QUERY_PARAM_REGISTRY_BOOK_REF = "registrBookRef";
    public static final String QUERY_PARAM_REGISTRATION_FROM_DATE = "registrationFromDate";
    public static final String QUERY_PARAM_REGISTRATION_TO_DATE = "registrationToDate";
    public static final String QUERY_PARAM_TOWN = "town";
    public static final String QUERY_PARAM_ISLAND = "island";
    public static final String QUERY_PARAM_OTHER_RIGHTHOLDER = "otherRightholder";
    public static final String QUERY_PARAM_ESTATE_NAME = "estateName";
    public static final String QUERY_ORDER_BY = "prop.name_firstpart, prop.name_lastpart";
    @Id
    @Column
    private String id;
    @Column
    private String name;
    @Column(name = "name_firstpart")
    private String nameFirstPart;
    @Column(name = "name_lastpart")
    private String nameLastPart;
    @Column(name = "status_code")
    private String statusCode;
    @Column
    private String rightholders;
    @Column(name = "island_name")
    private String islandName;
    @Column(name = "town_name")
    private String townName;
    @Column(name = "registration_date")
    private Date registeredDate;
    @Column(name = "other_rightholders")
    private String otherRightholders;
    @Column(name = "registered_name")
    private String parcelName;
    @Column(name = "registry_book_ref")
    private String registryBookRef;
    @Column(name = "type_code")
    private String baUnitTypeCode;
    @Column(name = "rrr_type_code")
    private String rrrTypeCode;
    @Column(name = "estate_name")
    private String estateName;

    public BaUnitSearchResult() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameFirstPart() {
        return nameFirstPart;
    }

    public void setNameFirstPart(String nameFirstPart) {
        this.nameFirstPart = nameFirstPart;
    }

    public String getNameLastPart() {
        return nameLastPart;
    }

    public void setNameLastPart(String nameLastPart) {
        this.nameLastPart = nameLastPart;
    }

    public String getRightholders() {
        return rightholders;
    }

    public void setRightholders(String rightholders) {
        this.rightholders = rightholders;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getIslandName() {
        return islandName;
    }

    public void setIslandName(String islandName) {
        this.islandName = islandName;
    }

    public String getTownName() {
        return townName;
    }

    public void setTownName(String townName) {
        this.townName = townName;
    }

    public Date getRegisteredDate() {
        return registeredDate;
    }

    public void setRegisteredDate(Date registeredDate) {
        this.registeredDate = registeredDate;
    }

    public String getOtherRightholders() {
        return otherRightholders;
    }

    public void setOtherRightholders(String otherRightholders) {
        this.otherRightholders = otherRightholders;
    }

    public String getParcelName() {
        return parcelName;
    }

    public void setParcelName(String parcelName) {
        this.parcelName = parcelName;
    }

    public String getRegistryBookRef() {
        return registryBookRef;
    }

    public void setRegistryBookRef(String registryBookRef) {
        this.registryBookRef = registryBookRef;
    }

    public String getBaUnitTypeCode() {
        return baUnitTypeCode;
    }

    public void setBaUnitTypeCode(String baUnitTypeCode) {
        this.baUnitTypeCode = baUnitTypeCode;
    }

    public String getRrrTypeCode() {
        return rrrTypeCode;
    }

    public void setRrrTypeCode(String rrrTypeCode) {
        this.rrrTypeCode = rrrTypeCode;
    }

    public String getEstateName() {
        return estateName;
    }

    public void setEstateName(String estateName) {
        this.estateName = estateName;
    }
}

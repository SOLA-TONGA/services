/**
 * ******************************************************************************************
 * Copyright (C) 2013 - Food and Agriculture Organization of the United Nations
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
package org.sola.services.ejb.search.repository.entities;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import org.sola.services.common.repository.entities.AbstractReadOnlyEntity;

public class PropertyVerifier extends AbstractReadOnlyEntity {

    public static final String QUERY_PARAM_FIRST_PART = "firstPart";
    public static final String QUERY_PARAM_LAST_PART = "lastPart";
    public static final String QUERY_PARAM_LEASE_NUM = "leaseNum";
    public static final String QUERY_PARAM_SUBLEASE_NUM = "subleaseNum";
    public static final String QUERY_PARAM_APPLICATION_NUMBER = "applicationNumber";
    public static final String CODE_LEASED_UNIT = "leasedUnit";
    public static final String CODE_SUBLEASE_UNIT = "subleaseUnit";
    public static final String CODE_TOWN_ALLOTMENT_UNIT = "townAllotmentUnit";
    public static final String CODE_TAX_UNIT = "taxUnit";
    public static final String CODE_ESTATE_UNIT = "estateUnit";
    public static final String CODE_TOWN_UNIT = "townUnit";
    public static final String CODE_ISLAND_UNIT = "islandUnit";
    // Allotment specific fields
    @Column(name = "lot_id")
    private String lotBaUnitId;
    @Column(name = "type_code")
    private String typeCode;
    @Column(name = "deed_num")
    private String deedNumber;
    @Column
    private String folio;
    @Column(name = "reg_date")
    private Date registrationDate;
    @Column(name = "holder_name")
    private String holderName;
    @Column(name = "parcel_name")
    private String parcelName;
    // Common fields
    @Column(name = "district_id")
    private String districtId;
    @Column(name = "estate_id")
    private String estateId;
    @Column(name = "town_id")
    private String townId;
    @Column(name = "area")
    private BigDecimal area;
    @Column(name = "land_use_code")
    private String landUseTypeCode;
    // Lease specific fields
    @Column(name = "lease_id")
    private String leaseBaUnitId;
    @Column(name = "lease_num")
    private String leaseNumber;
    @Column(name = "term")
    private BigDecimal leaseTerm;
    @Column(name = "rental")
    private BigDecimal leaseRental;
    @Column(name = "lessee_name")
    private String lesseeName;
    // Sublease specific fields
    @Column(name = "sublease_id")
    private String subleaseBaUnitId;
    @Column(name = "sublease_num")
    private String subleaseNumber;
    @Column(name = "sublessee_name")
    private String sublesseeName;
    private String applicationsWhereFound;

    public PropertyVerifier() {
        super();
    }

    public String getLotBaUnitId() {
        return lotBaUnitId;
    }

    public void setLotBaUnitId(String lotBaUnitId) {
        this.lotBaUnitId = lotBaUnitId;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public String getDeedNumber() {
        return deedNumber;
    }

    public void setDeedNumber(String deedNumber) {
        this.deedNumber = deedNumber;
    }

    public String getFolio() {
        return folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getHolderName() {
        return holderName;
    }

    public void setHolderName(String holderName) {
        this.holderName = holderName;
    }

    public String getParcelName() {
        return parcelName;
    }

    public void setParcelName(String parcelName) {
        this.parcelName = parcelName;
    }

    public String getDistrictId() {
        return districtId;
    }

    public void setDistrictId(String districtId) {
        this.districtId = districtId;
    }

    public String getEstateId() {
        return estateId;
    }

    public void setEstateId(String estateId) {
        this.estateId = estateId;
    }

    public String getTownId() {
        return townId;
    }

    public void setTownId(String townId) {
        this.townId = townId;
    }

    public BigDecimal getArea() {
        return area;
    }

    public void setArea(BigDecimal area) {
        this.area = area;
    }

    public String getLandUseTypeCode() {
        return landUseTypeCode;
    }

    public void setLandUseTypeCode(String landUseTypeCode) {
        this.landUseTypeCode = landUseTypeCode;
    }

    public String getLeaseBaUnitId() {
        return leaseBaUnitId;
    }

    public void setLeaseBaUnitId(String leaseBaUnitId) {
        this.leaseBaUnitId = leaseBaUnitId;
    }

    public String getLeaseNumber() {
        return leaseNumber;
    }

    public void setLeaseNumber(String leaseNumber) {
        this.leaseNumber = leaseNumber;
    }

    public BigDecimal getLeaseTerm() {
        return leaseTerm;
    }

    public void setLeaseTerm(BigDecimal leaseTerm) {
        this.leaseTerm = leaseTerm;
    }

    public BigDecimal getLeaseRental() {
        return leaseRental;
    }

    public void setLeaseRental(BigDecimal leaseRental) {
        this.leaseRental = leaseRental;
    }

    public String getLesseeName() {
        return lesseeName;
    }

    public void setLesseeName(String lesseeName) {
        this.lesseeName = lesseeName;
    }

    public String getSubleaseBaUnitId() {
        return subleaseBaUnitId;
    }

    public void setSubleaseBaUnitId(String subleaseBaUnitId) {
        this.subleaseBaUnitId = subleaseBaUnitId;
    }

    public String getSubleaseNumber() {
        return subleaseNumber;
    }

    public void setSubleaseNumber(String subleaseNumber) {
        this.subleaseNumber = subleaseNumber;
    }

    public String getSublesseeName() {
        return sublesseeName;
    }

    public void setSublesseeName(String sublesseeName) {
        this.sublesseeName = sublesseeName;
    }

    public String getApplicationsWhereFound() {
        return applicationsWhereFound;
    }

    public void setApplicationsWhereFound(String applicationsWhereFound) {
        this.applicationsWhereFound = applicationsWhereFound;
    }
}

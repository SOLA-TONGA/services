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
package org.sola.services.ejb.search.repository.entities;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import org.sola.services.common.repository.entities.AbstractReadOnlyEntity;

public class AllotmentVerifier extends AbstractReadOnlyEntity {

    @Column(name = "lot_id")
    private String lotbaUnitId;
    @Column(name = "deed_num")
    private String deedNumber;
    @Column
    private String folio;
    @Column(name = "holder_name")
    private String holderName;
    @Column(name = "island_id")
    private String islandId;
    @Column(name = "town_id")
    private String townId;
    @Column(name = "land_use_code")
    private String landUseCode;
    @Column(name = "lot_area")
    private BigDecimal lotArea;
    @Column(name = "reg_date")
    private Date lotRegistrationDate;
    @Column(name = "lease_linked")
    boolean leaseLinked; 

    public AllotmentVerifier() {
        super();
    }

    public String getLotbaUnitId() {
        return lotbaUnitId;
    }

    public void setLotbaUnitId(String lotbaUnitId) {
        this.lotbaUnitId = lotbaUnitId;
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

    public String getHolderName() {
        return holderName;
    }

    public void setHolderName(String holderName) {
        this.holderName = holderName;
    }

    public String getIslandId() {
        return islandId;
    }

    public void setIslandId(String islandId) {
        this.islandId = islandId;
    }

    public String getTownId() {
        return townId;
    }

    public void setTownId(String townId) {
        this.townId = townId;
    }

    public String getLandUseCode() {
        return landUseCode;
    }

    public void setLandUseCode(String landUseCode) {
        this.landUseCode = landUseCode;
    }

    public BigDecimal getLotArea() {
        return lotArea;
    }

    public void setLotArea(BigDecimal lotArea) {
        this.lotArea = lotArea;
    }

    public Date getLotRegistrationDate() {
        return lotRegistrationDate;
    }

    public void setLotRegistrationDate(Date lotRegistrationDate) {
        this.lotRegistrationDate = lotRegistrationDate;
    }

    public boolean isLeaseLinked() {
        return leaseLinked;
    }

    public void setLeaseLinked(boolean leaseLinked) {
        this.leaseLinked = leaseLinked;
    } 
}
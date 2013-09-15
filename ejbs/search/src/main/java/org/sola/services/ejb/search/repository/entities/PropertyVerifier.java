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
import org.sola.services.common.repository.entities.AbstractReadOnlyEntity;

public class PropertyVerifier extends AbstractReadOnlyEntity {

    public static final String QUERY_PARAM_FIRST_PART = "firstPart";
    public static final String QUERY_PARAM_LAST_PART = "lastPart";
    public static final String QUERY_PARAM_LEASE_NUM = "leaseNum";
    public static final String QUERY_PARAM_APPLICATION_NUMBER = "applicationNumber";
    private LeaseVerifier leaseVerifier;
    private AllotmentVerifier allotmentVerifier;
    private String applicationsWhereFound;

    public PropertyVerifier() {
        super();
        allotmentVerifier = new AllotmentVerifier();
        leaseVerifier = new LeaseVerifier();
    }

    public PropertyVerifier(LeaseVerifier lease, AllotmentVerifier lot,
            String apps) {
        super();
        leaseVerifier = lease;
        allotmentVerifier = lot;
        applicationsWhereFound = apps;
    }

    public String getLotbaUnitId() {
        return allotmentVerifier == null ? null : allotmentVerifier.getLotbaUnitId();
    }

    public void setLotbaUnitId(String lotbaUnitId) {
        if (allotmentVerifier != null) {
            allotmentVerifier.setLotbaUnitId(lotbaUnitId);
        }
    }

    public String getDeedNumber() {
        return allotmentVerifier == null ? null : allotmentVerifier.getDeedNumber();
    }

    public void setDeedNumber(String deedNumber) {
        if (allotmentVerifier != null) {
            allotmentVerifier.setDeedNumber(deedNumber);
        }
    }

    public String getFolio() {
        return allotmentVerifier == null ? null : allotmentVerifier.getFolio();
    }

    public void setFolio(String folio) {
        if (allotmentVerifier != null) {
            allotmentVerifier.setFolio(folio);
        }
    }

    public String getHolderName() {
        return allotmentVerifier == null ? null : allotmentVerifier.getHolderName();
    }

    public void setHolderName(String holderName) {
        if (allotmentVerifier != null) {
            allotmentVerifier.setHolderName(holderName);
        }
    }

    public String getIslandId() {
        return allotmentVerifier == null ? null : allotmentVerifier.getIslandId();
    }

    public void setIslandId(String islandId) {
        if (allotmentVerifier != null) {
            allotmentVerifier.setIslandId(islandId);
        }
    }

    public String getTownId() {
        return allotmentVerifier == null ? null : allotmentVerifier.getTownId();
    }

    public void setTownId(String townId) {
        if (allotmentVerifier != null) {
            allotmentVerifier.setTownId(townId);
        }
    }

    public String getLandUseCode() {
        return allotmentVerifier == null ? null : allotmentVerifier.getLandUseCode();
    }

    public void setLandUseCode(String landUseCode) {
        if (allotmentVerifier != null) {
            allotmentVerifier.setLandUseCode(landUseCode);
        }
    }

    public BigDecimal getLotArea() {
        return allotmentVerifier == null ? null : allotmentVerifier.getLotArea();
    }

    public void setLotArea(BigDecimal lotArea) {
        if (allotmentVerifier != null) {
            allotmentVerifier.setLotArea(lotArea);
        }
    }

    public Date getLotRegistrationDate() {
        return allotmentVerifier == null ? null : allotmentVerifier.getLotRegistrationDate();
    }

    public void setLotRegistrationDate(Date lotRegistrationDate) {
        if (allotmentVerifier != null) {
            allotmentVerifier.setLotRegistrationDate(lotRegistrationDate);
        }
    }

    public boolean isLeaseLinked() {
        return allotmentVerifier == null ? false : allotmentVerifier.isLeaseLinked();
    }

    public void setLeaseLinked(boolean leaseLinked) {
        if (allotmentVerifier != null) {
            allotmentVerifier.setLeaseLinked(leaseLinked);
        }
    }

    public String getLeaseBaUnitId() {
        return leaseVerifier == null ? null : leaseVerifier.getLeaseBaUnitId();
    }

    public void setLeaseBaUnitId(String leaseBaUnitId) {
        if (leaseVerifier != null) {
            leaseVerifier.setLeaseBaUnitId(leaseBaUnitId);
        }
    }

    public String getLeaseNumber() {
        return leaseVerifier == null ? null : leaseVerifier.getLeaseNumber();
    }

    public void setLeaseNumber(String leaseNumber) {
        if (leaseVerifier != null) {
            leaseVerifier.setLeaseNumber(leaseNumber);
        }
    }

    public String getLesseeName() {
        return leaseVerifier == null ? null : leaseVerifier.getLesseeName();
    }

    public void setLesseeName(String lesseeName) {
        if (leaseVerifier != null) {
            leaseVerifier.setLesseeName(lesseeName);
        }
    }

    public BigDecimal getLeaseArea() {
        return leaseVerifier == null ? null : leaseVerifier.getLeaseArea();
    }

    public void setLeaseArea(BigDecimal leaseArea) {
        if (leaseVerifier != null) {
            leaseVerifier.setLeaseArea(leaseArea);
        }
    }

    public BigDecimal getLeaseRental() {
        return leaseVerifier == null ? null : leaseVerifier.getLeaseRental();
    }

    public void setLeaseRental(BigDecimal leaseRental) {
        if (leaseVerifier != null) {
            leaseVerifier.setLeaseRental(leaseRental);
        }
    }

    public BigDecimal getLeaseTerm() {
        return leaseVerifier == null ? null : leaseVerifier.getLeaseTerm();
    }

    public void setLeaseTerm(BigDecimal leaseTerm) {
        if (leaseVerifier != null) {
            leaseVerifier.setLeaseTerm(leaseTerm);
        }
    }

    public String getApplicationsWhereFound() {
        return applicationsWhereFound;
    }

    public void setApplicationsWhereFound(String applicationsWhereFound) {
        this.applicationsWhereFound = applicationsWhereFound;
    }
}

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
package org.sola.services.ejb.administrative.repository.entities;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import org.sola.common.SOLAException;
import org.sola.common.StringUtility;
import org.sola.common.messaging.ServiceMessage;
import org.sola.services.common.LocalInfo;
import org.sola.services.common.repository.*;
import org.sola.services.common.repository.entities.AbstractVersionedEntity;
import org.sola.services.ejb.cadastre.businesslogic.CadastreEJBLocal;
import org.sola.services.ejb.cadastre.repository.entities.CadastreObject;
import org.sola.services.ejb.source.businesslogic.SourceEJBLocal;
import org.sola.services.ejb.source.repository.entities.Source;
import org.sola.services.ejb.system.br.Result;
import org.sola.services.ejb.system.businesslogic.SystemEJBLocal;
import org.sola.services.ejb.transaction.businesslogic.TransactionEJBLocal;
import org.sola.services.ejb.transaction.repository.entities.Transaction;
import org.sola.services.ejb.transaction.repository.entities.TransactionStatusType;

/**
 *
 * @author soladev
 */
@Table(schema = "administrative", name = "ba_unit")
public class BaUnit extends AbstractVersionedEntity {

    public static final String QUERY_PARAMETER_TRANSACTIONID = "transactionId";
    public static final String QUERY_PARAMETER_FIRSTPART = "firstPart";
    public static final String QUERY_PARAMETER_LASTPART = "lastPart";
    public static final String QUERY_PARAMETER_ID = "id";
    public static final String QUERY_PARAMETER_COLIST = "colist";
    public static final String QUERY_WHERE_BYTRANSACTIONID = "transaction_id = "
            + "#{" + QUERY_PARAMETER_TRANSACTIONID + "} or id in "
            + "(select ba_unit_id from administrative.ba_unit_target where "
            + "transaction_id = #{" + QUERY_PARAMETER_TRANSACTIONID + "})";
    public static final String QUERY_WHERE_BY_TRANSACTION_ID_EXTENDED =
            "transaction_id = #{" + QUERY_PARAMETER_TRANSACTIONID + "} OR id IN "
            + "(SELECT rrr.ba_unit_id FROM administrative.rrr rrr  "
            + "WHERE rrr.transaction_id = #{" + QUERY_PARAMETER_TRANSACTIONID + "} "
            + "UNION "
            + "SELECT n.ba_unit_id FROM administrative.notation n "
            + "WHERE n.ba_unit_id IS NOT NULL AND n.transaction_id = #{"
            + QUERY_PARAMETER_TRANSACTIONID + "})";
    public static final String QUERY_WHERE_BYPROPERTYCODE =
            "name_firstpart = #{" + QUERY_PARAMETER_FIRSTPART + "} AND "
            + "name_lastpart = #{" + QUERY_PARAMETER_LASTPART + "}";
    public static final String QUERY_WHERE_BYBAUNITID =
            "id = #{" + QUERY_PARAMETER_ID + "}";
    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "type_code")
    private String typeCode;
    @Column(name = "name")
    private String name;
    @Column(name = "name_firstpart")
    private String nameFirstpart;
    @Column(name = "name_lastpart")
    private String nameLastpart;
    @Column(name = "status_code", updatable = false)
    private String statusCode;
    @Column(name = "transaction_id", updatable = false)
    private String transactionId;
    @Column(name = "creation_date", updatable = false)
    private Date folioRegDate;
    @Column(name = "expiration_date", updatable = false)
    private String cancellationDate;
    // Tonga customization
    @Column(name = "registered_name")
    private String registeredName;
    // Tonga customization - add LandUseCode to ba_unit instead of CadastreOjbect 
    @Column(name = "land_use_code")
    private String landUseCode;
    @ChildEntityList(parentIdField = "baUnitId")
    private List<Rrr> rrrList;
    @ChildEntityList(parentIdField = "baUnitId")
    private List<BaUnitNotation> baUnitNotationList;
    // SOLA Tonga customization
    @ChildEntityList(parentIdField = "baUnitId")
    private List<BaUnitArea> baUnitAreaList;
    @ExternalEJB(ejbLocalClass = SourceEJBLocal.class,
            loadMethod = "getSources", saveMethod = "saveSource")
    @ChildEntityList(parentIdField = "baUnitId", childIdField = "sourceId",
            manyToManyClass = SourceDescribesBaUnit.class)
    private List<Source> sourceList;
    @ExternalEJB(ejbLocalClass = CadastreEJBLocal.class,
            loadMethod = "getCadastreObjects", saveMethod = "saveCadastreObject")
    @ChildEntityList(parentIdField = "baUnitId", childIdField = "spatialUnitId",
            manyToManyClass = BaUnitContainsSpatialUnit.class)
    private List<CadastreObject> cadastreObjectList;
    private Boolean locked;
    @ChildEntityList(parentIdField = "baUnitId")
    private List<ChildBaUnitInfo> childBaUnits;
    @ChildEntityList(parentIdField = "baUnitId")
    private List<ParentBaUnitInfo> parentBaUnits;
    @Column(insertable = false, updatable = false, name = "pending_action_code")
    @AccessFunctions(onSelect = "administrative.get_ba_unit_pending_action(id)")
    private String pendingActionCode;
    // Tonga Customization - calculated Area Size is not required. 
    //@Column(insertable = false, updatable = false, name = "calculated_area_size")
    //@AccessFunctions(onSelect = "administrative.get_calculated_area_size_action(#{" + QUERY_PARAMETER_COLIST + "})")
    private BigDecimal calculatedAreaSize;

    public BigDecimal getCalculatedAreaSize() {
        return calculatedAreaSize;
    }

    public void setCalculatedAreaSize(BigDecimal calculatedAreaSize) {
        this.calculatedAreaSize = calculatedAreaSize;
    }

    public BaUnit() {
        super();
    }

    private Transaction getTransaction() {
        Transaction result = null;
        TransactionEJBLocal transactionEJB = RepositoryUtility.tryGetEJB(TransactionEJBLocal.class);
        if (transactionEJB != null) {
            result = transactionEJB.getTransactionById(getTransactionId(), Transaction.class);
        }
        return result;
    }

    public String getId() {
        id = id == null ? generateId() : id;
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        // Prevent changes to the status code if the value has been loaded from the database. 
        // Updates to the status code are made via the BaUnitStatusChanger
        if (isNew()) {
            this.statusCode = statusCode;
        }
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        // Prevent changes to the transaction id if the value has been loaded from the database.
        if (isNew()) {
            this.transactionId = transactionId;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameFirstpart() {
        return nameFirstpart;
    }

    public void setNameFirstpart(String nameFirstpart) {
        this.nameFirstpart = nameFirstpart;
    }

    public String getNameLastpart() {
        return nameLastpart;
    }

    public void setNameLastpart(String nameLastpart) {
        this.nameLastpart = nameLastpart;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public Date getFolioRegDate() {
        return folioRegDate;
    }

    public void setFolioRegDate(Date folioRegDate) {
        this.folioRegDate = folioRegDate;
    }

    public String getCancellationDate() {
        return cancellationDate;
    }

    public void setCancellationDate(String cancellationDate) {
        this.cancellationDate = cancellationDate;
    }

    public String getRegisteredName() {
        return registeredName;
    }

    public void setRegisteredName(String registeredName) {
        this.registeredName = registeredName;
    }

    public String getLandUseCode() {
        return landUseCode;
    }

    public void setLandUseCode(String landUseCode) {
        this.landUseCode = landUseCode;
    }

    public List<BaUnitNotation> getBaUnitNotationList() {
        return baUnitNotationList;
    }

    public void setBaUnitNotationList(List<BaUnitNotation> baUnitNotationList) {
        this.baUnitNotationList = baUnitNotationList;
    }

    public List<CadastreObject> getCadastreObjectList() {
        return cadastreObjectList;
    }

    public void setCadastreObjectList(List<CadastreObject> cadastreObjectList) {
        this.cadastreObjectList = cadastreObjectList;
    }

    public List<Rrr> getRrrList() {
        return rrrList;
    }

    public void setRrrList(List<Rrr> rrrList) {
        this.rrrList = rrrList;
    }

    public List<Source> getSourceList() {
        return sourceList;
    }

    public void setSourceList(List<Source> sourceList) {
        this.sourceList = sourceList;
    }

    public List<ChildBaUnitInfo> getChildBaUnits() {
        return childBaUnits;
    }

    public void setChildBaUnits(List<ChildBaUnitInfo> childBaUnits) {
        this.childBaUnits = childBaUnits;
    }

    public List<ParentBaUnitInfo> getParentBaUnits() {
        return parentBaUnits;
    }

    public void setParentBaUnits(List<ParentBaUnitInfo> parentBaUnits) {
        this.parentBaUnits = parentBaUnits;
    }

    public String getPendingActionCode() {
        return pendingActionCode;
    }

    public void setPendingActionCode(String pendingActionCode) {
        this.pendingActionCode = pendingActionCode;
    }

    public List<BaUnitArea> getBaUnitAreaList() {
        return baUnitAreaList;
    }

    public void setBaUnitAreaList(List<BaUnitArea> baUnitAreaList) {
        this.baUnitAreaList = baUnitAreaList;
    }

    public Boolean isLocked() {
        if (locked == null) {
            locked = false;
            Transaction transaction = getTransaction();
            if (transaction != null
                    && transaction.getStatusCode().equals(TransactionStatusType.COMPLETED)) {
                locked = true;
            }
        }
        return locked;
    }

    private String generateBaUnitNumber() {
        String result = "";
        SystemEJBLocal systemEJB = RepositoryUtility.tryGetEJB(SystemEJBLocal.class);
        if (systemEJB != null) {
            Result newNumberResult = systemEJB.checkRuleGetResultSingle("generate-baunit-nr", null);
            if (newNumberResult != null && newNumberResult.getValue() != null) {
                result = newNumberResult.getValue().toString();
            }
        }
        return result;
    }

    @Override
    public void preSave() {
        if (this.isNew()) {
            setTransactionId(LocalInfo.getTransactionId());
        }
        if ((StringUtility.isEmpty(getNameFirstpart()) || StringUtility.isEmpty(getNameLastpart()))
                && (isAllotment() || isLease() || isSublease())) {
            // The user must specify the name for the ba unit (Lease or Allotment)
            throw new SOLAException(ServiceMessage.EJB_ADMINISTRATIVE_DEED_NUM_REQUIRED);
        }
        if (StringUtility.isEmpty(getName())) {
            if (isAllotment() || isSublease()) {
                setName(getNameFirstpart() + "/" + getNameLastpart());
            } else {
                setName(getNameFirstpart());
            }
        }

        // Set the reference id on the source so that it is possible to 
        // generate a number for the source that uses the BA Unit number
        if (getSourceList() != null && getSourceList().size() > 0) {
            for (Source source : getSourceList()) {
                source.setLaNrReferenceId(getId());
            }
        }
        super.preSave();
    }

    /**
     * Returns true if the property is a Lease
     */
    public boolean isLease() {
        return BaUnitType.CODE_LEASED_UNIT.equals(this.getTypeCode());
    }

    /**
     * Returns true if the property is an Allotment (either Town or Tax
     * allotment)
     */
    public boolean isAllotment() {
        return BaUnitType.CODE_TOWN_ALLOTMENT_UNIT.equals(this.getTypeCode())
                || BaUnitType.CODE_TAX_UNIT.equals(this.getTypeCode());
    }

    /**
     * Returns true if the property is the estate of a Noble or the King
     */
    public boolean isEstate() {
        return BaUnitType.CODE_ESTATE_UNIT.equals(this.getTypeCode());
    }

    /**
     * Returns true if the property represents a Town
     */
    public boolean isTown() {
        return BaUnitType.CODE_TOWN_UNIT.equals(this.getTypeCode());
    }

    /**
     * Returns true if the property represents an island
     */
    public boolean isIsland() {
        return BaUnitType.CODE_ISLAND_UNIT.equals(this.getTypeCode());
    }

    /**
     * Returns true if the property represents a sublease
     */
    public boolean isSublease() {
        return BaUnitType.CODE_SUBLEASE_UNIT.equals(this.getTypeCode());
    }
}

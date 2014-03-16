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
import org.sola.common.StringUtility;
import org.sola.services.common.repository.entities.AbstractEntity;

public class BaUnitSearchParams extends AbstractEntity {

    public static final String SEARCH_TYPE_ALLOTMENT = "allotment";
    public static final String SEARCH_TYPE_LEASE = "lease";
    public static final String SEARCH_TYPE_SUBLEASE = "sublease";
    public static final String SEARCH_TYPE_ESTATE = "estate";
    public static final String SEARCH_TYPE_TOWN = "town";
    public static final String SEARCH_TYPE_MORTGAGE = "mortgage";
    private String nameFirstPart;
    private String nameLastPart;
    private String ownerName;
    private String searchType;
    private String registryBook;
    private String registryPageRef;
    private String parcelName;
    private Date registeredDateFrom;
    private Date registeredDateTo;
    private boolean taxAllotment;
    private boolean townAllotment;
    private String otherRightholder;
    private String townId;
    private String islandId;
    private String estateName;
    private String rrrReference;

    public BaUnitSearchParams() {
        super();
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

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getSearchType() {
        return searchType;
    }

    public void setSearchType(String searchType) {
        this.searchType = searchType;
    }

    public String getRegistryBook() {
        return registryBook;
    }

    public void setRegistryBook(String registryBook) {
        this.registryBook = registryBook;
    }

    public String getRegistryPageRef() {
        return registryPageRef;
    }

    public void setRegistryPageRef(String registryPageRef) {
        this.registryPageRef = registryPageRef;
    }

    public String getParcelName() {
        return parcelName;
    }

    public void setParcelName(String parcelName) {
        this.parcelName = parcelName;
    }

    public Date getRegisteredDateFrom() {
        return registeredDateFrom;
    }

    public void setRegisteredDateFrom(Date registeredDateFrom) {
        this.registeredDateFrom = registeredDateFrom;
    }

    public Date getRegisteredDateTo() {
        return registeredDateTo;
    }

    public void setRegisteredDateTo(Date registeredDateTo) {
        this.registeredDateTo = registeredDateTo;
    }

    public boolean isTaxAllotment() {
        return taxAllotment;
    }

    public void setTaxAllotment(boolean taxAllotment) {
        this.taxAllotment = taxAllotment;
    }

    public boolean isTownAllotment() {
        return townAllotment;
    }

    public void setTownAllotment(boolean townAllotment) {
        this.townAllotment = townAllotment;
    }

    public String getOtherRightholder() {
        return otherRightholder;
    }

    public void setOtherRightholder(String otherRightholder) {
        this.otherRightholder = otherRightholder;
    }

    public String getTownId() {
        return townId;
    }

    public void setTownId(String townId) {
        this.townId = townId;
    }

    public String getIslandId() {
        return islandId;
    }

    public void setIslandId(String islandId) {
        this.islandId = islandId;
    }
      
    /**
     * Returns the query parameter for the Registry Book Reference value
     * including special space delimiter characters to ensure a more accurate
     * match.
     */
    public String getRegistryBookRefQueryParam() {
        String result = StringUtility.isEmpty(getRegistryBook()) ? ""
                : getRegistryBook();
        result += StringUtility.isEmpty(getRegistryPageRef()) ? ""
                : " " + getRegistryPageRef();
        return result.trim();
    }

    /**
     * Indicates if the search is of the specified type.
     *
     * @param searchType
     * @return
     */
    public boolean isSearchType(String searchType) {
        return searchType.equals(getSearchType());
    }

    public String getEstateName() {
        return estateName;
    }

    public void setEstateName(String estateName) {
        this.estateName = estateName;
    }
    
    public String getRrrReference() {
        return rrrReference;
    }
    
    public void setRrrReference(String rrrReference) {
        this.rrrReference = rrrReference;
    }
    
}

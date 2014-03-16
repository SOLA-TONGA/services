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
import org.sola.services.common.repository.entities.AbstractReadOnlyEntity;

/**
 *
 * @author soladev
 */
public class CashierImport extends AbstractReadOnlyEntity {

    public static final String QUERY_PARAMETER_LEASE_NUMBER = "leaseNumber";
    public static final String PAYMENT_TYPE_LEASE = "lease payment";
    private int recordId;
    private String leaseNumber;
    private Date paymentDate;
    private String paymentParticulars;
    private String paymentDescription;
    private BigDecimal totalPayment;
    private String receiptNumber;
    private BigDecimal rentGov;
    private BigDecimal rentalTax;
    private BigDecimal deedLease;
    private BigDecimal registerFee;
    private BigDecimal transferFee;
    private BigDecimal surveyFee;

    public CashierImport() {
        super();
    }

    public int getRecordId() {
        return recordId;
    }

    public void setRecordId(int recordId) {
        this.recordId = recordId;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getPaymentParticulars() {
        return paymentParticulars;
    }

    public void setPaymentParticulars(String paymentParticulars) {
        this.paymentParticulars = paymentParticulars;
    }

    public String getPaymentDescription() {
        return paymentDescription;
    }

    public void setPaymentDescription(String paymentDescription) {
        this.paymentDescription = paymentDescription;
    }

    public BigDecimal getTotalPayment() {
        return totalPayment;
    }

    public void setTotalPayment(BigDecimal totalPayment) {
        this.totalPayment = totalPayment;
    }

    public String getReceiptNumber() {
        return receiptNumber;
    }

    public void setReceiptNumber(String receiptNumber) {
        this.receiptNumber = receiptNumber;
    }

    public BigDecimal getRentGov() {
        return rentGov;
    }

    public void setRentGov(BigDecimal rentGov) {
        this.rentGov = rentGov;
    }

    public BigDecimal getRentalTax() {
        return rentalTax;
    }

    public void setRentalTax(BigDecimal rentalTax) {
        this.rentalTax = rentalTax;
    }

    public BigDecimal getDeedLease() {
        return deedLease;
    }

    public void setDeedLease(BigDecimal deedLease) {
        this.deedLease = deedLease;
    }

    public BigDecimal getRegisterFee() {
        return registerFee;
    }

    public void setRegisterFee(BigDecimal registerFee) {
        this.registerFee = registerFee;
    }

    public BigDecimal getTransferFee() {
        return transferFee;
    }

    public void setTransferFee(BigDecimal transferFee) {
        this.transferFee = transferFee;
    }

    public BigDecimal getSurveyFee() {
        return surveyFee;
    }

    public void setSurveyFee(BigDecimal surveyFee) {
        this.surveyFee = surveyFee;
    }

    public String getLeaseNumber() {
        return leaseNumber;
    }

    public void setLeaseNumber(String leaseNumber) {
        this.leaseNumber = leaseNumber;
    }
}

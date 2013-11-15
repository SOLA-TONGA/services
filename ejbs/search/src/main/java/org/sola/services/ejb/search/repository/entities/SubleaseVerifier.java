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

import javax.persistence.Column;
import org.sola.services.common.repository.entities.AbstractReadOnlyEntity;

/**
 * Class used to verify sublease details
 *
 * @author soladev
 */
public class SubleaseVerifier extends AbstractReadOnlyEntity {

    @Column(name = "sublease_id")
    private String subleaseBaUnitId;
    @Column(name = "sublease_num")
    private String subleaseNumber;
    @Column(name = "sublease_lease_linked")
    private boolean subleaseLeaseLinked;
    @Column(name = "sublease_lot_linked")
    private boolean subleaseLotLinked;

    public SubleaseVerifier() {
        super();
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

    public boolean isSubleaseLeaseLinked() {
        return subleaseLeaseLinked;
    }

    public void setSubleaseLeaseLinked(boolean subleaseLeaseLinked) {
        this.subleaseLeaseLinked = subleaseLeaseLinked;
    }

    public boolean isSubleaseLotLinked() {
        return subleaseLotLinked;
    }

    public void setSubleaseLotLinked(boolean subleaseLotLinked) {
        this.subleaseLotLinked = subleaseLotLinked;
    } 
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sola.services.ejb.administrative.repository.entities;

import javax.persistence.Table;
import org.sola.services.common.repository.entities.AbstractReadOnlyEntity;

/**
 *
 * @author Admin
 */
@Table(name = "rrr", schema = "administrative")
public class CashierImport extends AbstractReadOnlyEntity{
    public static final String QUERY_LEASE_NUMBER = "leaseNum";
    public CashierImport(){
        super();
    }
}

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
package org.sola.services.ejb.administrative.businesslogic;

import org.apache.ibatis.jdbc.SqlBuilder;
import static org.apache.ibatis.jdbc.SqlBuilder.*;
import org.sola.services.ejb.administrative.repository.entities.CashierImport;
import org.sola.services.ejb.administrative.repository.entities.RrrPaymentHistory;

/**
 * Helper class used to build complete SQL strings for advanced SQL queries
 *
 * @author soladev
 */
public class AdministrativeSqlProvider {

    /**
     * SQL to retrieve estates from the ba_unit table including a link to the
     * island the estate is part of. If there is no island relationship, just
     * the details for the estate are returned.
     *
     * @return SQL string to retrieve estate details
     */
    public static String buildGetEstatesSql() {
        String sql;
        BEGIN();
        SELECT("DISTINCT ba.id AS code");
        SELECT("ba.name_firstpart AS display_value");
        SELECT("NULL AS description");
        SELECT("CASE WHEN ba.status_code = 'current' THEN 'c' ELSE 'x' END AS status");
        SELECT("(SELECT string_agg(req1.from_ba_unit_id, '|') "
                    + "FROM administrative.required_relationship_baunit req1 "
                    + "WHERE req1.to_ba_unit_id = ba.id "
                    + "AND req1.relation_code = 'island' ) AS island_id");
        FROM("administrative.ba_unit ba");
        WHERE("ba.type_code = 'estateUnit'");
        ORDER_BY("ba.name_firstpart");
        sql = SqlBuilder.SQL();
        return sql;
    }

    public static String buildGetIslandsSql() {
        String sql;
        BEGIN();
        SELECT("DISTINCT ba.id AS code");
        SELECT("ba.name_firstpart AS display_value");
        SELECT("NULL AS description");
        SELECT("CASE WHEN ba.status_code = 'current' THEN 'c' ELSE 'x' END AS status");
        FROM("administrative.ba_unit ba ");
        WHERE("ba.type_code = 'islandUnit'");
        ORDER_BY("ba.name_firstpart");
        sql = SqlBuilder.SQL();
        return sql;
    }

    public static String buildGetTownsSql() {
        String sql;
        BEGIN();
        SELECT("DISTINCT ba.id AS code");
        SELECT("ba.name_firstpart AS display_value");
        SELECT("NULL AS description");
        SELECT("CASE WHEN ba.status_code = 'current' THEN 'c' ELSE 'x' END AS status");
        SELECT("ba_rel.from_ba_unit_id AS island_id");
        FROM("administrative.ba_unit ba "
                + " LEFT OUTER JOIN administrative.required_relationship_baunit ba_rel "
                + " ON ba_rel.to_ba_unit_id = ba.id AND ba_rel.relation_code = 'island'");
        WHERE("ba.type_code = 'townUnit'");
        WHERE("LOWER(TRIM(ba.name_firstpart)) NOT IN ('', 'expire', 'invalid', 'valid')");
        ORDER_BY("ba.name_firstpart");
        sql = SqlBuilder.SQL();
        return sql;
    }

    /**
     * Obtains the payment history for the Rrr. Checks for records that were
     * updated by the cashier load process in the rrr_historic table to ensure
     * these are correctly displayed as payment records.
     */
    public static String buildPaymentHistorySql() {
        String sql;
        // Get the current rrr details from the rrr table
        BEGIN();
        SELECT("r.id");
        SELECT("r.due_date");
        SELECT("r.receipt_date");
        SELECT("r.receipt_reference");
        SELECT("r.receipt_amount");
        SELECT("r.cashier_update");
        SELECT("r.change_user");
        FROM("administrative.rrr r");
        WHERE("EXISTS "
                + "(SELECT r2.id FROM administrative.rrr r2"
                + " WHERE  r2.id = #{" + RrrPaymentHistory.QUERY_PARAMETER_ID + "}"
                + " AND    r.nr = r2.nr"
                + " AND    r.ba_unit_id = r2.ba_unit_id)");
        WHERE("(r.due_date IS NOT NULL OR r.receipt_date IS NOT NULL "
                + "OR r.receipt_reference IS NOT NULL OR r.receipt_amount IS NOT NULL)");
        sql = SQL() + " UNION ";
        // Get all historic records that are marked as Cashier Update
        SELECT("rh.id");
        SELECT("rh.due_date");
        SELECT("rh.receipt_date");
        SELECT("rh.receipt_reference");
        SELECT("rh.receipt_amount");
        SELECT("rh.cashier_update");
        SELECT("rh.change_user");
        FROM("administrative.rrr_historic rh");
        WHERE("rh.cashier_update = TRUE");
        WHERE("EXISTS "
                + "(SELECT r2.id FROM administrative.rrr r2"
                + " WHERE  r2.id = #{" + RrrPaymentHistory.QUERY_PARAMETER_ID + "}"
                + " AND    rh.nr = r2.nr"
                + " AND    rh.ba_unit_id = r2.ba_unit_id)");
        WHERE("(rh.due_date IS NOT NULL OR rh.receipt_date IS NOT NULL "
                + "OR rh.receipt_reference IS NOT NULL OR rh.receipt_amount IS NOT NULL)");
        sql += SQL() + " UNION ";
        // Get the history record just prior to any cashier updates
        // where the cashier update is on the rrr record
        SELECT("rh.id");
        SELECT("rh.due_date");
        SELECT("rh.receipt_date");
        SELECT("rh.receipt_reference");
        SELECT("rh.receipt_amount");
        SELECT("rh.cashier_update");
        SELECT("rh.change_user");
        FROM("administrative.rrr_historic rh");
        WHERE("rh.cashier_update = FALSE");
        WHERE("EXISTS "
                + "(SELECT r2.id FROM administrative.rrr r2"
                + " WHERE  r2.id = #{" + RrrPaymentHistory.QUERY_PARAMETER_ID + "}"
                + " AND    rh.nr = r2.nr"
                + " AND    rh.ba_unit_id = r2.ba_unit_id"
                + " AND    r2.cashier_update = TRUE"
                + " AND    r2.rowversion = (rh.rowversion + 1))");
        WHERE("(rh.due_date IS NOT NULL OR rh.receipt_date IS NOT NULL "
                + "OR rh.receipt_reference IS NOT NULL OR rh.receipt_amount IS NOT NULL)");
        
        sql += SQL() + " UNION ";
        // Get the history record just prior to any cashier updates
        // where the cashier update is on the rrr_historic record
        SELECT("rh.id");
        SELECT("rh.due_date");
        SELECT("rh.receipt_date");
        SELECT("rh.receipt_reference");
        SELECT("rh.receipt_amount");
        SELECT("rh.cashier_update");
        SELECT("rh.change_user");
        FROM("administrative.rrr_historic rh");
        WHERE("rh.cashier_update = FALSE");
        WHERE("EXISTS "
                + "(SELECT rh2.id FROM administrative.rrr_historic rh2"
                + " WHERE  rh2.id = #{" + RrrPaymentHistory.QUERY_PARAMETER_ID + "}"
                + " AND    rh.nr = rh2.nr"
                + " AND    rh.ba_unit_id = rh2.ba_unit_id"
                + " AND    rh2.cashier_update = TRUE"
                + " AND    rh2.rowversion = (rh.rowversion + 1))");
        WHERE("(rh.due_date IS NOT NULL OR rh.receipt_date IS NOT NULL "
                + "OR rh.receipt_reference IS NOT NULL OR rh.receipt_amount IS NOT NULL)");
        sql += SQL();

        return sql;
    }

    /**
     * Obtains the id of the RRR lease record based on the lease number
     */
    public static String buildGetRrrIdByLeaseNumberSql() {
        String sql;
        BEGIN();
        SELECT("r.id");
        FROM("administrative.rrr r");
        FROM("administrative.ba_unit b");
        WHERE("r.ba_unit_id = b.id");
        WHERE("b.name = #{" + CashierImport.QUERY_PARAMETER_LEASE_NUMBER + "}");
        WHERE("r.type_code IN ('lease', 'sublease')");
        WHERE("r.status_code = b.status_code");
        ORDER_BY("r.registration_date DESC");

        sql = SQL();
        sql += " LIMIT 1";

        return sql;
    }
}

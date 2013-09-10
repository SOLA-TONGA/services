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
package org.sola.services.ejb.administrative.businesslogic;

import org.apache.ibatis.jdbc.SqlBuilder;
import static org.apache.ibatis.jdbc.SqlBuilder.*;

/**
 * Helper class used to build complete SQL strings for advanced SQL queries
 *
 * @author soladev
 */
public class AdministrativeSqlProvider {
    
    /**
     * SQL to retrieve estates from the ba_unit table including a link to the island
     * the estate is part of. If there is no island relationship, just the details for
     * the estate are returned. 
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
        SELECT("ba_rel.from_ba_unit_id AS island_id");
        FROM("administrative.ba_unit ba "
                + " LEFT OUTER JOIN administrative.required_relationship_baunit ba_rel "
                + " ON ba_rel.to_ba_unit_id = ba.id AND ba_rel.relation_code = 'island'");
        WHERE("ba.type_code = 'estateUnit'");
        ORDER_BY("ba.name_firstpart");
        sql = SqlBuilder.SQL();
        return sql;
    }
    
    public static String buildGetDistrictsSql() {
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
        WHERE("ba.type_code = 'districtUnit'");
        ORDER_BY("ba.name_firstpart");
        sql = SqlBuilder.SQL();
        return sql;
    }
}

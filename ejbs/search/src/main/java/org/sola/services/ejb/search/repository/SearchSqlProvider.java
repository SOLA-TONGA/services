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
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sola.services.ejb.search.repository;

import static org.apache.ibatis.jdbc.SqlBuilder.*;
import org.sola.common.StringUtility;
import org.sola.services.ejb.search.repository.entities.BaUnitSearchParams;
import org.sola.services.ejb.search.repository.entities.BaUnitSearchResult;
import org.sola.services.ejb.search.repository.entities.PropertyVerifier;

/**
 *
 * @author soladev
 */
public class SearchSqlProvider {

    public static final String PARAM_APPLICATION_ID = "applicationId";
    private static final String APPLICATION_GROUP = "application";
    private static final String SERVICE_GROUP = "service";
    private static final String RRR_GROUP = "rrr";
    private static final String PROPERTY_GROUP = "Property";
    private static final String SOURCE_GROUP = "Source";
    private static final String AGENT_GROUP = "Agent";
    private static final String CONTACT_PERSON_GROUP = "Contact person";
    private static final String CHANGE_ACTION = "changed";
    private static final String ADDED_PROPERTY = "ADDED PROPERTY: ";
    private static final String DELETED_PROPERTY = "DELETED PROPERTY: ";
    private static final String ADDED_SOURCE = "ADDED DOCUMENT: Ref#";
    private static final String DELETED_SOURCE = "REMOVED DOCUMENT: Ref#";
    private static final String ADDED_AGENT = "ADDED AGENT: ";
    private static final String DELETED_AGENT = "REMOVED AGENT: ";
    private static final String ADDED_CONTACT_PERSON = "ADDED CONTACT PERSON: ";
    private static final String DELETED_CONTACT_PERSON = "REMOVED CONTACT PERSON: ";

    public static String buildApplicationLogSql() {
        String sql;
        int sortClassifier = 1;

        // Application 
        BEGIN();
        SELECT("'" + APPLICATION_GROUP + "' AS record_group");
        SELECT("'" + APPLICATION_GROUP + "' AS record_type");
        SELECT(sortClassifier + " as sort_classifier");
        SELECT("app1.id AS record_id");
        SELECT("app1.rowversion AS record_sequence");
        SELECT("app1.nr AS nr");
        SELECT("CASE WHEN COALESCE(prev.action_code, 'null') = app1.action_code "
                + " THEN '" + CHANGE_ACTION + "' ELSE app1.action_code END AS action_code");
        SELECT("NULL::text AS notation");
        SELECT("app1.change_time");
        SELECT("(SELECT (appuser.first_name::text || ' '::text) || appuser.last_name::text"
                + " FROM system.appuser"
                + " WHERE appuser.username::text = app1.change_user::text)"
                + " AS user_fullname");
        FROM("application.application app1 "
                + " LEFT JOIN application.application_historic prev "
                + " ON app1.id = prev.id AND (app1.rowversion - 1) = prev.rowversion");
        WHERE("app1.id = #{" + PARAM_APPLICATION_ID + "}");

        sql = SQL() + " UNION ";
        sortClassifier++;

        //Application History
        BEGIN();
        SELECT("'" + APPLICATION_GROUP + "' AS record_group");
        SELECT("'" + APPLICATION_GROUP + "' AS record_type");
        SELECT(sortClassifier + " as sort_classifier");
        SELECT("app_hist.id AS record_id");
        SELECT("app_hist.rowversion AS record_sequence");
        SELECT("app_hist.nr AS nr");
        SELECT("CASE WHEN COALESCE(prev.action_code, 'null') = app_hist.action_code "
                + " THEN '" + CHANGE_ACTION + "' ELSE app_hist.action_code END AS action_code");
        SELECT("NULL::text AS notation");
        SELECT("app_hist.change_time");
        SELECT("(SELECT (appuser.first_name::text || ' '::text) || appuser.last_name::text"
                + " FROM system.appuser"
                + " WHERE appuser.username::text = app_hist.change_user::text)"
                + " AS user_fullname");
        FROM("application.application_historic app_hist "
                + " LEFT JOIN application.application_historic prev "
                + " ON app_hist.id = prev.id AND (app_hist.rowversion - 1) = prev.rowversion");
        WHERE("app_hist.id = #{" + PARAM_APPLICATION_ID + "}");

        sql = sql + SQL() + " UNION ";
        sortClassifier++;

        // Service
        BEGIN();
        SELECT("'" + SERVICE_GROUP + "' AS record_group");
        SELECT("ser1.request_type_code AS record_type");
        SELECT(sortClassifier + " as sort_classifier");
        SELECT("ser1.id AS record_id");
        SELECT("ser1.rowversion AS record_sequence");
        SELECT("ser1.service_order::text AS nr");
        SELECT("CASE WHEN COALESCE(prev.action_code, 'null') = ser1.action_code "
                + " THEN '" + CHANGE_ACTION + "' ELSE ser1.action_code END AS action_code");
        SELECT("NULL::text AS notation");
        SELECT("ser1.change_time");
        SELECT("(SELECT (appuser.first_name::text || ' '::text) || appuser.last_name::text"
                + " FROM system.appuser"
                + " WHERE appuser.username::text = ser1.change_user::text)"
                + " AS user_fullname");
        FROM("application.service ser1 "
                + " LEFT JOIN application.service_historic prev "
                + " ON ser1.id = prev.id AND (ser1.rowversion - 1) = prev.rowversion");
        WHERE("ser1.application_id = #{" + PARAM_APPLICATION_ID + "}");

        sql = sql + SQL() + " UNION ";
        sortClassifier++;

        // Service History
        BEGIN();
        SELECT("'" + SERVICE_GROUP + "' AS record_group");
        SELECT("ser_hist.request_type_code AS record_type");
        SELECT(sortClassifier + " as sort_classifier");
        SELECT("ser_hist.id AS record_id");
        SELECT("ser_hist.rowversion AS record_sequence");
        SELECT("ser_hist.service_order::text AS nr");
        SELECT("CASE WHEN COALESCE(prev.action_code, 'null') = ser_hist.action_code "
                + " THEN '" + CHANGE_ACTION + "' ELSE ser_hist.action_code END AS action_code");
        SELECT("NULL::text AS notation");
        SELECT("ser_hist.change_time");
        SELECT("(SELECT (appuser.first_name::text || ' '::text) || appuser.last_name::text"
                + " FROM system.appuser"
                + " WHERE appuser.username::text = ser_hist.change_user::text)"
                + " AS user_fullname");
        FROM("application.service ser");
        FROM("application.service_historic ser_hist"
                + " LEFT JOIN application.service_historic prev "
                + " ON ser_hist.id = prev.id AND (ser_hist.rowversion - 1) = prev.rowversion");
        WHERE("ser.application_id = #{" + PARAM_APPLICATION_ID + "}");
        WHERE("ser_hist.rowidentifier = ser.rowidentifier");

//        sql = sql + SQL() + " UNION ";
//        sortClassifier++;

//        // RRR
//        BEGIN();
//        SELECT("'" + RRR_GROUP + "' AS record_group");
//        SELECT("rrr.type_code AS record_type");
//        SELECT(sortClassifier + " as sort_classifier");
//        SELECT("rrr.id AS record_id");       
//        SELECT("rrr.rowversion AS record_sequence");
//        SELECT("rrr.nr::text AS nr");
//        SELECT("'changesMade'  AS action_code");
//        SELECT("note.notation_text AS notation");
//        SELECT("COALESCE(note.change_time, rrr.change_time) AS change_time");
//        SELECT("(SELECT (appuser.first_name::text || ' '::text) || appuser.last_name::text"
//                + " FROM system.appuser"
//                + " WHERE appuser.username::text = COALESCE(note.change_user, rrr.change_user))"
//                + " AS user_fullname");
//        FROM("application.service ser");
//        FROM("transaction.transaction tran");
//        FROM("administrative.rrr LEFT JOIN administrative.notation note ON "
//                + " rrr.id = note.rrr_id");
//        WHERE("ser.application_id = #{" + PARAM_APPLICATION_ID + "}");
//        WHERE("tran.from_service_id = ser.id");
//        WHERE("rrr.transaction_id = tran.id");

        sql = sql + SQL() + " UNION ";
        sortClassifier++;

        // Property
        BEGIN();
        SELECT("'" + PROPERTY_GROUP + "' AS record_group");
        SELECT("'" + PROPERTY_GROUP + "' AS record_type");
        SELECT(sortClassifier + " as sort_classifier");
        SELECT("prop1.id AS record_id");
        SELECT("prop1.rowversion AS record_sequence");
        SELECT("''::text AS nr");
        SELECT("replace(prop1.change_action,'i','" + ADDED_PROPERTY + "')||prop1.name_firstpart||'/'||prop1.name_lastpart AS action_code");
        SELECT("NULL::text AS notation");
        SELECT("prop1.change_time");
        SELECT("(SELECT (appuser.first_name::text || ' '::text) || appuser.last_name::text"
                + " FROM system.appuser"
                + " WHERE appuser.username::text = prop1.change_user::text)"
                + " AS user_fullname");
        FROM("application.application_property prop1 ");
        WHERE("prop1.application_id = #{" + PARAM_APPLICATION_ID + "}");

        sql = sql + SQL() + " UNION ";
        sortClassifier++;

        // Application property History
        BEGIN();
        SELECT("'" + PROPERTY_GROUP + "' AS record_group");
        SELECT("'" + PROPERTY_GROUP + "' AS record_type");
        SELECT(sortClassifier + " as sort_classifier");
        SELECT("prop_hist.id AS record_id");
        SELECT("prop_hist.rowversion AS record_sequence");
        SELECT("''::text AS nr");
        SELECT("CASE WHEN prop_hist.change_action = 'i' then replace(prop_hist.change_action,'i','" + ADDED_PROPERTY + "')||' - '|| prop_hist.name_firstpart|| '/'||prop_hist.name_lastpart"
                + "  WHEN prop_hist.change_action = 'd' then replace(prop_hist.change_action,'d','" + DELETED_PROPERTY + "')||' - '|| prop_hist.name_firstpart|| '/'||prop_hist.name_lastpart"
                + "  END AS action_code");
        SELECT("NULL::text AS notation");
        SELECT("prop_hist.change_time");
        SELECT("(SELECT (appuser.first_name::text || ' '::text) || appuser.last_name::text"
                + " FROM system.appuser"
                + " WHERE appuser.username::text = prop_hist.change_user::text)"
                + " AS user_fullname");
        FROM("application.application_property_historic prop_hist");
        WHERE("prop_hist.application_id = #{" + PARAM_APPLICATION_ID + "}");


        sql = sql + SQL() + " UNION ";
        sortClassifier++;

        // SOURCE
        BEGIN();
        SELECT("'" + SOURCE_GROUP + "' AS record_group");
        SELECT("'" + SOURCE_GROUP + "' AS record_type");
        SELECT(sortClassifier + " as sort_classifier");
        SELECT("source1.source_id AS record_id");
        SELECT("source1.rowversion AS record_sequence");
        SELECT("''::text AS nr");
        SELECT("replace(source1.change_action,'i','" + ADDED_SOURCE + "')||coalesce(source.reference_nr,'')||' - '||coalesce(source.type_code,'')   AS action_code");
        SELECT("NULL::text AS notation");
        SELECT("source1.change_time");
        SELECT("(SELECT (appuser.first_name::text || ' '::text) || appuser.last_name::text"
                + " FROM system.appuser"
                + " WHERE appuser.username::text = source1.change_user::text)"
                + " AS user_fullname");
        FROM("application.application_uses_source source1  "
                + " LEFT JOIN source.source source "
                + " ON source1.source_id = source.id ");
        WHERE("source1.application_id = #{" + PARAM_APPLICATION_ID + "}");

        sql = sql + SQL() + " UNION ";
        sortClassifier++;

        // Application Source History
        BEGIN();
        SELECT("'" + SOURCE_GROUP + "' AS record_group");
        SELECT("'" + SOURCE_GROUP + "' AS record_type");
        SELECT(sortClassifier + " as sort_classifier");
        SELECT("source1.source_id AS record_id");
        SELECT("source1.rowversion AS record_sequence");
        SELECT("''::text AS nr");
        SELECT("CASE WHEN source1.change_action = 'i' then replace(source1.change_action,'i','" + ADDED_SOURCE + "')||coalesce(source.reference_nr,'')||' - '||coalesce(source.type_code,'') "
                + "  WHEN source1.change_action = 'd' then replace(source1.change_action,'d','" + DELETED_SOURCE + "')||coalesce(source.reference_nr,'')||' - '||coalesce(source.type_code,'') "
                + "  END AS action_code");
        SELECT("NULL::text AS notation");
        SELECT("source1.change_time");
        SELECT("(SELECT (appuser.first_name::text || ' '::text) || appuser.last_name::text"
                + " FROM system.appuser"
                + " WHERE appuser.username::text = source1.change_user::text)"
                + " AS user_fullname");
        FROM("application.application_uses_source_historic source1  "
                + " LEFT JOIN source.source source "
                + " ON source1.source_id = source.id ");
        WHERE("source1.application_id = #{" + PARAM_APPLICATION_ID + "}");


        sql = sql + SQL() + " UNION ";
        sortClassifier++;

        // AGENT 
        BEGIN();

        SELECT("'" + AGENT_GROUP + "' AS record_group");
        SELECT("'" + AGENT_GROUP + "' AS record_type");
        SELECT(sortClassifier + " as sort_classifier");
        SELECT("app.agent_id AS record_id");
        SELECT("app.rowversion AS record_sequence");
        SELECT("''::text AS nr");
        SELECT("replace(party.change_action,'i','" + ADDED_AGENT + "')||' - '||party.name||' '||coalesce(party.last_name,'') AS action_code");
        SELECT("NULL::text AS notation");
        SELECT("app.change_time");
        SELECT("(SELECT (appuser.first_name::text || ' '::text) || appuser.last_name::text"
                + " FROM system.appuser"
                + " WHERE appuser.username::text = app.change_user::text)"
                + " AS user_fullname");
        FROM("application.application app");
        FROM("party.party party");
        WHERE("app.id = #{" + PARAM_APPLICATION_ID + "}");
        WHERE("app.agent_id=party.id");

        sql = sql + SQL() + " UNION ";
        sortClassifier++;

        // AGENT History
        BEGIN();

        SELECT("'" + AGENT_GROUP + "' AS record_group");
        SELECT("'" + AGENT_GROUP + "' AS record_type");
        SELECT(sortClassifier + " as sort_classifier");
        SELECT("app.agent_id AS record_id");
        SELECT("app.rowversion AS record_sequence");
        SELECT("''::text AS nr");
        SELECT("CASE WHEN (app.change_action='i') then replace(party.change_action,'i','" + ADDED_AGENT + "')||' - '||coalesce(party.name,'')||' '||coalesce(party.last_name,'')"
                + " ELSE  replace(app.change_action,app.change_action,'" + DELETED_AGENT + "')||' - '||coalesce(party.name,'')||' '||coalesce(party.last_name,'')"
                + " END AS action_code");
        SELECT("NULL::text AS notation");
        SELECT("app.change_time");
        SELECT("(SELECT (appuser.first_name::text || ' '::text) || appuser.last_name::text"
                + " FROM system.appuser"
                + " WHERE appuser.username::text = app.change_user::text)"
                + " AS user_fullname");
        FROM("application.application new_app");
        FROM("application.application_historic app"
                + " LEFT JOIN party.party party"
                + "  ON app.agent_id = party.id");
        WHERE("app.id = #{" + PARAM_APPLICATION_ID + "}");
        WHERE("app.agent_id != new_app.agent_id");
        WHERE("app.agent_id=party.id");
        WHERE("((app.rowversion - 1) = new_app.rowversion OR (app.rowversion) = new_app.rowversion)");

        sql = sql + SQL() + " UNION ";
        sortClassifier++;


        // contact_person 
        BEGIN();

        SELECT("'" + CONTACT_PERSON_GROUP + "' AS record_group");
        SELECT("'" + CONTACT_PERSON_GROUP + "' AS record_type");
        SELECT(sortClassifier + " as sort_classifier");
        SELECT("app.contact_person_id AS record_id");
        SELECT("app.rowversion AS record_sequence");
        SELECT("''::text AS nr");
        SELECT("replace(party.change_action,app.change_action,'" + ADDED_CONTACT_PERSON + "')||' - '||party.name||' '||coalesce(party.last_name,'') AS action_code");
        SELECT("NULL::text AS notation");
        SELECT("app.change_time");
        SELECT("(SELECT (appuser.first_name::text || ' '::text) || appuser.last_name::text"
                + " FROM system.appuser"
                + " WHERE appuser.username::text = app.change_user::text)"
                + " AS user_fullname");
        FROM("application.application app");
        FROM("party.party party");
        WHERE("app.id = #{" + PARAM_APPLICATION_ID + "}");
        WHERE("app.contact_person_id=party.id");

        sql = sql + SQL() + " UNION ";
        sortClassifier++;

        // contact_person History
        BEGIN();

        SELECT("'" + CONTACT_PERSON_GROUP + "' AS record_group");
        SELECT("'" + CONTACT_PERSON_GROUP + "' AS record_type");
        SELECT(sortClassifier + " as sort_classifier");
        SELECT("app.contact_person_id AS record_id");
        SELECT("app.rowversion AS record_sequence");
        SELECT("''::text AS nr");
        SELECT("CASE WHEN (app.change_action='i') then replace(party.change_action,'i','" + ADDED_CONTACT_PERSON + "')||' - '||coalesce(party.name,'')||' '||coalesce(party.last_name,'')"
                + " ELSE  replace(app.change_action,app.change_action,'" + DELETED_CONTACT_PERSON + "')||' - '||coalesce(party.name,'')||' '||coalesce(party.last_name,'')"
                + " END AS action_code");
        SELECT("NULL::text AS notation");
        SELECT("app.change_time");
        SELECT("(SELECT (appuser.first_name::text || ' '::text) || appuser.last_name::text"
                + " FROM system.appuser"
                + " WHERE appuser.username::text = app.change_user::text)"
                + " AS user_fullname");
        FROM("application.application new_app");
        FROM("application.application_historic app"
                + " LEFT JOIN party.party_historic party"
                + "  ON app.contact_person_id = party.id");
        WHERE("app.id = #{" + PARAM_APPLICATION_ID + "}");
        WHERE("app.contact_person_id != new_app.contact_person_id");
        WHERE("app.contact_person_id=party.id");
        WHERE("((app.rowversion - 1) = new_app.rowversion OR (app.rowversion) = new_app.rowversion)");

        ORDER_BY("change_time, sort_classifier, nr");

        sql = sql + SQL();

        return sql;
    }

    /**
     * Uses the BA Unit Search parameters to build an SQL Query for Allotments.
     * This method does not inject the search parameter values into the SQL as
     * that would prevent the database from performing statement caching.
     *
     * @param params Object containing the parameter values provided by the user
     * @return SQL String
     */
    public static String buildSearchAllotmentSql(BaUnitSearchParams params) {
        String sql;
        BEGIN();
        SELECT("DISTINCT prop.id");
        SELECT("prop.name");
        SELECT("prop.name_firstpart");
        SELECT("prop.name_lastpart");
        SELECT("prop.status_code");
        SELECT("(SELECT string_agg(COALESCE(p1.name, '') || ' ' || COALESCE(p1.last_name, ''), '::::') "
                + "FROM administrative.rrr rrr1, administrative.party_for_rrr pr1, party.party p1 "
                + "WHERE rrr1.ba_unit_id = prop.id "
                + "AND rrr1.status_code = 'current' "
                + "AND rrr1.is_primary = TRUE "
                + "AND pr1.rrr_id = rrr1.id "
                + "AND p1.id = pr1.party_id ) AS rightholders");
        SELECT("prop.registered_name");
        SELECT("prop.type_code");
        SELECT("rrr.registry_book_ref");
        SELECT("rrr.registration_date");
        SELECT("(SELECT t1.name "
                + "FROM administrative.ba_unit t1, "
                + "     administrative.required_relationship_baunit req1 "
                + "WHERE req1.to_ba_unit_id = prop.id "
                + "AND req1.relation_code = 'town' "
                + "AND t1.id = req1.from_ba_unit_id ) AS town_name");
        FROM("administrative.ba_unit prop");
        FROM("administrative.rrr rrr");
        WHERE("rrr.ba_unit_id = prop.id");
        WHERE("rrr.status_code = 'current'");
        WHERE("rrr.is_primary = TRUE");

        if (params.getSearchType().equals("lease")) {
            WHERE("prop.type_code = 'leasedUnit'");
        } else if (params.getSearchType().equals("estate")) {
            WHERE("prop.type_code = 'estateUnit'");
        } else if (params.getSearchType().equals("town")) {
            WHERE("prop.type_code IN ('townUnit', 'islandUnit')");
        } else {
            if (params.isTaxAllotment() == params.isTownAllotment()) {
                // Ensure there is always type_code criteria
                WHERE("prop.type_code IN ('taxUnit', 'townAllotmentUnit')");
            } else if (params.isTaxAllotment()) {
                WHERE("prop.type_code = 'taxUnit'");
            } else if (params.isTownAllotment()) {
                WHERE("prop.type_code = 'townAllotmentUnit'");
            }
        }

        if (!StringUtility.isEmpty(params.getOwnerName())) {
            FROM("administrative.party_for_rrr pr");
            FROM("party.party p");
            WHERE("pr.rrr_id = rrr.id");
            WHERE("p.id = pr.party_id");
            WHERE("compare_strings(#{" + BaUnitSearchResult.QUERY_PARAM_OWNER_NAME + "}, "
                    + "COALESCE(p.name, '') || ' ' || COALESCE(p.last_name, '') || ' ' || COALESCE(p.alias, ''))");
        }

        if (!StringUtility.isEmpty(params.getNameFirstPart())) {
            WHERE("compare_strings(#{" + BaUnitSearchResult.QUERY_PARAM_NAME_FIRSTPART
                    + "}, COALESCE(prop.name_firstpart, ''))");
        }

        if (!StringUtility.isEmpty(params.getNameLastPart())) {
            WHERE("compare_strings(#{" + BaUnitSearchResult.QUERY_PARAM_NAME_LASTPART
                    + "}, COALESCE(prop.name_lastpart, ''))");
        }

        if (!StringUtility.isEmpty(params.getParcelName())) {
            WHERE("compare_strings(#{" + BaUnitSearchResult.QUERY_PARAM_PARCEL_NAME
                    + "}, COALESCE(prop.registered_name, ''))");
        }

        if (!StringUtility.isEmpty(params.getRegistryBookRefQueryParam())) {
            WHERE("compare_strings(#{" + BaUnitSearchResult.QUERY_PARAM_REGISTRY_BOOK_REF
                    + "}, COALESCE(rrr.registry_book_ref, ''))");
        }

        if (params.getRegisteredDateFrom() != null) {
            WHERE("rrr.registration_date >= #{" + BaUnitSearchResult.QUERY_PARAM_REGISTRATION_FROM_DATE + "}");
        }

        if (params.getRegisteredDateTo() != null) {
            WHERE("rrr.registration_date <= #{" + BaUnitSearchResult.QUERY_PARAM_REGISTRATION_TO_DATE + "}");
        }

        if (!StringUtility.isEmpty(params.getTownId())) {
            WHERE("EXISTS (SELECT req.from_ba_unit_id "
                    + "FROM administrative.required_relationship_baunit req "
                    + "WHERE req.to_ba_unit_id = prop.id "
                    + "AND req.from_ba_unit_id = #{" + BaUnitSearchResult.QUERY_PARAM_TOWN + "} "
                    + "AND req.relation_code = 'town')");
        }

        ORDER_BY(BaUnitSearchResult.QUERY_ORDER_BY
                + " LIMIT 100");
        sql = SQL();
        return sql;
    }

    /**
     * Uses the BA Unit Search parameters to build an appropriate SQL Query.
     * This method does not inject the search parameter values into the SQL as
     * that would prevent the database from performing statement caching.
     *
     * @param nameFirstPart The name first part search parameter value
     * @param nameLastPart The name last part search parameter value
     * @param owernName The owner name search parameter value
     * @return SQL String
     */
    public static String buildSearchBaUnitSql(String nameFirstPart,
            String nameLastPart, String owernName) {
        String sql;
        BEGIN();
        SELECT("DISTINCT prop.id");
        SELECT("prop.name");
        SELECT("prop.name_firstpart");
        SELECT("prop.name_lastpart");
        SELECT("prop.status_code");
        SELECT("(SELECT string_agg(COALESCE(p1.name, '') || ' ' || COALESCE(p1.last_name, ''), '::::') "
                + "FROM administrative.rrr rrr1, administrative.party_for_rrr pr1, party.party p1 "
                + "WHERE rrr1.ba_unit_id = prop.id "
                + "AND rrr1.status_code = 'current' "
                + "AND pr1.rrr_id = rrr1.id "
                + "AND p1.id = pr1.party_id ) AS rightholders");
        SELECT("prop.type_code");
        FROM("administrative.ba_unit prop");
        if (!StringUtility.isEmpty(owernName)) {
            FROM("administrative.rrr rrr");
            FROM("administrative.party_for_rrr pr");
            FROM("party.party p");
            WHERE("rrr.ba_unit_id = prop.id");
            WHERE("rrr.status_code = 'current'");
            WHERE("pr.rrr_id = rrr.id");
            WHERE("p.id = pr.party_id");
            WHERE("compare_strings(#{" + BaUnitSearchResult.QUERY_PARAM_OWNER_NAME + "}, "
                    + "COALESCE(p.name, '') || ' ' || COALESCE(p.last_name, '') || ' ' || COALESCE(p.alias, ''))");
        }
        if (!StringUtility.isEmpty(nameFirstPart)) {
            WHERE("compare_strings(#{" + BaUnitSearchResult.QUERY_PARAM_NAME_FIRSTPART
                    + "}, COALESCE(prop.name_firstpart, ''))");
        }
        if (!StringUtility.isEmpty(nameLastPart)) {
            WHERE("compare_strings(#{" + BaUnitSearchResult.QUERY_PARAM_NAME_LASTPART
                    + "}, COALESCE(prop.name_lastpart, ''))");
        }
        ORDER_BY(BaUnitSearchResult.QUERY_ORDER_BY + " LIMIT 100");
        sql = SQL();
        return sql;
    }

    /**
     * Builds a SQL string to retrieve property information. Used to verify if
     * the allotment details entered for a new application match details in the
     * database. Customization for Tonga.
     *
     * @param appNumber The number for the new application the property details
     * will be associated with.
     * @param nameFirstPart The deed number of the allotment
     * @param nameLastPart The folio of the allotment
     * @param leaseNumber The lease number for the lease
     */
    public static String buildAllotmentVerifierSql(String nameFirstPart,
            String nameLastPart, String leaseNumber) {
        String sql;

        // Retrieve details about the allotment
        BEGIN();
        SELECT("b.id AS lot_id");
        SELECT("b.name_firstpart AS deed_num");
        SELECT("b.name_lastpart AS folio");
        SELECT("(SELECT string_agg(TRIM(COALESCE(p.name, '') || ' ' || COALESCE(p.last_name, '')), ', ') "
                + "FROM administrative.rrr r, administrative.party_for_rrr pfr, party.party p "
                + "WHERE r.ba_unit_id = b.id "
                + "AND r.status_code = 'current' "
                + "AND r.type_code = 'ownership' "
                + "AND pfr.rrr_id = r.id "
                + "AND p.id = pfr.party_id ) AS holder_name");
        SELECT("(SELECT ba.size "
                + "FROM administrative.ba_unit_area ba "
                + "WHERE ba.ba_unit_id = b.id "
                + "AND ba.type_code = 'officialArea') AS lot_area");
        SELECT("b.creation_date AS reg_date");
        SELECT("(SELECT co.land_use_code "
                + "FROM cadastre.cadastre_object co, "
                + "     administrative.ba_unit_contains_spatial_unit bas "
                + "WHERE co.id = bas.spatial_unit_id "
                + "AND bas.ba_unit_id = b.id "
                + "AND co.status_code = 'current') AS land_use_code");
        SELECT("(SELECT island.from_ba_unit_id "
                + "FROM administrative.required_relationship_baunit  town, "
                + "     administrative.required_relationship_baunit island "
                + "WHERE town.to_ba_unit_id = b.id "
                + "AND town.relation_code = 'town' "
                + "AND island.to_ba_unit_id = town.from_ba_unit_id "
                + "AND island.relation_code = 'island') AS island_id");
        SELECT("(SELECT ba_rel.from_ba_unit_id "
                + "FROM administrative.required_relationship_baunit  ba_rel "
                + "WHERE ba_rel.to_ba_unit_id = b.id "
                + "AND ba_rel.relation_code = 'town') AS town_id");
        FROM("administrative.ba_unit b");
        WHERE("b.status_code = 'current'");
        WHERE("b.type_code IN ('townAllotmentUnit', 'taxUnit') ");

        if (!StringUtility.isEmpty(nameFirstPart) && !StringUtility.isEmpty(nameLastPart)) {
            if (!StringUtility.isEmpty(leaseNumber)) {
                // Check if the lease is linked to the allotment
                SELECT("COALESCE((SELECT (lease.name_firstpart = #{" + PropertyVerifier.QUERY_PARAM_LEASE_NUM + "}) "
                        + "FROM administrative.required_relationship_baunit lease_rel,"
                        + "     administrative.ba_unit lease "
                        + "WHERE lease_rel.from_ba_unit_id = b.id "
                        + "AND lease_rel.relation_code = 'allotment' "
                        + "AND lease.id = lease_rel.to_ba_unit_id "
                        + "AND lease.type_code = 'leasedUnit' "
                        + "AND lease.status_code = 'current'), false) AS lease_linked");
            }
            // Use the deed number and folio for the criteria
            WHERE("b.name_firstpart = #{" + PropertyVerifier.QUERY_PARAM_FIRST_PART + "} ");
            WHERE("b.name_lastpart = #{" + PropertyVerifier.QUERY_PARAM_LAST_PART + "} ");
        } else if (!StringUtility.isEmpty(leaseNumber)) {
            // Use the lease number if the deed number and folio are not provided. 
            SELECT("true AS lease_linked");
            FROM("administrative.ba_unit lease ");
            FROM("administrative.required_relationship_baunit rel");
            WHERE("lease.name_firstpart = #{" + PropertyVerifier.QUERY_PARAM_LEASE_NUM + "} ");
            WHERE("lease.type_code = 'leasedUnit'");
            WHERE("lease.status_code = 'current'");
            WHERE("rel.to_ba_unit_id = lease.id");
            WHERE("rel.relation_code = 'allotment'");
            WHERE("b.id = rel.from_ba_unit_id");
        }
        sql = SQL();
        return sql;
    }

    /**
     * Builds a SQL string to retrieve property information. Used to verify if
     * the lease details entered for a new application match details in the
     * database. Customization for Tonga.
     *
     * @param leaseNumber The lease number for the lease
     */
    public static String buildLeaseVerifierSql(String leaseNumber) {
        String sql;

        // Retrieve details about the lease
        BEGIN();
        SELECT("b.id AS lease_id");
        SELECT("b.name_firstpart AS lease_num");
        SELECT("(SELECT string_agg(TRIM(COALESCE(p.name, '') || ' ' || COALESCE(p.last_name, '')), ', ') "
                + "FROM administrative.party_for_rrr pfr, party.party p "
                + "WHERE pfr.rrr_id = r.id "
                + "AND p.id = pfr.party_id ) AS lessee_name");
        SELECT("(SELECT ba.size "
                + "FROM administrative.ba_unit_area ba "
                + "WHERE ba.ba_unit_id = b.id "
                + "AND ba.type_code = 'officialArea') AS lease_area");
        SELECT("r.term AS term");
        SELECT("r.amount AS rental");
        FROM("administrative.ba_unit b");
        FROM("administrative.rrr r");
        WHERE("b.name_firstpart = #{" + PropertyVerifier.QUERY_PARAM_LEASE_NUM + "} ");
        WHERE("b.type_code = 'leasedUnit'");
        WHERE("b.status_code = 'current'");
        WHERE("r.ba_unit_id = b.id");
        WHERE("r.status_code = 'current'");
        WHERE("r.type_code = 'lease'");
        sql = SQL();

        return sql;
    }

    /**
     * Builds a SQL string to check if any other applications link to the
     * allotment and/or lease used by the specified application. Customization
     * for Tonga.
     *
     * @param appNumber The number for the new application the property details
     * will be associated with.
     * @param nameFirstPart The deed number of the allotment
     * @param nameLastPart The folio of the allotment
     * @param leaseNumber The lease number for the lease
     */
    public static String buildApplicationVerifierSql(String appNumber, String nameFirstPart,
            String nameLastPart, String leaseNumber) {
        String sql;

        // Check if there are any pending applications on this lease or allotment
        BEGIN();
        SELECT("string_agg(nr, ',') AS applications_where_found");
        FROM("application.application a");
        FROM("application.application_property ap");
        WHERE("ap.application_id = a.id");
        WHERE("a.status_code = 'lodged'");
        WHERE("a.nr != #{" + PropertyVerifier.QUERY_PARAM_APPLICATION_NUMBER + "}");
        if (!StringUtility.isEmpty(nameFirstPart) && !StringUtility.isEmpty(nameLastPart)) {
            if (!StringUtility.isEmpty(leaseNumber)) {
                WHERE("((ap.name_firstpart = #{" + PropertyVerifier.QUERY_PARAM_FIRST_PART + "} "
                        + "AND ap.name_lastpart = #{" + PropertyVerifier.QUERY_PARAM_LAST_PART + "}) "
                        + "OR (ap.lease_number = #{" + PropertyVerifier.QUERY_PARAM_LEASE_NUM + "}))");
            } else {
                WHERE("ap.name_firstpart = #{" + PropertyVerifier.QUERY_PARAM_FIRST_PART + "} "
                        + "AND ap.name_lastpart = #{" + PropertyVerifier.QUERY_PARAM_LAST_PART + "} ");
            }
        } else if (!StringUtility.isEmpty(leaseNumber)) {
            WHERE("ap.lease_number = #{" + PropertyVerifier.QUERY_PARAM_LEASE_NUM + "} ");
        }
        sql = SQL();
        return sql;
    }
}

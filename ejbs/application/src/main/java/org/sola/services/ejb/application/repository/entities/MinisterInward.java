/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sola.services.ejb.application.repository.entities;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import org.sola.services.common.repository.entities.AbstractVersionedEntity;

/**
 *
 * @author Admin
 */
@Table(name = "minister_inward", schema = "application")
public class MinisterInward extends AbstractVersionedEntity{
    
    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "service_id")
    private String serviceId;
    @Column(name = "subject")
    private String subject;
    @Column(name = "date_in")
    private Date dateIn;
    @Column(name = "file_number")
    private String fileNumber;
    @Column(name = "date_out")
    private Date dateOut;
    @Column(name = "directed_division")
    private String directedDivision;
    @Column(name = "directed_officer")
    private String directedOfficer;
    @Column(name = "ceo_direction")
    private String ceoDirection;
    @Column(name = "minister_direction")
    private String ministerDirection;
    @Column(name = "from_whom")
    private String fromWhom;
    @Column(name = "remark")
    private String remark;
    @Column(name = "comment")
    private String comment;
    
    public MinisterInward(){
        super();
    }

    public String getId() {
        id = id == null ? generateId() : id;
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Date getDateIn() {
        return dateIn;
    }

    public void setDateIn(Date dateIn) {
        this.dateIn = dateIn;
    }

    public String getFileNumber() {
        return fileNumber;
    }

    public void setFileNumber(String fileNumber) {
        this.fileNumber = fileNumber;
    }

    public Date getDateOut() {
        return dateOut;
    }

    public void setDateOut(Date dateOut) {
        this.dateOut = dateOut;
    }

    public String getDirectedDivision() {
        return directedDivision;
    }

    public void setDirectedDivision(String directedDivision) {
        this.directedDivision = directedDivision;
    }

    public String getDirectedOfficer() {
        return directedOfficer;
    }

    public void setDirectedOfficer(String directedOfficer) {
        this.directedOfficer = directedOfficer;
    }

    public String getCeoDirection() {
        return ceoDirection;
    }

    public void setCeoDirection(String ceoDirection) {
        this.ceoDirection = ceoDirection;
    }

    public String getMinisterDirection() {
        return ministerDirection;
    }

    public void setMinisterDirection(String ministerDirection) {
        this.ministerDirection = ministerDirection;
    }

    public String getFromWhom() {
        return fromWhom;
    }

    public void setFromWhom(String fromWhom) {
        this.fromWhom = fromWhom;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
    
}

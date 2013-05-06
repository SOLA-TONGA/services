/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sola.services.ejb.application.repository.entities;

import javax.persistence.Column;
import javax.persistence.Table;
import org.sola.services.common.repository.entities.AbstractCodeEntity;

/**
 *
 * @author Admin
 */
@Table(name = "checklist_item", schema = "application")
public class ChecklistItem extends AbstractCodeEntity{
    @Column(name = "code")
    private String code;
    @Column(name = "display_value")
    private String displayValue;
    @Column(name = "description")
    private String description;
    @Column(name = "status")
    private String status;

    public ChecklistItem(){
        super();
    }
    
    public String getCode(){
        return code;
    }
    
    public void getCode(String code){
        this.code = code;
    }
    
    public String getDisplayValue(){
        return displayValue;
    }
    
    public void setDisplayValue(String displayValue){
        this.displayValue = displayValue;
    }
    
    public String getDescription(){
        return description;
    }
    
    public void setDescription(String description){
        this.description = description;
    }
    
    public String getStatus(){
        return status;
    }
    
    public void setStatus(String status){
        this.status = status;
    }
    
}

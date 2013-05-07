/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sola.services.ejb.application.repository.entities;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Table;
import org.sola.services.common.repository.ChildEntityList;
import org.sola.services.common.repository.entities.AbstractCodeEntity;
import org.sola.services.ejb.source.repository.entities.Source;

/**
 *
 * @author Admin
 */
@Table(name = "checklist_item_in_group", schema = "application")
public class ChecklistItemInGroup extends AbstractCodeEntity{
    
    /*@Column(name = "checklist_group_code")
    private String checklistGroupCode;
    @Column(name = "checklist_item_code")
    private String checklistItemCode;*/
    @ChildEntityList(parentIdField = "checklist_group_code", childIdField = "checklist_item_code",
        manyToManyClass = ChecklistItemInGroup.class)
    private List<ChecklistItem> checklistItemList;
      
    public ChecklistItemInGroup(){
        super();
    }
    
    public List<ChecklistItem> getChecklistItemList() {
        checklistItemList = checklistItemList == null ? new ArrayList<ChecklistItem>() : checklistItemList;
        return checklistItemList;
    }

    public void setSourceList(List<ChecklistItem> checklistItemList) {
        this.checklistItemList = checklistItemList;
    }
}

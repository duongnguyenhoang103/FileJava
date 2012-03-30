/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.pilot.sb25.subpersonsb25.entity;

import java.util.Calendar;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.openide.util.lookup.ServiceProvider;
import vn.com.hkt.pilot.identity.access.api.IAccessData;
import vn.com.hkt.pilot.identity.entitiy.api.IEntity;
import vn.com.hkt.pilot.sb25.Installer;
import vn.com.hkt.pilot.sb25.dao.OtherProfileBN;

/**
 *
 * @author khangpn
 */
@Entity
@ServiceProvider(service = IEntity.class)
public class OtherProfile implements IEntity {
    
    public static final String FIELD_OTHERPROFILE_ID = "otherProfileId";
    public static final String FIELD_DATE_OTHER = "dateOther";
    public static final String FIELD_DESCRIPTION = "description";
    public static final String FIELD_NOTE = "note";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String otherProfileId;
    @Temporal(TemporalType.DATE)
    private Calendar dateOther; // Thời gian
    private String description; // Thông tin
    private String note;
    
    public OtherProfile() {
    }
    
    public OtherProfile(String otherProfileId, Calendar dateOther,
            String description, String note) {
        this.otherProfileId = otherProfileId;
        this.dateOther = dateOther;
        this.description = description;
        this.note = note;
    }
    
    @Override
    public String getEntityName() {
        return this.getClass().getSimpleName();
    }
    
    @Override
    public String getModuleOfEntity() {
        return Installer.MODULE_NAME;
    }
    
    @Override
    public String getEntityDescription() {
        return "Module mở rộng SB25";
    }
    
    @Override
    public IAccessData getAccessDataOfEntity() {
        return new OtherProfileBN();
    }
    
    @Override
    public String getFieldNameObjectId() {
        return FIELD_OTHERPROFILE_ID;
    }
    
    public Calendar getDateOther() {
        return dateOther;
    }
    
    public void setDateOther(Calendar dateOther) {
        this.dateOther = dateOther;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getNote() {
        return note;
    }
    
    public void setNote(String note) {
        this.note = note;
    }
    
    public String getOtherProfileId() {
        return otherProfileId;
    }
    
    public void setOtherProfileId(String otherProfileId) {
        this.otherProfileId = otherProfileId;
    }    

    @Override
    public String getDataRealyOfField(String fieldName, String data) {
        return data;
    }

    @Override
    public String getDescriptionOfField(String fieldName) {
        return fieldName;
    }
}

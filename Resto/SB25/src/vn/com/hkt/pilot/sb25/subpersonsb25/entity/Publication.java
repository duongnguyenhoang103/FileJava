/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.pilot.sb25.subpersonsb25.entity;

import java.lang.reflect.Field;
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
import vn.com.hkt.pilot.sb25.dao.PublicationBN;

/**
 * 
 * @author khangpn
 */
@Entity
@ServiceProvider(service = IEntity.class)
public class Publication  implements IEntity { // Công trình nghiên cứu
    
    public static final String FIELD_PUBLICATION_ID = "publicationId";
    public static final String FIELD_TIME_PUBLISHED = "timePublished";
    public static final String FIELD_DESCRIPTION = "description";
    public static final String FIELD_RESEARCH_RESULT = "researchResult";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String publicationId;
    @Temporal(TemporalType.DATE)
    private Calendar timePublished;
    private String description;
    private String researchResult; // Kết quả

    public Publication(String publicationId, Calendar timePublished, 
            String description, String researchResult) {
        this.publicationId = publicationId;
        this.timePublished = timePublished;
        this.description = description;
        this.researchResult = researchResult;
    }

    public String getPublicationId() {
        return publicationId;
    }

    public void setPublicationId(String publicationId) {
        this.publicationId = publicationId;
    }

    public Calendar getTimePublished() {
        return timePublished;
    }

    public void setTimePublished(Calendar timePublished) {
        this.timePublished = timePublished;
    }

    public Publication() {
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
    
    public String getResearchResult() {
        return researchResult;
    }
    
    public void setResearchResult(String researchResult) {
        this.researchResult = researchResult;
    }
    
    @Override
    public String toString() {
        return researchResult;
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
        return new PublicationBN();
    }
    
    @Override
    public String getFieldNameObjectId() {
        return FIELD_PUBLICATION_ID;
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

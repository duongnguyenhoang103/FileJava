/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.pilot.promotion.entity;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.openide.util.lookup.ServiceProvider;
import org.openide.util.lookup.ServiceProviders;
import vn.com.hkt.pilot.identity.access.api.IAccessData;
import vn.com.hkt.pilot.identity.entitiy.api.IEntity;
import vn.com.hkt.pilot.promotion.Installer;
import vn.com.hkt.pilot.promotion.dao.ClassifiedByPersonBN;

/**
 *
 * @author khangpn
 */
@Entity
@ServiceProviders(value = {
    @ServiceProvider(service = IEntity.class),
    @ServiceProvider(service = IClassificationOperation.class)
})
public class ClassifiedByPerson implements IEntity, IClassificationOperation{

    public static final String FIELD_CLASSIFICATION_ID_ACTUAL = "classificationId";
    public static final String FIELD_PERSON_ID_ACTUAL = "personIdActuals";
    public static final String FIELD_PROMOTION_ID_ACTUAL = "promotionIdActual";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int promotionIdActual; // Ma Promotion
    private String classificationId; // Ma phan loai
    private List<Integer> personIdActuals; // Ma Person-nếu null Enter và Depar --> Khach

    public ClassifiedByPerson() {
    }

    public ClassifiedByPerson(int promotionIdActual, String classificationId, List<Integer> personIdActuals) {
        this.promotionIdActual = promotionIdActual;
        this.classificationId = classificationId;
        this.personIdActuals = personIdActuals;
    }

    public String getClassificationId() {
        return classificationId;
    }

    public void setClassificationId(String classificationId) {
        this.classificationId = classificationId;
    }

    public int getPromotionIdActual() {
        return promotionIdActual;
    }

    public void setPromotionIdActual(int promotionIdActual) {
        this.promotionIdActual = promotionIdActual;
    }

    

    public List<Integer> getPersonIdActuals() {
        return personIdActuals;
    }

    public void setPersonIdActuals(List<Integer> personIdActuals) {
        this.personIdActuals = personIdActuals;
    }

   

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        return "Module mở rộng SB45";
    }

    @Override
    public IAccessData getAccessDataOfEntity() {
        return new ClassifiedByPersonBN();
    }

    @Override
    public String getFieldNameObjectId() {
        return FIELD_CLASSIFICATION_ID_ACTUAL;
    }

    @Override
    public String getDataRealyOfField(String fieldName, String data) {
        return data;
    }

    @Override
    public String getDescriptionOfField(String fieldName) {
        return fieldName;
    }

    @Override
    public String toString() {
        return "Person";
    }

    @Override
    public String getClassificationName() {
        return "Person";
    }

    @Override
    public int getHardId() {
        return 4;
    }
    
    
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.pilot.promotion.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.openide.util.lookup.ServiceProvider;
import org.openide.util.lookup.ServiceProviders;
import vn.com.hkt.pilot.identity.access.api.IAccessData;
import vn.com.hkt.pilot.identity.entitiy.api.IEntity;
import vn.com.hkt.pilot.promotion.Installer;
import vn.com.hkt.pilot.promotion.dao.ClassifiedByOperationBN;

/**
 *
 * @author khangpn
 */
@Entity
@ServiceProviders(value = {
    @ServiceProvider(service = IEntity.class),
    @ServiceProvider(service = IClassificationOperation.class)
})
public class ClassifiedByOperation implements IEntity, IClassificationOperation {

    public static final String FIELD_CLASSIFICATION_ID_ACTUAL = "classificationId";
    public static final String FIELD_OPERATION_TYPE = "operationType";
    public static final String FIELD_PROMOTION_ID_ACTUAL = "promotionIdActual";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String classificationId; // Ma phan loai
    private int promotionIdActual; // Ma Promotion
    private String operationType; // Loai hoa don-Le, do....

    public ClassifiedByOperation() {
    }

    public ClassifiedByOperation(String classificationId, int promotionIdActual, String operationType) {
        this.classificationId = classificationId;
        this.promotionIdActual = promotionIdActual;
        this.operationType = operationType;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
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
        return new ClassifiedByOperationBN();
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
        return "Operation";
    }

    @Override
    public String getClassificationName() {
        return "Operation";
    }

    @Override
    public int getHardId() {
        return 3;
    }
}

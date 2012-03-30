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
import vn.com.hkt.pilot.promotion.dao.ClassifiedByEnterpriseBN;

/**
 *
 * @author khangpn
 */
@Entity
@ServiceProviders(value = {
    @ServiceProvider(service = IEntity.class),
    @ServiceProvider(service = IClassificationOperation.class)
})
public class ClassifiedByEnterprise implements IEntity, IClassificationOperation {

    public static final String FIELD_CLASSIFICATION_ID_ACTUAL = "classificationId";
    public static final String FIELD_ENTERPRISE_ID_ACTUAL = "enterpriseIdActuals";
    public static final String FIELD_PROMOTION_ID_ACTUAL = "promotionIdActual";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String classificationId; // Ma phan loai
    private int promotionIdActual; // Ma Promotion
    private List<Integer> enterpriseIdActuals;// Ma cong ty duoc ap dung CTKM

    public ClassifiedByEnterprise() {
    }

    public ClassifiedByEnterprise(String classificationId, int promotionIdActual, 
            List<Integer> enterpriseIdActuals) {
        this.classificationId = classificationId;
        this.promotionIdActual = promotionIdActual;
        this.enterpriseIdActuals = enterpriseIdActuals;
    }

    public String getClassificationId() {
        return classificationId;
    }

    public void setClassificationId(String classificationId) {
        this.classificationId = classificationId;
    }

    public List<Integer> getEnterpriseIdActuals() {
        return enterpriseIdActuals;
    }

    public void setEnterpriseIdActuals(List<Integer> enterpriseIdActuals) {
        this.enterpriseIdActuals = enterpriseIdActuals;
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
        return new ClassifiedByEnterpriseBN();
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
        return "Doanh nghiệp";
    }

    @Override
    public String getClassificationName() {
        return "Doanh nghiệp";
    }

    @Override
    public int getHardId() {
        return 2;
    }
}

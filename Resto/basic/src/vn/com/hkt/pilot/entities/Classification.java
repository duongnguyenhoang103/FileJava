/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.pilot.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.openide.util.lookup.ServiceProvider;
import vn.com.hkt.basic.Installer;
import vn.com.hkt.pilot.dialog.dao.ClassificationBN;
import vn.com.hkt.pilot.identity.access.api.IAccessData;
import vn.com.hkt.pilot.identity.entitiy.api.IEntity;

/**
 *
 * @author duong
 */
@ServiceProvider(service = IEntity.class)
@Entity
public class Classification implements IEntity {

    public static final String CLASSIFICATION_IMPORT = "chi";
    public static final String CLASSIFICATION_EXPORT = "thu";
    public static final String FIELD_ID = "id";
    public static final String FIELD_CLASSIFICATION_ID = "classificationId";
    public static final String FIELD_CLASSIFICATION_NAME = "classificationName";
    public static final String FIELD_CLASSIFICATION_TYPE = "classificationType";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String classificationId;
    private String classificationName;
    private String classificationType;

    public Classification() {
    }

    public Classification(String classificationId, String classificationName, String classificationType) {
        this.classificationId = classificationId;
        this.classificationName = classificationName;
        this.classificationType = classificationType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getClassificationId() {
        return classificationId;
    }

    public void setClassificationId(String classificationId) {
        this.classificationId = classificationId;
    }

    public String getClassificationName() {
        return classificationName;
    }

    public void setClassificationName(String classificationName) {
        this.classificationName = classificationName;
    }

    public String getClassificationType() {
        return classificationType;
    }

    public void setClassificationType(String classificationType) {
        this.classificationType = classificationType;
    }

    @Override
    public String toString() {
        return classificationName;
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
        return "Mô tả thông tinc cơ bản phân loại nghiệp vụ";
    }

    @Override
    public IAccessData getAccessDataOfEntity() {
        return new ClassificationBN();
    }

    @Override
    public String getFieldNameObjectId() {
        return FIELD_CLASSIFICATION_ID;
    }

    @Override
    public String getDataRealyOfField(String fieldName, String data) {
        return data;
    }

    @Override
    public String getDescriptionOfField(String fieldName) {
        if (fieldName.equals(FIELD_CLASSIFICATION_ID)) {
            return "Mã loại nghiệp vụ";
        } else if (fieldName.equals(FIELD_CLASSIFICATION_NAME)) {
            return "Tên loại nghiệp vụ";
        } else if (fieldName.equals(FIELD_CLASSIFICATION_TYPE)) {
            return "Loại hình nhập xuất";
        }
        return null;
    }
}

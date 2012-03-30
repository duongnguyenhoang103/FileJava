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
import vn.com.hkt.erm.enterprise.dao.EnterpriseBN;
import vn.com.hkt.hrm.person.dao.PersonBN;
import vn.com.hkt.pilot.identity.access.api.IAccessData;
import vn.com.hkt.pilot.identity.entitiy.api.IEntity;

/**
 *
 * @author khanguct
 */
@ServiceProvider(service = IEntity.class)
@Entity
public class Enterprise implements IEntity {

    public static final String FILED_ID = "id";
    public static final String FILED_ENTERPRISE_ID = "enterpriseId";
    public static final String FILED_ENTERPRISE_NAME = "enterpriseName";
    public static final String FILED_ENTERPRISE_PARENT_ID_ACTUAL = "enterpriseParentIdActual";
    public static final String FILED_DIRECTOR_ID_ACTUAL = "directorIdActual";
    public static final String FILED_SLOGAN = "slogan";
    public static final String FILED_PICTURE = "picture";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String enterpriseId;
    private String enterpriseName;
    private int enterpriseParentIdActual;
    private int directorIdActual;
    private byte[] picture;
    private String slogan;

    public Enterprise() {
    }

    public Enterprise(String enterpriseId, String enterpriseName, int enterpriseParentIdActual, int director, byte[] picture, String slogan) {
        this.enterpriseId = enterpriseId;
        this.enterpriseName = enterpriseName;
        this.enterpriseParentIdActual = enterpriseParentIdActual;
        this.directorIdActual = director;
        this.picture = picture;
        this.slogan = slogan;
    }

    public int getDirectorIdActual() {
        return directorIdActual;
    }

    public void setDirectorIdActual(int director) {
        this.directorIdActual = director;
    }

    public String getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(String enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

    public int getEnterpriseParentIdActual() {
        return enterpriseParentIdActual;
    }

    public void setEnterpriseParentIdActual(int enterpriseParentIdActual) {
        this.enterpriseParentIdActual = enterpriseParentIdActual;
    }

    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    public String getSlogan() {
        return slogan;
    }

    public void setSlogan(String slogan) {
        this.slogan = slogan;
    }

    @Override
    public String toString() {
        return this.enterpriseName;
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
        return "Mô tả thông tin cơ bản về doanh nghiệp";
    }

    @Override
    public IAccessData getAccessDataOfEntity() {
        return new EnterpriseBN();
    }

    @Override
    public String getFieldNameObjectId() {
        return FILED_ENTERPRISE_ID;
    }

    @Override
    public String getDataRealyOfField(String fieldName, String data) {
        String dt = data;
        try {
            if (fieldName.equals(FILED_DIRECTOR_ID_ACTUAL)) {
                dt = new PersonBN().getById(Integer.valueOf(data)).toString();
            } else if (fieldName.equals(FILED_ENTERPRISE_PARENT_ID_ACTUAL)) {
                dt = new EnterpriseBN().getById(Integer.valueOf(data)).toString();
            }
        } catch (Exception ex) {
        }
        return dt;
    }

    @Override
    public String getDescriptionOfField(String fieldName) {
        if (fieldName.equals(FILED_DIRECTOR_ID_ACTUAL)) {
            return "Người đứng đầu";
        } else if (fieldName.equals(FILED_ENTERPRISE_ID)) {
            return "Mã doanh nghiệp";
        } else if (fieldName.equals(FILED_ENTERPRISE_NAME)) {
            return "Tên doanh nghiệp";
        } else if (fieldName.equals(FILED_ENTERPRISE_PARENT_ID_ACTUAL)) {
            return "Doanh nghiệp cha";
        } else if (fieldName.equals(FILED_PICTURE)) {
            return "Logo";
        } else if (fieldName.equals(FILED_SLOGAN)) {
            return "Khẩu hiệu";
        } else {
            return null;
        }
    }

   
}

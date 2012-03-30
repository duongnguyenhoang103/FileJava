/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.pilot.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.openide.util.Exceptions;
import org.openide.util.lookup.ServiceProvider;
import vn.com.hkt.basic.Installer;
import vn.com.hkt.bom.product.dao.ProductBN;
import vn.com.hkt.erm.department.dao.DepartmentBN;
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
public class Product implements IEntity {

    public static final String FIELD_PRODUCT_ID = "productId";
    public static final String FIELD_IMAGE_PHOTO = "imagePhoto";
    public static final String FIELD_PRODUCT_NAME = "productName";
    public static final String FIELD_ENTERPRISE_ID_ACTUAL = "enterpriseIdActual";
    public static final String FIELD_DEPARTMENT_ID_ACTUAL = "departmentIdActual";
    public static final String FIELD_PERSON_ID_ACTUAL = "personDesignIdActual";
    public static final String FIELD_PRODUCT_GROUP_ID_ACTUAL = "productGroupIdActual";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String productId;
    private String productName;
    private int enterpriseIdActual;
    private int departmentIdActual;
    private int personDesignIdActual;// Nguoi sang lap
    private int productGroupIdActual;
    private byte[] imagePhoto;

    public Product() {
    }

    public Product(String productId, String productName, int enterpriseIdActual, int departmentIdActual, int personDesignIdActual, int productGroupIdActual, byte[] imagePhoto) {
        this.productId = productId;
        this.productName = productName;
        this.enterpriseIdActual = enterpriseIdActual;
        this.departmentIdActual = departmentIdActual;
        this.personDesignIdActual = personDesignIdActual;
        this.productGroupIdActual = productGroupIdActual;
        this.imagePhoto = imagePhoto;
    }

    public int getDepartmentIdActual() {
        return departmentIdActual;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setDepartmentIdActual(int departmentIdActual) {
        this.departmentIdActual = departmentIdActual;
    }

    public int getEnterpriseIdActual() {
        return enterpriseIdActual;
    }

    public void setEnterpriseIdActual(int enterpriseIdActual) {
        this.enterpriseIdActual = enterpriseIdActual;
    }

    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public byte[] getImagePhoto() {
        return imagePhoto;
    }

    public void setImagePhoto(byte[] imagePhoto) {
        this.imagePhoto = imagePhoto;
    }

    public int getPersonDesignIdActual() {
        return personDesignIdActual;
    }

    public void setPersonDesignIdActual(int personDesignIdActual) {
        this.personDesignIdActual = personDesignIdActual;
    }

    public int getProductGroupIdActual() {
        return productGroupIdActual;
    }

    public void setProductGroupIdActual(int productGroupIdActual) {
        this.productGroupIdActual = productGroupIdActual;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    @Override
    public String toString() {
        return this.productName;
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
        return "Miêu tả cơ bản về sản phẩm";
    }

    @Override
    public IAccessData getAccessDataOfEntity() {
        return new ProductBN();
    }

    @Override
    public String getFieldNameObjectId() {
        return FIELD_PRODUCT_ID;
    }

    @Override
    public String getDataRealyOfField(String fieldName, String data) {
        String dt = data;
        try {
            if (fieldName.equals(FIELD_DEPARTMENT_ID_ACTUAL)) {
                dt = new DepartmentBN().getById(Integer.valueOf(data)).toString();
            } else if (fieldName.equals(FIELD_ENTERPRISE_ID_ACTUAL)) {
                dt = new EnterpriseBN().getById(Integer.valueOf(data)).toString();
            } else if (fieldName.equals(FIELD_PERSON_ID_ACTUAL)) {
                dt = new PersonBN().getById(Integer.valueOf(data)).toString();
            } else if (fieldName.equals(FIELD_PRODUCT_GROUP_ID_ACTUAL)) {
                dt = new ProductBN().getById(Integer.valueOf(data)).toString();
            }
        } catch (Exception ex) {
            Exceptions.printStackTrace(ex);
        }
        return dt;
    }

    @Override
    public String getDescriptionOfField(String fieldName) {
        if (fieldName.equals(FIELD_DEPARTMENT_ID_ACTUAL)) {
            return "Phòng ban,dự án";
        } else if (fieldName.equals(FIELD_ENTERPRISE_ID_ACTUAL)) {
            return "Doanh nghiệp";
        } else if (fieldName.equals(FIELD_PERSON_ID_ACTUAL)) {
            return "Nhân viên";
        } else if (fieldName.equals(FIELD_PRODUCT_GROUP_ID_ACTUAL)) {
            return "Nhóm sản phẩm";
        } else if (fieldName.equals(FIELD_PRODUCT_ID)) {
            return "Mã sản phẩm";
        } else if (fieldName.equals(FIELD_PRODUCT_NAME)) {
            return "Tên sản phẩm";
        } else if (fieldName.equals(FIELD_IMAGE_PHOTO)) {
            return "Ảnh sản phẩm";
        }
        return null;
    }
}

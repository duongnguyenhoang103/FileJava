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
public class Department implements IEntity{

    public static final String FIELD_ID = "id";
    public static final String FIELD_DEPARTMENT_ID = "departmentId";
    public static final String FIELD_DEPARTMENT_NAME = "departmentName";
    public static final String FIELD_ENTERPRISE_ID_ACTUAL = "enterpriseIdActual";
    public static final String FIELD_PERSON_ID_ACTUAL = "personIdActual";
    public static final String FIELD_DEPARTMENT_PARENT_ID_ACTUAL = "departmentParentIdAcutal";
    public static final String FIELD_PRODUCT_ID_ACTUAL = "productIdActual";
    public static final String FIELD_PICTURE = "picture";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String departmentId;
    private String departmentName;
    private int enterpriseIdActual;
    private int personIdActual;// Truong du an
    private int departmentParentIdAcutal;//Dự án mẹ
    private int productIdActual; //  ma San pham
    private byte[] picture;

    public Department() {
    }

    public Department(String departmentId, String departmentName, int enterpriseIdActual, int personIdActual, int departmentParentIdAcutal, int productIdActual, byte[] picture) {
        this.departmentId = departmentId;
        this.departmentName = departmentName;
        this.enterpriseIdActual = enterpriseIdActual;
        this.personIdActual = personIdActual;
        this.departmentParentIdAcutal = departmentParentIdAcutal;
        this.productIdActual = productIdActual;
        this.picture = picture;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public int getDepartmentParentIdAcutal() {
        return departmentParentIdAcutal;
    }

    public void setDepartmentParentIdAcutal(int departmentParentIdAcutal) {
        this.departmentParentIdAcutal = departmentParentIdAcutal;
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

    public int getPersonIdActual() {
        return personIdActual;
    }

    public void setPersonIdActual(int personIdActual) {
        this.personIdActual = personIdActual;
    }

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    public int getProductIdActual() {
        return productIdActual;
    }

    public void setProductIdActual(int productIdActual) {
        this.productIdActual = productIdActual;
    }

    @Override
    public String toString() {
        return this.departmentName;
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
        return "Miêu tả thông tin cơ bản về dự án, phòng ban";
    }

    @Override
    public IAccessData getAccessDataOfEntity() {
        return new DepartmentBN();
    }

    @Override
    public String getFieldNameObjectId() {
        return FIELD_DEPARTMENT_ID;
    }

    @Override
    public String getDataRealyOfField(String fieldName, String data) {
        String dt = data;
        try {
            if (fieldName.equals(FIELD_ENTERPRISE_ID_ACTUAL)) {
                dt = new EnterpriseBN().getById(Integer.valueOf(data)).toString();
            } else if (fieldName.equals(FIELD_PERSON_ID_ACTUAL)) {
                dt = new PersonBN().getById(Integer.valueOf(data)).toString();
            } else if (fieldName.equals(FIELD_PRODUCT_ID_ACTUAL)) {
                dt = new ProductBN().getById(Integer.valueOf(data)).toString();
            } else if (fieldName.equals(FIELD_DEPARTMENT_PARENT_ID_ACTUAL)) {
                dt = new DepartmentBN().getById(Integer.valueOf(data)).toString();
            }
        } catch (Exception ex) {
        }
        return data;
    }

    @Override
    public String getDescriptionOfField(String fieldName) {
        if (fieldName.equals(FIELD_DEPARTMENT_ID)) {
            return "Mã phòng ban, dự án";
        } else if (fieldName.equals(FIELD_DEPARTMENT_NAME)) {
            return "Tên phòng ban dự án";
        } else if (fieldName.equals(FIELD_ENTERPRISE_ID_ACTUAL)) {
            return "Tên doanh nghiệp";
        } else if (fieldName.equals(FIELD_PERSON_ID_ACTUAL)) {
            return "Tên người đứng đầu";
        } else if (fieldName.equals(FIELD_PRODUCT_ID_ACTUAL)) {
            return "Tên sản phẩm phụ trách";
        } else if (fieldName.equals(FIELD_DEPARTMENT_PARENT_ID_ACTUAL)) {
            return "Tên phòng ban(dự án) cha";
        } else if (fieldName.equals(FIELD_PICTURE)) {
            return "Ảnh sản phấm";
        }
        return null;
    }

}

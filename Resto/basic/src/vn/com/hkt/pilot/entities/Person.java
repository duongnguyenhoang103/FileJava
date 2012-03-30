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
import vn.com.hkt.erm.department.dao.DepartmentBN;
import vn.com.hkt.erm.enterprise.dao.EnterpriseBN;
import vn.com.hkt.hrm.person.dao.PersonBN;
import vn.com.hkt.pilot.dialog.dao.CountryBN;
import vn.com.hkt.pilot.dialog.dao.MissionBN;
import vn.com.hkt.pilot.identity.access.api.IAccessData;
import vn.com.hkt.pilot.identity.entitiy.api.IEntity;

/**
 *
 * @author khanguct
 */
@ServiceProvider(service = IEntity.class)
@Entity
public class Person implements IEntity {

    public static final String FIELD_PERSON_ID = "personId";
    public static final String FIELD_PERSON_NAME = "personName";
    public static final String FIELD_ENTERPRISE_ID_ACTUAl = "enterpriseIdActual";
    public static final String FIELD_DEPARTMENt_ID_ACTUAL = "departmentIdActual";
    public static final String FIELD_MISSION_ID_ACTUAL = "missionIdActual";
    public static final String FIELD_COUNTRY_ID_ACTUAL = "countryIdActual";
    public static final String FIELD_IMAGGE = "image";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String personId;//Ma
    private String personName;
    private int enterpriseIdActual;//ma ty 
    private int departmentIdActual;// ten bo phan phong
    private int missionIdActual;//Chuc vu
    private int countryIdActual; // Quoc tich
    private byte[] image;// ảnh

    public Person() {
    }

    public Person(String personId, String personName, int enterpriseIdActual, int departmentIdActual, int missionIdActual, int countryIdActual, byte[] image) {
        this.personId = personId;
        this.personName = personName;
        this.enterpriseIdActual = enterpriseIdActual;
        this.departmentIdActual = departmentIdActual;
        this.missionIdActual = missionIdActual;
        this.countryIdActual = countryIdActual;
        this.image = image;
    }

    public int getCountryIdActual() {
        return countryIdActual;
    }

    public void setCountryIdActual(int countryIdActual) {
        this.countryIdActual = countryIdActual;
    }

    public int getDepartmentIdActual() {
        return departmentIdActual;
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

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public int getMissionIdActual() {
        return missionIdActual;
    }

    public void setMissionIdActual(int missionIdActual) {
        this.missionIdActual = missionIdActual;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    @Override
    public String toString() {
        return personName;
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
        return "Miêu tả thông tin cơ bản về Nhân viên";
    }

    @Override
    public IAccessData getAccessDataOfEntity() {
        return new PersonBN();
    }

    @Override
    public String getFieldNameObjectId() {
        return FIELD_PERSON_ID;
    }

    @Override
    public String getDataRealyOfField(String fieldName, String data) {
        String dt = data;
        try {
            if (fieldName.equals(FIELD_COUNTRY_ID_ACTUAL)) {
                dt = new CountryBN().getById(Integer.valueOf(data)).toString();
            } else if (fieldName.equals(FIELD_DEPARTMENt_ID_ACTUAL)) {
                dt = new DepartmentBN().getById(Integer.valueOf(data)).toString();
            } else if (fieldName.equals(FIELD_ENTERPRISE_ID_ACTUAl)) {
                dt = new EnterpriseBN().getById(Integer.valueOf(data)).toString();
            } else if (fieldName.equals(FIELD_MISSION_ID_ACTUAL)) {
                dt = new MissionBN().getById(Integer.valueOf(data)).toString();
            } 
        } catch (Exception ex) {
        }
        return dt;
    }

    @Override
    public String getDescriptionOfField(String fieldName) {
        if (fieldName.equals(FIELD_COUNTRY_ID_ACTUAL)) {
            return "Quốc gia";
        } else if (fieldName.equals(FIELD_DEPARTMENt_ID_ACTUAL)) {
            return "Phòng ban,dự án";
        } else if (fieldName.equals(FIELD_ENTERPRISE_ID_ACTUAl)) {
            return "Doanh nghiệp";
        } else if (fieldName.equals(FIELD_MISSION_ID_ACTUAL)) {
            return "Chức vụ";
        } else if (fieldName.equals(FIELD_IMAGGE)) {
            return "Ảnh";
        } else if (fieldName.equals(FIELD_PERSON_ID)) {
            return "Mã nhân viên";
        } else if (fieldName.equals(FIELD_PERSON_NAME)) {
            return "Tên nhân viên";
        }
        return null;
    }
}

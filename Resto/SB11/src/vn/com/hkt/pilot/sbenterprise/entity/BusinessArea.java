/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.pilot.sbenterprise.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import vn.com.hkt.pilot.identity.access.api.IAccessData;
import vn.com.hkt.pilot.identity.entitiy.api.IEntity;
import vn.com.hkt.pilot.sbenterprise.Installer;
import vn.com.hkt.pilot.subenterprise.dao.BusinessAreaBN;

/**
 *
 * @author BinLe
 */
@Entity
public class BusinessArea implements IEntity {

    public static final String TYPE_VN = "VN";
    public static final String TYPE_NACIS = "NAICS";
    public static final String FIELD_BUSINESS_CODE = "businessCode";
    public static final String FIELD_DESCRIPTION = "description";
    public static final String FIELD_TYPE = "type";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String businessCode;// mã kinh doanh
    private String description;// mô tả
    private String type=TYPE_VN;// quy dinh theo loại quốc tế(1) hay Việt Nam(1)

    public BusinessArea() {
    }

    public BusinessArea(String businessCode, String description, String type) {
        this.businessCode = businessCode;
        this.description = description;
        this.type = type;
    }

    public String getBusinessCode() {
        return businessCode;
    }

    public void setBusinessCode(String businessCode) {
        this.businessCode = businessCode;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return businessCode;
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
        return "Lĩnh vực kinh doanh được quy định";
    }

    @Override
    public IAccessData getAccessDataOfEntity() {
        return new BusinessAreaBN();
    }

    @Override
    public String getFieldNameObjectId() {
        return "id";
    }

    @Override
    public String getDataRealyOfField(String fieldName, String data) {
        return data;
    }

    @Override
    public String getDescriptionOfField(String fieldName) {
        if (fieldName.equals(FIELD_BUSINESS_CODE)) {
            return "Mã kinh doanh";
        }
        if (fieldName.equals(FIELD_DESCRIPTION)) {
            return "Miêu tả";
        }
        if (fieldName.equals(FIELD_TYPE)) {
            return "Theo quy định";
        }
        return "";
    }
}

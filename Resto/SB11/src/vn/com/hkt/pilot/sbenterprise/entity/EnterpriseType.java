/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.pilot.sbenterprise.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.openide.util.lookup.ServiceProvider;
import vn.com.hkt.pilot.identity.access.api.IAccessData;
import vn.com.hkt.pilot.identity.entitiy.api.IEntity;
import vn.com.hkt.pilot.sbenterprise.Installer;
import vn.com.hkt.pilot.subenterprise.dao.EnterpriseTypeBN;


/**
 * tình trạng công ty
 * @author BinLe
 */
@ServiceProvider(service = IEntity.class)
@Entity
public class EnterpriseType implements IEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String typeName;// tên tình trạng
    private String description;// miêu tả tình trạng

    public EnterpriseType() {
    }

    public EnterpriseType(String statusName, String description) {
        this.typeName = statusName;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatusName() {
        return typeName;
    }

    public void setStatusName(String statusName) {
        this.typeName = statusName;
    }

    @Override
    public String toString() {
        return typeName;
    }

    @Override
    public String getEntityName() {
        return getClass().getSimpleName();
    }

    @Override
    public String getModuleOfEntity() {
        return Installer.MODULE_NAME;
    }

    @Override
    public String getEntityDescription() {
        return "Miêu tả tình trạng công ty";
    }

    @Override
    public IAccessData getAccessDataOfEntity() {
        return new EnterpriseTypeBN();
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
        return fieldName;
    }
}

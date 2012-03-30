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
import vn.com.hkt.pilot.identity.access.api.IAccessData;
import vn.com.hkt.pilot.identity.entitiy.api.IEntity;
import vn.com.hkt.pilot.keymanager.api.PrefixManagerBN;

/**
 *
 * @author khangpn
 */
@ServiceProvider(service = IEntity.class)
@Entity
public class PrefixManager implements IEntity {

    public static final String FIELD_ID = "id";
    public static final String FIELD_ENTITY_TYPE_NAME = "entityTypeName";
    public static final String FIELD_LAST_PREFIX = "lastPrefix";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String entityTypeName;
    private String lastPrefix;

    public PrefixManager() {
    }

    public PrefixManager(String entityTypeName, String lastPrefix) {
        this.entityTypeName = entityTypeName;
        this.lastPrefix = lastPrefix;
    }

    public String getEntityTypeName() {
        return entityTypeName;
    }

    public void setEntityTypeName(String entityTypeName) {
        this.entityTypeName = entityTypeName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLastPrefix() {
        return lastPrefix;
    }

    public void setLastPrefix(String lastPrefix) {
        this.lastPrefix = lastPrefix;
    }

    @Override
    public String toString() {
        return lastPrefix + " " + entityTypeName;
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
        return "Mô tả tiền tố mã đối tượng";
    }

    @Override
    public IAccessData getAccessDataOfEntity() {
        return new PrefixManagerBN();
    }

    @Override
    public String getFieldNameObjectId() {
        //TODO không có trường objectId
        return "";
    }

    @Override
    public String getDataRealyOfField(String fieldName, String data) {
        return data;
    }

    @Override
    public String getDescriptionOfField(String fieldName) {
        if (fieldName.equals(FIELD_ENTITY_TYPE_NAME)) {
            return "Loại đối tượng";
        } else if (fieldName.equals(FIELD_LAST_PREFIX)) {
            return "Mã sau cuối cùng";
        }
        return null;
    }
}

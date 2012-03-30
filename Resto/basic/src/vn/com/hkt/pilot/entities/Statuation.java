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
import vn.com.hkt.bom.operation.dao.StatuationBN;
import vn.com.hkt.pilot.identity.access.api.IAccessData;
import vn.com.hkt.pilot.identity.entitiy.api.IEntity;

/**
 *
 * @author khangpn
 * Entity Tình trạng
 */
@ServiceProvider(service = IEntity.class)
@Entity
public class Statuation implements IEntity {

    public static final String FIELD_STATUATION_ID = "StatuationId";
    public static final String FIELD_STATUATION_NAME = "StatuationName";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String StatuationId;
    private String StatuationName;

    public Statuation() {
    }

    public Statuation(String StatuationId, String StatuationName) {
        this.StatuationId = StatuationId;
        this.StatuationName = StatuationName;
    }

    public String getStatuationId() {
        return StatuationId;
    }

    public void setStatuationId(String StatuationId) {
        this.StatuationId = StatuationId;
    }

    public String getStatuationName() {
        return StatuationName;
    }

    public void setStatuationName(String StatuationName) {
        this.StatuationName = StatuationName;
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
        return "Module basic";
    }

    @Override
    public IAccessData getAccessDataOfEntity() {
        return new StatuationBN();
    }

    @Override
    public String getFieldNameObjectId() {
        return FIELD_STATUATION_ID;
    }

    @Override
    public int getId() {
        return this.id;
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

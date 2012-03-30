/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.pilot.sb51.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.openide.util.lookup.ServiceProvider;
import vn.com.hkt.pilot.identity.access.api.IAccessData;
import vn.com.hkt.pilot.identity.entitiy.api.IEntity;
import vn.com.hkt.pilot.sb51.Installer;
import vn.com.hkt.pilot.sb51.spi.BillTypeBN;

/**
 *
 * @author BinLe
 */
@ServiceProvider(service=IEntity.class)
@Entity
public class BillType implements IEntity {

    private static final String FIELD_ID = "id";
    private static final String FIELD_BILL_TYPE_NAME = "billTypeName";
    private static final String FIELD_DESCRIPTION = "description";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String billTypeName;
    private String description;

    public BillType() {
    }

    public BillType(String billTypeName, String description) {
        this.billTypeName = billTypeName;
        this.description = description;
    }

    public String getBillTypeName() {
        return billTypeName;
    }

    public void setBillTypeName(String billTypeName) {
        this.billTypeName = billTypeName;
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

    @Override
    public String toString() {
        return billTypeName;
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
        return "Loại bình hóa đơn";
    }

    @Override
    public IAccessData getAccessDataOfEntity() {
        return new BillTypeBN();
    }

    @Override
    public String getFieldNameObjectId() {
        return FIELD_ID;
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

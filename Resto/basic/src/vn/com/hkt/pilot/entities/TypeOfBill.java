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
import vn.com.hkt.bom.operation.dao.TypeOfBillBN;
import vn.com.hkt.pilot.identity.access.api.IAccessData;
import vn.com.hkt.pilot.identity.entitiy.api.IEntity;

/**
 *
 * @author khangpn
 * Entity Loại hóa đơn
 */
@ServiceProvider(service = IEntity.class)
@Entity
public class TypeOfBill implements IEntity {

    public static final String FIELD_TYPEOFBILL_ID = "typeOfBillId";
    public static final String FIELD_TYPEOFBILL_NAME = "typeOfBillName";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String typeOfBillId;
    private String typeOfBillName;

    public TypeOfBill() {
    }

    public String getTypeOfBillId() {
        return typeOfBillId;
    }

    public void setTypeOfBillId(String typeOfBillId) {
        this.typeOfBillId = typeOfBillId;
    }

    public String getTypeOfBillName() {
        return typeOfBillName;
    }

    public void setTypeOfBillName(String typeOfBillName) {
        this.typeOfBillName = typeOfBillName;
    }

    public TypeOfBill(String typeOfBillId, String typeOfBillName) {
        this.typeOfBillId = typeOfBillId;
        this.typeOfBillName = typeOfBillName;
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
        return new TypeOfBillBN();
    }

    @Override
    public String getFieldNameObjectId() {
        return FIELD_TYPEOFBILL_ID;
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

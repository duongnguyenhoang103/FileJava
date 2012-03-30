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
import vn.com.hkt.pilot.dialog.dao.UnitsOfMeasurementBN;
import vn.com.hkt.pilot.identity.access.api.IAccessData;
import vn.com.hkt.pilot.identity.entitiy.api.IEntity;

/**
 *
 * @author duong
 */
@ServiceProvider(service = IEntity.class)
@Entity
public class UnitsOfMeasurement implements IEntity {

    public static final String FIELD_ID = "id";
    public static final String FIELD_NAME_UNITS = "nameUnits";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nameUnits;// đơn vị đo

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameUnits() {
        return nameUnits;
    }

    public void setNameUnits(String nameUnits) {
        this.nameUnits = nameUnits;
    }

    public UnitsOfMeasurement(int id, String nameUnits) {
        this.id = id;
        this.nameUnits = nameUnits;
    }

    public UnitsOfMeasurement() {
        super();
    }

    @Override
    public String toString() {
        return this.nameUnits;
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
        return "Mô tả thông tin về đơn vị tính";
    }

    @Override
    public IAccessData getAccessDataOfEntity() {
        return new UnitsOfMeasurementBN();
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
        if (fieldName.equals(FIELD_NAME_UNITS)) {
            return "đơn vị đo";
        }
        return null;
    }
}

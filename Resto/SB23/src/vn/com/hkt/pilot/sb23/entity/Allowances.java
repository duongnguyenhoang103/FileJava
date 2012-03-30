/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.pilot.sb23.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.openide.util.lookup.ServiceProvider;
import vn.com.hkt.pilot.identity.access.api.IAccessData;
import vn.com.hkt.pilot.identity.entitiy.api.IEntity;
import vn.com.hkt.pilot.sb23.Installer;
import vn.com.hkt.pilot.subpersonsb23.dao.AllowancesBN;

/**
 *
 * @author khangpn
 */
@ServiceProvider(service = IEntity.class)
@Entity
public class Allowances implements IEntity {

    public static final String FIELD_ALLOW_ANCES_ID = "allowancesId";
    public static final String FIELD_ALLOW_ANCES_NAME = "allowancesName";
    public static final String FIELD_PERSON_ID_ACTUAL = "personIdActual";
    public static final String FIELD_VALUE = "value";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String allowancesId; // Mã phụ cấp
    private String allowancesName; // Tên phụ cấp
    private int personIdActual; // Mã nhân viên
    private int value; // Giá trị

    public Allowances() {
    }

    public Allowances(int id, String allowancesId, String allowancesName,
            int personIdActual, int value) {
        this.id = id;
        this.allowancesId = allowancesId;
        this.allowancesName = allowancesName;
        this.personIdActual = personIdActual;
        this.value = value;
    }

    public int getPersonIdActual() {
        return personIdActual;
    }

    public void setPersonIdActual(int personIdActual) {
        this.personIdActual = personIdActual;
    }

    public String getAllowancesId() {
        return allowancesId;
    }

    public void setAllowancesId(String allowancesId) {
        this.allowancesId = allowancesId;
    }

    public String getAllowancesName() {
        return allowancesName;
    }

    public void setAllowancesName(String allowancesName) {
        this.allowancesName = allowancesName;
    }

    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return allowancesId;
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
        return "Module mở rộng SB23";
    }

    @Override
    public IAccessData getAccessDataOfEntity() {
        return new AllowancesBN();
    }

    @Override
    public String getFieldNameObjectId() {
        return FIELD_ALLOW_ANCES_ID;
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

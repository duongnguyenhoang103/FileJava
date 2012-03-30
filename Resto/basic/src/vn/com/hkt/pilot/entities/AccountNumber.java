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
import vn.com.hkt.bom.operation.dao.AccountNumberBN;
import vn.com.hkt.pilot.identity.access.api.IAccessData;
import vn.com.hkt.pilot.identity.entitiy.api.IEntity;

/**
 *
 * @author khangpn
 * Entity Số tài khoản
 */
@ServiceProvider(service = IEntity.class)
@Entity
public class AccountNumber implements IEntity {

    public static final String FIELD_ACCOUNTNUMBER_ID = "AccountNumberId";
    public static final String FIELD_ACCOUNTNUMBER_NAME = "AccountNumberName";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Override
    public String toString() {
        return AccountNumberName;
    }
    private String AccountNumberId;
    private String AccountNumberName;

    public AccountNumber() {
    }

    public AccountNumber(String AccountNumberId, String AccountNumberName) {
        this.AccountNumberId = AccountNumberId;
        this.AccountNumberName = AccountNumberName;
    }

    public String getAccountNumberId() {
        return AccountNumberId;
    }

    public void setAccountNumberId(String AccountNumberId) {
        this.AccountNumberId = AccountNumberId;
    }

    public String getAccountNumberName() {
        return AccountNumberName;
    }

    public void setAccountNumberName(String AccountNumberName) {
        this.AccountNumberName = AccountNumberName;
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
        return new AccountNumberBN();
    }

    @Override
    public String getFieldNameObjectId() {
        return FIELD_ACCOUNTNUMBER_ID;
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

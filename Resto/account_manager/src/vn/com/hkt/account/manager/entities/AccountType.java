/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.account.manager.entities;

import java.lang.reflect.Field;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.openide.util.Exceptions;
import org.openide.util.lookup.ServiceProvider;
import vn.com.hkt.account.manager.Installer;
import vn.com.hkt.account.manager.spi.AccountTypeDAO;
import vn.com.hkt.pilot.identity.access.api.IAccessData;
import vn.com.hkt.pilot.identity.entitiy.api.IEntity;

/**
 *
 * @author Admin
 */
@ServiceProvider(service = IEntity.class)
@Entity
public class AccountType implements IEntity {

    public static final String FIELD_ACCOUNT_TYPE_NAME = "AccountTypeName";
    public static final String FIELD_DESCRIPTION = "description";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String AccountTypeName;
    private String description;
    private List<Integer> interfacePermisstionsIdActual;

    public String getAccountTypeName() {
        return AccountTypeName;
    }

    public void setAccountTypeName(String AccountTypeName) {
        this.AccountTypeName = AccountTypeName;
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

    public List<Integer> getInterfacePermisstionsIdActual() {
        return interfacePermisstionsIdActual;
    }

    public void setInterfacePermisstionsIdActual(List<Integer> interfacePermisstionsIdActual) {
        this.interfacePermisstionsIdActual = interfacePermisstionsIdActual;
    }

    @Override
    public String toString() {
        return AccountTypeName;
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
        return "Mô tả thông tin về nhóm account";
    }

    @Override
    public IAccessData getAccessDataOfEntity() {
        return new AccountTypeDAO();
    }

    @Override
    public String getFieldNameObjectId() {
        return FIELD_ACCOUNT_TYPE_NAME;
    }

    @Override
    public String getDataRealyOfField(String fieldName, String data) {
        return data;
    }

    @Override
    public String getDescriptionOfField(String fieldName) {
        if (fieldName.equals(FIELD_ACCOUNT_TYPE_NAME)) {
            return "Tên lớp quản lý";
        } else if (fieldName.equals(FIELD_DESCRIPTION)) {
            return "Miêu tả lớp quản lý";
        }
        return null;
    }
}

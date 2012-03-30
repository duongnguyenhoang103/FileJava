/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.account.manager.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.swing.JOptionPane;
import org.openide.util.Lookup;
import org.openide.util.lookup.ServiceProvider;
import vn.com.hkt.account.manager.Installer;
import vn.com.hkt.account.manager.spi.AccountDAO;
import vn.com.hkt.account.manager.spi.AccountTypeDAO;
import vn.com.hkt.basic.api.IPersonBN;
import vn.com.hkt.pilot.identity.access.api.IAccessData;
import vn.com.hkt.pilot.identity.entitiy.api.IEntity;

/**
 *
 * @author Admin
 */
@ServiceProvider(service = IEntity.class)
@Entity
public class Account implements IEntity {

    public static final String FIELD_ID = "id";
    public static final String FIELD_USERNAME = "username";
    public static final String FIELD_PASSWORD = "password";
    public static final String FIELD_PERSON_ID_ACTUAL = "personIdActual";
    public static final String FIELD_ACCOUNT_TYPE_ID_ACTUAL = "accountTypeIdActual";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String username;
    private String password;
    private int personIdActual;
    private int accountTypeIdActual;

    public int getAccountTypeIdActual() {
        return accountTypeIdActual;
    }

    public void setAccountTypeIdActual(int accountTypeIdActual) {
        this.accountTypeIdActual = accountTypeIdActual;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPersonIdActual() {
        return personIdActual;
    }

    public void setPersonIdActual(int personIdActual) {
        this.personIdActual = personIdActual;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
        return "Thông tin account đăng nhập";
    }

    @Override
    public IAccessData getAccessDataOfEntity() {
        return new AccountDAO();
    }

    @Override
    public String getFieldNameObjectId() {
        return "";
    }

    @Override
    public String getDataRealyOfField(String fieldName, String data) {
        String dt = data;
        try {
            if (fieldName.equals(FIELD_ACCOUNT_TYPE_ID_ACTUAL)) {
                dt = new AccountTypeDAO().getById(Integer.valueOf(data)).toString();
            } else if (fieldName.equals(FIELD_PERSON_ID_ACTUAL)) {
                IPersonBN pbn = Lookup.getDefault().lookup(IPersonBN.class);
                dt = pbn.getById(Integer.valueOf(data)).toString();
            }
        } catch (Exception ex) {
        }
        return dt;
    }

    @Override
    public String getDescriptionOfField(String fieldName) {
        if (fieldName.equals(FIELD_ACCOUNT_TYPE_ID_ACTUAL)) {
            return "Lớp quản lý";
        } else if (fieldName.equals(FIELD_PASSWORD)) {
            return "Mật khẩu";
        } else if (fieldName.equals(FIELD_PERSON_ID_ACTUAL)) {
            return "Nhân viên";
        } else if (fieldName.equals(FIELD_USERNAME)) {
            return "Tên đăng nhập";
        }
        return null;
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.account.manager.entities;

import java.lang.reflect.Field;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.openide.util.lookup.ServiceProvider;
import vn.com.hkt.account.manager.Installer;
import vn.com.hkt.account.manager.spi.AccountTypeDAO;
import vn.com.hkt.account.manager.spi.InterfacePermissionDAO;
import vn.com.hkt.account.manager.spi.PermissionDAO;
import vn.com.hkt.account.manager.spi.UserInterfaceDAO;
import vn.com.hkt.pilot.identity.access.api.IAccessData;
import vn.com.hkt.pilot.identity.entitiy.api.IEntity;

/**
 *
 * @author Admin
 */
@ServiceProvider(service = IEntity.class)
@Entity
public class InterfacePermission implements IEntity {

    public static final String FIELD_ACCOUNT_TYPE_ID_ACTUAL = "accountTypeIdActual";
    public static final String FIELD_USER_INTERFACE_ID_ACTUAL = "userInterfaceIdActual";
    public static final String FIELD_PERMISSION_ID_ACTUAL = "permissionIdActual";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int accountTypeIdActual;
    private int userInterfaceIdActual;
    private int permissionIdActual;

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

    public int getPermissionIdActual() {
        return permissionIdActual;
    }

    public void setPermissionIdActual(int permissionIdActual) {
        this.permissionIdActual = permissionIdActual;
    }

    public int getUserInterfaceIdActual() {
        return userInterfaceIdActual;
    }

    public void setUserInterfaceIdActual(int userInterfaceIdActual) {
        this.userInterfaceIdActual = userInterfaceIdActual;
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
        return "Mô tả quyền hạn của nhóm account đối với 1 giao diện";
    }

    @Override
    public IAccessData getAccessDataOfEntity() {
        return new InterfacePermissionDAO();
    }

    @Override
    public String getFieldNameObjectId() {
        return "TODO";
    }

    @Override
    public String getDataRealyOfField(String fieldName, String data) {
        String dt = data;
        try {
            if (fieldName.equals(FIELD_ACCOUNT_TYPE_ID_ACTUAL)) {
                dt = new AccountTypeDAO().getById(Integer.valueOf(data)).toString();
            } else if (fieldName.equals(FIELD_PERMISSION_ID_ACTUAL)) {
                dt = new PermissionDAO().getById(Integer.valueOf(data)).toString();
            } else if (fieldName.equals(FIELD_USER_INTERFACE_ID_ACTUAL)) {
                dt = new UserInterfaceDAO().getById(Integer.valueOf(data)).toString();
            }
        } catch (Exception e) {
        }
        return dt;
    }

    @Override
    public String getDescriptionOfField(String fieldName) {
        if (fieldName.equals(FIELD_ACCOUNT_TYPE_ID_ACTUAL)) {
            return "Lớp quản lý";
        } else if (fieldName.equals(FIELD_PERMISSION_ID_ACTUAL)) {
            return "Quyền ";
        } else if (fieldName.equals(FIELD_USER_INTERFACE_ID_ACTUAL)) {
            return "Giao diện";
        }
        return null;
    }
}

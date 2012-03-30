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
import org.openide.util.Exceptions;
import org.openide.util.lookup.ServiceProvider;
import vn.com.hkt.account.manager.Installer;
import vn.com.hkt.account.manager.spi.PermissionDAO;
import vn.com.hkt.authenticate.identity.IdentityPermission;
import vn.com.hkt.pilot.identity.access.api.IAccessData;
import vn.com.hkt.pilot.identity.entitiy.api.IEntity;

/**
 *
 * @author Admin
 */
@ServiceProvider(service = IEntity.class)
@Entity
public class Permission implements IEntity {

    public static final int PERMISSION_FUNCTION_LOCK = IdentityPermission.PERMISSION_FUNCTION_LOCK;
    public static final int PERMISSION_FUNCTION_VIEW = IdentityPermission.PERMISSION_FUNCTION_VIEW;
    public static final int PERMISSION_FUNCTION_EDIT = IdentityPermission.PERMISSION_FUNCTION_EDIT;
    public static final String FILED_PERMISSTION_NAME = "permissionName";
    public static final String FILED_PERMISSTION_FUNCTION = "permissionFunction";
    public static final String FILED_DESCRIPTION = "description";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String permissionName;
    private int permissionFunction;
    private String description;

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

    public int getPermissionFunction() {
        return permissionFunction;
    }

    public void setPermissionFunction(int permissionFunction) {
        this.permissionFunction = permissionFunction;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    @Override
    public String toString() {
        return permissionName;
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
        return "Mô tả thông tin chi tiết về quyền hạn";
    }

    @Override
    public IAccessData getAccessDataOfEntity() {
        return new PermissionDAO();
    }

    @Override
    public String getFieldNameObjectId() {
        return FILED_PERMISSTION_NAME;
    }

    @Override
    public String getDataRealyOfField(String fieldName, String data) {
        return data;
    }

    @Override
    public String getDescriptionOfField(String fieldName) {
        if (fieldName.equals(FILED_DESCRIPTION)) {
            return "Miêu tả quyền";
        } else if (fieldName.equals(FILED_PERMISSTION_FUNCTION)) {
            return "Chức năng quyền";
        } else if (fieldName.equals(FILED_PERMISSTION_NAME)) {
            return "Tên quyền hạn";
        }
        return null;
    }
}

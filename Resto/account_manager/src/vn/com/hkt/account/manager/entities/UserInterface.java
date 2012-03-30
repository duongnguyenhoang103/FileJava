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
import vn.com.hkt.account.manager.spi.ModuleDAO;
import vn.com.hkt.account.manager.spi.UserInterfaceDAO;
import vn.com.hkt.pilot.identity.access.api.IAccessData;
import vn.com.hkt.pilot.identity.entitiy.api.IEntity;

/**
 *
 * @author Admin
 */
@ServiceProvider(service = IEntity.class)
@Entity
public class UserInterface implements IEntity {

    public static final String FIELD_ID = "id";
    public static final String FIELD_INTERFACE_NAME = "interfaceName";
    public static final String FIELD_DESCRIPTION = "description";
    public static final String FIELD_MODULE_ID_ACTUAL = "moduleIdActual";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String interfaceName;
    private String description;
    private int moduleIdActual;

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

    public String getInterfaceName() {
        return interfaceName;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    public int getModuleIdActual() {
        return moduleIdActual;
    }

    public void setModuleIdActual(int moduleId) {
        this.moduleIdActual = moduleId;
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
        return "Miêu tả thông tin về giao diện";
    }

    @Override
    public IAccessData getAccessDataOfEntity() {
        return new UserInterfaceDAO();
    }

    @Override
    public String getFieldNameObjectId() {
        return FIELD_INTERFACE_NAME;
    }

    @Override
    public String getDataRealyOfField(String fieldName, String data) {
        String dt = data;
        try {
            if (fieldName.equals(FIELD_MODULE_ID_ACTUAL)) {
                return new ModuleDAO().getById(Integer.valueOf(data)).toString();
            }
        } catch (Exception ex) {
            Exceptions.printStackTrace(ex);
        }
        return dt;
    }

    @Override
    public String getDescriptionOfField(String fieldName) {
        if (fieldName.equals(FIELD_DESCRIPTION)) {
            return "Miêu tả giao diện";
        } else if (fieldName.equals(FIELD_INTERFACE_NAME)) {
            return "Tên giao diện giao diện";
        } else if (fieldName.equals(FIELD_MODULE_ID_ACTUAL)) {
            return "Modue";
        } else {
            return null;
        }
    }
}

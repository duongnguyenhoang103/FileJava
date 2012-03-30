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
import org.openide.util.lookup.ServiceProvider;
import vn.com.hkt.account.manager.Installer;
import vn.com.hkt.account.manager.spi.ModuleDAO;
import vn.com.hkt.pilot.identity.access.api.IAccessData;
import vn.com.hkt.pilot.identity.entitiy.api.IEntity;

/**
 *
 * @author Admin
 */
@ServiceProvider(service = IEntity.class)
@Entity
public class Module implements IEntity {

    public static final String FIELD_ID = "id";
    public static final String FIELD_MODULE_NAME = "moduleName";
    public static final String FIELD_MODULE_DESCRIPTION = "description";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String moduleName;
    private String description;
    private List<Integer> userInterfacesId;

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

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public List<Integer> getUserInterfacesId() {
        return userInterfacesId;
    }

    public void setUserInterfacesId(List<Integer> userInterfacesId) {
        this.userInterfacesId = userInterfacesId;
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
        return "Mô tả thông tin về một module";
    }

    @Override
    public IAccessData getAccessDataOfEntity() {
        return new ModuleDAO();
    }

    @Override
    public String getFieldNameObjectId() {
        return Installer.MODULE_NAME;
    }

    @Override
    public String getDataRealyOfField(String fieldName, String data) {
        return data;
    }

    @Override
    public String getDescriptionOfField(String fieldName) {
        if (fieldName.equals(FIELD_MODULE_DESCRIPTION)) {
            return "Miêu tả module";
        } else if (fieldName.equals(FIELD_MODULE_NAME)) {
            return "Tên module";
        }
        return null;
    }
}

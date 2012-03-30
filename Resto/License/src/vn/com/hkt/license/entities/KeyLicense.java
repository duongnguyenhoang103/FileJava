/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.license.entities;

import java.lang.reflect.Field;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.openide.util.lookup.ServiceProvider;
import vn.com.hkt.license.Installer;
import vn.com.hkt.license.spi.KeyLicenseDAO;
import vn.com.hkt.pilot.identity.access.api.IAccessData;
import vn.com.hkt.pilot.identity.entitiy.api.IEntity;

/**
 *
 * KeyLicense : key đăng kí của người dùng
 */
@ServiceProvider(service = IEntity.class)
@Entity
public class KeyLicense implements IEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;// id
    private String keyMachine;// key mã máy
    private String keyUser;// key người dùng

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKeyMachine() {
        return keyMachine;
    }

    public void setKeyMachine(String keyMachine) {
        this.keyMachine = keyMachine;
    }

    public String getKeyUser() {
        return keyUser;
    }

    public void setKeyUser(String keyUser) {
        this.keyUser = keyUser;
    }
    // TODO toString();   

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
        return "Miêu tả thông tin key đăng ký";
    }

    @Override
    public IAccessData getAccessDataOfEntity() {
        return new KeyLicenseDAO();
    }

    @Override
    public String getFieldNameObjectId() {
        return "TODO";
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

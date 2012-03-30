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
import vn.com.hkt.license.spi.KeyInstallDAO;
import vn.com.hkt.pilot.identity.access.api.IAccessData;
import vn.com.hkt.pilot.identity.entitiy.api.IEntity;

/**
 * Key dùng để cài đặt sử dụng
 *
 */
@ServiceProvider(service = IEntity.class)
@Entity
public class KeyInstall implements IEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;//ID
    private String keyInstall;// key cài đặt
    private String module;// tên module
    private String dateActivate;// ngày kích hoạt
    private int keyLicenseId;// id keyLicense

    public int getKeyLicenseId() {
        return keyLicenseId;
    }

    public void setKeyLicenseId(int keyLicenseId) {
        this.keyLicenseId = keyLicenseId;
    }

    public String getDateActivate() {
        return dateActivate;
    }

    public void setDateActivate(String dateActivate) {
        this.dateActivate = dateActivate;
    }

    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKeyInstall() {
        return keyInstall;
    }

    public void setKeyInstall(String keyInstall) {
        this.keyInstall = keyInstall;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
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
        return "Mô tả thông tin về key cài đặt";
    }

    @Override
    public IAccessData getAccessDataOfEntity() {
        return new KeyInstallDAO();
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

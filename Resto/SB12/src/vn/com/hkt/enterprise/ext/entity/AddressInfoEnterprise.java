/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.enterprise.ext.entity;

import java.lang.reflect.Field;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.openide.util.lookup.ServiceProvider;
import vn.com.hkt.enterprise.ext.dao.AddressInfoEnterpriseBN;
import vn.com.hkt.extension.Installer;
import vn.com.hkt.pilot.identity.access.api.IAccessData;
import vn.com.hkt.pilot.identity.entitiy.api.IEntity;

/**
 *
 * @author khangpn
 */
@Entity
@ServiceProvider(service = IEntity.class)
public class AddressInfoEnterprise implements IEntity {

    public static final String FIELD_ADDRESS_INFO_ID = "addressInfoId";
    public static final String FIELD_ADDRESS_INFO_NAME = "addressInfoName";
    public static final String FIELD_COUNTRY_ID_ACTUAL = "countryIdActual";
    public static final String FIELD_CITY_ID_ACTUAL = "cityIdActual";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String addressInfoName;
    private int countryIdActual;
    private int cityIdActual;

    public AddressInfoEnterprise() {
    }

    public AddressInfoEnterprise(String addressInfoName,
            int countryIdActual, int cityIdActual) {
        this.addressInfoName = addressInfoName;
        this.countryIdActual = countryIdActual;
        this.cityIdActual = cityIdActual;
    }


    public String getAddressInfoName() {
        return addressInfoName;
    }

    public void setAddressInfoName(String addressInfoName) {
        this.addressInfoName = addressInfoName;
    }

    public int getCityIdActual() {
        return cityIdActual;
    }

    public void setCityIdActual(int cityIdActual) {
        this.cityIdActual = cityIdActual;
    }

    public int getCountryIdActual() {
        return countryIdActual;
    }

    public void setCountryIdActual(int countryIdActual) {
        this.countryIdActual = countryIdActual;
    }

    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "";
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
        return "Module mở rộng SB22";
    }

    @Override
    public IAccessData getAccessDataOfEntity() {
        return new AddressInfoEnterpriseBN();
    }

    @Override
    public String getFieldNameObjectId() {
        return FIELD_ADDRESS_INFO_ID;
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

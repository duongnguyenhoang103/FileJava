/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.pilot.sb22.person.extW23.entity;

import java.lang.reflect.Field;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.openide.util.lookup.ServiceProvider;
import vn.com.hkt.pilot.identity.access.api.IAccessData;
import vn.com.hkt.pilot.identity.entitiy.api.IEntity;
import vn.com.hkt.pilot.sb22.Installer;
import vn.com.hkt.pilot.sb22.person.extW23.dao.AddressInfoBN;

/**
 *
 * @author khangpn
 */
@Entity
@ServiceProvider(service = IEntity.class)
public class AddressInfo implements IEntity {

    public static final String FIELD_ADDRESS_INFO_ID = "addressInfoId";
    public static final String FIELD_ADDRESS_INFO_NAME = "addressInfoName";
    public static final String FIELD_COUNTRY_ID_ACTUAL = "countryIdActual";
    public static final String FIELD_CITY_ID_ACTUAL = "cityIdActual";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int addressInfoId;  // Luu ID person
    private String addressInfoName;
    private int countryIdActual;
    private int cityIdActual;

    public AddressInfo() {
    }

    public AddressInfo(int addressInfoId, String addressInfoName,
            int countryIdActual, int cityIdActual) {
        this.addressInfoId = addressInfoId;
        this.addressInfoName = addressInfoName;
        this.countryIdActual = countryIdActual;
        this.cityIdActual = cityIdActual;
    }

    public int getAddressInfoId() {
        return addressInfoId;
    }

    public void setAddressInfoId(int addressInfoId) {
        this.addressInfoId = addressInfoId;
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
        return addressInfoId+"";
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
        return new AddressInfoBN();
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

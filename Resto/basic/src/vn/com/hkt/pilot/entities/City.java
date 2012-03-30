/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.pilot.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.openide.util.lookup.ServiceProvider;
import vn.com.hkt.basic.Installer;
import vn.com.hkt.pilot.dialog.dao.CityBN;
import vn.com.hkt.pilot.dialog.dao.CountryBN;
import vn.com.hkt.pilot.dialog.dao.PartitionBN;
import vn.com.hkt.pilot.identity.access.api.IAccessData;
import vn.com.hkt.pilot.identity.entitiy.api.IEntity;

/**
 *
 * @author duong
 */
@ServiceProvider(service = IEntity.class)
@Entity
public class City implements IEntity {

    public static final String FIELD_ID = "id";
    public static final String FIELD_CITY_ID = "cityId";
    public static final String FIELD_CITY_NAME = "cityName";
    public static final String FIELD_PARTITION_ID = "partitionIdActual";
    public static final String FIELD_COUNTRY_ID = "countryIdActual";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String cityId;
    private String cityName;// tên thành phố
    private int partitionIdActual; // Thuộc vùng miền nào
    private int countryIdActual; // Thuộc QG nào
    private int postalCode;

    public int getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }

    public City() {
    }

    public City(String cityId, String cityName, int partitionIdActual, int countryIdActual) {
        this.cityId = cityId;
        this.cityName = cityName;
        this.partitionIdActual = partitionIdActual;
        this.countryIdActual = countryIdActual;
    }

    public int getCountryIdActual() {
        return countryIdActual;
    }

    public void setCountryIdActual(int countryIdActual) {
        this.countryIdActual = countryIdActual;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPartitionIdActual() {
        return partitionIdActual;
    }

    public void setPartitionIdActual(int partitionIdActual) {
        this.partitionIdActual = partitionIdActual;
    }

    @Override
    public String toString() {
        return cityName;
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
        return "Mô tả tổng quát về thành phố";
    }

    @Override
    public IAccessData getAccessDataOfEntity() {
        return new CityBN();
    }

    @Override
    public String getFieldNameObjectId() {
        return FIELD_CITY_ID;
    }    

    @Override
    public String getDataRealyOfField(String fieldName, String data) {
        String dt = data;
        try {
            if (fieldName.equals(FIELD_PARTITION_ID)) {
                dt = new PartitionBN().getById((Integer.valueOf(data))).toString();
            } else if (fieldName.equals(FIELD_COUNTRY_ID)) {
                return new CountryBN().getById((Integer.valueOf(data))).toString();
            }
        } catch (Exception e) {
        }
        return dt;
    }

    @Override
    public String getDescriptionOfField(String fieldName) {
        if (fieldName.equals(FIELD_CITY_ID)) {
            return "Mã thành phố";
        } else if (fieldName.equals(FIELD_CITY_NAME)) {
            return "Tên thành phố";
        } else if (fieldName.equals(FIELD_COUNTRY_ID)) {
            return "Quốc gia";
        } else if (fieldName.equals(FIELD_PARTITION_ID)) {
            return "Khu vực";
        }
        return null;
    }
}

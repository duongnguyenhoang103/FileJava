/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.pilot.entities;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.openide.util.lookup.ServiceProvider;
import vn.com.hkt.basic.Installer;
import vn.com.hkt.pilot.dialog.dao.CountryBN;
import vn.com.hkt.pilot.identity.access.api.IAccessData;
import vn.com.hkt.pilot.identity.entitiy.api.IEntity;

/**
 *
 * @author duong
 */
@ServiceProvider(service = IEntity.class)
@Entity
public class Country implements IEntity {

    public static final String FIELD_ID = "id";
    public static final String FIELD_COUNTRY_ID = "countryId";
    public static final String FIELD_COUNTRY_NAME = "countryName";
    public static final String FIELD_LANGUAGE = "language";
    public static final String FIELD_NATIONAL_FLAG = "nationalFlag";
    public static final String FIELD_NATIONALITY = "nationality";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String countryId;// ma quoc gia tu nhap
    private String countryName;// ten quoc gia
    private String language; // Ngôn ngữ
    private String nationalFlag; // Quốc kỳ
    private String nationality; // Quốc tịch    
    private List<Integer> partititionsIdActual;// danh sach vung mien
    private List<Integer> citysIdActual;// danh sach thanh pho

    public Country() {
    }

    public Country(String countryId, String countryName, String language, String nationalFlag, String nationality, List<Integer> partititionsIdActual, List<Integer> citysIdActual) {
        this.countryId = countryId;
        this.countryName = countryName;
        this.language = language;
        this.nationalFlag = nationalFlag;
        this.nationality = nationality;
        this.partititionsIdActual = partititionsIdActual;
        this.citysIdActual = citysIdActual;
    }

    @Override
    public String toString() {
        return countryName;
    }

    public List<Integer> getCitysIdActual() {
        return citysIdActual;
    }

    public void setCitysIdActual(List<Integer> citysIdActual) {
        this.citysIdActual = citysIdActual;
    }

    public String getCountryId() {
        return countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getNationalFlag() {
        return nationalFlag;
    }

    public void setNationalFlag(String nationalFlag) {
        this.nationalFlag = nationalFlag;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public List<Integer> getPartititionsIdActual() {
        return partititionsIdActual;
    }

    public void setPartititionsIdActual(List<Integer> partititionsIdActual) {
        this.partititionsIdActual = partititionsIdActual;
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
        return "Mô tả thông tin cơ bản về quốc gia";
    }

    @Override
    public IAccessData getAccessDataOfEntity() {
        return new CountryBN();
    }

    @Override
    public String getFieldNameObjectId() {
        return FIELD_COUNTRY_ID;
    }

    @Override
    public String getDataRealyOfField(String fieldName, String data) {
        return data;
    }

    @Override
    public String getDescriptionOfField(String fieldName) {
        if (fieldName.equals(FIELD_COUNTRY_ID)) {
            return "Mã đất nước";
        } else if (fieldName.equals(FIELD_COUNTRY_NAME)) {
            return "Tên đất nước";
        } else if (fieldName.equals(FIELD_LANGUAGE)) {
            return "Ngôn ngữ";
        } else if (fieldName.equals(FIELD_NATIONALITY)) {
            return "Quốc tịch";
        } else if (fieldName.equals(FIELD_NATIONAL_FLAG)) {
            return "Quốc kỳ";
        } else {
            return "";
        }
    }
}

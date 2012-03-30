/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.pilot.sb22.person.extW23.entity;

import java.lang.reflect.Field;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.openide.util.lookup.ServiceProvider;
import vn.com.hkt.pilot.identity.access.api.IAccessData;
import vn.com.hkt.pilot.identity.entitiy.api.IEntity;
import vn.com.hkt.pilot.sb22.Installer;
import vn.com.hkt.pilot.sb22.person.extW23.dao.SubPersonSB22BN;

/**
 *
 * @author duong
 */
@Entity
@ServiceProvider(service = IEntity.class)
public class SubPersonSB22 implements IEntity {

    public static final String FIELD_PERSON_ID_ACTUAL = "personIdActual";
    public static final String FIELD_TEL = "tel";
    public static final String FIELD_FAX = "fax";
    public static final String FIELD_EMAIL = "email";
    public static final String FIELD_WEB = "web";
    public static final String FIELD_COUNTRY_ID_ACTUAL = "countryIdActual";
    public static final String FIELD_ADDRESS_ID_ACTUAL = "addressIdActual";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int personIdActual;
    private String tel;
    private String fax;
    private String email;
    private String web;
    private int countryIdActual;
    private List<Integer> addressIdActual;

    /**
     * Constructors
     */
    public SubPersonSB22(int personIdActual, String tel, String fax,
            String email, String web, int countryIdActual,
            List<Integer> addressIdActual) {
        this.personIdActual = personIdActual;
        this.tel = tel;
        this.fax = fax;
        this.email = email;
        this.web = web;
        this.countryIdActual = countryIdActual;
        this.addressIdActual = addressIdActual;
    }

    public SubPersonSB22() {
    }

    /**
     * Getters and Setters
     */
    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    public List<Integer> getAddressIdActual() {
        return addressIdActual;
    }

    public void setAddressIdActual(List<Integer> addressIdActual) {
        this.addressIdActual = addressIdActual;
    }

    public int getCountryIdActual() {
        return countryIdActual;
    }

    public void setCountryIdActual(int countryIdActual) {
        this.countryIdActual = countryIdActual;
    }

    public int getPersonIdActual() {
        return personIdActual;
    }

    public void setPersonIdActual(int personIdActual) {
        this.personIdActual = personIdActual;
    }

    @Override
    public String toString() {
        return String.valueOf(personIdActual);
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
        return new SubPersonSB22BN();
    }

    @Override
    public String getFieldNameObjectId() {
        return FIELD_PERSON_ID_ACTUAL;
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

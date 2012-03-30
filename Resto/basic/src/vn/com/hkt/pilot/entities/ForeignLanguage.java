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
import vn.com.hkt.hrm.person.dao.ForeignLanguageBN;
import vn.com.hkt.pilot.identity.access.api.IAccessData;
import vn.com.hkt.pilot.identity.entitiy.api.IEntity;

/**
 *
 * @author khangpn
 */
@Entity
@ServiceProvider(service = IEntity.class)
public class ForeignLanguage implements IEntity {

    public static final String FIELD_FOREIGNLANGUAGE_ID = "foreignLanguageId";
    public static final String FIELD_FOREIGNLANGUAGE_NAME = "foreignLanguageName";
    public static final String FIELD_COUNTRY_ID_ACTUAL = "countryIdActual";
    public static final String FIELD_NOTE = "note";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String foreignLanguageId; // Mã ngoại ngữ
    private String foreignLanguageName; // Tên ngoại ngữ
    private String note; // Ghi chú
    private int countryIdActual;// mã quốc gia

    public ForeignLanguage() {
    }

    public ForeignLanguage(int countryIdActual, String foreignLanguageId, String foreignLanguageName,
            String note) {
        this.countryIdActual = countryIdActual;
        this.foreignLanguageId = foreignLanguageId;
        this.foreignLanguageName = foreignLanguageName;
        this.note = note;
    }

    public void setCountryIdActual(int countryIdActual) {
        this.countryIdActual = countryIdActual;
    }

    public int getCountryIdActual() {
        return countryIdActual;
    }

    public String getForeignLanguageId() {
        return foreignLanguageId;
    }

    public void setForeignLanguageId(String foreignLanguageId) {
        this.foreignLanguageId = foreignLanguageId;
    }

    public String getForeignLanguageName() {
        return foreignLanguageName;
    }

    public void setForeignLanguageName(String foreignLanguageName) {
        this.foreignLanguageName = foreignLanguageName;
    }

    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return foreignLanguageName;
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
        return "Module mở rộng SB24";
    }

    @Override
    public IAccessData getAccessDataOfEntity() {
        return new ForeignLanguageBN();
    }

    @Override
    public String getFieldNameObjectId() {
        return FIELD_FOREIGNLANGUAGE_ID;
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

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.pilot.sb24.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.openide.util.lookup.ServiceProvider;
import vn.com.hkt.pilot.identity.access.api.IAccessData;
import vn.com.hkt.pilot.identity.entitiy.api.IEntity;
import vn.com.hkt.pilot.sb24.Installer;
import vn.com.hkt.pilot.sb24.dao.SkillLanguageBN;

/**
 *
 * @author khangpn
 */
@Entity
@ServiceProvider(service = IEntity.class)
public class SkillLanguage implements IEntity {

    public static final String FIELD_FOREIGNLANGUAGE_ID = "foreignLanguageId";
    public static final String FIELD_FOREIGNLANGUAGE_NAME = "foreignLanguageName";
    public static final String FIELD_NOTE = "note";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int foreignLanguageId; // Mã ngoại ngữ   
    private int levelLanguageIdActual;// trình độ ngoại ngữ

    public SkillLanguage() {
    }

    public SkillLanguage(int foreignLanguageId, int levelLanguageIdActual) {
        this.foreignLanguageId = foreignLanguageId;
        this.levelLanguageIdActual = levelLanguageIdActual;
    }

    public int getForeignLanguageId() {
        return foreignLanguageId;
    }

    public void setForeignLanguageId(int foreignLanguageId) {
        this.foreignLanguageId = foreignLanguageId;
    }

    public int getLevelLanguageIdActual() {
        return levelLanguageIdActual;
    }

    public void setLevelLanguageIdActual(int levelLanguageIdActual) {
        this.levelLanguageIdActual = levelLanguageIdActual;
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
        return "Module mở rộng SB24";
    }

    @Override
    public IAccessData getAccessDataOfEntity() {
        return new SkillLanguageBN();
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

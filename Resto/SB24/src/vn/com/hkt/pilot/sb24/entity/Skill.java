/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.pilot.sb24.entity;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.openide.util.lookup.ServiceProvider;
import vn.com.hkt.pilot.identity.access.api.IAccessData;
import vn.com.hkt.pilot.identity.entitiy.api.IEntity;
import vn.com.hkt.pilot.sb24.Installer;
import vn.com.hkt.pilot.sb24.dao.SkillBN;

/**
 *
 * @author khangpn
 */
@Entity
@ServiceProvider(service = IEntity.class)
public class Skill implements IEntity {

    public static final String FIELD_PERSON_ID_ACTUAL = "personIdActual";
    public static final String FIELD_PARTICULARITY = "particularity";
    public static final String FIELD_PERSONALPREFERENCES = "personalPreferences";
    public static final String FIELD_CAREER_INTERESTS = "careerInterests";
    public static final String FIELD_RESEARCH_INTERESTS = "researchInterests";
    public static final String FIELD_INFOMATICS = "informatics";
    public static final String FIELD_FOREIGNLANGUAGE_ID_ACTUAL = "foreignLanguageIdActual";
    public static final String FIELD_LEVEL = "level";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int personIdActual; // Mã nhân viên
    private String particularity; // Đặc tính
    private String personalPreferences; // Sở thích cá nhân
    private String careerInterests; // Sở thích nghề nghiệp
    private String researchInterests; // Sở thích nghiên cứu
    private String informatics; // Tin học
    private List<Integer> skillLanguagesIdActual; // Ngoại ngữ    

    public Skill() {
    }

    public Skill(int personIdActual, String particularity, String personalPreferences, String careerInterests, String researchInterests, String informatics, List<Integer> skillLanguagesIdActual) {
        this.personIdActual = personIdActual;
        this.particularity = particularity;
        this.personalPreferences = personalPreferences;
        this.careerInterests = careerInterests;
        this.researchInterests = researchInterests;
        this.informatics = informatics;
        this.skillLanguagesIdActual = skillLanguagesIdActual;
    }

    public List<Integer> getSkillLanguagesIdActual() {
        return skillLanguagesIdActual;
    }

    public void setSkillLanguagesIdActual(List<Integer> skillLanguagesIdActual) {
        this.skillLanguagesIdActual = skillLanguagesIdActual;
    }

    public int getPersonIdActual() {
        return personIdActual;
    }

    public void setPersonIdActual(int personIdActual) {
        this.personIdActual = personIdActual;
    }

    public String getCareerInterests() {
        return careerInterests;
    }

    public void setCareerInterests(String careerInterests) {
        this.careerInterests = careerInterests;
    }

    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getInformatics() {
        return informatics;
    }

    public void setInformatics(String informatics) {
        this.informatics = informatics;
    }

    public String getParticularity() {
        return particularity;
    }

    public void setParticularity(String particularity) {
        this.particularity = particularity;
    }

    public String getPersonalPreferences() {
        return personalPreferences;
    }

    public void setPersonalPreferences(String personalPreferences) {
        this.personalPreferences = personalPreferences;
    }

    public String getResearchInterests() {
        return researchInterests;
    }

    public void setResearchInterests(String researchInterests) {
        this.researchInterests = researchInterests;
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
        return "Module mở rộng SB24";
    }

    @Override
    public IAccessData getAccessDataOfEntity() {
        return new SkillBN();
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

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.pilot.sb25.subpersonsb25.entity;

import java.lang.reflect.Field;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.openide.util.lookup.ServiceProvider;
import vn.com.hkt.pilot.identity.access.api.IAccessData;
import vn.com.hkt.pilot.identity.entitiy.api.IEntity;
import vn.com.hkt.pilot.sb25.Installer;
import vn.com.hkt.pilot.sb25.dao.SubPersonSb25BN;

/**
 *
 * @author khangpn
 */
@Entity
@ServiceProvider(service = IEntity.class)
public class SubPersonSB25 implements IEntity {

    public static final String FIELD_PERSON_ID_ACTUAL = "personIdActual";
    public static final String FIELD_WORKING_PROCCESSID_ID_ACTUAL = "workingProccessIdActual";
    public static final String FIELD_LEARNING_PROCCESSID_ID_ACTUAL = "learningProccessIdActual";
    public static final String FIELD_PUBLICATION_ID_ACTUAL = "publicationIdActual";
    public static final String FIELD_OTHER_ID_ACTUAL = "otherProfileIdActual";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int personIdActual;
    private List<Integer> workingProccessIdActual;
    private List<Integer> learningProccessIdActual;
    private List<Integer> publicationIdActual;
    private List<Integer> otherProfileIdActual;

    public SubPersonSB25() {
    }

    public SubPersonSB25(int personIdActual, List<Integer> workingProccessIdActual,
            List<Integer> learningProccessIdActual, List<Integer> publicationIdActual,
            List<Integer> otherProfileIdActual) {
        this.personIdActual = personIdActual;
        this.workingProccessIdActual = workingProccessIdActual;
        this.learningProccessIdActual = learningProccessIdActual;
        this.publicationIdActual = publicationIdActual;
        this.otherProfileIdActual = otherProfileIdActual;
    }

    public List<Integer> getOtherProfileIdActual() {
        return otherProfileIdActual;
    }

    public void setOtherProfileIdActual(List<Integer> otherProfileIdActual) {
        this.otherProfileIdActual = otherProfileIdActual;
    }

    public List<Integer> getPublicationIdActual() {
        return publicationIdActual;
    }

    public void setPublicationIdActual(List<Integer> publicationIdActual) {
        this.publicationIdActual = publicationIdActual;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Integer> getLearningProccessIdActual() {
        return learningProccessIdActual;
    }

    public void setLearningProccessIdActual(List<Integer> learningProccessIdActual) {
        this.learningProccessIdActual = learningProccessIdActual;
    }

    public int getPersonIdActual() {
        return personIdActual;
    }

    public void setPersonIdActual(int personIdActual) {
        this.personIdActual = personIdActual;
    }

    public List<Integer> getWorkingProccessIdActual() {
        return workingProccessIdActual;
    }

    public void setWorkingProccessIdActual(List<Integer> workingProccessIdActual) {
        this.workingProccessIdActual = workingProccessIdActual;
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
        return "Module mở rộng SB25";
    }

    @Override
    public IAccessData getAccessDataOfEntity() {
        return new SubPersonSb25BN();
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
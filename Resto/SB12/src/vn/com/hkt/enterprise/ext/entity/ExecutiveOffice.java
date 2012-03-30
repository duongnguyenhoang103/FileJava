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
import vn.com.hkt.enterprise.ext.dao.ExecutiveBN;
import vn.com.hkt.extension.Installer;
import vn.com.hkt.pilot.identity.access.api.IAccessData;
import vn.com.hkt.pilot.identity.entitiy.api.IEntity;

/**
 *
 * @author khangpn
 */
@Entity
@ServiceProvider(service = IEntity.class)
public class ExecutiveOffice implements IEntity {

    public static final String FIELD_EXECUTIVEOFFICE_ID_ACTUAL = "executiveOfficeIdActual";
    public static final String FIELD_EXECUTIVEOFFICE_NAME = "executiveOfficeName";
    public static final String FIELD_PERSON_ID_ACTUAL = "personIdActual";
    public static final String FIELD_MISSION_ID = "missionIdActual";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int executiveOfficeIdActual;
    private int executiveOfficeName; // 0 là điều hành, 1 là cổ đông
    //@OneToOne
    private int personIdActual;
    //@OneToOne
    private int missionIdActual;

    public ExecutiveOffice() {
    }

    public ExecutiveOffice(int executiveOfficeIdActual, int executiveOfficeName,
            int personIdActual, int missionIdActual) {
        this.executiveOfficeIdActual = executiveOfficeIdActual;
        this.executiveOfficeName = executiveOfficeName;
        this.personIdActual = personIdActual;
        this.missionIdActual = missionIdActual;
    }

    public int getExecutiveOfficeIdActual() {
        return executiveOfficeIdActual;
    }

    public void setExecutiveOfficeIdActual(int executiveOfficeIdActual) {
        this.executiveOfficeIdActual = executiveOfficeIdActual;
    }

    public int getExecutiveOfficeName() {
        return executiveOfficeName;
    }

    public void setExecutiveOfficeName(int executiveOfficeName) {
        this.executiveOfficeName = executiveOfficeName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMissionIdActual() {
        return missionIdActual;
    }

    public void setMissionIdActual(int missionIdActual) {
        this.missionIdActual = missionIdActual;
    }

    public int getPersonIdActual() {
        return personIdActual;
    }

    public void setPersonIdActual(int personIdActual) {
        this.personIdActual = personIdActual;
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
        return "Mở rộng Enterprise SB12";
    }

    @Override
    public IAccessData getAccessDataOfEntity() {
        return new ExecutiveBN();
    }

    @Override
    public String getFieldNameObjectId() {
        return FIELD_EXECUTIVEOFFICE_ID_ACTUAL;
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

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.pilot.sb31.deparment.extW41.entity;

import java.lang.reflect.Field;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.openide.util.lookup.ServiceProvider;
import vn.com.hkt.pilot.identity.access.api.IAccessData;
import vn.com.hkt.pilot.identity.entitiy.api.IEntity;
import vn.com.hkt.pilot.sb31.Installer;
import vn.com.hkt.pilot.sb31.department.extW41.dao.InformationBN;

/**
 *
 * @author khangpn
 */
@ServiceProvider(service = IEntity.class)
@Entity
public class Information implements IEntity {

    public static final String FIELD_ID = "id";
    public static final String FIELD_INFORMATION_NAME = "informationName";
    public static final String FIELD_DEPARTMENT_ID = "departmentIdActual";
    public static final String FIELD_INFORMATION_DESCRIPTION = "informationDescription";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int departmentIdActual;
    private String informationName;
    private String informationDescription;

    public Information() {
    }

    public Information(int departmentIdActual, String informationName, String informationDescription) {
        this.departmentIdActual = departmentIdActual;
        this.informationName = informationName;
        this.informationDescription = informationDescription;
    }

    public int getDepartmentIdActual() {
        return departmentIdActual;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDepartmentIdActual(int departmentIdActual) {
        this.departmentIdActual = departmentIdActual;
    }

    public String getInformationDescription() {
        return informationDescription;
    }

    public void setInformationDescription(String informationDescription) {
        this.informationDescription = informationDescription;
    }

    public String getInformationName() {
        return informationName;
    }

    public void setInformationName(String informationName) {
        this.informationName = informationName;
    }

    @Override
    public String toString() {
        return informationName;
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
        return "Miêu tả thông tin bổ xung";
    }

    @Override
    public IAccessData getAccessDataOfEntity() {
        return new InformationBN();
    }

    @Override
    public String getFieldNameObjectId() {
        return FIELD_DEPARTMENT_ID;
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

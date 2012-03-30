/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.pilot.sb31.deparment.extW41.entity;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.openide.util.lookup.ServiceProvider;
import vn.com.hkt.pilot.identity.access.api.IAccessData;
import vn.com.hkt.pilot.identity.entitiy.api.IEntity;
import vn.com.hkt.pilot.sb31.Installer;
import vn.com.hkt.pilot.sb31.department.extW41.dao.DepartmentExtSB31W43_DAO;

/**
 *
 * @author VietAnh
 */
@ServiceProvider(service = IEntity.class)
@Entity
public class DepartmentExtSB31_W43 implements IEntity {

    public static final String FIELD_ID = "id";
    public static final String FIELD_DEPARTMENT_ID = "departmentIdActual";
    public static final String FIELD_PERSON_ID = "idPersonActual";
    public static final String FIELD_MISSION_ID = "idMissionIdActual";
    public static final String FIELD_PERCENT = "percent";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int departmentIdActual;
    private List<Integer> idPersonActual;
    private List<Integer> idMissionIdActual;
    private List<Float> percent;

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
        return "Thành Viên Dự Án";
    }

    @Override
    public IAccessData getAccessDataOfEntity() {
        return new DepartmentExtSB31W43_DAO();
    }

    @Override
    public String getFieldNameObjectId() {
        return FIELD_DEPARTMENT_ID;
    }

    @Override
    public int getId() {
        return id;
    }

    public List<Integer> getIdMissionIdActual() {
        return idMissionIdActual;
    }

    public void setIdMissionIdActual(List<Integer> idMissionIdActual) {
        this.idMissionIdActual = idMissionIdActual;
    }

    public List<Integer> getIdPersonActual() {
        return idPersonActual;
    }

    public void setIdPersonActual(List<Integer> idPersonActual) {
        this.idPersonActual = idPersonActual;
    }

    public DepartmentExtSB31_W43() {
    }


    public int getDepartmentIdActual() {
        return departmentIdActual;
    }

    public void setDepartmentIdActual(int departmentIdActual) {
        this.departmentIdActual = departmentIdActual;
    }

    public List<Float> getPercent() {
        return percent;
    }

    public void setPercent(List<Float> percent) {
        this.percent = percent;
    }


    @Override
    public String toString() {
        return "Thành Viên Dự Án";
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

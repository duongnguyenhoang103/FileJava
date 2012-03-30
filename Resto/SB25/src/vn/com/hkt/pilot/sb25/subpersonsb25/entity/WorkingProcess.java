/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.pilot.sb25.subpersonsb25.entity;

import java.lang.reflect.Field;
import java.util.Calendar;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.openide.util.lookup.ServiceProvider;
import vn.com.hkt.pilot.identity.access.api.IAccessData;
import vn.com.hkt.pilot.identity.entitiy.api.IEntity;
import vn.com.hkt.pilot.sb25.Installer;
import vn.com.hkt.pilot.sb25.dao.WorkingProessBN;

/**
 *
 * @author khangpn
 */
@Entity
@ServiceProvider(service = IEntity.class)
public class WorkingProcess implements IEntity { // Quá trình làm việc

    public static final String FIELD_WORKINGPROCCESS_ID = "workingProccessId";
    public static final String FIELD_DATE_FROM = "dateFrom";
    public static final String FIELD_DATE_TO = "dateTo";
    public static final String FIELD_MISSION_ID_ACTUAL = "missionIdActual";
    public static final String FIELD_ENTERPRISE_ID_ACTUAL = "enterpriseIdActual";
    public static final String FIELD_DESCIPTION = "desciption";
    public static final String FIELD_REASON = "reason";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String workingProccessId;
    @Temporal(TemporalType.DATE)
    private Calendar dateFrom;
    @Temporal(TemporalType.DATE)
    private Calendar dateTo;
    private int missionIdActual;
    private int enterpriseIdActual;
    private String desciption; // Mô tả công việc
    private String reason; //Lý do

    public WorkingProcess() {
    }

    public WorkingProcess(String workingProccessId, Calendar dateFrom,
            Calendar dateTo, int missionIdActual, int enterpriseIdActual,
            String desciption, String reason) {
        this.workingProccessId = workingProccessId;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.missionIdActual = missionIdActual;
        this.enterpriseIdActual = enterpriseIdActual;
        this.desciption = desciption;
        this.reason = reason;
    }

    public Calendar getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Calendar dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Calendar getDateTo() {
        return dateTo;
    }

    public void setDateTo(Calendar dateTo) {
        this.dateTo = dateTo;
    }

    public String getDesciption() {
        return desciption;
    }

    public void setDesciption(String desciption) {
        this.desciption = desciption;
    }

    public int getEnterpriseIdActual() {
        return enterpriseIdActual;
    }

    public void setEnterpriseIdActual(int enterpriseIdActual) {
        this.enterpriseIdActual = enterpriseIdActual;
    }

    public int getMissionIdActual() {
        return missionIdActual;
    }

    public void setMissionIdActual(int missionIdActual) {
        this.missionIdActual = missionIdActual;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getWorkingProccessId() {
        return workingProccessId;
    }

    public void setWorkingProccessId(String workingProccessId) {
        this.workingProccessId = workingProccessId;
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
        return new WorkingProessBN();
    }

    @Override
    public String getFieldNameObjectId() {
        return FIELD_WORKINGPROCCESS_ID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

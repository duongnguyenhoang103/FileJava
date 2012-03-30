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
import vn.com.hkt.pilot.dialog.dao.MissionBN;
import vn.com.hkt.pilot.identity.access.api.IAccessData;
import vn.com.hkt.pilot.identity.entitiy.api.IEntity;

/**
 *
 * @author khanguct
 */
@ServiceProvider(service = IEntity.class)
@Entity
public class Mission implements IEntity {

    public static final String FIELD_ID = "id";
    public static final String FIELD_MISSION_ID = "missionID";
    public static final String FIELD_MISSION_NAME = "missionName";
    public static final String FIELD_LEVER = "lever";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String missionID; // Mã chức vụ
    private String missionName; // Tên chức vụ
    private int lever;

    public int getLever() {
        return lever;
    }

    public void setLever(int lever) {
        this.lever = lever;
    }

    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Mission() {
    }

    public Mission(String missionID, String missionName, int lever) {
        this.missionID = missionID;
        this.missionName = missionName;
        this.lever = lever;
    }

    public String getMissionID() {
        return missionID;
    }

    public void setMissionID(String missionID) {
        this.missionID = missionID;
    }

    public String getMissionName() {
        return missionName;
    }

    public void setMissionName(String missionName) {
        this.missionName = missionName;
    }

    @Override
    public String toString() {
        return missionName;
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
        return "Miêu tả thông tin cơ bản về chức vụ";
    }

    @Override
    public IAccessData getAccessDataOfEntity() {
        return new MissionBN();
    }

    @Override
    public String getFieldNameObjectId() {
        return missionID;
    }

    @Override
    public String getDataRealyOfField(String fieldName, String data) {
        return data;
    }

    @Override
    public String getDescriptionOfField(String fieldName) {
        if (fieldName.equals(FIELD_MISSION_ID)) {
            return "Mã chức vụ";
        } else if (fieldName.equals(FIELD_MISSION_NAME)) {
            return "Tên chức vụ";
        } else if (fieldName.equals(FIELD_LEVER)) {
            return "Phân cấp";
        }
        return null;
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.pilot.sb23.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.openide.util.lookup.ServiceProvider;
import vn.com.hkt.pilot.identity.access.api.IAccessData;
import vn.com.hkt.pilot.identity.entitiy.api.IEntity;
import vn.com.hkt.pilot.sb23.Installer;
import vn.com.hkt.pilot.subpersonsb23.dao.RewardBN;

/**
 *
 * @author khangpn
 */
@ServiceProvider(service = IEntity.class)
@Entity
public class Reward implements IEntity {

    public static final String FIELD_REWARD_ID = "rewardId";
    public static final String FIELD_REWARD_NAME = "rewardName";
    public static final String FIELD_PERSON_ID_ACTUAL = "personIdActual";
    public static final String FIELD_VALUE = "value";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String rewardId; // Mã thưởng
    private String rewardName; // Tên thưởng
    private int personIdActual; // Mã nhân viên
    private int value; // Giá trị (%)

    public Reward() {
    }

    public Reward(String rewardId, String rewardName, int personIdActual, int value) {
        this.rewardId = rewardId;
        this.rewardName = rewardName;
        this.personIdActual = personIdActual;
        this.value = value;
    }

    public int getPersonIdActual() {
        return personIdActual;
    }

    public void setPersonIdActual(int personIdActual) {
        this.personIdActual = personIdActual;
    }

    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRewardId() {
        return rewardId;
    }

    public void setRewardId(String rewardId) {
        this.rewardId = rewardId;
    }

    public String getRewardName() {
        return rewardName;
    }

    public void setRewardName(String rewardName) {
        this.rewardName = rewardName;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return rewardId;
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
        return "Module mở rộng SB23";
    }

    @Override
    public IAccessData getAccessDataOfEntity() {
        return new RewardBN();
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

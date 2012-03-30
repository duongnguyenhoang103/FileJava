/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.pilot.promotion.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.openide.util.lookup.ServiceProvider;
import vn.com.hkt.pilot.identity.access.api.IAccessData;
import vn.com.hkt.pilot.identity.entitiy.api.IEntity;
import vn.com.hkt.pilot.promotion.Installer;
import vn.com.hkt.pilot.promotion.dao.SaleOffOptionBN;

/**
 *
 * @author HKT01
 */
@Entity
@ServiceProvider(service = IEntity.class)
public class SaleOffOption implements IEntity {

    public static final String FIELD_SALEOFFOPTION_ID = "saleOffOptionId";
    public static final String FIELD_WEEKLY_ID = "weeklyID";
    public static final String FIELD_DAILY_ID = "dailyID";
    public static final String FIELD_MONTHLY_ID = "monthlyID";
    public static final String FIELD_YEARLY_ID = "yearlyID";
    public static final String FIELD_IS_DAILY = "isDaily";
    public static final String FIELD_IS_WEEKLY = "isWeekly";
    public static final String FIELD_IS_MONTHLY = "isMonthly";
    public static final String FIELD_IS_YEARLY = "isYearly";
    /******************** Properties *********************/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String saleOffOptionId;
    private int weeklyID;
    private int dailyID;
    private int monthlyID;
    private int yearlyID;
    private boolean isDaily;
    private boolean isWeekly;
    private boolean isMonthly;
    private boolean isYearly;

    public SaleOffOption(String saleOffOptionID, int weeklyID, int dailyID,
            int monthlyID, int yearlyID, boolean isDaily, boolean isWeekly,
            boolean isMonthly, boolean isYearly) {
        this.saleOffOptionId = saleOffOptionID;
        this.weeklyID = weeklyID;
        this.dailyID = dailyID;
        this.monthlyID = monthlyID;
        this.yearlyID = yearlyID;
        this.isDaily = isDaily;
        this.isWeekly = isWeekly;
        this.isMonthly = isMonthly;
        this.isYearly = isYearly;
    }

    /******************** Constructors *********************/
    public SaleOffOption() {
    }

    /******************** Getters and Setters *********************/
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDailyID() {
        return dailyID;
    }

    public void setDailyID(int dailyID) {
        this.dailyID = dailyID;
    }

    public int getMonthlyID() {
        return monthlyID;
    }

    public void setMonthlyID(int monthlyID) {
        this.monthlyID = monthlyID;
    }

    public String getSaleOffOptionID() {
        return saleOffOptionId;
    }

    public void setSaleOffOptionID(String saleOffOptionID) {
        this.saleOffOptionId = saleOffOptionID;
    }

    public int getWeeklyID() {
        return weeklyID;
    }

    public void setWeeklyID(int weeklyID) {
        this.weeklyID = weeklyID;
    }

    public int getYearlyID() {
        return yearlyID;
    }

    public void setYearlyID(int yearlyID) {
        this.yearlyID = yearlyID;
    }

    public boolean isIsDaily() {
        return isDaily;
    }

    public void setIsDaily(boolean isDaily) {
        this.isDaily = isDaily;
    }

    public boolean isIsMonthly() {
        return isMonthly;
    }

    public void setIsMonthly(boolean isMonthly) {
        this.isMonthly = isMonthly;
    }

    public boolean isIsWeekly() {
        return isWeekly;
    }

    public void setIsWeekly(boolean isWeekly) {
        this.isWeekly = isWeekly;
    }

    public boolean isIsYearly() {
        return isYearly;
    }

    public void setIsYearly(boolean isYearly) {
        this.isYearly = isYearly;
    }

    /******************** Override toString method *********************/
    @Override
    public String toString() {
        return saleOffOptionId;
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
        return "Module mở rộng SB45";
    }

    @Override
    public IAccessData getAccessDataOfEntity() {
        return new SaleOffOptionBN();
    }

    @Override
    public String getFieldNameObjectId() {
        return FIELD_SALEOFFOPTION_ID;
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

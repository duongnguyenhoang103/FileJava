/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.pilot.calendaroption.entity;

/**
 *
 * @author khangpn
 */
public class SaleOffOptionLookup {
    
    private int saleOffOptionID;
    private int weeklyID;
    private int dailyID;
    private int monthlyID;
    private int yearlyID;
    private boolean isDaily;
    private boolean isWeekly;
    private boolean isMonthly;
    private boolean isYearly;

    public SaleOffOptionLookup() {
    }

    public SaleOffOptionLookup(int saleOffOptionID, int weeklyID, int dailyID,
            int monthlyID, int yearlyID, boolean isDaily, boolean isWeekly, 
            boolean isMonthly, boolean isYearly) {
        this.saleOffOptionID = saleOffOptionID;
        this.weeklyID = weeklyID;
        this.dailyID = dailyID;
        this.monthlyID = monthlyID;
        this.yearlyID = yearlyID;
        this.isDaily = isDaily;
        this.isWeekly = isWeekly;
        this.isMonthly = isMonthly;
        this.isYearly = isYearly;
    }

    public int getDailyID() {
        return dailyID;
    }

    public void setDailyID(int dailyID) {
        this.dailyID = dailyID;
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

    public int getMonthlyID() {
        return monthlyID;
    }

    public void setMonthlyID(int monthlyID) {
        this.monthlyID = monthlyID;
    }

    public int getSaleOffOptionID() {
        return saleOffOptionID;
    }

    public void setSaleOffOptionID(int saleOffOptionID) {
        this.saleOffOptionID = saleOffOptionID;
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

    
    
}

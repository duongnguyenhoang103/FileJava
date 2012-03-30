/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.pilot.promotion.calculation;

import java.util.Calendar;
import java.util.List;
import org.openide.util.Lookup;
import vn.com.hkt.pilot.calendaroption.dao.api.IDailyBN;
import vn.com.hkt.pilot.calendaroption.dao.api.IMonthlyBN;
import vn.com.hkt.pilot.calendaroption.dao.api.IWeeklyBN;
import vn.com.hkt.pilot.calendaroption.dao.api.IYearlyBN;
import vn.com.hkt.pilot.calendaroption.entity.Daily;
import vn.com.hkt.pilot.calendaroption.entity.Monthly;
import vn.com.hkt.pilot.calendaroption.entity.Weekly;
import vn.com.hkt.pilot.calendaroption.entity.Yearly;
import vn.com.hkt.pilot.datetime.utils.DateTimeConverter;
import vn.com.hkt.pilot.datetime.utils.DateTimeUtils;
import vn.com.hkt.pilot.promotion.dao.SaleOffOptionBN;
import vn.com.hkt.pilot.promotion.entity.Promotion;
import vn.com.hkt.pilot.promotion.entity.SaleOff;
import vn.com.hkt.pilot.promotion.entity.SaleOffOption;
import vn.com.hkt.pilot.promotion.entity.SaleOffProduct;

/**
 *
 * @author khangpn
 */
public class PromotionCalculate {

    private DateTimeConverter dateTimeConverter = new DateTimeConverter();
    private DateTimeUtils dateTimeUtils = new DateTimeUtils();
    private IDailyBN dailyBN;
    private IWeeklyBN weeklyBN;
    private IMonthlyBN monthlyBN;
    private IYearlyBN yearlyBN;

    public PromotionCalculate() {
        this.dailyBN = Lookup.getDefault().lookup(IDailyBN.class);
        this.weeklyBN = Lookup.getDefault().lookup(IWeeklyBN.class);
        this.monthlyBN = Lookup.getDefault().lookup(IMonthlyBN.class);
        this.yearlyBN = Lookup.getDefault().lookup(IYearlyBN.class);
    }

    /**
     * Kiem tra 1 chuong trinh khuyen mai con thoi gian ap dung khong
     * @param promotion
     * @param calendar
     * @return 
     */
    public boolean checkPromotionTimeLimit(Promotion promotion, Calendar calendar) {
        Calendar dateStart = promotion.getDateSart();
        Calendar dateFinish = promotion.getDateFinish();
        Calendar timeStart = promotion.getHourStart();
        Calendar timeFinish = promotion.getHourFinish();
        if (dateTimeConverter.compareBetweenDate(calendar, dateStart, dateFinish)) {
            if(dateTimeConverter.compareBetweenTime(timeStart, timeFinish, calendar)==1){
                return true;
            }
        }
        return false;
    }

    /**
     * Kiem tra khuyen mai theo %,gia tien co duoc ap dung trong ngay nao do khong
     * @param promotion
     * @param current
     * @return 
     */
    public boolean checkPromotionLimitOption(Promotion promotion, Calendar startDate, Calendar current){
        int saleOffOptionId = promotion.getSaleOffOptionIdActual();
        return checkPromotionTimeLimit(saleOffOptionId, startDate,current);
    }
    
//    /**
//     * Kiem tra khuyen mai theo SP co duoc ap dung trong ngay nao do khong
//     * @param saleOffProduct
//     * @param startDate
//     * @param current
//     * @return 
//     */
//    public boolean checkSaleOffProductTimeLimit(SaleOffProduct saleOffProduct, Calendar startDate, Calendar current){
//        int saleOffOptionId = saleOffProduct.getSaleOffOptionIdActual();
//        return checkPromotionTimeLimit(saleOffOptionId, startDate,current);
//    }
//    
    /**
     * Ktra 1 chương trình KM bất kỳ
     * @param saleOffOptionId
     * @param startDate
     * @param current
     * @return 
     */
    protected boolean checkPromotionTimeLimit(int saleOffOptionId, Calendar startDate, Calendar current) {
        //int saleOffOptionId = saleOff.getSaleOffOptionIdActual();
        if (saleOffOptionId == 0) {
            return true; // Neu khong ton tai tuy chon nay thi tra ve true----------
        } else { // Kiem tra cac tuy chon tiep theo ---------
            SaleOffOptionBN saleOffOptionBN = new SaleOffOptionBN();
            SaleOffOption saleOffOption = saleOffOptionBN.getById(saleOffOptionId);
            if (saleOffOption != null) {
                if (saleOffOption.isIsDaily()) { // Neu Lap lai hang ngay-----------------------------------
                    return checkDailyLimit(saleOffOption, startDate, current);
                } else if (saleOffOption.isIsWeekly()) {// Neu lap theo tuan
                    return checkWeeklyLimit(saleOffOption, startDate, current);
                } else if (saleOffOption.isIsMonthly()) { // Neu lap theo thang
                    return checkMonthlyLimit(saleOffOption, startDate, current);
                } else if (saleOffOption.isIsYearly()) {// Neu lap theo nam
                    return checkYearlyLimit(saleOffOption, startDate, current);
                }

            } else {// Khong ton tai tuy chon trong co so du lieu,return true-----------
                return false;
            }
        }
        return false;
    }

    /**
     * Kiem tra 1 tuy chon ap dung theo quy luat hang ngay
     * Tra ve true neu dung quy luat
     * Tra ve false neu sai quy luat
     * @param saleOff
     * @param saleOffOption
     * @param startDate
     * @param current
     * @return 
     */
    protected boolean checkDailyLimit(SaleOffOption saleOffOption, Calendar startDate, Calendar current) {
        int dailyId = saleOffOption.getDailyID();
        if (dailyId != 0) {
            Daily daily = dailyBN.getById(dailyId);
            if (daily != null) { // Neu tuy chon hang ngay da dc chon------------------------------
                if (daily.isEveryWeekday()) { // Tra ve true neu tuy chon la hang ngay-------------
                    return true;
                } else { // Nguoc lai neu tuy chon theo boi cua so ngay----------------------------
                    int num = daily.getStepNumber();
                    int days = (int) dateTimeConverter.countDayBetweenTwoDate(startDate, current);
                    if (days % num == 0) { // Neu ngay tryen vao dung quy luat ap dung
                        return true;
                    } else { // Ngay tryen vao ko dung quy luat ap dung
                        return false;
                    }
                }
            } else {
                return false;
            }
        }
        return false;
    }

    /**
     * Kiem tra ngay nao do co thuoc quy luat ap dung theo tuan hay khong
     * @param saleOff
     * @param saleOffOption
     * @param startDate
     * @param current
     * @return 
     */
    protected boolean checkWeeklyLimit(SaleOffOption saleOffOption, Calendar startDate, Calendar current) {
        int weekId = saleOffOption.getWeeklyID();
        if (weekId != 0) {
            Weekly weekly = weeklyBN.getById(weekId);
            if (weekly != null) {
                int weeks = dateTimeConverter.countWeeksBetweenTwoMonths(startDate, current);
                int step = weekly.getStepNumber();
                /**
                 * Neu so tuan hien tai la tuan dau hoac so tuan hien tai-1
                 * chia het cho step thi tien hanh kiem tra ngay trong tuan
                 */
                if ((weeks == 1) || ((weeks - 1) % step == 0)) {
                    List<Integer> dayOfWeek = weekly.getDayOfWeek();
                    int day = current.get(Calendar.DAY_OF_WEEK);
                    for (int i = 0; i < dayOfWeek.size(); i++) {
                        if (day == dayOfWeek.get(i)) {
                            return true;
                        }
                    }
                }
            } else {
                return false;
            }
        }
        return false;
    }

    public boolean checkMonthlyLimit(SaleOffOption saleOffOption, Calendar startDate, Calendar current) {
        int monthlyId = saleOffOption.getMonthlyID();
        if (monthlyId != 0) {
            Monthly monthly = monthlyBN.getById(monthlyId);
            if (monthly != null) {
                int level = monthly.getDayLevel();
                int day = monthly.getDay();
                int step = monthly.getStepOfMonth();
                List<Integer> dayOfweek = monthly.getDayofWeek();
                int months = dateTimeConverter.countMonthsBetweenTwoMonths(startDate, current);
                if (day != 0) { // Neu la tuy chon theo ngay
                    int calendarDay = current.get(Calendar.DAY_OF_MONTH);
                    if ((months % step == 0) && (day == calendarDay)) {
                        return true;
                    }
                    return false;
                } else {
                    int i;
                    for (i = 0; i < dayOfweek.size(); i++) {
                        boolean value = dateTimeConverter.isOrder(current, dayOfweek.get(i), level);
                        if ((months % step == 0) && (value == true)) {
                            return true;
                        }
                    }

                    return false;
                }
            }
        }
        return false;
    }

    /**
     * Kiem tra tinh hop le cua nam
     * @param saleOffOption
     * @param startDate
     * @param current
     * @return 
     */
    public boolean checkYearlyLimit(SaleOffOption saleOffOption, Calendar startDate, Calendar current) {
        int yearlyId = saleOffOption.getYearlyID();
        if (yearlyId != 0) {
            Yearly yearly = yearlyBN.getById(yearlyId);
            if (yearly != null) {
                int currentDay = current.get(Calendar.DAY_OF_MONTH);
                int currentMonth = current.get(Calendar.MONTH) + 1;
                int day = yearly.getDayOfMonth();
                int month = yearly.getMonthOfYear();
                if (day != 0) { // Neu tuy chon theo ngay
                    if ((month == currentMonth) && (day == currentDay)) {
                        return true;
                    }
                    return false;
                } else { // Neu tuy chon theo quy luat phan cap
                    int levelWeek = yearly.getDayLevel();
                    List<Integer> levelDay = yearly.getDayOfWeek();
                    int currenLevelWeek = dateTimeConverter.getCurrentWeek(current);
                    if ((currentMonth == month) && (currenLevelWeek == levelWeek)) {
                        int i;
                        for (i = 0; i < levelDay.size(); i++) {
                            if (currentDay == levelDay.get(i)) {
                                return true;
                            }
                        }

                    }
                }
            }
        }
        return false;
    }
}

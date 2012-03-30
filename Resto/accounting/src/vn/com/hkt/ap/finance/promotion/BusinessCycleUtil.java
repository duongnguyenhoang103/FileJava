/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.ap.finance.promotion;

import java.util.Calendar;
import java.util.List;
import vn.com.hkt.pilot.datetime.utils.DateTimeConverter;
import vn.com.hkt.pilot.entities.Operation;

/**
 * tiện ích tính kỳ kinh doanh
 * @author Admin
 */
public class BusinessCycleUtil {

    /**
     *  Tính giá trị thu chi trong kỳ theo tháng
     */
    public static final String TIME_PERIOD_MONTH = "Tháng";
    /**
     *  Tính giá trị thu chi trong kỳ theo năm
     */
    public static final String TIME_PERIOD_YEAR = "Năm";
    private Calendar dateLanmark = Calendar.getInstance();
    private String timePeriodType;
    private int number;
    private DateTimeConverter dtc;   
    /**
     * Khởi tạo ban đầu
     * @param dateLanMark: thời điểm tính từ ngày nào(ngày trong tháng, tháng trong năm, năm tính)
     * @param timePeriodType: kiểu khoảng cách thời gian trong kỳ : Tháng, Năm
     * @param number : khoảng cách của kỳ kinh doanh
     */
    public BusinessCycleUtil(Calendar dateLanMark, String timePeriodType, int number) {
        this.timePeriodType = timePeriodType;
        this.number = number;
        this.dateLanmark = dateLanMark;
        dtc = new DateTimeConverter();
    }
    /**
     * kiểm tra ngày giữa khoảng thời gian xác định
     * @param date ngày muốn kiểm tra
     * @param date1 mốc thời gian nhỏ
     * @param date2 mốc thời gian lớn
     * @return true false
     */
    public boolean dateBetween(String date, String date1, String date2) {
        if (dtc.compareDate(date, date1) >= 0 && dtc.compareDate(date, date2) <= 0) {
            return true;
        }
        return false;
    }
    
    
    /**
     * kiểm tra ngày giữa khoảng thời gian xác định
     * @param date ngày muốn kiểm tra
     * @param date1 mốc thời gian nhỏ
     * @param date2 mốc thời gian lớn
     * @return true false
     */
    public boolean dateBetween(Calendar date, Calendar date1, Calendar date2) {
        if (dtc.compareTwoDate(date, date1) >= 0 && dtc.compareTwoDate(date, date2) <= 0) {
            return true;
        }
        return false;
    }
    
    /**
     * trả về ngày đầu kỳ tiếp theo
     * @param dateStart : Ngày đầu kỳ xác định
     * @return : ngày đầu kỳ tiếp theo
     */
    public Calendar increaseCycle(Calendar dateStart) {
        if (timePeriodType.equals(TIME_PERIOD_MONTH)) {
            return dtc.getMonthSpace(dateStart, number);
        } else if (timePeriodType.equals(TIME_PERIOD_YEAR)) {
            return dtc.getYearSpace(dateStart, number);
        } else {
            return dateStart;
        }
    }
    /**
     * trả về ngày đầu kỳ trước đó
     * @param dateStart : Ngày đầu kỳ xác định
     * @return : ngày đầu kỳ sau đó
     */
    public Calendar decreaseCycle(Calendar dateStart) {
        if (timePeriodType.equals(TIME_PERIOD_MONTH)) {
            return dtc.getMonthSpace(dateStart, -number);
        }
        if (timePeriodType.equals(TIME_PERIOD_YEAR)) {
            return dtc.getYearSpace(dateStart, -number);
        }
        return dateStart;
    }
    /**
     * trả về ngày đầu kỳ của ngày thuộc kỳ đó
     * @param dateStart : ngày muốn xác định xem nó thuộc kỳ nào
     * @return : ngày đầu kỳ 
     */
    public Calendar dateStartCycle(Calendar date) {
        Calendar date1 = dateLanmark;
        Calendar date2 = null;
        int compare = dtc.compareTwoDate(date, dateLanmark);
        if (compare == 0 || compare == 1) {
            date2 = increaseCycle(date1);
            while (dtc.compareTwoDate(date, date2) >= 0) {
                date1 = date2;
                date2 = increaseCycle(date2);
            }
            return date1;
        } else if (compare == -1) {
            date2 = decreaseCycle(date1);
            while (dtc.compareTwoDate(date, date2) < 0) {
                date1 = date2;
                date2 = decreaseCycle(date2);
            }
            return date2;
        } else {
            return null;
        }
    }
    /**
     * Ngày cuối kỳ
     * @param dateStartCycle ngày đầu kỳ
     * @return ngày cuối kỳ
     */    
    public Calendar dateFinishCycle(Calendar dateStartCycle) {
        return dtc.getDateSpace(increaseCycle(dateStartCycle), -1);
    }
/**
     * sắp xếp nghiệp vụ theo thời gian những nghiệp vụ không có ngày xác định được đưa xuống cuối cùng
     * @param operations: danh sách nghiệp vụ truyền vào
     * @return:danh sách đã sắp xếp
     */
    public List<Operation> sort(List<Operation> operations) {
        for (int i = 0; i < operations.size(); i++) {
            if (dtc.dateIsNull(operations.get(i).getDateTime())) {
                Operation operation = operations.get(i);
                for (int j = i + 1; j < operations.size(); j++) {
                    operations.set(j - 1, operations.get(j));
                }
                operations.set(operations.size() - 1, operation);
            }
        }
        for (int i = 0; i < operations.size(); i++) {
            for (int j = i + 1; j < operations.size(); j++) {
                int kq = dtc.compareTwoDate(operations.get(i).getDateTime(),
                        operations.get(j).getDateTime());
                if (kq == -2) {
                    return operations;
                } else if (kq == 2) {
                    break;
                } else if (kq == 1) {
                    Operation operation = operations.get(i);
                    operations.set(i, operations.get(j));
                    operations.set(j, operation);
                }
            }
        }
        return operations;
    }
}

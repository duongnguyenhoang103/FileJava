/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.ap.finance.api;

import java.util.Calendar;
import java.util.List;
import vn.com.hkt.ap.data.BusinessCycle;

/**
 *Tính toán giá trị đầu kì, cuối kì, phát sinh trong kì của mỗi phòng ban
 * @author Binle
 */
public interface IPrimeValueCaculator {    
    /**
     *  Truyền vào Id doanh nghie va id phòng ban muốn tính giá trị thu chi
     */
    public void setDepartmentId(int enterpriseID,int departmentId);
    /**
     * Truyền vào khoảng thời gian của mỗi kì
     * dateLandmarkCycle: cột mốc tính chu kỳ kinh doanh( tính lùi và tiến)
     * number: số lương đơn vị thời gian
     * timePeriodType: kiểu thời gian muốn tính là:
     *  TIME_PERIOD_MONTH -> tính theo tháng
     *  TIME_PERIOD_YEAR -> tính theo năm    
     */
    public void setTimePeriod(Calendar dateLandmarkCycle, int number,String timePeriodType);
   
    /**
     * Thực hiện việc tính toán giá trị thu chi theo phòng ban và thời gian tính truyền vào.
     * @return
     * Trả về mảng các phần tử được tính toán ứng với bảng thu chi 
     */
    public List<BusinessCycle> caculator();    
}

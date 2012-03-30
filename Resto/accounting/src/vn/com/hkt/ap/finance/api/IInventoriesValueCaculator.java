/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.ap.finance.api;

import java.util.Calendar;
import java.util.List;
import vn.com.hkt.ap.data.ProductStockCycle;

/**
 *Tính toán giá trị tồn kho,tăng giảm theo các kì
 * @author Binle
 */
public interface IInventoriesValueCaculator {

    /**
     *  Tính giá trị thu chi trong kỳ theo giá trị thực
     */
    public static final String CACULATOR_TYPE_REAL = "Theo giá trị thực";
    /**
     *  Tính giá trị thu chi trong kỳ theo giá trị trung bình
     */
    public static final String CACULATOR_TYPE_AVERAGE = "Theo giá trị trung bình";

    /**
     * Truyền vào khoảng thời gian của mỗi kì
     * number: số lương đơn vị thời gian
     * timePeriodType: kiểu thời gian muốn tính là:
     *  TIME_PERIOD_MONTH -> tính theo tháng
     *  TIME_PERIOD_YEAR -> tính theo năm    
     */
    public void setTimePeriod(Calendar dateLandmarkCycle, int number, String timePeriodType);

    /**
     *  Truyền vào mã sản phẩm muốn tính
     */
    public void setProductId(int enterpriseId, int departmentId, int productId);

    /**
     * Thực hiện việc tính toán giá trị thu chi theo phòng ban và thời gian tính truyền vào.
     * @return
     * Trả về mảng các phần tử được tính toán ứng với bảng thu chi 
     */
    public List<ProductStockCycle> caculator();
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.ap.data;

import java.util.Calendar;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *  Kỳ lưu trữ sản phẩm
 * @author Admin
 */
@Entity
public class ProductStockCycle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String productId;// mã sản phẩm
    @Temporal(TemporalType.DATE)
    private Calendar startTime;// thời gian bắt đầu
    @Temporal(TemporalType.DATE)
    private Calendar finishTime;// thời gian kết thúc
    private long firstQuantity;// số lượng đầu kỳ
    private long increaseQuantity;// số lượng tăng trong kỳ
    private long decreaseQuantity;// số lượng giảm trong kỳ
    private long lastQuantity;// số lượng cuối kỳ
    private float firstValueAverage;// giá trị đầu kỳ tính theo trung bình
    private float firstValueReal;// giá trị thực đầu kỳ
    private float increaseValueReal;// giá trị thực tăng trong kỳ
    private float decreaseValueReal;// giá trị thực giảm trong kỳ
    private float lastValueAverage;// giá gị cuối kỳ tính thoe giá trung bình
    private float lastValueReal;// giá trị thực cuối kỳ
    private float priceAverageFirstCycle;// giá trung bình đầu kỳ(= giá trung bình kỳ trước)
    private float priceAverageCycle;// giá trung bình trong kỳ
    private List<Integer> listOperationId;// các id nghiệp vụ phát sinh trong kì

    public long getDecreaseQuantity() {
        return decreaseQuantity;
    }

    public void setDecreaseQuantity(long decreaseQuantity) {
        this.decreaseQuantity = decreaseQuantity;
    }

    public float getDecreaseValueReal() {
        return decreaseValueReal;
    }

    public void setDecreaseValueReal(float decreaseValueReal) {
        this.decreaseValueReal = decreaseValueReal;
    }
   
    public long getFirstQuantity() {
        return firstQuantity;
    }

    public void setFirstQuantity(long firstQuantity) {
        this.firstQuantity = firstQuantity;
    }

    public float getFirstValueAverage() {
        return firstValueAverage;
    }

    public void setFirstValueAverage(float firstValueAverage) {
        this.firstValueAverage = firstValueAverage;
    }

    public float getFirstValueReal() {
        return firstValueReal;
    }

    public void setFirstValueReal(float firstValueReal) {
        this.firstValueReal = firstValueReal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getIncreaseQuantity() {
        return increaseQuantity;
    }

    public void setIncreaseQuantity(long increaseQuantity) {
        this.increaseQuantity = increaseQuantity;
    }

    public float getIncreaseValueReal() {
        return increaseValueReal;
    }

    public void setIncreaseValueReal(float increaseValueReal) {
        this.increaseValueReal = increaseValueReal;
    }

    public long getLastQuantity() {
        return lastQuantity;
    }

    public void setLastQuantity(long lastQuantity) {
        this.lastQuantity = lastQuantity;
    }

    public float getLastValueAverage() {
        return lastValueAverage;
    }

    public void setLastValueAverage(float lastValueAverage) {
        this.lastValueAverage = lastValueAverage;
    }

    public float getLastValueReal() {
        return lastValueReal;
    }

    public void setLastValueReal(float lastValueReal) {
        this.lastValueReal = lastValueReal;
    }

    public List<Integer> getListOperationId() {
        return listOperationId;
    }

    public void setListOperationId(List<Integer> listOperationId) {
        this.listOperationId = listOperationId;
    }

    public float getPriceAverageCycle() {
        return priceAverageCycle;
    }

    public void setPriceAverageCycle(float priceAverageCycle) {
        this.priceAverageCycle = priceAverageCycle;
    }

    public float getPriceAverageFirstCycle() {
        return priceAverageFirstCycle;
    }

    public void setPriceAverageFirstCycle(float priceAverageFirstCycle) {
        this.priceAverageFirstCycle = priceAverageFirstCycle;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Calendar getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Calendar finishTime) {
        this.finishTime = finishTime;
    }

    public Calendar getStartTime() {
        return startTime;
    }

    public void setStartTime(Calendar startTime) {
        this.startTime = startTime;
    }

   
}

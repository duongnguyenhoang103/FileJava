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
 *Miêu tả 1 chu kỳ kinh doanh
 * @author Binle
 */
@Entity
public class BusinessCycle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int departmentId;// mã phòng ban thực hiện
    @Temporal(TemporalType.DATE)
    private Calendar startTime;// thời gian bắt đầu kì kinh doanh
    @Temporal(TemporalType.DATE)
    private Calendar finishTime;// thời gian kết thúc kỳ kinh doanh
    private float firstValue;// giá trị đầu kì
    private float lastValue;// giá trị cuối kì
    private float increaseValue;// giá trị tăng trong kỳ
    private float decreaseValue;// giá trị giảm trong kỳ
    private List<Integer> listOperationId;// các id nghiệp vụ phát sinh trong kì

    public float getDecreaseValue() {
        return decreaseValue;
    }

    public void setDecreaseValue(float decreaseValue) {
        this.decreaseValue = decreaseValue;
    }
   
    public float getFirstValue() {
        return firstValue;
    }

    public void setFirstValue(float firstValue) {
        this.firstValue = firstValue;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public float getIncreaseValue() {
        return increaseValue;
    }

    public void setIncreaseValue(float increaseValue) {
        this.increaseValue = increaseValue;
    }

    public float getLastValue() {
        return lastValue;
    }

    public void setLastValue(float lastValue) {
        this.lastValue = lastValue;
    }

    public List<Integer> getListOperationId() {
        return listOperationId;
    }

    public void setListOperationId(List<Integer> listOperationId) {
        this.listOperationId = listOperationId;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
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

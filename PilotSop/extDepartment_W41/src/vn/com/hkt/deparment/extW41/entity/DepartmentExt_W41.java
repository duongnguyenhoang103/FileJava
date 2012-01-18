/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.deparment.extW41.entity;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author duong
 */
@Entity
public class DepartmentExt_W41 {

    @Id
    private String departmentID;
    private String stateDepartment;// tình trạng dự án  
    private Date dkTrienKhai;// dự kiến triển khai
    private Date daTrienKhai; //đã triển khai
    private Date dkHoanThanh; // dự kiến hoàn thành
    private Date daHoanThanh; // đã hoàn thành
    private String describe;// mô tả ghi chú
    private String unequal; // chênh lệch

    public Date getDaHoanThanh() {
        return daHoanThanh;
    }

    public void setDaHoanThanh(Date daHoanThanh) {
        this.daHoanThanh = daHoanThanh;
    }

    public Date getDaTrienKhai() {
        return daTrienKhai;
    }

    public void setDaTrienKhai(Date daTrienKhai) {
        this.daTrienKhai = daTrienKhai;
    }

    public String getDepartmentID() {
        return departmentID;
    }

    public void setDepartmentID(String departmentID) {
        this.departmentID = departmentID;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public Date getDkHoanThanh() {
        return dkHoanThanh;
    }

    public void setDkHoanThanh(Date dkHoanThanh) {
        this.dkHoanThanh = dkHoanThanh;
    }

    public Date getDkTrienKhai() {
        return dkTrienKhai;
    }

    public void setDkTrienKhai(Date dkTrienKhai) {
        this.dkTrienKhai = dkTrienKhai;
    }

    public String getStateDepartment() {
        return stateDepartment;
    }

    public void setStateDepartment(String stateDepartment) {
        this.stateDepartment = stateDepartment;
    }

    public String getUnequal() {
        return unequal;
    }

    public void setUnequal(String unequal) {
        this.unequal = unequal;
    }

    public DepartmentExt_W41(String departmentID, String stateDepartment, Date dkTrienKhai, Date daTrienKhai, Date dkHoanThanh, Date daHoanThanh, String describe, String unequal) {
        this.departmentID = departmentID;
        this.stateDepartment = stateDepartment;
        this.dkTrienKhai = dkTrienKhai;
        this.daTrienKhai = daTrienKhai;
        this.dkHoanThanh = dkHoanThanh;
        this.daHoanThanh = daHoanThanh;
        this.describe = describe;
        this.unequal = unequal;
    }

    public DepartmentExt_W41() {
        super();
    }

    @Override
    public String toString() {
        return departmentID;
    }
}

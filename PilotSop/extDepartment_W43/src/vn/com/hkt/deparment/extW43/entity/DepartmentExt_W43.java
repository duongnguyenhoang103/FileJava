/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.deparment.extW43.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author duong
 */
@Entity
public class DepartmentExt_W43 {

    @Id
    private String departmentID;
    private float sumMoneyImport;// tổng tiền nhập  
    private float sumMoneyExport;// ---xuất
    private float sumprofit; //tổng lợi nhuận
    private String proportionProfit;//tỉ suất lợi nhuận
    private String estimate;//đánh giá

    public String getDepartmentID() {
        return departmentID;
    }

    public void setDepartmentID(String departmentID) {
        this.departmentID = departmentID;
    }

    public String getEstimate() {
        return estimate;
    }

    public void setEstimate(String estimate) {
        this.estimate = estimate;
    }

    public String getProportionProfit() {
        return proportionProfit;
    }

    public void setProportionProfit(String proportionProfit) {
        this.proportionProfit = proportionProfit;
    }

    public float getSumMoneyExport() {
        return sumMoneyExport;
    }

    public void setSumMoneyExport(float sumMoneyExport) {
        this.sumMoneyExport = sumMoneyExport;
    }

    public float getSumMoneyImport() {
        return sumMoneyImport;
    }

    public void setSumMoneyImport(float sumMoneyImport) {
        this.sumMoneyImport = sumMoneyImport;
    }

    public float getSumprofit() {
        return sumprofit;
    }

    public void setSumprofit(float sumprofit) {
        this.sumprofit = sumprofit;
    }

    public DepartmentExt_W43(String departmentID, float sumMoneyImport, float sumMoneyExport, float sumprofit, String proportionProfit, String estimate) {
        this.departmentID = departmentID;
        this.sumMoneyImport = sumMoneyImport;
        this.sumMoneyExport = sumMoneyExport;
        this.sumprofit = sumprofit;
        this.proportionProfit = proportionProfit;
        this.estimate = estimate;
    }

    public DepartmentExt_W43() {
        super();
    }

    @Override
    public String toString() {
        return departmentID;
    }
}

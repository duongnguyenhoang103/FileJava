/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.product.extW52.entity;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author duong
 */
@Entity
public class ProductExt_W52 {

    @Id
    private String ProductID;
    private int totalExports; // tổng lượng xuất
    private int totalImport; // tổng lượng nhập
    private int sumMoneyEx;//  tổng tiền xuất
    private int sumMoneyIm; // tổng tiền nhập
    private int sumProfit; //tổng lợi nhuận
    private float profitRatio; //tỉ suất lợi nhuận
    private String evaluation; // đánh giá

    public String getProductID() {
        return ProductID;
    }

    public void setProductID(String ProductID) {
        this.ProductID = ProductID;
    }

    public String getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(String evaluation) {
        this.evaluation = evaluation;
    }

    public float getProfitRatio() {
        return profitRatio;
    }

    public void setProfitRatio(float profitRatio) {
        this.profitRatio = profitRatio;
    }

    public int getSumMoneyEx() {
        return sumMoneyEx;
    }

    public void setSumMoneyEx(int sumMoneyEx) {
        this.sumMoneyEx = sumMoneyEx;
    }

    public int getSumMoneyIm() {
        return sumMoneyIm;
    }

    public void setSumMoneyIm(int sumMoneyIm) {
        this.sumMoneyIm = sumMoneyIm;
    }

    public int getSumProfit() {
        return sumProfit;
    }

    public void setSumProfit(int sumProfit) {
        this.sumProfit = sumProfit;
    }

    public int getTotalExports() {
        return totalExports;
    }

    public void setTotalExports(int totalExports) {
        this.totalExports = totalExports;
    }

    public int getTotalImport() {
        return totalImport;
    }

    public void setTotalImport(int totalImport) {
        this.totalImport = totalImport;
    }

    public ProductExt_W52(String ProductID, int totalExports, int totalImport, int sumMoneyEx, int sumMoneyIm, int sumProfit, float profitRatio, String evaluation) {
        this.ProductID = ProductID;
        this.totalExports = totalExports;
        this.totalImport = totalImport;
        this.sumMoneyEx = sumMoneyEx;
        this.sumMoneyIm = sumMoneyIm;
        this.sumProfit = sumProfit;
        this.profitRatio = profitRatio;
        this.evaluation = evaluation;
    }

    public ProductExt_W52() {
        super();
    }

    @Override
    public String toString() {
        return ProductID;
    }
}

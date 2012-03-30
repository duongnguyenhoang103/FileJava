/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.pilot.subenterprise13.entity;

import java.lang.reflect.Field;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.openide.util.lookup.ServiceProvider;
import vn.com.hkt.pilot.identity.access.api.IAccessData;
import vn.com.hkt.pilot.identity.entitiy.api.IEntity;
import vn.com.hkt.pilot.subenterprise13.Installer;
import vn.com.hkt.pilot.subenterprise13.dao.SubEnterprise13BN;

/**
 *
 * @author khangpn
 */
@ServiceProvider(service = IEntity.class)
@Entity
public class SubEnterprise13 implements IEntity {

    public static final String FIELD_ENTERPRISE_ID_ACTUAL = "enterpriseIdActual";
    public static final String FIELD_REVENUE = "revenue";
    public static final String FIELD_PROFIT = "profit";
    public static final String FIELD_CHARTERCAPITAL = "charterCapital";
    public static final String FIELD_LEGALCAPITAL = "legalCapital";
    public static final String FIELD_EMPLOYEE_NUM = "employeeNum";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int enterpriseIdActual;
    // W15-------------------------
    private int revenue; // Doanh thu
    private int profit;// Lợi nhuận
    private int charterCapital; // Vốn điều lệ
    private int legalCapital; // Vốn pháp định
    private int employeeNum; // Số nhân viên

    public SubEnterprise13() {
    }

    public SubEnterprise13(int enterpriseIdActual, int revenue, int profit,
            int charterCapital, int legalCapital, int employeeNum) {
        this.enterpriseIdActual = enterpriseIdActual;
        this.revenue = revenue;
        this.profit = profit;
        this.charterCapital = charterCapital;
        this.legalCapital = legalCapital;
        this.employeeNum = employeeNum;
    }

    public int getEnterpriseIdActual() {
        return enterpriseIdActual;
    }

    public void setEnterpriseIdActual(int enterpriseIdActual) {
        this.enterpriseIdActual = enterpriseIdActual;
    }

    public int getCharterCapital() {
        return charterCapital;
    }

    public void setCharterCapital(int charterCapital) {
        this.charterCapital = charterCapital;
    }

    public int getEmployeeNum() {
        return employeeNum;
    }

    public void setEmployeeNum(int employeeNum) {
        this.employeeNum = employeeNum;
    }

    @Override
    public String toString() {
        return this.getId() + "";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLegalCapital() {
        return legalCapital;
    }

    public void setLegalCapital(int legalCapital) {
        this.legalCapital = legalCapital;
    }

    public int getProfit() {
        return profit;
    }

    public void setProfit(int profit) {
        this.profit = profit;
    }

    public int getRevenue() {
        return revenue;
    }

    public void setRevenue(int revenue) {
        this.revenue = revenue;
    }

    @Override
    public String getEntityName() {
        return this.getClass().getSimpleName();
    }

    @Override
    public String getModuleOfEntity() {
        return Installer.MODULE_NAME;
    }

    @Override
    public String getEntityDescription() {
        return "Module mở rộng SB13";
    }

    @Override
    public IAccessData getAccessDataOfEntity() {
        return new SubEnterprise13BN();
    }

    @Override
    public String getFieldNameObjectId() {
        return FIELD_ENTERPRISE_ID_ACTUAL;
    }
 @Override
    public String getDataRealyOfField(String fieldName, String data) {
        return data;
    }

    @Override
    public String getDescriptionOfField(String fieldName) {
        return fieldName;
    }
}

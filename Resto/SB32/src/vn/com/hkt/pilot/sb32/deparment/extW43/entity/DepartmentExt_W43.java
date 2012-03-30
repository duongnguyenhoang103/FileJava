/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.pilot.sb32.deparment.extW43.entity;

import java.lang.reflect.Field;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.openide.util.lookup.ServiceProvider;
import vn.com.hkt.pilot.identity.access.api.IAccessData;
import vn.com.hkt.pilot.identity.entitiy.api.IEntity;
import vn.com.hkt.pilot.sb32.Installer;
import vn.com.hkt.pilot.sb32.department.extW43.dao.DepartmentExtW43_DAO;

/**
 *
 * @author duong
 */
@ServiceProvider(service = IEntity.class)
@Entity
public class DepartmentExt_W43 implements IEntity {

    public static final String FILED_ID = "id";
    public static final String FILED_DEPARTMENT_ID = "departmentIdActual";
    public static final String FILED_SUM_MONEY_IMPORT = "sumMoneyImport";
    public static final String FILED_SUM_MONEY_EXPORT = "sumMoneyExport";
    public static final String FILED_SUM_PROFIT = "sumprofit";
    public static final String FILED_PROPORTION_PROFIT = "proportionProfit";
    public static final String FILED_ESTIMATE = "estimate";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int departmentIdActual;
    private float sumMoneyImport;// tổng tiền nhập  
    private float sumMoneyExport;// ---xuất
    private float sumprofit; //tổng lợi nhuận
    private String proportionProfit;//tỉ suất lợi nhuận
    private String estimate;//đánh giá

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int getDepartmentIdActual() {
        return departmentIdActual;
    }

    public void setDepartmentIdActual(int departmentIdActual) {
        this.departmentIdActual = departmentIdActual;
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

    public DepartmentExt_W43(int departmentID, float sumMoneyImport,
            float sumMoneyExport, float sumprofit, String proportionProfit,
            String estimate) {
        this.departmentIdActual = departmentID;
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
        return departmentIdActual+"";
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
        return "Thông tin cụ thể về phòng ban";
    }

    @Override
    public IAccessData getAccessDataOfEntity() {
        return new DepartmentExtW43_DAO();
    }

    @Override
    public String getFieldNameObjectId() {
        return FILED_DEPARTMENT_ID;
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

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.pilot.sb31.deparment.extW41.entity;

import java.util.Calendar;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.openide.util.lookup.ServiceProvider;
import vn.com.hkt.pilot.identity.access.api.IAccessData;
import vn.com.hkt.pilot.identity.entitiy.api.IEntity;
import vn.com.hkt.pilot.sb31.Installer;
import vn.com.hkt.pilot.sb31.department.extW41.dao.DepartmentExtW41_DAO;

/**
 *
 * @author duong
 */
@ServiceProvider(service = IEntity.class)
@Entity
public class DepartmentExt_W41 implements IEntity {

    public static final String FIELD_ID = "id";
    public static final String FIELD_DEPARTMENT_ID_ACTUAL = "departmentIdActual";
    public static final String FIELD_STATE_DEPARTMENT = "stateDepartmentIdAcutal";
    public static final String FIELD_DEPLOYMENT_DATE = "deploymentDate";
    public static final String FIELD_PRINTER_NAME = "printerName";
    public static final String FIELD_DEPLOYED_DATE = "deployedDate";
    public static final String FIELD_FINISH_DATE = "finishDate";
    public static final String FIELD_FINISHED_DATE = "finishedDate";
    public static final String FIELD_DESCRIBE = "describe";
    public static final String FIELD_PROGRESS = "progress";
    public static final String FIELD_ENTERPRISE = "enterprise";
    public static final String FIELD_CUSTOMER = "customer";
    public static final String FIELD_CUSTOMER_NAME = "customerName";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int departmentIdActual;
    private int stateDepartmentIdAcutal;// tình trạng dự án 
    private String printerName;// tên máy in
    @Temporal(TemporalType.DATE)
    private Calendar deploymentDate;// dự kiến triển khai
    @Temporal(TemporalType.DATE)
    private Calendar deployedDate; //đã triển khai
    @Temporal(TemporalType.DATE)
    private Calendar finishDate; // dự kiến hoàn thành
    @Temporal(TemporalType.DATE)
    private Calendar finishedDate; // đã hoàn thành
    private String describe;// mô tả ghi chú
    private String progress; // Tiến độ
    private int idEnterprise = 0; // id doanh nghiep
    private int idPerson = 0; // id ca nhan
    private String customerName; // Là tên đối tác hoặc vãng lai

    public int getIdEnterprise() {
        return idEnterprise;
    }

    public void setIdEnterprise(int idEnterprise) {
        this.idEnterprise = idEnterprise;
    }

    public String getPrinterName() {
        return printerName;
    }

    public void setPrinterName(String printerName) {
        this.printerName = printerName;
    }

    public int getIdPerson() {
        return idPerson;
    }

    public void setIdPerson(int idPerson) {
        this.idPerson = idPerson;
    }

    public DepartmentExt_W41() {
    }

    public DepartmentExt_W41(int departmentIdActual, int stateDepartmentIdAcutal,
            Calendar deploymentDate, Calendar deployedDate, Calendar finishDate,
            Calendar finishedDate, String describe, String progress, int enterprise,
            int customer, String customerName) {
        this.departmentIdActual = departmentIdActual;
        this.stateDepartmentIdAcutal = stateDepartmentIdAcutal;
        this.deploymentDate = deploymentDate;
        this.deployedDate = deployedDate;
        this.finishDate = finishDate;
        this.finishedDate = finishedDate;
        this.describe = describe;
        this.progress = progress;
        this.idEnterprise = enterprise;
        this.idPerson = customer;
        this.customerName = customerName;
    }

    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public int getDepartmentIdActual() {
        return departmentIdActual;
    }

    public void setDepartmentIdActual(int departmentIdActual) {
        this.departmentIdActual = departmentIdActual;
    }

    public Calendar getDeployedDate() {
        return deployedDate;
    }

    public void setDeployedDate(Calendar deployedDate) {
        this.deployedDate = deployedDate;
    }

    public Calendar getDeploymentDate() {
        return deploymentDate;
    }

    public void setDeploymentDate(Calendar deploymentDate) {
        this.deploymentDate = deploymentDate;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public Calendar getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(Calendar finishDate) {
        this.finishDate = finishDate;
    }

    public Calendar getFinishedDate() {
        return finishedDate;
    }

    public void setFinishedDate(Calendar finishedDate) {
        this.finishedDate = finishedDate;
    }

    public String getProgress() {
        return progress;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }

    public int getStateDepartmentIdActual() {
        return stateDepartmentIdAcutal;
    }

    public void setStateDepartmentIdActual(int stateDepartmentIdAcutal) {
        this.stateDepartmentIdAcutal = stateDepartmentIdAcutal;
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
        return "Thông tin cụ thể của dự án";
    }

    @Override
    public IAccessData getAccessDataOfEntity() {
        return new DepartmentExtW41_DAO();
    }

    @Override
    public String getFieldNameObjectId() {
        return FIELD_DEPARTMENT_ID_ACTUAL;
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

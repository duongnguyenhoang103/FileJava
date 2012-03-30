/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.pilot.sb23.entity;

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
import vn.com.hkt.pilot.sb23.Installer;
import vn.com.hkt.pilot.subpersonsb23.dao.SubPersonSB23BN;

/**
 *
 * @author khangpn
 */
@ServiceProvider(service = IEntity.class)
@Entity
public class SubPersonSB23 implements IEntity {

    public static final String FIELD_PERSON_ID_ACTUAL = "personIdActual";
    public static final String FIELD_ENTERPRISE_ID_ACTUAL = "enterpriseIdActual";
    public static final String FIELD_MISSION_ID_ACTUAL = "missionIdActual";
    public static final String FIELD_COEFFICIENT_MIN = "coefficientMin";
    public static final String FIELD_COEFFICIENT_MAX = "coefficientMax";
    public static final String FIELD_FREQUENCY = "frequency";
    public static final String FIELD_TYPEOF_CONTRACT = "typeOfContract";
    public static final String FIELD_AUTO_RENEW_DEADLINE = "autoRenewDeadline";
    public static final String FIELD_SALARY_MIN = "salaryMin";
    public static final String FIELD_SALARY_MAX = "salaryMax";
    public static final String FIELD_CYCLE_INCREASE = "cycleIncrease";
    public static final String FIELD_CLASSIFICATION = "classification";
    public static final String FIELD_METHOD_INCREASE_ANNUALLY = "methodIncreaseAnnually";
    public static final String FIELD_INCREASE_VALUE = "increaseValue";
    public static final String FIELD_COMPENSATION_BYFIRED = "compensationByFired";
    public static final String FIELD_COMPENSATION_BYQUIT = "compensationByQuit";
    public static final String FIELD_SIGNEG_DATE = "signedDate";
    public static final String FIELD_START_DATE = "startDate";
    public static final String FIELD_UPDATE_DATE = "updatedDate";
    public static final String FIELD_FINISHED_DATE = "finishedDate";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int personIdActual;
    private int enterpriseIdActual; // Cong ty
    private int missionIdActual; // Chuc vu
    private double coefficientMin; // Hệ số tối thiểu
    private double coefficientMax; // Hệ số tối đa
    private String frequency; // Tần số trả lương
    private String typeOfContract;// Loại hợp đồng
    private int autoRenewDeadline; // Tự động làm mới khi hết hạn
    private double salaryMin; // Lương tối thiểu
    private double salaryMax; // Lương tối đa
    private int cycleIncrease; // Chu kỳ tăng thường niên
    private String classification; // Loại tăng thường niên (ngày hay tháng hay năm)
    private String methodIncreaseAnnually; // Hình thức tăng thường niên (Combo % hay tiền)
    private double increaseValue; // Giá trị tăng
    private double compensationByFired; // Bồi thường đuổi việc
    private double compensationByQuit; // Bồi thường bỏ việc
    @Temporal(TemporalType.DATE)
    private Calendar signedDate;// Ngày ký kết
    @Temporal(TemporalType.DATE)
    private Calendar startDate;// Ngày bắt đầu
    @Temporal(TemporalType.DATE)
    private Calendar updatedDate;// Ngày thay đổi
    @Temporal(TemporalType.DATE)
    private Calendar finishedDate;// Ngày kết thúc

    public SubPersonSB23() {
    }

    public SubPersonSB23(int id, int personIdActual, int enterpriseIdActual, int missionIdActual, double coefficientMin, double coefficientMax, String frequency, String typeOfContract, int autoRenewDeadline, double salaryMin, double salaryMax, int cycleIncrease, String classification, String methodIncreaseAnnually, double increaseValue, double compensationByFired, double compensationByQuit, Calendar signedDate, Calendar startDate, Calendar updatedDate, Calendar finishedDate) {
        this.id = id;
        this.personIdActual = personIdActual;
        this.enterpriseIdActual = enterpriseIdActual;
        this.missionIdActual = missionIdActual;
        this.coefficientMin = coefficientMin;
        this.coefficientMax = coefficientMax;
        this.frequency = frequency;
        this.typeOfContract = typeOfContract;
        this.autoRenewDeadline = autoRenewDeadline;
        this.salaryMin = salaryMin;
        this.salaryMax = salaryMax;
        this.cycleIncrease = cycleIncrease;
        this.classification = classification;
        this.methodIncreaseAnnually = methodIncreaseAnnually;
        this.increaseValue = increaseValue;
        this.compensationByFired = compensationByFired;
        this.compensationByQuit = compensationByQuit;
        this.signedDate = signedDate;
        this.startDate = startDate;
        this.updatedDate = updatedDate;
        this.finishedDate = finishedDate;
    }

    public int getEnterpriseIdActual() {
        return enterpriseIdActual;
    }

    public void setEnterpriseIdActual(int enterpriseIdActual) {
        this.enterpriseIdActual = enterpriseIdActual;
    }

    public int getMissionIdActual() {
        return missionIdActual;
    }

    public void setMissionIdActual(int missionIdActual) {
        this.missionIdActual = missionIdActual;
    }

    public int getPersonIdActual() {
        return personIdActual;
    }

    public void setPersonIdActual(int personIdActual) {
        this.personIdActual = personIdActual;
    }

    public int getAutoRenewDeadline() {
        return autoRenewDeadline;
    }

    public void setAutoRenewDeadline(int autoRenewDeadline) {
        this.autoRenewDeadline = autoRenewDeadline;
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public double getCoefficientMax() {
        return coefficientMax;
    }

    public void setCoefficientMax(double coefficientMax) {
        this.coefficientMax = coefficientMax;
    }

    public double getCoefficientMin() {
        return coefficientMin;
    }

    public void setCoefficientMin(double coefficientMin) {
        this.coefficientMin = coefficientMin;
    }

    public double getCompensationByFired() {
        return compensationByFired;
    }

    public void setCompensationByFired(double compensationByFired) {
        this.compensationByFired = compensationByFired;
    }

    public double getCompensationByQuit() {
        return compensationByQuit;
    }

    public void setCompensationByQuit(double compensationByQuit) {
        this.compensationByQuit = compensationByQuit;
    }

    public Calendar getFinishedDate() {
        return finishedDate;
    }

    public void setFinishedDate(Calendar finishedDate) {
        this.finishedDate = finishedDate;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCycleIncrease() {
        return cycleIncrease;
    }

    public void setCycleIncrease(int cycleIncrease) {
        this.cycleIncrease = cycleIncrease;
    }

    public String getMethodIncreaseAnnually() {
        return methodIncreaseAnnually;
    }

    public void setMethodIncreaseAnnually(String methodIncreaseAnnually) {
        this.methodIncreaseAnnually = methodIncreaseAnnually;
    }

    public double getIncreaseValue() {
        return increaseValue;
    }

    public void setIncreaseValue(double increaseValue) {
        this.increaseValue = increaseValue;
    }

    public double getSalaryMax() {
        return salaryMax;
    }

    public void setSalaryMax(double salaryMax) {
        this.salaryMax = salaryMax;
    }

    public double getSalaryMin() {
        return salaryMin;
    }

    public void setSalaryMin(double salaryMin) {
        this.salaryMin = salaryMin;
    }

    public Calendar getSignedDate() {
        return signedDate;
    }

    public void setSignedDate(Calendar signedDate) {
        this.signedDate = signedDate;
    }

    public Calendar getStartDate() {
        return startDate;
    }

    public void setStartDate(Calendar startDate) {
        this.startDate = startDate;
    }

    public String getTypeOfContract() {
        return typeOfContract;
    }

    public void setTypeOfContract(String typeOfContract) {
        this.typeOfContract = typeOfContract;
    }

    public Calendar getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Calendar updatedDate) {
        this.updatedDate = updatedDate;
    }

    @Override
    public String toString() {
        return String.valueOf(enterpriseIdActual);
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
        return "Module mở rộng SB23";
    }

    @Override
    public IAccessData getAccessDataOfEntity() {
        return new SubPersonSB23BN();
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

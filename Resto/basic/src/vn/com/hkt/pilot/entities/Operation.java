/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.pilot.entities;

import java.util.Calendar;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.openide.util.Exceptions;
import org.openide.util.lookup.ServiceProvider;
import vn.com.hkt.basic.Installer;
import vn.com.hkt.bom.operation.dao.OperationBN;
import vn.com.hkt.bom.product.dao.ProductBN;
import vn.com.hkt.erm.department.dao.DepartmentBN;
import vn.com.hkt.erm.enterprise.dao.EnterpriseBN;
import vn.com.hkt.hrm.person.dao.PersonBN;
import vn.com.hkt.pilot.dialog.dao.ClassificationBN;
import vn.com.hkt.pilot.identity.access.api.IAccessData;
import vn.com.hkt.pilot.identity.entitiy.api.IEntity;

/**
 *
 * @author khanguct
 */
@ServiceProvider(service = IEntity.class)
@Entity
public class Operation implements IEntity {

    public static final String FIELD_ID = "id";
    public static final String FIELD_OPERATION_ID = "operationId";
    public static final String FIELD_OPERATION_NAME = "operationName";
    public static final String FIELD_DATE_TIME = "dateTime";
    public static final String FIELD_PRODUCTID_ID_ACTUAL = "productIdActual";
    public static final String FIELD_ENTERPRISE_ID_ACTUAL = "enterpriseIdActual";
    public static final String FIELD_DEPARTMENT_ID_ACTUAL = "departmentIdActual";
    public static final String FIELD_PERSON_ID_ACTUAL = "personIdActual";
    public static final String FIELD_CLASSIFICATION_ID_ACTUAL = "classificationIdActual";
    public static final String FIELD_SUM_PRISE = "sumPrice";
    public static final String FIELD_CACULATION_UNIT = "calculationUnit";
    public static final String FIELD_UNIT_OF_MEASURE_ID_ACTUAL = "unitOfMeasureIdActual";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String operationId;
    private String operationName;
    @Temporal(TemporalType.DATE)
    private Calendar dateTime;
    private int enterpriseIdActual; // Cong ty
    private int departmentIdActual; // Bo phan
    private int personIdActual; // Nguoi chiu trach nhiem
    private int classificationIdActual;// Phan Loai
    private float sumPrice; // Tong gia
    private String calculationUnit; // DV tinh
    private int unitOfMeasureIdActual; // Don vi do
    private float saleoffValue; // Tong % chiet khau KM
    private int saleoffRealValue; // Tong gia tri chiet khau KM
    private float typeOfTaxes; // Loai thue
    private int taxesValue; // Tien thue
    private float payment; // Thanh toan
    private float changedMoney; //Tiền thừa
    private float debt; // Tiền nợ

    public Operation() {
    }

    public Operation(String operationId, String operationName,
            Calendar dateTime, int enterpriseIdActual,
            int departmentIdActual, int personIdActual, int classificationIdActual,
            float sumPrice, String calculationUnit, int unitOfMeasureIdActual,
            float saleoffValue, int saleoffRealValue, float typeOfTaxes, int taxesValue,
            float payment, float changedMoney, float debt) {
        this.operationId = operationId;
        this.operationName = operationName;
        this.dateTime = dateTime;
        this.enterpriseIdActual = enterpriseIdActual;
        this.departmentIdActual = departmentIdActual;
        this.personIdActual = personIdActual;
        this.classificationIdActual = classificationIdActual;
        this.sumPrice = sumPrice;
        this.calculationUnit = calculationUnit;
        this.unitOfMeasureIdActual = unitOfMeasureIdActual;
        this.saleoffValue = saleoffValue;
        this.saleoffRealValue = saleoffRealValue;
        this.typeOfTaxes = typeOfTaxes;
        this.taxesValue = taxesValue;
        this.payment = payment;
        this.changedMoney = changedMoney;
        this.debt = debt;
    }

    public float getChangedMoney() {
        return changedMoney;
    }

    public void setChangedMoney(float changedMoney) {
        this.changedMoney = changedMoney;
    }

    public float getDebt() {
        return debt;
    }

    public void setDebt(float debt) {
        this.debt = debt;
    }

    public float getPayment() {
        return payment;
    }

    public void setPayment(float payment) {
        this.payment = payment;
    }

    public int getSaleoffRealValue() {
        return saleoffRealValue;
    }

    public void setSaleoffRealValue(int saleoffRealValue) {
        this.saleoffRealValue = saleoffRealValue;
    }

    public float getSaleoffValue() {
        return saleoffValue;
    }

    public void setSaleoffValue(float saleoffValue) {
        this.saleoffValue = saleoffValue;
    }

    public int getTaxesValue() {
        return taxesValue;
    }

    public void setTaxesValue(int taxesValue) {
        this.taxesValue = taxesValue;
    }

    public float getTypeOfTaxes() {
        return typeOfTaxes;
    }

    public void setTypeOfTaxes(float typeOfTaxes) {
        this.typeOfTaxes = typeOfTaxes;
    }

    public String getCalculationUnit() {
        return calculationUnit;
    }

    public void setCalculationUnit(String calculationUnit) {
        this.calculationUnit = calculationUnit;
    }

    public int getClassificationIdActual() {
        return classificationIdActual;
    }

    public void setClassificationIdActual(int classificationIdActual) {
        this.classificationIdActual = classificationIdActual;
    }

    public Calendar getDateTime() {
        return dateTime;
    }

    public void setDateTime(Calendar dateTime) {
        this.dateTime = dateTime;
    }

    public int getDepartmentIdActual() {
        return departmentIdActual;
    }

    public void setDepartmentIdActual(int departmentIdActual) {
        this.departmentIdActual = departmentIdActual;
    }

    public int getEnterpriseIdActual() {
        return enterpriseIdActual;
    }

    public void setEnterpriseIdActual(int enterpriseIdActual) {
        this.enterpriseIdActual = enterpriseIdActual;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOperationId() {
        return operationId;
    }

    public void setOperationId(String operationId) {
        this.operationId = operationId;
    }

    public String getOperationName() {
        return operationName;
    }

    public void setOperationName(String operationName) {
        this.operationName = operationName;
    }

    public int getPersonIdActual() {
        return personIdActual;
    }

    public void setPersonIdActual(int personIdActual) {
        this.personIdActual = personIdActual;
    }

    public float getSumPrice() {
        return sumPrice;
    }

    public void setSumPrice(float sumPrice) {
        this.sumPrice = sumPrice;
    }

    public int getUnitOfMeasureIdActual() {
        return unitOfMeasureIdActual;
    }

    public void setUnitOfMeasureIdActual(int unitOfMeasureIdActual) {
        this.unitOfMeasureIdActual = unitOfMeasureIdActual;
    }

    @Override
    public String toString() {
        return operationName;
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
        return "Thông tin cơ bản về nghiệp vụ";
    }

    @Override
    public IAccessData getAccessDataOfEntity() {
        return new OperationBN();
    }

    @Override
    public String getFieldNameObjectId() {
        return FIELD_OPERATION_ID;
    }

    @Override
    public String getDataRealyOfField(String fieldName, String data) {
        String dt = data;
        try {
            if (fieldName.equals(FIELD_CLASSIFICATION_ID_ACTUAL)) {
                dt = new ClassificationBN().getById(Integer.valueOf(data)).toString();
            } else if (fieldName.equals(FIELD_DEPARTMENT_ID_ACTUAL)) {
                dt = new DepartmentBN().getById(Integer.valueOf(data)).toString();
            } else if (fieldName.equals(FIELD_ENTERPRISE_ID_ACTUAL)) {
                dt = new EnterpriseBN().getById(Integer.valueOf(data)).toString();
            } else if (fieldName.equals(FIELD_PERSON_ID_ACTUAL)) {
                dt = new PersonBN().getById(Integer.valueOf(data)).toString();
            } else if (fieldName.equals(FIELD_PRODUCTID_ID_ACTUAL)) {
                dt = new ProductBN().getById(Integer.valueOf(data)).toString();
            }
        } catch (Exception ex) {
            Exceptions.printStackTrace(ex);
        }
        return dt;
    }

    @Override
    public String getDescriptionOfField(String fieldName) {
        if (fieldName.equals(FIELD_CLASSIFICATION_ID_ACTUAL)) {
            return "Loại nghiệp vụ";
        } else if (fieldName.equals(FIELD_DEPARTMENT_ID_ACTUAL)) {
            return "Phòng ban thực hiện";
        } else if (fieldName.equals(FIELD_ENTERPRISE_ID_ACTUAL)) {
            return "Doanh nghiệp";
        } else if (fieldName.equals(FIELD_PERSON_ID_ACTUAL)) {
            return "Nhân viên thực hiện";
        } else if (fieldName.equals(FIELD_PRODUCTID_ID_ACTUAL)) {
            return "Sản phẩm ";
        }
        return "";
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.pilot.sb51.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.openide.util.Lookup;
import org.openide.util.lookup.ServiceProvider;
import vn.com.hkt.basic.api.IOperationBN;
import vn.com.hkt.pilot.identity.access.api.IAccessData;
import vn.com.hkt.pilot.identity.entitiy.api.IEntity;
import vn.com.hkt.pilot.sb51.Installer;
import vn.com.hkt.pilot.sb51.spi.OperationPaymentBN;

/**
 *
 * @author BinLe
 */
@ServiceProvider(service=IEntity.class)
@Entity
public class OperationPayment implements IEntity {
    
    public static final String FIELD_ID = "id";
    public static final String FIELD_OPERATION_ID_ACTUAL = "operationIdActual";
    public static final String FIELD_PAYMENT_MOTHOD_ID_ACTUAL = "paymentMethodIdActual";
    public static final String FIELD_BILL_TYPE_ID_ACTUAL = "billTypeIdActual";
    public static final String FIELD_BIIL_CODE = "billCode";
    public static final String FIELD_ACCOUNT = "account";
    public static final String FIELD_ACCOUNT_SYMMETRY = "accountSymmetry";
    public static final String FIELD_DESCRIPTION = "description";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int operationIdActual;
    private int operationStatusIdActual;
    private int paymentMethodIdActual;// hình thức
    private int billTypeIdActual;// loại hóa đơn
    private String billCode;// mã hóa đơn, số hóa đơn
    private int account;// tài khoản
    private int correspondingAccount;// tài khoản đối ứng
    private String description;
    
    public OperationPayment() {
    }
    
    public OperationPayment(int operationIdActual, int paymentMethodIdActual, int billTypeIdActual, String billCode, int account, int correspondingAccount, String description) {
        this.operationIdActual = operationIdActual;
        this.paymentMethodIdActual = paymentMethodIdActual;
        this.billTypeIdActual = billTypeIdActual;
        this.billCode = billCode;
        this.account = account;
        this.correspondingAccount = correspondingAccount;
        this.description = description;
    }

    public int getOperationStatusIdActual() {
        return operationStatusIdActual;
    }

    public void setOperationStatusIdActual(int operationStatusIdActual) {
        this.operationStatusIdActual = operationStatusIdActual;
    }
    
    
    public int getAccount() {
        return account;
    }
    
    public void setAccount(int account) {
        this.account = account;
    }
    
    public int getCorrespondingAccount() {
        return correspondingAccount;
    }
    
    public void setCorrespondingAccount(int correspondingAccount) {
        this.correspondingAccount = correspondingAccount;
    }
    
    public String getBillCode() {
        return billCode;
    }
    
    public void setBillCode(String billCode) {
        this.billCode = billCode;
    }
    
    public int getBillTypeIdActual() {
        return billTypeIdActual;
    }
    
    public void setBillTypeIdActual(int billTypeIdActual) {
        this.billTypeIdActual = billTypeIdActual;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    @Override
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public int getOperationIdActual() {
        return operationIdActual;
    }
    
    public void setOperationIdActual(int operationIdActual) {
        this.operationIdActual = operationIdActual;
    }
    
    public int getPaymentMethodIdActual() {
        return paymentMethodIdActual;
    }
    
    public void setPaymentMethodIdActual(int paymentMethodIdActual) {
        this.paymentMethodIdActual = paymentMethodIdActual;
    }
    
    @Override
    public String toString() {
        try {
            return ((IOperationBN) Lookup.getDefault().lookup(IOperationBN.class)).getById(operationIdActual).toString();
        } catch (Exception e) {
            return "";
        }
    }
    
    @Override
    public String getEntityName() {
        return getClass().getSimpleName();
    }
    
    @Override
    public String getModuleOfEntity() {
        return Installer.MODULE_NAME;
    }
    
    @Override
    public String getEntityDescription() {
        return "Loại hình thanh toán";
    }
    
    @Override
    public IAccessData getAccessDataOfEntity() {
        return new OperationPaymentBN();
    }
    
    @Override
    public String getFieldNameObjectId() {
        return FIELD_OPERATION_ID_ACTUAL;
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

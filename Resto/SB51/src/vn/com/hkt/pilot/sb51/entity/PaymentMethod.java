/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.pilot.sb51.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.openide.util.lookup.ServiceProvider;
import vn.com.hkt.pilot.identity.access.api.IAccessData;
import vn.com.hkt.pilot.identity.entitiy.api.IEntity;
import vn.com.hkt.pilot.sb51.Installer;
import vn.com.hkt.pilot.sb51.spi.PaymentMethodBN;

/**
 *
 * @author BinLe
 */
@ServiceProvider(service=IEntity.class)
@Entity
public class PaymentMethod implements IEntity {

    private static final String FIELD_ID = "id";
    private static final String FIELD_PAYMENT_METHOD_NAME = "paymentMethodName";
    private static final String FIELD_DESCRIPTION = "description";
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    private String paymentMethodName;
    private String description;

    public PaymentMethod() {
    }

    public PaymentMethod(String paymentMethodName, String description) {
        this.paymentMethodName = paymentMethodName;
        this.description = description;
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

    public String getPaymentMethodName() {
        return paymentMethodName;
    }

    public void setPaymentMethodName(String paymentMethodName) {
        this.paymentMethodName = paymentMethodName;
    }

    @Override
    public String toString() {
        return paymentMethodName;
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
        return "Hinh thuc thanh toan";
    }

    @Override
    public IAccessData getAccessDataOfEntity() {
        return new PaymentMethodBN();
    }

    @Override
    public String getFieldNameObjectId() {
        return FIELD_ID;
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

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.pilot.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.openide.util.lookup.ServiceProvider;
import vn.com.hkt.basic.Installer;
import vn.com.hkt.bom.operation.dao.OperationDetailBN;
import vn.com.hkt.pilot.identity.access.api.IAccessData;
import vn.com.hkt.pilot.identity.entitiy.api.IEntity;

/**
 *
 * @author khangpn
 */
@ServiceProvider(service = IEntity.class)
@Entity
public class OperationDetail implements IEntity {

    public static final String FIELD_OPERATION_ID_ACTUAL = "operationIdActual";
    public static final String FIELD_PRODUCT_ID_ACTUAL = "productIdActual";
    public static final String FIELD_PRICE = "price";
    public static final String FIELD_QUANTITY = "quantity";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int operationIdActual; // Mã Hóa đơn
    private int productIdActual; // Mã sản phẩm
    private int quantity; // Số lượng
    private float price;// giá của sản sản phẩm của nghiệp vụ

    public OperationDetail() {
    }

    public OperationDetail(int operationIdActual, int productIdActual, int quantity, float price) {
        this.operationIdActual = operationIdActual;
        this.productIdActual = productIdActual;
        this.quantity = quantity;
        this.price = price;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getOperationIdActual() {
        return operationIdActual;
    }

    public void setOperationIdActual(int operationIdActual) {
        this.operationIdActual = operationIdActual;
    }

    public int getProductIdActual() {
        return productIdActual;
    }

    public void setProductIdActual(int productIdActual) {
        this.productIdActual = productIdActual;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setId(int id) {
        this.id = id;
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
        return "Thông tin về chi tiết nghiệp vụ";
    }

    @Override
    public IAccessData getAccessDataOfEntity() {
        return new OperationDetailBN();
    }

    @Override
    public String getFieldNameObjectId() {
        return FIELD_OPERATION_ID_ACTUAL;
    }

    @Override
    public int getId() {
        return id;
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

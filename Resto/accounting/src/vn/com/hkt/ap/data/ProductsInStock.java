/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.ap.data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Miêu tả đợt hàng trong kho
 * @author Admin 
 */
@Entity
public class ProductsInStock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String operationImportID;// mã nghiệp vụ nhập
    private long initialQuantity;// số lượng nhập ban đâu
    private long quantity;// số lượng thực còn lại
    private float price;// giá nhập

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getInitialQuantity() {
        return initialQuantity;
    }

    public void setInitialQuantity(long initialQuantity) {
        this.initialQuantity = initialQuantity;
    }

    public String getOperationImportID() {
        return operationImportID;
    }

    public void setOperationImportID(String operationImportID) {
        this.operationImportID = operationImportID;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }
}

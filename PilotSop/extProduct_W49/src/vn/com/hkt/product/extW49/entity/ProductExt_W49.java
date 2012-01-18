/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.product.extW49.entity;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author duong
 */
@Entity
public class ProductExt_W49 {

    @Id
    private String ProductID;
    private String specifications; // thông số   
    private String description;// mô tả 

    public String getProductID() {
        return ProductID;
    }

    public void setProductID(String ProductID) {
        this.ProductID = ProductID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSpecifications() {
        return specifications;
    }

    public void setSpecifications(String specifications) {
        this.specifications = specifications;
    }

    public ProductExt_W49(String ProductID, String specifications, String description) {
        this.ProductID = ProductID;
        this.specifications = specifications;
        this.description = description;
    }

    public ProductExt_W49() {
        super();
    }

    @Override
    public String toString() {
        return ProductID;
    }
}

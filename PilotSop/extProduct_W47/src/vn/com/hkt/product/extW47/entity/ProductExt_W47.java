/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.product.extW47.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author duong
 */
@Entity
public class ProductExt_W47 {

    @Id
    private String ProductID;
    private String provider; // nhà cung cấp 
    private String partner; // đối tác
    private String anonymous;// vô danh , vãng lai

    public String getProductID() {
        return ProductID;
    }

    public void setProductID(String ProductID) {
        this.ProductID = ProductID;
    }

   

    public String getAnonymous() {
        return anonymous;
    }

    public void setAnonymous(String anonymous) {
        this.anonymous = anonymous;
    }

    public String getPartner() {
        return partner;
    }

    public void setPartner(String partner) {
        this.partner = partner;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public ProductExt_W47(String ProductID, String provider, String partner, String anonymous) {
        this.ProductID = ProductID;
        this.provider = provider;
        this.partner = partner;
        this.anonymous = anonymous;
    }

    

    public ProductExt_W47() {
        super();
    }

    @Override
    public String toString() {
        return ProductID;
    }
}

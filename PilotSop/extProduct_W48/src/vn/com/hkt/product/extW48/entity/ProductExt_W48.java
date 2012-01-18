/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.product.extW48.entity;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author duong
 */
@Entity
public class ProductExt_W48 {

    @Id
    private String ProductID;
    private String nameEnglish;
    private Date dateOfProduction; // ngày sản xuất
    private Date dateEdit; //  ngày chỉnh sửa
    private Date expiryDate;// hạn sử dụng
    private String descriptiveNote;// mô tả ghi chú
    private String promotion; // khuyến mãi

    public String getProductID() {
        return ProductID;
    }

    public void setProductID(String ProductID) {
        this.ProductID = ProductID;
    }

    public Date getDateEdit() {
        return dateEdit;
    }

    public void setDateEdit(Date dateEdit) {
        this.dateEdit = dateEdit;
    }

    public Date getDateOfProduction() {
        return dateOfProduction;
    }

    public void setDateOfProduction(Date dateOfProduction) {
        this.dateOfProduction = dateOfProduction;
    }

    public String getDescriptiveNote() {
        return descriptiveNote;
    }

    public void setDescriptiveNote(String descriptiveNote) {
        this.descriptiveNote = descriptiveNote;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getNameEnglish() {
        return nameEnglish;
    }

    public void setNameEnglish(String nameEnglish) {
        this.nameEnglish = nameEnglish;
    }

    public String getPromotion() {
        return promotion;
    }

    public void setPromotion(String promotion) {
        this.promotion = promotion;
    }

    public ProductExt_W48(String ProductID, String nameEnglish, Date dateOfProduction, Date dateEdit, Date expiryDate, String descriptiveNote, String promotion) {
        this.ProductID = ProductID;
        this.nameEnglish = nameEnglish;
        this.dateOfProduction = dateOfProduction;
        this.dateEdit = dateEdit;
        this.expiryDate = expiryDate;
        this.descriptiveNote = descriptiveNote;
        this.promotion = promotion;
    }

    public ProductExt_W48() {
        super();
    }

    @Override
    public String toString() {
        return ProductID;
    }
}

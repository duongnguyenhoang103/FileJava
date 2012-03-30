/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.pilot.promotion.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.openide.util.lookup.ServiceProvider;
import vn.com.hkt.pilot.identity.access.api.IAccessData;
import vn.com.hkt.pilot.identity.entitiy.api.IEntity;
import vn.com.hkt.pilot.promotion.Installer;
import vn.com.hkt.pilot.promotion.dao.SaleOffProductBN;

/**
 *
 * @author HKT01
 */
@Entity
@ServiceProvider(service = IEntity.class)
public class SaleOffProduct implements IEntity {

    public static final String FIELD_SALEOFFPRODUCT_ID = "saleOffProductID";
    public static final String FIELD_PROMOTION_ID_ACTUAL = "promotionIdActual";
    public static final String FIELD_PRODUCTMAIN_ID_ACTUAL = "productMainIdActual";
    public static final String FIELD_PRODUCTSUB_ID_ACTUAL = "productSubIdActual";
    public static final String FIELD_CLASSIFICATION_ID_ACTUAL = "classificationIdActual";
    public static final String FIELD_PRODUCT_MAIN_NUM = "productMainNum";
    public static final String FIELD_SALEOFF_PRODUCT_NUM = "saleOffProductNum";
    public static final String FIELD_NOTE = "note";
    public static final String FIELD_SALEOFF_OPTION_ID_ACTUAL = "saleOffOptionIdActual";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String saleOffProductID;
    private int promotionIdActual;
    private int productMainIdActual; // Ma SP chinh
    private int productSubIdActual; // Ma SP KM
    private int productMainNum; // SP chinh
    private int saleOffProductNum; // SP KM --> Ty le = SP chinh/SP KM VD: Ty le: 2/3
    private String note; // Ghi chu

//////////////////// Constructors //////////////////////////////////

    public SaleOffProduct(String saleOffProductID, int promotionIdActual, 
            int productMainIdActual, int productSubIdActual,
            int productMainNum, int saleOffProductNum, String note) {
        this.saleOffProductID = saleOffProductID;
        this.promotionIdActual = promotionIdActual;
        this.productMainIdActual = productMainIdActual;
        this.productSubIdActual = productSubIdActual;
        this.productMainNum = productMainNum;
        this.saleOffProductNum = saleOffProductNum;
        this.note = note;
    }
    

    public SaleOffProduct() {
    }

////////////////////// Getters and setters /////////////////////////////
   
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProductMainIdActual() {
        return productMainIdActual;
    }

    public void setProductMainIdActual(int productMainIdActual) {
        this.productMainIdActual = productMainIdActual;
    }

    public int getProductMainNum() {
        return productMainNum;
    }

    public void setProductMainNum(int productMainNum) {
        this.productMainNum = productMainNum;
    }

    public int getProductSubIdActual() {
        return productSubIdActual;
    }

    public void setProductSubIdActual(int productSubIdActual) {
        this.productSubIdActual = productSubIdActual;
    }

    public int getSaleOffProductNum() {
        return saleOffProductNum;
    }

    public void setSaleOffProductNum(int saleOffProductNum) {
        this.saleOffProductNum = saleOffProductNum;
    }
   
    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getPromotionIdActual() {
        return promotionIdActual;
    }

    public void setPromotionIdActual(int promotionIdActual) {
        this.promotionIdActual = promotionIdActual;
    }

    public String getSaleOffProductID() {
        return saleOffProductID;
    }

    public void setSaleOffProductID(String saleOffProductID) {
        this.saleOffProductID = saleOffProductID;
    }

   
////////////////// Override toString method /////////////////////////
    @Override
    public String toString() {
        return this.saleOffProductID;
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
        return "Module mở rộng SB45";
    }

    @Override
    public IAccessData getAccessDataOfEntity() {
        return new SaleOffProductBN();
    }

    @Override
    public String getFieldNameObjectId() {
        return FIELD_SALEOFFPRODUCT_ID;
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

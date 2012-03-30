/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.pilot.sb41.product.extW48.entity;

import java.util.Calendar;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.openide.util.lookup.ServiceProvider;
import vn.com.hkt.pilot.entities.Product;
import vn.com.hkt.pilot.identity.access.api.IAccessData;
import vn.com.hkt.pilot.identity.entitiy.api.IEntity;
import vn.com.hkt.pilot.sb41.Installer;
import vn.com.hkt.pilot.sb41.product.extW48.dao.ProductExtW48_BN;

/**
 *
 * @author duong
 */
@ServiceProvider(service = IEntity.class)
@Entity
public class ProductExt_W48 implements IEntity {

    public static final String FILED_PRODUCT_ID_ACTUAL = "productIdActual";
    public static final String FIELD_NAME_ENGLIST = "nameEnglish";
    public static final String FIELD_DATE_OF_PRODUCTION = "dateOfProduction";
    public static final String FIELD_DATE_EDIT = "dateEdit";
    public static final String FIELD_EXPIRY_DATE = "expiryDate";
    public static final String FIELD_DESCRIPTION_NOTE = "descriptiveNote";
    public static final String FIELD_PROMOTION = "promotion";
    public static final String FIELD_STATE = "state";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int productIdActual;
    private String nameEnglish;
    private int countryIdActual;// nước sản xuất
    private int cityIdActual;//thành phố sản xuất
    @Temporal(TemporalType.DATE)
    private Calendar dateOfProduction; // ngày sản xuất
    @Temporal(TemporalType.DATE)
    private Calendar dateEdit; //  ngày chỉnh sửa    
    @Temporal(TemporalType.DATE)
    private Calendar expiryDate;// hạn sử dụng
    private String descriptiveNote;// mô tả ghi chú
    private int statusIdActual;// tình trạng sản phẩm    
    private int priceSheetIdActual; // bảng giá    
    private int promotionIdActual;// khuyến mại

    public ProductExt_W48() {
    }

    public ProductExt_W48(int productIdActual, String nameEnglish, int countryIdActual, int cityIdActual, Calendar dateOfProduction, Calendar dateEdit, Calendar expiryDate, String descriptiveNote, int statusIdActual, int priceSheetIdActual, int promotionIdActual) {
        this.productIdActual = productIdActual;
        this.nameEnglish = nameEnglish;
        this.countryIdActual = countryIdActual;
        this.cityIdActual = cityIdActual;
        this.dateOfProduction = dateOfProduction;
        this.dateEdit = dateEdit;
        this.expiryDate = expiryDate;
        this.descriptiveNote = descriptiveNote;
        this.statusIdActual = statusIdActual;
        this.priceSheetIdActual = priceSheetIdActual;
        this.promotionIdActual = promotionIdActual;
    }

    public int getCityIdActual() {
        return cityIdActual;
    }

    public void setCityIdActual(int cityIdActual) {
        this.cityIdActual = cityIdActual;
    }

    public int getCountryIdActual() {
        return countryIdActual;
    }

    public void setCountryIdActual(int countryIdActual) {
        this.countryIdActual = countryIdActual;
    }

    public Calendar getDateEdit() {
        return dateEdit;
    }

    public void setDateEdit(Calendar dateEdit) {
        this.dateEdit = dateEdit;
    }

    public Calendar getDateOfProduction() {
        return dateOfProduction;
    }

    public void setDateOfProduction(Calendar dateOfProduction) {
        this.dateOfProduction = dateOfProduction;
    }

    public String getDescriptiveNote() {
        return descriptiveNote;
    }

    public void setDescriptiveNote(String descriptiveNote) {
        this.descriptiveNote = descriptiveNote;
    }

    public Calendar getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Calendar expiryDate) {
        this.expiryDate = expiryDate;
    }

    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameEnglish() {
        return nameEnglish;
    }

    public void setNameEnglish(String nameEnglish) {
        this.nameEnglish = nameEnglish;
    }

    public int getPriceSheetIdActual() {
        return priceSheetIdActual;
    }

    public void setPriceSheetIdActual(int priceSheetIdActual) {
        this.priceSheetIdActual = priceSheetIdActual;
    }

    public int getProductIdActual() {
        return productIdActual;
    }

    public void setProductIdActual(int productIdActual) {
        this.productIdActual = productIdActual;
    }

    public int getPromotionIdActual() {
        return promotionIdActual;
    }

    public void setPromotionIdActual(int promotionIdActual) {
        this.promotionIdActual = promotionIdActual;
    }

    public int getStatusIdActual() {
        return statusIdActual;
    }

    public void setStatusIdActual(int statusIdActual) {
        this.statusIdActual = statusIdActual;
    }

    @Override
    public String toString() {
        try {
            return new Product().getAccessDataOfEntity().getById(productIdActual).toString();
        } catch (Exception e) {
            return "";
        }
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
        return "Thông số sản xuất của sản phẩm";
    }

    @Override
    public IAccessData getAccessDataOfEntity() {
        return new ProductExtW48_BN();
    }

    @Override
    public String getFieldNameObjectId() {
        return FILED_PRODUCT_ID_ACTUAL;
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
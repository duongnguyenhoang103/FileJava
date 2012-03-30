/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.pilot.promotion.entity;

import java.util.Calendar;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.openide.util.lookup.ServiceProvider;
import vn.com.hkt.pilot.identity.access.api.IAccessData;
import vn.com.hkt.pilot.identity.entitiy.api.IEntity;
import vn.com.hkt.pilot.promotion.Installer;
import vn.com.hkt.pilot.promotion.dao.PromotionBN;

/**
 *
 * @author HKT01
 */
@Entity
@ServiceProvider(service = IEntity.class)
public class Promotion implements IEntity {

    public static final String PROMOTION_TYPE_PRICE = "Giá";
    public static final String PROMOTION_TYPE_PRODUCT = "Sản Phẩm";
    public static final String FIELD_PROMOTION_ID = "promotionID";
    public static final String FIELD_PROMOTION_NAME = "promotionName";
    public static final String FIELD_HOUR_START = "hourStart";
    public static final String FIELD_HOUR_FINISH = "hourFinish";
    public static final String FIELD_DATE_START = "dateSart";
    public static final String FIELD_DATE_FINISH = "dateFinish";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String promotionID;
    private String promotionName; // Tên chương trình khuyến mãi
    private String promotionType;// Loai chuong trinh khuyen mai (KM theo chiet khau, theo tang pham..)
    private int idClassification; // Lay tu basic
    private int classificationIdActual;// Phan loai KM theo HD,SP,Vung...
    @Temporal(TemporalType.TIME)
    private Calendar hourStart; // Gio bat dau
    @Temporal(TemporalType.TIME)
    private Calendar hourFinish; // Gio ket thuc
    @Temporal(TemporalType.DATE)
    private Calendar dateSart; // Ngay bat dau
    @Temporal(TemporalType.DATE)
    private Calendar dateFinish; // Ngay ket thuc
    private int saleOffOptionIdActual; // Ma tuy chon ngay thang KM

///////////////////////// Constructors ///////////////////////////////////    

    public Promotion(String promotionID, String promotionName, 
            String promotionType, int idClassification, int classificationIdActual,
            Calendar hourStart, Calendar hourFinish, Calendar dateSart,
            Calendar dateFinish, int saleOffOptionIdActual) {
   
        this.promotionID = promotionID;
        this.promotionName = promotionName;
        this.promotionType = promotionType;
        this.idClassification = idClassification;
        this.classificationIdActual = classificationIdActual;
        this.hourStart = hourStart;
        this.hourFinish = hourFinish;
        this.dateSart = dateSart;
        this.dateFinish = dateFinish;
        this.saleOffOptionIdActual = saleOffOptionIdActual;
    }
    

    public Promotion() {
    }

///////////////////////// Getters and Setters /////////////////////////////////

    public int getClassificationIdActual() {
        return classificationIdActual;
    }

    public void setClassificationIdActual(int classificationIdActual) {
        this.classificationIdActual = classificationIdActual;
    }

    public int getIdClassification() {
        return idClassification;
    }

    public void setIdClassification(int idClassification) {
        this.idClassification = idClassification;
    }
        
    public int getSaleOffOptionIdActual() {
        return saleOffOptionIdActual;
    }

    public void setSaleOffOptionIdActual(int saleOffOptionIdActual) {
        this.saleOffOptionIdActual = saleOffOptionIdActual;
    }
    
    public String getPromotionType() {
        return promotionType;
    }

    public void setPromotionType(String promotionType) {
        this.promotionType = promotionType;
    }

    public Calendar getDateFinish() {
        return dateFinish;
    }

    public void setDateFinish(Calendar dateFinish) {
        this.dateFinish = dateFinish;
    }

    public Calendar getDateSart() {
        return dateSart;
    }

    public void setDateSart(Calendar dateSart) {
        this.dateSart = dateSart;
    }

    public Calendar getHourFinish() {
        return hourFinish;
    }

    public void setHourFinish(Calendar hourFinish) {
        this.hourFinish = hourFinish;
    }

    public Calendar getHourStart() {
        return hourStart;
    }

    public void setHourStart(Calendar hourStart) {
        this.hourStart = hourStart;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPromotionID() {
        return promotionID;
    }

    public void setPromotionID(String promotionID) {
        this.promotionID = promotionID;
    }

    public String getPromotionName() {
        return promotionName;
    }

    public void setPromotionName(String promotionName) {
        this.promotionName = promotionName;
    }

////////////////// Override toString method ///////////////////////////
    @Override
    public String toString() {
        return promotionName;
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
        return new PromotionBN();
    }

    @Override
    public String getFieldNameObjectId() {
        return FIELD_PROMOTION_ID;
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

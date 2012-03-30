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
import vn.com.hkt.pilot.promotion.dao.SaleOffBN;

/**
 *
 * @author HKT01
 */
@Entity
@ServiceProvider(service = IEntity.class)
public class SaleOff implements IEntity {
    
    public static final String FIELD_SALEOFF_ID = "saleOffId";
    public static final String FIELD_PROMOTION_ID_ACTUAL = "promotionIdActual";
    public static final String FIELD_ID_CLASSIFICATION = "idClassification";
    public static final String FIELD_VALUE = "value";
    public static final String FIELD_REAL_VALUE = "realValue";
    public static final String FIELD_MONEYUNIT_ID_ACTUAL = "moneyUnitIdActual";
    public static final String FIELD_CLASSIFICATION_ID_ACTUAL = "classificationIdActual";
    public static final String FIELD_NOTE = "note";
    public static final String FIELD_CALCULATION_WAY = "calculationWay";
/////////////////  Properties ///////////////////////////////////////
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int promotionIdActual; // Ten chuong trinh khuyen mai
    
    private int value; // Tinh theo %
    private int realValue; // Gia tri tinh theo value --> output tien sau khi tinh
    private String moneyUnitIdActual; // Ma loai tien    
    private String note; // Ghi chu     
    private String calculationWay; // Cách tính

    /////////////////////////// Constructors ///////////////////////////////////
    public SaleOff(int promotionIdActual, int value, int realValue,
            String moneyUnitIdActual, String note, String calculationWay) {
        this.promotionIdActual = promotionIdActual;
        this.value = value;
        this.realValue = realValue;
        this.moneyUnitIdActual = moneyUnitIdActual;
        this.note = note;
        this.calculationWay = calculationWay;
    }
    
    public SaleOff() {
    }

/////////////////////// Getters and setters ///////////////////////
    public String getCalculationWay() {
        return calculationWay;
    }
    
    public void setCalculationWay(String calculationWay) {
        this.calculationWay = calculationWay;
    }
   
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
        
    public String getMoneyUnitIdActual() {
        return moneyUnitIdActual;
    }
    
    public void setMoneyUnitIdActual(String moneyUnitIdActual) {
        this.moneyUnitIdActual = moneyUnitIdActual;
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
    
    public int getRealValue() {
        return realValue;
    }
    
    public void setRealValue(int realValue) {
        this.realValue = realValue;
    }
      
    public int getValue() {
        return value;
    }
    
    public void setValue(int value) {
        this.value = value;
    }

/////////////////// Override toString method /////////////////////////////////
    @Override
    public String toString() {
        return String.valueOf(promotionIdActual);
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
        return new SaleOffBN();
    }
    
    @Override
    public String getFieldNameObjectId() {
        return FIELD_SALEOFF_ID;
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

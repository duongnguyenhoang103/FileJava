/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.pilot.sb42.pricesheeta.entity;

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
import vn.com.hkt.pilot.sb42.pricesheet.Installer;
import vn.com.hkt.pilot.sb42.pricesheet.dao.PriceSheetBN;

/**
 *
 * @author HKT01
 */
@ServiceProvider(service = IEntity.class)
@Entity
public class PriceSheet implements IEntity {

    public static final String FIELD_ID = "id";
    public static final String FIELD_PRICE_SHEET_ID = "pricesheetID";
    public static final String FIELD_MONEY_UNIT_ID = "moneyUnitID";
    public static final String FIELD_PRICE_SHEET_NAME = "pricesheetName";
    public static final String FIELD_ID_CLASSIFICATION = "idClassification";
    public static final String FIELD_EXPORT_UNIT = "exportUnit";
    public static final String FIELD_EXPORT_MIN = "exportMin";
    public static final String FIELD_EXPORT_MAX = "exportMax";
    public static final String FIELD_AMPLETUDE = "ampliTude";
    public static final String FIELD_APPLIED_FORM = "appliedFrom";
    public static final String FIELD_UNIT_MONEY_OF_EXPORT_UNIT = "unitMoneyOfExportUnit";
    public static final String FIELD_UNIT_MONEY_OF_EXPORT_MIN = "unitMoneyOfExportMin";
    public static final String FIELD_UNIT_MONEY_OF_EXPORT_MAX = "unitMoneyOfExportMax";
    public static final String FIELD_TYPE_OF_TAX = "typeOfTaxes";
    public static final String FIELD_APPLIED_TO = "appliedTo";
    public static final String FIELD_UN_EQUAL = "unEqual";
    public static final String FIELD_SYMMETRICAL_PRISE = "symmetricalPrice";
    public static final String FIELD_PRODUCT_ID_ACTUAL = "productIdActual";
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id; // Ma tu sinh khong cho phep thay doi
    private String pricesheetID; // Ma nguoi dung co the thay doi
    private String moneyUnitID; // Ma don vi tien te
    private String pricesheetName; // Ten bang gia
    private int idClassification; // Phan loai lay tu Basic : Mua ban, nhap xuat
    private int productIdActual; // Ma SP
    private float exportUnit; // Don gia xuat
    private float exportMin; // Gia xuat nho nhat co the
    private float exportMax; // Gia xuat lon nhat co the
    private float ampliTude; // Bien do
    private String ampliTudeUnit; // Đơn vị của biên độ (% hay là số)
    @Temporal(TemporalType.DATE)
    private Calendar appliedFrom; // Ap dung tu
    private String unitMoneyOfExportUnit; // Don vi tinh cua don gia xuat
    private String unitMoneyOfExportMin; // Don vi tinh cua don gia xuat Min
    private String unitMoneyOfExportMax; // Don vi tinh cua don gia xuat Max
    private String typeOfTaxes; // Loai thue
    @Temporal(TemporalType.DATE)
    private Calendar appliedTo; // Ap dung den ngay
    private int unEqual; // Chênh lệch
    private boolean symmetricalPrice; //Giá đối xứng

/////////////////////////// Constructors ////////////////////////////////
    public PriceSheet(String pricesheetID, String moneyUnitID, String pricesheetName,
            int idClassification, float exportUnit, float exportMin,
            float exportMax, float ampliTude, String ampliTudeUnit, Calendar appliedFrom,
            String unitMoneyOfExportUnit, String unitMoneyOfExportMin,
            String unitMoneyOfExportMax, String typeOfTaxes, Calendar appliedTo,
            int unEqual, boolean symmetricalPrice,int productIdActual) {
        this.pricesheetID = pricesheetID;
        this.moneyUnitID = moneyUnitID;
        this.pricesheetName = pricesheetName;
        this.idClassification = idClassification;
        this.exportUnit = exportUnit;
        this.exportMin = exportMin;
        this.exportMax = exportMax;
        this.ampliTude = ampliTude;
        this.ampliTudeUnit = ampliTudeUnit;
        this.appliedFrom = appliedFrom;
        this.unitMoneyOfExportUnit = unitMoneyOfExportUnit;
        this.unitMoneyOfExportMin = unitMoneyOfExportMin;
        this.unitMoneyOfExportMax = unitMoneyOfExportMax;
        this.typeOfTaxes = typeOfTaxes;
        this.appliedTo = appliedTo;
        this.unEqual = unEqual;
        this.symmetricalPrice = symmetricalPrice;
        this.productIdActual = productIdActual;
    }

    public PriceSheet() {
    }

///////////////////////////// Getters and Setters ////////////////////////////
    public float getAmpliTude() {
        return ampliTude;
    }

    public void setAmpliTude(float ampliTude) {
        this.ampliTude = ampliTude;
    }

    public Calendar getAppliedFrom() {
        return appliedFrom;
    }

    public void setAppliedFrom(Calendar appliedFrom) {
        this.appliedFrom = appliedFrom;
    }

    public Calendar getAppliedTo() {
        return appliedTo;
    }

    public void setAppliedTo(Calendar appliedTo) {
        this.appliedTo = appliedTo;
    }

    public float getExportMax() {
        return exportMax;
    }

    public void setExportMax(float exportMax) {
        this.exportMax = exportMax;
    }

    public float getExportMin() {
        return exportMin;
    }

    public void setExportMin(float exportMin) {
        this.exportMin = exportMin;
    }

    public float getExportUnit() {
        return exportUnit;
    }

    public void setExportUnit(float exportUnit) {
        this.exportUnit = exportUnit;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdClassification() {
        return idClassification;
    }

    public String getAmpliTudeUnit() {
        return ampliTudeUnit;
    }

    public void setAmpliTudeUnit(String ampliTudeUnit) {
        this.ampliTudeUnit = ampliTudeUnit;
    }

    public void setIdClassification(int idClassification) {
        this.idClassification = idClassification;
    }

    public String getMoneyUnitID() {
        return moneyUnitID;
    }

    public void setMoneyUnitID(String moneyUnitID) {
        this.moneyUnitID = moneyUnitID;
    }

    public String getPricesheetID() {
        return pricesheetID;
    }

    public void setPricesheetID(String pricesheetID) {
        this.pricesheetID = pricesheetID;
    }

    public String getPricesheetName() {
        return pricesheetName;
    }

    public void setPricesheetName(String pricesheetName) {
        this.pricesheetName = pricesheetName;
    }

    public String getTypeOfTaxes() {
        return typeOfTaxes;
    }

    public void setTypeOfTaxes(String typeOfTaxes) {
        this.typeOfTaxes = typeOfTaxes;
    }

    public String getUnitMoneyOfExportMax() {
        return unitMoneyOfExportMax;
    }

    public void setUnitMoneyOfExportMax(String unitMoneyOfExportMax) {
        this.unitMoneyOfExportMax = unitMoneyOfExportMax;
    }

    public String getUnitMoneyOfExportMin() {
        return unitMoneyOfExportMin;
    }

    public void setUnitMoneyOfExportMin(String unitMoneyOfExportMin) {
        this.unitMoneyOfExportMin = unitMoneyOfExportMin;
    }

    public String getUnitMoneyOfExportUnit() {
        return unitMoneyOfExportUnit;
    }

    public void setUnitMoneyOfExportUnit(String unitMoneyOfExportUnit) {
        this.unitMoneyOfExportUnit = unitMoneyOfExportUnit;
    }

    public boolean isSymmetricalPrice() {
        return symmetricalPrice;
    }

    public void setSymmetricalPrice(boolean symmetricalPrice) {
        this.symmetricalPrice = symmetricalPrice;
    }

    public int getUnEqual() {
        return unEqual;
    }

    public void setUnEqual(int unEqual) {
        this.unEqual = unEqual;
    }

    public int getProductIdActual() {
        return productIdActual;
    }

    public void setProductIdActual(int productIdActual) {
        this.productIdActual = productIdActual;
    }

///////////////////////////// Override toString method ///////////////////////
    @Override
    public String toString() {
        return String.valueOf(this.exportUnit);
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
        return "Miêu tả thông tin bảng giá";
    }

    @Override
    public IAccessData getAccessDataOfEntity() {
        return new PriceSheetBN();
    }

    @Override
    public String getFieldNameObjectId() {
        return FIELD_PRICE_SHEET_ID;
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

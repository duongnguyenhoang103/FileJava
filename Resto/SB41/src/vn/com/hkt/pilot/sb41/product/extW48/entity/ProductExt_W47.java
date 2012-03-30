/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.pilot.sb41.product.extW48.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.openide.util.lookup.ServiceProvider;
import vn.com.hkt.pilot.entities.Product;
import vn.com.hkt.pilot.identity.access.api.IAccessData;
import vn.com.hkt.pilot.identity.entitiy.api.IEntity;
import vn.com.hkt.pilot.sb41.Installer;
import vn.com.hkt.pilot.sb41.product.extW48.dao.ProductExt_W47BN;

/**
 *
 * @author BinLe
 */
@ServiceProvider(service = IEntity.class)
@Entity
public class ProductExt_W47 implements IEntity {

    public static final String SUPPLIER_TYPE_PARNER = "Đối tác";
    public static final String PRODUCT_ID_ACTUAL = "productIdActual";
    public static final String SUPPLIER_TYPE_IRREGULAR = "Vãng lai";
    public static final String PARNERSHIP_TYPE_ENTERPRISE = "Doanh nghiệp";
    public static final String PARNERSHIP_TYPE_PERSON = "Cá nhân";
    public static final String FIELD_SUBPPLIER_TYPE = "supplierType";
    public static final String FIELD_PARNERSHIP_TYPE = "parnershipType";
    public static final String FIELD_SUPPLIER_IRREGULAR_NAME = "supplierIrregularName";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int productIdActual;
    private String supplierType ;// loại nhà cung cấp đối tác hay vãng lai    
    private String parnershipType="";// loại đối tác doanh nghiệp hay cá nhân
    private int enterpriseIdActual=0;// đối tác là doanh nghiệp
    private int personIdActual =0;// đối tác là cá nhân
    private String supplierIrregularName;// tên nhà cung cấp vãng lai

    public ProductExt_W47() {
    }

    public ProductExt_W47(int productIdActual, String supplierType, String parnershipType, int enterpriseIdActual, int personIdActual, String supplierIrregularName) {
        this.productIdActual = productIdActual;
        this.supplierType = supplierType;
        this.parnershipType = parnershipType;
        this.enterpriseIdActual = enterpriseIdActual;
        this.personIdActual = personIdActual;
        this.supplierIrregularName = supplierIrregularName;
    }

    public int getEnterpriseIdActual() {
        return enterpriseIdActual;
    }

    public void setEnterpriseIdActual(int enterpriseIdActual) {
        this.enterpriseIdActual = enterpriseIdActual;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getParnershipType() {
        return parnershipType;
    }

    public void setParnershipType(String parnershipType) {
        this.parnershipType = parnershipType;
    }

    public int getPersonIdActual() {
        return personIdActual;
    }

    public void setPersonIdActual(int personIdActual) {
        this.personIdActual = personIdActual;
    }

    public int getProductIdActual() {
        return productIdActual;
    }

    public void setProductIdActual(int productIdActual) {
        this.productIdActual = productIdActual;
    }

    public String getSupplierIrregularName() {
        return supplierIrregularName;
    }

    public void setSupplierIrregularName(String supplierIrregularName) {
        this.supplierIrregularName = supplierIrregularName;
    }

    public String getSupplierType() {
        return supplierType;
    }

    public void setSupplierType(String supplierType) {
        this.supplierType = supplierType;
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
        return "";
    }

    @Override
    public IAccessData getAccessDataOfEntity() {
        return new ProductExt_W47BN();
    }

    @Override
    public String getFieldNameObjectId() {
        return PRODUCT_ID_ACTUAL ;
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
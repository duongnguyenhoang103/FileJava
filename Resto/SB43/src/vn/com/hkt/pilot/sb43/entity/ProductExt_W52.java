/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.pilot.sb43.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.openide.util.lookup.ServiceProvider;
import vn.com.hkt.pilot.entities.Product;
import vn.com.hkt.pilot.identity.access.api.IAccessData;
import vn.com.hkt.pilot.identity.entitiy.api.IEntity;
import vn.com.hkt.pilot.sb43.Installer;
import vn.com.hkt.pilot.sb43.product.extW48.dao.ProductExt_W52BN;

/**
 *
 * @author BinLe
 */
@ServiceProvider(service = IEntity.class)
@Entity
public class ProductExt_W52 implements IEntity {
    public static final String FILED_PRODUCT_ID_ACTUAL = "productIdActual";
    public static final String FIELD_TOTAL_NUMBER_IMPORT = "totalNumberImport";
    public static final String FIELD_TOTAL_NUMBER_EXPORT = "totalNumberExport";
    public static final String FIELD_VALUE_IMPORT = "totalVauleImport";
    public static final String FIELD_VALUE_EXPORT = "totalVauleExport";
    public static final String FIELD_TOTAL_PROFIT = "totalProfit";
    public static final String FIELD_PERCENT_PROFIT = "percentProfit";
    public static final String FIELD_DESCRIPTION = "description";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int productIdActual;// sản phẩm
    private float totalNumberImport;// lượng nhập
    private float totalNumberExport;// lượng xuất
    private float totalVauleImport;// giá trị nhập
    private float totalVauleExport;// giá trị xuất
    private float totalProfit;// lợi nhuận
    private float percentProfit;// tỉ suât lợi nhuận
    private String description;// đánh giá

    public ProductExt_W52() {
    }

    public ProductExt_W52(int productIdActual, float totalNumberImport, float totalNumberExport, float totalVauleImport, float totalVauleExport, float totalProfit, float percentProfit, String description) {
        this.productIdActual = productIdActual;
        this.totalNumberImport = totalNumberImport;
        this.totalNumberExport = totalNumberExport;
        this.totalVauleImport = totalVauleImport;
        this.totalVauleExport = totalVauleExport;
        this.totalProfit = totalProfit;
        this.percentProfit = percentProfit;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getPercentProfit() {
        return percentProfit;
    }

    public void setPercentProfit(float percentProfit) {
        this.percentProfit = percentProfit;
    }

    public int getProductIdActual() {
        return productIdActual;
    }

    public void setProductIdActual(int productIdActual) {
        this.productIdActual = productIdActual;
    }

    public float getTotalNumberExport() {
        return totalNumberExport;
    }

    public void setTotalNumberExport(float totalNumberExport) {
        this.totalNumberExport = totalNumberExport;
    }

    public float getTotalNumberImport() {
        return totalNumberImport;
    }

    public void setTotalNumberImport(float totalNumberImport) {
        this.totalNumberImport = totalNumberImport;
    }

    public float getTotalProfit() {
        return totalProfit;
    }

    public void setTotalProfit(float totalProfit) {
        this.totalProfit = totalProfit;
    }

    public float getTotalVauleExport() {
        return totalVauleExport;
    }

    public void setTotalVauleExport(float totalVauleExport) {
        this.totalVauleExport = totalVauleExport;
    }

    public float getTotalVauleImport() {
        return totalVauleImport;
    }

    public void setTotalVauleImport(float totalVauleImport) {
        this.totalVauleImport = totalVauleImport;
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
        return "Báo cáo số lượng sản phẩm";
    }

    @Override
    public IAccessData getAccessDataOfEntity() {
        return new ProductExt_W52BN();
    }

    @Override
    public String getFieldNameObjectId() {
//        try {
//            return new Product().getAccessDataOfEntity().getById(productIdActual).toString();
//        } catch (Exception e) {
//            return null;
//        }
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

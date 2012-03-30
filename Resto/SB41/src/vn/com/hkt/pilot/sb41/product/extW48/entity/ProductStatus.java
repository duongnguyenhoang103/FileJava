/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.pilot.sb41.product.extW48.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import vn.com.hkt.pilot.identity.access.api.IAccessData;
import vn.com.hkt.pilot.identity.entitiy.api.IEntity;
import vn.com.hkt.pilot.sb41.Installer;
import vn.com.hkt.pilot.sb41.product.extW48.dao.ProductStatusBN;

/**
 *
 * @author BinLe
 */
@Entity
public class ProductStatus implements IEntity {

    public static final String FIELD_ID = "id";
    public static final String FIELD_PRODUCT_STATUS_NAME = "productStatusName";
    public static final String FIELD_DESCRIPTION = "description";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String productStatusName;
    private String description;

    public ProductStatus() {
    }

    public ProductStatus(String productStatusName, String description) {
        this.productStatusName = productStatusName;
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

    public String getProductStatusName() {
        return productStatusName;
    }

    public void setProductStatusName(String productStatusName) {
        this.productStatusName = productStatusName;
    }

    @Override
    public String toString() {
        return productStatusName;
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
        return "Miêu tả tình trạng của sản phẩm";
    }

    @Override
    public IAccessData getAccessDataOfEntity() {
        return new ProductStatusBN();
    }

    @Override
    public String getFieldNameObjectId() {
        return FIELD_ID;
    }

    @Override
    public String getDataRealyOfField(String fieldName, String data) {
        return data;
    }

    @Override
    public String getDescriptionOfField(String fieldName) {
        if (fieldName.endsWith(FIELD_PRODUCT_STATUS_NAME)) {
            return "Tình trạng sản phẩm";
        } else if (fieldName.endsWith(FIELD_DESCRIPTION)) {
            return "Mô tả tình trạng sản phẩm";
        }
        return "";
    }
}

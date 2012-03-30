/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.pilot.sb41.product.extW48.entity;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.openide.util.lookup.ServiceProvider;
import vn.com.hkt.pilot.entities.Product;
import vn.com.hkt.pilot.identity.access.api.IAccessData;
import vn.com.hkt.pilot.identity.entitiy.api.IEntity;
import vn.com.hkt.pilot.sb41.Installer;
import vn.com.hkt.pilot.sb41.product.extW48.dao.ProductExt_W49BN;

/**
 *
 * @author BinLe
 */
@ServiceProvider(service = IEntity.class)
@Entity
public class ProductExt_W49 implements IEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int productIdActual;
    private List<String> parameters;
    private List<String> descriptions;

    public ProductExt_W49() {
    }

    public ProductExt_W49(int productIdActual, List<String> parameters, List<String> descriptions) {
        this.productIdActual = productIdActual;
        this.parameters = parameters;
        this.descriptions = descriptions;
    }

    public List<String> getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(List<String> descriptions) {
        this.descriptions = descriptions;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<String> getParameters() {
        return parameters;
    }

    public void setParameters(List<String> parameters) {
        this.parameters = parameters;
    }

    public int getProductIdActual() {
        return productIdActual;
    }

    public void setProductIdActual(int productIdActual) {
        this.productIdActual = productIdActual;
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
        return "Các thông số sản phẩm";
    }

    @Override
    public IAccessData getAccessDataOfEntity() {
        return new ProductExt_W49BN();
    }

    @Override
    public String getFieldNameObjectId() {
        return "productIdActual";
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

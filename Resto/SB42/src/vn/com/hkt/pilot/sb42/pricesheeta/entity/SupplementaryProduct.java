/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.pilot.sb42.pricesheeta.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.openide.util.lookup.ServiceProvider;
import vn.com.hkt.pilot.identity.access.api.IAccessData;
import vn.com.hkt.pilot.identity.entitiy.api.IEntity;
import vn.com.hkt.pilot.sb42.pricesheet.Installer;
import vn.com.hkt.pilot.sb42.pricesheet.dao.SupplementaryProductBN;

/**
 *
 * @author khangpn
 */
@Entity
@ServiceProvider(service=IEntity.class)
public class SupplementaryProduct implements IEntity{

    public static final String FIELD_PRODUCTID_ACTUAL = "productIdActual";
    
    @Id @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    private int productIdActual;

    public SupplementaryProduct(int productIdActual) {
        this.productIdActual = productIdActual;
    }
    
    
    public SupplementaryProduct() {
    }

    public int getProductIdActual() {
        return productIdActual;
    }

    public void setProductIdActual(int productIdActual) {
        this.productIdActual = productIdActual;
    }

    public void setId(int id) {
        this.id = id;
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
        return "Module mở rộng SB44";
    }

    @Override
    public IAccessData getAccessDataOfEntity() {
       return new SupplementaryProductBN();
    }

    @Override
    public String getFieldNameObjectId() {
        return "id";
    }

    @Override
    public int getId() {
        return id;
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

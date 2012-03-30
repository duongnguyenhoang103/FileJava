/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.pilot.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.openide.util.lookup.ServiceProvider;
import vn.com.hkt.basic.Installer;
import vn.com.hkt.bom.operation.dao.KindOfTaxBN;
import vn.com.hkt.pilot.identity.access.api.IAccessData;
import vn.com.hkt.pilot.identity.entitiy.api.IEntity;

/**
 *
 * @author khangpn
 * Entity Loại thuế
 */
@ServiceProvider(service=IEntity.class)
@Entity
public class KindOfTax implements IEntity{
    
    public static final String FIELD_KINDOFTAX_ID = "kindOfTaxId";
    public static final String FIELD_KINDOFTAX_NAME = "kindOfTaxName";
    
    @Id 
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    private String kindOfTaxId;// ma tu nhap loai thue
    private String kindOfTaxName;// ten loai thue

    public KindOfTax() {
    }

    public KindOfTax(String kindOfTaxId, String kindOfTaxName) {
        this.kindOfTaxId = kindOfTaxId;
        this.kindOfTaxName = kindOfTaxName;
    }

    public String getKindOfTaxId() {
        return kindOfTaxId;
    }

    public void setKindOfTaxId(String kindOfTaxId) {
        this.kindOfTaxId = kindOfTaxId;
    }

    public String getKindOfTaxName() {
        return kindOfTaxName;
    }

    public void setKindOfTaxName(String kindOfTaxName) {
        this.kindOfTaxName = kindOfTaxName;
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
        return "Module basic";
    }

    @Override
    public IAccessData getAccessDataOfEntity() {
        return new KindOfTaxBN();
    }

    @Override
    public String getFieldNameObjectId() {
        return FIELD_KINDOFTAX_ID;
    }

    @Override
    public int getId() {
        return this.id;
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

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
import vn.com.hkt.bom.operation.dao.OperationPartnerBN;
import vn.com.hkt.pilot.identity.access.api.IAccessData;
import vn.com.hkt.pilot.identity.entitiy.api.IEntity;

/**
 *
 * @author khangpn
 */
@ServiceProvider(service = IEntity.class)
@Entity
public class OperationPartner implements IEntity {

    public static final String SUPPLIER_TYPE_PARNER = "Đối tác";
    public static final String SUPPLIER_TYPE_IRREGULAR = "Vãng lai";
    public static final String PARNERSHIP_TYPE_ENTERPRISE = "Doanh nghiệp";
    public static final String PARNERSHIP_TYPE_PERSON = "Cá nhân";
    public static final String FIELD_OPERATION_ID_ACTUAL = "operationIdActual";
    public static final String FIELD_SUBPPLIER_TYPE = "supplierType";
    public static final String FIELD_PARNERSHIP_TYPE = "parnershipType";
    public static final String FIELD_SUPPLIER_IRREGULAR_NAME = "supplierIrregularName";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int operationIdActual;
    private String supplierType;// loại nhà cung cấp đối tác hay vãng lai    
    private String parnershipType="";// loại đối tác doanh nghiệp hay cá nhân
    private int enterpriseIdActual = -1;// đối tác là doanh nghiệp
    private int personIdActual = -1;// đối tác là cá nhân
    private String supplierIrregularName;// tên nhà cung cấp vãng lai

    public OperationPartner() {
    }

    public OperationPartner(int operationIdActual, String supplierType, 
            String parnershipType, String supplierIrregularName) {
        this.operationIdActual = operationIdActual;
        this.supplierType = supplierType;
        this.parnershipType = parnershipType;
        this.supplierIrregularName = supplierIrregularName;
    }

    public int getEnterpriseIdActual() {
        return enterpriseIdActual;
    }

    public void setEnterpriseIdActual(int enterpriseIdActual) {
        this.enterpriseIdActual = enterpriseIdActual;
    }

    public int getOperationIdActual() {
        return operationIdActual;
    }

    public void setOperationIdActual(int operationIdActual) {
        this.operationIdActual = operationIdActual;
    }

    public String isParnershipType() {
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
        return "Module Basic";
    }

    @Override
    public IAccessData getAccessDataOfEntity() {
        return new OperationPartnerBN();
    }

    @Override
    public String getFieldNameObjectId() {
        return FIELD_OPERATION_ID_ACTUAL;
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

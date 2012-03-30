/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.enterprise.ext.entity;

import java.lang.reflect.Field;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.openide.util.lookup.ServiceProvider;
import vn.com.hkt.enterprise.ext.dao.EnterpriseExtBN;
import vn.com.hkt.extension.Installer;
import vn.com.hkt.pilot.identity.access.api.IAccessData;
import vn.com.hkt.pilot.identity.entitiy.api.IEntity;

/**
 *
 * @author khanguct
 */
@Entity
@ServiceProvider(service = IEntity.class)
public class EnterpriseExt implements IEntity {

    public static final String FIELD_ENTERPRISEEXT_ID_ACTUAL = "enterpriseIdActual";
    public static final String FIELD_ENTERPRISEEXT_NAME = "enterpriseName";
    public static final String FIELD_ENTERPRISEEXT_TEL = "enterpriseTel";
    public static final String FIELD_ENTERPRISEEXT_EMAIL = "enterpriseEmail";
    public static final String FIELD_ENTERPRISEEXT_FAX = "enterpriseFax";
    public static final String FIELD_ENTERPRISEEXT_WEB = "enterpriseWeb";
    public static final String FIELD_ENTERPRISEEXT_ADDRESS_ID = "enterpriseAddressIdActual";
    public static final String FIELD_ENTERPRISEEXT_COUNTRY = "countryIdActual";
    public static final String FIELD_ENTERPRISEEXT_CITY = "cityIdActual";
    public static final String FIELD_ENTERPRISEEXT_POSTALCODE = "postalCode";
    public static final String FIELD_ENTERPRISEEXT_EXECUTIVEOFFICE = "executiveOfficesIdActual";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int enterpriseIdActual;
    private String enterpriseName;
    private String enterpriseTel;
    private String enterpriseEmail;
    private String enterpriseFax;
    private String enterpriseWeb;
    // W14-------------------------
    private List<Integer> enterpriseAddressIdActual;
//    private int countryIdActual;
//    private int cityIdActual;
    private String postalCode;
    // W16---------------------------
    private List<Integer> executiveOfficesIdActual; // 1 Cty có nhiều chức vụ điều hành

    public EnterpriseExt() {
    }

    public EnterpriseExt(int enterpriseIdActual, String enterpriseName,
            String enterpriseTel, String enterpriseEmail, String enterpriseFax,
            String enterpriseWeb, List<Integer> enterpriseAddressIdActual, String postalCode, List<Integer> executiveOfficesIdActual) {
        this.enterpriseIdActual = enterpriseIdActual;
        this.enterpriseName = enterpriseName;
        this.enterpriseTel = enterpriseTel;
        this.enterpriseEmail = enterpriseEmail;
        this.enterpriseFax = enterpriseFax;
        this.enterpriseWeb = enterpriseWeb;
        this.enterpriseAddressIdActual = enterpriseAddressIdActual;
        this.postalCode = postalCode;
        this.executiveOfficesIdActual = executiveOfficesIdActual;
    }


    public List<Integer> getEnterpriseAddressIdActual() {
        return enterpriseAddressIdActual;
    }

    public void setEnterpriseAddressIdActual(List<Integer> enterpriseAddressIdActual) {
        this.enterpriseAddressIdActual = enterpriseAddressIdActual;
    }

    public String getEnterpriseEmail() {
        return enterpriseEmail;
    }

    public void setEnterpriseEmail(String enterpriseEmail) {
        this.enterpriseEmail = enterpriseEmail;
    }

    public String getEnterpriseFax() {
        return enterpriseFax;
    }

    public void setEnterpriseFax(String enterpriseFax) {
        this.enterpriseFax = enterpriseFax;
    }

    public int getEnterpriseIdActual() {
        return enterpriseIdActual;
    }

    public void setEnterpriseIdActual(int enterpriseIdActual) {
        this.enterpriseIdActual = enterpriseIdActual;
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

    public String getEnterpriseTel() {
        return enterpriseTel;
    }

    public void setEnterpriseTel(String enterpriseTel) {
        this.enterpriseTel = enterpriseTel;
    }

    public String getEnterpriseWeb() {
        return enterpriseWeb;
    }

    public void setEnterpriseWeb(String enterpriseWeb) {
        this.enterpriseWeb = enterpriseWeb;
    }

    public List<Integer> getExecutiveOfficesIdActual() {
        return executiveOfficesIdActual;
    }

    public void setExecutiveOfficesIdActual(List<Integer> executiveOfficesIdActual) {
        this.executiveOfficesIdActual = executiveOfficesIdActual;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
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
        return "Mở rộng Enterprise SB12";
    }

    @Override
    public IAccessData getAccessDataOfEntity() {
        return new EnterpriseExtBN();
    }

    @Override
    public String getFieldNameObjectId() {
        return FIELD_ENTERPRISEEXT_ID_ACTUAL;
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

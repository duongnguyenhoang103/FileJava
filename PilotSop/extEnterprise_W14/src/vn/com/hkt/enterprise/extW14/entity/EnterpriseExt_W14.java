/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.enterprise.extW14.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author duong
 */
@Entity
public class EnterpriseExt_W14 {
    @Id
    private String EnterpriseID;
    private String  EnterpriseAddress;   
    private String idCountry;
    private String idCity;
    private  String postalCode;

    public String getEnterpriseAddress() {
        return EnterpriseAddress;
    }

    public void setEnterpriseAddress(String EnterpriseAddress) {
        this.EnterpriseAddress = EnterpriseAddress;
    }

    public String getEnterpriseID() {
        return EnterpriseID;
    }

    public void setEnterpriseID(String EnterpriseID) {
        this.EnterpriseID = EnterpriseID;
    }

    public String getIdCity() {
        return idCity;
    }

    public void setIdCity(String idCity) {
        this.idCity = idCity;
    }

    public String getIdCountry() {
        return idCountry;
    }

    public void setIdCountry(String idCountry) {
        this.idCountry = idCountry;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public EnterpriseExt_W14(String EnterpriseID, String EnterpriseAddress, String idCountry, String idCity, String postalCode) {
        this.EnterpriseID = EnterpriseID;
        this.EnterpriseAddress = EnterpriseAddress;
        this.idCountry = idCountry;
        this.idCity = idCity;
        this.postalCode = postalCode;
    }

   
    
    public EnterpriseExt_W14(){
        super();
    }

    @Override
    public String toString() {
        return EnterpriseID ;
    }
    
    
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.enterprise.extW12.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author duong
 */
@Entity
public class EnterpriseExt_W12 {
    @Id
    private String EnterpriseID;
    private String businessCode;
    private String description;

    public String getEnterpriseID() {
        return EnterpriseID;
    }

    public void setEnterpriseID(String EnterpriseID) {
        this.EnterpriseID = EnterpriseID;
    }

    public String getBusinessCode() {
        return businessCode;
    }

    public void setBusinessCode(String businessCode) {
        this.businessCode = businessCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public EnterpriseExt_W12(String EnterpriseID, String businessCode, String description) {
        this.EnterpriseID = EnterpriseID;
        this.businessCode = businessCode;
        this.description = description;
    }
    
     public EnterpriseExt_W12(){
         super();
     }

    @Override
    public String toString() {
        return  EnterpriseID ;
    }
    
     
}

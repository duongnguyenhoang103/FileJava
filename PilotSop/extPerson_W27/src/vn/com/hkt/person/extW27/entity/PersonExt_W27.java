/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.person.extW27.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author duong
 */
@Entity
public class PersonExt_W27 {
    @Id
    private String PersonID;
    private String foreignLanguage;// ngoại ngữ    
    private String informatics;// trình độ tin học
    private String particularilty; //đặc tính
    private String likePersonal;// sở thích cá nhân
    private String likeOccupation ;//sở thích nghề nghiệp
    private String likeExamine ; // nghiên cứu

    public String getPersonID() {
        return PersonID;
    }

    public void setPersonID(String PersonID) {
        this.PersonID = PersonID;
    }

    public String getForeignLanguage() {
        return foreignLanguage;
    }

    public void setForeignLanguage(String foreignLanguage) {
        this.foreignLanguage = foreignLanguage;
    }

    public String getInformatics() {
        return informatics;
    }

    public void setInformatics(String informatics) {
        this.informatics = informatics;
    }

    public String getLikeExamine() {
        return likeExamine;
    }

    public void setLikeExamine(String likeExamine) {
        this.likeExamine = likeExamine;
    }

    public String getLikeOccupation() {
        return likeOccupation;
    }

    public void setLikeOccupation(String likeOccupation) {
        this.likeOccupation = likeOccupation;
    }

    public String getLikePersonal() {
        return likePersonal;
    }

    public void setLikePersonal(String likePersonal) {
        this.likePersonal = likePersonal;
    }

    public String getParticularilty() {
        return particularilty;
    }

    public void setParticularilty(String particularilty) {
        this.particularilty = particularilty;
    }

    public PersonExt_W27(String PersonID, String foreignLanguage, String informatics, String particularilty, String likePersonal, String likeOccupation, String likeExamine) {
        this.PersonID = PersonID;
        this.foreignLanguage = foreignLanguage;
        this.informatics = informatics;
        this.particularilty = particularilty;
        this.likePersonal = likePersonal;
        this.likeOccupation = likeOccupation;
        this.likeExamine = likeExamine;
    }
 
    
     public PersonExt_W27(){
         super();
     }

    @Override
    public String toString() {
        return  PersonID ;
    }
     
     
}

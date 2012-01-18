/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.person.extW23.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author duong
 */
@Entity
public class PersonExt_W23 {

    @Id
    private String PersonID;
    private String adderss;
    private String idCountry;
    private String idCity;
    private int postalCode;

    public String getPersonID() {
        return PersonID;
    }

    public void setPersonID(String PersonID) {
        this.PersonID = PersonID;
    }

    public String getAdderss() {
        return adderss;
    }

    public void setAdderss(String adderss) {
        this.adderss = adderss;
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

    public int getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }

    public PersonExt_W23(String PersonID, String adderss, String idCountry, String idCity, int postalCode) {
        this.PersonID = PersonID;
        this.adderss = adderss;
        this.idCountry = idCountry;
        this.idCity = idCity;
        this.postalCode = postalCode;
    }

    public PersonExt_W23() {
        super();
    }

    @Override
    public String toString() {
        return PersonID;
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.enterprise.extW11.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author duong
 */
@Entity
public class EnterpriseExt_W11 {

    @Id
    private String EnterpriseID;
    private String nameVN;
    private String nameEnglish;
    private String type; //loại hình
    private int register;//đăng ký KD
    private String dateStart;//ngày thành lập
    private String dateRegister;//ngày đăng ký
    private String bookRegisterBussines; // sổ đăng ký kinh doanh
    private String idTax;//mã số kinh doanh

    public String getEnterpriseID() {
        return EnterpriseID;
    }

    public void setEnterpriseID(String EnterpriseID) {
        this.EnterpriseID = EnterpriseID;
    }

    public String getNameVN() {
        return nameVN;
    }

    public void setNameVN(String nameVN) {
        this.nameVN = nameVN;
    }

    public String getNameEnglish() {
        return nameEnglish;
    }

    public void setNameEnglish(String nameEnglish) {
        this.nameEnglish = nameEnglish;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getRegister() {
        return register;
    }

    public void setRegister(int register) {
        this.register = register;
    }

    public String getDateStart() {
        return dateStart;
    }

    public void setDateStart(String dateStart) {
        this.dateStart = dateStart;
    }

    public String getDateRegister() {
        return dateRegister;
    }

    public void setDateRegister(String dateRegister) {
        this.dateRegister = dateRegister;
    }

    public String getBookRegisterBussines() {
        return bookRegisterBussines;
    }

    public void setBookRegisterBussines(String bookRegisterBussines) {
        this.bookRegisterBussines = bookRegisterBussines;
    }

    public String getIdTax() {
        return idTax;
    }

    public void setIdTax(String idTax) {
        this.idTax = idTax;
    }

    public EnterpriseExt_W11(String EnterpriseID, String nameVN, String nameEnglish, String dateStart, String dateRegister, String bookRegisterBussines, String idTax) {
        this.EnterpriseID = EnterpriseID;
        this.nameVN = nameVN;
        this.nameEnglish = nameEnglish;
        this.dateStart = dateStart;
        this.dateRegister = dateRegister;
        this.bookRegisterBussines = bookRegisterBussines;
        this.idTax = idTax;
    }

    public EnterpriseExt_W11() {
        super();
    }

    @Override
    public String toString() {
        return  EnterpriseID ;
    }
   
}

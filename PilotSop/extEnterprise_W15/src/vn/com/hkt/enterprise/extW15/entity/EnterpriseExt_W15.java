/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.enterprise.extW15.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author duong
 */
@Entity
public class EnterpriseExt_W15 {
    @Id
    private String EnterpriseID;
    private String doanhThu;
    private int vonDieuLe;
    private String loiNhuan;
    private int vonPhapDinh;
    private  String soNhanVien;

    public String getEnterpriseID() {
        return EnterpriseID;
    }

    public void setEnterpriseID(String EnterpriseID) {
        this.EnterpriseID = EnterpriseID;
    }

    public String getDoanhThu() {
        return doanhThu;
    }

    public void setDoanhThu(String doanhThu) {
        this.doanhThu = doanhThu;
    }

    public String getLoiNhuan() {
        return loiNhuan;
    }

    public void setLoiNhuan(String loiNhuan) {
        this.loiNhuan = loiNhuan;
    }

    public String getSoNhanVien() {
        return soNhanVien;
    }

    public void setSoNhanVien(String soNhanVien) {
        this.soNhanVien = soNhanVien;
    }

    public int getVonDieuLe() {
        return vonDieuLe;
    }

    public void setVonDieuLe(int vonDieuLe) {
        this.vonDieuLe = vonDieuLe;
    }

    public int getVonPhapDinh() {
        return vonPhapDinh;
    }

    public void setVonPhapDinh(int vonPhapDinh) {
        this.vonPhapDinh = vonPhapDinh;
    }

    public EnterpriseExt_W15(String EnterpriseID, String doanhThu, int vonDieuLe, String loiNhuan, int vonPhapDinh, String soNhanVien) {
        this.EnterpriseID = EnterpriseID;
        this.doanhThu = doanhThu;
        this.vonDieuLe = vonDieuLe;
        this.loiNhuan = loiNhuan;
        this.vonPhapDinh = vonPhapDinh;
        this.soNhanVien = soNhanVien;
    }
    
    public EnterpriseExt_W15(){
        super();
    }

    @Override
    public String toString() {
        return EnterpriseID ;
    }
    
    
}

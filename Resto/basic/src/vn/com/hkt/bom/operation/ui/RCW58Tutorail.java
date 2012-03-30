/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.bom.operation.ui;

import javax.swing.JTextArea;

/**
 *
 * @author DONGTAM
 */
public class RCW58Tutorail {
    private JTextArea DoiTac;
    private JTextArea VangLai;
    public  RCW58Tutorail()
    {
        DoiTac=new JTextArea();
        DoiTac.setText("Chọn đối tác từ combobox");
        VangLai=new JTextArea();
        VangLai.setText("Nhập khách hàng vãng lai");
    }

    public JTextArea getDoiTac() {
        return DoiTac;
    }

    public JTextArea getVangLai() {
        return VangLai;
    }
    
}

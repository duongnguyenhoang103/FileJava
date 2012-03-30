/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.bom.operation.ui;

import java.awt.Component;
import javax.swing.AbstractCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableCellEditor;

/**
 *
 * @author longnt
 */
public class RCTongTienCell extends AbstractCellEditor implements TableCellEditor {

    private Component component;
    private JComboBox cboLoaiThue;
    private DefaultComboBoxModel model;
    private JTextField txtTongTien, txtChietKhau, txtGiaTriCK, txtTienThieu, txtTongCong, txtThanhToan, txtTientraLai, txtTienNo;

    public RCTongTienCell() {
        cboLoaiThue = new JComboBox();
        txtChietKhau = new JTextField();
        txtGiaTriCK = new JTextField();
        txtThanhToan = new JTextField();
        txtTienNo = new JTextField();
        txtTienThieu = new JTextField();
        txtTientraLai = new JTextField();
        txtTongCong = new JTextField();
        txtTongTien = new JTextField();
        
        model = new DefaultComboBoxModel();
        model.addElement(" ");
        cboLoaiThue.setModel(model);
    }

    @Override
    public Object getCellEditorValue() {
        if (component == cboLoaiThue) {
            return cboLoaiThue.getSelectedItem();
        } else if (component == txtChietKhau) {
            return txtChietKhau.getText();
        } else if (component == txtGiaTriCK) {
            return txtGiaTriCK.getText();
        } else if (component == txtTienNo) {
            return txtTienNo.getText();
        } else if (component == txtTienThieu) {
            return txtTienThieu.getText();
        } else if (component == txtTientraLai) {
            return txtTientraLai.getText();
        } else if (component == txtTongCong) {
            return txtTongCong.getText();
        } else if (component == txtThanhToan) {
            return txtThanhToan.getText();
        }else {
            return txtTongTien.getText();
        }
    }

    public JTextField getTxtChietKhau() {
        return txtChietKhau;
    }

    public JTextField getTxtGiaTriCK() {
        return txtGiaTriCK;
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        if (column == 1) {
            if (row == 0) {
                component = txtTongTien;
            }
            if (row == 1) {
                component = txtChietKhau;
            }
            if (row == 2) {
                component = cboLoaiThue;
            }
            if (row == 3) {
                component = txtTongCong;
            }
            if (row == 4) {
                component = txtTientraLai;
            }

        }
        if (column == 3) {

            if (row == 1) {
                component = txtGiaTriCK;
            }
            if (row == 2) {
                component = txtTienThieu;
            }
            if (row == 3) {
                component = txtThanhToan;
            }
            if (row == 4) {
                component = txtTienNo;
            }
        }
        return component;
    }
}

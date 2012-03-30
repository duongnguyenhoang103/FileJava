/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.pilot.sb23.ui.panel;

import java.awt.Component;
import javax.swing.AbstractCellEditor;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableCellEditor;

/**
 *
 * @author DONGTAM
 */
public class ExtentionSB23CellPhuCap_Thuong extends AbstractCellEditor implements TableCellEditor {

    private JTextField txtInsurance;// BHYT
    private JTextField txtMedical;//BHXH
    private JTextField txtRevenues;//% Doanh thu
    private JTextField txtProfit;// %loi nhuan
    private Component component;

    public ExtentionSB23CellPhuCap_Thuong() {
        txtInsurance = new JTextField();
        txtMedical = new JTextField();
        txtRevenues = new JTextField();
        txtProfit = new JTextField();
    }
    
    public void reset() {
        txtInsurance.setText("");
        txtMedical.setText("");
        txtRevenues.setText("");
        txtProfit.setText("");
    }

    @Override
    public Object getCellEditorValue() {
        try {
            if (component == txtInsurance) {
                return txtInsurance.getText();
            } else if (component == txtMedical) {
                return txtMedical.getText();
            } else if (component == txtRevenues) {
                return txtRevenues.getText();
            } else if (component == txtProfit) {
                return txtProfit.getText();
            }
            return null;

        } catch (Exception e) {
            return "";
        }
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected,
            int row,
            int column) {
        if (column == 1) {
            if (row == 0) {
                component = txtInsurance;
            }
            if (row == 1) {
                component = txtMedical;
            }
        }
        if (column == 3) {
            if (row == 0) {
                component = txtRevenues;
            }
            if (row == 1) {
                component = txtProfit;
            }
        }
        return component;
    }
}
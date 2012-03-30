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
public class ExtentionSB23CellExcutePhucap_Thuong extends AbstractCellEditor implements TableCellEditor {

    private JTextField txtInsurance[];// BHYT
    private JTextField txtRevenues[];//% Doanh thu
    private Component component;
    private int n;

    public ExtentionSB23CellExcutePhucap_Thuong(int n) {
        this.n = n;
        txtInsurance = new JTextField[n];
        txtRevenues = new JTextField[n];
        for (int i = 0; i < n; i++) {
            txtInsurance[i] = new JTextField();
            txtRevenues[i] = new JTextField();
        }

    }

    public void reset() {
        for (int i = 0; i < n; i++) {
            txtInsurance[i].setText("");
            txtRevenues[i].setText("");
        }
    }

    @Override
    public Object getCellEditorValue() {
        for (int i = 0; i < n; i++) {
            if (component == txtInsurance[i]) {
                return txtInsurance[i].getText();
            }
            if (component == txtRevenues[i]) {
                return txtRevenues[i].getText();
            }
        }
        return "";
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        for (int i = 0; i < n; i++) {
            if (i == row) {
                if (column == 1) {
                    component = txtInsurance[i];
                }
                if (column == 3) {
                    component = txtRevenues[i];
                }
            }
        }
        return component;
    }
}

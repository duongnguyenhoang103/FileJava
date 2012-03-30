/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.pilot.subenterprise.ui.panel;

import java.awt.Component;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;
import javax.swing.AbstractCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableCellEditor;
import vn.com.hkt.pilot.sbenterprise.entity.BusinessArea;
import vn.com.hkt.pilot.subenterprise.dao.BusinessAreaBN;

/**
 *
 * @author VietAnh
 */
public class ExtEnterpriseSB11Cell extends AbstractCellEditor implements TableCellEditor, ItemListener {

    private Component component;
    private JComboBox cbo[];
    private DefaultComboBoxModel model;
    private JTextField txt[];
    private BusinessAreaBN businessBN = new BusinessAreaBN();
    private int n;

    public ExtEnterpriseSB11Cell(int n) {
        cbo = new JComboBox[n];
        txt = new JTextField[n];

        model = new DefaultComboBoxModel();
        //model.addElement(""); 
        List<BusinessArea> list = businessBN.selectAll();
        if (!list.isEmpty()) {
            for (BusinessArea b : list) {
                model.addElement(b);
            }
        }

        for (int i = 0; i < n; i++) {
            cbo[i] = new JComboBox(model);
            if (cbo[i].getItemAt(0) != null) {
                cbo[i].setSelectedIndex(0);
            }
            cbo[i].addItemListener(this);
            txt[i] = new JTextField("");
        }
        this.n = n;
    }

    public void reset() {
        for (int i = 0; i < n; i++) {
            txt[i].setText("");
        }
    }

    @Override
    public Object getCellEditorValue() {
        for (int i = 0; i < n; i++) {
            if (component == cbo[i]) {
                return cbo[i].getSelectedItem();
            }
            if (component == txt[i]) {
                return txt[i].getText();
            }
        }
        return "";
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        for (int i = 1; i <= n; i++) {
            if (column == 0) {
                component = cbo[i - 1];
            }
            if (column == 1) {
                component = txt[i - 1];
            }
        }
        return component;

    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        JComboBox cbx = (JComboBox) e.getSource();
        for (int i = 0; i < n; i++) {
            if (cbx == cbo[i]) {
                BusinessArea b = (BusinessArea) cbo[i].getSelectedItem();
                txt[i].setText(b.getDescription());
            }

        }
    }
}

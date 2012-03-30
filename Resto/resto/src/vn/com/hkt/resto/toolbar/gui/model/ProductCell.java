/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.resto.toolbar.gui.model;

import java.awt.Component;
import javax.swing.AbstractCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

/**
 *
 * @author VietAnh
 */
public class ProductCell extends AbstractCellEditor implements TableCellEditor {
    private int n ;
    private Component component;
    private JComboBox cbo[];
    private DefaultComboBoxModel model[] ;
    
    public ProductCell(int n ){
        this.n = n; 
        model = new DefaultComboBoxModel[n];
        if (n> 0) 
            for (int i=0;i<n;i++){
                model[i] = new DefaultComboBoxModel();
                model[i].addElement("Lua chon");
                model[i].addElement("100");
                model[i].addElement("200");
                model[i].addElement("300");
                 model[i].addElement("400");
            }
        cbo = new JComboBox[n];
        if (n> 0) 
            for (int i=0;i<n;i++){
                cbo[i] = new JComboBox(model[i]);
                cbo[i].setSelectedIndex(0);
            }
    }
    @Override
    public Object getCellEditorValue() {
        for (int i=0;i<n;i++)
            if (component == cbo[i]){
                return cbo[i].getSelectedItem();
            }
        return "";
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        for (int i=0;i<n;i++)
            if (i == row ){
                if (column == 3){
                    component = cbo[i];
                }
            }
        return component;
    }
    
}

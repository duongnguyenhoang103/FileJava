/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.pilot.sb31.ui.panel.ext_d_w41;

import com.toedter.calendar.JDateChooser;
import java.awt.Component;
import java.text.SimpleDateFormat;
import javax.swing.AbstractCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

/**
 *
 * @author VietAnh
 */
public class ExtListDepartmentW41Cell extends AbstractCellEditor implements TableCellEditor{
     private JComponent component;
     private JDateChooser d1 , d2 , d3 ,d4;
     private DefaultComboBoxModel model ;
     private JComboBox cbo ;
     
     public ExtListDepartmentW41Cell(){
         d1 = new JDateChooser();
         d2 = new JDateChooser();
         d3 = new JDateChooser();
         d4 = new JDateChooser();
         
         model = new DefaultComboBoxModel();
         model.addElement(" ");
         model.addElement("Hoàn thành");
         model.addElement("Chưa hoàn thành");
         
         cbo = new JComboBox(model);
                 
     }

    @Override
    public Object getCellEditorValue() {
        SimpleDateFormat sdf =
          new SimpleDateFormat("dd-MM-yyyy");
    
        try {
            if (component == d1) return sdf.format(d1.getDate());
            else if (component == d2) return sdf.format(d2.getDate());
            else if (component == d3) return sdf.format(d3.getDate());
            else if (component == d4) return sdf.format(d4.getDate());
            else return cbo.getSelectedItem() ;
            
        } catch(Exception e){
            return null ;
        }
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        if (column == 5) component = d1 ;
        if (column == 6) component = d2 ;
        if (column == 7) component = d3 ;
        if (column == 8) component = d4 ;
        if (column == 3) component = cbo ;
        return component;
    }
     
}

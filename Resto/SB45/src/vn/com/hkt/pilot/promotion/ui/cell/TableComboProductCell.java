/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.pilot.promotion.ui.cell;

import java.awt.Component;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.ListCellRenderer;
import javax.swing.table.AbstractTableModel;
import org.openide.util.Lookup;
import vn.com.hkt.basic.api.IProductBN;
import vn.com.hkt.pilot.entities.Product;

/**
 *
 * @author khangpn
 */
public class TableComboProductCell extends JTable implements ListCellRenderer{

    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
       
        setModel(new RowTableModel(value.toString()));
        if (isSelected) {
            getSelectionModel().setSelectionInterval(0, 0);
        }
        return this;
        
    }
    
    private static class RowTableModel extends AbstractTableModel {
               
        private String row;

        public RowTableModel(String row) {
            this.row = row;            
        }

        @Override
        public int getRowCount() {
            return 1;
        }

        @Override
        public int getColumnCount() {
            return 3;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            Product p = getProduct(row);
            switch (columnIndex) {
                case 0:
                    return p.getProductId();
                case 1:
                    return p.getProductName();
                case 2:
                    return p.getProductGroupIdActual();
            }
            return null;
        }
    }
    
    public static Product getProduct(String strId){
        IProductBN productBN = null;
        productBN = Lookup.getDefault().lookup(IProductBN.class);
        return productBN.getByObjectId(strId);        
    }
    
}

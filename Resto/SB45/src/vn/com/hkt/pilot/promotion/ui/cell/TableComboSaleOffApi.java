/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.pilot.promotion.ui.cell;

import java.awt.Component;
import java.util.List;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;
import javax.swing.table.AbstractTableModel;
import vn.com.hkt.pilot.promotion.entity.Promotion;
import vn.com.hkt.pilot.promotion.entity.SaleOff;

/**
 *
 * @author khangpn
 */
public class TableComboSaleOffApi extends JTable implements ListCellRenderer {

    public TableComboSaleOffApi() {
        setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
    }

    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        setModel(new RowTableModel((Promotion) value));
        if (isSelected) {
            getSelectionModel().setSelectionInterval(0, 0);
        }
        return this;
    }

    private static class RowTableModel extends AbstractTableModel {

        private Promotion row;

        public RowTableModel(Promotion row) {
            this.row = row;
        }

        @Override
        public int getRowCount() {
            return 1;
        }

        @Override
        public int getColumnCount() {
            return 4;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            SaleOff saleOff = new SaleOff();
            List<SaleOff> saleOffs = saleOff.getAccessDataOfEntity().select(saleOff.FIELD_PROMOTION_ID_ACTUAL,
                    String.valueOf(row.getId()));
            if (!saleOffs.isEmpty()) {
                saleOff = saleOffs.get(0);
                if (saleOff != null) {
                    switch (columnIndex) {
                        case 0:
                            return row.getPromotionID();
                        case 1:
                            return row.getPromotionName();
                        case 2:
                            return saleOff.getValue() == 0 ? saleOff.getRealValue() : saleOff.getValue();
                        case 3:
                            return saleOff.getValue() == 0 ? "Tiền mặt" : "%";
                    }
                }
            }

            return null;
        }
    }
}

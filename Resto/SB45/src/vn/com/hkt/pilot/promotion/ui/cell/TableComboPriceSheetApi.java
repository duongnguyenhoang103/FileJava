/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.pilot.promotion.ui.cell;

import java.awt.Component;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;
import javax.swing.table.AbstractTableModel;
import vn.com.hkt.pilot.sb42.pricesheeta.entity.PriceSheet;

/**
 *
 * @author khangpn
 */
public class TableComboPriceSheetApi extends JTable implements ListCellRenderer {

    public TableComboPriceSheetApi() {
        setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
    }

    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        setModel(new RowTableModel((PriceSheet)value));
        if (isSelected) {
            getSelectionModel().setSelectionInterval(0, 0);
        }
        return this;
    }

    private static class RowTableModel extends AbstractTableModel {

        private PriceSheet row;

        public RowTableModel(PriceSheet row) {
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
            switch (columnIndex) {
                case 0:
                    return row.getPricesheetID();
                case 1:
                    return row.getPricesheetName();
                case 2:
                    return row.getExportUnit();
            }
            return null;
        }
    }
}

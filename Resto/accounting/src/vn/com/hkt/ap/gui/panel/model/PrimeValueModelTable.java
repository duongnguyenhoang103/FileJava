/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.ap.gui.panel.model;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import vn.com.hkt.ap.data.BusinessCycle;

/**
 *
 * @author Admin
 */
public class PrimeValueModelTable extends AbstractTableModel {

    private List<BusinessCycle> businessCycles;// danh sách các kỳ kinh doanh
    private String[] header = new String[]{"Thời gian", "Giá trị đầu kì", "Phát sinh tăng", "Phát sinh giảm", " giá trị cuối kì"};

    public PrimeValueModelTable(List<BusinessCycle> businessCycles) {
        this.businessCycles = businessCycles;
    }

    @Override
    public String getColumnName(int column) {
        return header[column];
    }

    @Override
    public int getRowCount() {
        return businessCycles.size();
    }

    @Override
    public int getColumnCount() {
        return header.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return businessCycles.get(rowIndex).getStartTime()
                        + " ->" + businessCycles.get(rowIndex).getFinishTime();
            case 1:
                return businessCycles.get(rowIndex).getFirstValue();
            case 2:
                return businessCycles.get(rowIndex).getIncreaseValue();
            case 3:
                return businessCycles.get(rowIndex).getDecreaseValue();
            case 4:
                return businessCycles.get(rowIndex).getLastValue();
            default:
                return "";
        }
    }
}

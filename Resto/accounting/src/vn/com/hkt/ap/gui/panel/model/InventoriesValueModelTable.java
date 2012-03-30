/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.ap.gui.panel.model;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import vn.com.hkt.ap.data.ProductStockCycle;

/**
 *
 * @author Admin
 */
public class InventoriesValueModelTable extends AbstractTableModel {

    private String[] header = new String[]{"Thời gian",
        "<html>Số lượng<br>đầu kỳ</html>",
        "<html>Đơn giá trung<br>bình đầu kỳ</html>",
        "<html>Giá trị<br>đầu kỳ</html>",
        "<html>Giá trị thực<br>đầu kỳ</html>",
        "<html>Số lương<br>tăng trong kỳ</html>",
        "<html>Số lượng<br>giảm trong kỳ</html>",
        "<html>Số lượng <br>còn cuối kỳ</htm>",
        "<html>Giá trị<br>tăng thực<br>trong kỳ</html>",
        "<html>Giá trị<br>giảm thực<br>trong kỳ</html>",
        "<html>Đơn giá<br>trung bình<br>trong kỳ</html>",
        "<html>Giá trị<br>tồn thực<br>cuối kỳ</html>",
        "<html>Giá trị<br>cuối kỳ</html>"};
    private List<ProductStockCycle> cycles;// danh sách các kỳ nhập xuất 

    public InventoriesValueModelTable(List<ProductStockCycle> cycles) {
        this.cycles = cycles;
    }

    @Override
    public String getColumnName(int column) {
        return header[column];
    }

    @Override
    public int getRowCount() {
        return cycles.size();
    }

    @Override
    public int getColumnCount() {
        return header.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return cycles.get(rowIndex).getStartTime() + "->" + cycles.get(rowIndex).getFinishTime();
            case 1:
                return cycles.get(rowIndex).getFirstQuantity();
            case 2:
                return cycles.get(rowIndex).getPriceAverageFirstCycle();
            case 3:
                return cycles.get(rowIndex).getFirstValueAverage();
            case 4:
                return cycles.get(rowIndex).getFirstValueReal();
            case 5:
                return cycles.get(rowIndex).getIncreaseQuantity();
            case 6:
                return cycles.get(rowIndex).getDecreaseQuantity();
            case 7:
                return cycles.get(rowIndex).getLastQuantity();
            case 8:
                return cycles.get(rowIndex).getIncreaseValueReal();
            case 9:
                return cycles.get(rowIndex).getDecreaseValueReal();
            case 10:
                return cycles.get(rowIndex).getPriceAverageCycle();
            case 11:
                return cycles.get(rowIndex).getLastValueReal();
            case 12:
                return cycles.get(rowIndex).getLastValueAverage();
            default:
                return "";
        }
    }
}

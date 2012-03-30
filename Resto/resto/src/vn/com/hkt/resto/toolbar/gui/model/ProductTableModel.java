/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.resto.toolbar.gui.model;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import vn.com.hkt.pilot.entities.Product;

/**
 *
 * @author duong
 */
public class ProductTableModel extends AbstractTableModel {

    private String[] header = new String[]{"STT", "Tên sản phẩm", " Số lượng", "Đơn giá", "Tiền"};
    private List<Product> listProducts;
    private List<Integer> slg;

    public ProductTableModel(List<Product> listProducts, List<Integer> slg) {
        this.listProducts = listProducts;
        this.slg = slg;
    }      

    @Override
    public String getColumnName(int column) {
        return header[column];
    }

    @Override
    public int getRowCount() {
        return listProducts.size();
    }

    @Override
    public int getColumnCount() {
        return header.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return rowIndex + 1;
            case 1:
                return listProducts.get(rowIndex).getProductName();
            case 2:
                return slg.get(rowIndex);
            case 3:
                return "Đơn giá";
            case 4:
                return "Tiền";
            default:
                return "";
        }
    }
}

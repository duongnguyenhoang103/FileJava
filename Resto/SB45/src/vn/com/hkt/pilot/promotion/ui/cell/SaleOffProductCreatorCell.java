/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.pilot.promotion.ui.cell;

import java.awt.Component;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.table.TableCellEditor;
import org.openide.util.Lookup;
import vn.com.hkt.basic.api.IProductBN;
import vn.com.hkt.pilot.entities.Product;
import vn.com.hkt.pilot.promotion.ui.panel.SaleOffProductCreatorPanel;

/**
 *
 * @author khangpn
 */
public class SaleOffProductCreatorCell extends AbstractCellEditor implements ItemListener,
        TableCellEditor, CaretListener {

    private JComboBox cboPrimeProduct, cboSubProduct;
    private JTextField txtProductPrimeNum, txtProductSubNum,txtNote;
    private DefaultComboBoxModel cboPrimeProductModel, cboSubProductModel;
    private SaleOffProductCreatorPanel saleOffProductCreatorPanel;
    private IProductBN productBN;
    private Component component;

    public SaleOffProductCreatorCell(SaleOffProductCreatorPanel saleOffProductCreatorPanel) {
        this.saleOffProductCreatorPanel = saleOffProductCreatorPanel;
        productBN = Lookup.getDefault().lookup(IProductBN.class);

        txtProductPrimeNum = new JTextField();
        txtProductPrimeNum.addCaretListener(this);
        
        txtNote = new JTextField();
        txtNote.addCaretListener(this);

        txtProductSubNum = new JTextField();
        txtProductSubNum.addCaretListener(this);

        cboPrimeProductModel = new DefaultComboBoxModel();
        cboSubProductModel = new DefaultComboBoxModel();

        fillCombo();

        cboPrimeProduct = new JComboBox(cboPrimeProductModel);
        cboPrimeProduct.addItemListener(this);
        cboSubProduct = new JComboBox(cboSubProductModel);
        cboSubProduct.addItemListener(this);
    }

    public void fillCombo() {
        cboPrimeProductModel.removeAllElements();
        cboSubProductModel.removeAllElements();
        List<Product> products = new ArrayList<Product>();
        if (productBN != null) {
            products = productBN.selectAll();
            if (!products.isEmpty()) {
                for (Product product : products) {
                    cboPrimeProductModel.addElement(product.getProductId());
                    cboSubProductModel.addElement(product.getProductId());
                }
            }
        }

    }

    @Override
    public Object getCellEditorValue() {
        if (component instanceof JComboBox) {
            JComboBox comboBox = (JComboBox) component;
            return comboBox.getSelectedItem();
        }
        if(component instanceof JTextField){
            JTextField textField = (JTextField)component;
            return textField.getText().trim();
        }
        return null;
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        switch (column) {
            case 0:
                component = cboPrimeProduct;
                break;
            case 2:
                component = cboSubProduct;
                break;
            case 4:
                component = txtProductPrimeNum;
                break;
            case 5:
                component = txtProductSubNum;
                break;
            case 6:
                component = txtNote;
                break;
            default:
                component = null;
        }
        return component;
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        int row = saleOffProductCreatorPanel.getTblSaleOffProductViewer().getSelectedRow();
        if (row >= 0) {
            if (e.getSource() == cboPrimeProduct) {
                Product product = new Product();
                String id = cboPrimeProduct.getSelectedItem().toString();
                if (id.trim().length() != 0) {
                    product = productBN.getByObjectId(id);
                    if (product != null) {
                        saleOffProductCreatorPanel.getTblSaleOffProductViewer().
                                setValueAt(product.getProductName(), row, 1);
                        saleOffProductCreatorPanel.getSaleOffProduct().
                                setProductMainIdActual(product.getId());
                    }
                }
            }
            if (e.getSource() == cboSubProduct) {
                Product product = new Product();
                String id = cboSubProduct.getSelectedItem().toString();
                if (id.trim().length() != 0) {
                    product = productBN.getByObjectId(id);
                    if (product != null) {
                        saleOffProductCreatorPanel.getTblSaleOffProductViewer().
                                setValueAt(product.getProductName(), row, 3);
                        saleOffProductCreatorPanel.getSaleOffProduct().
                                setProductSubIdActual(product.getId());
                    }
                }
            }
        }

    }

    @Override
    public void caretUpdate(CaretEvent e) {
        if (e.getSource() == txtProductPrimeNum) {
            try {
                saleOffProductCreatorPanel.getSaleOffProduct().setProductMainNum(
                        Integer.parseInt(txtProductPrimeNum.getText()));
            }catch(Exception e1){
                return;
            }
        }
        if (e.getSource() == txtProductSubNum) {
            try {
                saleOffProductCreatorPanel.getSaleOffProduct().setSaleOffProductNum(
                        Integer.parseInt(txtProductSubNum.getText()));
            }catch(Exception e2){
                return;
            }
        }
        
        if (e.getSource() == txtNote) {
            try {
                saleOffProductCreatorPanel.getSaleOffProduct().setNote(txtNote.getText());
            }catch(Exception e2){
                return;
            }
        }
    }
}

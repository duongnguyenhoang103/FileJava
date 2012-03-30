/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.pilot.sb42.pricesheet.ui.panel;

import com.toedter.calendar.JDateChooser;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import vn.com.hkt.basic.api.IClassificationBN;
import vn.com.hkt.basic.api.IProductBN;
import vn.com.hkt.pilot.entities.Classification;
import vn.com.hkt.pilot.entities.Product;

/**
 *
 * @author khangutc
 */
public class PricesheetTableCell extends AbstractCellEditor implements TableCellEditor,
        ActionListener, ItemListener {

    private Component component = null;
    private JTextField txtPricesheetName, txtPricesheetId, txtExportUnit, txtAmpliTude,
            txtExportMin, txtExportMax, txtUnEqual;
    private JComboBox cboTypeOfTaxes, cboNumPercent, cboClassification, cboUnitMoneyOfExportUnit,
            cboUnitMoneyOfExportMin, cboUnitMoneyOfExportMax, cboSymmetricalPrice, cboListProduct;
    private JDateChooser dateChooserFrom, dateChooserTo;
    private DefaultComboBoxModel cboModelTypeOfTaxes, cboModelNumPercent, cboModelClassification,
            cboModelUnitMoneyOfExportUnit, cboModelUnitMoneyOfExportMin, cboModelUnitMoneyOfExportMax,
            cboModelSymmetricalPrice, cboListProductModel;
    private IProductBN productBN;
    private PriceSheetCreatorPanel priceSheetCreatorPanel;

    public PricesheetTableCell(PriceSheetCreatorPanel priceSheetCreatorPanel) {

        this.priceSheetCreatorPanel = priceSheetCreatorPanel;

        productBN = Lookup.getDefault().lookup(IProductBN.class);

        txtAmpliTude = new JTextField();
        txtAmpliTude.addCaretListener(new CaretListener() {

            @Override
            public void caretUpdate(CaretEvent e) {
                pressedAmpliTude();
            }
        });
        txtExportMax = new JTextField();
        txtExportMin = new JTextField();
        txtExportUnit = new JTextField();
        txtExportUnit.addCaretListener(new CaretListener() {

            @Override
            public void caretUpdate(CaretEvent e) {
                pressedExportUnit();
            }
        });
        txtPricesheetId = new JTextField();
        txtPricesheetName = new JTextField();
        txtUnEqual = new JTextField();

        loadCombo();

        cboClassification = new JComboBox(cboModelClassification);
        if (cboModelClassification.getSize() != 0) {
            cboClassification.setSelectedIndex(0);
        }

        cboNumPercent = new JComboBox(cboModelNumPercent);
        if (cboModelNumPercent.getSize() != 0) {
            cboNumPercent.setSelectedIndex(0);
        }
        cboNumPercent.addActionListener(this);

        cboSymmetricalPrice = new JComboBox(cboModelSymmetricalPrice);
        if (cboModelSymmetricalPrice.getSize() != 0) {
            cboSymmetricalPrice.setSelectedIndex(0);
        }
        cboSymmetricalPrice.addItemListener(this);

        cboTypeOfTaxes = new JComboBox(cboModelTypeOfTaxes);
        if (cboModelTypeOfTaxes.getSize() != 0) {
            cboTypeOfTaxes.setSelectedIndex(0);
        }

        cboUnitMoneyOfExportMax = new JComboBox(cboModelUnitMoneyOfExportMax);
        if (cboModelUnitMoneyOfExportMax.getSize() != 0) {
            cboUnitMoneyOfExportMax.setSelectedIndex(0);
        }

        cboUnitMoneyOfExportMin = new JComboBox(cboModelUnitMoneyOfExportMin);
        if (cboModelUnitMoneyOfExportMin.getSize() != 0) {
            cboUnitMoneyOfExportMin.setSelectedIndex(0);
        }

        cboUnitMoneyOfExportUnit = new JComboBox(cboModelUnitMoneyOfExportUnit);
        if (cboModelUnitMoneyOfExportUnit.getSize() != 0) {
            cboUnitMoneyOfExportUnit.setSelectedIndex(0);
        }

        cboListProduct = new JComboBox(cboListProductModel);
        if (cboListProductModel.getSize() != 0) {
            cboListProduct.setSelectedIndex(0);
        }
        cboListProduct.addItemListener(this);

        dateChooserFrom = new JDateChooser();
        dateChooserFrom.setDateFormatString("dd/MM/yyyy");
        dateChooserTo = new JDateChooser();
        dateChooserTo.setDateFormatString("dd/MM/yyyy");

    }

    public void loadCombo() {
        IClassificationBN classificationBN = Lookup.getDefault().lookup(IClassificationBN.class);
        List<Classification> classifications = new ArrayList<Classification>();
        List<Product> products = new ArrayList<Product>();

        products = productBN.selectAll();
        classifications = classificationBN.selectAll();

        cboModelClassification = new DefaultComboBoxModel();
        if (!classifications.isEmpty()) {
            for (Classification classification : classifications) {
                cboModelClassification.addElement(classification);
            }
        }

        cboListProductModel = new DefaultComboBoxModel();
        cboListProductModel.addElement(" ");
        if (!products.isEmpty()) {
            for (Product p : products) {
                cboListProductModel.addElement(p.getProductId());
            }
        }

        cboModelNumPercent = new DefaultComboBoxModel();
        cboModelNumPercent.addElement("%");
        cboModelNumPercent.addElement("Số");

        cboModelSymmetricalPrice = new DefaultComboBoxModel();
        cboModelSymmetricalPrice.addElement("Không");
        cboModelSymmetricalPrice.addElement("Có");

        cboModelTypeOfTaxes = new DefaultComboBoxModel();
        for (int i = 0; i <= 50; i += 5) {
            cboModelTypeOfTaxes.addElement(i);
        }

        cboModelUnitMoneyOfExportMax = new DefaultComboBoxModel();
        cboModelUnitMoneyOfExportMax.addElement("VND");
        cboModelUnitMoneyOfExportMax.addElement("USD");

        cboModelUnitMoneyOfExportMin = new DefaultComboBoxModel();
        cboModelUnitMoneyOfExportMin.addElement("VND");
        cboModelUnitMoneyOfExportMin.addElement("USD");

        cboModelUnitMoneyOfExportUnit = new DefaultComboBoxModel();
        cboModelUnitMoneyOfExportUnit.addElement("VND");
        cboModelUnitMoneyOfExportUnit.addElement("USD");

    }

    @Override
    public Object getCellEditorValue() {
        JTextField textField = null;
        JComboBox comboBox = null;
        JDateChooser dateChooser = null;
        Object object = null;
        if (component instanceof JTextField) {
            textField = (JTextField) component;
            object = textField.getText();
        }
        if (component instanceof JComboBox) {
            comboBox = (JComboBox) component;
            object = comboBox.getSelectedItem();
        }
        if (component instanceof JDateChooser) {
            dateChooser = (JDateChooser) component;
            JTextField field = (JTextField) dateChooser.getDateEditor().getUiComponent();
            object = field.getText();
        }
        return object;
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        if (column == 1) {
            if (row == 0) {
                component = txtPricesheetName;
            } else if (row == 1) {
                component = txtPricesheetId;
            } else if (row == 2) {
                component = cboClassification;
            } else if (row == 3) {
                component = txtExportUnit;
            } else if (row == 4) {
                component = txtAmpliTude;
            } else if (row == 5) {
                component = txtExportMin;
            } else if (row == 6) {
                component = txtExportMax;
            } else if (row == 7) {
                component = dateChooserFrom;
            } else if (row == 8) {
                component = cboSymmetricalPrice;
            } else {
                component = null;
            }
        } else if (column == 3) {
            if (row == 0) {
                component = cboListProduct;
            } else if (row == 2) {
                component = cboTypeOfTaxes;
            } else if (row == 3) {
                component = cboUnitMoneyOfExportUnit;
            } else if (row == 5) {
                component = cboUnitMoneyOfExportMin;
            } else if (row == 6) {
                component = cboUnitMoneyOfExportMax;
            } else if (row == 7) {
                component = dateChooserTo;
            } else if (row == 8) {
                component = txtUnEqual;
            } else {
                component = null;
            }
        } else if (column == 2) {
            if (row == 4) {
                component = cboNumPercent;
            } else {
                component = null;
            }
        } else {
            component = null;
        }
        return component;
    }

    public JDateChooser getDateChooserFrom() {
        return dateChooserFrom;
    }

    public void setDateChooserFrom(JDateChooser dateChooserFrom) {
        this.dateChooserFrom = dateChooserFrom;
    }

    public JDateChooser getDateChooserTo() {
        return dateChooserTo;
    }

    public void setDateChooserTo(JDateChooser dateChooserTo) {
        this.dateChooserTo = dateChooserTo;
    }

    public JComboBox getCboClassification() {
        return cboClassification;
    }

    public void setCboClassification(JComboBox cboClassification) {
        this.cboClassification = cboClassification;
    }

    public JComboBox getCboNumPercent() {
        return cboNumPercent;
    }

    public void setCboNumPercent(JComboBox cboNumPercent) {
        this.cboNumPercent = cboNumPercent;
    }

    public JComboBox getCboSymmetricalPrice() {
        return cboSymmetricalPrice;
    }

    public void setCboSymmetricalPrice(JComboBox cboSymmetricalPrice) {
        this.cboSymmetricalPrice = cboSymmetricalPrice;
    }

    public JComboBox getCboTypeOfTaxes() {
        return cboTypeOfTaxes;
    }

    public void setCboTypeOfTaxes(JComboBox cboTypeOfTaxes) {
        this.cboTypeOfTaxes = cboTypeOfTaxes;
    }

    public JComboBox getCboUnitMoneyOfExportMax() {
        return cboUnitMoneyOfExportMax;
    }

    public void setCboUnitMoneyOfExportMax(JComboBox cboUnitMoneyOfExportMax) {
        this.cboUnitMoneyOfExportMax = cboUnitMoneyOfExportMax;
    }

    public JComboBox getCboUnitMoneyOfExportMin() {
        return cboUnitMoneyOfExportMin;
    }

    public void setCboUnitMoneyOfExportMin(JComboBox cboUnitMoneyOfExportMin) {
        this.cboUnitMoneyOfExportMin = cboUnitMoneyOfExportMin;
    }

    public JComboBox getCboUnitMoneyOfExportUnit() {
        return cboUnitMoneyOfExportUnit;
    }

    public void setCboUnitMoneyOfExportUnit(JComboBox cboUnitMoneyOfExportUnit) {
        this.cboUnitMoneyOfExportUnit = cboUnitMoneyOfExportUnit;
    }

    public Component getComponent() {
        return component;
    }

    public void setComponent(Component component) {
        this.component = component;
    }

    public JTextField getTxtAmpliTude() {
        return txtAmpliTude;
    }

    public void setTxtAmpliTude(JTextField txtAmpliTude) {
        this.txtAmpliTude = txtAmpliTude;
    }

    public JTextField getTxtExportMax() {
        return txtExportMax;
    }

    public void setTxtExportMax(JTextField txtExportMax) {
        this.txtExportMax = txtExportMax;
    }

    public JTextField getTxtExportMin() {
        return txtExportMin;
    }

    public void setTxtExportMin(JTextField txtExportMin) {
        this.txtExportMin = txtExportMin;
    }

    public JTextField getTxtExportUnit() {
        return txtExportUnit;
    }

    public void setTxtExportUnit(JTextField txtExportUnit) {
        this.txtExportUnit = txtExportUnit;
    }

    public JTextField getTxtPricesheetId() {
        return txtPricesheetId;
    }

    public void setTxtPricesheetId(JTextField txtPricesheetId) {
        this.txtPricesheetId = txtPricesheetId;
    }

    public JTextField getTxtPricesheetName() {
        return txtPricesheetName;
    }

    public void setTxtPricesheetName(JTextField txtPricesheetName) {
        this.txtPricesheetName = txtPricesheetName;
    }

    public JTextField getTxtUnEqual() {
        return txtUnEqual;
    }

    public void setTxtUnEqual(JTextField txtUnEqual) {
        this.txtUnEqual = txtUnEqual;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cboNumPercent) {
            pressedAmpliTude();
            pressedExportUnit();
        }
    }

    protected void pressedExportUnit() {
        if (txtExportUnit.getText().trim().length() != 0) {
            if (txtAmpliTude.getText().trim().length() != 0) {
                float i = Float.parseFloat(txtAmpliTude.getText());
                float j = Float.parseFloat(txtExportUnit.getText());
                float value = 0;
                if (cboModelNumPercent.getSelectedItem().equals("%")) {
                    value = j * i / 100;
                } else {
                    value = i;
                }
                txtExportMax.setText(String.valueOf(j + value));
                txtExportMin.setText(String.valueOf(j - value));
            }
        }
    }

    protected void pressedAmpliTude() {
        if (txtAmpliTude.getText().trim().length() != 0) {
            float i = Float.parseFloat(txtAmpliTude.getText());
            if (txtExportUnit.getText().trim().length() != 0) {
                float j = Float.parseFloat(txtExportUnit.getText());
                float value = 0;
                if (cboModelNumPercent.getSelectedItem().equals("%")) {
                    value = j * i / 100;
                } else {
                    value = i;
                }
                txtExportMax.setText(String.valueOf(j + value));
                txtExportMin.setText(String.valueOf(j - value));
            }
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getSource() == cboListProduct) {
            String str = cboListProduct.getSelectedItem().toString();
            if (cboListProduct.getSelectedIndex() != -1) {
                if (str.trim().length() != 0) {
                    String id = cboListProduct.getSelectedItem().toString();
                    Product product = productBN.getByObjectId(id);
                    priceSheetCreatorPanel.setProductIdActual(product.getId());
                    priceSheetCreatorPanel.getTblPricesheet().setValueAt(product.getProductName(), 1, 3);
                } else {
                    priceSheetCreatorPanel.getTblPricesheet().setValueAt(" ", 1, 3);
                }
            }

        }
        if (e.getSource() == cboSymmetricalPrice) {
            if (cboSymmetricalPrice.getSelectedIndex() == 1) {
                priceSheetCreatorPanel.setSymmetricalPrice(true);
            } else {
                priceSheetCreatorPanel.setSymmetricalPrice(false);
            }
        }
    }
}

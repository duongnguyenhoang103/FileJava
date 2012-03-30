/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.pilot.sb41.product.extW48.ui.panel;

import com.toedter.calendar.JDateChooser;
import java.awt.Component;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableCellEditor;
import org.openide.util.Lookup;
import vn.com.hkt.basic.api.ICityBN;
import vn.com.hkt.basic.api.ICountryBN;
import vn.com.hkt.pilot.entities.City;
import vn.com.hkt.pilot.entities.Country;
import vn.com.hkt.pilot.sb41.product.extW48.dao.ProductStatusBN;
import vn.com.hkt.pilot.sb41.product.extW48.entity.ProductStatus;

/**
 *
 * @author VietAnh
 */
public class ExtProductW48Cell extends AbstractCellEditor implements TableCellEditor, ItemListener {

    private JTextField txtNameEnglish, txtDescriptiveNote;
    private JComboBox cboCountry;  // Nuoc san xuat
    private JComboBox cboCity, cboPrice, cboPromotion, cboState;
    private JDateChooser dateOfProduction, dateEdit, expiryDate;
    private DefaultComboBoxModel modelCountry, modelCity, modelPrice, modelPromotion, modelState;
    private Component component;
    private ProductStatusBN dao = new ProductStatusBN();
    private ICountryBN countryBN;
    private ICityBN cityBN;
    private List<ProductStatus> list = new ArrayList<ProductStatus>();
    private List<Country> listCountry = new ArrayList<Country>();

    public ExtProductW48Cell() {
        countryBN = Lookup.getDefault().lookup(ICountryBN.class);
        cityBN = Lookup.getDefault().lookup(ICityBN.class);

        modelCountry = new DefaultComboBoxModel();
        modelCountry.addElement(" ");
        listCountry = countryBN.selectAll();
        for (Country c : listCountry) {
            modelCountry.addElement(c);
        }

        modelCity = new DefaultComboBoxModel();
        modelCity.addElement(" ");

        modelPrice = new DefaultComboBoxModel();
        modelPrice.addElement("Bảng giá lấy sau");
        modelPrice.addElement(" Bảng giá 1");
        modelPrice.addElement(" BG 2");
        modelPrice.addElement(" BG 3");

        modelPromotion = new DefaultComboBoxModel();
        modelPromotion.addElement("Khuyến Mại lấy sau");
        modelPromotion.addElement(" KMai 1");
        modelPromotion.addElement(" Kmai 2");
        modelPromotion.addElement(" Kmai 3");

        modelState = new DefaultComboBoxModel();
        modelState.addElement("");

        list = dao.selectAll();
        if (!list.isEmpty()) {
            for (ProductStatus p : list) {
                modelState.addElement(p);
            }
        }

        txtNameEnglish = new JTextField("");
        txtDescriptiveNote = new JTextField("");

        dateOfProduction = new com.toedter.calendar.JDateChooser();
        expiryDate = new com.toedter.calendar.JDateChooser();
        dateEdit = new com.toedter.calendar.JDateChooser();

        cboCountry = new JComboBox(modelCountry);
        cboCity = new JComboBox(modelCity);
        cboPrice = new JComboBox(modelPrice);
        cboPromotion = new JComboBox(modelPromotion);
        cboState = new JComboBox(modelState);

        cboCountry.setSelectedIndex(0);
        cboCity.setSelectedIndex(0);
        cboPrice.setSelectedIndex(0);
        cboPromotion.setSelectedIndex(0);
        cboState.setSelectedIndex(0);

        cboCountry.addItemListener(this);
    }

    public void reset() {
        txtDescriptiveNote.setText("");
        txtNameEnglish.setText("");
    }

    @Override
    public Object getCellEditorValue() {
        SimpleDateFormat sdf =
                new SimpleDateFormat("dd-MM-yyyy");
        try {
            if (component == cboCountry) {
                return cboCountry.getSelectedItem();
            } else if (component == cboCity) {
                return cboCity.getSelectedItem();
            } else if (component == cboPrice) {
                return cboPrice.getSelectedItem();
            } else if (component == cboPromotion) {
                return cboPromotion.getSelectedItem();
            } else if (component == cboState) {
                return cboState.getSelectedItem();
            } else if (component == dateOfProduction) {
                return sdf.format(dateOfProduction.getDate());
            } else if (component == expiryDate) {
                return sdf.format(expiryDate.getDate());
            } else if (component == dateEdit) {
                return sdf.format(dateEdit.getDate());
            } else if (component == txtNameEnglish) {
                return txtNameEnglish.getText();
            }
            return txtDescriptiveNote.getText();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        if (column == 1) {
            if (row == 0) {
                component = txtNameEnglish;
            }
            if (row == 1) {
                component = cboCountry;
            }
            if (row == 2) {
                component = dateOfProduction;
            }
            if (row == 3) {
                component = expiryDate;
            }
            if (row == 4) {
                component = txtDescriptiveNote;
            }
            if (row == 5) {
                component = cboPrice;
            }
        }
        if (column == 3) {
            if (row == 1) {
                component = cboCity;
            }
            if (row == 2) {
                component = dateEdit;
            }
            if (row == 3) {
                component = cboState;
            }
            if (row == 5) {
                component = cboPromotion;
            }
        }
        return component;
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getSource() == cboCountry) {
            modelCity.removeAllElements();
            if (cboCountry.getSelectedIndex() == 0) {
                modelCity.addElement(" ");
                return;
            }
            Country country = (Country) cboCountry.getSelectedItem();
            modelCity.addElement(" ");
            if (country != null) {
                List<City> listCity = cityBN.getCityByCountry(country);
                if (!listCity.isEmpty()) {
                    for (City c : listCity) {
                        modelCity.addElement(c);
                    }
                }
            }

        }
    }

    public void loadCboStatus() {
        list = dao.selectAll();
        modelState.removeAllElements();
        modelState.addElement(" ");
        if (!list.isEmpty()) {
            for (ProductStatus p : list) {
                modelState.addElement(p);
            }
        }
    }

    public void loadCboCoutry() {
        listCountry = countryBN.selectAll();
        modelCountry.removeAllElements();
        modelCountry.addElement(" ");
        for (Country c : listCountry) {
            modelCountry.addElement(c);
        }
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.pilot.sb22.person.extW23.ui.panel;

import java.awt.Component;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.AbstractCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableCellEditor;
import org.openide.util.Lookup;
import org.openide.windows.TopComponent;
import org.openide.windows.WindowManager;
import vn.com.hkt.basic.api.ICityBN;
import vn.com.hkt.basic.api.ICountryBN;
import vn.com.hkt.pilot.entities.City;
import vn.com.hkt.pilot.entities.Country;

/**
 *
 * @author Admin
 */
public final class PersonExtSB22Cell extends AbstractCellEditor implements TableCellEditor, ItemListener {

    private JTextField[] txtAddress;
    private JComboBox[] cboCountry, cboCity;
    private DefaultComboBoxModel modelCity;
    private DefaultComboBoxModel modelCountry;
    private ICityBN cityBN;
    private ICountryBN countryBN;
    private Component component;
    private int n;

    public PersonExtSB22Cell(int n) {
        cityBN = Lookup.getDefault().lookup(ICityBN.class);
        countryBN = Lookup.getDefault().lookup(ICountryBN.class);
        this.n = n;
        txtAddress = new JTextField[n];
        modelCity = new DefaultComboBoxModel();
        modelCountry = new DefaultComboBoxModel();
        modelCountry.addElement("");
        cboCountry = new JComboBox[n];
        cboCity = new JComboBox[n];
         
        for (int i = 0; i < n; i++) {
            cboCountry[i] = new JComboBox(modelCountry);
            cboCity[i] = new JComboBox(modelCity);
            cboCountry[i].addItemListener(this);
            txtAddress[i] = new JTextField("");
        }
        loadCboCountry();


    }

    public void reset() {

        for (int i = 0; i < n; i++) {
            txtAddress[i].setText("");
        }
    }

    @Override
    public Object getCellEditorValue() {

        for (int i = 0; i < n; i++) {
            if (component == txtAddress[i]) {
                return txtAddress[i].getText();
            }
            if (component == cboCountry[i]) {
                return cboCountry[i].getSelectedItem();
            }
            if (component == cboCity[i]) {
                return cboCity[i].getSelectedItem();
            }
        }
        return "";
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        for (int i = 0; i < n; i++) {
            if (i == row) {
                if (column == 1) {
                    component = txtAddress[i];
                }
                if (column == 2) {
                    component = cboCountry[i];
                }
                if (column == 3) {
                    component = cboCity[i];
                }
            }
        }
        return component;
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        JComboBox cbo = (JComboBox) e.getSource();
        for (int i = 0; i < n; i++) {
            if (cbo == cboCountry[i]) {
                if (cboCountry[i].isShowing()) {
                    if (cboCountry[i].getSelectedIndex() != 0) {
                        modelCity.removeAllElements();
                        modelCity.addElement("");
                        cboCity[i].setSelectedIndex(0);
                        cboCity[i].repaint();
                        Country country = (Country) cboCountry[i].getSelectedItem();
                        TopComponent tc = WindowManager.getDefault().findTopComponent("EnterpriseTutorialCreatorTopComponent");
                        if (tc.isShowing() == false) {
                            for (City c : cityBN.getCityByCountry(country)) {
                                modelCity.addElement(c);
                            }
                        }

                    }
                }
            }
        }
    }

    public void loadCboCountry() {
        modelCountry.removeAllElements();
        modelCountry.addElement("");
        for (Country c : countryBN.selectAll()) {
            modelCountry.addElement(c);
        }
      
    }
}

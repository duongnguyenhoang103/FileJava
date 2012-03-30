/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.pilot.subperson.ui.panel;

import com.toedter.calendar.JDateChooser;
import java.awt.Component;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.AbstractCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableCellEditor;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import org.openide.util.Lookup;
import vn.com.hkt.basic.api.ICityBN;
import vn.com.hkt.basic.api.ICountryBN;
import vn.com.hkt.pilot.entities.City;
import vn.com.hkt.pilot.entities.Country;

/**
 *
 * @author VietAnh
 */
public class ExtSB21Cell extends AbstractCellEditor implements TableCellEditor, ItemListener {

    private Component component;
    private JComboBox cboMaritalStatus, cboGender, cboCountry, cboCity;
    private DefaultComboBoxModel modelMaritalStatus, modelGender, modelCountry, modelCity;
    private JDateChooser birthday;
    private JTextField txtIdentityCard, txtHeight, txtPhone, txtEmail, txtAddress, txtWeight, txtChilrenNum,
            txtMobile, txtWeb;
    private JLabel lbl;
    private static int old1 = 0;
    private ICountryBN countryBN;
    private ICityBN cityBN;

    public ExtSB21Cell() {
        countryBN = Lookup.getDefault().lookup(ICountryBN.class);
        modelCountry = new DefaultComboBoxModel();
        List<Country> list = countryBN.selectAll();
        if (!list.isEmpty()) {
            for (Country c : list) {
                modelCountry.addElement(c);
            }
        }
        cboCountry = new JComboBox(modelCountry);

        cityBN = Lookup.getDefault().lookup(ICityBN.class);
        modelCity = new DefaultComboBoxModel();
        List<City> listCity = cityBN.selectAll();
        if (!listCity.isEmpty()) {
            for (City c : listCity) {
                modelCity.addElement(c);
            }
        }
        cboCity = new JComboBox(modelCity);

        modelMaritalStatus = new DefaultComboBoxModel();
        modelMaritalStatus.addElement(" ");
        modelMaritalStatus.addElement("Chưa");
        modelMaritalStatus.addElement("Đã");


        modelGender = new DefaultComboBoxModel();
        modelGender.addElement(" ");
        modelGender.addElement("Nam");
        modelGender.addElement("Nữ");

        // Model country +  City 

        birthday = new JDateChooser();


        cboMaritalStatus = new JComboBox(modelMaritalStatus);
        cboGender = new JComboBox(modelGender);
        //cbo = new JComboBox(modelGender);

        cboMaritalStatus.setSelectedIndex(0);
        cboGender.setSelectedIndex(0);
        //

        txtIdentityCard = new JTextField();
        txtHeight = new JTextField();
        txtPhone = new JTextField();
        txtEmail = new JTextField();
        txtAddress = new JTextField();
        txtWeight = new JTextField();
        txtChilrenNum = new JTextField();
        txtMobile = new JTextField();
        txtWeb = new JTextField();

        txtIdentityCard.setDocument(new DigitsDocument());
        txtHeight.setDocument(new DigitsDocument());
        txtWeight.setDocument(new DigitsDocument());
        txtChilrenNum.setDocument(new DigitsDocument());
        txtMobile.setDocument(new DigitsDocument());
        txtPhone.setDocument(new DigitsDocument());
        lbl = new JLabel("0");

    }

    public JTextField getTxtAddress() {
        return txtAddress;
    }

    public JTextField getTxtChilrenNum() {
        return txtChilrenNum;
    }

    public JTextField getTxtEmail() {
        return txtEmail;
    }

    public JTextField getTxtHeight() {
        return txtHeight;
    }

    public JTextField getTxtIdentityCard() {
        return txtIdentityCard;
    }

    public JTextField getTxtMobile() {
        return txtMobile;
    }

    public JTextField getTxtPhone() {
        return txtPhone;
    }

    public JTextField getTxtWeb() {
        return txtWeb;
    }

    public JTextField getTxtWeight() {
        return txtWeight;
    }

    public void reset() {
        txtAddress.setText("");
        txtChilrenNum.setText("");
        txtEmail.setText("");
        txtHeight.setText("");
        txtIdentityCard.setText("");
        txtMobile.setText("");
        txtPhone.setText("");
        txtWeb.setText("");
        txtWeight.setText("");
    }

    @Override
    public Object getCellEditorValue() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        try {
            if (component == cboMaritalStatus) {
                return cboMaritalStatus.getSelectedItem();
            } else if (component == cboGender) {
                return cboGender.getSelectedItem();
            } else if (component == birthday) {
                int now = (new Date()).getYear();
                Date d1 = birthday.getDate();
                old1 = now - d1.getYear();
                return sdf.format(birthday.getDate());
            } else if (component == txtIdentityCard) {
                return txtIdentityCard.getText();
            } else if (component == txtHeight) {
                return txtHeight.getText();
            } else if (component == txtHeight) {
                return txtHeight.getText();
            } else if (component == txtPhone) {
                return txtPhone.getText();
            } else if (component == txtEmail) {
                return txtEmail.getText();
            } else if (component == txtAddress) {
                return txtAddress.getText();
            } else if (component == txtWeight) {
                return txtWeight.getText();
            } else if (component == txtChilrenNum) {
                return txtChilrenNum.getText();
            } else if (component == txtMobile) {
                return txtMobile.getText();
            } else if (component == lbl) {
                return "" + old1;
            } else if (component == cboCountry) {
                return cboCountry.getSelectedItem();
            } else if (component == cboCity) {
                return cboCity.getSelectedItem();
            } else {
                return txtWeb.getText();
            }
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        if (column == 1) {
            if (row == 0) {
                component = txtIdentityCard;
            }
            if (row == 1) {
                component = birthday;
            }
            if (row == 2) {
                component = txtHeight;
            }
            if (row == 3) {
                component = cboMaritalStatus;
            }
            if (row == 4) {
                component = txtPhone;
            }
            if (row == 5) {
                component = txtEmail;
            }
            if (row == 6) {
                component = txtAddress;
            }

        }
        if (column == 3) {
            if (row == 0) {
                component = cboGender;
            }
            if (row == 1) {
                component = lbl;
            }
            if (row == 2) {
                component = txtWeight;
            }
            if (row == 3) {
                component = txtChilrenNum;
            }
            if (row == 4) {
                component = txtMobile;
            }
            if (row == 5) {
                component = txtWeb;
            }
            if (row == 6) {
                component = cboCity;
            }
        }
        if (row == 6) {
            if (column == 2) {
                component = cboCountry;
            }
        }
        return component;
    }

    public class DigitsDocument extends PlainDocument {

        @Override
        public void insertString(int offs, String str, AttributeSet a)
                throws BadLocationException {
            if (str == null) {
                return;
            }
            char[] addedFigures = str.toCharArray();
            char c;
            for (int i = addedFigures.length; i > 0; i--) {
                c = addedFigures[i - 1];
                if (Character.isDigit(c) || c == '.') {
                    super.insertString(offs, new String(new Character(c).toString()), a);
                }
            }
        }
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
}

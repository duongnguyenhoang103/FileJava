/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.pilot.subenterprise.ui.panel;

import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.Component;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.CaretEvent;
import javax.swing.table.TableCellEditor;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import vn.com.hkt.pilot.sbenterprise.entity.EnterpriseStatus;
import vn.com.hkt.pilot.sbenterprise.entity.EnterpriseType;
import vn.com.hkt.pilot.subenterprise.dao.EnterpriseStatusBN;
import vn.com.hkt.pilot.subenterprise.dao.EnterpriseTypeBN;

/**
 *
 * @author VietAnh
 */
public class ExtEnterpriseSB112CellEdittor extends AbstractCellEditor implements TableCellEditor {

    private Component component;
    private JComboBox cboState, cboType;
    private DefaultComboBoxModel modelState, modelType;
    private JDateChooser dateS, dateR;
    private JTextField txtBookRegisterBussines, txtRegister, txtIdTax;
    private List<EnterpriseType> listEnterpriseTypes = new ArrayList<EnterpriseType>();
    private List<EnterpriseStatus> listEnterpriseStatuses = new ArrayList<EnterpriseStatus>();
    private EnterpriseStatusBN enterpriseStatusBN = new EnterpriseStatusBN();
    private EnterpriseTypeBN enterpriseTypeBN = new EnterpriseTypeBN();

    public ExtEnterpriseSB112CellEdittor() {
        modelState = new DefaultComboBoxModel();
        modelState.addElement(" ");
        modelType = new DefaultComboBoxModel();
        modelType.addElement(" ");
        loadCombo();

        dateS = new com.toedter.calendar.JDateChooser();
        dateR = new com.toedter.calendar.JDateChooser();


        cboState = new JComboBox(modelState);
        cboType = new JComboBox(modelType);

//        cboState.setSelectedIndex(0);
//        cboType.setSelectedIndex(0);

        txtBookRegisterBussines = new JTextField();
        txtIdTax = new JTextField();
        txtRegister = new JTextField();

        txtRegister.addCaretListener(new javax.swing.event.CaretListener() {

            @Override
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtRegisterCaretUpdate(evt);
            }

            private void txtRegisterCaretUpdate(CaretEvent evt) {
                String s = txtRegister.getText();
                int i;
                try {
                    i = Integer.parseInt(s);
                    txtRegister.setForeground(Color.BLACK);
                } catch (Exception ex) {
                    txtRegister.setForeground(Color.RED);
                }
            }
        });
        txtBookRegisterBussines.setDocument(new DigitsDocument());
        txtIdTax.setDocument(new DigitsDocument());
        txtRegister.setDocument(new DigitsDocument());
    }

    public JTextField getTxtBookRegisterBussines() {
        return txtBookRegisterBussines;
    }

    public JTextField getTxtIdTax() {
        return txtIdTax;
    }

    public JTextField getTxtRegister() {
        return txtRegister;
    }

    public void reset() {
        txtBookRegisterBussines.setText("");
        txtIdTax.setText("");
        txtRegister.setText("");
    }

    @Override
    public Object getCellEditorValue() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        try {
            if (component == cboState) {
                return cboState.getSelectedItem();
            } else if (component == cboType) {
                return cboType.getSelectedItem();
            } else if (component == dateS) {
                return sdf.format(dateS.getDate());
            } else if (component == dateR) {
                return sdf.format(dateR.getDate());
            } else if (component == txtBookRegisterBussines) {
                return txtBookRegisterBussines.getText();
            } else if (component == txtIdTax) {
                return txtIdTax.getText();
            }
            return txtRegister.getText();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        if (column == 1) {
            if (row == 0) {
                component = cboType;
            }
            if (row == 1) {
                component = dateS;
            }
            if (row == 2) {
                component = txtBookRegisterBussines;
            }
            if (row == 3) {
                component = cboState;
            }

        }
        if (column == 3) {
            if (row == 0) {
                component = txtRegister;
            }
            if (row == 1) {
                component = dateR;
            }
            if (row == 2) {
                component = txtIdTax;
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

   

    public void loadCombo() {
        listEnterpriseStatuses = enterpriseStatusBN.selectAll();
        listEnterpriseTypes = enterpriseTypeBN.selectAll();
        modelState.removeAllElements();
        modelState.addElement(" ");
        modelType.removeAllElements();
        modelType.addElement(" ");

        for (EnterpriseStatus bean : listEnterpriseStatuses) {
            modelState.addElement(bean);
        }

        for (EnterpriseType bean : listEnterpriseTypes) {
            modelType.addElement(bean);
        }
    }
}

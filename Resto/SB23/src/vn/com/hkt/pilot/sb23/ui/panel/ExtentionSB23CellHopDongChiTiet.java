/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.pilot.sb23.ui.panel;

import com.toedter.calendar.JDateChooser;
import java.awt.Component;
import java.text.SimpleDateFormat;
import javax.swing.AbstractCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableCellEditor;

/**
 *
 * @author DONGTAM
 */
public class ExtentionSB23CellHopDongChiTiet extends AbstractCellEditor implements TableCellEditor {

    private JComboBox cboEnterprise, cboPosition, cboPayfrequency, cboTypeOfContract, cboMonthlyIncrease;
    private DefaultComboBoxModel modelEnterprise, modelPosition, modelPayfrequency, modelTypeOfContract, modelMonthlyIncrease;
    private JTextField txtCoefficientMin, txtcoefficientMax, txtCycleIncrease, txtCompensationByFired, txtAutoRenewDeadline, txtSalaryMin, txtSalaryMax, txtCompensationByQuit, txtMonthlyIncreaseValue;
    private JDateChooser dchSignedDate, dchStartDate, dchUpdatedDate, dchFinishedDate;
    private Component component;

    public ExtentionSB23CellHopDongChiTiet() {

        txtCoefficientMin = new JTextField();
        txtcoefficientMax = new JTextField();
        txtCycleIncrease = new JTextField();
        txtCompensationByFired = new JTextField();
        txtAutoRenewDeadline = new JTextField();
        txtSalaryMin = new JTextField();
        txtSalaryMax = new JTextField();
        txtCompensationByQuit = new JTextField();
        txtMonthlyIncreaseValue = new JTextField();

        modelEnterprise = new DefaultComboBoxModel();
        modelPosition = new DefaultComboBoxModel();
        modelMonthlyIncrease = new DefaultComboBoxModel();
        modelTypeOfContract = new DefaultComboBoxModel();
        modelPayfrequency = new DefaultComboBoxModel();

        cboEnterprise = new JComboBox(modelEnterprise);
        cboPosition = new JComboBox(modelPosition);
        cboPayfrequency = new JComboBox(modelPayfrequency);
        cboTypeOfContract = new JComboBox(modelTypeOfContract);
        cboMonthlyIncrease = new JComboBox(modelMonthlyIncrease);

        dchSignedDate = new JDateChooser();
        dchStartDate = new JDateChooser();
        dchUpdatedDate = new JDateChooser();
        dchFinishedDate = new JDateChooser();
    }

    public void reset() {
        txtAutoRenewDeadline.setText("");
        txtCoefficientMin.setText("");
        txtCompensationByFired.setText("");
        txtCompensationByQuit.setText("");
        txtCycleIncrease.setText("");
        txtMonthlyIncreaseValue.setText("");
        txtSalaryMax.setText("");
        txtSalaryMin.setText("");
        txtcoefficientMax.setText("");
    }

    @Override
    public Object getCellEditorValue() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            if (component == cboEnterprise) {
                return cboEnterprise.getSelectedItem();
            } else if (component == cboPosition) {
                return cboPosition.getSelectedItem();
            } else if (component == txtCoefficientMin) {
                return txtCoefficientMin.getText();
            } else if (component == txtcoefficientMax) {
                return txtcoefficientMax.getText();
            } else if (component == cboPayfrequency) {
                return cboPayfrequency.getSelectedItem();
            } else if (component == txtCycleIncrease) {
                return txtCycleIncrease.getText();
            } else if (component == txtCompensationByFired) {
                return txtCompensationByFired.getText();
            } else if (component == dchSignedDate) {
                return sdf.format(dchSignedDate.getDate());
            } else if (component == dchUpdatedDate) {
                return sdf.format(dchUpdatedDate.getDate());
            } else if (component == cboTypeOfContract) {
                return cboTypeOfContract.getSelectedItem();
            } else if (component == txtAutoRenewDeadline) {
                return txtAutoRenewDeadline.getText();
            } else if (component == txtSalaryMin) {
                return txtSalaryMin.getText();
            } else if (component == txtSalaryMax) {
                return txtSalaryMax.getText();
            } else if (component == cboMonthlyIncrease) {
                return cboMonthlyIncrease.getSelectedItem();
            } else if (component == txtMonthlyIncreaseValue) {
                return txtMonthlyIncreaseValue.getText();
            } else if (component == txtCompensationByQuit) {
                return txtCompensationByQuit.getText();
            } else if (component == dchStartDate) {
                return sdf.format(dchStartDate.getDate());
            } else if (component == dchFinishedDate) {
                return sdf.format(dchFinishedDate.getDate());
            }
            return null;
        } catch (Exception e) {
            return "2000-01-01";
        }
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        if (column == 1) {
            if (row == 0) {
                component = cboEnterprise;
            }
            if (row == 1) {
                component = cboPosition;
            }
            if (row == 2) {
                component = txtCoefficientMin;
            }
            if (row == 3) {
                component = txtcoefficientMax;
            }
            if (row == 4) {
                component = cboPayfrequency;
            }
            if (row == 5) {
                component = txtCycleIncrease;
            }
            if (row == 6) {
                component = txtCompensationByFired;
            }
            if (row == 7) {
                component = dchSignedDate;
            }
            if (row == 8) {
                component = dchUpdatedDate;
            }

        }
        if (column == 3) {
            if (row == 0) {
                component = cboTypeOfContract;
            }
            if (row == 1) {
                component = txtAutoRenewDeadline;
            }
            if (row == 2) {
                component = txtSalaryMin;
            }
            if (row == 3) {
                component = txtSalaryMax;
            }
            if (row == 4) {
                component = cboMonthlyIncrease;
            }
            if (row == 5) {
                component = txtMonthlyIncreaseValue;
            }
            if (row == 6) {
                component = txtCompensationByQuit;
            }
            if (row == 7) {
                component = dchStartDate;
            }
            if (row == 8) {
                component = dchFinishedDate;
            }
        }
        return component;
    }
}

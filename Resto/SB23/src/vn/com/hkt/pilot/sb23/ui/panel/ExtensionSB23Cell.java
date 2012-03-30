/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.pilot.sb23.ui.panel;

import com.toedter.calendar.JDateChooser;
import java.awt.Component;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.AbstractCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableCellEditor;
import org.openide.util.Lookup;
import vn.com.hkt.basic.api.IMission;
import vn.com.hkt.pilot.entities.Mission;

/**
 *
 * @author Admin
 */
public class ExtensionSB23Cell extends AbstractCellEditor implements TableCellEditor {

    private JComboBox cboPosition, cboTypeContract;
    private DefaultComboBoxModel modelPosition, modelTypeContract;
    private JTextField txtMinSalary, txtCurrentSalary, txtGetTotal, txtMaxSalary, txtTotalAllowance, txtPaid, txtRemain;
    private JDateChooser dchStartDate, dchExpireDate;
    private Component component;
    private IMission missionAPI;

    public ExtensionSB23Cell() {
        modelPosition = new DefaultComboBoxModel();
        modelPosition.addElement("");
        missionAPI = Lookup.getDefault().lookup(IMission.class);

        List<Mission> list = missionAPI.selectAll();
        if (!list.isEmpty()) {
            for (Mission m : list) {
                modelPosition.addElement(m);
            }
        }

        modelTypeContract = new DefaultComboBoxModel();
        modelTypeContract.addElement("Dài hạn");
        modelTypeContract.addElement("Thời vụ");


        txtMinSalary = new JTextField();
        txtCurrentSalary = new JTextField();
        txtGetTotal = new JTextField();
        txtMaxSalary = new JTextField();
        txtTotalAllowance = new JTextField();
        txtPaid = new JTextField();
        txtRemain = new JTextField();

        cboPosition = new JComboBox(modelPosition);
        cboPosition.setSelectedIndex(0);
        cboTypeContract = new JComboBox(modelTypeContract);
        cboTypeContract.setSelectedIndex(0);

        dchStartDate = new JDateChooser();
        dchExpireDate = new JDateChooser();

    }

    @Override
    public Object getCellEditorValue() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            if (component == cboPosition) {
                return cboPosition.getSelectedItem();
            } else if (component == cboTypeContract) {
                return cboTypeContract.getSelectedItem();
            } else if (component == txtMinSalary) {
                return txtMinSalary.getText();
            } else if (component == txtCurrentSalary) {
                return txtCurrentSalary.getText();
            } else if (component == txtGetTotal) {
                return txtGetTotal.getText();
            } else if (component == txtMaxSalary) {
                return txtMaxSalary.getText();
            } else if (component == txtTotalAllowance) {
                return txtTotalAllowance.getText();
            } else if (component == txtPaid) {
                return txtPaid.getText();
            } else if (component == txtRemain) {
                return txtRemain.getText();
            } else if (component == dchStartDate) {
                return sdf.format(dchStartDate.getDate());
            } else if (component == dchExpireDate) {
                return sdf.format(dchExpireDate.getDate());
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        if (column == 1) {
            if (row == 0) {
                component = cboPosition;
            }
            if (row == 1) {
                component = dchStartDate;
            }
            if (row == 2) {
                component = txtMinSalary;
            }
            if (row == 3) {
                component = txtCurrentSalary;
            }
            if (row == 4) {
                component = txtGetTotal;
            }
        }
        if (column == 3) {
            if (row == 0) {
                component = cboTypeContract;
            }
            if (row == 1) {
                component = dchExpireDate;
            }
            if (row == 2) {
                component = txtMaxSalary;
            }
            if (row == 3) {
                component = txtTotalAllowance;
            }
            if (row == 4) {
                component = txtPaid;
            }
            if (row == 5) {
                component = txtRemain;
            }
        }
        return component;
    }

    public void reset() {
        txtCurrentSalary.setText("");
        txtGetTotal.setText("");
        txtMaxSalary.setText("");
        txtMinSalary.setText("");
        txtPaid.setText("");
        txtRemain.setText("");
        txtTotalAllowance.setText("");
    }
}

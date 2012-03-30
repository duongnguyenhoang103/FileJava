/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.pilot.sb31.ui.panel.ext_d_w41;

import com.toedter.calendar.JDateChooser;
import java.awt.Component;
import java.text.SimpleDateFormat;
import javax.print.DocFlavor;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.swing.AbstractCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableCellEditor;
import vn.com.hkt.pilot.sb31.deparment.extW41.entity.ProjectStatus;
import vn.com.hkt.pilot.sb31.department.extW41.dao.ProjectStatusBN;

/**
 *
 * @author VietAnh
 */
public class ExtDepartmentW41Cell extends AbstractCellEditor implements TableCellEditor {

    private Component component;
    private JComboBox cboStateDepartment, cboPrinter;
    private JTextField txtDescribe, txtunequal;
    private DefaultComboBoxModel modelState, modelPrinter;
    private JDateChooser dkTrienKhai, daTrienKhai, dkHoanThanh, daHoanThanh;
    private ProjectStatusBN projectStatusBN = new ProjectStatusBN();

    public ExtDepartmentW41Cell() {
        modelState = new DefaultComboBoxModel();
        modelState.addElement(" ");
        loadCombo();
        DocFlavor flavor = DocFlavor.SERVICE_FORMATTED.PRINTABLE;
        PrintService[] printService = PrintServiceLookup.lookupPrintServices(flavor, null);
        modelPrinter = new DefaultComboBoxModel(printService);

        txtDescribe = new JTextField("");
        txtunequal = new JTextField("");

        dkTrienKhai = new com.toedter.calendar.JDateChooser();
        daTrienKhai = new com.toedter.calendar.JDateChooser();
        dkHoanThanh = new com.toedter.calendar.JDateChooser();
        daHoanThanh = new com.toedter.calendar.JDateChooser();

        cboStateDepartment = new JComboBox(modelState);
        cboPrinter = new JComboBox(modelPrinter);

//        cboStateDepartment.setSelectedIndex(0);
//        cboPrinter.setSelectedIndex(0);

    }

    public void reset() {
        txtDescribe.setText("");
        txtunequal.setText("");
    }

    @Override
    public Object getCellEditorValue() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        try {
            if (component == cboStateDepartment) {
                return cboStateDepartment.getSelectedItem();
            } else if (component == cboPrinter) {
                return cboPrinter.getSelectedItem();
            } else if (component == dkTrienKhai) {
                return sdf.format(dkTrienKhai.getDate());
            } else if (component == daTrienKhai) {
                return sdf.format(daTrienKhai.getDate());
            } else if (component == dkHoanThanh) {
                return sdf.format(dkHoanThanh.getDate());
            } else if (component == daHoanThanh) {
                return sdf.format(daHoanThanh.getDate());
            } else if (component == txtDescribe) {
                return txtDescribe.getText();
            }
            return txtunequal.getText();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        if (column == 1) {
            if (row == 0) {
                component = cboStateDepartment;
            }
            if (row == 1) {
                component = dkTrienKhai;
            }
            if (row == 2) {
                component = daTrienKhai;
            }
            if (row == 3) {
                component = txtDescribe;
            }
        }
        if (column == 3) {
            if (row == 0) {
                component = cboPrinter;
            }
            if (row == 1) {
                component = dkHoanThanh;
            }
            if (row == 2) {
                component = daHoanThanh;
            }
            if (row == 3) {
                component = txtunequal;
            }
        }
        return component;
    }

    public void loadCombo() {
        modelState.removeAllElements();
        modelState.addElement(" ");
        for (ProjectStatus bean : projectStatusBN.selectAll()) {
            modelState.addElement(bean);
        }
    }
}

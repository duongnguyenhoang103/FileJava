/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.ui.panel;

import java.awt.Component;
import javax.swing.AbstractCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableCellEditor;
import org.openide.util.Lookup;
import vn.com.hkt.basic.api.IPersonBN;
import vn.com.hkt.basic.api.IMission;
import vn.com.hkt.pilot.entities.Mission;
import vn.com.hkt.pilot.entities.Person;

/**
 *
 * @author VietAnh
 */
public class ExecutiveOfficeCell extends AbstractCellEditor implements TableCellEditor {

    private JTextField txtChucDanh;
    private JTextField txtHoTen;
    private JComboBox[] cboChucDanh, cboHoTen;
    private DefaultComboBoxModel modelChucDanh, modelHoTen;
    private Component component;
    private IMission missionBN;
    private IPersonBN personBN;
    private int n;

    public ExecutiveOfficeCell(int n) {
        this.n = n;
        modelChucDanh = new DefaultComboBoxModel();
        modelHoTen = new DefaultComboBoxModel();

        missionBN = Lookup.getDefault().lookup(IMission.class);
        personBN = Lookup.getDefault().lookup(IPersonBN.class);

        loadCombo();
        cboChucDanh = new JComboBox[n];
        cboHoTen = new JComboBox[n];
        for (int i = 0; i < n; i++) {
            cboChucDanh[i] = new JComboBox(modelChucDanh);
            cboHoTen[i] = new JComboBox(modelHoTen);
        }

    }

    public void reset() {
        txtChucDanh.setText("");
        txtHoTen.setText("");
    }

    public void loadCombo() {
        modelChucDanh.removeAllElements();
        modelChucDanh.addElement(" ");
        modelHoTen.removeAllElements();
        for (Mission m : missionBN.selectAll()) {
            modelChucDanh.addElement(m);
        }
        modelHoTen.addElement(" ");
        for (Person p : personBN.selectAll()) {
            modelHoTen.addElement(p);
        }
    }

    @Override
    public Object getCellEditorValue() {
        for (int i = 0; i < n; i++) {
            if (component == cboChucDanh[i]) {
                return cboChucDanh[i].getSelectedItem();
            }
            if (component == cboHoTen[i]) {
                return cboHoTen[i].getSelectedItem();
            }
        }
        return "";
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        for (int i = 1; i < n; i++) {
            if (i == row) {
                if (column == 0) {
                    component = cboChucDanh[i - 1];
                }
                if (column == 1) {
                    component = cboHoTen[i - 1];
                }
            }
        }
        return component;
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.pilot.sb31.ui.panel.ext_d_w41;

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
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;
import org.openide.util.Lookup;
import vn.com.hkt.basic.api.IMission;
import vn.com.hkt.basic.api.IPersonBN;
import vn.com.hkt.pilot.entities.Mission;
import vn.com.hkt.pilot.entities.Person;

/**
 *
 * @author VietAnh
 */
public class ExtDepartmentW43Cell extends AbstractCellEditor implements TableCellEditor, ItemListener, ActionListener {

    private JComponent component;
    private JComboBox[] cboName, cboRole;
    private JLabel[] lableTile;
    private DefaultComboBoxModel model1, model2;
    private IPersonBN personBN;
    private IMission missionBN;
    private List<Person> listP = new ArrayList<Person>();
    private int n;

    public ExtDepartmentW43Cell(int n) {
        this.n = n;

        model2 = new DefaultComboBoxModel();
        model1 = new DefaultComboBoxModel();

        missionBN = Lookup.getDefault().lookup(IMission.class);
        personBN = Lookup.getDefault().lookup(IPersonBN.class);
        listP = personBN.selectAll();

        loadCombo();
        cboName = new JComboBox[n];
        cboRole = new JComboBox[n];
        lableTile = new JLabel[n];
        for (int i = 0; i < n; i++) {
            cboName[i] = new JComboBox(model1);
            cboRole[i] = new JComboBox(model2);
            cboName[i].addActionListener(this);

            lableTile[i] = new JLabel();
        }

    }

    @Override
    public Object getCellEditorValue() {
        for (int i = 1; i < n; i++) {
            if (component == cboName[i]) {
                return cboName[i].getSelectedItem();
            }
            if (component == cboRole[i]) {
                return cboRole[i].getSelectedItem();
            }
            if (component == lableTile[i]) {
                return lableTile[i].getText();
            }
        }
        return "";
    }

    public JComboBox[] getCboName() {
        return cboName;
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        for (int i = 1; i < n; i++) {
            if (i == row) {
                if (column == 0) {
                    component = cboName[i];
                }
                if (column == 1) {
                    component = cboRole[i];
                }
                if (column == 2) {
                    component = lableTile[i];
                }
            }
        }
        return component;
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        JComboBox comboBox = (JComboBox) e.getSource();
        for (int i = 1; i < n; i++) {
            if (comboBox == cboName[i]) {
                if (cboName[i].isShowing()) {
                    int m = listP.size();
                    lableTile[i].setText(String.valueOf(2 * (m - (i - 1)) * 100 / (m * (m + 1))));
                }
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JComboBox comboBox = (JComboBox) e.getSource();
        for (int i = 1; i < n; i++) {
            if (comboBox == cboName[i]) {
                if (cboName[i].isShowing()) {
                    int m = listP.size();
                    if (m != 0) {
                        lableTile[i].setText(String.valueOf(2 * (m - (i - 1)) * 100 / (m * (m + 1))));
                    } else {
                        lableTile[i].setText("0");
                    }

                }
            }
        }
    }

// Cong thuc tinh ti le    
    public void loadCombo() {
        model1.removeAllElements();
        model2.removeAllElements();
        model1.addElement("");
        model2.addElement("");
        for (Mission m : missionBN.selectAll()) {
            model2.addElement(m);
        }
        for (Person p : personBN.selectAll()) {
            model1.addElement(p);
        }
    }

    public JLabel[] getLableTile() {
        return lableTile;
    }

    public void resetCell() {

        for (int i = 1; i < n; i++) {
            lableTile[i].setText(" ");

        }
    }

    public void setCell(int t) {
        for (int i = 1; i < t; i++) {
            int m = listP.size();
            lableTile[i].setText(String.valueOf(2 * (m - (i - 1)) * 100 / (m * (m + 1))));
            lableTile[i].repaint();
        }
    }
}

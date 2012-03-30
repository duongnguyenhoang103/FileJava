/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.pilot.sb24.ui.panel;

import java.awt.Component;
import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableCellEditor;
import org.openide.util.Lookup;
import vn.com.hkt.basic.api.IForeignLanguageBN;
import vn.com.hkt.pilot.entities.ForeignLanguage;
import vn.com.hkt.pilot.sb24.dao.LevelLanguageBN;
import vn.com.hkt.pilot.sb24.entity.LevelLanguage;

/**
 *
 * @author Admin
 */
public class ExtensionSB24Cell extends AbstractCellEditor implements TableCellEditor {

    private JTextField txtcharacteristics, txtWorkingHobbies, txtComputer, txtPersonalHobbies, txtResearchHobbies;
    private JComboBox[] cbxLevel, cbxLanguage;
    private DefaultComboBoxModel modelLevel;
    private DefaultComboBoxModel modelLanguage;
    private Component component;
    private int quantity;
    private IForeignLanguageBN foreignLanguageBN = Lookup.getDefault().lookup(IForeignLanguageBN.class);
    private List<ForeignLanguage> list;
    private LevelLanguageBN levelLanguageBN = new LevelLanguageBN();
    private List<LevelLanguage> listLevel;

    public ExtensionSB24Cell(int n) {
        txtcharacteristics = new JTextField();
        txtWorkingHobbies = new JTextField();
        txtComputer = new JTextField();
        txtPersonalHobbies = new JTextField();
        txtResearchHobbies = new JTextField();

        this.quantity = n;
        cbxLevel = new JComboBox[n];
        cbxLanguage = new JComboBox[n];
        
        modelLevel = new DefaultComboBoxModel();
        modelLevel.addElement(" ");

        modelLanguage = new DefaultComboBoxModel();
        modelLanguage.addElement(" ");
        loadCbo(n);
//
//        list = new ArrayList<ForeignLanguage>();
//        list = foreignLanguageBN.selectAll();
//        for (int j = 0; j < list.size(); j++) {
//            modelLanguage.addElement(list.get(j));
//        }
//
//        listLevel = new ArrayList<LevelLanguage>();
//        listLevel = levelLanguageBN.selectAll();
//        for (int j = 0; j < listLevel.size(); j++) {
//            modelLevel.addElement(listLevel.get(j));
//        }
//
//        for (int i = 0; i < n; i++) {
//            cbxLevel[i] = new JComboBox(modelLevel);
//            cbxLanguage[i] = new JComboBox(modelLanguage);
//        }
    }

    public void reset() {
        txtComputer.setText("");
        txtPersonalHobbies.setText("");
        txtResearchHobbies.setText("");
        txtWorkingHobbies.setText("");
        txtcharacteristics.setText("");
    }

    @Override
    public Object getCellEditorValue() {
        try {
            if (component == txtcharacteristics) {
                return txtcharacteristics.getText();
            } else if (component == txtWorkingHobbies) {
                return txtWorkingHobbies.getText();
            } else if (component == txtComputer) {
                return txtComputer.getText();
            } else if (component == txtPersonalHobbies) {
                return txtPersonalHobbies.getText();
            } else if (component == txtResearchHobbies) {
                return txtResearchHobbies.getText();
            }
            for (int i = 0; i < quantity; i++) {
                if (component == cbxLevel[i]) {
                    return cbxLevel[i].getSelectedItem();
                }
                if (component == cbxLanguage[i]) {
                    return cbxLanguage[i].getSelectedItem();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        if (column == 1) {
            if (row == 0) {
                component = txtcharacteristics;
            }
            if (row == 1) {
                component = txtWorkingHobbies;
            }
            if (row == 2) {
                component = txtComputer;
            }
        }
        if (column == 2) {
            if (row == 0) {
                component = txtPersonalHobbies;
            }
            if (row == 1) {
                component = txtResearchHobbies;
            }
        }
        for (int i = 0; i < quantity; i++) {
            if (i == row) {
                if (column == 1) {
                    component = cbxLanguage[i];
                }
                if (column == 2) {
                    component = cbxLevel[i];
                }
            }
        }
        return component;
    }

    public void loadCbo(int n) {
        modelLanguage.removeAllElements();
        modelLanguage.addElement(" ");
        modelLevel.removeAllElements();
        modelLevel.addElement(" ");
        list = new ArrayList<ForeignLanguage>();
        list = foreignLanguageBN.selectAll();
        for (int j = 0; j < list.size(); j++) {
            modelLanguage.addElement(list.get(j));
        }

        listLevel = new ArrayList<LevelLanguage>();
        listLevel = levelLanguageBN.selectAll();
        for (int j = 0; j < listLevel.size(); j++) {
            modelLevel.addElement(listLevel.get(j));
        }
        for (int i = 0; i < n; i++) {
            cbxLevel[i] = new JComboBox(modelLevel);
            cbxLanguage[i] = new JComboBox(modelLanguage);
        }
    }
}
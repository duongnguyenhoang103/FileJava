/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.erm.enterprise.ui;

import vn.com.hkt.pilot.entities.Enterprise;
import vn.com.hkt.pilot.entities.Person;
import vn.com.hkt.basic.api.IEnterpriseBN;
import vn.com.hkt.basic.api.IPersonBN;
import java.awt.Component;
import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableCellEditor;
import org.openide.util.Lookup;

/**
 *
 * @author longnt
 */
public class EnterpriseCellEditor extends AbstractCellEditor implements TableCellEditor {

    private Component component;
    private JComboBox cboEnterprise, cboPerson;
    private JTextField txtNameE, txtIdE, txtSlogan;
    private DefaultComboBoxModel modelPerson, modelEnter;
    private IEnterpriseBN enterpriseDAO;
    private IPersonBN personDAO;
    private List<Enterprise> list = new ArrayList<Enterprise>();
    private List<Person> list1 = new ArrayList<Person>();

    public JTextField getTxtIdE() {
        return txtIdE;
    }

    public JComboBox getCboEnterprise() {
        return cboEnterprise;
    }

    public JComboBox getCboPerson() {
        return cboPerson;
    }

    public JTextField getTxtNameE() {
        return txtNameE;
    }

    public void reset() {
        txtNameE.setText(" ");
        txtSlogan.setText(" ");
    }

    public JTextField getTxtSlogan() {
        return txtSlogan;
    }

    public EnterpriseCellEditor() {
        this.enterpriseDAO = Lookup.getDefault().lookup(IEnterpriseBN.class);
        this.personDAO = Lookup.getDefault().lookup(IPersonBN.class);
        txtIdE = new JTextField();
        txtNameE = new JTextField();
        txtSlogan = new JTextField();

        modelEnter = new DefaultComboBoxModel();
        modelEnter.addElement(null);
        modelPerson = new DefaultComboBoxModel();
        modelPerson.addElement(null);
        txtIdE.setText("");
        txtNameE.setText("");

        // Create model for combobox

        cboEnterprise = new JComboBox(modelEnter);
//        cboEnterprise.setEditable(true);
//        if (cboEnterprise.getItemCount() != 0) {
//            new JComboBoxFilterAPI(cboEnterprise);
//        }

        cboPerson = new JComboBox(modelPerson);
//        cboPerson.setEditable(true);
//        if (cboPerson.getItemCount() != 0) {
//            new JComboBoxFilterAPI(cboPerson);
//        }
    }

    @Override
    public Object getCellEditorValue() {
        if (component == txtIdE) {
            return txtIdE.getText();
        } else if (component == txtNameE) {
            return txtNameE.getText();
        } else if (component == txtSlogan) {
            return txtSlogan.getText();
        } else if (component == cboEnterprise) {
            if (cboEnterprise.getSelectedItem() != null) {
                return (Enterprise) cboEnterprise.getSelectedItem();
            } else {
                return cboEnterprise.getSelectedItem();
            }
        } else {
            if (cboPerson.getSelectedItem() != null) {
                return (Person) cboPerson.getSelectedItem();
            } else {
                return cboPerson.getSelectedItem();
            }
        }
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        if (column == 1) {
            if (row == 0) {
                component = txtNameE;
            }
            if (row == 1) {
                component = txtIdE;
            }
            if (row == 2) {
                component = cboEnterprise;
            }
            if (row == 3) {
                component = cboPerson;
            }
            if (row == 4) {
                component = txtSlogan;
            }
        }
        return component;
    }

    public void loadCbo() {

        modelEnter.removeAllElements();
        modelEnter.addElement(null);
        list = enterpriseDAO.selectAll();
        for (Enterprise bean : list) {
            modelEnter.addElement(bean);
        }


    }

    public void loadPerson(Enterprise enterprise) {
        modelPerson.removeAllElements();
        modelPerson.addElement(null);
        list1 = personDAO.filterPersonByEnterprise(enterprise);
        for (Person bean : list1) {
            modelPerson.addElement(bean);
        }
    }
}

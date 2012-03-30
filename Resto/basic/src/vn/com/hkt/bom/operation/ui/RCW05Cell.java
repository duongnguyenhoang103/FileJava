/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.bom.operation.ui;

import com.toedter.calendar.JDateChooser;
import java.awt.Component;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.table.TableCellEditor;
import org.openide.util.Lookup;
import vn.com.hkt.basic.api.IDepartmentBN;
import vn.com.hkt.basic.api.IEnterpriseBN;
import vn.com.hkt.basic.api.IPersonBN;
import vn.com.hkt.pilot.entities.Department;
import vn.com.hkt.pilot.entities.Enterprise;
import vn.com.hkt.pilot.entities.Person;

/**
 *
 * @author long
 */
public class RCW05Cell extends AbstractCellEditor implements TableCellEditor, ItemListener {

    private Component component;
    private JComboBox cboEnterprise, cboDepartment, cboPerson;
    private DefaultComboBoxModel modelEnterprise, modelDepartment, modelPerson;
    private JDateChooser date;
    private JTextField txtNameO;
    private IEnterpriseBN enterpriseDAO;
    private IPersonBN personBN;
    private IDepartmentBN departmentBN;
    private  List<Enterprise> list = new ArrayList<Enterprise>();

    public RCW05Cell() {
        this.personBN = Lookup.getDefault().lookup(IPersonBN.class);
        this.enterpriseDAO = Lookup.getDefault().lookup(IEnterpriseBN.class);
        this.departmentBN = Lookup.getDefault().lookup(IDepartmentBN.class);

        txtNameO = new JTextField();
        txtNameO.setText(" ");
        date = new com.toedter.calendar.JDateChooser();
        modelEnterprise = new DefaultComboBoxModel();
        modelEnterprise.addElement(" ");
        modelPerson = new DefaultComboBoxModel();
        modelPerson.addElement(" ");
        modelDepartment = new DefaultComboBoxModel();
        modelDepartment.addElement(" ");
       
        list = enterpriseDAO.selectAll();
        for (Enterprise bean : list) {
            modelEnterprise.addElement(bean);
        }
        cboEnterprise = new JComboBox(modelEnterprise);
        cboPerson = new JComboBox(modelPerson);
        cboDepartment = new JComboBox(modelDepartment);
        cboEnterprise.addItemListener(this);
    }

    @Override
    public Object getCellEditorValue() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        try {
            if (component == cboDepartment) {
                return cboDepartment.getSelectedItem();
            } else if (component == cboEnterprise) {
                return cboEnterprise.getSelectedItem();
            } else if (component == date) {
                return sdf.format(date.getDate());
            } else if (component == cboPerson) {
                return cboPerson.getSelectedItem();
            } else {
                return txtNameO.getText();
            }
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
          if (column == 1) {
            if (row == 0) {
                component = date;
            }
            if (row == 1) {
                component = txtNameO;
            }
            if (row == 2) {
                component = cboPerson;
            }

        }
        if (column == 3) {
           
            if (row == 1) {
                component = cboEnterprise;
            }
            if (row == 2) {
                component = cboDepartment;
            }
        }
        return component;
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        JComboBox comboBox = (JComboBox) e.getSource();
        if (comboBox == cboEnterprise) {
            if (cboEnterprise.getSelectedItem().toString().trim().length() != 0) {
                Enterprise enterprise = (Enterprise) cboEnterprise.getSelectedItem();
                resetCombo(enterprise);
            }
        }
    }

    public JTextField getTxtNameO() {
        return txtNameO;
    }

    public void resetCombo(Enterprise enterprise) {
        modelDepartment.removeAllElements();
        modelPerson.removeAllElements();

        modelDepartment.addElement(" ");
        modelPerson.addElement(" ");
        List<Person> list1 = new ArrayList<Person>();
        list1 = personBN.filterPersonByEnterprise(enterprise);

        for (Person bean : list1) {
            modelPerson.addElement(bean);
        }

        List<Department> list2 = new ArrayList<Department>();
        list2 = departmentBN.filterDepartmentByEnterprise(enterprise);
        for (Department bean : list2) {
            modelDepartment.addElement(bean);
        }

    }
    
    public void loadCboEnterprise(){
        modelEnterprise.removeAllElements();
        modelEnterprise.addElement(" ");
        list = enterpriseDAO.selectAll();
        for (Enterprise bean : list) {
            modelEnterprise.addElement(bean);
        }
    }
}

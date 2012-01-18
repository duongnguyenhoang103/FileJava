/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.bom.product.ui;

import java.awt.event.ItemEvent;
import vn.com.hkt.pilot.entities.Department;
import vn.com.hkt.pilot.entities.Enterprise;
import vn.com.hkt.pilot.entities.Product;
import vn.com.hkt.basic.api.IDepartmentBN;
import vn.com.hkt.basic.api.IEnterpriseBN;
import vn.com.hkt.basic.api.IPersonBN;
import vn.com.hkt.basic.api.IProductBN;
import java.awt.Component;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableCellEditor;
import org.openide.util.Lookup;
import vn.com.hkt.pilot.dialog.api.ICountryBN;
import vn.com.hkt.pilot.entities.Country;
import vn.com.hkt.pilot.entities.Person;

/**
 *
 * @author longnt
 */
public class ProductCell extends AbstractCellEditor implements TableCellEditor, ItemListener {

    private Component component;
    private JComboBox cboEnterprise, cboPerson, cboDepartment, cboProductParent, cboNuocSX, cboCity;
    private JTextField txtNamePro, txtIdPro, txtImagePhoTo;
    private DefaultComboBoxModel modelPerson, modelEnter, modelDepartment, modelProductParent, modelNuocSX, modelCity;
    private IEnterpriseBN enterpriseDAO;
    private IPersonBN personBN;
    private IDepartmentBN departmentBN;
    private IProductBN productBN;
    private Enterprise enterprise = null;
    private ICountryBN countryDAO;

    public ProductCell() {

        this.enterpriseDAO = Lookup.getDefault().lookup(IEnterpriseBN.class);
        this.personBN = Lookup.getDefault().lookup(IPersonBN.class);
        this.departmentBN = Lookup.getDefault().lookup(IDepartmentBN.class);
        this.productBN = Lookup.getDefault().lookup(IProductBN.class);
        this.countryDAO = Lookup.getDefault().lookup(ICountryBN.class);
        // enterprise = new Enterprise();
        txtIdPro = new JTextField();
        txtNamePro = new JTextField();
        txtImagePhoTo = new JTextField();

        modelEnter = new DefaultComboBoxModel();
        modelEnter.addElement(" ");
        modelPerson = new DefaultComboBoxModel();
        modelPerson.addElement(" ");
        modelDepartment = new DefaultComboBoxModel();
        modelDepartment.addElement(" ");
        modelProductParent = new DefaultComboBoxModel();
        modelProductParent.addElement(" ");

        modelNuocSX = new DefaultComboBoxModel();
        modelNuocSX.addElement(" ");
         List<Country> list4 = new ArrayList<Country>();
        list4 = countryDAO.getAllCountry();
        for(Country bean : list4) {
            modelNuocSX.addElement(bean);
        }
        modelCity = new DefaultComboBoxModel();
        modelCity.addElement(" ");
        cboNuocSX = new JComboBox(modelNuocSX);
          cboNuocSX.setSelectedIndex(0);
          


        List<Enterprise> list = new ArrayList<Enterprise>();
        list = enterpriseDAO.getAllEnterprise();
        for (Enterprise bean : list) {
            modelEnter.addElement(bean);
        }

        cboEnterprise = new JComboBox(modelEnter);
        cboPerson = new JComboBox(modelPerson);
        cboDepartment = new JComboBox(modelDepartment);
        cboProductParent = new JComboBox(modelProductParent);

       
        cboCity = new JComboBox(modelCity);

        // Set index = null for combobox
        cboEnterprise.setSelectedIndex(0);
        cboPerson.setSelectedIndex(0);
        cboDepartment.setSelectedIndex(0);
        cboProductParent.setSelectedIndex(0);
      
        cboCity.setSelectedIndex(0);

        txtIdPro.setText(" ");
        txtNamePro.setText(" ");
        txtImagePhoTo.setText(" ");
        cboEnterprise.addItemListener(this);
       
    }

    public JTextField getTxtIdPro() {
        return txtIdPro;
    }

    public JTextField getTxtNamePro() {
        return txtNamePro;
    }

    public JTextField getTxtImagePhoTo() {
        return txtImagePhoTo;
    }

    @Override
    public Object getCellEditorValue() {
        if (component == txtIdPro) {
            return txtIdPro.getText();
        } else if (component == cboDepartment) {
            if (cboDepartment.getSelectedItem().toString().trim().length() != 0) {
                return (Department) cboDepartment.getSelectedItem();
            } else {
                return cboDepartment.getSelectedItem();
            }
        } else if (component == txtNamePro) {
            return txtNamePro.getText();
            
        }else if (component == cboNuocSX) {
            return cboNuocSX.getSelectedItem();
        }
        else if (component == cboEnterprise) {
            if (cboEnterprise.getSelectedItem().toString().trim().length() != 0) {
                return (Enterprise) cboEnterprise.getSelectedItem();
            } else {
                return cboEnterprise.getSelectedItem();
            }
        } else if (component == cboProductParent) {
            if (cboProductParent.getSelectedItem().toString().trim().length() != 0) {
                return (Product) cboProductParent.getSelectedItem();
            } else {
                return cboProductParent.getSelectedItem();
            }
        } else {
            if (cboPerson.getSelectedItem().toString().trim().length() != 0) {
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
                component = txtNamePro;
            }
            if (row == 1) {
                component = txtIdPro;
            }
            if (row == 2) {
                component = cboEnterprise;
            }
            if (row == 3) {
                component = cboDepartment;
            }
            if (row == 4) {
                component = cboPerson;
            }
            if (row == 5) {
                component = cboProductParent;
            }
            if (row == 6) {
                component = cboNuocSX;
            }
            if (row == 7) {
                component = cboCity;
            }
            if (row == 8) {
                component = txtImagePhoTo;
            }
        }
        return component;
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        JComboBox comboBox = (JComboBox) e.getSource();
        if (comboBox == cboEnterprise) {
            if (cboEnterprise.getSelectedItem().toString().trim().length() != 0) {
                enterprise = (Enterprise) cboEnterprise.getSelectedItem();
                modelDepartment.removeAllElements();
                modelProductParent.removeAllElements();
                modelPerson.removeAllElements();
                modelPerson.addElement(" ");
                modelProductParent.addElement(" ");
                modelDepartment.addElement(" ");
                List<Product> list3 = new ArrayList<Product>();
                list3 = productBN.getByEnterprise(enterprise);
                for (Product bean : list3) {
                    modelProductParent.addElement(bean);
                }

                List<Department> list2 = new ArrayList<Department>();
                list2 = departmentBN.filterDepartmentByEnterprise(enterprise);
                for (Department bean : list2) {
                    modelDepartment.addElement(bean);
                }
                List<Person> list1 = new ArrayList<Person>();
                list1 = personBN.filterPersonByEnterprise(enterprise);
                for (Person bean : list1) {
                    modelPerson.addElement(bean);
                }
            }

        }
    }

    public void resetCombobox(Enterprise enterprise) {
        modelDepartment.removeAllElements();
        modelProductParent.removeAllElements();
        modelPerson.removeAllElements();
        modelPerson.addElement(" ");
        modelProductParent.addElement(" ");
        modelDepartment.addElement(" ");
        List<Product> list3 = new ArrayList<Product>();
        list3 = productBN.getByEnterprise(enterprise);
        for (Product bean : list3) {
            modelProductParent.addElement(bean);
        }

        List<Department> list2 = new ArrayList<Department>();
        list2 = departmentBN.filterDepartmentByEnterprise(enterprise);
        for (Department bean : list2) {
            modelDepartment.addElement(bean);
        }
        List<Person> list1 = new ArrayList<Person>();
        list1 = personBN.filterPersonByEnterprise(enterprise);
        for (Person bean : list1) {
            modelPerson.addElement(bean);
        }

        modelNuocSX.removeAllElements();
        modelNuocSX.addElement(" ");
        List<Country> list4 = new ArrayList<Country>();
        list4 = countryDAO.getAllCountry();
        for(Country bean : list4) {
            modelNuocSX.addElement(bean);
        }
        
    
    }
}

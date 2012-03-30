/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.pilot.promotion.ui.cell;

import java.awt.Component;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;
import org.openide.util.Lookup;
import vn.com.hkt.basic.api.IDepartmentBN;
import vn.com.hkt.basic.api.IEnterpriseBN;
import vn.com.hkt.basic.api.IOperationBN;
import vn.com.hkt.basic.api.IPersonBN;
import vn.com.hkt.basic.api.IProductBN;
import vn.com.hkt.basic.toolbar.BasicToolbarManager;
import vn.com.hkt.pilot.entities.Department;
import vn.com.hkt.pilot.entities.Enterprise;
import vn.com.hkt.pilot.entities.Operation;
import vn.com.hkt.pilot.entities.Person;
import vn.com.hkt.pilot.entities.Product;
import vn.com.hkt.pilot.promotion.ui.panel.ClassificationOperationPanel;

/**
 *
 * @author khangpn
 */
public class ClassificationOperationCell extends AbstractCellEditor implements TableCellEditor,ItemListener {

    private JComboBox cboEnterprise, cboDepartment, cboProduct, cboPerson, cboOperation;
    private DefaultComboBoxModel cboEnterpriseModel, cboDepartmentModel,
            cboProductModel, cboPersonModel, cboOperationModel;
    private Component component;
    /**
     * Nếu là 1-> thực thi theo Department
     * Nếu là 2-> thực thi theo Enterprise
     * Nếu là 3-> thực thi theo Operation
     * Nếu là 4-> thực thi theo Person
     * Nếu là 5-> thực thi theo Product
     */
    private int flag, enterpriseId = 0;
    private List<Enterprise> listEnterprise = new ArrayList<Enterprise>();
    private IEnterpriseBN enterpriseBN;
    private IDepartmentBN departmentBN;
    private IProductBN productBN;
    private IPersonBN personBN;
    private IOperationBN operationBN;
    private ClassificationOperationPanel classificationOperationPanel;

    public ClassificationOperationCell(int flag, ClassificationOperationPanel classificationOperationPanel) {
        this.classificationOperationPanel = classificationOperationPanel;
        Enterprise enterprise = BasicToolbarManager.getBasicToolbar().getEnterprise();
        if (enterprise != null) {
            enterpriseId = enterprise.getId();
        }

        this.flag = flag;

        enterpriseBN = Lookup.getDefault().lookup(IEnterpriseBN.class);
        departmentBN = Lookup.getDefault().lookup(IDepartmentBN.class);
        productBN = Lookup.getDefault().lookup(IProductBN.class);
        personBN = Lookup.getDefault().lookup(IPersonBN.class);
        operationBN = Lookup.getDefault().lookup(IOperationBN.class);

        cboDepartmentModel = new DefaultComboBoxModel();
        cboEnterpriseModel = new DefaultComboBoxModel();
        cboOperationModel = new DefaultComboBoxModel();
        cboPersonModel = new DefaultComboBoxModel();
        cboProductModel = new DefaultComboBoxModel();
        
        fillCombo();

        cboDepartment = new JComboBox(cboDepartmentModel);

        cboEnterprise = new JComboBox(cboEnterpriseModel);

        cboPerson = new JComboBox(cboPersonModel);

        cboProduct = new JComboBox(cboProductModel);

        cboOperation = new JComboBox(cboOperationModel);

    }

    public void fillCombo() {
        /**
         * Khởi tạo
         */        

        cboDepartmentModel.removeAllElements();
        cboEnterpriseModel.removeAllElements();
        cboOperationModel.removeAllElements();
        cboPersonModel.removeAllElements();
        cboProductModel.removeAllElements();
        
        if (enterpriseId != 0) {
            List<Enterprise> enterprises = new ArrayList<Enterprise>();// List<Enterprise> enterprises = enterpriseBN.select(Enterprise.FILED_ENTERPRISE_PARENT_ID_ACTUAL, String.valueOf(enterpriseId));
            Enterprise enterprise = BasicToolbarManager.getBasicToolbar().getEnterprise();
        if (enterprise != null) {
            enterprises = getEnterpriseChildren(enterprise);
        }
            List<Department> departments = departmentBN.select(Department.
                    FIELD_ENTERPRISE_ID_ACTUAL , String.valueOf(enterprise.getId()));
            List<Product> products = productBN.select(Product.FIELD_ENTERPRISE_ID_ACTUAL, String.valueOf(enterprise.getId()));
            List<Operation> operations = operationBN.select(Product.FIELD_ENTERPRISE_ID_ACTUAL, 
                    String.valueOf(enterprise.getId()));
            List<Person> persons = personBN.selectAll();

            if (!enterprises.isEmpty()) {
                for (Enterprise e : enterprises) {
                    cboEnterpriseModel.addElement(e);
                }
            }
            if (!departments.isEmpty()) {
                for (Department department : departments) {
                    cboDepartmentModel.addElement(department);
                }
            }
            if (!products.isEmpty()) {
                for (Product p : products) {
                    cboProductModel.addElement(p);
                }
            }
            if (!operations.isEmpty()) {
                for (Operation o : operations) {
                    cboOperationModel.addElement(o);
                }
            }
            if (!persons.isEmpty()) {
                for (Person p : persons) {
                    cboPersonModel.addElement(p);
                }
            }
        }
    }
    
    public void setListObjectId(int n){
        List<Integer> integers = classificationOperationPanel.getListObjectId();
        if(!integers.isEmpty()){
            if(!checkListObjectId(n, integers)){
                classificationOperationPanel.getListObjectId().add(n);
            }
        }
    }
    
    public boolean checkListObjectId(int n, List<Integer> integers){
        if(!integers.isEmpty()){
            for(Integer i:integers){
                if(i==n)return true;
            }
        }
        return false;
    }

    public List<Enterprise> getEnterpriseChildren(Enterprise enterprise) {
        List<Enterprise> le = new ArrayList<Enterprise>();
        List<Enterprise> list = enterpriseBN.select(
                Enterprise.FILED_ENTERPRISE_PARENT_ID_ACTUAL, String.valueOf(enterprise.getId()));
        for (int i = 0; i < list.size(); i++) {
            le.add(list.get(i));
            List<Enterprise> l = getEnterpriseChildren(list.get(i));
            for (int j = 0; j < l.size(); j++) {
                le.add(l.get(j));
            }
        }
        return le;

    }

    @Override
    public Object getCellEditorValue() {
        if (component instanceof JComboBox) {
            JComboBox comboBox = (JComboBox) component;
            return comboBox.getSelectedItem();
        }
        return null;
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {

        switch (column) {
            case 1:
                component = chooseComponent(flag);
                break;
            case 3:
                component = chooseComponent(flag);
                break;
            default:
                component = null;
        }
        return component;
    }

    public Component chooseComponent(int i) {
        switch (i) {
            case 1:
                component = cboDepartment;
                break;
            case 2:
                component = cboEnterprise;
                break;
            case 3:
                component = cboOperation;
                break;
            case 4:
                component = cboPerson;
                break;
            case 5:
                component = cboProduct;
                break;
            default:
                component = null;
        }
        return component;
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        
        JComboBox comboBox = (JComboBox)e.getSource();
        int n = Integer.parseInt(comboBox.getSelectedItem().toString());
        setListObjectId(n);
        
    }
}

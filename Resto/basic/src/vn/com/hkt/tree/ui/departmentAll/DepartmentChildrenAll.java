/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.tree.ui.departmentAll;

import java.beans.PropertyEditor;
import java.beans.PropertyEditorSupport;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.openide.nodes.Node;
import org.openide.nodes.PropertySupport;
import org.openide.nodes.Sheet;
import org.openide.util.Lookup;
import org.openide.util.lookup.Lookups;
import vn.com.hkt.basic.api.IDepartmentBN;
import vn.com.hkt.erm.department.dao.DepartmentBN;
import vn.com.hkt.hrm.person.dao.PersonBN;
import vn.com.hkt.pilot.entities.Department;
import vn.com.hkt.pilot.entities.Enterprise;
import vn.com.hkt.pilot.entities.Person;

/**
 *
 * @author longnt
 */
public class DepartmentChildrenAll extends Children.Keys {

    private List<Department> list = new ArrayList<Department>();
    private IDepartmentBN departmentBN = Lookup.getDefault().lookup(IDepartmentBN.class);
    public static String Giamdoc_PROPERTY = "TruongPhong";
    public static String Congtyme_PROPERTY = "CongTy";
    private Department department;
    private Enterprise enterprise;
    public DepartmentChildrenAll(boolean lazy) {
        super(lazy);
    }

    public DepartmentChildrenAll(Department department, Enterprise enterprise) {
        //b= true;
        this.department = department;
        this.enterprise = enterprise;
    }

    @Override
    protected Node[] createNodes(Object key) {     
        final Department department1 = (Department) key;
        //Node rootNode = new AbstractNode(new PersonChildren(person1,enterprise),Lookups.singleton(department1));    

        if (department1.getEnterpriseIdActual()==0){
            Node rootNode = null;
            rootNode = new AbstractNode(new PersonParentAll(department,enterprise),Lookups.singleton(department1)){
            @Override
            protected Sheet createSheet() {
                Sheet sheet = Sheet.createDefault();
                Sheet.Set set = Sheet.createPropertiesSet();
                this.setIconBaseWithExtension("vn/com/hkt/tree/ui/department/department.gif");
                sheet.put(set);
                return sheet;
            }
        };

            rootNode.setDisplayName("Person");
            return new Node[]{rootNode};
        }
        else{
        Node result = new AbstractNode(new DepartmentChildrenAll(department1, enterprise), Lookups.singleton(department1)) {

            @Override
            protected Sheet createSheet() {
                Sheet sheet = Sheet.createDefault();
                Sheet.Set set = Sheet.createPropertiesSet();

                Property giamDocProperty = new PropertySupport.ReadOnly<String>(Giamdoc_PROPERTY, String.class, "Trưởng phòng", "aaa") {

                    @Override
                    public String getValue() throws IllegalAccessException, InvocationTargetException {
                        Person person= new PersonBN().getById(department1.getPersonIdActual());
                        return person.getPersonName();
                    }

                    @Override
                    public PropertyEditor getPropertyEditor() {
                        return new PropertyEditorSupport();
                    }
                };
                set.put(giamDocProperty);
                Property congTyMeProperty =
                        new PropertySupport.ReadOnly<String>(Congtyme_PROPERTY, String.class, "Công ty", "bbb") {

                            @Override
                            public String getValue() throws IllegalAccessException, InvocationTargetException {
                                Department department= new DepartmentBN().getById(department1.getId());
                                return department.getDepartmentName();
                            }

                            @Override
                            public PropertyEditor getPropertyEditor() {
                                return new PropertyEditorSupport();
                            }
                        };
                set.put(congTyMeProperty);
                sheet.put(set);
                return sheet;
            }
        };

        result.setDisplayName(department1.getDepartmentName());
        return new Node[]{result};
        }
    }

    @Override
    protected void addNotify() {
        if (enterprise != null) {
            list = departmentBN.filterDepartmentByEnterprise(enterprise);
            super.addNotify();
            Vector instr = new Vector();
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getDepartmentParentIdAcutal()==(department.getId())) {
                    Department department1 = new Department();
                    department1.setDepartmentName(list.get(i).getDepartmentName());
                    department1.setPersonIdActual(list.get(i).getPersonIdActual());
                    department1.setEnterpriseIdActual(list.get(i).getEnterpriseIdActual());
                    department1.setDepartmentParentIdAcutal(list.get(i).getDepartmentParentIdAcutal());
                    department1.setDepartmentId(list.get(i).getDepartmentId());
                    instr.addElement(department1);
                }
            }

            Department[] departments = new Department[instr.size()+1];
            departments[0] = new Department("",department.getDepartmentName(),0,0,0,0,null);
            for (int i = 1; i <= instr.size(); i++) {
                departments[i] = (Department) instr.elementAt(i-1);
            }
            setKeys(departments);

        }

    }
}

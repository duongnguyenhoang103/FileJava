/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.tree.ui.departmentAll;

import java.beans.PropertyEditor;
import java.beans.PropertyEditorSupport;
import java.lang.String;
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
import vn.com.hkt.basic.api.IPersonBN;
import vn.com.hkt.erm.enterprise.dao.EnterpriseBN;
import vn.com.hkt.pilot.entities.Department;
import vn.com.hkt.pilot.entities.Enterprise;
import vn.com.hkt.pilot.entities.Mission;
import vn.com.hkt.pilot.entities.Person;

/**
 *
 * @author VietAnh
 */
public class PersonChildrenAll extends Children.Keys {

    private List<Person> list = new ArrayList<Person>();
    private IPersonBN personBN = Lookup.getDefault().lookup(IPersonBN.class);
    public static String Giamdoc_PROPERTY = "TruongPhong";
    public static String Congtyme_PROPERTY = "CongTy";
    private Person person = null;
    private Department department;
    private Mission mission;
    Enterprise enterprise;

    public PersonChildrenAll(boolean lazy) {
        super(lazy);
    }

    public PersonChildrenAll(Mission m, Department department, Enterprise enterprise) {
        this.mission = m;
        this.department = department;
        this.enterprise = enterprise;


    }

    public PersonChildrenAll(Person p, Department department, Mission m) {
        this.mission = m;
        this.person = p;
        this.department = department;
    }

    public PersonChildrenAll(Department department) {

        this.department = department;
    }

    @Override
    protected Node[] createNodes(Object key) {

        final Person person1 = (Person) key;


        Node result = new AbstractNode(new PersonChildrenAll(person1, department, mission), Lookups.singleton(person1)) {

            @Override
            protected Sheet createSheet() {
                Sheet sheet = Sheet.createDefault();
                Sheet.Set set = Sheet.createPropertiesSet();

                //JOptionPane.showMessageDialog(null, "Create Nodes");
                Property giamDocProperty = new PropertySupport.ReadOnly<String>(Giamdoc_PROPERTY, String.class, "Trưởng phòng", "aaa") {

                    @Override
                    public String getValue() throws IllegalAccessException, InvocationTargetException {
                        //JOptionPane.showMessageDialog(null, "Create Node 2");
                        return person1.getPersonName();
                    }

                    @Override
                    public PropertyEditor getPropertyEditor() {
//                        JOptionPane.showMessageDialog(null, "Create Node property editor");
                        return new PropertyEditorSupport();
                    }
                };

                Property congTyMeProperty =
                        new PropertySupport.ReadOnly<String>(Congtyme_PROPERTY, String.class, "Công ty", "bbb") {

                            @Override
                            public String getValue() throws IllegalAccessException, InvocationTargetException {
                                //JOptionPane.showMessageDialog(null, "get last name"+ person1.getLastName());
                                Enterprise enter = new EnterpriseBN().getById(person1.getEnterpriseIdActual());
                                return enter.getEnterpriseName();
                            }

                            @Override
                            public PropertyEditor getPropertyEditor() {
                                return new PropertyEditorSupport();
                            }
                        };
                set.put(giamDocProperty);
                set.put(congTyMeProperty);







                this.setIconBaseWithExtension("vn/com/hkt/tree/ui/department/a.gif");
                sheet.put(set);
                return sheet;
            }
        };


        result.setDisplayName(person1.getPersonName());
        return new Node[]{result};

    }

    @Override
    protected void addNotify() {
        if (person != null) {
            Person[] persons = new Person[0];
            setKeys(persons);
        } 
        else if (department.getId()!=0) {
            list = personBN.selectAll();
            super.addNotify();
            Vector instr = new Vector();
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getDepartmentIdActual()==(department.getId()) 
                        && list.get(i).getMissionIdActual()==(mission.getId())) {
                    Person person1 = new Person();
                    person1.setDepartmentIdActual(list.get(i).getDepartmentIdActual());
                    person1.setEnterpriseIdActual(list.get(i).getEnterpriseIdActual());
                    person1.setPersonName(list.get(i).getPersonName());
                    person1.setPersonId(list.get(i).getPersonId());
                    instr.addElement(person1);
                }
            }

            Person[] persons = new Person[instr.size()];
            for (int i = 0; i < instr.size(); i++) {
                persons[i] = (Person) instr.elementAt(i);
            }
            setKeys(persons);
        } else {
            list = personBN.selectAll();
            super.addNotify();
            Vector instr = new Vector();
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getEnterpriseIdActual()==(enterprise.getId()) 
                        && list.get(i).getMissionIdActual()==(mission.getId())
                        && list.get(i).getDepartmentIdActual()==(department.getId())) {
                    Person person1 = new Person();
                    person1.setDepartmentIdActual(list.get(i).getDepartmentIdActual());
                    person1.setEnterpriseIdActual(list.get(i).getEnterpriseIdActual());
                    person1.setPersonName(list.get(i).getPersonName());
                    person1.setPersonId(list.get(i).getPersonId());
                    person1.setCountryIdActual(list.get(i).getCountryIdActual());
                    instr.addElement(person1);
                }

            }

            Person[] persons = new Person[instr.size()];
            for (int i = 0; i < instr.size(); i++) {
                persons[i] = (Person) instr.elementAt(i);
            }
            setKeys(persons);
        }

    }
}

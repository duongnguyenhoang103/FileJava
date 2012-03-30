/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.tree.ui.enterprise;

import java.beans.PropertyEditor;
import java.beans.PropertyEditorSupport;
import vn.com.hkt.pilot.entities.Enterprise;
import vn.com.hkt.basic.api.IEnterpriseBN;
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
import vn.com.hkt.erm.enterprise.dao.EnterpriseBN;
import vn.com.hkt.hrm.person.dao.PersonBN;
import vn.com.hkt.pilot.entities.Person;

/**
 *
 * @author longnt
 */
public class EnterpriseChildren extends Children.Keys {

    private Enterprise enterprise;
    List<Enterprise> list = new ArrayList<Enterprise>();
    private IEnterpriseBN enterpriseBN = Lookup.getDefault().lookup(IEnterpriseBN.class);
    public static String Giamdoc_PROPERTY = "GiamDoc";
    public static String Congtyme_PROPERTY = "CongTyMe";

    public EnterpriseChildren(boolean lazy) {
        super(lazy);
    }

    public EnterpriseChildren(Enterprise enterprise) {
        this.enterprise = enterprise;
    }

    public EnterpriseChildren() {
        list = enterpriseBN.selectAll();
    }

    @Override
    protected Node[] createNodes(Object key) {

        final Enterprise enterprise1 = (Enterprise) key;
        Node result;
        result = new AbstractNode(new EnterpriseChildren(enterprise1), Lookups.singleton(enterprise1)) {

            @Override
            protected Sheet createSheet() {
                Sheet sheet = Sheet.createDefault();
                Sheet.Set set = Sheet.createPropertiesSet();

                Property giamDocProperty = new PropertySupport.ReadOnly<String>(Giamdoc_PROPERTY, String.class, "Giám đốc", "aaa") {

                    @Override
                    public String getValue() throws IllegalAccessException, InvocationTargetException {
                        Person p= new PersonBN().getById(enterprise1.getDirectorIdActual());
                        return p.getPersonName();
                    }

                    @Override
                    public PropertyEditor getPropertyEditor() {
                        return new PropertyEditorSupport();
                    }
                };
                set.put(giamDocProperty);
                Property congTyMeProperty =
                        new PropertySupport.ReadOnly<String>(Congtyme_PROPERTY, String.class, "Công ty mẹ", "bbb") {

                            @Override
                            public String getValue() throws IllegalAccessException, InvocationTargetException {
                                Enterprise enterprise= new EnterpriseBN().getById(enterprise1.getEnterpriseParentIdActual());
                                return enterprise.getEnterpriseName();
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

        result.setDisplayName(enterprise1.getEnterpriseName());
        return new Node[]{result};

    }

    @Override
    protected void addNotify() {
        list = enterpriseBN.selectAll();
        super.addNotify();
        Vector instr = new Vector();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getEnterpriseParentIdActual()==enterprise.getId()) {
                Enterprise enterprise1 = new Enterprise();
                enterprise1.setEnterpriseName(list.get(i).getEnterpriseName());
                enterprise1.setDirectorIdActual(list.get(i).getDirectorIdActual());
                enterprise1.setEnterpriseParentIdActual(list.get(i).getEnterpriseParentIdActual());
                instr.addElement(enterprise1);
            }

        }

        Enterprise[] enterprises = new Enterprise[instr.size()];
        for (int i = 0; i < instr.size(); i++) {
            enterprises[i] = (Enterprise) instr.elementAt(i);
        }
        setKeys(enterprises);

    }
}

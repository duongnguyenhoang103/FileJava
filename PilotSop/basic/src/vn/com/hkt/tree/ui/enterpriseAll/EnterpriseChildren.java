/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.tree.ui.enterpriseAll;

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
import vn.com.hkt.tree.ui.departmentAll.DepartmentParentAll;
import vn.com.hkt.tree.ui.product.ProductParent;

/**
 *
 * @author longnt
 */
public class EnterpriseChildren extends Children.Keys {

    private Enterprise enterprise;
    List<Enterprise> list = new ArrayList<Enterprise>();
    private IEnterpriseBN enterpriseBN = Lookup.getDefault().lookup(IEnterpriseBN.class);
    public static String Giamdoc_PROPERTY = "TruongPhong";
    public static String Congtyme_PROPERTY = "CongTy";
    static int count= 0;

    public EnterpriseChildren(boolean lazy) {
        super(lazy);
    }

    public EnterpriseChildren(Enterprise enterprise) {
        this.enterprise = enterprise;
    }

    public EnterpriseChildren() {
        list = enterpriseBN.getAllEnterprise();
    }

    @Override
    protected Node[] createNodes(Object key) {
        final Enterprise enterprise1 = (Enterprise) key;
        if (count==0){
            count++;
            Node rootNode = null;
            rootNode = new AbstractNode(new DepartmentParentAll(enterprise),Lookups.singleton(enterprise)){
            @Override
            protected Sheet createSheet() {
                Sheet sheet = Sheet.createDefault();
                Sheet.Set set = Sheet.createPropertiesSet();
                this.setIconBaseWithExtension("vn/com/hkt/tree/ui/enterpriseAll/department.gif");
                sheet.put(set);
                return sheet;
            }
        };
            rootNode.setDisplayName("Department");
            return new Node[]{rootNode};          
        }        
        else
            if (count==1){
                count++;
                Node rootNode = null;
            rootNode = new AbstractNode(new ProductParent(enterprise),Lookups.singleton(enterprise)){
            @Override
            protected Sheet createSheet() {
                Sheet sheet = Sheet.createDefault();
                Sheet.Set set = Sheet.createPropertiesSet();
                this.setIconBaseWithExtension("vn/com/hkt/tree/ui/enterpriseAll/product.gif");
                sheet.put(set);
                return sheet;
            }
        };
            rootNode.setDisplayName("Product");
            return new Node[]{rootNode};    
            }
        
        else {
            Node result;
            result = new AbstractNode(new EnterpriseChildren(enterprise1), Lookups.singleton(enterprise1))
            {

                @Override
                protected Sheet createSheet() {
                    Sheet sheet = Sheet.createDefault();
                    Sheet.Set set = Sheet.createPropertiesSet();

                    Property giamDocProperty = new PropertySupport.ReadOnly<String>(Giamdoc_PROPERTY, String.class, "Trưởng phòng", "aaa") {

                        @Override
                        public String getValue() throws IllegalAccessException, InvocationTargetException {
                            return enterprise1.getDirector();
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
                                    return enterprise1.getEnterpriseParent();
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
    }

    @Override
    protected void addNotify() {
        count = 0 ;
        list = enterpriseBN.getAllEnterprise();
        super.addNotify();
        Vector instr = new Vector();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getEnterpriseParent().equals(enterprise.getEnterpriseName())) {
                Enterprise enterprise1 = new Enterprise();
                enterprise1.setEnterpriseID(list.get(i).getEnterpriseID());
                enterprise1.setEnterpriseName(list.get(i).getEnterpriseName());
                enterprise1.setDirector(list.get(i).getDirector());
                enterprise1.setEnterpriseParent(list.get(i).getEnterpriseParent());
                instr.addElement(enterprise1);
            }
        }
        Enterprise[] enterprises = new Enterprise[instr.size()+2];
        enterprises[0] = new Enterprise("","","","",null,"");
        enterprises[1] = new Enterprise("","","","",null,"");
        for (int i = 2; i < instr.size()+2; i++) {
            enterprises[i] = (Enterprise) instr.elementAt(i-2);
        }
        setKeys(enterprises);

    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.tree.ui.product;

import java.beans.PropertyEditor;
import java.beans.PropertyEditorSupport;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.openide.nodes.Node;
import org.openide.nodes.Node.Property;
import org.openide.nodes.PropertySupport;
import org.openide.nodes.Sheet;
import org.openide.util.Lookup;
import org.openide.util.lookup.Lookups;
import vn.com.hkt.basic.api.IProductBN;
import vn.com.hkt.erm.enterprise.dao.EnterpriseBN;
import vn.com.hkt.hrm.person.dao.PersonBN;
import vn.com.hkt.pilot.entities.Enterprise;
import vn.com.hkt.pilot.entities.Person;
import vn.com.hkt.pilot.entities.Product;

/**
 *
 * @author longnt
 */
public class ProductParent extends Children.Keys {

    private List<Product> list = new ArrayList<Product>();
    private IProductBN productBN = Lookup.getDefault().lookup(IProductBN.class);
    public static String Giamdoc_PROPERTY = "TruongPhong";
    public static String Congtyme_PROPERTY = "CongTy";
    private Enterprise enterprise;

    public ProductParent(boolean lazy) {
        super(lazy);
    }

    public ProductParent(Enterprise enterprise) {
        this.enterprise = enterprise;

    }

    @Override
    protected Node[] createNodes(Object key) {
        final Product product1 = (Product) key;
        Node result = new AbstractNode(new ProductChildren(product1, enterprise), Lookups.singleton(product1)) {

            @Override
            protected Sheet createSheet() {
                Sheet sheet = Sheet.createDefault();
                Sheet.Set set = Sheet.createPropertiesSet();

                Property giamDocProperty = new PropertySupport.ReadOnly<String>(Giamdoc_PROPERTY, String.class, "Người chịu trách nhiệm", "aaa") {

                    @Override
                    public String getValue() throws IllegalAccessException, InvocationTargetException {
                        Person person = new PersonBN().getById(product1.getPersonDesignIdActual());
                        return person.getPersonName();
                    }

                    @Override
                    public PropertyEditor getPropertyEditor() {
                        return new PropertyEditorSupport();
                    }
                };
                set.put(giamDocProperty);
                Property congTyMeProperty =
                        new PropertySupport.ReadOnly<String>(Congtyme_PROPERTY, String.class, "Công Ty", "bbb") {

                            @Override
                            public String getValue() throws IllegalAccessException, InvocationTargetException {
                                Enterprise enter = new EnterpriseBN().getById(product1.getEnterpriseIdActual());
                                return enter.getEnterpriseName();
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
        result.setDisplayName(product1.getProductName());
        return new Node[]{result};
    }

    @Override
    protected void addNotify() {
        super.addNotify();
        if (enterprise != null) {
            list = productBN.getByEnterprise(enterprise);
            Vector instr = new Vector();
            for (Product bean : list) {
                if (bean.getProductGroupIdActual() == 0) {
                    instr.addElement(bean);
                }
            }

            Product[] products = new Product[instr.size()];
            for (int i = 0; i < instr.size(); i++) {
                products[i] = (Product) instr.elementAt(i);
            }
            setKeys(products);
        }
    }
}

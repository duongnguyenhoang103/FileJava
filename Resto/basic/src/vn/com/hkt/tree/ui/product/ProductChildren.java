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
import org.openide.nodes.PropertySupport;
import org.openide.nodes.Sheet;
import org.openide.util.Lookup;
import org.openide.util.lookup.Lookups;
import vn.com.hkt.basic.api.IProductBN;
import vn.com.hkt.erm.enterprise.dao.EnterpriseBN;
import vn.com.hkt.hrm.person.dao.PersonBN;
import vn.com.hkt.pilot.entities.Product;
import vn.com.hkt.pilot.entities.Enterprise;
import vn.com.hkt.pilot.entities.Person;

/**
 *
 * @author longnt
 */
public class ProductChildren extends Children.Keys {

    private List<Product> list = new ArrayList<Product>();
    private IProductBN ProductBN = Lookup.getDefault().lookup(IProductBN.class);
    public static String Giamdoc_PROPERTY = "TruongPhong";
    public static String Congtyme_PROPERTY = "CongTy";
    private Product product;
    private Enterprise enterprise;

    public ProductChildren(boolean lazy) {
        super(lazy);
    }

    public ProductChildren(Product product, Enterprise enterprise) {
        this.product = product;
        this.enterprise = enterprise;
    }

    @Override
    protected Node[] createNodes(Object key) {

        final Product product1 = (Product) key;
        Node result;
        result = new AbstractNode(new ProductChildren(product1, enterprise), Lookups.singleton(product1)) {

            @Override
            protected Sheet createSheet() {
                Sheet sheet = Sheet.createDefault();
                Sheet.Set set = Sheet.createPropertiesSet();

                Property giamDocProperty = new PropertySupport.ReadOnly<String>(Giamdoc_PROPERTY, String.class, "Trưởng phòng", "aaa") {

                    @Override
                    public String getValue() throws IllegalAccessException, InvocationTargetException {
                        Person person= new PersonBN().getById(product1.getPersonDesignIdActual());
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
                                Enterprise enter= new EnterpriseBN().getById(product1.getId());
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
        if (enterprise != null) {
            list = ProductBN.getByEnterprise(enterprise);
            super.addNotify();
            Vector instr = new Vector();
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getProductGroupIdActual()==(product.getId())) {
                    Product product1 = new Product();
                    product1.setProductName(list.get(i).getProductName());
                    product1.setEnterpriseIdActual(list.get(i).getEnterpriseIdActual());
                    product1.setPersonDesignIdActual(list.get(i).getPersonDesignIdActual());
                    product1.setProductGroupIdActual(list.get(i).getProductGroupIdActual());
                    instr.addElement(product1);
                }

            }

            Product[] Products = new Product[instr.size()];
            for (int i = 0; i < instr.size(); i++) {
                Products[i] = (Product) instr.elementAt(i);
            }
            setKeys(Products);

        }

    }
}

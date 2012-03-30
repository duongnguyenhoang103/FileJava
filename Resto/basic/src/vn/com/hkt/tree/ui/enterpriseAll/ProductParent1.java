/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.tree.ui.enterpriseAll;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import org.openide.nodes.Children;
import org.openide.nodes.Node;
import org.openide.util.Lookup;
import vn.com.hkt.basic.api.IProductBN;
import vn.com.hkt.pilot.entities.Enterprise;
import vn.com.hkt.pilot.entities.Product;

/**
 *
 * @author VietAnh
 */
public class ProductParent1 extends Children.Keys{
    Enterprise enterprise;
    IProductBN productBN = Lookup.getDefault().lookup(IProductBN.class);
    List<Product> list = new ArrayList<Product>();

    public ProductParent1(Enterprise e){
        this.enterprise = e;
    }
    
    @Override
    protected Node[] createNodes(Object t) {
        final Product product1 = (Product) t;
        Node result = null;
        result.setDisplayName(product1.getProductName());
        return new Node[]{result};
        
    }
    
    @Override
    protected void addNotify(){
        list = productBN.selectAll();
        super.addNotify();
        Vector instr = new Vector();
        for (int i=0;i< list.size();i++)
        if (list.get(i).getEnterpriseIdActual()==(enterprise.getId())) {
            Product p = new Product();
            p.setDepartmentIdActual(list.get(i).getDepartmentIdActual());
            p.setEnterpriseIdActual(list.get(i).getEnterpriseIdActual());
            p.setProductId(list.get(i).getProductId());
            p.setProductName(list.get(i).getProductName());
            instr.addElement(p);
        }
        
        Product[] products = new Product[instr.size()];
        for (int i=0;i<instr.size();i++) {
            products[i]= (Product) instr.get(i);
        }
        setKeys(products);
        
    }
    
}

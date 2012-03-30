/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.pilot.sb41.product.extW48.dao;

import java.util.ArrayList;
import java.util.List;
import org.openide.util.Lookup;
import vn.com.hkt.basic.api.IProductBN;
import vn.com.hkt.pilot.entities.Enterprise;
import vn.com.hkt.pilot.entities.Product;
import vn.com.hkt.pilot.identity.access.api.IAccessData;
import vn.com.hkt.pilot.identity.access.spi.AccessData;
import vn.com.hkt.pilot.sb41.generic.jpas.utils.PersistenceUtility;
import vn.com.hkt.pilot.sb41.product.extW48.entity.ProductExt_W48;

/**
 *
 * @author duong
 */
public class ProductExtW48_BN extends AccessData<ProductExt_W48> {

    private IProductBN productBN = Lookup.getDefault().lookup(IProductBN.class);

    public ProductExtW48_BN() {
        setAccessData(PersistenceUtility.getEmf(), ProductExt_W48.class);
    }

    public List<ProductExt_W48> searchProductExt_W48ById(String key) {
        return select(ProductExt_W48.FILED_PRODUCT_ID_ACTUAL, key);
    }

    public List<ProductExt_W48> filterProductExt_W23ByID(String id) {
        return filter(ProductExt_W48.FILED_PRODUCT_ID_ACTUAL, id);
    }

    public List<ProductExt_W48> filterProductExt_W23ByName(String name) {
        List<ProductExt_W48> list = selectAll();
        List<ProductExt_W48> ext_W48s = new ArrayList<ProductExt_W48>();
        IAccessData accessProduct = new Product().getAccessDataOfEntity();
        for (int i = 0; i < list.size(); i++) {
            Product p = (Product) accessProduct.getById(list.get(i).getProductIdActual());
            if (p != null && p.getProductName().contains(name)) {
                ext_W48s.add(list.get(i));
            }
        }
        return ext_W48s;
    }

    public List<ProductExt_W48> getByEnterpriser(Enterprise e) {
        List<ProductExt_W48> list = selectAll();
        List<ProductExt_W48> lr = new ArrayList<ProductExt_W48>();
        for (int i = 0; i < list.size(); i++) {
            Product p = productBN.getById(list.get(i).getProductIdActual());
            if (p.getEnterpriseIdActual() == e.getId()) {
                lr.add(list.get(i));
            }
        }
        return lr;
    }
}

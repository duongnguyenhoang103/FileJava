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
import vn.com.hkt.pilot.identity.access.spi.AccessData;
import vn.com.hkt.pilot.sb41.generic.jpas.utils.PersistenceUtility;
import vn.com.hkt.pilot.sb41.product.extW48.entity.ProductExt_W49;

/**
 *
 * @author BinLe
 */
public class ProductExt_W49BN extends AccessData<ProductExt_W49> {

    private IProductBN productBN = Lookup.getDefault().lookup(IProductBN.class);

    public ProductExt_W49BN() {
        setAccessData(PersistenceUtility.getEmf(), ProductExt_W49.class);
    }

    public List<ProductExt_W49> getByEnterpriser(Enterprise e) {
        List<ProductExt_W49> list = selectAll();
        List<ProductExt_W49> lr = new ArrayList<ProductExt_W49>();
        for (int i = 0; i < list.size(); i++) {
            Product p = productBN.getById(list.get(i).getProductIdActual());
            if (p.getEnterpriseIdActual() == e.getId()) {
                lr.add(list.get(i));
            }
        }
        return lr;
    }
}

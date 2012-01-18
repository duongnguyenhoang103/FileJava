/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.product.extW47.dao;

import java.util.ArrayList;
import java.util.List;
import org.openide.util.Lookup;
import vn.com.hkt.generic.api.IGenericAPI_extProductW47;
import vn.com.hkt.product.extW47.entity.ProductExt_W47;

/**
 *
 * @author duong
 */
public class ProductExtW47_DAO {

    private IGenericAPI_extProductW47 mydao;

    public ProductExtW47_DAO() {
        mydao = Lookup.getDefault().lookup(IGenericAPI_extProductW47.class);
    }

    public boolean insertProductExt_W47(ProductExt_W47 producttExtW47) {
        if (mydao.insertData(producttExtW47)) {
            return true;
        }
        return false;
    }

    public boolean udateDProductExt_W47(ProductExt_W47 producttExtW47) {
        if (mydao.updateData(producttExtW47)) {
            return true;
        }
        return false;
    }

    public boolean deleteProductExt_W47(ProductExt_W47 producttExtW47) {
        if (mydao.deleteData(ProductExt_W47.class, producttExtW47.getProductID())) {
            return true;
        }
        return false;
    }

    public List<ProductExt_W47> getAllProductExt_W47() {
        List<ProductExt_W47> list = new ArrayList<ProductExt_W47>();
        List<Object> obs = new ArrayList<Object>();
        obs = mydao.getAllData(ProductExt_W47.class);
        if (!obs.isEmpty()) {
            int i;
            for (i = 0; i < obs.size(); i++) {
                list.add((ProductExt_W47) obs.get(i));
            }
        }
        return list;
    }

    public ProductExt_W47 getProductExt_W47(String id) {
        return (ProductExt_W47) mydao.getByID(ProductExt_W47.class, id);
    }

    public List<ProductExt_W47> SearchProductExt_W47ByName(String key) {
        List<ProductExt_W47> list = new ArrayList<ProductExt_W47>();
        List<Object> obs = new ArrayList<Object>();
        obs = mydao.SearchByCondition(ProductExt_W47.class, "ProductID", key);
        if (!obs.isEmpty()) {
            int i;
            for (i = 0; i < obs.size(); i++) {
                list.add((ProductExt_W47) obs.get(i));
            }
        }
        return list;
    }
}

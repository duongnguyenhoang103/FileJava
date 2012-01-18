/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.product.extW49.dao;

import java.util.ArrayList;
import java.util.List;
import org.openide.util.Lookup;
import vn.com.hkt.generic.api.IGenericAPI_extProductW49;
import vn.com.hkt.product.extW49.entity.ProductExt_W49;

/**
 *
 * @author duong
 */
public class ProductExtW49_DAO {

    private IGenericAPI_extProductW49 mydao;

    public ProductExtW49_DAO() {
        mydao = Lookup.getDefault().lookup(IGenericAPI_extProductW49.class);
    }

    public boolean insertProductExt_W49(ProductExt_W49 productExtW49) {
        if (mydao.insertData(productExtW49)) {
            return true;
        }
        return false;
    }

    public boolean udateProductExt_W49(ProductExt_W49 productExtW49) {
        if (mydao.updateData(productExtW49)) {
            return true;
        }
        return false;
    }

    public boolean deleteProductExt_W49(ProductExt_W49 productExtW49) {
        if (mydao.deleteData(ProductExt_W49.class, productExtW49.getProductID())) {
            return true;
        }
        return false;
    }

    public List<ProductExt_W49> getAllProductExt_W49() {
        List<ProductExt_W49> list = new ArrayList<ProductExt_W49>();
        List<Object> obs = new ArrayList<Object>();
        obs = mydao.getAllData(ProductExt_W49.class);
        if (!obs.isEmpty()) {
            int i;
            for (i = 0; i < obs.size(); i++) {
                list.add((ProductExt_W49) obs.get(i));
            }
        }
        return list;
    }

    public ProductExt_W49 getProductExt_W49(String id) {
        return (ProductExt_W49) mydao.getByID(ProductExt_W49.class, id);
    }

    public List<ProductExt_W49> SearchProductExt_W49ByName(String key) {
        List<ProductExt_W49> list = new ArrayList<ProductExt_W49>();
        List<Object> obs = new ArrayList<Object>();
        obs = mydao.SearchByCondition(ProductExt_W49.class, "ProductID", key);
        if (!obs.isEmpty()) {
            int i;
            for (i = 0; i < obs.size(); i++) {
                list.add((ProductExt_W49) obs.get(i));
            }
        }
        return list;
    }
}

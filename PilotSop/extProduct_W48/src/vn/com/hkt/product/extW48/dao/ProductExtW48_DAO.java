/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.product.extW48.dao;

import java.util.ArrayList;
import java.util.List;
import org.openide.util.Lookup;
import vn.com.hkt.generic.api.IGenericAPI_extProductW48;
import vn.com.hkt.product.extW48.entity.ProductExt_W48;

/**
 *
 * @author duong
 */
public class ProductExtW48_DAO {

    private IGenericAPI_extProductW48 mydao;

    public ProductExtW48_DAO() {
        mydao = Lookup.getDefault().lookup(IGenericAPI_extProductW48.class);
    }

    public boolean insertProductExt_W48(ProductExt_W48 productExtW48) {
        if (mydao.insertData(productExtW48)) {
            return true;
        }
        return false;
    }

    public boolean udateProductExt_W48(ProductExt_W48 productExtW48) {
        if (mydao.updateData(productExtW48)) {
            return true;
        }
        return false;
    }

    public boolean deleteProductExt_W48(ProductExt_W48 productExtW48) {
        if (mydao.deleteData(ProductExt_W48.class, productExtW48.getProductID())) {
            return true;
        }
        return false;
    }

    public List<ProductExt_W48> getAllProductExt_W48() {
        List<ProductExt_W48> list = new ArrayList<ProductExt_W48>();
        List<Object> obs = new ArrayList<Object>();
        obs = mydao.getAllData(ProductExt_W48.class);
        if (!obs.isEmpty()) {
            int i;
            for (i = 0; i < obs.size(); i++) {
                list.add((ProductExt_W48) obs.get(i));
            }
        }
        return list;
    }

    public ProductExt_W48 getProductExt_W48(String id) {
        return (ProductExt_W48) mydao.getByID(ProductExt_W48.class, id);
    }

    public List<ProductExt_W48> SearchProductExt_W48ByName(String key) {
        List<ProductExt_W48> list = new ArrayList<ProductExt_W48>();
        List<Object> obs = new ArrayList<Object>();
        obs = mydao.SearchByCondition(ProductExt_W48.class, "ProductID", key);
        if (!obs.isEmpty()) {
            int i;
            for (i = 0; i < obs.size(); i++) {
                list.add((ProductExt_W48) obs.get(i));
            }
        }
        return list;
    }
}

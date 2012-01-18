/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.product.extW52.dao;

import java.util.ArrayList;
import java.util.List;
import org.openide.util.Lookup;
import vn.com.hkt.generic.api.IGenericAPI_extProductW52;
import vn.com.hkt.product.extW52.entity.ProductExt_W52;

/**
 *
 * @author duong
 */
public class ProductExtW52_DAO {

    private IGenericAPI_extProductW52 mydao;

    public ProductExtW52_DAO() {
        mydao = Lookup.getDefault().lookup(IGenericAPI_extProductW52.class);
    }

    public boolean insertProductExt_W52(ProductExt_W52 productExtW52) {
        if (mydao.insertData(productExtW52)) {
            return true;
        }
        return false;
    }

    public boolean udateProductExt_W52(ProductExt_W52 productExtW52) {
        if (mydao.updateData(productExtW52)) {
            return true;
        }
        return false;
    }

    public boolean deleteProductExt_W52(ProductExt_W52 productExtW52) {
        if (mydao.deleteData(ProductExt_W52.class, productExtW52.getProductID())) {
            return true;
        }
        return false;
    }

    public List<ProductExt_W52> getAllProductExt_W52() {
        List<ProductExt_W52> list = new ArrayList<ProductExt_W52>();
        List<Object> obs = new ArrayList<Object>();
        obs = mydao.getAllData(ProductExt_W52.class);
        if (!obs.isEmpty()) {
            int i;
            for (i = 0; i < obs.size(); i++) {
                list.add((ProductExt_W52) obs.get(i));
            }
        }
        return list;
    }

    public ProductExt_W52 getProductExt_W52(String id) {
        return (ProductExt_W52) mydao.getByID(ProductExt_W52.class, id);
    }

    public List<ProductExt_W52> SearchProductExt_W52ByName(String key) {
        List<ProductExt_W52> list = new ArrayList<ProductExt_W52>();
        List<Object> obs = new ArrayList<Object>();
        obs = mydao.SearchByCondition(ProductExt_W52.class, "ProductID", key);
        if (!obs.isEmpty()) {
            int i;
            for (i = 0; i < obs.size(); i++) {
                list.add((ProductExt_W52) obs.get(i));
            }
        }
        return list;
    }
}

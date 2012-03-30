/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.bom.product.dao;

import java.util.ArrayList;
import java.util.List;
import org.openide.util.lookup.ServiceProvider;
import vn.com.hkt.basic.api.IProductBN;
import vn.com.hkt.basic.jpa.PersistenceUltility;
import vn.com.hkt.pilot.entities.Department;
import vn.com.hkt.pilot.entities.Enterprise;
import vn.com.hkt.pilot.entities.Product;
import vn.com.hkt.pilot.identity.access.spi.AccessData;

/**
 *
 * @author khangphamngoc
 */
@ServiceProvider(service = IProductBN.class)
public class ProductBN extends AccessData<Product> implements IProductBN {

    public ProductBN() {
        setAccessData(PersistenceUltility.getEMF(), Product.class);
    }
   
    @Override
    public List<Enterprise> enterpriseHasProduct() {
        try {
            String sql = "SELECT enter  FROM " + Enterprise.class.getSimpleName() + " enter"
                    + " where enter." + Enterprise.FILED_ID + " IN "
                    + "(Select sp." + Product.FIELD_ENTERPRISE_ID_ACTUAL + " from " + Product.class.getSimpleName() + " sp)";
            List<Object> list = queryList(sql);
            List<Enterprise> le = new ArrayList<Enterprise>();
            for (int i = 0; i < list.size(); i++) {
                le.add((Enterprise) list.get(i));
            }
            return le;
        } catch (Exception e) {
            return new ArrayList<Enterprise>();
        }
    }

    @Override
    public List<Product> getByEnterprise(Enterprise enterprise) {
        return select(Product.FIELD_ENTERPRISE_ID_ACTUAL, String.valueOf(enterprise.getId()));
    }

    @Override
    public List<Product> filterProductByDepartment(Enterprise enterprise, Department department) {
        List<String> listFieldName = new ArrayList<String>();
        listFieldName.add(Product.FIELD_ENTERPRISE_ID_ACTUAL);
        listFieldName.add(Product.FIELD_DEPARTMENT_ID_ACTUAL);
        List<String> listVaule = new ArrayList<String>();
        listVaule.add(String.valueOf(enterprise.getId()));
        listVaule.add(String.valueOf(department.getId()));
        return filter(listFieldName, listVaule);
    }
}

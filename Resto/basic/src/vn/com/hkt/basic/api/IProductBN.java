/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.basic.api;

import vn.com.hkt.pilot.entities.Product;
import java.util.List;
import vn.com.hkt.pilot.entities.Department;
import vn.com.hkt.pilot.entities.Enterprise;
import vn.com.hkt.pilot.identity.access.api.IAccessData;

/**
 *
 * @author khanguct
 */
public interface IProductBN extends IAccessData<Product> {
    
    public List<Enterprise> enterpriseHasProduct();

    public List<Product> getByEnterprise(Enterprise enterprise);

    public List<Product> filterProductByDepartment(Enterprise enterprise, Department department);
}

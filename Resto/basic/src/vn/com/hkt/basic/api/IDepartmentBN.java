/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.basic.api;

import vn.com.hkt.pilot.entities.Department;
import java.util.List;
import vn.com.hkt.pilot.entities.Enterprise;
import vn.com.hkt.pilot.identity.access.api.IAccessData;

/**
 *
 * @author khanguct
 */
public interface IDepartmentBN extends IAccessData<Department> {

    public List<Department> filterDepartmentByID(String id);

    public List<Department> filterDepartmentByName(String name);

    public List<Department> filterDepartmentByEnterprise(Enterprise enterprise);

    public List<Enterprise> enterpriseHasDepartment();

    public List<Department> filterDepartmentByRootDepartment(Enterprise enterprise, Department department);
}

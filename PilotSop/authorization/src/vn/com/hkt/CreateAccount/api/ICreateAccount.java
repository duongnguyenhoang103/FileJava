/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.CreateAccount.api;

import java.util.List;
import vn.com.hkt.pilot.entities.Department;
import vn.com.hkt.pilot.entities.Enterprise;
import vn.com.hkt.pilot.entities.Person;

/**
 *
 * @author Admin
 */
public interface ICreateAccount {

    public Enterprise getEnterpriseIDByName(String enterpriseName);

    public List<Department> getDepartmentByEnterprise(String enterpriseID);

    public List<Person> getPersonByEnterpriseAndDepartment(String enterpriseID, String departmentName);
}

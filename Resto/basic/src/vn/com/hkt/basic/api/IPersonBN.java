package vn.com.hkt.basic.api;

import vn.com.hkt.pilot.entities.Person;
import java.util.List;
import vn.com.hkt.pilot.entities.Department;
import vn.com.hkt.pilot.entities.Enterprise;
import vn.com.hkt.pilot.identity.access.api.IAccessData;

/**
 *
 * @author khanguct
 */
public interface IPersonBN extends IAccessData<Person> {      
    
    public List<Person> filterPersonByID(String id);
    
    public List<Person> filterPersonByName(String name);       
    
    public List<Person> filterPersonByEnterprise(Enterprise enterprise);
    
    public List<Person> filterPersonByDepartment(Enterprise enterprise, Department department);
}

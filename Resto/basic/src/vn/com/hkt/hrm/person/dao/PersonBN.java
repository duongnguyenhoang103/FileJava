/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.hrm.person.dao;

import vn.com.hkt.pilot.entities.Department;
import vn.com.hkt.pilot.entities.Enterprise;
import vn.com.hkt.pilot.entities.Person;
import java.util.ArrayList;
import java.util.List;
import org.openide.util.lookup.ServiceProvider;
import vn.com.hkt.basic.api.IPersonBN;
import vn.com.hkt.basic.jpa.PersistenceUltility;
import vn.com.hkt.pilot.identity.access.spi.AccessData;

/**
 *
 * @author khanguct
 */
@ServiceProvider(service = IPersonBN.class)
public class PersonBN extends AccessData<Person> implements IPersonBN {

    public PersonBN() {
        setAccessData(PersistenceUltility.getEMF(), Person.class);
    }

    @Override
    public List<Person> filterPersonByID(String id) {
        return filter(id, id);
    }

    @Override
    public List<Person> filterPersonByName(String name) {
        return filter(Person.FIELD_PERSON_NAME, name);
    }

    @Override
    public List<Person> filterPersonByEnterprise(Enterprise enterprise) {
        return filter(Person.FIELD_ENTERPRISE_ID_ACTUAl, String.valueOf(enterprise.getId()));
    }

    @Override
    public List<Person> filterPersonByDepartment(Enterprise enterprise, Department department) {
        List<String> listFieldName = new ArrayList<String>();
        listFieldName.add(Person.FIELD_ENTERPRISE_ID_ACTUAl);
        listFieldName.add(Person.FIELD_DEPARTMENt_ID_ACTUAL);
        List<String> listVaule = new ArrayList<String>();
        listVaule.add(String.valueOf(enterprise.getId()));
        listVaule.add(String.valueOf(department.getId()));
        return filter(listFieldName, listVaule);
    }
}

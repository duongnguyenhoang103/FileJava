/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.person.extW23.dao;

import java.util.ArrayList;
import java.util.List;
import org.openide.util.Lookup;
import vn.com.hkt.generic.api.IGenericAPI_extPersonW23;
import vn.com.hkt.person.extW23.entity.PersonExt_W23;

/**
 *
 * @author duong
 */
public class PersonExtW23_DAO {

    private IGenericAPI_extPersonW23 mydao;

    public PersonExtW23_DAO() {
        mydao = Lookup.getDefault().lookup(IGenericAPI_extPersonW23.class);
    }

    public boolean insertPersonExt_W22(PersonExt_W23 personExtW23) {
        if (mydao.insertData(personExtW23)) {
            return true;
        }
        return false;
    }

    public boolean udatePersonExt_W22(PersonExt_W23 personExtW23) {
        if (mydao.updateData(personExtW23)) {
            return true;
        }
        return false;
    }

    public boolean deletePersonExt_W23(PersonExt_W23 personExtW23) {
        if (mydao.deleteData(PersonExt_W23.class, personExtW23.getPersonID())) {
            return true;
        }
        return false;
    }

    public List<PersonExt_W23> getAllPersonExt_W23() {
        List<PersonExt_W23> list = new ArrayList<PersonExt_W23>();
        List<Object> obs = new ArrayList<Object>();
        obs = mydao.getAllData(PersonExt_W23.class);
        if (!obs.isEmpty()) {
            int i;
            for (i = 0; i < obs.size(); i++) {
                list.add((PersonExt_W23) obs.get(i));
            }
        }
        return list;
    }

    public PersonExt_W23 getPersonExt_W23ByID(String id) {
        return (PersonExt_W23) mydao.getByID(PersonExt_W23.class, id);
    }

    public List<PersonExt_W23> SearchPersonExt_W23ByName(String key) {
        List<PersonExt_W23> list = new ArrayList<PersonExt_W23>();
        List<Object> obs = new ArrayList<Object>();
        obs = mydao.SearchByCondition(PersonExt_W23.class, "PersonID", key);
        if (!obs.isEmpty()) {
            int i;
            for (i = 0; i < obs.size(); i++) {
                list.add((PersonExt_W23) obs.get(i));
            }
        }
        return list;
    }
}

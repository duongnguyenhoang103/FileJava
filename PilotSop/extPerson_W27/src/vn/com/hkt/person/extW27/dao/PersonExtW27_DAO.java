/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.person.extW27.dao;

import java.util.ArrayList;
import java.util.List;
import org.openide.util.Lookup;
import vn.com.hkt.generic.api.IGenericAPI_extPersonW27;
import vn.com.hkt.person.extW27.entity.PersonExt_W27;

/**
 *
 * @author duong
 */
public class PersonExtW27_DAO {

    private IGenericAPI_extPersonW27 mydao;

    public PersonExtW27_DAO() {
        mydao = Lookup.getDefault().lookup(IGenericAPI_extPersonW27.class);
    }

    public boolean insertPersonExt_W27(PersonExt_W27 personExtW27) {
        if (mydao.insertData(personExtW27)) {
            return true;
        }
        return false;
    }

    public boolean udatePersonExt_W27(PersonExt_W27 personExtW27) {
        if (mydao.updateData(personExtW27)) {
            return true;
        }
        return false;
    }

    public boolean deletePersonExt_W27(PersonExt_W27 personExtW27) {
        if (mydao.deleteData(PersonExt_W27.class, personExtW27.getPersonID())) {
            return true;
        }
        return false;
    }

    public List<PersonExt_W27> getAllPersonExt_W27() {
        List<PersonExt_W27> list = new ArrayList<PersonExt_W27>();
        List<Object> obs = new ArrayList<Object>();
        obs = mydao.getAllData(PersonExt_W27.class);
        if (!obs.isEmpty()) {
            int i;
            for (i = 0; i < obs.size(); i++) {
                list.add((PersonExt_W27) obs.get(i));
            }
        }
        return list;
    }

    public PersonExt_W27 getPersonExt_W27ByID(String id) {
        return (PersonExt_W27) mydao.getByID(PersonExt_W27.class, id);
    }

    public List<PersonExt_W27> SearchPersonExt_W23ByName(String key) {
        List<PersonExt_W27> list = new ArrayList<PersonExt_W27>();
        List<Object> obs = new ArrayList<Object>();
        obs = mydao.SearchByCondition(PersonExt_W27.class, "PersonID", key);
        if (!obs.isEmpty()) {
            int i;
            for (i = 0; i < obs.size(); i++) {
                list.add((PersonExt_W27) obs.get(i));
            }
        }
        return list;
    }
}

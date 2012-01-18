/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.person.extW30.dao;

import java.util.ArrayList;
import java.util.List;
import org.openide.util.Lookup;
import vn.com.hkt.generic.api.IGenericAPI_extPersonW30;
import vn.com.hkt.person.extW30.entity.PersonExt_W30;

/**
 *
 * @author duong
 */
public class PersonExtW30_DAO {

    private IGenericAPI_extPersonW30 mydao;

    public PersonExtW30_DAO() {
        mydao = Lookup.getDefault().lookup(IGenericAPI_extPersonW30.class);
    }

    public boolean insertPersonExt_W30(PersonExt_W30 personExtW30) {
        if (mydao.insertData(personExtW30)) {
            return true;
        }
        return false;
    }

    public boolean udatePersonExt_W30(PersonExt_W30 personExtW30) {
        if (mydao.updateData(personExtW30)) {
            return true;
        }
        return false;
    }

    public boolean deletePersonExt_W30(PersonExt_W30 personExtW30) {
        if (mydao.deleteData(PersonExt_W30.class, personExtW30.getPersonID())) {
            return true;
        }
        return false;
    }

    public List<PersonExt_W30> getAllPersonExt_W30() {
        List<PersonExt_W30> list = new ArrayList<PersonExt_W30>();
        List<Object> obs = new ArrayList<Object>();
        obs = mydao.getAllData(PersonExt_W30.class);
        if (!obs.isEmpty()) {
            int i;
            for (i = 0; i < obs.size(); i++) {
                list.add((PersonExt_W30) obs.get(i));
            }
        }
        return list;
    }

    public PersonExt_W30 getPersonExt_W30ByID(String id) {
        return (PersonExt_W30) mydao.getByID(PersonExt_W30.class, id);
    }

    public List<PersonExt_W30> SearchPersonExt_W30ByName(String key) {
        List<PersonExt_W30> list = new ArrayList<PersonExt_W30>();
        List<Object> obs = new ArrayList<Object>();
        obs = mydao.SearchByCondition(PersonExt_W30.class, "PersonID", key);
        if (!obs.isEmpty()) {
            int i;
            for (i = 0; i < obs.size(); i++) {
                list.add((PersonExt_W30) obs.get(i));
            }
        }
        return list;
    }
}

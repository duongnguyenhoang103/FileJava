/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.person.extW31.dao;

import java.util.ArrayList;
import java.util.List;
import org.openide.util.Lookup;
import vn.com.hkt.generic.api.IGenericAPI_extPersonW31;
import vn.com.hkt.person.extW31.entity.PersonExt_W31;

/**
 *
 * @author duong
 */
public class PersonExtW31_DAO {

    private IGenericAPI_extPersonW31 mydao;

    public PersonExtW31_DAO() {
        mydao = Lookup.getDefault().lookup(IGenericAPI_extPersonW31.class);
    }

    public boolean insertPersonExt_W30(PersonExt_W31 personExtW30) {
        if (mydao.insertData(personExtW30)) {
            return true;
        }
        return false;
    }

    public boolean udatePersonExt_W30(PersonExt_W31 personExtW30) {
        if (mydao.updateData(personExtW30)) {
            return true;
        }
        return false;
    }

    public boolean deletePersonExt_W30(PersonExt_W31 personExtW30) {
        if (mydao.deleteData(PersonExt_W31.class, personExtW30.getPersonID())) {
            return true;
        }
        return false;
    }

    public List<PersonExt_W31> getAllPersonExt_W30() {
        List<PersonExt_W31> list = new ArrayList<PersonExt_W31>();
        List<Object> obs = new ArrayList<Object>();
        obs = mydao.getAllData(PersonExt_W31.class);
        if (!obs.isEmpty()) {
            int i;
            for (i = 0; i < obs.size(); i++) {
                list.add((PersonExt_W31) obs.get(i));
            }
        }
        return list;
    }

    public PersonExt_W31 getPersonExt_W30ByID(String id) {
        return (PersonExt_W31) mydao.getByID(PersonExt_W31.class, id);
    }

    public List<PersonExt_W31> SearchPersonExt_W30ByName(String key) {
        List<PersonExt_W31> list = new ArrayList<PersonExt_W31>();
        List<Object> obs = new ArrayList<Object>();
        obs = mydao.SearchByCondition(PersonExt_W31.class, "PersonID", key);
        if (!obs.isEmpty()) {
            int i;
            for (i = 0; i < obs.size(); i++) {
                list.add((PersonExt_W31) obs.get(i));
            }
        }
        return list;
    }
}

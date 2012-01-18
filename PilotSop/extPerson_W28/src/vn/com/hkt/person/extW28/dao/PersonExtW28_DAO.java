/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.person.extW28.dao;

import java.util.ArrayList;
import java.util.List;
import org.openide.util.Lookup;
import vn.com.hkt.generic.api.IGenericAPI_extPersonW28;
import vn.com.hkt.person.extW28.entity.PersonExt_W28;

/**
 *
 * @author duong
 */
public class PersonExtW28_DAO {

    private IGenericAPI_extPersonW28 mydao;

    public PersonExtW28_DAO() {
        mydao = Lookup.getDefault().lookup(IGenericAPI_extPersonW28.class);
    }

    public boolean insertPersonExt_W28(PersonExt_W28 personExtW28) {
        if (mydao.insertData(personExtW28)) {
            return true;
        }
        return false;
    }

    public boolean udatePersonExt_W28(PersonExt_W28 personExtW28) {
        if (mydao.updateData(personExtW28)) {
            return true;
        }
        return false;
    }

    public boolean deletePersonExt_W28(PersonExt_W28 personExtW28) {
        if (mydao.deleteData(PersonExt_W28.class, personExtW28.getPersonID())) {
            return true;
        }
        return false;
    }

    public List<PersonExt_W28> getAllPersonExt_W28() {
        List<PersonExt_W28> list = new ArrayList<PersonExt_W28>();
        List<Object> obs = new ArrayList<Object>();
        obs = mydao.getAllData(PersonExt_W28.class);
        if (!obs.isEmpty()) {
            int i;
            for (i = 0; i < obs.size(); i++) {
                list.add((PersonExt_W28) obs.get(i));
            }
        }
        return list;
    }

    public PersonExt_W28 getPersonExt_W28ByID(String id) {
        return (PersonExt_W28) mydao.getByID(PersonExt_W28.class, id);
    }

    public List<PersonExt_W28> SearchPersonExt_W28ByName(String key) {
        List<PersonExt_W28> list = new ArrayList<PersonExt_W28>();
        List<Object> obs = new ArrayList<Object>();
        obs = mydao.SearchByCondition(PersonExt_W28.class, "PersonID", key);
        if (!obs.isEmpty()) {
            int i;
            for (i = 0; i < obs.size(); i++) {
                list.add((PersonExt_W28) obs.get(i));
            }
        }
        return list;
    }
}

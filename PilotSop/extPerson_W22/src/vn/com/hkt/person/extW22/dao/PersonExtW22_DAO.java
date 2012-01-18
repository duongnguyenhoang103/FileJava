/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.person.extW22.dao;

import java.util.ArrayList;
import java.util.List;
import org.openide.util.Lookup;
import vn.com.hkt.person.extW22.entity.PersonExt_W22;
import vn.com.hkt.generic.api.IGenericAPI_extPersonW22;

/**
 *
 * @author duong
 */
public class PersonExtW22_DAO {

    private IGenericAPI_extPersonW22 mydao;

    public PersonExtW22_DAO() {
        mydao = Lookup.getDefault().lookup(IGenericAPI_extPersonW22.class);
    }

    public boolean insertPersonExt_W22(PersonExt_W22 personExtW22) {
        if (mydao.insertData(personExtW22)) {
            return true;
        }
        return false;
    }

    public boolean udatePersonExt_W22(PersonExt_W22 personExtW22) {
        if (mydao.updateData(personExtW22)) {
            return true;
        }
        return false;
    }

    public boolean deletePersonExt_W22(PersonExt_W22 personExtW22) {
        if (mydao.deleteData(PersonExt_W22.class, personExtW22.getPersonID())) {
            return true;
        }
        return false;
    }

    public List<PersonExt_W22> getAllPersonExt_W22() {
        List<PersonExt_W22> list = new ArrayList<PersonExt_W22>();
        List<Object> obs = new ArrayList<Object>();
        obs = mydao.getAllData(PersonExt_W22.class);
        if (!obs.isEmpty()) {
            int i;
            for (i = 0; i < obs.size(); i++) {
                list.add((PersonExt_W22) obs.get(i));
            }
        }
        return list;
    }

    public PersonExt_W22 getPersonExt_W22ByID(String id) {
        return (PersonExt_W22) mydao.getByID(PersonExt_W22.class, id);
    }

    public List<PersonExt_W22> SearchPersonExt_W22ByName(String key) {
        List<PersonExt_W22> list = new ArrayList<PersonExt_W22>();
        List<Object> obs = new ArrayList<Object>();
        obs = mydao.SearchByCondition(PersonExt_W22.class, "PersonID", key);
        if (!obs.isEmpty()) {
            int i;
            for (i = 0; i < obs.size(); i++) {
                list.add((PersonExt_W22) obs.get(i));
            }
        }
        return list;
    }
}

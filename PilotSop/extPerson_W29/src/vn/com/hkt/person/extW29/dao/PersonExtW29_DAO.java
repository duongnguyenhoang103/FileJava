/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.person.extW29.dao;

import java.util.ArrayList;
import java.util.List;
import org.openide.util.Lookup;
import vn.com.hkt.generic.api.IGenericAPI_extPersonW29;
import vn.com.hkt.person.extW29.entity.PersonExt_W29;

/**
 *
 * @author duong
 */
public class PersonExtW29_DAO {

    private IGenericAPI_extPersonW29 mydao;

    public PersonExtW29_DAO() {
        mydao = Lookup.getDefault().lookup(IGenericAPI_extPersonW29.class);
    }

    public boolean insertPersonExt_W29(PersonExt_W29 personExtW29) {
        if (mydao.insertData(personExtW29)) {
            return true;
        }
        return false;
    }

    public boolean udatePersonExt_W29(PersonExt_W29 personExtW29) {
        if (mydao.updateData(personExtW29)) {
            return true;
        }
        return false;
    }

    public boolean deletePersonExt_W29(PersonExt_W29 personExtW29) {
        if (mydao.deleteData(PersonExt_W29.class, personExtW29.getPersonID())) {
            return true;
        }
        return false;
    }

    public List<PersonExt_W29> getAllPersonExt_W29() {
        List<PersonExt_W29> list = new ArrayList<PersonExt_W29>();
        List<Object> obs = new ArrayList<Object>();
        obs = mydao.getAllData(PersonExt_W29.class);
        if (!obs.isEmpty()) {
            int i;
            for (i = 0; i < obs.size(); i++) {
                list.add((PersonExt_W29) obs.get(i));
            }
        }
        return list;
    }

    public PersonExt_W29 getPersonExt_W29ByID(String id) {
        return (PersonExt_W29) mydao.getByID(PersonExt_W29.class, id);
    }

    public List<PersonExt_W29> SearchPersonExt_W29ByName(String key) {
        List<PersonExt_W29> list = new ArrayList<PersonExt_W29>();
        List<Object> obs = new ArrayList<Object>();
        obs = mydao.SearchByCondition(PersonExt_W29.class, "PersonID", key);
        if (!obs.isEmpty()) {
            int i;
            for (i = 0; i < obs.size(); i++) {
                list.add((PersonExt_W29) obs.get(i));
            }
        }
        return list;
    }
}

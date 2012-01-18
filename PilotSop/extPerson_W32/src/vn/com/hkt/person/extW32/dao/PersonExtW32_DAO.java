/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.person.extW32.dao;

import java.util.ArrayList;
import java.util.List;
import org.openide.util.Lookup;
import vn.com.hkt.generic.api.IGenericAPI_extPersonW32;
import vn.com.hkt.person.extW32.entity.PersonExt_W32;

/**
 *
 * @author duong
 */
public class PersonExtW32_DAO {

    private IGenericAPI_extPersonW32 mydao;

    public PersonExtW32_DAO() {
        mydao = Lookup.getDefault().lookup(IGenericAPI_extPersonW32.class);
    }

    public boolean insertPersonExt_W32(PersonExt_W32 personExtW32) {
        if (mydao.insertData(personExtW32)) {
            return true;
        }
        return false;
    }

    public boolean udatePersonExt_W32(PersonExt_W32 personExtW32) {
        if (mydao.updateData(personExtW32)) {
            return true;
        }
        return false;
    }

    public boolean deletePersonExt_W32(PersonExt_W32 personExtW32) {
        if (mydao.deleteData(PersonExt_W32.class, personExtW32.getPersonID())) {
            return true;
        }
        return false;
    }

    public List<PersonExt_W32> getAllPersonExt_W32() {
        List<PersonExt_W32> list = new ArrayList<PersonExt_W32>();
        List<Object> obs = new ArrayList<Object>();
        obs = mydao.getAllData(PersonExt_W32.class);
        if (!obs.isEmpty()) {
            int i;
            for (i = 0; i < obs.size(); i++) {
                list.add((PersonExt_W32) obs.get(i));
            }
        }
        return list;
    }

    public PersonExt_W32 getPersonExt_W32ByID(String id) {
        return (PersonExt_W32) mydao.getByID(PersonExt_W32.class, id);
    }

    public List<PersonExt_W32> SearchPersonExt_W32ByName(String key) {
        List<PersonExt_W32> list = new ArrayList<PersonExt_W32>();
        List<Object> obs = new ArrayList<Object>();
        obs = mydao.SearchByCondition(PersonExt_W32.class, "PersonID", key);
        if (!obs.isEmpty()) {
            int i;
            for (i = 0; i < obs.size(); i++) {
                list.add((PersonExt_W32) obs.get(i));
            }
        }
        return list;
    }
}

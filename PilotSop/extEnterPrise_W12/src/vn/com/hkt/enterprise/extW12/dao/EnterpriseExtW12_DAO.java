/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.enterprise.extW12.dao;

import java.util.ArrayList;
import java.util.List;
import org.openide.util.Lookup;
import vn.com.hkt.enterprise.extW12.entity.EnterpriseExt_W12;
import vn.com.hkt.generic.api.IGenericAPI_extEnterpriseW12;

/**
 *
 * @author duong
 */
public class EnterpriseExtW12_DAO {

    private IGenericAPI_extEnterpriseW12 mydao;

    public EnterpriseExtW12_DAO() {
        mydao = Lookup.getDefault().lookup(IGenericAPI_extEnterpriseW12.class);
    }

    public boolean insertEnterpriseExt_W12(EnterpriseExt_W12 enterExtW12) {
        if (mydao.insertData(enterExtW12)) {
            return true;
        }
        return false;
    }

    public boolean udateEnterpriseExt_W12(EnterpriseExt_W12 enterExtW12) {
        if (mydao.updateData(enterExtW12)) {
            return true;
        }
        return false;
    }

    public boolean deleteEnterpriseExt_W12(EnterpriseExt_W12 enterExtW12) {
        if (mydao.deleteData(EnterpriseExt_W12.class, enterExtW12.getEnterpriseID())) {
            return true;
        }
        return false;
    }

    public List<EnterpriseExt_W12> getAllEnterpriseExt_W12() {
        List<EnterpriseExt_W12> list = new ArrayList<EnterpriseExt_W12>();
        List<Object> obs = new ArrayList<Object>();
        obs = mydao.getAllData(EnterpriseExt_W12.class);
        if (!obs.isEmpty()) {
            int i;
            for (i = 0; i < obs.size(); i++) {
                list.add((EnterpriseExt_W12) obs.get(i));
            }
        }
        return list;
    }

    public EnterpriseExt_W12 getEnterpriseExt_W12ByID(String id) {
        return (EnterpriseExt_W12) mydao.getByID(EnterpriseExt_W12.class, id);
    }

    public List<EnterpriseExt_W12> SearchEnterpriseExt_W12ByName(String key) {
        List<EnterpriseExt_W12> list = new ArrayList<EnterpriseExt_W12>();
        List<Object> obs = new ArrayList<Object>();
        obs = mydao.SearchByCondition(EnterpriseExt_W12.class, "EnterpriseID", key);
        if (!obs.isEmpty()) {
            int i;
            for (i = 0; i < obs.size(); i++) {
                list.add((EnterpriseExt_W12) obs.get(i));
            }
        }
        return list;
    }
}

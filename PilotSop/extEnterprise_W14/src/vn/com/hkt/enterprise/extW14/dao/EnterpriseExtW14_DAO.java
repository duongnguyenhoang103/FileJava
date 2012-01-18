/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.enterprise.extW14.dao;

import java.util.ArrayList;
import java.util.List;
import org.openide.util.Lookup;
import vn.com.hkt.enterprise.extW14.entity.EnterpriseExt_W14;
import vn.com.hkt.generic.api.IGenericAPI_extEnterpriseW14;

/**
 *
 * @author duong
 */
public class EnterpriseExtW14_DAO {

    private IGenericAPI_extEnterpriseW14 mydao;

    public EnterpriseExtW14_DAO() {
        mydao = Lookup.getDefault().lookup(IGenericAPI_extEnterpriseW14.class);
    }

    public boolean insertEnterpriseExt_W14(EnterpriseExt_W14 enterExtW14) {
        if (mydao.insertData(enterExtW14)) {
            return true;
        }
        return false;
    }

    public boolean udateEnterpriseExt_W14(EnterpriseExt_W14 enterExtW14) {
        if (mydao.updateData(enterExtW14)) {
            return true;
        }
        return false;
    }

    public boolean deleteEnterpriseExt_W14(EnterpriseExt_W14 enterExtW14) {
        if (mydao.deleteData(EnterpriseExt_W14.class, enterExtW14.getEnterpriseID())) {
            return true;
        }
        return false;
    }

    public List<EnterpriseExt_W14> getAllEnterpriseExt_W14() {
        List<EnterpriseExt_W14> list = new ArrayList<EnterpriseExt_W14>();
        List<Object> obs = new ArrayList<Object>();
        obs = mydao.getAllData(EnterpriseExt_W14.class);
        if (!obs.isEmpty()) {
            int i;
            for (i = 0; i < obs.size(); i++) {
                list.add((EnterpriseExt_W14) obs.get(i));
            }
        }
        return list;
    }

    public EnterpriseExt_W14 getEnterpriseExt_W14ByID(String id) {
        return (EnterpriseExt_W14) mydao.getByID(EnterpriseExt_W14.class, id);
    }

    public List<EnterpriseExt_W14> SearchEnterpriseExt_W14ByName(String key) {
        List<EnterpriseExt_W14> list = new ArrayList<EnterpriseExt_W14>();
        List<Object> obs = new ArrayList<Object>();
        obs = mydao.SearchByCondition(EnterpriseExt_W14.class, "EnterpriseID", key);
        if (!obs.isEmpty()) {
            int i;
            for (i = 0; i < obs.size(); i++) {
                list.add((EnterpriseExt_W14) obs.get(i));
            }
        }
        return list;
    }
}

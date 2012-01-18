/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.enterprise.extW15.dao;

import java.util.ArrayList;
import java.util.List;
import org.openide.util.Lookup;
import vn.com.hkt.enterprise.extW15.entity.EnterpriseExt_W15;
import vn.com.hkt.generic.api.IGenericAPI_extEnterpriseW15;

/**
 *
 * @author duong
 */
public class EnterpriseExtW15_DAO {

    private IGenericAPI_extEnterpriseW15 mydao;

    public EnterpriseExtW15_DAO() {
         mydao = Lookup.getDefault().lookup(IGenericAPI_extEnterpriseW15.class);
    }

    public boolean insertEnterpriseExt_W15(EnterpriseExt_W15 enterExtW15) {
        if (mydao.insertData(enterExtW15)) {
            return true;
        }
        return false;
    }

    public boolean udateEnterpriseExt_W15(EnterpriseExt_W15 enterExtW15) {
        if (mydao.updateData(enterExtW15)) {
            return true;
        }
        return false;
    }

    public boolean deleteEnterpriseExt_W15(EnterpriseExt_W15 enterExtW15) {
        if (mydao.deleteData(EnterpriseExt_W15.class, enterExtW15.getEnterpriseID())) {
            return true;
        }
        return false;
    }

    public List<EnterpriseExt_W15> getAllEnterpriseExt_W15() {
        List<EnterpriseExt_W15> list = new ArrayList<EnterpriseExt_W15>();
        List<Object> obs = new ArrayList<Object>();
        obs = mydao.getAllData(EnterpriseExt_W15.class);
        if (!obs.isEmpty()) {
            int i;
            for (i = 0; i < obs.size(); i++) {
                list.add((EnterpriseExt_W15) obs.get(i));
            }
        }
        return list;
    }

    public EnterpriseExt_W15 getEnterpriseExt_W15ByID(String id) {
        return (EnterpriseExt_W15) mydao.getByID(EnterpriseExt_W15.class, id);
    }

    public List<EnterpriseExt_W15> SearchEnterpriseExt_W15ByName(String name) {
        List<EnterpriseExt_W15> list = new ArrayList<EnterpriseExt_W15>();
        List<Object> obs = new ArrayList<Object>();
        obs = mydao.SearchByCondition(EnterpriseExt_W15.class, "EnterpriseName", name);
        if (!obs.isEmpty()) {
            int i;
            for (i = 0; i < obs.size(); i++) {
                list.add((EnterpriseExt_W15) obs.get(i));
            }
        }
        return list;
    }
}

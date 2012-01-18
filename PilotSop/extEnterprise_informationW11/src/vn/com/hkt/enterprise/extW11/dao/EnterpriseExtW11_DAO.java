/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.enterprise.extW11.dao;

import java.util.ArrayList;
import java.util.List;
import org.openide.util.Lookup;
import vn.com.hkt.enterprise.extW11.entity.EnterpriseExt_W11;
import vn.com.hkt.generic.api.IGenericAPI_extEnterpriseW11;

/**
 *
 * @author duong
 */
public class EnterpriseExtW11_DAO {

    private IGenericAPI_extEnterpriseW11 mydao;

    public EnterpriseExtW11_DAO() {
        mydao = Lookup.getDefault().lookup(IGenericAPI_extEnterpriseW11.class);
    }

    public boolean insertEnterpriseExt_W11(EnterpriseExt_W11 enterExtW11) {
        if (mydao.insertData(enterExtW11)) {
            return true;
        }
        return false;
    }

    public boolean udateEnterpriseExt_W11(EnterpriseExt_W11 enterExtW11) {
        if (mydao.updateData(enterExtW11)) {
            return true;
        }
        return false;
    }

    public boolean deleteEnterpriseExt_W11(EnterpriseExt_W11 enterExtW11) {
        if (mydao.deleteData(EnterpriseExt_W11.class, enterExtW11.getEnterpriseID())) {
            return true;
        }
        return false;
    }

    public List<EnterpriseExt_W11> getAllEnterpriseExt_W11() {
        List<EnterpriseExt_W11> list = new ArrayList<EnterpriseExt_W11>();
        List<Object> obs = new ArrayList<Object>();
        obs = mydao.getAllData(EnterpriseExt_W11.class);
        if (!obs.isEmpty()) {
            int i;
            for (i = 0; i < obs.size(); i++) {
                list.add((EnterpriseExt_W11) obs.get(i));
            }
        }
        return list;
    }

    public EnterpriseExt_W11 getEnterpriseExt_W11ByID(String id) {
        return (EnterpriseExt_W11) mydao.getByID(EnterpriseExt_W11.class, id);
    }

    public List<EnterpriseExt_W11> SearchEnterpriseExt_W11ByName(String name) {
        List<EnterpriseExt_W11> list = new ArrayList<EnterpriseExt_W11>();
        List<Object> obs = new ArrayList<Object>();
        obs = mydao.SearchByCondition(EnterpriseExt_W11.class, "EnterpriseName", name);
        if (!obs.isEmpty()) {
            int i;
            for (i = 0; i < obs.size(); i++) {
                list.add((EnterpriseExt_W11) obs.get(i));
            }
        }
        return list;
    }
}

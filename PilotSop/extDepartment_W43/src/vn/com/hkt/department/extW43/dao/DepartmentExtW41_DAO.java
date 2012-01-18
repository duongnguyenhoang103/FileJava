/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.department.extW43.dao;

import java.util.ArrayList;
import java.util.List;
import org.openide.util.Lookup;
import vn.com.hkt.generic.api.IGenericAPI_extDepartmentW43;
import vn.com.hkt.deparment.extW43.entity.DepartmentExt_W43;

/**
 *
 * @author duong
 */
public class DepartmentExtW41_DAO {

    private IGenericAPI_extDepartmentW43 mydao;

    public DepartmentExtW41_DAO() {
        mydao = Lookup.getDefault().lookup(IGenericAPI_extDepartmentW43.class);
    }

    public boolean insertDepartmentExt_W43(DepartmentExt_W43 departmentExtW43) {
        if (mydao.insertData(departmentExtW43)) {
            return true;
        }
        return false;
    }

    public boolean udateDepartmentExt_W43(DepartmentExt_W43 departmentExtW43) {
        if (mydao.updateData(departmentExtW43)) {
            return true;
        }
        return false;
    }

    public boolean deleteDepartmentExt_W43(DepartmentExt_W43 departmentExtW43) {
        if (mydao.deleteData(DepartmentExt_W43.class, departmentExtW43.getDepartmentID())) {
            return true;
        }
        return false;
    }

    public List<DepartmentExt_W43> getAllDepartmentExt_W43() {
        List<DepartmentExt_W43> list = new ArrayList<DepartmentExt_W43>();
        List<Object> obs = new ArrayList<Object>();
        obs = mydao.getAllData(DepartmentExt_W43.class);
        if (!obs.isEmpty()) {
            int i;
            for (i = 0; i < obs.size(); i++) {
                list.add((DepartmentExt_W43) obs.get(i));
            }
        }
        return list;
    }

    public DepartmentExt_W43 getDepartmentExt_W43(String id) {
        return (DepartmentExt_W43) mydao.getByID(DepartmentExt_W43.class, id);
    }

    public List<DepartmentExt_W43> SearchDepartmentExt_W43ByName(String key) {
        List<DepartmentExt_W43> list = new ArrayList<DepartmentExt_W43>();
        List<Object> obs = new ArrayList<Object>();
        obs = mydao.SearchByCondition(DepartmentExt_W43.class, "departmentID", key);
        if (!obs.isEmpty()) {
            int i;
            for (i = 0; i < obs.size(); i++) {
                list.add((DepartmentExt_W43) obs.get(i));
            }
        }
        return list;
    }
}

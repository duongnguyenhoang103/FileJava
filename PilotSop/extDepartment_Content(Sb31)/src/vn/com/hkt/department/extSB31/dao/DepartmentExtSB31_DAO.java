/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.department.extSB31.dao;

import java.util.ArrayList;
import java.util.List;
import org.openide.util.Lookup;
import vn.com.hkt.generic.api.IGenericAPI_extDepartmentSB31;
import vn.com.hkt.deparment.extSB31.entity.DepartmentExt_SB31;

/**
 *
 * @author duong
 */
public class DepartmentExtSB31_DAO {

    private IGenericAPI_extDepartmentSB31 mydao;

    public DepartmentExtSB31_DAO() {
        mydao = Lookup.getDefault().lookup(IGenericAPI_extDepartmentSB31.class);
    }

    public boolean insertDepartmentExt_SB31(DepartmentExt_SB31 departmentExtSB31) {
        if (mydao.insertData(departmentExtSB31)) {
            return true;
        }
        return false;
    }

    public boolean udateDepartmentExt_SB31(DepartmentExt_SB31 departmentExtSB31) {
        if (mydao.updateData(departmentExtSB31)) {
            return true;
        }
        return false;
    }

    public boolean deleteDepartmentExt_SB31(DepartmentExt_SB31 departmentExtSB31) {
        if (mydao.deleteData(DepartmentExt_SB31.class, departmentExtSB31.getDepartmentID())) {
            return true;
        }
        return false;
    }

    public List<DepartmentExt_SB31> getAllDepartmentExt_SB31() {
        List<DepartmentExt_SB31> list = new ArrayList<DepartmentExt_SB31>();
        List<Object> obs = new ArrayList<Object>();
        obs = mydao.getAllData(DepartmentExt_SB31.class);
        if (!obs.isEmpty()) {
            int i;
            for (i = 0; i < obs.size(); i++) {
                list.add((DepartmentExt_SB31) obs.get(i));
            }
        }
        return list;
    }

    public DepartmentExt_SB31 getDepartmentExt_SB31(String id) {
        return (DepartmentExt_SB31) mydao.getByID(DepartmentExt_SB31.class, id);
    }

    public List<DepartmentExt_SB31> SearchDepartmentExt_SB31ByName(String key) {
        List<DepartmentExt_SB31> list = new ArrayList<DepartmentExt_SB31>();
        List<Object> obs = new ArrayList<Object>();
        obs = mydao.SearchByCondition(DepartmentExt_SB31.class, "departmentID", key);
        if (!obs.isEmpty()) {
            int i;
            for (i = 0; i < obs.size(); i++) {
                list.add((DepartmentExt_SB31) obs.get(i));
            }
        }
        return list;
    }
}

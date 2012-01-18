/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.department.extW41.dao;

import java.util.ArrayList;
import java.util.List;
import org.openide.util.Lookup;
import vn.com.hkt.generic.api.IGenericAPI_extDepartmentW41;
import vn.com.hkt.deparment.extW41.entity.DepartmentExt_W41;

/**
 *
 * @author duong
 */
public class DepartmentExtW41_DAO {

    private IGenericAPI_extDepartmentW41 mydao;

    public DepartmentExtW41_DAO() {
        mydao = Lookup.getDefault().lookup(IGenericAPI_extDepartmentW41.class);
    }

    public boolean insertDepartmentExt_W41(DepartmentExt_W41 departmentExtW41) {
        if (mydao.insertData(departmentExtW41)) {
            return true;
        }
        return false;
    }

    public boolean udateDepartmentExt_W41(DepartmentExt_W41 departmentExtW41) {
        if (mydao.updateData(departmentExtW41)) {
            return true;
        }
        return false;
    }

    public boolean deleteDepartmentExt_W41(DepartmentExt_W41 departmentExtW41) {
        if (mydao.deleteData(DepartmentExt_W41.class, departmentExtW41.getDepartmentID())) {
            return true;
        }
        return false;
    }

    public List<DepartmentExt_W41> getAllDepartmentExt_W41() {
        List<DepartmentExt_W41> list = new ArrayList<DepartmentExt_W41>();
        List<Object> obs = new ArrayList<Object>();
        obs = mydao.getAllData(DepartmentExt_W41.class);
        if (!obs.isEmpty()) {
            int i;
            for (i = 0; i < obs.size(); i++) {
                list.add((DepartmentExt_W41) obs.get(i));
            }
        }
        return list;
    }

    public DepartmentExt_W41 getDepartmentExt_W41(String id) {
        return (DepartmentExt_W41) mydao.getByID(DepartmentExt_W41.class, id);
    }

    public List<DepartmentExt_W41> SearchDepartmentExt_W41ByName(String key) {
        List<DepartmentExt_W41> list = new ArrayList<DepartmentExt_W41>();
        List<Object> obs = new ArrayList<Object>();
        obs = mydao.SearchByCondition(DepartmentExt_W41.class, "PersonID", key);
        if (!obs.isEmpty()) {
            int i;
            for (i = 0; i < obs.size(); i++) {
                list.add((DepartmentExt_W41) obs.get(i));
            }
        }
        return list;
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.pilot.sb31.department.extW41.dao;

import java.util.ArrayList;
import java.util.List;
import org.openide.util.Lookup;
import vn.com.hkt.basic.api.IDepartmentBN;
import vn.com.hkt.pilot.entities.Department;
import vn.com.hkt.pilot.entities.Enterprise;
import vn.com.hkt.pilot.identity.access.api.IAccessData;
import vn.com.hkt.pilot.identity.access.spi.AccessData;
import vn.com.hkt.pilot.sb31.deparment.extW41.entity.DepartmentExtSB31_W43;
import vn.com.hkt.pilot.sb31.generic.jpas.utils.PersistenceUtility;

/**
 *
 * @author VietAnh
 */
public class DepartmentExtSB31W43_DAO extends AccessData<DepartmentExtSB31_W43> {
private IDepartmentBN departmentBN = Lookup.getDefault().lookup(IDepartmentBN.class);
    public DepartmentExtSB31W43_DAO() {
        setAccessData(PersistenceUtility.getEmf(), DepartmentExtSB31_W43.class);
    }

    public List<DepartmentExtSB31_W43> searchDepartmentExt_W43ById(String key) {
        return select(DepartmentExtSB31_W43.FIELD_DEPARTMENT_ID, key);
    }

    public List<DepartmentExtSB31_W43> filterDepartmentExt_W43ByID(String id) {
        return filter(DepartmentExtSB31_W43.FIELD_DEPARTMENT_ID, id);
    }

    public List<DepartmentExtSB31_W43> filterDepartmentExt_W43ByName(String name) {
        List<DepartmentExtSB31_W43> list = selectAll();
        List<DepartmentExtSB31_W43> ls = new ArrayList<DepartmentExtSB31_W43>();
        IAccessData accessData = new Department().getAccessDataOfEntity();
        for (int i = 0; i < list.size(); i++) {
            Department de = (Department) accessData.getById(list.get(i).getDepartmentIdActual());
            if (de != null && de.getDepartmentName().contains(name)) {
                ls.add(list.get(i));
            }
        }
        return ls;
    }
    public List<DepartmentExtSB31_W43> getByEnterpriser(Enterprise e) {
        List<DepartmentExtSB31_W43> list = selectAll();
        List<DepartmentExtSB31_W43> lr = new ArrayList<DepartmentExtSB31_W43>();
        for (int i = 0; i < list.size(); i++) {
            Department p = departmentBN.getById(list.get(i).getDepartmentIdActual());
            if (p.getEnterpriseIdActual() == e.getId()) {
                lr.add(list.get(i));
            }
        }
        return lr;
    }
}


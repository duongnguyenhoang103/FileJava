/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.pilot.sb31.department.extW41.dao;

import java.util.ArrayList;
import java.util.List;
import org.openide.util.Lookup;
import vn.com.hkt.basic.api.IDepartmentBN;
import vn.com.hkt.pilot.sb31.deparment.extW41.entity.DepartmentExt_W41;
import vn.com.hkt.pilot.entities.Department;
import vn.com.hkt.pilot.entities.Enterprise;
import vn.com.hkt.pilot.identity.access.api.IAccessData;
import vn.com.hkt.pilot.identity.access.spi.AccessData;
import vn.com.hkt.pilot.sb31.generic.jpas.utils.PersistenceUtility;

/**
 *
 * @author duong
 */
public class DepartmentExtW41_DAO extends AccessData<DepartmentExt_W41> {

    private IDepartmentBN departmentBN = Lookup.getDefault().lookup(IDepartmentBN.class);

    public DepartmentExtW41_DAO() {
        setAccessData(PersistenceUtility.getEmf(), DepartmentExt_W41.class);
    }

    public List<DepartmentExt_W41> searchDepartmentExt_W41ById(String key) {
        return select(DepartmentExt_W41.FIELD_DEPARTMENT_ID_ACTUAL, key);
    }

    public List<DepartmentExt_W41> filterDepartmentExt_W41ByID(String id) {
        return filter(DepartmentExt_W41.FIELD_DEPARTMENT_ID_ACTUAL, id);
    }

    public List<DepartmentExt_W41> filterDepartmentExt_W41ByName(String name) {
        List<DepartmentExt_W41> list = selectAll();
        List<DepartmentExt_W41> ls = new ArrayList<DepartmentExt_W41>();
        IAccessData accessData = new Department().getAccessDataOfEntity();
        for (int i = 0; i < list.size(); i++) {
            Department de = (Department) accessData.getById(list.get(i).getDepartmentIdActual());
            if (de != null && de.getDepartmentName().contains(name)) {
                ls.add(list.get(i));
            }
        }
        return ls;
    }

    public List<DepartmentExt_W41> getByEnterpriser(Enterprise e) {
        List<DepartmentExt_W41> list = selectAll();
        List<DepartmentExt_W41> lr = new ArrayList<DepartmentExt_W41>();
        for (int i = 0; i < list.size(); i++) {
            Department p = departmentBN.getById(list.get(i).getDepartmentIdActual());
            if (p.getEnterpriseIdActual() == e.getId()) {
                lr.add(list.get(i));
            }
        }
        return lr;
    }
}

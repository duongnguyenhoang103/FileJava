/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.pilot.sb32.department.extW43.dao;

import java.util.ArrayList;
import java.util.List;
import org.openide.util.Lookup;
import vn.com.hkt.basic.api.IDepartmentBN;
import vn.com.hkt.pilot.entities.Department;
import vn.com.hkt.pilot.entities.Enterprise;
import vn.com.hkt.pilot.identity.access.spi.AccessData;
import vn.com.hkt.pilot.sb32.deparment.extW43.entity.DepartmentExt_W43;
import vn.com.hkt.pilot.sb32.subdepartment32.jpa.util.PersistenceUtility;

/**
 *
 * @author duong
 */
public class DepartmentExtW43_DAO extends AccessData<DepartmentExt_W43> {
 private IDepartmentBN departmentBN = Lookup.getDefault().lookup(IDepartmentBN.class);
    public DepartmentExtW43_DAO() {
        setAccessData(PersistenceUtility.getEmf(), DepartmentExt_W43.class);
    }

    public List<DepartmentExt_W43> SearchDepartmentExt_W43ById(String id) {
        return filter(DepartmentExt_W43.FILED_DEPARTMENT_ID, id);
    }
    
     public List<DepartmentExt_W43> getByEnterpriser(Enterprise e) {
        List<DepartmentExt_W43> list = selectAll();
        List<DepartmentExt_W43> lr = new ArrayList<DepartmentExt_W43>();
        for (int i = 0; i < list.size(); i++) {
            Department p = departmentBN.getById(list.get(i).getDepartmentIdActual());
            if (p.getEnterpriseIdActual() == e.getId()) {
                lr.add(list.get(i));
            }
        }
        return lr;
    }
}

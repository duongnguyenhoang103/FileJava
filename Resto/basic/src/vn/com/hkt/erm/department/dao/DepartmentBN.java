/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.erm.department.dao;

import vn.com.hkt.pilot.entities.Department;
import java.util.ArrayList;
import java.util.List;
import org.openide.util.lookup.ServiceProvider;
import vn.com.hkt.basic.api.IDepartmentBN;
import vn.com.hkt.basic.jpa.PersistenceUltility;
import vn.com.hkt.pilot.entities.Enterprise;
import vn.com.hkt.pilot.identity.access.spi.AccessData;

/**
 *
 * @author duong
 */
@ServiceProvider(service = IDepartmentBN.class)
public class DepartmentBN extends AccessData<Department> implements IDepartmentBN {

    public DepartmentBN() {
        setAccessData(PersistenceUltility.getEMF(), Department.class);
    }

    @Override
    public List<Department> filterDepartmentByID(String id) {
        return filter(Department.FIELD_DEPARTMENT_ID, id);
    }

    @Override
    public List<Department> filterDepartmentByName(String name) {
        return filter(Department.FIELD_DEPARTMENT_NAME, name);
    }

    @Override
    public List<Department> filterDepartmentByEnterprise(Enterprise enterprise) {
        return filter(Department.FIELD_ENTERPRISE_ID_ACTUAL, String.valueOf(enterprise.getId()));
    }

    @Override
    public List<Enterprise> enterpriseHasDepartment() {
        try {
            String sql = "select enter FROM " + Enterprise.class.getSimpleName() + " enter"
                    + " where enter." + Enterprise.FILED_ID
                    + " IN (Select duan." + Department.FIELD_ENTERPRISE_ID_ACTUAL + " from "
                    + Department.class.getSimpleName() + " duan)";
            List<Object> list = queryList(sql);
            List<Enterprise> le = new ArrayList<Enterprise>();
            for (int i = 0; i < list.size(); i++) {
                le.add((Enterprise) list.get(i));
            }
            return le;
        } catch (Exception e) {
            return new ArrayList<Enterprise>();
        }
    }

    @Override
    public List<Department> filterDepartmentByRootDepartment(Enterprise enterprise, Department department) {
        List<String> listFieldName = new ArrayList<String>();
        listFieldName.add(Department.FIELD_ENTERPRISE_ID_ACTUAL);
        listFieldName.add(Department.FIELD_DEPARTMENT_PARENT_ID_ACTUAL);
        List<String> listVaule = new ArrayList<String>();
        listVaule.add(String.valueOf(enterprise.getId()));
        listVaule.add(String.valueOf(department.getId()));
        return filter(listFieldName, listVaule);
    }
}

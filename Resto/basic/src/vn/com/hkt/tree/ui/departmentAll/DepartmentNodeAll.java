/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.tree.ui.departmentAll;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.openide.nodes.PropertySupport;
import org.openide.nodes.Sheet;
import org.openide.util.Lookup;
import vn.com.hkt.erm.enterprise.dao.EnterpriseBN;
import vn.com.hkt.hrm.person.dao.PersonBN;
import vn.com.hkt.pilot.entities.Department;
import vn.com.hkt.pilot.entities.Enterprise;
import vn.com.hkt.pilot.entities.Person;

/**
 *
 * @author longnt
 */
public class DepartmentNodeAll extends AbstractNode {

    public static String Giamdoc_PROPERTY = "TruongPhong";
    public static String Congtyme_PROPERTY = "CongTy";
    private Department department;

    public DepartmentNodeAll(Department department) throws IntrospectionException {
        super(Children.LEAF, Lookup.getDefault());
        this.department = department;
        setDisplayName(department.getDepartmentName());
    }

    @Override
    protected Sheet createSheet() {
        Sheet sheet = Sheet.createDefault();
        Sheet.Set set = Sheet.createPropertiesSet();

        Property tweetDateProperty = new PropertySupport.ReadOnly<String>(Giamdoc_PROPERTY, String.class, "Trưởng phòng", "aaa") {

            @Override
            public String getValue() throws IllegalAccessException, InvocationTargetException {
                Person person = new PersonBN().getById(department.getId());
                return person.getPersonName();
            }
        };
        set.put(tweetDateProperty);
        Property tweetAuthorProperty =
                new PropertySupport.ReadOnly<String>(Congtyme_PROPERTY, String.class, "Công ty", "bbb") {

                    @Override
                    public String getValue() throws IllegalAccessException, InvocationTargetException {
                        Enterprise enter = new EnterpriseBN().getById(department.getEnterpriseIdActual());
                        return enter.getEnterpriseName();
                    }
                };
        set.put(tweetAuthorProperty);
        sheet.put(set);
        return sheet;
    }
}

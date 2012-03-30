/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.tree.ui.enterprise;

import vn.com.hkt.pilot.entities.Enterprise;
import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.openide.nodes.PropertySupport;
import org.openide.nodes.Sheet;
import org.openide.util.Lookup;
import vn.com.hkt.erm.enterprise.dao.EnterpriseBN;
import vn.com.hkt.hrm.person.dao.PersonBN;
import vn.com.hkt.pilot.entities.Person;

/**
 *
 * @author longnt
 */
public class EnterpriseNode extends AbstractNode {

    public static String Giamdoc_PROPERTY = "GiamDoc";
    public static String Congtyme_PROPERTY = "CongTyMe";
    private Enterprise enterprise;

    public EnterpriseNode(Enterprise enterprise) throws IntrospectionException {
        super(Children.LEAF, Lookup.getDefault());
        this.enterprise = enterprise;
        setDisplayName(enterprise.getEnterpriseName());
    }

    @Override
    protected Sheet createSheet() {
        Sheet sheet = Sheet.createDefault();
        Sheet.Set set = Sheet.createPropertiesSet();

        Property tweetDateProperty = new PropertySupport.ReadOnly<String>(Giamdoc_PROPERTY, String.class, "Giám đốc", "aaa") {

            @Override
            public String getValue() throws IllegalAccessException, InvocationTargetException {
                Person p= new PersonBN().getById(enterprise.getDirectorIdActual());
                return p.getPersonName();
            }
        };
        set.put(tweetDateProperty);
        Property tweetAuthorProperty =
                new PropertySupport.ReadOnly<String>(Congtyme_PROPERTY, String.class, "Công ty mẹ", "bbb") {

                    @Override
                    public String getValue() throws IllegalAccessException, InvocationTargetException {
                        Enterprise enter = new EnterpriseBN().getById(enterprise.getEnterpriseParentIdActual());
                        return enter.getEnterpriseName();
                    }
                };
        set.put(tweetAuthorProperty);
        sheet.put(set);
        return sheet;
    }
}

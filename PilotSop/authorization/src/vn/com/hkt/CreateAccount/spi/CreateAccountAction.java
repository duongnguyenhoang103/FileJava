/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.CreateAccount.spi;

import com.vn.hkt.core.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.openide.util.Exceptions;
import org.openide.util.lookup.ServiceProvider;
import vn.com.hkt.CreateAccount.api.ICreateAccount;
import vn.com.hkt.pilot.entities.Department;
import vn.com.hkt.pilot.entities.Enterprise;
import vn.com.hkt.pilot.entities.Person;

/**
 *
 * @author Admin
 */
@ServiceProvider(service = ICreateAccount.class)
public class CreateAccountAction implements ICreateAccount {

    @Override
    public Enterprise getEnterpriseIDByName(String enterpriseName) {
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        Enterprise e = new Enterprise();
        conn = DBConnection.openConnection();
        try {
            pstm = conn.prepareStatement("select Enterpriseid  from Enterprise where EnterpriseName=?");
            pstm.setString(1, enterpriseName);
            rs = pstm.executeQuery();
            while (rs.next()) {
                e.setEnterpriseID(rs.getString("EnterpriseID"));
            }
        } catch (Exception ex) {
            Exceptions.printStackTrace(ex);
        } finally {
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(pstm);
            DBConnection.closeConnection(conn);
        }
        return e;
    }

    @Override
    public List<Department> getDepartmentByEnterprise(String enterpriseID) {
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        List<Department> list = new ArrayList<Department>();
        conn = DBConnection.openConnection();
        try {
            pstm = conn.prepareStatement("select DepartmentName from Department where EnterpriseID=?");
            pstm.setString(1, enterpriseID);
            rs = pstm.executeQuery();
            while (rs.next()) {
                Department d = new Department();
                d.setDepartmentName(rs.getString("DepartmentName"));
                list.add(d);
            }
        } catch (Exception ex) {
            Exceptions.printStackTrace(ex);
        } finally {
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(pstm);
            DBConnection.closeConnection(conn);
        }
        return list;
    }

    @Override
    public List<Person> getPersonByEnterpriseAndDepartment(String enterpriseID, String departmentName) {
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        List<Person> list = new ArrayList<Person>();
        conn = DBConnection.openConnection();
        try {
            pstm = conn.prepareStatement("select PersonID, FirstName, LastName from Person where EnterpriseID=? and DepartmentName=?");
            pstm.setString(1, enterpriseID);
            pstm.setString(2, departmentName);
            rs = pstm.executeQuery();
            while (rs.next()) {
                Person p = new Person();
                p.setFirstName(rs.getString("FirstName"));
                p.setLastName(rs.getString("LastName"));
                p.setPersonID(rs.getString("PersonID"));
                list.add(p);
            }
        } catch (Exception ex) {
            Exceptions.printStackTrace(ex);
        } finally {
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(pstm);
            DBConnection.closeConnection(conn);
        }
        return list;
    }
}

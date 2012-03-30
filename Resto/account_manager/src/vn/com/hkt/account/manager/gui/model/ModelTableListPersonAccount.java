/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.account.manager.gui.model;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import vn.com.hkt.account.manager.entities.Account;
import vn.com.hkt.account.manager.entities.AccountType;
import vn.com.hkt.pilot.entities.Department;
import vn.com.hkt.pilot.entities.Enterprise;
import vn.com.hkt.pilot.entities.Person;

/**
 *
 * @author BinLe
 */
public class ModelTableListPersonAccount extends AbstractTableModel {

    private final String[] header = new String[]{"Nhân viên", "Account", "Lớp quản lý", "Doanh nghiêp", "Phòng ban"};
    private List<Account> accounts;
    private List<AccountType> accountTypes;
    private List<Person> persons;
    private List<Enterprise> enterprises;
    private List<Department> departments;

    public ModelTableListPersonAccount(List<Account> accounts, List<AccountType> accountTypes, List<Person> persons, List<Enterprise> enterprises, List<Department> departments) {
        this.accounts = accounts;
        this.accountTypes = accountTypes;
        this.persons = persons;
        this.enterprises = enterprises;
        this.departments = departments;
    }

    @Override
    public String getColumnName(int column) {
        return header[column];
    }

    @Override
    public int getRowCount() {
        return persons.size();
    }

    @Override
    public int getColumnCount() {
        return header.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                if (persons.get(rowIndex) != null) {
                    return persons.get(rowIndex).getPersonName();
                }
                return "";
            case 1:
                if (accounts.get(rowIndex) != null) {
                    return accounts.get(rowIndex).getUsername();
                }
                return "";
            case 2:
                if (accountTypes.get(rowIndex) != null) {
                    return accountTypes.get(rowIndex).getAccountTypeName();
                }
                return "";
            case 3:
                if (enterprises.get(rowIndex) != null) {
                    return enterprises.get(rowIndex).getEnterpriseName();
                }
                return "";
            case 4:
                if (departments.get(rowIndex) != null) {
                    return departments.get(rowIndex).getDepartmentName();
                }
                return "";
            default:
                return "";
        }
    }
}

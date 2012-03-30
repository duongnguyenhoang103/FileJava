/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.account.manager.gui.model;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import vn.com.hkt.account.manager.api.IPermissionDAO;
import vn.com.hkt.account.manager.api.IUserInterfaceDAO;
import vn.com.hkt.account.manager.entities.InterfacePermission;
import vn.com.hkt.account.manager.entities.Permission;
import vn.com.hkt.account.manager.entities.UserInterface;
import vn.com.hkt.account.manager.spi.PermissionDAO;
import vn.com.hkt.account.manager.spi.UserInterfaceDAO;

/**
 *
 * @author Admin
 */
public class ModelTableInterfacePermission extends AbstractTableModel {

    private final String[] header = new String[]{"Tên giao diện","Mô tả giao diện","Tên quyền","Mô tả quyền"};
    private List<InterfacePermission> list;
    private IUserInterfaceDAO userInterfaceDAO = new UserInterfaceDAO();
    private IPermissionDAO permissionDAO = new PermissionDAO();

    public ModelTableInterfacePermission(List<InterfacePermission> list) {
        this.list = list;
    }

    @Override
    public String getColumnName(int column) {
        return header[column];
    }

    @Override
    public int getRowCount() {
        return list.size();
    }

    @Override
    public int getColumnCount() {
        return header.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        UserInterface ui = userInterfaceDAO.getById(list.get(rowIndex).getUserInterfaceIdActual());
        Permission p = permissionDAO.getById(list.get(rowIndex).getPermissionIdActual());
        switch (columnIndex) {
            case 0:
                return ui.getInterfaceName();
            case 1:
                return ui.getDescription();
            case 2:
                return p.getPermissionName();
            case 3:
                return p.getDescription();
            default:
                return "";
        }
    }
}

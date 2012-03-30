/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.account.manager.utilities;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import org.openide.util.Lookup;
import vn.com.hkt.account.manager.api.IAccountDAO;
import vn.com.hkt.account.manager.api.IAccountTypeDAO;
import vn.com.hkt.account.manager.api.IInterfacePermissionDAO;
import vn.com.hkt.account.manager.api.IModuleDAO;
import vn.com.hkt.account.manager.api.IPermissionDAO;
import vn.com.hkt.account.manager.api.IUserInterfaceDAO;
import vn.com.hkt.account.manager.entities.Account;
import vn.com.hkt.account.manager.entities.AccountType;
import vn.com.hkt.account.manager.entities.InterfacePermission;
import vn.com.hkt.account.manager.entities.Module;
import vn.com.hkt.account.manager.entities.Permission;
import vn.com.hkt.account.manager.entities.UserInterface;
import vn.com.hkt.account.manager.spi.AccountDAO;
import vn.com.hkt.account.manager.spi.AccountTypeDAO;
import vn.com.hkt.account.manager.spi.InterfacePermissionDAO;
import vn.com.hkt.account.manager.spi.ModuleDAO;
import vn.com.hkt.account.manager.spi.PermissionDAO;
import vn.com.hkt.account.manager.spi.UserInterfaceDAO;
import vn.com.hkt.authenticate.identity.IdentityPermission;
import vn.com.hkt.pilot.identity.presentation.api.IUserInterface;

/**
 *
 * @author Admin
 */
public class SuperAdmin {

    private IAccountDAO accountDAO = new AccountDAO();
    private IAccountTypeDAO accountTypeDAO = new AccountTypeDAO();
    private IUserInterfaceDAO userInterfaceDAO = new UserInterfaceDAO();
    private IPermissionDAO permissionDAO = new PermissionDAO();
    private IInterfacePermissionDAO interfacePermissionDAO = new InterfacePermissionDAO();
    private IModuleDAO moduleDAO = new ModuleDAO();
    private List<Module> modules;
    private List<AccountType> accountTypes;
    private List<UserInterface> userInterfaces;
    private List<IUserInterface> listWindowDeclare;
    private List<InterfacePermission> interfacePermissions;
    private List<Permission> permissions;
    private List<Account> accounts;

    public SuperAdmin() {
        listWindowDeclare = (List<IUserInterface>) Lookup.getDefault().lookupAll(IUserInterface.class);
        permissions = permissionDAO.selectAll();
        accountTypes = accountTypeDAO.selectAll();
    }

    private AccountType getAccountTypeSuperAddmin() {
        if (getPermissionFullFunction() == null) {            
            return null;
        }
        if (!checkListWindow()) {            
            return null;
        }
        for (int i = 0; i < accountTypes.size(); i++) {
            if (checkPermissionSuperAdmin(accountTypes.get(i))) {             
                return accountTypes.get(i);
            }
        }        
        return addAccountTypSuperAdmin();
    }

    private Permission getPermissionFullFunction() {
        permissions = permissionDAO.selectAll();
        for (int i = 0; i < permissions.size(); i++) {
            if (permissions.get(i).getPermissionFunction() == IdentityPermission.PERMISSION_FUNCTION_EDIT) {
                return permissions.get(i);
            }
        }
        Permission permission = new Permission();
        permission.setPermissionFunction(IdentityPermission.PERMISSION_FUNCTION_EDIT);
        permission.setPermissionName("Xem Sua Xoa");        
        if (!permissionDAO.insert(permission)) {
            JOptionPane.showMessageDialog(null, "thêm quyền quản lý lỗis");
            return null;
        }
        return permission;
    }

    private Module getModule(String moduleName) {
        modules = moduleDAO.select(Module.FIELD_MODULE_NAME, moduleName);
        if (modules.size() > 0) {
            return modules.get(0);
        } else {
            return null;
        }
    }

    private boolean checkListWindow() {
        userInterfaces = userInterfaceDAO.selectAll();
        for (int i = 0; i < listWindowDeclare.size(); i++) {
            String interfaceName = listWindowDeclare.get(i).getUserInterfaceName();
            String description = listWindowDeclare.get(i).getUserInterfaceDescription();
            String moduleName = listWindowDeclare.get(i).getModuleName();
            boolean had = false;
            for (int j = 0; j < userInterfaces.size(); j++) {
                if (userInterfaces.get(j).getInterfaceName().equals(interfaceName)) {
                    had = true;
                    break;
                }
            }
            if (!had) {
                Module module = getModule(moduleName);
                if (module == null) {
                    module = new Module();
                    module.setModuleName(moduleName);
                    if (!moduleDAO.insert(module)) {
                        JOptionPane.showMessageDialog(null, "Thêm module lỗi");
                        return false;
                    }
                }
                UserInterface ui = new UserInterface();
                ui.setInterfaceName(interfaceName);
                ui.setDescription(moduleName);
                ui.setDescription(description);
                ui.setModuleIdActual(module.getId());
                if (!userInterfaceDAO.insert(ui)) {
                    JOptionPane.showMessageDialog(null, "Thêm giao diện lỗis");
                    return false;
                }
                List<Integer> list = module.getUserInterfacesId();
                if (list == null) {
                    list = new ArrayList<Integer>();
                }
                list.add(ui.getId());
                module.setUserInterfacesId(list);
                if (!moduleDAO.update(module)) {
                    JOptionPane.showMessageDialog(null, "cập nhật lại module lỗi");
                    return false;
                }
            }
        }
        userInterfaces = userInterfaceDAO.selectAll();
        return true;
    }

    private boolean checkPermissionSuperAdmin(AccountType accountType) {
        if (!checkListWindow()) {
            return false;
        }
        interfacePermissions =
                interfacePermissionDAO.select(InterfacePermission.FIELD_ACCOUNT_TYPE_ID_ACTUAL, String.valueOf(accountType.getId()));
        for (int i = 0; i < userInterfaces.size(); i++) {
            boolean had = false;
            for (int j = 0; j < interfacePermissions.size(); j++) {
                if (userInterfaces.get(i).getId() == interfacePermissions.get(j).getUserInterfaceIdActual()) {
                    Permission permission = permissionDAO.getById(interfacePermissions.get(i).getPermissionIdActual());
                    if (permission == null) {
                        return false;
                    }
                    if (permission.getPermissionFunction() != IdentityPermission.PERMISSION_FUNCTION_EDIT) {
                        return false;
                    }
                    had = true;
                    break;
                }
            }
            if (!had) {
                return false;
            }
        }
        return true;
    }

    private AccountType addAccountTypSuperAdmin() {
        List<AccountType> list = accountTypeDAO.select(AccountType.FIELD_ACCOUNT_TYPE_NAME, "super admin");
        for (int i = 1; i < list.size(); i++) {
            accountTypeDAO.delete(list.get(i).getId());
        }
        AccountType accountTypeSuperAdmin = new AccountType();
        boolean had = false;
        if (list.size() > 0) {
            had = true;
            accountTypeSuperAdmin = list.get(0);
        }

        accountTypeSuperAdmin.setAccountTypeName("super admin");
        List<Integer> listInterfacePermissionID = new ArrayList<Integer>();
        accountTypeSuperAdmin.setInterfacePermisstionsIdActual(listInterfacePermissionID);

        if (!had) {
            if (!accountTypeDAO.insert(accountTypeSuperAdmin)) {
                JOptionPane.showMessageDialog(null, "Thêm lớp quản lý super admin lỗi");
                return null;
            }
        }
        else{
            if (!accountTypeDAO.update(accountTypeSuperAdmin)) {
                JOptionPane.showMessageDialog(null, "Thiết lập lại lớp quản lý super adin lỗi ");
                return null;
            }
        }

        Permission permissionFullFunction = getPermissionFullFunction();
        if (permissionFullFunction == null) {
            return null;
        }

        for (int i = 0; i < userInterfaces.size(); i++) {
            InterfacePermission ip = new InterfacePermission();
            ip.setAccountTypeIdActual(accountTypeSuperAdmin.getId());
            ip.setPermissionIdActual(permissionFullFunction.getId());
            ip.setUserInterfaceIdActual(userInterfaces.get(i).getId());
            if (!interfacePermissionDAO.insert(ip)) {
                JOptionPane.showMessageDialog(null, "Thêm quyền quản lý đối với giao diện lỗi");
                return null;
            }
            listInterfacePermissionID.add(ip.getId());
        }
        accountTypeSuperAdmin.setInterfacePermisstionsIdActual(listInterfacePermissionID);
        if (!accountTypeDAO.update(accountTypeSuperAdmin)) {
            JOptionPane.showMessageDialog(null, "Thiết lập lớp quản lý super adin lỗi");
            return null;
        }
        return accountTypeSuperAdmin;
    }

    public Account getAccountSuperAdmin() {
        accounts = accountDAO.selectAll();
        for (int i = 0; i < accounts.size(); i++) {
            AccountType accountType = accountTypeDAO.getById((accounts.get(i).getAccountTypeIdActual()));
            if (accountType != null) {
                if (checkPermissionSuperAdmin(accountType)) {
                    return accounts.get(i);
                }
            }
        }        
        AccountType accountTypeSuperAdmin = getAccountTypeSuperAddmin();        
        Account accountAdmin = checkAccountSuperAddmin();        
        if (accountAdmin == null) {
            accountAdmin = new Account();
        }           
        accountAdmin.setUsername("super admin");
        accountAdmin.setPassword("super admin");
        accountAdmin.setAccountTypeIdActual(accountTypeSuperAdmin.getId());        
        if (!accountDAO.insert(accountAdmin)) {
            JOptionPane.showMessageDialog(null, "thêm account super admin lỗi");
            return null;
        }
        return accountAdmin;
    }

    private Account checkAccountSuperAddmin() {
        List<Account> list = accountDAO.select(Account.FIELD_USERNAME, "super admin");        
        for (int i = 1; i < list.size(); i++) {
            accountDAO.delete(list.get(i).getId());
        }                        
        if (list.size() > 0) {
            return list.get(0);
        } else {
            return null;
        }
    }
}

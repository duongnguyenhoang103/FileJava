/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.account.manager.tools;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import org.openide.DialogDisplayer;
import org.openide.LifecycleManager;
import org.openide.NotifyDescriptor;
import org.openide.util.lookup.ServiceProvider;
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
import vn.com.hkt.account.manager.gui.panel.Login;
import vn.com.hkt.account.manager.spi.AccountDAO;
import vn.com.hkt.account.manager.spi.AccountTypeDAO;
import vn.com.hkt.account.manager.spi.InterfacePermissionDAO;
import vn.com.hkt.account.manager.spi.ModuleDAO;
import vn.com.hkt.account.manager.spi.PermissionDAO;
import vn.com.hkt.account.manager.spi.UserInterfaceDAO;
import vn.com.hkt.account.manager.utilities.SuperAdmin;
import vn.com.hkt.authenticate.api.AccountManagerAPI;
import vn.com.hkt.component.transparent.JButtonTransparent;

/**
 *
 * @author Admin
 */
@ServiceProvider(service = AccountManagerAPI.class)
public class AccountManager implements AccountManagerAPI {

    private IAccountDAO accountDAO;
    private IAccountTypeDAO accountTypeDAO;
    private IInterfacePermissionDAO interfacePermissionDAO;
    private IUserInterfaceDAO userInterfaceDAO;
    private IPermissionDAO permissionDAO;
    private IModuleDAO moduleDAO;
    private Account accountCurrent;
    private String username;
    private String password;
    private NotifyDescriptor notifyDescriptor;
    private Login panelLogin;
    private boolean runLogin = false;

    public AccountManager() {
        accountCurrent = null;
        accountDAO = new AccountDAO();
        accountTypeDAO = new AccountTypeDAO();
        interfacePermissionDAO = new InterfacePermissionDAO();
        userInterfaceDAO = new UserInterfaceDAO();
        moduleDAO = new ModuleDAO();
        permissionDAO = new PermissionDAO();
    }

    @Override
    public boolean login() {
        // TODO  check
        if (runLogin) {
            return false;
        }        
        runLogin = true;
        if (new SuperAdmin().getAccountSuperAdmin() == null) {
            runLogin = false;
            return false;
        }        
        panelLogin = new Login();
        panelLogin.setVisible(true);
        JButtonTransparent btnOk = new JButtonTransparent("OK");
        btnOk.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                username = panelLogin.getUsername();
                password = panelLogin.getPassword();
                getAccountCurent();
            }
        });
        JButtonTransparent btnCancel = new JButtonTransparent("Cancel");
        btnCancel.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                LifecycleManager.getDefault().exit();
            }
        });
        notifyDescriptor = new NotifyDescriptor.Confirmation(panelLogin, "Login");
        notifyDescriptor.setOptions(new Object[]{btnOk, btnCancel});
        notifyDescriptor.addPropertyChangeListener(new PropertyChangeListener() {

            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                SwingUtilities.invokeLater(new Runnable() {

                    @Override
                    public void run() {
                        if (notifyDescriptor.getValue().toString().equals("-1")) {
                            LifecycleManager.getDefault().exit();
                        }
                    }
                });
            }
        });
        DialogDisplayer.getDefault().notifyLater(notifyDescriptor);
        return true;
    }

    @Override
    public boolean checkLogin() {  
        if (accountCurrent != null) {
            return true;
        }
        return false;
    }

    @Override
    public void logout() {
        JOptionPane.showMessageDialog(null, "Logout roi");
        notifyDescriptor = null;
        accountCurrent = null;
    }

    @Override
    public int getPermistion(String moduleName, String userInterfaceName) {
        return checkPermistion(userInterfaceName, moduleName).getPermissionFunction();
    }

    private UserInterface getUserInterface(String moduleName, String userInterfaceName) {
        Module module = null;
        List<Module> modules = moduleDAO.selectAll();
        for (int i = 0; i < modules.size(); i++) {
            if (modules.get(i).getModuleName().equals(moduleName)) {
                module = modules.get(i);
                break;
            }
        }
        if (module == null) {
            return null;
        }
        for (int i = 0; i < module.getUserInterfacesId().size(); i++) {
            UserInterface ui = userInterfaceDAO.getById(module.getUserInterfacesId().get(i));
            if (ui != null && ui.getInterfaceName().equals(userInterfaceName)) {
                return ui;
            }
        }
        return null;
    }

    private Permission checkPermistion(String userInterfaceName, String moduleName) {
        AccountType accountType = accountTypeDAO.getById(accountCurrent.getAccountTypeIdActual());
        if (accountType == null) {
            return null;
        }
        UserInterface ui = getUserInterface(moduleName, userInterfaceName);
        if (ui == null) {
            return null;
        }
        List<InterfacePermission> ips = interfacePermissionDAO.selectAll();
        for (int i = 0; i < ips.size(); i++) {
            if (ips.get(i).getAccountTypeIdActual() == accountType.getId()
                    && ips.get(i).getUserInterfaceIdActual() == ui.getId()) {
                Permission permission = permissionDAO.getById(ips.get(i).getPermissionIdActual());
                return permission;
            }
        }
        return null;
    }

    private void getAccountCurent() {
        List<Account> accounts = accountDAO.selectAll();
        for (int i = 0; i < accounts.size(); i++) {
            if (accounts.get(i).getUsername().equals(username)
                    && accounts.get(i).getPassword().equals(password)) {
                accountCurrent = accounts.get(i);
                runLogin = false;
                return;
            }
        }
        accountCurrent = null;
        runLogin = true;
        DialogDisplayer.getDefault().notifyLater(notifyDescriptor);
    }

    @Override
    public int getAccountCurrent() {
        if (accountCurrent != null) {
            return accountCurrent.getId();
        }
        return -1;
    }

    @Override
    public int getPersonIdByAccountId(int accountId) {
        Account account = accountDAO.getById(accountId);
        if (account != null) {
            return account.getPersonIdActual();
        }
        return -1;
    }

    @Override
    public String getUsernameByAccountId(int accountId) {
        Account account = accountDAO.getById(accountId);
        if (account != null) {
            return account.getUsername();
        }
        return null;
    }
}

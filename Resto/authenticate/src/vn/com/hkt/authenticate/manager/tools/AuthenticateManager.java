/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.authenticate.manager.tools;

import org.openide.util.Lookup;
import vn.com.hkt.authenticate.api.AccountManagerAPI;
import vn.com.hkt.authenticate.identity.IdentityPermission;

/**
 *
 * @author Admin
 */
public class AuthenticateManager {

    private AccountManagerAPI accountManager = null;
    private static AuthenticateManager authenticateManager;

    public static AuthenticateManager getAuthenticateManager() {
        if (authenticateManager == null) {
            authenticateManager = new AuthenticateManager();
        }
        return authenticateManager;
    }

    private AuthenticateManager() {
        getAccountManager();
    }

    private AccountManagerAPI getAccountManager() {
        accountManager = Lookup.getDefault().lookup(AccountManagerAPI.class);
        return accountManager;
    }

    public boolean login() {
        if (accountManager == null) {
            return true;
        }
        if (!accountManager.login()) {
            return false;
        }
        return true;
    }

    public void logout() {
        if (accountManager != null) {
            accountManager.logout();
        }
    }

    public boolean checkLogined() {
        if (accountManager == null) {
            return true;
        }
        return accountManager.checkLogin();
    }

    public int getPermistion(String moduleName, String userInterfaceName) {
        if (accountManager == null) {
            return IdentityPermission.PERMISSION_FUNCTION_EDIT;
        }
        return accountManager.getPermistion(moduleName, userInterfaceName);
    }

    public int getIdOfAccountCurrent() {
        if (accountManager != null) {
            return accountManager.getAccountCurrent();
        }
        return -1;
    }

    public int getPersonIdByAccountId(int accountId) {
        if (accountManager != null) {
            return accountManager.getPersonIdByAccountId(accountId);
        }
        return -1;
    }

    public String getUsernameByAccountId(int accountId) {
        if (accountManager != null) {
            return accountManager.getUsernameByAccountId(accountId);
        }
        return null;
    }
}

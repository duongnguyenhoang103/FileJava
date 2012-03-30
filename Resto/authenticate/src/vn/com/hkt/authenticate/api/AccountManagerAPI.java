/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.authenticate.api;

/**
 *
 * @author Admin
 */
public interface AccountManagerAPI {

    public boolean login();

    public void logout();

    public boolean checkLogin();

    public int getPermistion(String moduleName, String userInterfaceName);

    public int getAccountCurrent();

    public String getUsernameByAccountId(int accountId);

    public int getPersonIdByAccountId(int accountId);
}

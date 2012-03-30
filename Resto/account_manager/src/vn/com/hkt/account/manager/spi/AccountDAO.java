/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.account.manager.spi;

import vn.com.hkt.account.manager.api.IAccountDAO;
import vn.com.hkt.account.manager.entities.Account;
import vn.com.hkt.account.manager.utilities.PersistenceUtitlity;
import vn.com.hkt.pilot.identity.access.spi.AccessData;

/**
 *
 * @author Admin
 */
public class AccountDAO extends AccessData<Account> implements IAccountDAO {

    public AccountDAO() {
        setAccessData(PersistenceUtitlity.getEMF(), Account.class);
    }
}

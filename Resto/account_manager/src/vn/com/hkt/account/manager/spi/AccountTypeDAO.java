/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.account.manager.spi;

import vn.com.hkt.account.manager.api.IAccountTypeDAO;
import vn.com.hkt.account.manager.entities.AccountType;
import vn.com.hkt.account.manager.utilities.PersistenceUtitlity;
import vn.com.hkt.pilot.identity.access.spi.AccessData;

/**
 *
 * @author Admin
 */
public class AccountTypeDAO extends AccessData<AccountType> implements IAccountTypeDAO {

    public AccountTypeDAO() {
        setAccessData(PersistenceUtitlity.getEMF(), AccountType.class);
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.bom.operation.dao;

import org.openide.util.lookup.ServiceProvider;
import vn.com.hkt.basic.api.IAccountNumberBN;
import vn.com.hkt.basic.jpa.PersistenceUltility;
import vn.com.hkt.pilot.entities.AccountNumber;
import vn.com.hkt.pilot.identity.access.spi.AccessData;

/**
 *
 * @author khangpn
 */
@ServiceProvider(service=IAccountNumberBN.class)
public class AccountNumberBN extends AccessData<AccountNumber> implements IAccountNumberBN {

    public AccountNumberBN() {
        setAccessData(PersistenceUltility.getEMF(), AccountNumber.class);
    }
    
}

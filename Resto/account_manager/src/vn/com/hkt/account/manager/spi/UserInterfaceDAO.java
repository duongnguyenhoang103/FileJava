/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.account.manager.spi;

import vn.com.hkt.account.manager.api.IUserInterfaceDAO;
import vn.com.hkt.account.manager.entities.UserInterface;
import vn.com.hkt.account.manager.utilities.PersistenceUtitlity;
import vn.com.hkt.pilot.identity.access.spi.AccessData;

/**
 *
 * @author Admin
 */
public class UserInterfaceDAO extends AccessData<UserInterface> implements IUserInterfaceDAO {

    public UserInterfaceDAO() {
        setAccessData(PersistenceUtitlity.getEMF(), UserInterface.class);
    }
}

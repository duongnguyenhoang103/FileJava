/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.account.manager.spi;

import vn.com.hkt.account.manager.api.IInterfacePermissionDAO;
import vn.com.hkt.account.manager.entities.InterfacePermission;
import vn.com.hkt.account.manager.utilities.PersistenceUtitlity;
import vn.com.hkt.pilot.identity.access.spi.AccessData;

/**
 *
 * @author Admin
 */
public class InterfacePermissionDAO extends AccessData<InterfacePermission> implements IInterfacePermissionDAO {

    public InterfacePermissionDAO() {
        setAccessData(PersistenceUtitlity.getEMF(), InterfacePermission.class);
    }
}

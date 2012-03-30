/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.account.manager.spi;

import vn.com.hkt.account.manager.api.IPermissionDAO;
import vn.com.hkt.account.manager.entities.Permission;
import vn.com.hkt.account.manager.utilities.PersistenceUtitlity;
import vn.com.hkt.pilot.identity.access.spi.AccessData;

/**
 *
 * @author Admin
 */
public class PermissionDAO extends AccessData<Permission> implements IPermissionDAO {

    public PermissionDAO() {
        setAccessData(PersistenceUtitlity.getEMF(), Permission.class);
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.license.spi;

import vn.com.hkt.license.api.IKeyInstallDAO;
import vn.com.hkt.license.entities.KeyInstall;
import vn.com.hkt.license.utilities.PersistenceUtitlity;
import vn.com.hkt.pilot.identity.access.spi.AccessData;

/**
 *
 * @author Admin
 */
public class KeyInstallDAO extends AccessData<KeyInstall> implements IKeyInstallDAO {

    public KeyInstallDAO() {
        setAccessData(PersistenceUtitlity.getEMF(), KeyInstall.class);
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.license.spi;

import vn.com.hkt.license.api.IKeyLicenseDAO;
import vn.com.hkt.license.entities.KeyLicense;
import vn.com.hkt.license.utilities.PersistenceUtitlity;
import vn.com.hkt.pilot.identity.access.spi.AccessData;

/**
 *
 * @author Admin
 */
public class KeyLicenseDAO extends AccessData<KeyLicense> implements IKeyLicenseDAO {

    public KeyLicenseDAO() {
        setAccessData(PersistenceUtitlity.getEMF(), KeyLicense.class);
    }
}

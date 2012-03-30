/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.account.manager.spi;

import vn.com.hkt.account.manager.api.IModuleDAO;
import vn.com.hkt.account.manager.entities.Module;
import vn.com.hkt.account.manager.utilities.PersistenceUtitlity;
import vn.com.hkt.pilot.identity.access.spi.AccessData;

/**
 *
 * @author Admin
 */
public class ModuleDAO extends AccessData<Module> implements IModuleDAO {

    public ModuleDAO() {
        setAccessData(PersistenceUtitlity.getEMF(), Module.class);
    }
}

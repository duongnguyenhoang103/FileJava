/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.pilot.entities.system.dao;

import org.openide.util.lookup.ServiceProvider;
import vn.com.hkt.basic.api.ISystemSotfwareBN;
import vn.com.hkt.basic.jpa.PersistenceUltility;
import vn.com.hkt.pilot.entities.system.SystemSoftware;
import vn.com.hkt.pilot.identity.access.spi.AccessData;

/**
 *
 * @author BinLe
 */
@ServiceProvider(service=ISystemSotfwareBN.class)
public class SystemSotfwareDAO extends AccessData<SystemSoftware> implements ISystemSotfwareBN{

    public SystemSotfwareDAO() {
        setAccessData(PersistenceUltility.getEMF(),SystemSoftware.class);
    }      
    
}

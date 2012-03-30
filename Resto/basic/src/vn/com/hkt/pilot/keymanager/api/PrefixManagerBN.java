/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.pilot.keymanager.api;

import java.util.List;
import vn.com.hkt.basic.jpa.PersistenceUltility;
import vn.com.hkt.pilot.entities.PrefixManager;
import vn.com.hkt.pilot.identity.access.spi.AccessData;

/**
 *
 * @author khangpn
 */
public class PrefixManagerBN extends AccessData<PrefixManager> {

    public PrefixManagerBN() {
        setAccessData(PersistenceUltility.getEMF(), PrefixManager.class);
    }

    public List<PrefixManager> getPrefixByEntityName(String name) {
        return select(PrefixManager.FIELD_ENTITY_TYPE_NAME, name);
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.bom.operation.dao;

import org.openide.util.lookup.ServiceProvider;
import vn.com.hkt.basic.api.IStatuationBN;
import vn.com.hkt.basic.jpa.PersistenceUltility;
import vn.com.hkt.pilot.entities.Statuation;
import vn.com.hkt.pilot.identity.access.spi.AccessData;

/**
 *
 * @author khangpn
 */
@ServiceProvider(service=IStatuationBN.class)
public class StatuationBN extends AccessData<Statuation> implements IStatuationBN {

    public StatuationBN() {
        setAccessData(PersistenceUltility.getEMF(), Statuation.class);
    }
    
}

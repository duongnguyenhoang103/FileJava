/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.bom.operation.dao;

import org.openide.util.lookup.ServiceProvider;
import vn.com.hkt.basic.api.IKindOfTaxBN;
import vn.com.hkt.basic.jpa.PersistenceUltility;
import vn.com.hkt.pilot.entities.KindOfTax;
import vn.com.hkt.pilot.identity.access.spi.AccessData;

/**
 *
 * @author khangpn
 */
@ServiceProvider(service=IKindOfTaxBN.class)
public class KindOfTaxBN extends AccessData<KindOfTax> implements IKindOfTaxBN {

    public KindOfTaxBN() {
        setAccessData(PersistenceUltility.getEMF(), KindOfTax.class);
    }
    
}

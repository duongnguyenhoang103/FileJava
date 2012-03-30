/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.pilot.sb42.pricesheet.dao;

import org.openide.util.lookup.ServiceProvider;
import vn.com.hkt.pilot.identity.access.spi.AccessData;
import vn.com.hkt.pilot.sb42.pricesheet.dao.api.ISupplementaryProductBN;
import vn.com.hkt.pilot.sb42.pricesheet.jpa.utils.PersistenceUtility;
import vn.com.hkt.pilot.sb42.pricesheeta.entity.SupplementaryProduct;

/**
 *
 * @author khangpn
 */
@ServiceProvider(service = ISupplementaryProductBN.class)
public class SupplementaryProductBN extends AccessData<SupplementaryProduct>
        implements ISupplementaryProductBN {

    public SupplementaryProductBN() {
        setAccessData(PersistenceUtility.getEmf(), SupplementaryProduct.class);
    }
}

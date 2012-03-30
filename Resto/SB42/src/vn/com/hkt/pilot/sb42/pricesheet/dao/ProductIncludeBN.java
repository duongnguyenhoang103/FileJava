/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.pilot.sb42.pricesheet.dao;

import org.openide.util.lookup.ServiceProvider;
import vn.com.hkt.pilot.identity.access.spi.AccessData;
import vn.com.hkt.pilot.sb42.pricesheet.dao.api.IProductIncludeBN;
import vn.com.hkt.pilot.sb42.pricesheet.jpa.utils.PersistenceUtility;
import vn.com.hkt.pilot.sb42.pricesheeta.entity.ProductInclude;


/**
 *
 * @author khangpn
 */
@ServiceProvider(service=IProductIncludeBN.class)
public class ProductIncludeBN extends AccessData<ProductInclude> implements IProductIncludeBN {

    public ProductIncludeBN() {
        setAccessData(PersistenceUtility.getEmf(), ProductInclude.class);
    }

    
    
    
}

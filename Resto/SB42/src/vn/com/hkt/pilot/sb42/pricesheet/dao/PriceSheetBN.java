/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.pilot.sb42.pricesheet.dao;

import java.util.List;
import org.openide.util.lookup.ServiceProvider;
import vn.com.hkt.pilot.identity.access.spi.AccessData;
import vn.com.hkt.pilot.sb42.pricesheet.dao.api.IPriceSheetBN;
import vn.com.hkt.pilot.sb42.pricesheeta.entity.PriceSheet;
import vn.com.hkt.pilot.sb42.pricesheet.jpa.utils.PersistenceUtility;

/**
 *
 * @author HKT01
 */
@ServiceProvider(service=IPriceSheetBN.class)
public class PriceSheetBN extends AccessData<PriceSheet> implements IPriceSheetBN {

    public PriceSheetBN() {
        setAccessData(PersistenceUtility.getEmf(), PriceSheet.class);
    }

    public PriceSheet getPriceSheetByID(String id) {
        List<PriceSheet> list = select(PriceSheet.FIELD_PRICE_SHEET_ID, id);
        if (list.size() > 0) {
            return list.get(0);
        }
        return null;
    }
}

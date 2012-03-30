/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.pilot.sb51.spi;

import vn.com.hkt.pilot.identity.access.spi.AccessData;
import vn.com.hkt.pilot.sb51.api.IBillTypeBN;
import vn.com.hkt.pilot.sb51.entity.BillType;
import vn.com.hkt.pilot.sb51.jpa.PersistenceUltility;

/**
 *
 * @author BinLe
 */
public class BillTypeBN extends AccessData<BillType> implements IBillTypeBN {

    public BillTypeBN() {
        setAccessData(PersistenceUltility.getEMF(), BillType.class);
    }
}

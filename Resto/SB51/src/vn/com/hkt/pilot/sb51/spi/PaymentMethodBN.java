/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.pilot.sb51.spi;

import vn.com.hkt.pilot.identity.access.spi.AccessData;
import vn.com.hkt.pilot.sb51.api.IPaymentMethodBN;
import vn.com.hkt.pilot.sb51.entity.PaymentMethod;
import vn.com.hkt.pilot.sb51.jpa.PersistenceUltility;

/**
 *
 * @author BinLe
 */
public class PaymentMethodBN extends AccessData<PaymentMethod> implements IPaymentMethodBN {

    public PaymentMethodBN() {
        setAccessData(PersistenceUltility.getEMF(), PaymentMethod.class);
    }

    
    
}

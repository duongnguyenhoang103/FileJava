/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.pilot.promotion.dao;

import vn.com.hkt.pilot.identity.access.spi.AccessData;
import vn.com.hkt.pilot.promotion.entity.SaleOffOption;
import vn.com.hkt.pilot.promotion.jpa.utils.jpaUtils;

/**
 *
 * @author khangpn
 */
public class SaleOffOptionBN extends AccessData<SaleOffOption> {
    
    public SaleOffOptionBN() {
        setAccessData(jpaUtils.getEmf(), SaleOffOption.class);
    }
}

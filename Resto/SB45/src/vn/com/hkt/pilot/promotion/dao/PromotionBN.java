/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.pilot.promotion.dao;

import vn.com.hkt.pilot.identity.access.spi.AccessData;
import vn.com.hkt.pilot.promotion.entity.Promotion;
import vn.com.hkt.pilot.promotion.jpa.utils.jpaUtils;

/**
 *
 * @author HKT01
 */
public class PromotionBN extends AccessData<Promotion> {
    
    public PromotionBN() {
        setAccessData(jpaUtils.getEmf(), Promotion.class);
    }
}

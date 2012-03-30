/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.pilot.promotion.dao;

import vn.com.hkt.pilot.identity.access.spi.AccessData;
import vn.com.hkt.pilot.promotion.entity.ClassifiedByEnterprise;
import vn.com.hkt.pilot.promotion.jpa.utils.jpaUtils;

/**
 *
 * @author khangpn
 */
public class ClassifiedByEnterpriseBN extends AccessData<ClassifiedByEnterprise> {
    
    public ClassifiedByEnterpriseBN() {
        setAccessData(jpaUtils.getEmf(), ClassifiedByEnterprise.class);
    }
}

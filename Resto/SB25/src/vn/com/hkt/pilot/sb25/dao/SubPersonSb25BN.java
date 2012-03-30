/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.pilot.sb25.dao;

import vn.com.hkt.pilot.identity.access.spi.AccessData;
import vn.com.hkt.pilot.sb25.jpa.util.SubPersonSB25JPAUtil;
import vn.com.hkt.pilot.sb25.subpersonsb25.entity.SubPersonSB25;

/**
 *
 * @author khangpn
 */
public class SubPersonSb25BN extends AccessData<SubPersonSB25> {
    
    public SubPersonSb25BN() {
        setAccessData(SubPersonSB25JPAUtil.getEmf(), SubPersonSB25.class);
    }
}

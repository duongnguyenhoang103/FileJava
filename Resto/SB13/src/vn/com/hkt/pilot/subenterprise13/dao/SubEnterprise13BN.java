/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.pilot.subenterprise13.dao;

import vn.com.hkt.pilot.identity.access.spi.AccessData;
import vn.com.hkt.pilot.subenterprise13.entity.SubEnterprise13;
import vn.com.hkt.pilot.subenterprise13.jpa.util.SubEnterprise13JPAUtil;

/**
 *
 * @author khangpn
 */
public class SubEnterprise13BN extends AccessData<SubEnterprise13> {

    public SubEnterprise13BN() {
        setAccessData(SubEnterprise13JPAUtil.getEmf(), SubEnterprise13.class);
    }
}

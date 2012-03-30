/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.pilot.subpersonsb23.dao;

import vn.com.hkt.pilot.identity.access.spi.AccessData;
import vn.com.hkt.pilot.sb23.entity.Allowances;
import vn.com.hkt.pilot.sb23.jpa.util.SubPersonSB23JPAUtil;

/**
 *
 * @author khangpn
 */
public class AllowancesBN extends AccessData<Allowances> {

    public AllowancesBN() {
        setAccessData(SubPersonSB23JPAUtil.getEmf(), Allowances.class);
    }
    
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.pilot.sb22.person.extW23.dao;

import vn.com.hkt.pilot.identity.access.spi.AccessData;
import vn.com.hkt.pilot.sb22.generic.jpas.utils.SubPersonSB22JPAUtil;
import vn.com.hkt.pilot.sb22.person.extW23.entity.AddressInfo;

/**
 *
 * @author khangpn
 */
public class AddressInfoBN extends AccessData<AddressInfo> {
    
    public AddressInfoBN() {
        setAccessData(SubPersonSB22JPAUtil.getEmf(), AddressInfo.class);
    }
}

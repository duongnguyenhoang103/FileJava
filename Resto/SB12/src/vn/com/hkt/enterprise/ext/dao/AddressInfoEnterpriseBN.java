/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.enterprise.ext.dao;

import vn.com.hkt.enterprise.ext.entity.AddressInfoEnterprise;
import vn.com.hkt.generic.jpas.utils.PersistenceUtility;
import vn.com.hkt.pilot.identity.access.spi.AccessData;


/**
 *
 * @author khangpn
 */
public class AddressInfoEnterpriseBN extends AccessData<AddressInfoEnterprise> {
    
    public AddressInfoEnterpriseBN() {
        setAccessData(PersistenceUtility.getEmf(), AddressInfoEnterprise.class);
    }
}

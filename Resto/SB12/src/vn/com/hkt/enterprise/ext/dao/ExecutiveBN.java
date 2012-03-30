/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.enterprise.ext.dao;

import vn.com.hkt.enterprise.ext.entity.ExecutiveOffice;
import vn.com.hkt.extension.api.IExecutiveBN;
import vn.com.hkt.generic.jpas.utils.PersistenceUtility;
import vn.com.hkt.pilot.identity.access.spi.AccessData;

/**
 *
 * @author khangpn
 */
public class ExecutiveBN extends AccessData<ExecutiveOffice> implements IExecutiveBN {

    public ExecutiveBN() {
        setAccessData(PersistenceUtility.getEmf(), ExecutiveOffice.class);
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.bom.operation.dao;

import vn.com.hkt.basic.api.IOperationPartnerBN;
import vn.com.hkt.basic.jpa.PersistenceUltility;
import vn.com.hkt.pilot.entities.OperationPartner;
import vn.com.hkt.pilot.identity.access.spi.AccessData;

/**
 *
 * @author khangpn
 */
public class OperationPartnerBN extends AccessData<OperationPartner> implements IOperationPartnerBN {

    public OperationPartnerBN() {
        setAccessData(PersistenceUltility.getEMF(), OperationPartner.class);
    }
}

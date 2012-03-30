/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.bom.operation.dao;

import org.openide.util.lookup.ServiceProvider;
import vn.com.hkt.basic.api.IOperationDetailBN;
import vn.com.hkt.basic.jpa.PersistenceUltility;
import vn.com.hkt.pilot.entities.OperationDetail;
import vn.com.hkt.pilot.identity.access.spi.AccessData;

/**
 *
 * @author khangpn
 */
@ServiceProvider(service=IOperationDetailBN.class)
public class OperationDetailBN extends AccessData<OperationDetail> implements IOperationDetailBN {

    public OperationDetailBN() {
        setAccessData(PersistenceUltility.getEMF(), OperationDetail.class);
    }
    
}

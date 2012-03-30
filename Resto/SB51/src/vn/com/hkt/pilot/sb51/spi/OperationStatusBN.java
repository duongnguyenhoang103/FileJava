/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.pilot.sb51.spi;

import vn.com.hkt.pilot.identity.access.spi.AccessData;
import vn.com.hkt.pilot.sb51.api.IOperationStatusBN;
import vn.com.hkt.pilot.sb51.entity.OperationStatus;
import vn.com.hkt.pilot.sb51.jpa.PersistenceUltility;

/**
 *
 * @author BinLe
 */
public class OperationStatusBN extends AccessData<OperationStatus> implements IOperationStatusBN {

    public OperationStatusBN() {
        setAccessData(PersistenceUltility.getEMF(), OperationStatus.class);
    }
}

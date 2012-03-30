/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.pilot.sb51.spi;

import java.util.ArrayList;
import java.util.List;
import org.openide.util.Lookup;
import vn.com.hkt.basic.api.IOperationBN;
import vn.com.hkt.pilot.entities.Enterprise;
import vn.com.hkt.pilot.entities.Operation;
import vn.com.hkt.pilot.identity.access.spi.AccessData;
import vn.com.hkt.pilot.sb51.api.IOperationPaymentBN;
import vn.com.hkt.pilot.sb51.entity.OperationPayment;
import vn.com.hkt.pilot.sb51.jpa.PersistenceUltility;

/**
 *
 * @author BinLe
 */
public class OperationPaymentBN extends AccessData<OperationPayment> implements IOperationPaymentBN {

    private IOperationBN operationBN = Lookup.getDefault().lookup(IOperationBN.class);

    public OperationPaymentBN() {
        setAccessData(PersistenceUltility.getEMF(), OperationPayment.class);
    }

    public List<OperationPayment> getByEnterpriser(Enterprise e) {
        List<OperationPayment> list = selectAll();
        List<OperationPayment> lr = new ArrayList<OperationPayment>();
        for (int i = 0; i < list.size(); i++) {
            Operation p = operationBN.getById(list.get(i).getOperationIdActual());
            if (p.getEnterpriseIdActual() == e.getId()) {
                lr.add(list.get(i));
            }
        }
        return lr;
    }
}

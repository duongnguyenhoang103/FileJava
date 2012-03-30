/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.pilot.sb51.backup;

import java.util.Hashtable;
import java.util.List;
import org.openide.util.lookup.ServiceProvider;
import vn.com.hkt.pilot.backup.manager.api.IBackupModule;
import vn.com.hkt.pilot.entities.Operation;
import vn.com.hkt.pilot.sb51.entity.BillType;
import vn.com.hkt.pilot.sb51.entity.OperationPayment;
import vn.com.hkt.pilot.sb51.entity.OperationStatus;
import vn.com.hkt.pilot.sb51.entity.PaymentMethod;
import vn.com.hkt.pilot.sb51.spi.OperationPaymentBN;

/**
 *
 * @author BinLe
 */
@ServiceProvider(service=IBackupModule.class)
public class SB51Backup implements IBackupModule {

    private Hashtable<String, Hashtable<Integer, Integer>> hashTotal;

    @Override
    public void setHashId(Hashtable<String, Hashtable<Integer, Integer>> hashID) {
        this.hashTotal = hashID;
    }

    @Override
    public void backupAll() {
        backupOperationPayment();
    }

    private void backupOperationPayment() {
        Hashtable<Integer, Integer> ho = hashTotal.get(Operation.class.getSimpleName());
        Hashtable<Integer, Integer> hos = hashTotal.get(OperationStatus.class.getSimpleName());
        Hashtable<Integer, Integer> hpm = hashTotal.get(PaymentMethod.class.getSimpleName());
        Hashtable<Integer, Integer> hbt = hashTotal.get(BillType.class.getSimpleName());
        if (ho == null && hos == null && hpm == null && hbt == null) {
            return;
        }
        List<OperationPayment> ld = new OperationPaymentBN().selectAll();
        for (int i = 0; i < ld.size(); i++) {
            if (ho != null) {
                int idOld = ld.get(i).getOperationIdActual();
                int idNew = ho.get(idOld);
                ld.get(i).setOperationIdActual(idNew);
            }
            if (hos != null) {
                int idOld = ld.get(i).getOperationStatusIdActual();
                int idNew = hos.get(idOld);
                ld.get(i).setOperationStatusIdActual(idNew);
            }
            if (hpm != null) {
                int idOld = ld.get(i).getPaymentMethodIdActual();
                int idNew = hpm.get(idOld);
                ld.get(i).setPaymentMethodIdActual(idNew);
            }
            if (hbt != null) {
                int idOld = ld.get(i).getBillTypeIdActual();
                int idNew = hbt.get(idOld);
                ld.get(i).setBillTypeIdActual(idNew);
            }
            new OperationPaymentBN().update(ld.get(i));
        }
    }
}

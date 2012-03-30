/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.pilot.sbenterprise13.backup;

import java.util.Hashtable;
import java.util.List;
import org.openide.util.lookup.ServiceProvider;
import vn.com.hkt.pilot.backup.manager.api.IBackupModule;
import vn.com.hkt.pilot.entities.Enterprise;
import vn.com.hkt.pilot.subenterprise13.dao.SubEnterprise13BN;
import vn.com.hkt.pilot.subenterprise13.entity.SubEnterprise13;

/**
 *
 * @author BinLe
 */
@ServiceProvider(service = IBackupModule.class)
public class SB13Backup implements IBackupModule {

    private Hashtable<String, Hashtable<Integer, Integer>> hashTotal;

    @Override
    public void setHashId(Hashtable<String, Hashtable<Integer, Integer>> hashID) {
        this.hashTotal = hashID;
    }

    @Override
    public void backupAll() {
        backupEnterprise();
    }

    private void backupEnterprise() {
        Hashtable<Integer, Integer> he = hashTotal.get(Enterprise.class.getSimpleName());
        if (he == null) {
            return;
        }
        List<SubEnterprise13> ld = new SubEnterprise13BN().selectAll();
        for (int i = 0; i < ld.size(); i++) {

            if (he != null) {
                int idOld = ld.get(i).getEnterpriseIdActual();
                int idNew = he.get(idOld);
                ld.get(i).setEnterpriseIdActual(idNew);
            }

            new SubEnterprise13BN().update(ld.get(i));
        }
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.pilot.sbenterprise.backup;

import java.util.Hashtable;
import java.util.List;
import org.openide.util.lookup.ServiceProvider;
import vn.com.hkt.pilot.backup.manager.api.IBackupModule;
import vn.com.hkt.pilot.entities.Enterprise;
import vn.com.hkt.pilot.sbenterprise.entity.BusinessArea;
import vn.com.hkt.pilot.sbenterprise.entity.SubEnterprise;
import vn.com.hkt.pilot.subenterprise.dao.SubEnterpriseBN;

/**
 *
 * @author BinLe
 */
@ServiceProvider(service = IBackupModule.class)
public class SB11Backup implements IBackupModule {

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
        Hashtable<Integer, Integer> hastIdBusiness = hashTotal.get(BusinessArea.class.getSimpleName());
        if (he == null && hastIdBusiness == null) {
            return;
        }
        List<SubEnterprise> ld = new SubEnterpriseBN().selectAll();
        for (int i = 0; i < ld.size(); i++) {

            if (he != null) {
                int idOld = ld.get(i).getEnterpriseIdActual();
                int idNew = he.get(idOld);
                ld.get(i).setEnterpriseIdActual(idNew);
            }
            if (hastIdBusiness != null) {
                for (int j = 0; j < ld.get(i).getBusinessAreasIdActual().size(); j++) {
                    int idBusinessOld = ld.get(i).getBusinessAreasIdActual().get(j);
                    int idBusinessNew = hastIdBusiness.get(idBusinessOld);
                    ld.get(i).getBusinessAreasIdActual().set(j, idBusinessNew);
                }
            }
            new SubEnterpriseBN().update(ld.get(i));
        }
    }
}

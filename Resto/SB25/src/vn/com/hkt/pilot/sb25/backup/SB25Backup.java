/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.pilot.sb25.backup;

import java.util.Hashtable;
import java.util.List;
import org.openide.util.lookup.ServiceProvider;
import vn.com.hkt.pilot.backup.manager.api.IBackupModule;
import vn.com.hkt.pilot.entities.Enterprise;
import vn.com.hkt.pilot.entities.Mission;
import vn.com.hkt.pilot.entities.Person;
import vn.com.hkt.pilot.sb25.dao.SubPersonSb25BN;
import vn.com.hkt.pilot.sb25.dao.WorkingProessBN;
import vn.com.hkt.pilot.sb25.subpersonsb25.entity.LearningProcess;
import vn.com.hkt.pilot.sb25.subpersonsb25.entity.OtherProfile;
import vn.com.hkt.pilot.sb25.subpersonsb25.entity.Publication;
import vn.com.hkt.pilot.sb25.subpersonsb25.entity.SubPersonSB25;
import vn.com.hkt.pilot.sb25.subpersonsb25.entity.WorkingProcess;

/**
 *
 * @author BinLe
 */
@ServiceProvider(service = IBackupModule.class)
public class SB25Backup implements IBackupModule {

    private Hashtable<String, Hashtable<Integer, Integer>> hashTotal;

    @Override
    public void setHashId(Hashtable<String, Hashtable<Integer, Integer>> hashID) {
        this.hashTotal = hashID;
    }

    @Override
    public void backupAll() {
        backupSubPersonSB25();
        backupWorkingProcess();
    }

    private void backupSubPersonSB25() {
        Hashtable<Integer, Integer> hw = hashTotal.get(WorkingProcess.class.getSimpleName());
        Hashtable<Integer, Integer> hl = hashTotal.get(LearningProcess.class.getSimpleName());
        Hashtable<Integer, Integer> hp = hashTotal.get(Publication.class.getSimpleName());
        Hashtable<Integer, Integer> ho = hashTotal.get(OtherProfile.class.getSimpleName());
        Hashtable<Integer, Integer> hpe = hashTotal.get(Person.class.getSimpleName());
        if (hw == null && hl == null && hp == null && ho == null && hpe == null) {
            return;
        }
        List<SubPersonSB25> datas = new SubPersonSb25BN().selectAll();
        for (int i = 0; i < datas.size(); i++) {
            if (hpe != null) {
                int idOld = datas.get(i).getPersonIdActual();
                int idNew = hpe.get(idOld);
                datas.get(i).setPersonIdActual(idNew);
            }
            if (hw!=null && datas.get(i).getWorkingProccessIdActual() != null) {
                for (int j = 0; j < datas.get(i).getWorkingProccessIdActual().size(); j++) {
                    int idCityOld = datas.get(i).getWorkingProccessIdActual().get(j);
                    int idCityNew = hw.get(idCityOld);
                    datas.get(i).getWorkingProccessIdActual().set(j, idCityNew);
                }
            }
            if (hl!=null && datas.get(i).getLearningProccessIdActual() != null) {
                for (int j = 0; j < datas.get(i).getLearningProccessIdActual().size(); j++) {
                    int idPOld = datas.get(i).getLearningProccessIdActual().get(j);
                    int idPNew = hl.get(idPOld);
                    datas.get(i).getLearningProccessIdActual().set(j, idPNew);
                }
            }if (hp!=null && datas.get(i).getPublicationIdActual() != null) {
                for (int j = 0; j < datas.get(i).getPublicationIdActual().size(); j++) {
                    int idCityOld = datas.get(i).getPublicationIdActual().get(j);
                    int idCityNew = hp.get(idCityOld);
                    datas.get(i).getPublicationIdActual().set(j, idCityNew);
                }
            }
            if (ho!=null && datas.get(i).getOtherProfileIdActual() != null) {
                for (int j = 0; j < datas.get(i).getOtherProfileIdActual().size(); j++) {
                    int idPOld = datas.get(i).getOtherProfileIdActual().get(j);
                    int idPNew = ho.get(idPOld);
                    datas.get(i).getOtherProfileIdActual().set(j, idPNew);
                }
            }
            new SubPersonSb25BN().update(datas.get(i));
        }
    }

    private void backupWorkingProcess() {
        Hashtable<Integer, Integer> he = hashTotal.get(Enterprise.class.getSimpleName());
        Hashtable<Integer, Integer> hm = hashTotal.get(Mission.class.getSimpleName());
        if (he == null && hm == null) {
            return;
        }
        List<WorkingProcess> datas = new WorkingProessBN().selectAll();
        for (int i = 0; i < datas.size(); i++) {
            if (he != null) {
                int idOld = datas.get(i).getEnterpriseIdActual();
                int idNew = he.get(idOld);
                datas.get(i).setEnterpriseIdActual(idNew);
            }
            if (hm != null) {
                int idOld = datas.get(i).getMissionIdActual();
                int idNew = hm.get(idOld);
                datas.get(i).setMissionIdActual(idNew);
            }
            new WorkingProessBN().update(datas.get(i));
        }
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.pilot.sb31.backup;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import org.openide.util.lookup.ServiceProvider;
import vn.com.hkt.pilot.backup.manager.api.IBackupModule;
import vn.com.hkt.pilot.entities.Department;
import vn.com.hkt.pilot.entities.Mission;
import vn.com.hkt.pilot.entities.Person;
import vn.com.hkt.pilot.sb31.deparment.extW41.entity.DepartmentExtSB31_W43;
import vn.com.hkt.pilot.sb31.deparment.extW41.entity.DepartmentExt_W41;
import vn.com.hkt.pilot.sb31.deparment.extW41.entity.Information;
import vn.com.hkt.pilot.sb31.deparment.extW41.entity.ProjectStatus;
import vn.com.hkt.pilot.sb31.department.extW41.dao.DepartmentExtSB31W43_DAO;
import vn.com.hkt.pilot.sb31.department.extW41.dao.DepartmentExtW41_DAO;
import vn.com.hkt.pilot.sb31.department.extW41.dao.InformationBN;

/**
 *
 * @author VietAnh
 */
@ServiceProvider(service = IBackupModule.class)
public class SB31Backup implements IBackupModule {

    private Hashtable<String, Hashtable<Integer, Integer>> hashTotal;
    DepartmentExtSB31W43_DAO daoSB43 = new DepartmentExtSB31W43_DAO();
    DepartmentExtW41_DAO daoW41 = new DepartmentExtW41_DAO();
    InformationBN informationBN = new InformationBN();

    @Override
    public void setHashId(Hashtable<String, Hashtable<Integer, Integer>> hashID) {
        this.hashTotal = hashID;
    }

    @Override
    public void backupAll() {
        backupDepartmentSB31();
        backupDepartmentSB41();
        backupInformation();

    }

    private void backupDepartmentSB31() {
        Hashtable<Integer, Integer> hd = hashTotal.get(Department.class.getSimpleName());
        Hashtable<Integer, Integer> hp = hashTotal.get(Person.class.getSimpleName());
        Hashtable<Integer, Integer> hm = hashTotal.get(Mission.class.getSimpleName());
        if (hd == null && hp == null && hm == null) {
            return;
        }
        List<DepartmentExtSB31_W43> ld = daoSB43.selectAll();
        for (int i = 0; i < ld.size(); i++) {
            if (hd != null) {
                int idOld = ld.get(i).getDepartmentIdActual();
                int idNew = hd.get(idOld);
                ld.get(i).setDepartmentIdActual(idNew);
            }
            if (hp != null) {
                List<Integer> lstIdPerson = ld.get(i).getIdPersonActual();
                List<Integer> lstIdPersonNew = new ArrayList<Integer>();
                if (!lstIdPerson.isEmpty()) {
                    for (int j = 0; i < lstIdPerson.size(); j++) {
                        int idOld = lstIdPerson.get(j);
                        int idNew = hp.get(idOld);
                        lstIdPersonNew.add(idNew);
                    }
                }
                ld.get(i).setIdPersonActual(lstIdPersonNew);
            }
            if (hp != null) {
                List<Integer> lstIdMisson = ld.get(i).getIdMissionIdActual();
                List<Integer> lstIdMissionNew = new ArrayList<Integer>();
                if (!lstIdMisson.isEmpty()) {
                    for (int j = 0; i < lstIdMisson.size(); j++) {
                        int idOld = lstIdMisson.get(j);
                        int idNew = hm.get(idOld);
                        lstIdMissionNew.add(idNew);
                    }
                }
                ld.get(i).setIdMissionIdActual(lstIdMissionNew);
            }
            daoSB43.update(ld.get(i));
        }
    }

    private void backupDepartmentSB41() {
        Hashtable<Integer, Integer> hd = hashTotal.get(Department.class.getSimpleName());
        Hashtable<Integer, Integer> hs = hashTotal.get(ProjectStatus.class.getSimpleName());
        if (hd == null && hs == null) {
            return;
        }
        List<DepartmentExt_W41> ld = daoW41.selectAll();
        for (int i = 0; i < ld.size(); i++) {
            if (hd != null) {
                int idOld = ld.get(i).getDepartmentIdActual();
                int idNew = hd.get(idOld);
                ld.get(i).setDepartmentIdActual(idNew);
            }
            if (hs != null) {
                int idOld = ld.get(i).getStateDepartmentIdActual();
                int idNew = hs.get(idOld);
                ld.get(i).setDepartmentIdActual(idNew);
            }
            daoW41.update(ld.get(i));
        }
    }

    private void backupInformation() {
        Hashtable<Integer, Integer> hd = hashTotal.get(Department.class.getSimpleName());
        if (hd == null ) {
            return;
        }
        List<Information> ld = informationBN.selectAll();
        for (int i = 0; i < ld.size(); i++) {
            if (hd != null) {
                int idOld = ld.get(i).getDepartmentIdActual();
                int idNew = hd.get(idOld);
                ld.get(i).setDepartmentIdActual(idNew);
            }
            informationBN.update(ld.get(i));
        }
    }
}

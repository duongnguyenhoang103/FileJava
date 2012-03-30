/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.pilot.sb32.backup;

import java.util.Hashtable;
import java.util.List;
import org.openide.util.lookup.ServiceProvider;
import vn.com.hkt.pilot.backup.manager.api.IBackupModule;
import vn.com.hkt.pilot.entities.Department;
import vn.com.hkt.pilot.sb32.deparment.extW43.entity.DepartmentExt_W43;
import vn.com.hkt.pilot.sb32.deparment.extW43.entity.Information;
import vn.com.hkt.pilot.sb32.department.extW43.dao.DepartmentExtW43_DAO;
import vn.com.hkt.pilot.sb32.department.extW43.dao.InformationBN;

/**
 *
 * @author VietAnh
 */
@ServiceProvider(service = IBackupModule.class)
public class SB32Backup implements IBackupModule {
    private Hashtable<String, Hashtable<Integer, Integer>> hashTotal;
    DepartmentExtW43_DAO dao = new DepartmentExtW43_DAO();
    InformationBN informationBN = new InformationBN();

    @Override
    public void setHashId(Hashtable<String, Hashtable<Integer, Integer>> hashID) {
        this.hashTotal = hashID;
    }

    @Override
    public void backupAll() {
        backupDepartmentW43();
        backupInformation();

    }

    private void backupDepartmentW43() {
        Hashtable<Integer, Integer> hd = hashTotal.get(Department.class.getSimpleName());
        if (hd == null) {
            return;
        }
        List<DepartmentExt_W43> ld = dao.selectAll();
        for (int i = 0; i < ld.size(); i++) {
            if (hd != null) {
                int idOld = ld.get(i).getDepartmentIdActual();
                int idNew = hd.get(idOld);
                ld.get(i).setDepartmentIdActual(idNew);
            }
            dao.update(ld.get(i));
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

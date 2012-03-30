/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.pilot.subperson.backup;

import java.util.Hashtable;
import java.util.List;
import org.openide.util.lookup.ServiceProvider;
import vn.com.hkt.pilot.backup.manager.api.IBackupModule;
import vn.com.hkt.pilot.entities.City;
import vn.com.hkt.pilot.entities.Person;
import vn.com.hkt.pilot.subperson.dao.SubPersonBN;
import vn.com.hkt.pilot.subperson.entity.SubPerson;

/**
 *
 * @author BinLe
 */
@ServiceProvider(service = IBackupModule.class)
public class SB21Backup implements IBackupModule {

    private Hashtable<String, Hashtable<Integer, Integer>> hashTotal;

    @Override
    public void setHashId(Hashtable<String, Hashtable<Integer, Integer>> hashID) {
        this.hashTotal = hashID;
    }

    @Override
    public void backupAll() {
        backupDepartment();
    }

    private void backupDepartment() {
        Hashtable<Integer, Integer> he = hashTotal.get(Person.class.getSimpleName());
        Hashtable<Integer, Integer> hastCity = hashTotal.get(City.class.getSimpleName());
        Hashtable<Integer, Integer> hastCountry = hashTotal.get(City.class.getSimpleName());
        if (he == null && hastCity == null && hastCountry == null) {
            return;
        }
        List<SubPerson> ld = new SubPersonBN().selectAll();
        for (int i = 0; i < ld.size(); i++) {

            if (he != null) {
                int idOld = ld.get(i).getPersonIdActual();
                int idNew = he.get(idOld);
                ld.get(i).setPersonIdActual(idNew);
            }
            if (hastCity != null) {
                int idOld = ld.get(i).getCityIdActual();
                int idNew = hastCity.get(idOld);
                ld.get(i).setCityIdActual(idNew);
            }
            if (hastCountry != null) {
                int idOld = ld.get(i).getCountryIdActual();
                int idNew = hastCountry.get(idOld);
                ld.get(i).setCountryIdActual(idNew);
            }

            new SubPersonBN().update(ld.get(i));
        }
    }
}

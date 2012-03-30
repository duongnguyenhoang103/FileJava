/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.pilot.sb22.backup;

import java.util.Hashtable;
import java.util.List;
import org.openide.util.lookup.ServiceProvider;
import vn.com.hkt.pilot.backup.manager.api.IBackupModule;
import vn.com.hkt.pilot.entities.City;
import vn.com.hkt.pilot.entities.Country;
import vn.com.hkt.pilot.entities.Person;
import vn.com.hkt.pilot.sb22.person.extW23.dao.AddressInfoBN;
import vn.com.hkt.pilot.sb22.person.extW23.dao.SubPersonSB22BN;
import vn.com.hkt.pilot.sb22.person.extW23.entity.AddressInfo;
import vn.com.hkt.pilot.sb22.person.extW23.entity.SubPersonSB22;

/**
 *
 * @author BinLe
 */
@ServiceProvider(service = IBackupModule.class)
public class SB22Backup implements IBackupModule {

    private Hashtable<String, Hashtable<Integer, Integer>> hashTotal;

    @Override
    public void setHashId(Hashtable<String, Hashtable<Integer, Integer>> hashID) {
        this.hashTotal = hashID;
    }

    @Override
    public void backupAll() {
        backupEnterprise();
        backupAddress();
    }

    private void backupEnterprise() {
        Hashtable<Integer, Integer> he = hashTotal.get(Person.class.getSimpleName());
        Hashtable<Integer, Integer> hastIdAddress = hashTotal.get(AddressInfo.class.getSimpleName());
        if (he == null && hastIdAddress == null) {
            return;
        }
        List<SubPersonSB22> ld = new SubPersonSB22BN().selectAll();
        for (int i = 0; i < ld.size(); i++) {

            if (he != null) {
                int idOld = ld.get(i).getPersonIdActual();
                int idNew = he.get(idOld);
                ld.get(i).setPersonIdActual(idNew);
            }
            if (hastIdAddress != null) {
                for (int j = 0; j < ld.get(i).getAddressIdActual().size(); j++) {
                    int idBusinessOld = ld.get(i).getAddressIdActual().get(j);
                    int idBusinessNew = hastIdAddress.get(idBusinessOld);
                    ld.get(i).getAddressIdActual().set(j, idBusinessNew);
                }
            }

            new SubPersonSB22BN().update(ld.get(i));
        }
    }

    private void backupAddress() {
        Hashtable<Integer, Integer> hCountry = hashTotal.get(Country.class.getSimpleName());
        Hashtable<Integer, Integer> hCity = hashTotal.get(City.class.getSimpleName());
        if (hCountry == null && hCity == null) {
            return;
        }
        List<AddressInfo> ld = new AddressInfoBN().selectAll();
        for (int i = 0; i < ld.size(); i++) {

            if (hCountry != null) {
                int idOld = ld.get(i).getCountryIdActual();
                int idNew = hCountry.get(idOld);
                ld.get(i).setCountryIdActual(idNew);
            }
           
            if (hCity != null) {
                int idOld = ld.get(i).getCityIdActual();
                int idNew = hCity.get(idOld);
                ld.get(i).setCityIdActual(idNew);
            }

            new AddressInfoBN().update(ld.get(i));
        }
    }
}

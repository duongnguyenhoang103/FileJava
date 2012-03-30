/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.pilot.extension.backup;

import java.util.Hashtable;
import java.util.List;
import org.openide.util.lookup.ServiceProvider;
import vn.com.hkt.enterprise.ext.dao.AddressInfoEnterpriseBN;
import vn.com.hkt.enterprise.ext.dao.EnterpriseExtBN;
import vn.com.hkt.enterprise.ext.entity.AddressInfoEnterprise;
import vn.com.hkt.enterprise.ext.entity.EnterpriseExt;
import vn.com.hkt.enterprise.ext.entity.ExecutiveOffice;
import vn.com.hkt.pilot.backup.manager.api.IBackupModule;
import vn.com.hkt.pilot.entities.City;
import vn.com.hkt.pilot.entities.Country;
import vn.com.hkt.pilot.entities.Enterprise;

/**
 *
 * @author BinLe
 */
@ServiceProvider(service = IBackupModule.class)
public class SB12Backup implements IBackupModule {

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
        Hashtable<Integer, Integer> he = hashTotal.get(Enterprise.class.getSimpleName());
        Hashtable<Integer, Integer> hastIdMisson = hashTotal.get(ExecutiveOffice.class.getSimpleName());
        Hashtable<Integer, Integer> hastIdAdd = hashTotal.get(AddressInfoEnterprise.class.getSimpleName());
        if (he == null && hastIdMisson == null && hastIdAdd == null) {
            return;
        }
        List<EnterpriseExt> ld = new EnterpriseExtBN().selectAll();
        for (int i = 0; i < ld.size(); i++) {
            if (he != null) {
                int idOld = ld.get(i).getEnterpriseIdActual();
                int idNew = he.get(idOld);
                ld.get(i).setEnterpriseIdActual(idNew);
            }
            if (hastIdMisson != null) {
                for (int j = 0; j < ld.get(i).getExecutiveOfficesIdActual().size(); j++) {
                    int idBusinessOld = ld.get(i).getExecutiveOfficesIdActual().get(j);
                    int idBusinessNew = hastIdMisson.get(idBusinessOld);
                    ld.get(i).getExecutiveOfficesIdActual().set(j, idBusinessNew);
                }
            }
            if (hastIdAdd != null) {
                for (int j = 0; j < ld.get(i).getEnterpriseAddressIdActual().size(); j++) {
                    int idBusinessOld = ld.get(i).getEnterpriseAddressIdActual().get(j);
                    int idBusinessNew = hastIdAdd.get(idBusinessOld);
                    ld.get(i).getEnterpriseAddressIdActual().set(j, idBusinessNew);
                }
            }

            new EnterpriseExtBN().update(ld.get(i));
        }
    }
    private void backupAddress() {
        Hashtable<Integer, Integer> hCountry = hashTotal.get(Country.class.getSimpleName());
        Hashtable<Integer, Integer> hCity = hashTotal.get(City.class.getSimpleName());
        if (hCountry == null && hCity == null) {
            return;
        }
        List<AddressInfoEnterprise> ld = new AddressInfoEnterpriseBN().selectAll();
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

            new AddressInfoEnterpriseBN().update(ld.get(i));
        }
    }
}

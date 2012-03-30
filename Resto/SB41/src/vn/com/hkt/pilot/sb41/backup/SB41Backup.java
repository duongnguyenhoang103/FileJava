/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.pilot.sb41.backup;

import java.util.Hashtable;
import java.util.List;
import org.openide.util.lookup.ServiceProvider;
import vn.com.hkt.pilot.backup.manager.api.IBackupModule;
import vn.com.hkt.pilot.entities.City;
import vn.com.hkt.pilot.entities.Country;
import vn.com.hkt.pilot.entities.Enterprise;
import vn.com.hkt.pilot.entities.Person;
import vn.com.hkt.pilot.entities.Product;
import vn.com.hkt.pilot.sb41.product.extW48.dao.ProductExtW48_BN;
import vn.com.hkt.pilot.sb41.product.extW48.dao.ProductExt_W47BN;
import vn.com.hkt.pilot.sb41.product.extW48.dao.ProductExt_W49BN;
import vn.com.hkt.pilot.sb41.product.extW48.entity.ProductExt_W47;
import vn.com.hkt.pilot.sb41.product.extW48.entity.ProductExt_W48;
import vn.com.hkt.pilot.sb41.product.extW48.entity.ProductExt_W49;

/**
 *
 * @author BinLe
 */
@ServiceProvider(service = IBackupModule.class)
public class SB41Backup implements IBackupModule {

    private Hashtable<String, Hashtable<Integer, Integer>> hashTotal;

    @Override
    public void setHashId(Hashtable<String, Hashtable<Integer, Integer>> hashID) {
        this.hashTotal = hashID;
    }

    @Override
    public void backupAll() {
        backupProductExt_W47();
        backupProductExt_W48();
        backupProductExt_W49();
    }

    private void backupProductExt_W47() {
        Hashtable<Integer, Integer> he = hashTotal.get(Enterprise.class.getSimpleName());
        Hashtable<Integer, Integer> hpe = hashTotal.get(Person.class.getSimpleName());
        Hashtable<Integer, Integer> hpr = hashTotal.get(Product.class.getSimpleName());
        if (he == null && hpe == null && hpr == null) {
            return;
        }
        List<ProductExt_W47> datas = new ProductExt_W47BN().selectAll();
        for (int i = 0; i < datas.size(); i++) {
            if (he != null) {
                int idOld = datas.get(i).getEnterpriseIdActual();
                int idNew = he.get(idOld);
                datas.get(i).setEnterpriseIdActual(idNew);
            }
            if (hpe != null) {
                int idOld = datas.get(i).getPersonIdActual();
                int idNew = hpe.get(idOld);
                datas.get(i).setPersonIdActual(idNew);
            }
            if (hpr != null) {
                int idOld = datas.get(i).getProductIdActual();
                int idNew = hpr.get(idOld);
                datas.get(i).setProductIdActual(idNew);
            }
            new ProductExt_W47BN().update(datas.get(i));
        }
    }

    private void backupProductExt_W48() {        
        Hashtable<Integer, Integer> hpr = hashTotal.get(Product.class.getSimpleName());
        Hashtable<Integer, Integer> hco = hashTotal.get(Country.class.getSimpleName());
        Hashtable<Integer, Integer> hci = hashTotal.get(City.class.getSimpleName());      
        if (hpr == null && hco == null && hci == null) {
            return;
        }
        List<ProductExt_W48> datas = new ProductExtW48_BN().selectAll();
        for (int i = 0; i < datas.size(); i++) {
            if (hpr != null) {
                int idOld = datas.get(i).getProductIdActual();
                int idNew = hpr.get(idOld);
                datas.get(i).setProductIdActual(idNew);
            }
            if (hco != null) {
                int idOld = datas.get(i).getCountryIdActual();
                int idNew = hco.get(idOld);
                datas.get(i).setCountryIdActual(idNew);
            }
            if (hci != null) {
                int idOld = datas.get(i).getCityIdActual();
                int idNew = hci.get(idOld);
                datas.get(i).setCityIdActual(idNew);
            }
            new ProductExtW48_BN().update(datas.get(i));
        }
    }

    private void backupProductExt_W49() {
        Hashtable<Integer, Integer> hpr = hashTotal.get(Product.class.getSimpleName());      ;      
        if (hpr == null) {
            return;
        }
        List<ProductExt_W49> datas = new ProductExt_W49BN().selectAll();
        for (int i = 0; i < datas.size(); i++) {
            if (hpr != null) {
                int idOld = datas.get(i).getProductIdActual();
                int idNew = hpr.get(idOld);
                datas.get(i).setProductIdActual(idNew);
            }        
            new ProductExt_W49BN().update(datas.get(i));
        }
    }
}

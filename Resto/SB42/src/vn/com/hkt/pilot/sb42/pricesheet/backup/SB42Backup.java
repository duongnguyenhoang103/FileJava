/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.pilot.sb42.pricesheet.backup;

import java.util.Hashtable;
import java.util.List;
import org.openide.util.lookup.ServiceProvider;
import vn.com.hkt.pilot.backup.manager.api.IBackupModule;
import vn.com.hkt.pilot.entities.Classification;
import vn.com.hkt.pilot.entities.Product;
import vn.com.hkt.pilot.sb42.pricesheet.dao.PriceSheetBN;
import vn.com.hkt.pilot.sb42.pricesheet.dao.ProductIncludeBN;
import vn.com.hkt.pilot.sb42.pricesheet.dao.SupplementaryProductBN;
import vn.com.hkt.pilot.sb42.pricesheeta.entity.PriceSheet;
import vn.com.hkt.pilot.sb42.pricesheeta.entity.ProductInclude;
import vn.com.hkt.pilot.sb42.pricesheeta.entity.SupplementaryProduct;

/**
 *
 * @author BinLe
 */
@ServiceProvider(service = IBackupModule.class)
public class SB42Backup implements IBackupModule {

    private Hashtable<String, Hashtable<Integer, Integer>> hashTotal;

    @Override
    public void setHashId(Hashtable<String, Hashtable<Integer, Integer>> hashID) {
        this.hashTotal = hashID;
    }

    @Override
    public void backupAll() {
        backupPriceSheet();
        backupProductInclude();
        backupSupplementaryProduct();
    }

    private void backupPriceSheet() {
        Hashtable<Integer, Integer> hp = hashTotal.get(Product.class.getSimpleName());
        Hashtable<Integer, Integer> hc = hashTotal.get(Classification.class.getSimpleName());
        if (hp == null && hc == null) {
            return;
        }
        List<PriceSheet> datas = new PriceSheetBN().selectAll();
        for (int i = 0; i < datas.size(); i++) {
            if (hp != null) {
                int idOld = datas.get(i).getProductIdActual();
                int idNew = hp.get(idOld);
                datas.get(i).setProductIdActual(idNew);
            }
            if (hc != null) {
                int idOld = datas.get(i).getIdClassification();
                int idNew = hc.get(idOld);
                datas.get(i).setIdClassification(idNew);
            }
            new PriceSheetBN().update(datas.get(i));
        }
    }

    private void backupProductInclude() {
        Hashtable<Integer, Integer> hp = hashTotal.get(Product.class.getSimpleName());
        if (hp == null) {
            return;
        }
        List<ProductInclude> datas = new ProductIncludeBN().selectAll();
        for (int i = 0; i < datas.size(); i++) {
            if (hp != null) {
                int idOld = datas.get(i).getProductIdActual();
                int idNew = hp.get(idOld);
                datas.get(i).setProductIdActual(idNew);
            }            
            new ProductIncludeBN().update(datas.get(i));
        }
    }

    private void backupSupplementaryProduct() {
        Hashtable<Integer, Integer> hp = hashTotal.get(Product.class.getSimpleName());
        if (hp == null) {
            return;
        }
        List<SupplementaryProduct> datas = new SupplementaryProductBN().selectAll();
        for (int i = 0; i < datas.size(); i++) {
            if (hp != null) {
                int idOld = datas.get(i).getProductIdActual();
                int idNew = hp.get(idOld);
                datas.get(i).setProductIdActual(idNew);
            }            
            new SupplementaryProductBN().update(datas.get(i));
        }
    }
}

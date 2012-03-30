/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.pilot.sb43.backup;

import java.util.Hashtable;
import java.util.List;
import org.openide.util.lookup.ServiceProvider;
import vn.com.hkt.pilot.backup.manager.api.IBackupModule;
import vn.com.hkt.pilot.entities.Product;
import vn.com.hkt.pilot.sb43.entity.ProductExt_W52;
import vn.com.hkt.pilot.sb43.product.extW48.dao.ProductExt_W52BN;

/**
 *
 * @author BinLe
 */
@ServiceProvider(service = IBackupModule.class)
public class SB43Backup implements IBackupModule {

    private Hashtable<String, Hashtable<Integer, Integer>> hashTotal;

    @Override
    public void setHashId(Hashtable<String, Hashtable<Integer, Integer>> hashID) {
        this.hashTotal = hashID;
    }

    @Override
    public void backupAll() {
        Hashtable<Integer, Integer> hp = hashTotal.get(Product.class.getSimpleName());
        if(hp==null) return;
        List<ProductExt_W52> datas= new ProductExt_W52BN().selectAll();
        for(int i=0;i<datas.size();i++){
            if(hp!=null){
                int idOld=datas.get(i).getProductIdActual();
                int idNew=hp.get(idOld);
                datas.get(i).setProductIdActual(idNew);
            }
            new ProductExt_W52BN().update(datas.get(i));
        }
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.pilot.promotion.backup;

import java.util.Hashtable;
import java.util.List;
import org.openide.util.lookup.ServiceProvider;
import vn.com.hkt.pilot.backup.manager.api.IBackupModule;
import vn.com.hkt.pilot.entities.Classification;
import vn.com.hkt.pilot.entities.Department;
import vn.com.hkt.pilot.entities.Enterprise;
import vn.com.hkt.pilot.entities.Person;
import vn.com.hkt.pilot.entities.Product;
import vn.com.hkt.pilot.promotion.dao.ClassifiedByDepartmentBN;
import vn.com.hkt.pilot.promotion.dao.ClassifiedByEnterpriseBN;
import vn.com.hkt.pilot.promotion.dao.ClassifiedByOperationBN;
import vn.com.hkt.pilot.promotion.dao.ClassifiedByPersonBN;
import vn.com.hkt.pilot.promotion.dao.ClassifiedByProductBN;
import vn.com.hkt.pilot.promotion.dao.PromotionBN;
import vn.com.hkt.pilot.promotion.dao.SaleOffBN;
import vn.com.hkt.pilot.promotion.dao.SaleOffProductBN;
import vn.com.hkt.pilot.promotion.entity.ClassifiedByDepartment;
import vn.com.hkt.pilot.promotion.entity.ClassifiedByEnterprise;
import vn.com.hkt.pilot.promotion.entity.ClassifiedByOperation;
import vn.com.hkt.pilot.promotion.entity.ClassifiedByPerson;
import vn.com.hkt.pilot.promotion.entity.ClassifiedByProduct;
import vn.com.hkt.pilot.promotion.entity.Promotion;
import vn.com.hkt.pilot.promotion.entity.SaleOff;
import vn.com.hkt.pilot.promotion.entity.SaleOffOption;
import vn.com.hkt.pilot.promotion.entity.SaleOffProduct;

/**
 *
 * @author BinLe
 */
@ServiceProvider(service = IBackupModule.class)
public class SB45Backup implements IBackupModule {

    private Hashtable<String, Hashtable<Integer, Integer>> hashTotal;

    @Override
    public void setHashId(Hashtable<String, Hashtable<Integer, Integer>> hashID) {
        this.hashTotal = hashID;
    }

    @Override
    public void backupAll() {
        backupClassifiedByDepartment();
        backupClassifiedByEnterprise();
        backupClassifiedByOperation();
        backupClassifiedByPerson();
        backupClassifiedByProduct();
        backupPromotion();
        backupSalfOff();
        backupSaleOffProduct();

    }

    private void backupClassifiedByDepartment() {
        Hashtable<Integer, Integer> hp = hashTotal.get(Promotion.class.getSimpleName());
        Hashtable<Integer, Integer> hd = hashTotal.get(Department.class.getSimpleName());
        if (hp == null && hd == null) {
            return;
        }
        List<ClassifiedByDepartment> datas = new ClassifiedByDepartmentBN().selectAll();
        for (int i = 0; i < datas.size(); i++) {
            if (hp != null) {
                int idOld = datas.get(i).getPromotionIdActual();
                int idNew = hp.get(idOld);
                datas.get(i).setPromotionIdActual(idNew);
            }
            if (hd != null) {
                for (int j = 0; j < datas.get(i).getDepartmentIdActuals().size(); j++) {
                    int idOld = datas.get(i).getDepartmentIdActuals().get(j);
                    int idNew = hd.get(idOld);
                    datas.get(i).getDepartmentIdActuals().set(j, idNew);
                }
            }
            new ClassifiedByDepartmentBN().update(datas.get(i));
        }
    }

    private void backupClassifiedByEnterprise() {
        Hashtable<Integer, Integer> hp = hashTotal.get(Promotion.class.getSimpleName());
        Hashtable<Integer, Integer> he = hashTotal.get(Enterprise.class.getSimpleName());
        if (hp == null && he == null) {
            return;
        }
        List<ClassifiedByEnterprise> datas = new ClassifiedByEnterpriseBN().selectAll();
        for (int i = 0; i < datas.size(); i++) {
            if (hp != null) {
                int idOld = datas.get(i).getPromotionIdActual();
                int idNew = hp.get(idOld);
                datas.get(i).setPromotionIdActual(idNew);
            }
            if (he != null) {
                for (int j = 0; j < datas.get(i).getEnterpriseIdActuals().size(); j++) {
                    int idOld = datas.get(i).getEnterpriseIdActuals().get(j);
                    int idNew = he.get(idOld);
                    datas.get(i).getEnterpriseIdActuals().set(j, idNew);
                }
            }
            new ClassifiedByEnterpriseBN().update(datas.get(i));
        }
    }

    private void backupClassifiedByOperation() {
        Hashtable<Integer, Integer> hp = hashTotal.get(Promotion.class.getSimpleName());
        if (hp == null) {
            return;
        }
        List<ClassifiedByOperation> datas = new ClassifiedByOperationBN().selectAll();
        for (int i = 0; i < datas.size(); i++) {
            if (hp != null) {
                int idOld = datas.get(i).getPromotionIdActual();
                int idNew = hp.get(idOld);
                datas.get(i).setPromotionIdActual(idNew);
            }
            new ClassifiedByOperationBN().update(datas.get(i));
        }
    }

    private void backupClassifiedByPerson() {
        Hashtable<Integer, Integer> hp = hashTotal.get(Promotion.class.getSimpleName());
        Hashtable<Integer, Integer> hpe = hashTotal.get(Person.class.getSimpleName());
        if (hpe == null && hpe == null) {
            return;
        }
        List<ClassifiedByPerson> datas = new ClassifiedByPersonBN().selectAll();
        for (int i = 0; i < datas.size(); i++) {
            if (hp != null) {
                int idOld = datas.get(i).getPromotionIdActual();
                int idNew = hp.get(idOld);
                datas.get(i).setPromotionIdActual(idNew);
            }
            if (hpe != null) {
                for (int j = 0; j < datas.get(i).getPersonIdActuals().size(); j++) {
                    int idOld = datas.get(i).getPersonIdActuals().get(j);
                    int idNew = hpe.get(idOld);
                    datas.get(i).getPersonIdActuals().set(j, idNew);
                }
            }
            new ClassifiedByPersonBN().update(datas.get(i));
        }
    }

    private void backupClassifiedByProduct() {
        Hashtable<Integer, Integer> hp = hashTotal.get(Promotion.class.getSimpleName());
        Hashtable<Integer, Integer> hpr = hashTotal.get(Product.class.getSimpleName());
        if (hpr == null && hpr == null) {
            return;
        }
        List<ClassifiedByProduct> datas = new ClassifiedByProductBN().selectAll();
        for (int i = 0; i < datas.size(); i++) {
            if (hp != null) {
                int idOld = datas.get(i).getPromotionIdActual();
                int idNew = hp.get(idOld);
                datas.get(i).setPromotionIdActual(idNew);
            }
            if (hpr != null) {
                for (int j = 0; j < datas.get(i).getProductIdActuals().size(); j++) {
                    int idOld = datas.get(i).getProductIdActuals().get(j);
                    int idNew = hpr.get(idOld);
                    datas.get(i).getProductIdActuals().set(j, idNew);
                }
            }
            new ClassifiedByProductBN().update(datas.get(i));
        }
    }

    private void backupPromotion() {
        Hashtable<Integer, Integer> hc = hashTotal.get(Classification.class.getSimpleName());
        Hashtable<Integer, Integer> hso = hashTotal.get(SaleOffOption.class.getSimpleName());
        if (hc == null && hso == null) {
            return;
        }
        List<Promotion> datas = new PromotionBN().selectAll();
        for (int i = 0; i < datas.size(); i++) {
            if (hc != null) {
                int idOld = datas.get(i).getIdClassification();
                int idNew = hc.get(idOld);
                datas.get(i).setIdClassification(idNew);
            }
            if (hso != null) {
                int idOld = datas.get(i).getSaleOffOptionIdActual();
                int idNew = hso.get(idOld);
                datas.get(i).setSaleOffOptionIdActual(idNew);
            }
            new PromotionBN().update(datas.get(i));
        }
    }

    private void backupSalfOff() {
        Hashtable<Integer, Integer> hp = hashTotal.get(Promotion.class.getSimpleName());        
        if (hp == null ) {
            return;
        }
        List<SaleOff> datas = new SaleOffBN().selectAll();
        for (int i = 0; i < datas.size(); i++) {
            if (hp != null) {
                int idOld = datas.get(i).getPromotionIdActual();
                int idNew = hp.get(idOld);
                datas.get(i).setPromotionIdActual(idNew);
            }           
            new SaleOffBN().update(datas.get(i));
        }
    }

    private void backupSaleOffProduct() {
       Hashtable<Integer, Integer> hpr = hashTotal.get(Product.class.getSimpleName());        
        if (hpr == null ) {
            return;
        }
        List<SaleOffProduct> datas = new SaleOffProductBN().selectAll();
        for (int i = 0; i < datas.size(); i++) {
            if (hpr != null) {
                int idOld = datas.get(i).getPromotionIdActual();
                int idNew = hpr.get(idOld);
                datas.get(i).setPromotionIdActual(idNew);
            }           
            new SaleOffProductBN().update(datas.get(i));
        }
    }
}

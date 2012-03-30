/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.pilot.basic.backup;

import java.util.Hashtable;
import java.util.List;
import org.openide.util.lookup.ServiceProvider;
import vn.com.hkt.bom.operation.dao.OperationBN;
import vn.com.hkt.bom.operation.dao.OperationDetailBN;
import vn.com.hkt.bom.operation.dao.OperationPartnerBN;
import vn.com.hkt.bom.product.dao.ProductBN;
import vn.com.hkt.erm.department.dao.DepartmentBN;
import vn.com.hkt.erm.enterprise.dao.EnterpriseBN;
import vn.com.hkt.hrm.person.dao.ForeignLanguageBN;
import vn.com.hkt.hrm.person.dao.PersonBN;
import vn.com.hkt.pilot.backup.manager.api.IBackupModule;
import vn.com.hkt.pilot.dialog.dao.CityBN;
import vn.com.hkt.pilot.dialog.dao.CountryBN;
import vn.com.hkt.pilot.dialog.dao.PartitionBN;
import vn.com.hkt.pilot.entities.City;
import vn.com.hkt.pilot.entities.Classification;
import vn.com.hkt.pilot.entities.Country;
import vn.com.hkt.pilot.entities.Department;
import vn.com.hkt.pilot.entities.Enterprise;
import vn.com.hkt.pilot.entities.ForeignLanguage;
import vn.com.hkt.pilot.entities.Mission;
import vn.com.hkt.pilot.entities.Operation;
import vn.com.hkt.pilot.entities.OperationDetail;
import vn.com.hkt.pilot.entities.OperationPartner;
import vn.com.hkt.pilot.entities.Partition;
import vn.com.hkt.pilot.entities.Person;
import vn.com.hkt.pilot.entities.Product;
import vn.com.hkt.pilot.entities.UnitsOfMeasurement;

/**
 *
 * @author BinLe
 */
@ServiceProvider(service = IBackupModule.class)
public class BasicBackup implements IBackupModule {

    private Hashtable<String, Hashtable<Integer, Integer>> hashTotal;

    @Override
    public void setHashId(Hashtable<String, Hashtable<Integer, Integer>> hashID) {
        this.hashTotal = hashID;
    }

    @Override
    public void backupAll() {
        backupCity();
        backupCountry();
        backupDepartment();
        backupEnterprise();
        backupForeignLanguage();
        backupOperation();
        backupOperationDetail();
        backupOperationPartner();
        backupPartition();
        backupPerson();
        backupProduct();
    }

    private void backupCity() {
        Hashtable<Integer, Integer> hastIdCountry = hashTotal.get(Country.class.getSimpleName());
        Hashtable<Integer, Integer> hastIdPartition = hashTotal.get(Partition.class.getSimpleName());
        if (hastIdCountry == null && hastIdPartition == null) {
            return;
        }
        List<City> lc = new CityBN().selectAll();
        for (int i = 0; i < lc.size(); i++) {
            if (hastIdCountry != null) {
                int idCountry = lc.get(i).getCountryIdActual();
                int idCN = hastIdCountry.get(idCountry);
                lc.get(i).setCountryIdActual(idCN);
            }
            if (hastIdPartition != null) {
                int idPatition = lc.get(i).getPartitionIdActual();
                int idPN = hastIdPartition.get(idPatition);
                lc.get(i).setCountryIdActual(idPN);
            }
            new CityBN().update(lc.get(i));
        }
    }

    private void backupCountry() {
        Hashtable<Integer, Integer> hashCity = hashTotal.get(City.class.getSimpleName());
        Hashtable<Integer, Integer> hashPatiotion = hashTotal.get(Partition.class.getSimpleName());
        if (hashCity == null && hashPatiotion == null) {
            return;
        }
        List<Country> lc = new CountryBN().selectAll();
        for (int i = 0; i < lc.size(); i++) {
            if (lc.get(i).getCitysIdActual() != null) {
                for (int j = 0; j < lc.get(i).getCitysIdActual().size(); j++) {
                    int idCityOld = lc.get(i).getCitysIdActual().get(j);
                    int idCityNew = hashCity.get(idCityOld);
                    lc.get(i).getCitysIdActual().set(j, idCityNew);
                }
            }
            if (lc.get(i).getPartititionsIdActual() != null) {
                for (int j = 0; j < lc.get(i).getPartititionsIdActual().size(); j++) {
                    int idPOld = lc.get(i).getPartititionsIdActual().get(j);
                    int idPNew = hashCity.get(idPOld);
                    lc.get(i).getCitysIdActual().set(j, idPNew);
                }
            }
            new CountryBN().update(lc.get(i));
        }
    }

    private void backupDepartment() {
        Hashtable<Integer, Integer> hd = hashTotal.get(Department.class.getSimpleName());
        Hashtable<Integer, Integer> he = hashTotal.get(Enterprise.class.getSimpleName());
        Hashtable<Integer, Integer> hpe = hashTotal.get(Person.class.getSimpleName());
        Hashtable<Integer, Integer> hpr = hashTotal.get(Product.class.getSimpleName());
        if (hd == null && he == null && hpe == null && hpr == null) {
            return;
        }
        List<Department> ld = new DepartmentBN().selectAll();
        for (int i = 0; i < ld.size(); i++) {
            if (hd != null) {
                int idOld = ld.get(i).getDepartmentParentIdAcutal();
                int idNew = hd.get(idOld);
                ld.get(i).setDepartmentParentIdAcutal(idNew);
            }
            if (he != null) {
                int idOld = ld.get(i).getEnterpriseIdActual();
                int idNew = he.get(idOld);
                ld.get(i).setEnterpriseIdActual(idNew);
            }
            if (hpe != null) {
                int idOld = ld.get(i).getPersonIdActual();
                int idNew = hpe.get(idOld);
                ld.get(i).setPersonIdActual(idNew);
            }
            if (hpr != null) {
                int idOld = ld.get(i).getProductIdActual();
                int idNew = hpr.get(idOld);
                ld.get(i).setProductIdActual(idNew);
            }
            new DepartmentBN().update(ld.get(i));
        }
    }

    private void backupEnterprise() {
        Hashtable<Integer, Integer> he = hashTotal.get(Enterprise.class.getSimpleName());
        Hashtable<Integer, Integer> hp = hashTotal.get(Person.class.getSimpleName());
        if (he == null && hp == null) {
            return;
        }
        List<Enterprise> le = new EnterpriseBN().selectAll();
        for (int i = 0; i < le.size(); i++) {
            if (he != null) {
                int idOld = le.get(i).getEnterpriseParentIdActual();
                int idNew = he.get(idOld);
                le.get(i).setEnterpriseParentIdActual(idNew);
            }
            if (hp != null) {
                int idOld = le.get(i).getDirectorIdActual();
                int idNew = hp.get(idOld);
                le.get(i).setDirectorIdActual(idNew);
            }
            new EnterpriseBN().update(le.get(i));
        }
    }

    private void backupForeignLanguage() {
        Hashtable<Integer, Integer> hc = hashTotal.get(Country.class.getSimpleName());
        if (hc == null) {
            return;
        }
        List<ForeignLanguage> lf = new ForeignLanguageBN().selectAll();
        for (int i = 0; i < lf.size(); i++) {
            if (hc != null) {
                int idOld = lf.get(i).getCountryIdActual();
                int idNew = hc.get(idOld);
                lf.get(i).setCountryIdActual(idNew);
            }
            new ForeignLanguageBN().update(lf.get(i));
        }
    }

    private void backupOperation() {
        Hashtable<Integer, Integer> he = hashTotal.get(Enterprise.class.getSimpleName());
        Hashtable<Integer, Integer> hd = hashTotal.get(Department.class.getSimpleName());
        Hashtable<Integer, Integer> hpe = hashTotal.get(Person.class.getSimpleName());
        Hashtable<Integer, Integer> hcl = hashTotal.get(Classification.class.getSimpleName());
        Hashtable<Integer, Integer> hu = hashTotal.get(UnitsOfMeasurement.class.getSimpleName());
        if (he == null && hd == null && hpe == null && hcl == null && hu == null) {
            return;
        }
        List<Operation> lo = new OperationBN().selectAll();
        for (int i = 0; i < lo.size(); i++) {
            if (he != null) {
                int idOld = lo.get(i).getEnterpriseIdActual();
                int idNew = he.get(idOld);
                lo.get(i).setEnterpriseIdActual(idNew);
            }
            if (hd != null) {
                int idOld = lo.get(i).getDepartmentIdActual();
                int idNew = hd.get(idOld);
                lo.get(i).setDepartmentIdActual(idNew);
            }
            if (hpe != null) {
                int idOld = lo.get(i).getPersonIdActual();
                int idNew = hpe.get(idOld);
                lo.get(i).setPersonIdActual(idNew);
            }
            if (hcl != null) {
                int idOld = lo.get(i).getClassificationIdActual();
                int idNew = hcl.get(idOld);
                lo.get(i).setClassificationIdActual(idNew);
            }
            if (hu != null) {
                int idOld = lo.get(i).getUnitOfMeasureIdActual();
                int idNew = hu.get(idOld);
                lo.get(i).setUnitOfMeasureIdActual(idNew);
            }

        }



    }

    private void backupOperationDetail() {
        Hashtable<Integer, Integer> ho = hashTotal.get(Operation.class.getSimpleName());
        Hashtable<Integer, Integer> hpr = hashTotal.get(Product.class.getSimpleName());
        if (ho == null && hpr == null) {
            return;
        }
        List<OperationDetail> lod= new OperationDetailBN().selectAll();
        for (int i=0;i<lod.size();i++){
            if(ho!=null){
                int idOld= lod.get(i).getOperationIdActual();
                int idNew= ho.get(idOld);
                lod.get(i).setOperationIdActual(idNew);                
            }
            if(hpr!=null){
                int idOld= lod.get(i).getProductIdActual();
                int idNew= hpr.get(idOld);
                lod.get(i).setProductIdActual(idNew);                
            }
            new OperationDetailBN().update(lod.get(i));
        }
    }

    private void backupOperationPartner() {
        Hashtable<Integer, Integer> he = hashTotal.get(Enterprise.class.getSimpleName());
        Hashtable<Integer, Integer> hpe = hashTotal.get(Person.class.getSimpleName());
        Hashtable<Integer, Integer> ho = hashTotal.get(Operation.class.getSimpleName());
        if (he == null && hpe == null && ho==null) {
            return;
        }
        List<OperationPartner> lop= new OperationPartnerBN().selectAll();
        for (int i=0;i<lop.size();i++){
            if(he!=null){
                int idOld= lop.get(i).getEnterpriseIdActual();
                int idNew= ho.get(idOld);
                lop.get(i).setEnterpriseIdActual(idNew);                
            }
            if(hpe!=null){
                int idOld= lop.get(i).getPersonIdActual();
                int idNew= hpe.get(idOld);
                lop.get(i).setPersonIdActual(idNew);
            }
            if(ho!=null){
                int idOld= lop.get(i).getOperationIdActual();
                int idNew= ho.get(idOld);
                lop.get(i).setOperationIdActual(idNew);
            }
            new OperationPartnerBN().update(lop.get(i));
        }
        
    }

    private void backupPartition() {
        Hashtable<Integer, Integer> hp = hashTotal.get(Partition.class.getSimpleName());     
        Hashtable<Integer, Integer> hc = hashTotal.get(Country.class.getSimpleName());
        if (hp == null && hc == null) {
            return;
        }
        List<Partition> datas= new PartitionBN().selectAll();
        for (int i=0;i<datas.size();i++){
            if(hp!=null){
                int idOld= datas.get(i).getPartitionParentIdActual();
                int idNew= hp.get(idOld);
                datas.get(i).setPartitionParentIdActual(idNew);                
            }            
            if(hc!=null){
                int idOld= datas.get(i).getCountryIdActual();
                int idNew= hc.get(idOld);
                datas.get(i).setCountryIdActual(idNew);
            }
            new PartitionBN().update(datas.get(i));
        }
    }

    private void backupPerson() {        
        Hashtable<Integer, Integer> he = hashTotal.get(Enterprise.class.getSimpleName());     
        Hashtable<Integer, Integer> hd = hashTotal.get(Department.class.getSimpleName());
        Hashtable<Integer, Integer> hm = hashTotal.get(Mission.class.getSimpleName());     
        Hashtable<Integer, Integer> hc = hashTotal.get(Country.class.getSimpleName());
        if (he == null && hc == null && hm == null && hd == null) {
            return;
        }
        List<Person> datas= new PersonBN().selectAll();
        for (int i=0;i<datas.size();i++){
            if(he!=null){
                int idOld= datas.get(i).getEnterpriseIdActual();
                int idNew= he.get(idOld);
                datas.get(i).setEnterpriseIdActual(idNew);                
            }            
            if(hc!=null){
                int idOld= datas.get(i).getCountryIdActual();
                int idNew= hc.get(idOld);
                datas.get(i).setCountryIdActual(idNew);
            }if(hd!=null){
                int idOld= datas.get(i).getDepartmentIdActual();
                int idNew= hd.get(idOld);
                datas.get(i).setDepartmentIdActual(idNew);                
            }            
            if(hm!=null){
                int idOld= datas.get(i).getMissionIdActual();
                int idNew= hc.get(idOld);
                datas.get(i).setMissionIdActual(idNew);
            }
            new PersonBN().update(datas.get(i));
        }
    }

    private void backupProduct() {
        Hashtable<Integer, Integer> he = hashTotal.get(Enterprise.class.getSimpleName());     
        Hashtable<Integer, Integer> hd = hashTotal.get(Department.class.getSimpleName());
        Hashtable<Integer, Integer> hpe = hashTotal.get(Person.class.getSimpleName());     
        Hashtable<Integer, Integer> hpr = hashTotal.get(Product.class.getSimpleName());
        if (he == null && hpe == null && hpr == null && hd == null) {
            return;
        }
        List<Product> datas= new ProductBN().selectAll();
        for (int i=0;i<datas.size();i++){
            if(he!=null){
                int idOld= datas.get(i).getEnterpriseIdActual();
                int idNew= he.get(idOld);
                datas.get(i).setEnterpriseIdActual(idNew);                
            }            
            if(hpe!=null){
                int idOld= datas.get(i).getPersonDesignIdActual();
                int idNew= hpe.get(idOld);
                datas.get(i).setPersonDesignIdActual(idNew);
            }if(hd!=null){
                int idOld= datas.get(i).getDepartmentIdActual();
                int idNew= hd.get(idOld);
                datas.get(i).setDepartmentIdActual(idNew);                
            }            
            if(hpr!=null){
                int idOld= datas.get(i).getProductGroupIdActual();
                int idNew= hpr.get(idOld);
                datas.get(i).setProductGroupIdActual(idNew);
            }
            new ProductBN().update(datas.get(i));
        }
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.finance.spi;

import vn.com.hkt.finance.api.ICalculate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TemporalType;
import org.openide.util.Lookup;
import org.openide.util.lookup.ServiceProvider;
import vn.com.hkt.basic.api.IOperationBN;
import vn.com.hkt.basic.api.IOperationDetailBN;
import vn.com.hkt.basic.jpa.PersistenceUltility;
import vn.com.hkt.pilot.datetime.utils.DateTimeConverter;
import vn.com.hkt.pilot.dialog.dao.ClassificationBN;
import vn.com.hkt.pilot.entities.Classification;
import vn.com.hkt.pilot.entities.Department;
import vn.com.hkt.pilot.entities.Operation;
import vn.com.hkt.pilot.entities.Enterprise;
import vn.com.hkt.pilot.entities.OperationDetail;
import vn.com.hkt.pilot.entities.Product;

/**
 *
 * @author khanguct
 */
@ServiceProvider(service = ICalculate.class)
public class CalculateSPI implements ICalculate {

    private IOperationBN operationBN;
    private DateTimeConverter dateTimeConverter = new DateTimeConverter();
    private IOperationDetailBN operationDetailBN;

    public CalculateSPI() {
        this.operationBN = Lookup.getDefault().lookup(IOperationBN.class);
        operationDetailBN = Lookup.getDefault().lookup(IOperationDetailBN.class);
    }

///////////////////////////////////// Cac Ham Trung Gian (protected) /////////////////////////////////////////////
    /**
     * Nghiep vu tinh tong tien giao dich nao do
     * trong ngay, thang, nam hoac thang, nam hoac nam
     * @param date
     * @return 
     */
    protected int calculateOperationByDate(Enterprise enter, Calendar date, String classificationname) {

        EntityManager em = PersistenceUltility.getEMF().createEntityManager();
        String sql = "Select tbl from Operation tbl where tbl." + Operation.FIELD_DATE_TIME
                + " = :dateCondition";
        int S = 0;
        List<Operation> list = new ArrayList<Operation>();
        list = em.createQuery(sql).setParameter("dateCondition", date, TemporalType.DATE).getResultList();
        if (!list.isEmpty()) {
            for (Operation bean : list) {
                Classification cl = new ClassificationBN().getById(bean.getClassificationIdActual());
                if ((bean.getEnterpriseIdActual() == (enter.getId()))
                        && (cl.getClassificationName().equals(classificationname))) {
                    S += bean.getSumPrice();
                }
            }
        }
        return S;
    }

    protected int calculateOperationByMonthOrYear(int flag, Enterprise enter,
            Calendar date, String classificationname) {

        EntityManager em = PersistenceUltility.getEMF().createEntityManager();
        String sql = "";
        int S = 0;

        if (flag == 1) {
            sql = "Select tbl from Operation tbl where tbl." + Operation.FIELD_DATE_TIME
                    + " Between :dateStart And :dateEnd";
            Calendar start = Calendar.getInstance();
            date.set(date.get(Calendar.YEAR), date.get(Calendar.MONTH) + 1, 1);
            Calendar end = Calendar.getInstance();
            date.set(date.get(Calendar.YEAR), date.get(Calendar.MONTH) + 1, date.getActualMaximum(
                    Calendar.DAY_OF_MONTH));
            List<Operation> list = new ArrayList<Operation>();
            list = em.createQuery(sql).setParameter("dateStart", start, TemporalType.DATE).
                    setParameter("dateEnd", end, TemporalType.DATE).getResultList();
            if (!list.isEmpty()) {
                for (Operation bean : list) {
                    Classification cl = new ClassificationBN().getById(bean.getClassificationIdActual());
                    if ((bean.getEnterpriseIdActual() == (enter.getId()))
                            && (cl.getClassificationName().equals(classificationname))) {
                        S += bean.getSumPrice();
                    }
                }

            }
        } else {
            sql = "Select tbl from Operation tbl where tbl." + Operation.FIELD_DATE_TIME
                    + " Between :dateStart And :dateEnd";
            Calendar start = Calendar.getInstance();
            date.set(date.get(Calendar.YEAR), 1, 1);
            Calendar end = Calendar.getInstance();
            date.set(date.get(Calendar.YEAR), 12, 31);
            List<Operation> list = new ArrayList<Operation>();
            list = em.createQuery(sql).setParameter("dateStart", start, TemporalType.DATE).
                    setParameter("dateEnd", end, TemporalType.DATE).getResultList();
            if (!list.isEmpty()) {
                for (Operation bean : list) {
                    Classification cl = new ClassificationBN().getById(bean.getClassificationIdActual());
                    if ((bean.getEnterpriseIdActual() == (enter.getId()))
                            && (cl.getClassificationName().equals(classificationname))) {
                        S += bean.getSumPrice();
                    }
                }

            }
        }
        return S;
    }

    /**
     * 
     * Nghiep vu tinh thu, chi theo tuan
     * cua 1 Enterprise nao do
     * @param enter
     * @param classificationName
     * @param week
     * @param year
     * @return 
     */
    protected int calculateOperationByWeek(Enterprise enter, String classificationName, int week, int year) {
        int S = 0;
        List<Operation> list = new ArrayList<Operation>();
        list = operationBN.getOperationByWeek(enter, week, year);
        if (!list.isEmpty()) {
            for (Operation bean : list) {
                Classification cl = new ClassificationBN().getById(bean.getClassificationIdActual());
                if (cl.getClassificationName().equals(classificationName)) {
                    S += bean.getSumPrice();
                }
            }
        }
        return S;
    }

    /**
     * Nghiep vu tinh tong tien giao dich nao do 
     * @param date
     * @return 
     */
    protected int calculateOperationByDepartment(Enterprise enter, Department department, String classification) {

        int S = 0;
        List<Operation> list = new ArrayList<Operation>();
        list = operationBN.getOperationByEnterprise(enter);
        if (!list.isEmpty()) {
            for (Operation bean : list) {
                Classification cl = new ClassificationBN().getById(bean.getClassificationIdActual());
                if ((bean.getDepartmentIdActual() == (department.getId()))
                        && (cl.getClassificationName().equals(classification))) {
                    S += bean.getSumPrice();
                }
            }
        }
        return S;
    }

    /**
     * Nghiep vu tinh tong tien giao dich nao do
     * trong ngay, thang, nam hoac thang, nam hoac nam
     * @param date
     * @return 
     */
    protected int calculateOperationOfDepartmentByDate(Enterprise enter, Department department, Calendar date, String classification) {
        EntityManager em = PersistenceUltility.getEMF().createEntityManager();
        int S = 0;
        List<Operation> list = new ArrayList<Operation>();
        String sql = "Select tbl from Operation tbl where tbl." + Operation.FIELD_DATE_TIME
                + " = :dateCondition";

        list = em.createQuery(sql).setParameter("dateCondition", date, TemporalType.DATE).getResultList();
        if (!list.isEmpty()) {
            for (Operation bean : list) {
                Classification cl = new ClassificationBN().getById(bean.getClassificationIdActual());
                if ((bean.getEnterpriseIdActual() == (enter.getId()))
                        && (cl.getClassificationName().equals(classification))
                        && (bean.getDepartmentIdActual() == (department.getId()))) {
                    S += bean.getSumPrice();
                }
            }
        }
        return S;
    }

    /**
     * Nghiep vu tinh tong tien giao dich nao do
     * trong ngay, thang, nam hoac thang, nam hoac nam
     * @param date
     * @return 
     */
    protected int calculateOperationOfDepartmentByMonthOrYear(int flagTem, Enterprise enter, Department department, Calendar date, String classification) {
        EntityManager em = PersistenceUltility.getEMF().createEntityManager();
        int S = 0;
        String sql = "";
        if (flagTem == 1) {
            sql = "Select tbl from Operation tbl where tbl." + Operation.FIELD_DATE_TIME
                    + " Between :dateStart And :dateEnd";
            Calendar start = Calendar.getInstance();
            date.set(date.get(Calendar.YEAR), date.get(Calendar.MONTH) + 1, 1);
            Calendar end = Calendar.getInstance();
            date.set(date.get(Calendar.YEAR), date.get(Calendar.MONTH) + 1, date.getActualMaximum(
                    Calendar.DAY_OF_MONTH));
            List<Operation> list = new ArrayList<Operation>();
            list = em.createQuery(sql).setParameter("dateStart", start, TemporalType.DATE).
                    setParameter("dateEnd", end, TemporalType.DATE).getResultList();
            if (!list.isEmpty()) {
                for (Operation bean : list) {
                    Classification cl = new ClassificationBN().getById(bean.getClassificationIdActual());
                    if ((bean.getEnterpriseIdActual() == (enter.getId()))
                            && (cl.getClassificationName().equals(classification))
                            && (bean.getDepartmentIdActual() == (department.getId()))) {
                        S += bean.getSumPrice();
                    }
                }
            }
        } else {
            sql = "Select tbl from Operation tbl where tbl." + Operation.FIELD_DATE_TIME
                    + " Between :dateStart And :dateEnd";
            Calendar start = Calendar.getInstance();
            date.set(date.get(Calendar.YEAR), 1, 1);
            Calendar end = Calendar.getInstance();
            date.set(date.get(Calendar.YEAR), 12, 31);
            List<Operation> list = new ArrayList<Operation>();
            list = em.createQuery(sql).setParameter("dateStart", start, TemporalType.DATE).
                    setParameter("dateEnd", end, TemporalType.DATE).getResultList();
            if (!list.isEmpty()) {
                for (Operation bean : list) {
                    Classification cl = new ClassificationBN().getById(bean.getClassificationIdActual());
                    if ((bean.getEnterpriseIdActual() == (enter.getId()))
                            && (cl.getClassificationName().equals(classification))
                            && (bean.getDepartmentIdActual() == (department.getId()))) {
                        S += bean.getSumPrice();
                    }
                }
            }
        }

        return S;
    }

    /**
     * Nghiep vu tinh tong tien giao dich nao do
     * trong ngay, thang, nam hoac thang, nam hoac nam
     * @param date
     * @return 
     */
    protected int calculateOperationOfProductByDate(Enterprise enter, Product product, Calendar date, String classification) {
        EntityManager em = PersistenceUltility.getEMF().createEntityManager();
        int S = 0;
        List<Operation> list = new ArrayList<Operation>();
        String sql = "Select tbl from Operation tbl where tbl." + Operation.FIELD_DATE_TIME
                + " = :dateCondition";

        list = em.createQuery(sql).setParameter("dateCondition", date, TemporalType.DATE).getResultList();
        if (!list.isEmpty()) {
            for (Operation bean : list) {
                Classification cl = new ClassificationBN().getById(bean.getClassificationIdActual());
                if ((bean.getEnterpriseIdActual() == (enter.getId()))
                        && (cl.getClassificationName().equals(classification))) {

                    List<OperationDetail> operationDetails = operationDetailBN.select(OperationDetail.FIELD_OPERATION_ID_ACTUAL, String.valueOf(bean.getId()));

                    List<Integer> integers = new ArrayList<Integer>();
                    if (!operationDetails.isEmpty()) {
                        for (OperationDetail operationDetail : operationDetails) {
                            integers.add(operationDetail.getProductIdActual());
                        }
                    }

                    if (!integers.isEmpty()) {
                        boolean flagTem = false;
                        for (Integer i : integers) {
                            if (i == product.getId()) {
                                flagTem = true;
                            }
                        }
                        if (flagTem) {
                            S += bean.getSumPrice();
                        }
                    }

                }
            }
        }
        return S;
    }

    /**
     * Nghiep vu tinh tong tien giao dich nao do
     * trong ngay, thang, nam hoac thang, nam hoac nam
     * @param date
     * @return 
     */
    protected int calculateOperationOfProductByMonthOrYear(int flag, Enterprise enter, Product product, Calendar date, String classification) {

        EntityManager em = PersistenceUltility.getEMF().createEntityManager();
        int S = 0;
        String sql = "";
        if (flag == 1) {
            sql = "Select tbl from Operation tbl where tbl." + Operation.FIELD_DATE_TIME
                    + " Between :dateStart And :dateEnd";
            Calendar start = Calendar.getInstance();
            date.set(date.get(Calendar.YEAR), date.get(Calendar.MONTH) + 1, 1);
            Calendar end = Calendar.getInstance();
            date.set(date.get(Calendar.YEAR), date.get(Calendar.MONTH) + 1, date.getActualMaximum(
                    Calendar.DAY_OF_MONTH));
            List<Operation> list = new ArrayList<Operation>();
            list = em.createQuery(sql).setParameter("dateStart", start, TemporalType.DATE).
                    setParameter("dateEnd", end, TemporalType.DATE).getResultList();
            if (!list.isEmpty()) {
                for (Operation bean : list) {
                    Classification cl = new ClassificationBN().getById(bean.getClassificationIdActual());
                    if ((bean.getEnterpriseIdActual() == (enter.getId()))
                            && (cl.getClassificationName().equals(classification))) {
                        List<OperationDetail> operationDetails = operationDetailBN.select(OperationDetail.FIELD_OPERATION_ID_ACTUAL, String.valueOf(bean.getId()));

                        List<Integer> integers = new ArrayList<Integer>();
                        if (!operationDetails.isEmpty()) {
                            for (OperationDetail operationDetail : operationDetails) {
                                integers.add(operationDetail.getProductIdActual());
                            }
                        }
                        if (!integers.isEmpty()) {
                            boolean flagTem = false;
                            for (Integer i : integers) {
                                if (i == product.getId()) {
                                    flagTem = true;
                                }
                            }
                            if (flagTem) {
                                S += bean.getSumPrice();
                            }
                        }

                    }
                }
            }
        } else {
            sql = "Select tbl from Operation tbl where tbl." + Operation.FIELD_DATE_TIME
                    + " Between :dateStart And :dateEnd";
            Calendar start = Calendar.getInstance();
            date.set(date.get(Calendar.YEAR), 1, 1);
            Calendar end = Calendar.getInstance();
            date.set(date.get(Calendar.YEAR), 12, 31);
            List<Operation> list = new ArrayList<Operation>();
            list = em.createQuery(sql).setParameter("dateStart", start, TemporalType.DATE).
                    setParameter("dateEnd", end, TemporalType.DATE).getResultList();
            if (!list.isEmpty()) {
                for (Operation bean : list) {
                    Classification cl = new ClassificationBN().getById(bean.getClassificationIdActual());
                    if ((bean.getEnterpriseIdActual() == (enter.getId()))
                            && (cl.getClassificationName().equals(classification))) {
                        List<OperationDetail> operationDetails = operationDetailBN.select(OperationDetail.FIELD_OPERATION_ID_ACTUAL, String.valueOf(bean.getId()));

                        List<Integer> integers = new ArrayList<Integer>();
                        if (!operationDetails.isEmpty()) {
                            for (OperationDetail operationDetail : operationDetails) {
                                integers.add(operationDetail.getProductIdActual());
                            }
                        }
                        if (!integers.isEmpty()) {
                            boolean flagTem = false;
                            for (Integer i : integers) {
                                if (i == product.getId()) {
                                    flagTem = true;
                                }
                            }
                            if (flagTem) {
                                S += bean.getSumPrice();
                            }
                        }

                    }
                }
            }
        }
        return S;
    }

    /**
     * Nghiep vu tinh tong tien giao dich nao do 
     * @param date
     * @return 
     */
    protected int calculateOperationByProduct(Enterprise enter, Product product, String classification) {

        int S = 0;
        List<Operation> list = new ArrayList<Operation>();
        list = operationBN.getOperationByEnterprise(enter);
        if (!list.isEmpty()) {
            for (Operation bean : list) {
                Classification cl = new ClassificationBN().getById(bean.getClassificationIdActual());
                List<OperationDetail> operationDetails = operationDetailBN.select(OperationDetail.FIELD_OPERATION_ID_ACTUAL, String.valueOf(bean.getId()));

                List<Integer> integers = new ArrayList<Integer>();
                if (!operationDetails.isEmpty()) {
                    for (OperationDetail operationDetail : operationDetails) {
                        integers.add(operationDetail.getProductIdActual());
                    }
                }
                Classification classification1 = new Classification();
                classification1 = (Classification) classification1.getAccessDataOfEntity().getById(bean.getClassificationIdActual());
                if (!integers.isEmpty()) {
                    boolean flag = false;
                    for (Integer i : integers) {
                        if ((i == product.getId()) && (classification1.getClassificationName()).equals(classification)) {
                            flag = true;
                        }
                    }
                    if (flag) {
                        S += bean.getSumPrice();
                    }
                }

            }
        }
        return S;
    }

///////////////////////////////////////// Tinh Toan Theo Enterprise ////////////////////////////////////////////////////   
    @Override
    public int calculateImportByDate(Enterprise enter, Calendar date) {
        int S = 0;
        String income = Classification.CLASSIFICATION_IMPORT;
        S = calculateOperationByDate(enter, date, income);
        return S;
    }

    @Override
    public int calculateExportByDate(Enterprise enter, Calendar date) {
        int S = 0;
        String outcome = Classification.CLASSIFICATION_EXPORT;
        S = calculateOperationByDate(enter, date, outcome);
        return S;
    }

    @Override
    public int calculateImportByWeek(Enterprise enter, int week, int year) {
        int S = 0;
        S = calculateOperationByWeek(enter, Classification.CLASSIFICATION_IMPORT, week, year);
        return S;
    }

    @Override
    public int calculateExportByWeek(Enterprise enter, int week, int year) {
        int S = 0;
        S = calculateOperationByWeek(enter, Classification.CLASSIFICATION_EXPORT, week, year);
        return S;
    }

    @Override
    public int calculateImportByMonth(Enterprise enter, Calendar month) {
        int S = 0;
        String income = Classification.CLASSIFICATION_IMPORT;
        S = calculateOperationByMonthOrYear(1, enter, month, income);
        return S;
    }

    @Override
    public int calculateExportByMonth(Enterprise enter, Calendar month) {
        int S = 0;
        String outcome = Classification.CLASSIFICATION_EXPORT;
        S = calculateOperationByMonthOrYear(1, enter, month, outcome);
        return S;
    }

    @Override
    public int calculateImportByYear(Enterprise enter, Calendar year) {
        int S = 0;
        String income = Classification.CLASSIFICATION_IMPORT;
        S = calculateOperationByMonthOrYear(0, enter, year, income);
        return S;
    }

    @Override
    public int calculateExportByYear(Enterprise enter, Calendar year) {
        int S = 0;
        String outcome = Classification.CLASSIFICATION_EXPORT;
        S = calculateOperationByMonthOrYear(0, enter, year, outcome);
        return S;
    }

    @Override
    public int calculateSumImport() {
        int S = 0;
        List<Operation> list = operationBN.selectAll();
        if (!list.isEmpty()) {
            for (Operation bean : list) {
                Classification cl = new ClassificationBN().getById(bean.getClassificationIdActual());
                if (cl.getClassificationType().equals(Classification.CLASSIFICATION_IMPORT)) {
                    S += bean.getSumPrice();
                }
            }
        }
        return S;
    }

    @Override
    public int calculateSumExport() {
        int S = 0;
        List<Operation> list = operationBN.selectAll();
        if (!list.isEmpty()) {
            for (Operation bean : list) {
                Classification cl = new ClassificationBN().getById(bean.getClassificationIdActual());
                if (cl.getClassificationType().equals(Classification.CLASSIFICATION_EXPORT)) {
                    S += bean.getSumPrice();
                }
            }
        }
        return S;
    }

    @Override
    public int calculateSumImportByEnterprise(Enterprise enterprise) {
        int S = 0;
        List<Operation> list = operationBN.selectAll();
        if (!list.isEmpty()) {
            for (Operation bean : list) {
                Classification cl = new ClassificationBN().getById(bean.getClassificationIdActual());
                if ((cl.getClassificationType().equals(Classification.CLASSIFICATION_IMPORT))
                        && (bean.getEnterpriseIdActual() == (enterprise.getId()))) {
                    S += bean.getSumPrice();
                }
            }
        }
        return S;
    }

    @Override
    public int calculateSumExportByEnterprise(Enterprise enterprise) {
        int S = 0;
        List<Operation> list = operationBN.selectAll();
        if (!list.isEmpty()) {
            for (Operation bean : list) {
                Classification cl = new ClassificationBN().getById(bean.getClassificationIdActual());
                if ((cl.getClassificationType().equals(Classification.CLASSIFICATION_EXPORT))
                        && (bean.getEnterpriseIdActual() == (enterprise.getId()))) {
                    S += bean.getSumPrice();
                }
            }
        }
        return S;
    }

///////////////////////////////////////// Ket Thuc Tinh Toan Theo Enterprise ////////////////////////////////////////////////////    
///////////////////////////////////////// Tinh Toan Theo Department ////////////////////////////////////////////////////
    @Override
    public int calculateImportByMonthOfDepartment(Enterprise enterprise, Department department, Calendar date) {
        int S = 0;
        S = calculateOperationOfDepartmentByDate(enterprise, department, date, Classification.CLASSIFICATION_IMPORT);
        return S;
    }

    @Override
    public int calculateExportByMonthOfDepartment(Enterprise enterprise, Department department, Calendar date) {
        int S = 0;
        S = calculateOperationOfDepartmentByDate(enterprise, department, date, Classification.CLASSIFICATION_EXPORT);
        return S;
    }

    @Override
    public int calculateImportByYearOfDepartment(Enterprise enterprise, Department department, Calendar date) {
        int S = 0;
        S = calculateOperationOfDepartmentByMonthOrYear(1, enterprise, department, date, Classification.CLASSIFICATION_IMPORT);
        return S;
    }

    @Override
    public int calculateExportByYearOfDepartment(Enterprise enterprise, Department department, Calendar date) {
        int S = 0;
        S = calculateOperationOfDepartmentByMonthOrYear(0, enterprise, department, date, Classification.CLASSIFICATION_EXPORT);
        return S;
    }

    @Override
    public int calculateImportAllOfDepartment(Enterprise enterprise, Department department) {
        int S = 0;
        S = calculateOperationByDepartment(enterprise, department, Classification.CLASSIFICATION_IMPORT);
        return S;
    }

    @Override
    public int calculateExportAllOfDepartment(Enterprise enterprise, Department department) {
        int S = 0;
        S = calculateOperationByDepartment(enterprise, department, Classification.CLASSIFICATION_EXPORT);
        return S;
    }

//////////////////////////////////////////////// Ket Thuc Tinh Toan Theo Department//////////////////////////////////////////////////////////////
////////////////////////////////// Tính toán theo Product ///////////////////////////////////////////////////////    
    @Override
    public int calculateImportByMonthOfProduct(Enterprise enterprise, Product product, Calendar date) {
        int S = 0;
        S = calculateOperationOfProductByMonthOrYear(1, enterprise, product, date, Classification.CLASSIFICATION_IMPORT);
        return S;
    }

    @Override
    public int calculateExportByMonthOfProduct(Enterprise enterprise, Product product, Calendar date) {
        int S = 0;
        S = calculateOperationOfProductByMonthOrYear(1, enterprise, product, date, Classification.CLASSIFICATION_EXPORT);
        return S;
    }

    @Override
    public int calculateImportByYearOfProduct(Enterprise enterprise, Product product, Calendar date) {
        int S = 0;
        S = calculateOperationOfProductByMonthOrYear(0, enterprise, product, date, Classification.CLASSIFICATION_IMPORT);
        return S;
    }

    @Override
    public int calculateExportByYearOfProduct(Enterprise enterprise, Product product, Calendar date) {
        int S = 0;
        S = calculateOperationOfProductByMonthOrYear(0, enterprise, product, date, Classification.CLASSIFICATION_EXPORT);
        return S;
    }

    @Override
    public int calculateImportAllOfProduct(Enterprise enterprise, Product product) {
        int S = 0;
        S = calculateOperationByProduct(enterprise, product, Classification.CLASSIFICATION_IMPORT);
        return S;
    }

    @Override
    public int calculateExportAllOfProduct(Enterprise enterprise, Product product) {
        int S = 0;
        S = calculateOperationByProduct(enterprise, product, Classification.CLASSIFICATION_EXPORT);
        return S;
    }
///////////////////////// Kết thúc tính toán theo Product ///////////////////////////////////////////////////////    
}
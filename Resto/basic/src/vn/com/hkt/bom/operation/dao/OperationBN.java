/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.bom.operation.dao;

import vn.com.hkt.pilot.entities.Department;
import vn.com.hkt.pilot.entities.Operation;
import java.util.ArrayList;
import java.util.List;
import org.openide.util.Lookup;
import org.openide.util.lookup.ServiceProvider;
import vn.com.hkt.basic.api.IOperationBN;
import vn.com.hkt.basic.api.IOperationDetailBN;
import vn.com.hkt.basic.jpa.PersistenceUltility;
import vn.com.hkt.pilot.datetime.utils.DateTimeConverter;
import vn.com.hkt.pilot.entities.Enterprise;
import vn.com.hkt.pilot.entities.OperationDetail;
import vn.com.hkt.pilot.entities.Product;
import vn.com.hkt.pilot.identity.access.spi.AccessData;

/**
 *
 * @author khanguct
 */
@ServiceProvider(service = IOperationBN.class)
public class OperationBN extends AccessData<Operation> implements IOperationBN {
    
    private DateTimeConverter dateTimeConverter = new DateTimeConverter();
    private IOperationDetailBN operationDetailBN;
    
    public OperationBN() {
        setAccessData(PersistenceUltility.getEMF(), Operation.class);
        operationDetailBN = Lookup.getDefault().lookup(IOperationDetailBN.class);
    }
    
    @Override
    public List<Operation> filterOperationByDate(String date) {
        return filter(Operation.FIELD_DATE_TIME, date);
    }
    
    @Override
    public List<Operation> filterOperationsById(String id) {
        return filter(Operation.FIELD_OPERATION_ID, id);
    }
    
    @Override
    public List<Operation> filterOperationsByName(String name) {
        return filter(Operation.FIELD_OPERATION_NAME, name);
    }
    
    @Override
    public List<Operation> filterOperationsBy_EnterpriseId(int id) {
        return filter(Operation.FIELD_ENTERPRISE_ID_ACTUAL, String.valueOf(id));
    }
    
    @Override
    public List<Operation> getOperationByEnterprise(Enterprise enter) {
        return select(Operation.FIELD_ENTERPRISE_ID_ACTUAL, String.valueOf(enter.getId()));
    }
    
    @Override
    public List<Enterprise> enterpriseHasOperation() {
        try {
            String sql = "SELECT enter  FROM " + Enterprise.class.getSimpleName() + " enter "
                    + "where enter." + Enterprise.FILED_ID + " IN "
                    + "(Select oper." + Operation.FIELD_ENTERPRISE_ID_ACTUAL + " from "
                    + Operation.class.getSimpleName() + " oper)";
            List<Object> list = queryList(sql);
            List<Enterprise> le = new ArrayList<Enterprise>();
            for (int i = 0; i < list.size(); i++) {
                le.add((Enterprise) list.get(i));
            }
            return le;
        } catch (Exception e) {
            return new ArrayList<Enterprise>();
        }
    }
    
    @Override
    public List<String> weekHasOperation() {
        int i = 0;
        List<String> list = new ArrayList<String>();
        List<Operation> list1 = new ArrayList<Operation>();
        list1 = selectAll();
        if (!list1.isEmpty()) {
            for (Operation bean : list1) {
                i = dateTimeConverter.getWeekOfYear(bean.getDateTime());
                list.add(String.valueOf(i));
            }
        }
        return list;
    }
    
    @Override
    public List<String> weekHasOperation(Enterprise enterprise) {
        int i = 0;
        List<String> list = new ArrayList<String>();
        List<Operation> list1 = new ArrayList<Operation>();
        list1 = getOperationByEnterprise(enterprise);
        if (!list1.isEmpty()) {
            for (Operation bean : list1) {
                i = dateTimeConverter.getWeekOfYear(bean.getDateTime());
                list.add(String.valueOf(i));
            }
        }
        return list;
    }
    
    @Override
    public List<Operation> getOperationByWeek(int week, int year) {
        List<Operation> list = new ArrayList<Operation>();
        list = selectAll();
        if (!list.isEmpty()) {
            for (Operation bean : list) {
                int w = dateTimeConverter.getWeekOfMonth(bean.getDateTime());
                int y = dateTimeConverter.getYear(bean.getDateTime());
                if ((week == w) && (year == y)) {
                    list.add(bean);
                }
            }
        }
        return list;
    }
    
    @Override
    public List<Operation> getOperationByWeek(Enterprise enterprise, int week, int year) {
        List<Operation> list = new ArrayList<Operation>();
        list = getOperationByEnterprise(enterprise);
        if (!list.isEmpty()) {
            for (Operation bean : list) {
                int w = dateTimeConverter.getWeekOfMonth(bean.getDateTime());
                int y = dateTimeConverter.getYear(bean.getDateTime());
                if ((week == w) && (year == y)) {
                    list.add(bean);
                }
            }
        }
        return list;
    }
    
    @Override
    public List<Operation> filterOperationByDepartment(Enterprise enterprise, Department department) {
        List<Operation> list = new ArrayList<Operation>();
        List<Operation> list1 = new ArrayList<Operation>();
        list1 = selectAll();
        if (!list1.isEmpty()) {
            for (Operation bean : list1) {
                if (bean.getEnterpriseIdActual() == enterprise.getId()
                        && bean.getDepartmentIdActual() == department.getId()) {
                    list.add(bean);
                }
            }
        }
        return list;
    }
    
    @Override
    public List<Operation> filterOperationByProduct(Enterprise enterprise, Product product) {
        List<Operation> list = new ArrayList<Operation>();
        List<Operation> list1 = new ArrayList<Operation>();
        list1 = getOperationByEnterprise(enterprise);
        if (!list1.isEmpty()) {
            for (Operation operation : list1) {
                
                List<String> lf= new ArrayList<String>();
                lf.add(OperationDetail.FIELD_OPERATION_ID_ACTUAL);
                lf.add(OperationDetail.FIELD_PRODUCT_ID_ACTUAL);
                List<String> ld= new ArrayList<String>();
                ld.add(String.valueOf(operation.getId()));
                ld.add(String.valueOf(product.getId()));
                List<OperationDetail> lod= operationDetailBN.select(lf, ld);
                if(!lod.isEmpty()) {
                    list.add(operation);
                }                
//                List<OperationDetail> operationDetails = operationDetailBN.select(OperationDetail.FIELD_OPERATION_ID_ACTUAL, String.valueOf(operation.getId()));
//                List<Integer> productSet = new ArrayList<Integer>();
//                if (!operationDetails.isEmpty()) {
//                    for (OperationDetail operationDetail : operationDetails) {
//                        productSet.add(operationDetail.getProductIdActual());
//                    }
//                }
//                if (!productSet.isEmpty()) {
//                    boolean flag = false;
//                    for (Integer i : productSet) {
//                        if (i == product.getId()) {
//                            flag = true;
//                        }
//                    }
//                    if (flag) {
//                        list.add(operation);
//                    }
//                }
//                List<OperationDetail> operationDetails = operationDetailBN.select(OperationDetail.FIELD_OPERATION_ID_ACTUAL, String.valueOf(operation.getId()));
//                
//                List<Integer> productSet = new ArrayList<Integer>();
//                if (!operationDetails.isEmpty()) {
//                    for (OperationDetail operationDetail : operationDetails) {
//                        productSet.add(operationDetail.getProductIdActual());
//                    }
//                }
//                if (!productSet.isEmpty()) {
//                    boolean flag = false;
//                    for (Integer i : productSet) {
//                        if (i == product.getId()) {
//                            flag = true;
//                        }
//                    }
//                    if (flag) {
//                        list.add(operation);
//                    }
//                }
            }
        }
        return list;
    }
    
    @Override
    public List<Operation> filterOperationByProduct(Enterprise enterprise, Department department, Product product) {
        List<Operation> list = new ArrayList<Operation>();
        List<Operation> list1 = new ArrayList<Operation>();
        list1 = filterOperationByProduct(enterprise, product);
        if (!list1.isEmpty()) {
            for (Operation operation : list1) {
                if (operation.getDepartmentIdActual() == department.getId()) {
                    list.add(operation);
                }
            }
        }
        return list;
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.ap.finance.spi;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.swing.JOptionPane;
import org.openide.util.Lookup;
import org.openide.util.lookup.ServiceProvider;
import vn.com.hkt.basic.api.IDepartmentBN;
import vn.com.hkt.basic.api.IEnterpriseBN;
import vn.com.hkt.basic.api.IOperationBN;
import vn.com.hkt.basic.api.IProductBN;
import vn.com.hkt.pilot.datetime.utils.DateTimeConverter;
import vn.com.hkt.pilot.entities.Department;
import vn.com.hkt.pilot.entities.Enterprise;
import vn.com.hkt.pilot.entities.Operation;
import vn.com.hkt.pilot.entities.Product;
import vn.com.hkt.ap.data.ProductStockCycle;
import vn.com.hkt.ap.data.ProductsInStock;
import vn.com.hkt.ap.finance.api.IInventoriesValueCaculator;
import vn.com.hkt.ap.finance.promotion.BusinessCycleUtil;
import vn.com.hkt.basic.api.IClassificationBN;
import vn.com.hkt.basic.api.IOperationDetailBN;
import vn.com.hkt.pilot.entities.Classification;
import vn.com.hkt.pilot.entities.OperationDetail;

/**
 *
 * @author Admin
 */
@ServiceProvider(service = IInventoriesValueCaculator.class)
public class InventoriesValueCaculator implements IInventoriesValueCaculator {

    private Calendar dateLanMarkCycle = null;// mạc định mốc tính kỳ là 1/1/2012
    private int number = 1;// khoảng cách kỳ kinh doanh là 1
    private String timePeriodType = BusinessCycleUtil.TIME_PERIOD_MONTH;// mạc định tính theo tháng
    private int productId;// mã sản phẩm
    private int enterpriseId;// mã doanh nghiệp
    private BusinessCycleUtil bcu;// tiện ích xác định kỳ kinh doanh
    private List<Operation> operations;// danh sách nghiệp vụ
    private int depeartmentId;// mã phòng ban
    private DateTimeConverter dtc = new DateTimeConverter();// tiện ích xử lý thời gian
    private List<ProductsInStock> listProductFirstCycle;// danh sách các đợt hàng đầu kỳ
    private List<ProductsInStock> listProductLastCycle;// danh sách các đợt hàng cuối kỳ
    private List<Integer> listOperationId = new ArrayList<Integer>();// danh sách nghiệp vụ trong kỳ
    private List<ProductStockCycle> cycles;// danh sách kỳ kinh doanh
    private IClassificationBN classificationBN = Lookup.getDefault().lookup(IClassificationBN.class);
    private IOperationDetailBN operationDetailBN = Lookup.getDefault().lookup(IOperationDetailBN.class);

    public InventoriesValueCaculator() {
        dateLanMarkCycle = Calendar.getInstance();
        dateLanMarkCycle.set(2010, 1, 1);
    }

    @Override
    public void setTimePeriod(Calendar dateLanmarkCycle, int number, String timePeriodType) {
        this.dateLanMarkCycle = dateLanmarkCycle;
        this.number = number;
        this.timePeriodType = timePeriodType;
    }

    @Override
    public void setProductId(int enterpriseId, int departmentId, int productId) {
        this.productId = productId;
        this.enterpriseId = enterpriseId;
        this.depeartmentId = departmentId;
    }

    private void loadOperation() {
        IProductBN pbn = Lookup.getDefault().lookup(IProductBN.class);
        IEnterpriseBN ebn = Lookup.getDefault().lookup(IEnterpriseBN.class);
        IDepartmentBN dbn = Lookup.getDefault().lookup(IDepartmentBN.class);

        Enterprise enterprise = ebn.getById(enterpriseId);
        Product product = pbn.getById(productId);
        Department department = dbn.getById(depeartmentId);

        IOperationBN obn = Lookup.getDefault().lookup(IOperationBN.class);
        if (department == null) {
            operations = obn.filterOperationByProduct(enterprise, product);
        } else {
            operations = obn.filterOperationByProduct(enterprise, department, product);
        }
        operations = bcu.sort(operations);
    }

// TODO nghiệp vụ tính mặc định nhập trước xuất trước
    /**
     * thực hiện nghiệp vụ nhập
     * @param operation
     */
    private void importProduct(OperationDetail operation) {
        ProductsInStock pis = new ProductsInStock();
        pis.setInitialQuantity(operation.getQuantity());
        pis.setPrice(operation.getPrice());
        pis.setQuantity(operation.getQuantity());
        listProductLastCycle.add(pis);
    }
// TODO nghiệp vụ tính mặc định nhập trước xuất trước

    /**
     * thực hiện nghiệp vụ xuất
     * @param operation 
     */
    private void exportProduct(OperationDetail operation) {
        long total = -1;
        total = operation.getQuantity();
        for (int i = 0; i < listProductLastCycle.size(); i++) {
            if (total <= 0) {
                return;
            }
            if (listProductLastCycle.get(i).getQuantity() <= total) {
                total -= listProductLastCycle.get(i).getQuantity();
                listProductLastCycle.get(i).setQuantity(0);
            } else {
                listProductLastCycle.get(i).setQuantity(listProductLastCycle.get(i).getQuantity() - total);
                total = 0;
            }
        }
        if (total > 0) {
            JOptionPane.showMessageDialog(null, "Nợ xuất hàng: " + total);
        }
    }

    /**
     * Khởi tạo đầu kỳ
     */
    private void initProductFirstCycle() {
        if (listProductFirstCycle == null) {
            listProductFirstCycle = new ArrayList<ProductsInStock>();
        }
        if (listProductLastCycle == null) {
            listProductLastCycle = new ArrayList<ProductsInStock>();
        }
        int i = 0;
        while (i < listProductLastCycle.size()) {
            if (listProductLastCycle.get(i).getQuantity() == 0) {
                listProductLastCycle.remove(i);
            } else {
                i++;
            }
        }
        listProductFirstCycle = new ArrayList<ProductsInStock>();
        for (i = 0; i < listProductLastCycle.size(); i++) {
            listProductFirstCycle.add(listProductLastCycle.get(i));
        }
    }

    /**
     * tính toán đầu kỳ dựa trên kỳ trước
     * @param psc
     * @param date
     * @return 
     */
    private ProductStockCycle caculatorFirstCycle(ProductStockCycle psc, Calendar date) {
        initProductFirstCycle();
        long quatityFirstCycle = 0;
        float priceAverageFirstCycle = 0;
        float valueAverageFirstCycle = 0;
        if (psc != null) {
            quatityFirstCycle = psc.getLastQuantity();
            priceAverageFirstCycle = psc.getPriceAverageCycle();
            valueAverageFirstCycle = psc.getLastValueAverage();
        }
        psc = new ProductStockCycle();
        psc.setStartTime(bcu.dateStartCycle(date));
        psc.setFinishTime(bcu.dateFinishCycle(psc.getStartTime()));
        psc.setFirstQuantity(quatityFirstCycle);
        psc.setFirstValueAverage(valueAverageFirstCycle);
        psc.setPriceAverageFirstCycle(priceAverageFirstCycle);
        psc.setDecreaseQuantity(0);
        psc.setDecreaseValueReal(0);
        psc.setIncreaseQuantity(0);
        psc.setIncreaseValueReal(0);
        float s = 0;
        for (int i = 0; i < listProductFirstCycle.size(); i++) {
            s += listProductFirstCycle.get(i).getPrice() * listProductFirstCycle.get(i).getQuantity();
        }
        psc.setFirstValueReal(s);
        listOperationId = new ArrayList<Integer>();
        return psc;
    }

    /**
     * tính toán cuối kỳ
     * @param psc
     * @return 
     */
    private ProductStockCycle caculatorLastCycle(ProductStockCycle psc) {        
        psc.setLastQuantity(psc.getFirstQuantity() + psc.getIncreaseQuantity()
                - psc.getDecreaseQuantity());
        float valueIncreaseReal = 0;
        float valueDecreaseReal = 0;
        float lastVauleReal = 0;

        for (int i = 0; i < listProductLastCycle.size(); i++) {
            if (i < listProductFirstCycle.size()) {
                long t1 = listProductFirstCycle.get(i).getQuantity();
                long t2 = listProductLastCycle.get(i).getQuantity();
                float price = listProductFirstCycle.get(i).getPrice();
                valueDecreaseReal += (t1 - t2) * price;
            } else {
                long t1 = listProductLastCycle.get(i).getInitialQuantity();
                long t2 = listProductLastCycle.get(i).getQuantity();
                float price = listProductLastCycle.get(i).getPrice();
                valueDecreaseReal += (t1 - t2) * price;
                valueIncreaseReal += t1 * price;
            }
            if (listProductLastCycle.get(i).getQuantity() > 0) {
                lastVauleReal += listProductLastCycle.get(i).getQuantity()
                        * listProductLastCycle.get(i).getPrice();
            }
        }
        psc.setDecreaseValueReal(valueDecreaseReal);
        psc.setIncreaseValueReal(valueIncreaseReal);
        if (valueIncreaseReal != 0) {
            psc.setPriceAverageCycle(valueIncreaseReal / psc.getIncreaseQuantity());
        } else {
            psc.setPriceAverageCycle(psc.getPriceAverageFirstCycle());
        }
        psc.setLastValueAverage(psc.getPriceAverageCycle() * psc.getLastQuantity());
        psc.setLastValueReal(lastVauleReal);
        psc.setListOperationId(listOperationId);
        return psc;
    }

    @Override
    public List<ProductStockCycle> caculator() {
        bcu = new BusinessCycleUtil(dateLanMarkCycle, timePeriodType, number);
        loadOperation();
        cycles = new ArrayList<ProductStockCycle>();
        listProductFirstCycle = null;
        listProductLastCycle = null;
        ProductStockCycle psc = null;

        for (int i = 0; i < operations.size(); i++) {
            Operation operation = operations.get(i);
            if (dtc.dateIsNull(operation.getDateTime())) {
                break;
            }
            if (psc == null || !bcu.dateBetween(operation.getDateTime(),
                    psc.getStartTime(), psc.getFinishTime())) {
                if (psc != null) {
                    psc = caculatorLastCycle(psc);
                    cycles.add(psc);
                }
                psc = caculatorFirstCycle(psc, operation.getDateTime());
            }
            if (dtc.compareTwoDate(operation.getDateTime(), psc.getStartTime()) >= 0
                    && dtc.compareTwoDate(operation.getDateTime(), psc.getFinishTime()) <= 0) {
                listOperationId.add(operation.getId());
                OperationDetail operationDetail = getOperationDetail(operation.getId(), productId);
                if (operationDetail != null) {
                    Classification classification = classificationBN.getById(operation.getClassificationIdActual());
                    if (classification.getClassificationType().equals(Classification.CLASSIFICATION_IMPORT)) {
                        psc.setIncreaseQuantity(psc.getDecreaseQuantity() + operationDetail.getQuantity());
                        importProduct(operationDetail);
                    }
                    if (classification.getClassificationType().equals(Classification.CLASSIFICATION_EXPORT)) {
                        psc.setDecreaseQuantity(psc.getDecreaseQuantity() + operationDetail.getQuantity());
                        exportProduct(operationDetail);
                    }
                }
            }
        }
        if (psc != null) {
            psc = caculatorLastCycle(psc);
            cycles.add(psc);
        }
        return cycles;
    }

    private OperationDetail getOperationDetail(int id, int productId) {
        List<String> lf = new ArrayList<String>();
        lf.add(OperationDetail.FIELD_OPERATION_ID_ACTUAL);
        lf.add(OperationDetail.FIELD_PRODUCT_ID_ACTUAL);
        List<String> ld = new ArrayList<String>();
        ld.add(String.valueOf(id));
        ld.add(String.valueOf(productId));
        List<OperationDetail> lod = operationDetailBN.select(lf, ld);
        if (!lod.isEmpty()) {
            lod.get(0);
        }
        return null;
    }
}

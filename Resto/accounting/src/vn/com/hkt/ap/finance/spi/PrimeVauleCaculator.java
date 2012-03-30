/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.ap.finance.spi;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import org.openide.util.Lookup;
import org.openide.util.lookup.ServiceProvider;
import vn.com.hkt.basic.api.IDepartmentBN;
import vn.com.hkt.basic.api.IEnterpriseBN;
import vn.com.hkt.basic.api.IOperationBN;
import vn.com.hkt.pilot.datetime.utils.DateTimeConverter;
import vn.com.hkt.pilot.entities.Department;
import vn.com.hkt.pilot.entities.Enterprise;
import vn.com.hkt.pilot.entities.Operation;
import vn.com.hkt.ap.data.BusinessCycle;
import vn.com.hkt.ap.finance.api.IPrimeValueCaculator;
import vn.com.hkt.ap.finance.promotion.BusinessCycleUtil;
import vn.com.hkt.basic.api.IClassificationBN;
import vn.com.hkt.pilot.entities.Classification;

/**
 *
 * @author Binle
 */
@ServiceProvider(service = IPrimeValueCaculator.class)
public class PrimeVauleCaculator implements IPrimeValueCaculator {

    private int departmentId;
    private int enterpriseId;
    private String timePeriodType = BusinessCycleUtil.TIME_PERIOD_MONTH;
    private int number = 1;
    private Calendar dateLanMarkCycle = null;
    private List< Operation> operations = null;
    private BusinessCycleUtil bcu;
    private IClassificationBN classificationBN = Lookup.getDefault().lookup(IClassificationBN.class);
    private DateTimeConverter dtc = new DateTimeConverter();

    public PrimeVauleCaculator() {
        dateLanMarkCycle=Calendar.getInstance();
        dateLanMarkCycle.set(2010, 1, 1);
    }

    public PrimeVauleCaculator(int departmentId, int enterpriseId,
            String timePeriodType, int number, Calendar dateLanMarkCycle) {
        this.departmentId = departmentId;
        this.enterpriseId = enterpriseId;
        this.timePeriodType = timePeriodType;
        this.number = number;
        this.dateLanMarkCycle = dateLanMarkCycle;
    }

    @Override
    public void setDepartmentId(int enterpriseId, int departmentId) {
        this.departmentId = departmentId;
        this.enterpriseId = enterpriseId;
    }

    @Override
    public void setTimePeriod(Calendar dateLanMarkCycle, int number, String timePeriodType) {
        this.dateLanMarkCycle = dateLanMarkCycle;
        this.number = number;
        this.timePeriodType = timePeriodType;
    }

    /**
     * load toan bo cac nghiep vu cua ohong ban
     */
    private void loadOperation() {
        IDepartmentBN dbn = Lookup.getDefault().lookup(IDepartmentBN.class);
        IEnterpriseBN ebn = Lookup.getDefault().lookup(IEnterpriseBN.class);
        IOperationBN obn = Lookup.getDefault().lookup(IOperationBN.class);

        Enterprise enterprise = ebn.getById(enterpriseId);
        Department department = dbn.getById(departmentId);
        if (department != null) {
            operations = obn.filterOperationByDepartment(enterprise, department);
        } else {
            operations = obn.select(Operation.FIELD_ENTERPRISE_ID_ACTUAL, String.valueOf(enterpriseId));
        }
        operations = bcu.sort(operations);
    }

    @Override
    public final List< BusinessCycle> caculator() {
        bcu = new BusinessCycleUtil(dateLanMarkCycle, timePeriodType, number);
        loadOperation();
        List< BusinessCycle> businessCycles = new ArrayList< BusinessCycle>();
        BusinessCycle bc = null;
        List<Integer> listOperationId = null;
        for (int i = 0; i < operations.size(); i++) {
            Operation operation = operations.get(i);
            if (dtc.dateIsNull(operation.getDateTime())) {
                break;
            }
            if (bc == null || !bcu.dateBetween(operation.getDateTime(),
                    bc.getStartTime(), bc.getFinishTime())) {
                float value = 0;
                if (bc != null) {
                    value = bc.getFirstValue() + bc.getIncreaseValue()
                            - bc.getDecreaseValue();
                    bc.setLastValue(value);
                    bc.setListOperationId(listOperationId);
                    businessCycles.add(bc);
                }
                bc = new BusinessCycle();
                bc.setDepartmentId(departmentId);
                bc.setFirstValue(value);
                bc.setIncreaseValue(0);
                bc.setDecreaseValue(0);
                
                bc.setStartTime(bcu.dateStartCycle(operation.getDateTime()));
                bc.setFinishTime(bcu.dateFinishCycle(bc.getStartTime()));
                listOperationId = new ArrayList<Integer>();
            }
            if (dtc.compareTwoDate(operation.getDateTime(), bc.getStartTime()) >= 0
                    && dtc.compareTwoDate(operation.getDateTime(), bc.getFinishTime()) <= 0) {
                listOperationId.add(operation.getId());
                Classification classification = classificationBN.getById(operation.getClassificationIdActual());
                if (classification.getClassificationType().equals(Classification.CLASSIFICATION_IMPORT)) {
                    bc.setDecreaseValue(bc.getDecreaseValue() + operation.getSumPrice());
                }
                if (classification.getClassificationType().equals(Classification.CLASSIFICATION_EXPORT)) {
                    bc.setIncreaseValue(bc.getIncreaseValue() + operation.getSumPrice());
                }
            }
        }
        if (bc != null) {
            float value = bc.getFirstValue() + bc.getIncreaseValue()
                    - bc.getDecreaseValue();
            bc.setLastValue(value);
            bc.setListOperationId(listOperationId);
            businessCycles.add(bc);
        }
        return businessCycles;
    }
}

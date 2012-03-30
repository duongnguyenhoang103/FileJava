/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.basic.api;

import vn.com.hkt.pilot.entities.Operation;
import java.util.List;
import vn.com.hkt.pilot.entities.Department;
import vn.com.hkt.pilot.entities.Enterprise;
import vn.com.hkt.pilot.entities.Product;
import vn.com.hkt.pilot.identity.access.api.IAccessData;

/**
 *
 * @author khanguct
 */
public interface IOperationBN extends IAccessData<Operation> {

    public List<Operation> filterOperationsById(String id);

    public List<Operation> filterOperationsByName(String name);

    public List<Operation> filterOperationsBy_EnterpriseId(int id);

    public List<Operation> filterOperationByDate(String date);

    public List<Operation> getOperationByEnterprise(Enterprise id);

    public List<Enterprise> enterpriseHasOperation();

    public List<String> weekHasOperation();

    public List<String> weekHasOperation(Enterprise enterprise);

    public List<Operation> getOperationByWeek(int week, int year);

    public List<Operation> getOperationByWeek(Enterprise enterprise, int week, int year);

    public List<Operation> filterOperationByDepartment(Enterprise enterprise, Department department);

    public List<Operation> filterOperationByProduct(Enterprise enterprise, Product product);

    public List<Operation> filterOperationByProduct(Enterprise enterprise, Department department, Product product);
}

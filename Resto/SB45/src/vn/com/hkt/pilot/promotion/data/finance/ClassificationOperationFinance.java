/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.pilot.promotion.data.finance;

import java.util.Collection;
import org.openide.util.Lookup;
import vn.com.hkt.pilot.promotion.entity.IClassificationOperation;

/**
 *
 * @author khangpn
 */
public class ClassificationOperationFinance {
    
    private Collection<? extends IClassificationOperation> classificationOperations;

    public ClassificationOperationFinance() {
        classificationOperations = Lookup.getDefault().lookupAll(IClassificationOperation.class);
    }
    
    public String getClassificationOperationName(int id){
        String value = " ";
        if(!classificationOperations.isEmpty()){
            for(IClassificationOperation classificationOperation:classificationOperations){
                if(id == classificationOperation.getHardId()){
                    value = classificationOperation.getClassificationName();
                }
            }
        }
        return value;
    }
    
    public IClassificationOperation getClassificationOperation(int id){
        IClassificationOperation value = null;
        if(!classificationOperations.isEmpty()){
            for(IClassificationOperation classificationOperation:classificationOperations){
                if(id == classificationOperation.getHardId()){
                    value = classificationOperation;
                }
            }
        }
        return value;
    }
}

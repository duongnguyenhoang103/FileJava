/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.pilot.promotion.dao;

import vn.com.hkt.pilot.identity.access.spi.AccessData;
import vn.com.hkt.pilot.promotion.entity.IClassificationOperation;
import vn.com.hkt.pilot.promotion.jpa.utils.jpaUtils;

/**
 *
 * @author khangpn
 */
public class ClassificationOperationBN extends AccessData<IClassificationOperation> {
    
    public ClassificationOperationBN() {
        setAccessData(jpaUtils.getEmf(), IClassificationOperation.class);
    }
}

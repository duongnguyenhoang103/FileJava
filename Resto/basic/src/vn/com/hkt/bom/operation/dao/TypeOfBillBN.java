/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.bom.operation.dao;

import org.openide.util.lookup.ServiceProvider;
import vn.com.hkt.basic.api.ITypeOfBillBN;
import vn.com.hkt.basic.jpa.PersistenceUltility;
import vn.com.hkt.pilot.entities.TypeOfBill;
import vn.com.hkt.pilot.identity.access.spi.AccessData;

/**
 *
 * @author khangpn
 */
@ServiceProvider(service=ITypeOfBillBN.class)
public class TypeOfBillBN extends AccessData<TypeOfBill> implements ITypeOfBillBN {

    public TypeOfBillBN() {
        setAccessData(PersistenceUltility.getEMF(), TypeOfBill.class);
    }
    
}

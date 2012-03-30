/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.erm.enterprise.dao;

import vn.com.hkt.pilot.entities.Enterprise;
import java.util.List;
import org.openide.util.lookup.ServiceProvider;
import vn.com.hkt.basic.api.IEnterpriseBN;
import vn.com.hkt.basic.jpa.PersistenceUltility;
import vn.com.hkt.pilot.identity.access.spi.AccessData;

/**
 *
 * @author khangphamngoc
 */
@ServiceProvider(service = IEnterpriseBN.class)
public class EnterpriseBN extends AccessData<Enterprise> implements IEnterpriseBN {
    
    public EnterpriseBN() {
        setAccessData(PersistenceUltility.getEMF(), Enterprise.class);
    }

    @Override
    public List<Enterprise> filterEnterpriseByID(String id) {
        return filter(Enterprise.FILED_ENTERPRISE_ID, id);
    }

    @Override
    public List<Enterprise> filterEnterpriseByName(String name) {
        return filter(Enterprise.FILED_ENTERPRISE_NAME, name);
    }

    @Override
    public List<Enterprise> filterEnterpriseByChild(Enterprise enterprise) {
        return filter(Enterprise.FILED_ENTERPRISE_PARENT_ID_ACTUAL, String.valueOf(enterprise.getId()));
    }
}

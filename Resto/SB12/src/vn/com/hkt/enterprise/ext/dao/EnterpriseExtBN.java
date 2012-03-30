/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.enterprise.ext.dao;

import java.util.List;
import vn.com.hkt.enterprise.ext.entity.EnterpriseExt;
import vn.com.hkt.generic.jpas.utils.PersistenceUtility;
import vn.com.hkt.pilot.identity.access.spi.AccessData;

/**
 *
 * @author khanguct
 */
public class EnterpriseExtBN extends AccessData<EnterpriseExt> {

    public EnterpriseExtBN() {
        setAccessData(PersistenceUtility.getEmf(), EnterpriseExt.class);        
    }

    public List<EnterpriseExt> filterEnterpriseExtByID(String id) {
        return filter(EnterpriseExt.FIELD_ENTERPRISEEXT_ID_ACTUAL, id);
    }

    public List<EnterpriseExt> filterEnterpriseExtByName(String name) {
        return filter(EnterpriseExt.FIELD_ENTERPRISEEXT_NAME, name);
    }
}

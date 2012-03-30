/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.pilot.subenterprise.dao;

import java.util.ArrayList;
import java.util.List;
import vn.com.hkt.pilot.identity.access.spi.AccessData;
import vn.com.hkt.pilot.sbenterprise.entity.SubEnterprise;
import vn.com.hkt.pilot.subenterprise.jpa.util.SubEnterpriseJPAUtil;

/**
 *
 * @author khangpn
 */
public class SubEnterpriseBN extends AccessData<SubEnterprise> {

    public SubEnterpriseBN() {
        setAccessData(SubEnterpriseJPAUtil.getEmf(), SubEnterprise.class);
    }

    public SubEnterprise getSubEnterpriseByID(String id) {
        List<SubEnterprise> subEnterprises = new ArrayList<SubEnterprise>();
        subEnterprises =  select(SubEnterprise.FIELD_ENTERPRISE_ID_ACTUAL, id);
        return subEnterprises.size()>0?subEnterprises.get(0):null;
    }   
}

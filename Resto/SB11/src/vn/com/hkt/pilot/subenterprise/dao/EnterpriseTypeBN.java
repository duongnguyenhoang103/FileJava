/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.pilot.subenterprise.dao;

import vn.com.hkt.pilot.identity.access.spi.AccessData;
import vn.com.hkt.pilot.sbenterprise.entity.EnterpriseType;
import vn.com.hkt.pilot.subenterprise.jpa.util.SubEnterpriseJPAUtil;


/**
 *
 * @author BinLe
 */
public class EnterpriseTypeBN extends AccessData<EnterpriseType> {

    public EnterpriseTypeBN() {
        setAccessData(SubEnterpriseJPAUtil.getEmf(), EnterpriseType.class);
    }
}

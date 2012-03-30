/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.pilot.subenterprise.dao;

import vn.com.hkt.pilot.identity.access.spi.AccessData;
import vn.com.hkt.pilot.sbenterprise.entity.BusinessArea;
import vn.com.hkt.pilot.subenterprise.jpa.util.SubEnterpriseJPAUtil;

/**
 *
 * @author khangpn
 */
public class BusinessAreaBN extends AccessData<BusinessArea> {

    public BusinessAreaBN() {
        setAccessData(SubEnterpriseJPAUtil.getEmf(), BusinessArea.class);
    }

   
}

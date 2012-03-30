/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.pilot.sb25.dao;

import vn.com.hkt.pilot.identity.access.spi.AccessData;
import vn.com.hkt.pilot.sb25.jpa.util.SubPersonSB25JPAUtil;
import vn.com.hkt.pilot.sb25.subpersonsb25.entity.Publication;

/**
 *
 * @author khangpn
 */
public class PublicationBN extends AccessData<Publication> {
    
    public PublicationBN() {
        setAccessData(SubPersonSB25JPAUtil.getEmf(), Publication.class);
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.pilot.subpersonsb23.dao;

import java.util.ArrayList;
import java.util.List;
import org.openide.util.Lookup;
import vn.com.hkt.basic.api.IPersonBN;
import vn.com.hkt.pilot.entities.Enterprise;
import vn.com.hkt.pilot.entities.Person;
import vn.com.hkt.pilot.identity.access.spi.AccessData;
import vn.com.hkt.pilot.sb23.entity.SubPersonSB23;
import vn.com.hkt.pilot.sb23.jpa.util.SubPersonSB23JPAUtil;

/**
 *
 * @author khangpn
 */
public class SubPersonSB23BN extends AccessData<SubPersonSB23> {

    private IPersonBN pbn = Lookup.getDefault().lookup(IPersonBN.class);

    public SubPersonSB23BN() {
        setAccessData(SubPersonSB23JPAUtil.getEmf(), SubPersonSB23.class);
    }

    public List<SubPersonSB23> getByEnterprise(Enterprise e) {
        List<SubPersonSB23> list = selectAll();
        List<SubPersonSB23> lr = new ArrayList<SubPersonSB23>();
        for (int i = 0; i < list.size(); i++) {
            Person p = pbn.getById(list.get(i).getPersonIdActual());
            if (p.getEnterpriseIdActual() == e.getId()) {
                lr.add(list.get(i));
            }
        }
        return lr;
    }
}

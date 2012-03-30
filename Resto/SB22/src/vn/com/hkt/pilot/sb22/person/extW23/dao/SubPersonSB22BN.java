/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.pilot.sb22.person.extW23.dao;

import java.util.ArrayList;
import java.util.List;
import org.openide.util.Lookup;
import vn.com.hkt.basic.api.IPersonBN;
import vn.com.hkt.pilot.entities.Enterprise;
import vn.com.hkt.pilot.entities.Person;
import vn.com.hkt.pilot.sb22.person.extW23.entity.SubPersonSB22;
import vn.com.hkt.pilot.identity.access.spi.AccessData;
import vn.com.hkt.pilot.sb22.generic.jpas.utils.SubPersonSB22JPAUtil;

/**
 *
 * @author duong
 */
public class SubPersonSB22BN extends AccessData<SubPersonSB22> {

    private IPersonBN pbn = Lookup.getDefault().lookup(IPersonBN.class);

    public SubPersonSB22BN() {
        setAccessData(SubPersonSB22JPAUtil.getEmf(), SubPersonSB22.class);
    }

    public List<SubPersonSB22> getByEnterprise(Enterprise e) {
        List<SubPersonSB22> list = selectAll();
        List<SubPersonSB22> lr = new ArrayList<SubPersonSB22>();
        for (int i = 0; i < list.size(); i++) {
            Person p = pbn.getById(list.get(i).getPersonIdActual());
            if (p.getEnterpriseIdActual() == e.getId()) {
                lr.add(list.get(i));
            }
        }
        return lr;
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.pilot.subperson.dao;

import java.util.ArrayList;
import java.util.List;
import org.openide.util.Lookup;
import vn.com.hkt.basic.api.IPersonBN;

import vn.com.hkt.pilot.entities.Enterprise;
import vn.com.hkt.pilot.entities.Person;
import vn.com.hkt.pilot.identity.access.spi.AccessData;
import vn.com.hkt.pilot.subperson.entity.SubPerson;
import vn.com.hkt.pilot.subperson.generic.api.ISubPersonGenericAPI;
import vn.com.hkt.pilot.subperson.jpa.util.SubPersonJPAUtil;

/**
 *
 * @author khangpn
 */
public class SubPersonBN extends AccessData<SubPerson> {

    private ISubPersonGenericAPI mydao;
    private IPersonBN pbn = (IPersonBN) Lookup.getDefault().lookup(IPersonBN.class);

    public SubPersonBN() {
        setAccessData(SubPersonJPAUtil.getEmf(), SubPerson.class);
    }

    public List<SubPerson> getByEnterpriser(Enterprise e) {
        List<SubPerson> list = selectAll();
        List<SubPerson> lr = new ArrayList<SubPerson>();
        for (int i = 0; i < list.size(); i++) {
            Person p = pbn.getById(list.get(i).getPersonIdActual());
            try {
                if (p.getEnterpriseIdActual() == e.getId()) {
                    lr.add(list.get(i));
                }
            } catch (Exception ex) {
            }

        }
        return lr;
    }
}

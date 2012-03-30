/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.pilot.subperson;

import javax.persistence.EntityManager;
import org.openide.modules.ModuleInstall;
import vn.com.hkt.pilot.subperson.jpa.util.SubPersonJPAUtil;

public class Installer extends ModuleInstall {

    public static final String MODULE_NAME = "SB21";

    @Override
    public void restored() {
        EntityManager em = SubPersonJPAUtil.getEmf().createEntityManager();
        em.getTransaction().begin();
        em.getTransaction().commit();
    }
}

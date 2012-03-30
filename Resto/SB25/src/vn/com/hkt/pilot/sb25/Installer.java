/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.pilot.sb25;

import javax.persistence.EntityManager;
import org.openide.modules.ModuleInstall;
import vn.com.hkt.pilot.sb25.jpa.util.SubPersonSB25JPAUtil;

public class Installer extends ModuleInstall {

    public static final String MODULE_NAME = "SB25";

    @Override
    public void restored() {
        EntityManager em = SubPersonSB25JPAUtil.getEmf().createEntityManager();
        em.getTransaction().begin();
        em.getTransaction().commit();
    }
}

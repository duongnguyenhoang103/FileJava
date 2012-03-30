/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.pilot.sb23;

import javax.persistence.EntityManager;
import org.openide.modules.ModuleInstall;
import vn.com.hkt.pilot.sb23.jpa.util.SubPersonSB23JPAUtil;

public class Installer extends ModuleInstall {

    public static final String MODULE_NAME = "SB23";

    @Override
    public void restored() {
        EntityManager em = SubPersonSB23JPAUtil.getEmf().createEntityManager();
        em.getTransaction().begin();
        em.getTransaction().commit();
    }
}

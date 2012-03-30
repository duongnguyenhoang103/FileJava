/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.pilot.sb24;

import javax.persistence.EntityManager;
import org.openide.modules.ModuleInstall;
import vn.com.hkt.pilot.sb24.jpa.util.SubPersonSB24JPAUtil;

public class Installer extends ModuleInstall {

    public static final String MODULE_NAME = "SB24";

    @Override
    public void restored() {
        EntityManager em = SubPersonSB24JPAUtil.getEmf().createEntityManager();
        em.getTransaction().begin();
        em.getTransaction().commit();
    }
}

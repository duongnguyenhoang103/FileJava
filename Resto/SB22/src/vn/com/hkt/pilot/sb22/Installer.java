/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.pilot.sb22;

import javax.persistence.EntityManager;
import org.openide.modules.ModuleInstall;
import vn.com.hkt.pilot.sb22.generic.jpas.utils.SubPersonSB22JPAUtil;

public class Installer extends ModuleInstall {

    public static final String MODULE_NAME = "SB22";

    @Override
    public void restored() {
        EntityManager em = SubPersonSB22JPAUtil.getEmf().createEntityManager();
        em.getTransaction().begin();
        em.getTransaction().commit();
    }
}

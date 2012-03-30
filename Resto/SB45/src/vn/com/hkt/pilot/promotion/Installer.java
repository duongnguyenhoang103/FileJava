/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.pilot.promotion;

import javax.persistence.EntityManager;
import org.openide.modules.ModuleInstall;
import vn.com.hkt.pilot.promotion.jpa.utils.jpaUtils;

public class Installer extends ModuleInstall {

    public static final String MODULE_NAME = "SB45";
    
    @Override
    public void restored() {
        EntityManager em = jpaUtils.getEmf().createEntityManager();
        em.getTransaction().begin();
        em.getTransaction().commit();
    }
}

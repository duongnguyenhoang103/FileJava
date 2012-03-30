/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.pilot.subenterprise13;

import javax.persistence.EntityManager;
import org.openide.modules.ModuleInstall;
import vn.com.hkt.pilot.subenterprise13.jpa.util.SubEnterprise13JPAUtil;

public class Installer extends ModuleInstall {

    public static final String MODULE_NAME = "SB13";

    @Override
    public void restored() {       
        EntityManager em = SubEnterprise13JPAUtil.getEmf().createEntityManager();
        em.getTransaction().begin();
        em.getTransaction().commit();
    }
}

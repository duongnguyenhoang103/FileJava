/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.pilot.sbenterprise;

import javax.persistence.EntityManager;
import org.openide.modules.ModuleInstall;
import vn.com.hkt.pilot.subenterprise.jpa.util.SubEnterpriseJPAUtil;

public class Installer extends ModuleInstall {

    public static final String MODULE_NAME = "SB11";

    @Override
    public void restored() {
        EntityManager em = SubEnterpriseJPAUtil.getEmf().createEntityManager();
        em.getTransaction().begin();
        em.getTransaction().commit();
    }
}

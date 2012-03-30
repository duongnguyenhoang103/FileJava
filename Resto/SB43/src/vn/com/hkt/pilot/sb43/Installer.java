/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.pilot.sb43;

import javax.persistence.EntityManager;
import org.openide.modules.ModuleInstall;
import vn.com.hkt.pilot.sb43.generic.jpas.utils.PersistenceUtility;

public class Installer extends ModuleInstall {

    public static final String MODULE_NAME = "sb43";

    @Override
    public void restored() {
        EntityManager em = PersistenceUtility.getEmf().createEntityManager();
        em.getTransaction().begin();
        em.getTransaction().commit();
    }
}

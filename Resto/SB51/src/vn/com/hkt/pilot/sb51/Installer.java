/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.pilot.sb51;

import javax.persistence.EntityManager;
import org.openide.modules.ModuleInstall;
import vn.com.hkt.pilot.sb51.jpa.PersistenceUltility;

public class Installer extends ModuleInstall {

    public static String MODULE_NAME = "SB51";

    @Override
    public void restored() {
        EntityManager em = PersistenceUltility.getEMF().createEntityManager();
        em.getTransaction().begin();
        em.getTransaction().commit();
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.history;

import javax.persistence.EntityManager;
import org.openide.modules.ModuleInstall;
import vn.com.hkt.pilot.history.jpa.JPAUtility;


public class Installer extends ModuleInstall {

    public static final String MODULE_NAME = "history";

    @Override
    public void restored() {
        EntityManager em = JPAUtility.getEMF().createEntityManager();
        em.getTransaction().begin();
        em.getTransaction().commit();
    }
}

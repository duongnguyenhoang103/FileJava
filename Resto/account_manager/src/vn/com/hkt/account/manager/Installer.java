/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.account.manager;

import javax.persistence.EntityManager;
import org.openide.modules.ModuleInstall;
import vn.com.hkt.account.manager.utilities.PersistenceUtitlity;


public class Installer extends ModuleInstall {

    public static final String MODULE_NAME = "account_manager";

    @Override
    public void restored() {
        EntityManager em = PersistenceUtitlity.getEMF().createEntityManager();
        em.getTransaction().begin();
        em.getTransaction().commit();
    }
}

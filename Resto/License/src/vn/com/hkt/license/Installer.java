/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.license;

import javax.persistence.EntityManager;
import org.openide.modules.ModuleInstall;
import vn.com.hkt.license.utilities.PersistenceUtitlity;

public class Installer extends ModuleInstall {
    /**
     * Tên của module license
     */
    public static String MODULE_NAME = "License";

    /**
     * Kiểm tra module đã được đăng kí sử dụng chưa
     */
    @Override
    public void restored() {
        EntityManager em = PersistenceUtitlity.getEMF().createEntityManager();
        em.getTransaction().begin();
        em.getTransaction().commit();
        // TODO        
//        if (!Activity.getActivity().checKeyInstall(MODULE_NAME)) {
//            License.getFrame(MODULE_NAME).setVisible(true);
//        }      
    }
}

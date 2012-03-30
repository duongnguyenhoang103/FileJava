/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.basic;

import java.awt.Frame;
import javax.persistence.EntityManager;
import javax.swing.SwingUtilities;
import org.openide.modules.ModuleInstall;
import org.openide.windows.WindowManager;
import vn.com.hkt.basic.jpa.PersistenceUltility;
import vn.com.hkt.basic.toolbar.BasicToolbarManager;

public class Installer extends ModuleInstall {

    public static final String MODULE_NAME = "basic";

    @Override
    public void restored() {
        EntityManager em=PersistenceUltility.getEMF().createEntityManager();
        em.getTransaction().begin();
        em.getTransaction().commit();
        
//        if (!AuthenticateManager.getAuthenticateManager().checkLogined()) {
//            AuthenticateManager.getAuthenticateManager().login();
//        }                
        BasicToolbarManager.getToolbarManager().createToolbar();
        WindowManager.getDefault().invokeWhenUIReady(new Runnable() {

            public void run() {
                SwingUtilities.invokeLater(new Runnable() {

                    public void run() {                        
                        Frame mainWindow = WindowManager.getDefault().getMainWindow();
                        mainWindow.dispose();
                        mainWindow.setUndecorated(true);
                        mainWindow.setExtendedState(Frame.MAXIMIZED_BOTH);
                        mainWindow.setVisible(true);                        
                    }
                });
            }
        });

    }
//
//    @Override
//    public void close() {
//        BasicToolbarManager.getBasicToolbar().getTabToolbar(ISystemToolbar.tabIndex).save();
//    }
//    
}
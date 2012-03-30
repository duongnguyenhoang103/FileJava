/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.basic.toolbar;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.SwingUtilities;
import org.openide.windows.WindowManager;
import vn.com.hkt.basic.toolbar.api.IBasicToolbar;
import vn.com.hkt.basic.toolbar.gui.BasicToolbar;
import vn.com.hkt.basic.toolbar.layout.MyRootPaneLayout;

/**
 *
 * @author Admin
 */
public class BasicToolbarManager {

    private JComponent toolbar;

    private BasicToolbarManager() {
    }
    private static BasicToolbarManager toolbarManager;

    public static BasicToolbarManager getToolbarManager() {
        if (toolbarManager == null) {
            toolbarManager = new BasicToolbarManager();
        }
        return toolbarManager;
    }

    public static IBasicToolbar getBasicToolbar() {
        return (IBasicToolbar) toolbarManager.toolbar;
    }

    public void createToolbar() {
//        JOptionPane.showMessageDialog(null,"OK T2 3");
        System.setProperty("netbeans.winsys.no_toolbars", "true");
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                JFrame frame = (JFrame) WindowManager.getDefault().getMainWindow();
                toolbar = BasicToolbar.getBasicToolbar();
                frame.getRootPane().setLayout(new MyRootPaneLayout(toolbar));
                toolbar.putClientProperty(JLayeredPane.LAYER_PROPERTY, 0);
                frame.getRootPane().getLayeredPane().add(toolbar, 0);
            }
        });
    }
}

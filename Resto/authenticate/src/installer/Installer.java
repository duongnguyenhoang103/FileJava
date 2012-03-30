/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package installer;

import org.openide.modules.ModuleInstall;
import vn.com.hkt.authenticate.manager.tools.AuthenticateManager;

public class Installer extends ModuleInstall {

    @Override
    public void restored() {
        AuthenticateManager.getAuthenticateManager().login();
    }
}

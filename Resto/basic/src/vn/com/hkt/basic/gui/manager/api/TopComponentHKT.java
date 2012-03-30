/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.basic.gui.manager.api;

import org.openide.windows.TopComponent;
import vn.com.hkt.pilot.identity.presentation.api.ITopComponent;

/**
 *
 * @author Admin
 */
public abstract class TopComponentHKT extends TopComponent implements ITopComponent {
    
    @Override
    public void componentOpened() {
      //  TopcomponentManager.getTopcomponentManager().setTopComponentShowing(this);    
//        /Activity.getActivity().checKeyInstall(TOOL_TIP_TEXT_KEY);
    }       
}

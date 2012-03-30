/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.basic.gui.manager.spi;

import vn.com.hkt.pilot.identity.presentation.api.ITopComponent;

/**
 *
 * @author Admin
 */
public class TopcomponentManager {

    private ITopComponent topComponentShowing;
    private static TopcomponentManager topcomponentManager;

    public static TopcomponentManager getTopcomponentManager() {
        if (topcomponentManager == null) {
            topcomponentManager = new TopcomponentManager();
        }
        return topcomponentManager;
    }

    public ITopComponent getTopComponent() {
        return topComponentShowing;
    }

    public void setTopComponentShowing(ITopComponent topComponent) {
        topComponentShowing = topComponent;
    }
}

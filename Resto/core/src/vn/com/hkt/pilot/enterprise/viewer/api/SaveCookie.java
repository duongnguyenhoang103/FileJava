/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.pilot.enterprise.viewer.api;

import java.io.IOException;
import org.openide.nodes.Node.Cookie;

/**
 *
 * @author VietAnh
 */
public interface SaveCookie extends Cookie {
    public void SaveCookie() throws IOException;
    public boolean checkValidate();
    
}

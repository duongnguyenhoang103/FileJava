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
public interface RemoveCookieDepartment extends Cookie{
     public void remove() throws IOException;
     
     public void removeObject(int i) throws IOException;
}
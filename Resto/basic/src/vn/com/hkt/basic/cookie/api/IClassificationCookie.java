/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.basic.cookie.api;

import org.openide.nodes.Node.Cookie;
import vn.com.hkt.pilot.entities.Classification;

/**
 *
 * @author khangpn
 */
public interface IClassificationCookie extends Cookie{
    
    public Classification getClassification();
    
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.pilot.promotion.viewer.api;

import org.openide.nodes.Node.Cookie;

/**
 *
 * @author khangpn
 */
public interface PromotionFilterCookie extends Cookie{
    public void filterTable(String fieldName, String value);
}

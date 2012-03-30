/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.basic.api;

import java.util.List;
import vn.com.hkt.pilot.entities.Classification;
import vn.com.hkt.pilot.identity.access.api.IAccessData;

/**
 *
 * @author duong
 */
public interface IClassificationBN extends IAccessData<Classification> {

    public List<Classification> filterByClassificationName(String name);
}

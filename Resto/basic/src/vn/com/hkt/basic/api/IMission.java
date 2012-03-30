/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.basic.api;

import java.util.List;
import vn.com.hkt.pilot.entities.Mission;
import vn.com.hkt.pilot.identity.access.api.IAccessData;

/**
 *
 * @author khanguct
 */
public interface IMission extends IAccessData<Mission> {
    
    public List<Mission> filterMissionByID(String id);
    
    public List<Mission> filterMissionByName(String name);    
}

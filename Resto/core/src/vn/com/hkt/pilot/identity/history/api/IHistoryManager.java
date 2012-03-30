/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.pilot.identity.history.api;

import vn.com.hkt.pilot.identity.entitiy.api.IEntity;

/**
 *
 * @author Admin
 */
public interface IHistoryManager {

    public void checkInsertHistory(IEntity e);

    public void checkUpdateHistory(IEntity e);

    public void checkDeleteHistory(IEntity e);
    
    public void insertHistory(IEntity e);
}

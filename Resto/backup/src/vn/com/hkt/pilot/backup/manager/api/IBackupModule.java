/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.pilot.backup.manager.api;

import java.util.Hashtable;

/**
 *
 * @author BinLe
 */
public interface IBackupModule {

    public void setHashId(Hashtable<String, Hashtable<Integer, Integer>> hashID);

    public void backupAll();
}

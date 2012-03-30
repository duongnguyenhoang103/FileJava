/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.pilot.backup.manager;

import org.h2.tools.RunScript;
import org.h2.tools.Script;
import org.openide.util.Exceptions;

/**
 *
 * @author BinLe
 */
public class BackupSQL {

    private String urlJDBC = "jdbc:h2:tcp://localhost/~/EnterpriseManager";
    private String username = "sa";
    private String password = "";
    private String fileName = "data.hkt";

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUrlJDBC(String urlJDBC) {
        this.urlJDBC = urlJDBC;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public BackupSQL(String urlJDBC, String username, String password, String fileName) {
        this.urlJDBC = urlJDBC;
        this.username = username;
        this.password = password;
        this.fileName = fileName;
    }

    public BackupSQL() {
    }

    public boolean backup() {
        try {
            Script.execute(urlJDBC, username, password, fileName);
            return true;
        } catch (Exception e) {
            Exceptions.printStackTrace(e);
            return false;
        }

    }

    public boolean restore() {
        try {
            RunScript.execute(urlJDBC, username, password, fileName, null, true);
            return true;
        } catch (Exception e) {
            Exceptions.printStackTrace(e);
            return false;
        }
    }
}

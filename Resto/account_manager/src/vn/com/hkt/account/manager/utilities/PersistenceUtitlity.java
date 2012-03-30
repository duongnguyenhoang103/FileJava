/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.account.manager.utilities;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;

/**
 *
 * @author Admin 
 */
public class PersistenceUtitlity {

    private static String persistenceString = "Account_Manager";
    private static EntityManagerFactory emf;

    public static EntityManagerFactory getEMF() {
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory(persistenceString);
        }        
        return emf;
    }

    public static String getPersistenceString() {
        return persistenceString;
    }
}

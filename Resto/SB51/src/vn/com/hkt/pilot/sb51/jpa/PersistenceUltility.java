/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.pilot.sb51.jpa;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author longnt
 */
public class PersistenceUltility {

    private static final String persistenceString = "SB51";
    private static EntityManagerFactory emf;

    public static EntityManagerFactory getEMF() {
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory(persistenceString);
        }
        return emf;
    }
}

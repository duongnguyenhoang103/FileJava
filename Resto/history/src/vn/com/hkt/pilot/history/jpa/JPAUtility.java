/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.pilot.history.jpa;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Admin
 */
public class JPAUtility {    

    public static EntityManagerFactory getEMF() {
        return Persistence.createEntityManagerFactory("History");
    }
}

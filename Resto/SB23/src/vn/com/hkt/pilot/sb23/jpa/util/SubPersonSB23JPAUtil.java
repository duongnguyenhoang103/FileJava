package vn.com.hkt.pilot.sb23.jpa.util;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author khangpn
 */
public class SubPersonSB23JPAUtil {
    
    private static EntityManagerFactory emf;

    public static EntityManagerFactory getEmf() {
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory("SubPersonSB23Services");
        }
        return emf;
    }
    
    
}

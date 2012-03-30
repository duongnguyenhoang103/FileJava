/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.pilot.calendaroption.jpa.utils;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author khangphamngoc
 */
public class PersistenceUtility {
    
    private static EntityManagerFactory emf;    
 
    public static  EntityManagerFactory getEmf() {
        if(emf==null){
            emf = Persistence.createEntityManagerFactory("CalendarService");
        }
        return emf;
    }    
}

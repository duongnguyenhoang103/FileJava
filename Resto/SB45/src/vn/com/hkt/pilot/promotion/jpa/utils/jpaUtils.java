/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.pilot.promotion.jpa.utils;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author khangphamngoc
 */
public class jpaUtils {
    
    private static EntityManagerFactory emf;
    public  static EntityManagerFactory getEmf() {
        if(emf==null){
            emf = Persistence.createEntityManagerFactory("PromotionService");
        }
        return emf;
    }
    
}

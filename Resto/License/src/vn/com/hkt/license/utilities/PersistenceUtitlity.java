/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.license.utilities;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * JPA 
 */
public class PersistenceUtitlity {

    private static String persistenceString = "License";
    private static EntityManagerFactory emf;

    /**
     * lấy factory của module để kết nối cơ sở dữ liệu
     * @return 
     */
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

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.generic.jpas.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author duong
 */
public class jpaUtils_extProductW48 {

    private EntityManagerFactory emf;
    private EntityManager em;

    public EntityManagerFactory getEmf() {
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory("ProductExtService_W48");
        }
        return emf;
    }

    public EntityManager getEm() {
        return this.em = getEmf().createEntityManager();
    }
}

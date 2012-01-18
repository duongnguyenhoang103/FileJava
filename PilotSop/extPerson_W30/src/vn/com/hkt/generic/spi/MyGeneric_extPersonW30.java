/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.generic.spi;

import vn.com.hkt.generic.jpas.utils.jpaUtils_extPersonW30;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import org.openide.util.lookup.ServiceProvider;
import vn.com.hkt.generic.api.IGenericAPI_extPersonW30;

/**
 *
 * @author duong
 */
@ServiceProvider(service = IGenericAPI_extPersonW30.class)
public class MyGeneric_extPersonW30 implements IGenericAPI_extPersonW30 {

    private EntityManager em;
    private jpaUtils_extPersonW30 jpu = new jpaUtils_extPersonW30();

    public MyGeneric_extPersonW30() {
    }

    @Override
    public boolean insertData(Object ob) {
        em = jpu.getEm();
        em.getTransaction().begin();
        try {
            em.persist(ob);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            em.close();
        }
    }

    @Override
    public boolean updateData(Object ob) {
        em = jpu.getEm();
        em.getTransaction().begin();
        try {
            em.merge(ob);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            em.close();
        }
    }

    @Override
    public boolean deleteData(Class T, String id) {
        em = jpu.getEm();
        em.getTransaction().begin();
        try {
            Object ob = em.find(T, id);
            em.remove(ob);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            em.close();
        }
    }

    @Override
    public boolean removeData(Class T, int id) {
        em = jpu.getEm();
        em.getTransaction().begin();
        try {
            Object ob = em.find(T, id);
            em.remove(ob);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            em.close();
        }
    }

    @Override
    public List<Object> getAllData(Class T) {
        List<Object> list = new ArrayList<Object>();
        em = jpu.getEm();
        try {
            list = em.createQuery(" elect ob form " + T.getSimpleName() + "ob").getResultList();
        } catch (Exception e) {
        } finally {
            em.close();
        }
        return list;
    }

    @Override
    public Object getByID(Class T, String id) {
        Object object = null;
        em = jpu.getEm();
        em.getTransaction().begin();
        try {
            object = em.find(T, id);
        } catch (Exception e) {
        } finally {
            em.close();
        }
        return object;
    }

    @Override
    public Object getByID(Class T, int id) {
        Object object = null;
        em = jpu.getEm();
        em.getTransaction().begin();
        try {
            object = em.find(T, id);
        } catch (Exception e) {
        } finally {
            em.close();
        }
        return object;
    }

    @Override
    public List<Object> SearchByCondition(Class T, String field, String key) {
        List<Object> list = new ArrayList<Object>();
        String searchkey = "%" + key.toLowerCase() + "%";
        String sql = "Select tbl from " + T.getSimpleName() + " tbl where lower(tbl." + field
                + ") like '" + searchkey + "'";
        em = jpu.getEm();
        try {
            list = em.createQuery(sql).getResultList();
        } catch (Exception ex) {
        } finally {
            em.close();
        }
        return list;
    }

    @Override
    public List<Object> SearchByQuery(String sql) {
          List<Object> list = new ArrayList<Object>();
        em = jpu.getEm();
        try {
            list = em.createQuery(sql).getResultList();
        } catch (Exception ex) {
        } finally {
            em.close();
        }
        return list;
    }
}

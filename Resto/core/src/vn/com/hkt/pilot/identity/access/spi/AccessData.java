/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.pilot.identity.access.spi;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.swing.JOptionPane;
import org.openide.util.Exceptions;
import org.openide.util.Lookup;
import vn.com.hkt.pilot.identity.access.api.IAccessData;
import vn.com.hkt.pilot.identity.entitiy.api.IEntity;
import vn.com.hkt.pilot.identity.history.api.IHistoryManager;

/**
 *
 * @author Lê Xuân Bách
 */
public class AccessData<E> implements IAccessData<E> {

    private IHistoryManager historyManager;
    private EntityManagerFactory emf;
    private EntityManager em;
    private Class typeClass;

    @Override
    public void setAccessData(EntityManagerFactory emf, Class typeClass) {
        this.emf = emf;
        this.typeClass = typeClass;
        if (!"Reference".equals(typeClass.getSimpleName()) && !"Modification".equals(typeClass.getSimpleName())) {
            historyManager = Lookup.getDefault().lookup(IHistoryManager.class);
        }
    }

    @Override
    public boolean insert(E tblj) {
        try {
            em = emf.createEntityManager();
            em.getTransaction().begin();
            em.persist(tblj);
            em.getTransaction().commit();
            if (historyManager != null && (tblj instanceof IEntity)) {
                IEntity entity = (IEntity) tblj;
                historyManager.checkInsertHistory(entity);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            if (em.isOpen()) {
                em.close();
            }
        }
    }

    @Override
    public boolean update(E tblj) {
        try {
            IEntity e = (IEntity) tblj;
            IEntity eo = (IEntity) e.getAccessDataOfEntity().getById(e.getId());
            if (eo == null) {
                return insert(tblj);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        try {
            if (historyManager != null && (tblj instanceof IEntity)) {
                IEntity entity = (IEntity) tblj;
                historyManager.checkUpdateHistory(entity);
            }
            em = emf.createEntityManager();
            em.getTransaction().begin();
            em.merge(tblj);
            em.getTransaction().commit();                           
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            if (em.isOpen()) {
                em.close();
            }
        }
    }

    @Override
    public boolean delete(E tblj) {
        try {
            if (historyManager != null && (tblj instanceof IEntity)) {
                IEntity entity = (IEntity) tblj;
                historyManager.checkDeleteHistory(entity);
            }
            em = emf.createEntityManager();
            em.getTransaction().begin();
            em.remove(tblj);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            if (em.isOpen()) {
                em.close();
            }
        }
    }

    @Override
    public boolean delete(int id) {
        try {
            if (historyManager != null) {
                IEntity entity = (IEntity) getById(id);
                historyManager.checkDeleteHistory(entity);
            }
            em = emf.createEntityManager();
            em.getTransaction().begin();
            em.remove(em.find(typeClass, id));
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            if (em.isOpen()) {
                em.close();
            }
        }
    }

    @Override
    public boolean delete(String id) {
        try {
            em = emf.createEntityManager();
            em.getTransaction().begin();
            em.remove(em.find(typeClass, id));
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            if (em.isOpen()) {
                em.close();
            }
        }
    }

    @Override
    public E getById(int id) {
        try {
            em = emf.createEntityManager();
            em.getTransaction().begin();
            E tblj = (E) em.find(typeClass, id);
            return tblj;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (em.isOpen()) {
                em.close();
            }
        }
    }

    @Override
    public List<Object> queryList(String query) {
        try {
            em = emf.createEntityManager();
            return em.createQuery(query).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return (List<Object>) new ArrayList<E>();
        } finally {
            if (em.isOpen()) {
                em.close();
            }
        }
    }

    @Override
    public E getByObjectId(String id) {
        IEntity entity = null;
        try {
            entity = (IEntity) typeClass.newInstance();
        } catch (Exception ex) {
            Exceptions.printStackTrace(ex);
        }
        if (entity == null) {
            return null;
        }
        String queryString = "Select tbl from " + typeClass.getSimpleName() + " tbl "
                + "where tbl." + entity.getFieldNameObjectId() + " = '" + id + "'";
        List<E> list = (List<E>) queryList(queryString);
        if (list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public List<E> selectAll() {
        String queryString = "Select tbl from " + typeClass.getSimpleName() + " tbl ";
        return (List<E>) queryList(queryString);
    }

    @Override
    public List<E> selectAll(String typeOrder, String fieldNameOrder) {
        String queryString = "Select tbl from " + typeClass.getSimpleName() + " tbl "
                + "order by tbl." + fieldNameOrder + " " + typeOrder;
        return (List<E>) queryList(queryString);
    }

    @Override
    public List<E> select(String fieldName, String wordSearch) {
        String queryString = "Select tbl from " + typeClass.getSimpleName() + " tbl "
                + "where tbl." + fieldName + " = '" + wordSearch + "'";
        return (List<E>) queryList(queryString);
    }

    @Override
    public List<E> select(String fieldName, String wordSearch, String typeOrder, String fieldNameOrder) {
        String queryString = "Select tbl from " + typeClass.getSimpleName() + " tbl "
                + " where tbl." + fieldName + " = '" + wordSearch + "' "
                + " order by tbl." + fieldNameOrder + " " + typeOrder;
        return (List<E>) queryList(queryString);
    }

    @Override
    public List<E> filter(String fieldName, String wordFilter) {
        String serchWord = "%" + wordFilter.toLowerCase() + "%";
        String queryString = "Select tbl from " + typeClass.getSimpleName() + " tbl "
                + "where lower(tbl." + fieldName + ") like '" + serchWord + "'";
        return (List<E>) queryList(queryString);
    }

    @Override
    public List<E> filter(String fieldName, String wordFilter, String typeOrder, String fieldNameOrder) {
        String serchWord = "%" + wordFilter.toLowerCase() + "%";
        String queryString = "Select tbl from " + typeClass.getSimpleName() + " tbl "
                + "where lower(tbl." + fieldName + ") like '" + serchWord + "'"
                + " oder by tbl." + fieldNameOrder + " " + typeOrder;
        return (List<E>) queryList(queryString);
    }

    private String createQuerySelect(List<String> fieldNames, List<String> values) {
        String queryString = "Select tbl from " + typeClass.getSimpleName() + " tbl ";
        if (fieldNames != null && values != null && fieldNames.size() == values.size()) {
            queryString += " where ";
            for (int i = 0; i < fieldNames.size(); i++) {
                queryString += " tbl." + fieldNames.get(i) + " = '" + values.get(i) + "' ";
                if (i < fieldNames.size() - 1) {
                    queryString += " and ";
                }
            }
        }
        return queryString;
    }

    @Override
    public List<E> select(List<String> fieldNames, List<String> wordSearchs) {
        String queryString = "Select tbl from " + typeClass.getSimpleName() + " tbl ";
        if (fieldNames != null && wordSearchs != null && fieldNames.size() == wordSearchs.size()) {
            queryString += " where ";
            for (int i = 0; i < fieldNames.size(); i++) {
                queryString += " tbl." + fieldNames.get(i) + " = '" + wordSearchs.get(i) + "' ";
                if (i < fieldNames.size() - 1) {
                    queryString += " and ";
                }
            }
        }
        return (List<E>) queryList(queryString);
    }

    @Override
    public List<E> select(List<String> fieldNames, List<String> wordSearchs, String typeOrder, String fieldNameOrder) {
        String queryString = "Select tbl from " + typeClass.getSimpleName() + " tbl ";
        if (fieldNames != null && wordSearchs != null && fieldNames.size() == wordSearchs.size()) {
            queryString += " where ";
            for (int i = 0; i < fieldNames.size(); i++) {
                queryString += " tbl." + fieldNames.get(i) + " = '" + wordSearchs.get(i) + "' ";
                if (i < fieldNames.size() - 1) {
                    queryString += " and ";
                }
            }
        }
        queryString += " order by tbl." + fieldNameOrder + " " + typeOrder;
        return (List<E>) queryList(queryString);
    }

    @Override
    public List<E> filter(List<String> fieldNames, List<String> wordFilters) {
        String queryString = "Select tbl from " + typeClass.getSimpleName() + " tbl ";
        if (fieldNames != null && wordFilters != null && fieldNames.size() == wordFilters.size()) {
            queryString += " where ";
            for (int i = 0; i < fieldNames.size(); i++) {
                String serchWord = "%" + wordFilters.get(i).toLowerCase() + "%";
                queryString += "lower(tbl." + fieldNames.get(i) + ") like '" + serchWord + "'";
                if (i < fieldNames.size() - 1) {
                    queryString += " and ";
                }
            }
        }
        return (List<E>) queryList(queryString);
    }

    @Override
    public List<E> filter(List<String> fieldNames, List<String> wordFilters, String typeOrder, String fieldNameOrder) {
        String queryString = "Select tbl from " + typeClass.getSimpleName() + " tbl ";
        if (fieldNames != null && wordFilters != null && fieldNames.size() == wordFilters.size()) {
            queryString += " where ";
            for (int i = 0; i < fieldNames.size(); i++) {
                String serchWord = "%" + wordFilters.get(i).toLowerCase() + "%";
                queryString += "lower(tbl." + fieldNames.get(i) + ") like '" + serchWord + "'";
                if (i < fieldNames.size() - 1) {
                    queryString += " and ";
                }
            }
        }
        queryString += " order by tbl." + fieldNameOrder + " " + typeOrder;
        return (List<E>) queryList(queryString);
    }

    @Override
    public List<E> selectOrCondition(List<String> fieldNames, List<String> wordSearchs) {
        String queryString = "Select tbl from " + typeClass.getSimpleName() + " tbl ";
        if (fieldNames != null && wordSearchs != null && fieldNames.size() == wordSearchs.size()) {
            queryString += " where ";
            for (int i = 0; i < fieldNames.size(); i++) {
                queryString += " tbl." + fieldNames.get(i) + " = '" + wordSearchs.get(i) + "' ";
                if (i < fieldNames.size() - 1) {
                    queryString += " or ";
                }
            }
        }
        return (List<E>) queryList(queryString);
    }
}

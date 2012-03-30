/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.pilot.sb22.generic.api;

import java.util.List;

/**
 *
 * @author duong
 */
public interface ISubPersonSB22 {

    public boolean insertData(Object ob);

    public boolean updateData(Object ob);

    public boolean deleteData(Class T, String id);

    public boolean removeData(Class T, int id);

    public List<Object> getAllData(Class T);

    public Object getByID(Class T, String id);

    public Object getByID(Class T, int id);

    public List<Object> SearchByCondition(Class T, String field, String key);
        
    public List<Object> SearchByQuery(String sql);

     public List<Object> filterByCondition(Class T, String field, String key);     

  

}

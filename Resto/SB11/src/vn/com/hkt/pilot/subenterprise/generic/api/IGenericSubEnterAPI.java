/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.pilot.subenterprise.generic.api;

import java.util.List;

/**
 *
 * @author khanguct
 */
public interface IGenericSubEnterAPI {

    public boolean insertData(Object ob);

    public boolean deleteData(Class T, int id);

    public boolean updateData(Object ob);

    public List<Object> getAllData(Class T);

    public Object getByIdentityID(Class T, int id);

    public List<Object> filterByCondition(Class T, String field, String key);
    
    public List<Object> getByCondition(Class T, String field, String key);
}

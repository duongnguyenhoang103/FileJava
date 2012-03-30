/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.pilot.identity.entitiy.api;

import vn.com.hkt.pilot.identity.access.api.IAccessData;

/**
 * Mô tả chung nhất củas đối tượng entity
 *
 * @author Lê Xuân bách
 */
public interface IEntity {

    /**
     * Lấy tên của entity này.
     */
    public String getEntityName();

    /**
     * Lấy tên moduel của entity này.
     */
    public String getModuleOfEntity();

    /**
     * Miêu tả sơ qua về entity này.
     */
    public String getEntityDescription();

    /**
     * Lấy inteface (API) của việc truy suất cơ sở dữ liệu đối với entity này.
     *
     * @return
     */
    public IAccessData getAccessDataOfEntity();

    /**
     * Lấy tên trường id kiểu string của Ientity do người dùng đặt
     */
    public String getFieldNameObjectId();

    /**
     * lấy về id interger của đối tượng
     *
     * @return
     */
    public int getId();

    public String getDataRealyOfField(String fieldName, String data);

    public String getDescriptionOfField(String fieldName);
}

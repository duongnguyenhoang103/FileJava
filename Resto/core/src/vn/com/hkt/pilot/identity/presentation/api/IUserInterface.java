/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.pilot.identity.presentation.api;

/**
 * Mô tả chung nhất của một giao diện sử dụng bất kì
 * @author Lê Xuân Bách
 */
public interface IUserInterface {
    /**
     * Lấy tên giao diện     
     */
    public String getUserInterfaceName();
    /**
     * Lấy mô tả chung về giao diện
     * @return 
     */
    public String getUserInterfaceDescription();
    /**
     * Lấy tên module của giao diện
     * @return 
     */
    public String getModuleName();
}

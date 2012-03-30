/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.pilot.identity.presentation.api;

/**
 *
 * @author Admin
 */
public interface ITopComponent extends IUserInterface {

    public void componentOpened();

    public void reLoadFont();

    public void reLoadColorTable();

    public void reLoadSize();

    public void reLoadColorWord();

    public void reLoadColorTitle();

    public void reLoadColorMouse();
}

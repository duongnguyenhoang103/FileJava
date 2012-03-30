/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.basic.toolbar.api;

import vn.com.hkt.pilot.entities.Enterprise;
import vn.com.hkt.pilot.entities.Person;

/**
 *
 * @author Admin
 */
public interface IBasicToolbar {

    public static final int ENTERPRISE = 1;
    public static final int PERSON = 2;

    public int getTypeList();

    public void loadEnterprise();

    public void setEnterprise(Enterprise enterprise);

    public Enterprise getEnterprise();

    public void loadPerson();

    public void setPerson(Person person);

    public Person getPerson();

    public void loadTabToolbar();

    public ITabToolbar getTabToolbar(int index);

    public void changeColor();
}

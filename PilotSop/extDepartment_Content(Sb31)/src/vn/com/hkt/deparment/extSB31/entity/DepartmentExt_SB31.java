/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.deparment.extSB31.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author duong
 */
@Entity
public class DepartmentExt_SB31 {

    @Id
    private String departmentID;
    private String parameters; //thông số  
  
    private String contents;//nội dung
  

    public String getDepartmentID() {
        return departmentID;
    }

    public void setDepartmentID(String departmentID) {
        this.departmentID = departmentID;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getParameters() {
        return parameters;
    }

    public void setParameters(String parameters) {
        this.parameters = parameters;
    }

    public DepartmentExt_SB31(String departmentID, String parameters, String contents) {
        this.departmentID = departmentID;
        this.parameters = parameters;
        this.contents = contents;
    }

  

    public DepartmentExt_SB31() {
        super();
    }

    @Override
    public String toString() {
        return departmentID;
    }
}

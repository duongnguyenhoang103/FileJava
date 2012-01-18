/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vn.hkt.core;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author duong
 */
@Entity
public class Reference {
      @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    private String typeObject;// loại đối tượng được lưu history
    private String stringObject;// id của đối tượng
    private String fieldNameObject;// tên trường thay đổi
    @OneToMany(cascade=CascadeType.REMOVE)// khi tham chiếu bị xóa, các sự thay đổi của tham chiếu cũng bị xóa
    private List<Modification> modifications;// danh sách sự thay đổi

    public String getFieldNameObject() {
        return fieldNameObject;
    }

    public void setFieldNameObject(String fieldNameObject) {
        this.fieldNameObject = fieldNameObject;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStringObject() {
        return stringObject;
    }

    public void setStringObject(String stringObject) {
        this.stringObject = stringObject;
    }
    
    public List<Modification> getModifications() {
        return modifications;
    }

    public void setModifications(List<Modification> modifications) {
        this.modifications = modifications;
    }

    public String getTypeObject() {
        return typeObject;
    }

    public void setTypeObject(String typeObject) {
        this.typeObject = typeObject;
    }
}

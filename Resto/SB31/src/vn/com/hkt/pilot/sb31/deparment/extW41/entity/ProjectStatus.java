/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.pilot.sb31.deparment.extW41.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.openide.util.lookup.ServiceProvider;
import vn.com.hkt.pilot.identity.access.api.IAccessData;
import vn.com.hkt.pilot.identity.entitiy.api.IEntity;
import vn.com.hkt.pilot.sb31.Installer;
import vn.com.hkt.pilot.sb31.department.extW41.dao.ProjectStatusBN;

/**
 * tình trạng dự án
 * @author BinLe
 */
@ServiceProvider(service = IEntity.class)
@Entity
public class ProjectStatus implements IEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String statusName;// tên tình trạng
    private String description;// miêu tả tình trạng

    public ProjectStatus() {
    }

    public ProjectStatus(String statusName, String description) {
        this.statusName = statusName;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    @Override
    public String toString() {
        return statusName;
    }

    @Override
    public String getEntityName() {
        return getClass().getSimpleName();
    }

    @Override
    public String getModuleOfEntity() {
        return Installer.MODULE_NAME;
    }

    @Override
    public String getEntityDescription() {
        return "Miêu tả tình trạng dự án";
    }

    @Override
    public IAccessData getAccessDataOfEntity() {
        return new ProjectStatusBN();
    }

    @Override
    public String getFieldNameObjectId() {
        return "id";
    }

    @Override
    public String getDataRealyOfField(String fieldName, String data) {
        return data;
    }

    @Override
    public String getDescriptionOfField(String fieldName) {
        return fieldName;
    }
}

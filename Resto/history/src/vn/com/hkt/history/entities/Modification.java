/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.history.entities;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.openide.util.lookup.ServiceProvider;
import vn.com.hkt.history.Installer;
import vn.com.hkt.history.spidao.ModificationDAO;
import vn.com.hkt.history.spidao.ReferenceDAO;
import vn.com.hkt.pilot.identity.access.api.IAccessData;
import vn.com.hkt.pilot.identity.entitiy.api.IEntity;

/**
 *
 * @author BinLe
 */
@ServiceProvider(service = IEntity.class)
@Entity
public class Modification implements IEntity {

    public static final String FIELD_DATA = "data";
    public static final String FIELD_DATE_START = "dateStart";
    public static final String FIELD_DATA_END = "dateEnd";
    public static final String FIELD_DATA_DESCRIPTION = "description";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String data;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateStart;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateEnd;
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getData() {
        return data;
    }

    public void setData(String Data) {
        this.data = Data;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getEntityName() {
        return this.getClass().getSimpleName();
    }

    @Override
    public String getModuleOfEntity() {
        return Installer.MODULE_NAME;
    }

    @Override
    public String getEntityDescription() {
        return "Miêu tả sự thay đổi của thuộc tính";
    }

    public Modification(String Data, Date dateStart, Date dateEnd, String description) {
        this.data = Data;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.description = description;
    }

    public Modification() {
    }

    @Override
    public IAccessData getAccessDataOfEntity() {
        return new ModificationDAO();
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
        if (fieldName.equals(FIELD_DATA)) {
            return "Dữ liệu";
        } else if (fieldName.equals(FIELD_DATA_DESCRIPTION)) {
            return "Mô tả dữ liệu";
        } else if (fieldName.equals(FIELD_DATA_END)) {
            return "Ngày bị thay đổi";
        } else if (fieldName.equals(FIELD_DATE_START)) {
            return "Ngày được thay đổi";
        } else {
            return null;
        }
    }
}

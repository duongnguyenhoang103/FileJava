/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.pilot.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.openide.util.lookup.ServiceProvider;
import vn.com.hkt.basic.Installer;
import vn.com.hkt.pilot.dialog.dao.CountryBN;
import vn.com.hkt.pilot.dialog.dao.PartitionBN;
import vn.com.hkt.pilot.identity.access.api.IAccessData;
import vn.com.hkt.pilot.identity.entitiy.api.IEntity;

/**
 *
 * @author khangpn
 */
@ServiceProvider(service = IEntity.class)
@Entity
public class Partition implements IEntity {

    public static final String FIELD_ID = "id";
    public static final String FIELD_PARTITION_ID = "partitionId";
    public static final String FIELD_COUNTRY_ID_ACTUAL = "countryIdActual";
    public static final String FIELD_PARTITION_NAME = "partitionName";
    public static final String FIELD_PARTITION_PARENT_ID_ACTUAL = "partitionParentIdActual";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String partitionId; // Ma vung
    private int countryIdActual; // Ma QG
    private String partitionName; // Ten vung
    private int partitionParentIdActual; // Ten vung cha

    public Partition() {
    }

    public Partition(String partitionId, int countryIdActual, String partitionName, int partitionParentIdActual) {
        this.partitionId = partitionId;
        this.countryIdActual = countryIdActual;
        this.partitionName = partitionName;
        this.partitionParentIdActual = partitionParentIdActual;
    }

    public int getCountryIdActual() {
        return countryIdActual;
    }

    public void setCountryIdActual(int countryIdActual) {
        this.countryIdActual = countryIdActual;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPartitionId() {
        return partitionId;
    }

    public void setPartitionId(String partitionId) {
        this.partitionId = partitionId;
    }

    public String getPartitionName() {
        return partitionName;
    }

    public void setPartitionName(String partitionName) {
        this.partitionName = partitionName;
    }

    public int getPartitionParentIdActual() {
        return partitionParentIdActual;
    }

    public void setPartitionParentIdActual(int partitionParentIdActual) {
        this.partitionParentIdActual = partitionParentIdActual;
    }

    @Override
    public String toString() {
        return partitionName;
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
        return "Mô tả thông tin cơ bản về vùng lãnh thổ";
    }

    @Override
    public IAccessData getAccessDataOfEntity() {
        return new PartitionBN();
    }

    @Override
    public String getFieldNameObjectId() {
        return FIELD_PARTITION_ID;
    }

    @Override
    public String getDataRealyOfField(String fieldName, String data) {
        String dt = data;
        try {
            if (fieldName.equals(FIELD_COUNTRY_ID_ACTUAL)) {
                dt = new CountryBN().getById(Integer.valueOf(data)).toString();
            } else if (fieldName.equals(FIELD_PARTITION_PARENT_ID_ACTUAL)) {
                dt = new PartitionBN().getById(Integer.valueOf(data)).toString();
            }
        } catch (Exception ex) {
        }
        return dt;
    }

    @Override
    public String getDescriptionOfField(String fieldName) {
        if (fieldName.equals(FIELD_COUNTRY_ID_ACTUAL)) {
            return "Quốc gia";
        } else if (fieldName.equals(FIELD_PARTITION_ID)) {
            return "Mã vùng,khu vực";
        } else if (fieldName.equals(FIELD_PARTITION_NAME)) {
            return "Tên vùng,khu vực";
        } else if (fieldName.equals(FIELD_PARTITION_PARENT_ID_ACTUAL)) {
            return "Khu vực cha";
        }
        return null;
    }
}

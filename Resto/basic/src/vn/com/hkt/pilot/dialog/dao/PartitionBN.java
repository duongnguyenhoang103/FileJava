/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.pilot.dialog.dao;

import java.util.List;
import org.openide.util.lookup.ServiceProvider;
import vn.com.hkt.basic.jpa.PersistenceUltility;
import vn.com.hkt.basic.api.IPartitionBN;
import vn.com.hkt.pilot.entities.Country;
import vn.com.hkt.pilot.entities.Partition;
import vn.com.hkt.pilot.identity.access.spi.AccessData;

/**
 *
 * @author duong
 */
@ServiceProvider(service = IPartitionBN.class)
public class PartitionBN extends AccessData<Partition> implements IPartitionBN {

    public PartitionBN() {
        setAccessData(PersistenceUltility.getEMF(), Partition.class);
    }

    @Override
    public List<Partition> filterPartitionByID(String id) {
        return filter(Partition.FIELD_PARTITION_ID, id);
    }

    @Override
    public List<Partition> filterPartitionByName(String name) {
        return filter(Partition.FIELD_PARTITION_NAME, name);
    }
//Lấy tên vùng theo tên quốc gia

    @Override
    public List<Partition> getPartition1ByCountry(Country country) {
        return filter(Partition.FIELD_COUNTRY_ID_ACTUAL, String.valueOf(country.getId()));
    }

    @Override
    public List<Partition> getPartition2ByParent(Partition partition) {
        return filter(Partition.FIELD_PARTITION_PARENT_ID_ACTUAL,String.valueOf(partition.getId()));
    }
}

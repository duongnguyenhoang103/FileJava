/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.basic.api;

import java.util.List;
import vn.com.hkt.pilot.entities.Country;
import vn.com.hkt.pilot.entities.Partition;
import vn.com.hkt.pilot.identity.access.api.IAccessData;

/**
 *
 * @author duong
 */
public interface IPartitionBN extends IAccessData<Partition> {

    public List<Partition> filterPartitionByID(String id);

    public List<Partition> filterPartitionByName(String name);

    public List<Partition> getPartition1ByCountry(Country country);

    public List<Partition> getPartition2ByParent(Partition partition);
}

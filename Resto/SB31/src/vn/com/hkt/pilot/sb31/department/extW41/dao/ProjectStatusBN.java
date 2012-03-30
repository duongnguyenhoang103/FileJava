/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.pilot.sb31.department.extW41.dao;

import vn.com.hkt.pilot.identity.access.spi.AccessData;
import vn.com.hkt.pilot.sb31.deparment.extW41.entity.ProjectStatus;
import vn.com.hkt.pilot.sb31.generic.jpas.utils.PersistenceUtility;

/**
 *
 * @author BinLe
 */
public class ProjectStatusBN extends AccessData<ProjectStatus> {

    public ProjectStatusBN() {
        setAccessData(PersistenceUtility.getEmf(), ProjectStatus.class);
    }
}

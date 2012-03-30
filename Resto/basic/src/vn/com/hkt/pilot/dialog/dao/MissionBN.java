/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.pilot.dialog.dao;

import java.util.List;
import org.openide.util.lookup.ServiceProvider;
import vn.com.hkt.basic.jpa.PersistenceUltility;
import vn.com.hkt.basic.api.IMission;
import vn.com.hkt.pilot.entities.Mission;
import vn.com.hkt.pilot.identity.access.spi.AccessData;

/**
 *
 * @author khanguct
 */
@ServiceProvider(service = IMission.class)
public class MissionBN extends AccessData<Mission> implements IMission {

    public MissionBN() {
        setAccessData(PersistenceUltility.getEMF(), Mission.class);
    }

    @Override
    public List<Mission> filterMissionByID(String id) {
        return filter(Mission.FIELD_MISSION_ID, id);
    }

    @Override
    public List<Mission> filterMissionByName(String name) {
        return filter(Mission.FIELD_MISSION_NAME, name);
    }
}

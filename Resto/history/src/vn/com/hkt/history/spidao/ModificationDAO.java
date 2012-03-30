/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.history.spidao;

import javax.persistence.EntityManager;
import org.openide.util.lookup.ServiceProvider;
import vn.com.hkt.history.apidao.IModificationDAO;
import vn.com.hkt.history.entities.Modification;
import vn.com.hkt.pilot.history.jpa.JPAUtility;
import vn.com.hkt.pilot.identity.access.spi.AccessData;

/**
 *
 * @author BinLe
 */
@ServiceProvider(service = IModificationDAO.class)
public class ModificationDAO extends AccessData<Modification> implements IModificationDAO {

    private EntityManager em;

    public ModificationDAO() {
        setAccessData(JPAUtility.getEMF(), Modification.class);
    }   
}

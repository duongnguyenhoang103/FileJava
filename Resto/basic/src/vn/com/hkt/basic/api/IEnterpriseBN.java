/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.basic.api;

import vn.com.hkt.pilot.entities.Enterprise;
import java.util.List;
import vn.com.hkt.pilot.identity.access.api.IAccessData;

/**
 *
 * @author khanguct
 */
public interface IEnterpriseBN extends IAccessData<Enterprise> {

    public List<Enterprise> filterEnterpriseByID(String id);

    public List<Enterprise> filterEnterpriseByName(String name);

    public List<Enterprise> filterEnterpriseByChild(Enterprise enterprise);
}

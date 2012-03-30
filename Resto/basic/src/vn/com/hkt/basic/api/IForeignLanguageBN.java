/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.basic.api;

import java.util.List;
import vn.com.hkt.pilot.entities.ForeignLanguage;
import vn.com.hkt.pilot.identity.access.api.IAccessData;

/**
 *
 * @author khangpn
 */
public interface IForeignLanguageBN extends IAccessData<ForeignLanguage> {
    public List<ForeignLanguage> selectLanguageByCountryId(int countryId);
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.pilot.sb24.dao;

import vn.com.hkt.pilot.identity.access.spi.AccessData;
import vn.com.hkt.pilot.sb24.entity.SkillLanguage;
import vn.com.hkt.pilot.sb24.jpa.util.SubPersonSB24JPAUtil;

/**
 *
 * @author khangpn
 */
public class SkillLanguageBN extends AccessData<SkillLanguage> {

    public SkillLanguageBN() {
        setAccessData(SubPersonSB24JPAUtil.getEmf(), SkillLanguage.class);
    }
}

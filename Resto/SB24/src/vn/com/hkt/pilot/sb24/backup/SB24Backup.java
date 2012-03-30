/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.pilot.sb24.backup;

import java.util.Hashtable;
import java.util.List;
import org.openide.util.lookup.ServiceProvider;
import vn.com.hkt.pilot.backup.manager.api.IBackupModule;
import vn.com.hkt.pilot.entities.Person;
import vn.com.hkt.pilot.sb24.dao.SkillBN;
import vn.com.hkt.pilot.sb24.dao.SkillLanguageBN;
import vn.com.hkt.pilot.sb24.entity.LevelLanguage;
import vn.com.hkt.pilot.sb24.entity.Skill;
import vn.com.hkt.pilot.sb24.entity.SkillLanguage;

/**
 *
 * @author BinLe
 */
@ServiceProvider(service = IBackupModule.class)
public class SB24Backup implements IBackupModule {

    private Hashtable<String, Hashtable<Integer, Integer>> hashTotal;

    @Override
    public void setHashId(Hashtable<String, Hashtable<Integer, Integer>> hashID) {
        this.hashTotal = hashID;
    }

    @Override
    public void backupAll() {
        backupPerson();
        backupLevel();
    }

    private void backupPerson() {
        Hashtable<Integer, Integer> he = hashTotal.get(Person.class.getSimpleName());
        Hashtable<Integer, Integer> hastIdBusiness = hashTotal.get(SkillLanguage.class.getSimpleName());
        if (he == null && hastIdBusiness == null) {
            return;
        }
        List<Skill> ld = new SkillBN().selectAll();
        for (int i = 0; i < ld.size(); i++) {

            if (he != null) {
                int idOld = ld.get(i).getPersonIdActual();
                int idNew = he.get(idOld);
                ld.get(i).setPersonIdActual(idNew);
            }
            if (hastIdBusiness != null) {
                for (int j = 0; j < ld.get(i).getSkillLanguagesIdActual().size(); j++) {
                    int idBusinessOld = ld.get(i).getSkillLanguagesIdActual().get(j);
                    int idBusinessNew = hastIdBusiness.get(idBusinessOld);
                    ld.get(i).getSkillLanguagesIdActual().set(j, idBusinessNew);
                }
            }

            new SkillBN().update(ld.get(i));
        }
    }

    private void backupLevel() {
        Hashtable<Integer, Integer> hLevel = hashTotal.get(LevelLanguage.class.getSimpleName());

        if (hLevel == null) {
            return;
        }
        List<SkillLanguage> ld = new SkillLanguageBN().selectAll();
        for (int i = 0; i < ld.size(); i++) {

            if (hLevel != null) {
                int idOld = ld.get(i).getLevelLanguageIdActual();
                int idNew = hLevel.get(idOld);
                ld.get(i).setLevelLanguageIdActual(idNew);
            }


            new SkillLanguageBN().update(ld.get(i));
        }
    }
}

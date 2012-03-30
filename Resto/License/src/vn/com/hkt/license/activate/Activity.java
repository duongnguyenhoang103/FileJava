/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.license.activate;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import vn.com.hkt.license.api.IKeyInstallDAO;
import vn.com.hkt.license.api.IKeyLicenseDAO;
import vn.com.hkt.license.entities.KeyInstall;
import vn.com.hkt.license.spi.KeyInstallDAO;
import vn.com.hkt.license.spi.KeyLicenseDAO;
import vn.com.hkt.pilot.datetime.utils.DateTimeConverter;

/**
 *
 * @author Admin
 */
public class Activity {

    private static IKeyLicenseDAO keyLicenseDAO = new KeyLicenseDAO();
    private static IKeyInstallDAO keyInstallDAO = new KeyInstallDAO();
    private static Activity activity;

    public static Activity getActivity() {
        if (activity == null) {
            activity = new Activity();
        }
        return activity;
    }

    /**
     * kiểm tra module  đã được đăng ký chưa
     * module: tên module cần được kiểm tra     
     */
    public boolean checKeyInstall(String module) {
        List<KeyInstall> keyInstalls = keyInstallDAO.selectAll();
        for (int i = 0; i < keyInstalls.size(); i++) {
            if (keyInstalls.get(i).getModule().equals(module)) {
                if (activity.checkKey(keyInstalls.get(i))) {
                    return true;
                }
            }
        }
        return false;
    }
    /**
     * kiểm tra tính hợp lệ của key Instanll
     * @param keyInstall
     * @return 
     */

    private boolean checkKey(KeyInstall keyInstall) {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String dateNow = dateFormat.format(Calendar.getInstance().getTime());
        String dateActivate = keyInstall.getDateActivate();
        DateTimeConverter dtc = new DateTimeConverter();
        if (dtc.compareDate(dateNow, dateActivate) < 0) {
            return false;
        }
        if (dtc.getYear(dateNow) != dtc.getYear(dateActivate)) {
            return false;
        }
        return true;

    }
}

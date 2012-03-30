/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.pilot.sb42.pricesheet.finance;

import java.util.Calendar;  
import java.util.List;
import vn.com.hkt.pilot.datetime.utils.DateTimeConverter;
import vn.com.hkt.pilot.sb42.pricesheeta.entity.PriceSheet;
    
/**
 *
 * @author HKT01
 */ 
public class PriceSheetFinance {
            
    private DateTimeConverter dateTimeConverter = new DateTimeConverter();

    public PriceSheet chooseMinPriceSheet(List<PriceSheet> list) {
        PriceSheet priceSheet = new PriceSheet();
        if (!list.isEmpty()) {
            priceSheet = list.get(0);
            for (PriceSheet bean : list) {
                if (bean.getExportUnit() <= priceSheet.getExportUnit()) {
                    priceSheet = bean;
                }
            }
        }
        return priceSheet;
    }

    /**
     * 
     * @param priceSheet
     * @return true neu Bang gia con han su dung
     * Nguoc lai return false neu het han
     */
    public boolean isValidate(PriceSheet priceSheet) {
        String date1 = null; // Ngay hien tai cua he thong
        String date2 = null; // Ngay bat dau cua PriceSheet
        String date3 = null; // Ngay ket thuc cua PriceSheet
        Calendar calendar = Calendar.getInstance(); // Lay thoi gian hien tai cua he thong
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);

        /******** Ngay hien tai **********/
        date1 = String.valueOf(day) + "/" + String.valueOf(month) + "/" + String.valueOf(year);

        /******** Ngay bat dau cua PriceSheet **********/
        day = priceSheet.getAppliedFrom().get(Calendar.DAY_OF_MONTH);
        month = priceSheet.getAppliedFrom().get(Calendar.MONTH);
        year = priceSheet.getAppliedFrom().get(Calendar.YEAR);
        date2 = String.valueOf(day) + "/" + String.valueOf(month) + "/" + String.valueOf(year);

        /******** Ngay ket thuc cua PriceSheet **********/
        day = priceSheet.getAppliedTo().get(Calendar.DAY_OF_MONTH);
        month = priceSheet.getAppliedTo().get(Calendar.MONTH);
        year = priceSheet.getAppliedTo().get(Calendar.YEAR);
        date3 = String.valueOf(day) + "/" + String.valueOf(month) + "/" + String.valueOf(year);

        /**** So sanh --> Ngay hien tai co nam trong khoang d2,d3 khong ****/
        int compare1 = dateTimeConverter.compareDate(date1, date2);
        int compare2 = dateTimeConverter.compareDate(date1, date3);
        
        if(compare1==1 && compare2==-1 )return true;    
        return false;
    }
}

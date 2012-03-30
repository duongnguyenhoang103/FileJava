/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.history.apidao;

import java.util.Date;

/**
 *
 * @author Admin
 */
public interface IExportImportHistory {

    public void createFileExcel(String filePath);

    public void exportHistoryToExcel(String filePath, String moduleName, Class typeObject, Date fromDate, Date toDate);

    public void importHistory(String filePath);

    public void exportModuleHistoryToExcel(String filePath, String moduleName, Date fromDate, Date toDate);
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.pilot.backup.excel;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.openide.util.Exceptions;

/**
 *
 * @author BinLe
 */
public class ExcelWriting {

    private String fileExcelName;
    private HSSFWorkbook workbook;
    private FileInputStream fileInputStream;
    private FileOutputStream fileOutputStream;

    public ExcelWriting(String fileExcelName) {
        this.fileExcelName = fileExcelName;
        initProcess();
    }

    private void initProcess() {
        workbook = new HSSFWorkbook();
        try {
            fileInputStream = new FileInputStream(fileExcelName);
            workbook = new HSSFWorkbook(fileInputStream);
            fileInputStream.close();
        } catch (Exception ex) {
            workbook = new HSSFWorkbook();
        }
    }

    public HSSFSheet createSheet(String sheetName) {
        HSSFSheet sheet;
        sheet = workbook.getSheet(sheetName);
        if (sheet == null) {
            sheet = workbook.createSheet(sheetName);
        }
        return sheet;
    }

    public HSSFSheet getSheetByName(String sheetName) {
        return workbook.getSheet(sheetName);
    }

    public HSSFRow createRow(HSSFSheet sheet, int rowIndex) {
        if (sheet == null) {
            return null;
        }
        return sheet.createRow(rowIndex);
    }

    private HSSFCell createCell(HSSFSheet sheet, int rowIndex, int columnIndex) {
        if (sheet == null) {
            return null;
        }
        HSSFRow row = sheet.getRow(rowIndex);
        if (row == null) {
            row = createRow(sheet, rowIndex);
        }
        if (row == null) {
            return null;
        }
        return row.createCell(columnIndex);
    }

    public boolean setData(HSSFSheet sheet, int rowIndex, int columnIndex, String data) {
        if (sheet == null) {
            return false;
        }
        HSSFRow row = sheet.getRow(rowIndex);
        if (row == null) {
            row = createRow(sheet, rowIndex);
        }
        if (row == null) {
            return false;
        }
        HSSFCell cell = row.getCell(columnIndex);
        if (cell == null) {
            cell = createCell(sheet, rowIndex, columnIndex);
        }
        if (cell == null) {
            return false;
        }
        cell.setCellValue(data);
        return true;
    }

    public HSSFSheet writeHeader(HSSFSheet sheet, List<String> list) {
        if (list == null || sheet == null) {
            return null;
        }
        for (int i = 0; i < list.size(); i++) {
            if (!setData(sheet, 0, i, list.get(i))) {
                return null;
            }
        }
        return sheet;
    }

    public HSSFSheet writeDataRow(HSSFSheet sheet, List<String> list, int rowIndex) {
        if (list == null || sheet == null || rowIndex <= 0) {
            return null;
        }
        for (int i = 0; i < list.size(); i++) {
            if (!setData(sheet, rowIndex, i, list.get(i))) {
                return null;
            }
        }
        return sheet;
    }

    public HSSFSheet writeDataColumn(HSSFSheet sheet, List<String> list, int columnIndex) {
        if (list == null || sheet == null || columnIndex < 0) {
            return null;
        }
        for (int i = 0; i < list.size(); i++) {
            if (!setData(sheet, i, columnIndex, list.get(i))) {
                return null;
            }
        }
        return sheet;
    }

    public boolean writeExcel() {
        try {
            fileOutputStream = new FileOutputStream(fileExcelName);
            workbook.write(fileOutputStream);
            fileOutputStream.close();
            return true;
        } catch (Exception ex) {
            Exceptions.printStackTrace(ex);
            return false;
        }
    }
}

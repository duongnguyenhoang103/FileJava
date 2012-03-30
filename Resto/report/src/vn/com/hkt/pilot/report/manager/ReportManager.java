/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.pilot.report.manager;

import groovy.model.DefaultTableModel;
import java.util.HashMap;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;
import java.io.File;
import net.sf.jasperreports.view.JasperViewer;
import org.openide.util.lookup.ServiceProvider;
import vn.com.hkt.pilot.report.api.IReportManager;

/**
 *
 * @author DONGTAM
 */
@ServiceProvider(service = IReportManager.class)
public class ReportManager implements IReportManager {

    private String fileNameReport;
    private HashMap hasMap = null;
    private TableModel model;

    public ReportManager() {
    }

    @Override
    public void setReportManager(HashMap hasMap, TableModel model) {
        File fi = new File("");
        String str = fi.getAbsolutePath();
        JOptionPane.showMessageDialog(null, str);
        //   str += "\\report\\src\\vn\\com\\hkt\\pilot\\report\\template\\rpt";
        str += "//template//rpt";
        fileNameReport = str + model.getColumnCount() + ".jasper";
        this.hasMap = hasMap;
        this.model = model;
    }

    @Override
    public void exportReport() {
        try {
           // hasMap = new HashMap();
            //   hasMap=getHashMap(keys, values)
//            JOptionPane.showMessageDialog(null, "report start");
//            hasMap.put("prTenDoanhNghiep", "HKT Consult");
//            hasMap.put("prDiaChi", "66 Trần Thái Tông Hà Nội");
//            hasMap.put("prTenBaoCao", "");
//            hasMap.put("prNgayLap", "");
//            for (int i = 0; i < model.getColumnCount(); i++) {
            //hasMap.put("prColumn_" + i, String.valueOf(model.getColumnName(i)));
            //  }
            // hasMap.put("prNguoiLapBaoCao", "Đồng Thị Tâm");

            JasperPrint print2 = JasperFillManager.fillReport(fileNameReport, hasMap, new JRTableModelDataSource(model));
            JOptionPane.showMessageDialog(null, "viewer report");
            JasperViewer jv = new JasperViewer(print2, false);
            jv.setVisible(true);

        } catch (JRException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "error report!");
        }
    }

    @Override
    public javax.swing.table.DefaultTableModel getModel(JTable table) {
        String[] header = new String[table.getColumnCount()];
        for (int i = 0; i < table.getColumnCount(); i++) {
            header[i] = table.getColumnName(i);
        }
        javax.swing.table.DefaultTableModel modelTemp = new javax.swing.table.DefaultTableModel(header, 0);
        for (int i = 0; i < table.getRowCount(); i++) {
            String[] row = new String[table.getColumnCount()];
            for (int j = 0; j < table.getColumnCount(); j++) {
                String str;
                if (table.getValueAt(i, j) == null) {
                    str = "";
                } else {
                    str = table.getValueAt(i, j).toString();
                }
                row[j] = str;
            }
            modelTemp.addRow(row);
        }

        return modelTemp;
    }

    @Override
    public HashMap getHashMap(String[] keys, String[] values) {
        HashMap map = new HashMap();
        for (int i = 0; i < keys.length; i++) {
            map.put(keys[i], values[i]);
        }
        return map;
    }
}

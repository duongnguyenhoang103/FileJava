/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ChartYearThuChi.java
 *
 * Created on Nov 30, 2011, 5:55:37 AM
 */
package vn.com.hkt.chart.ui.panel;

import vn.com.hkt.finance.api.ICalculate;
import vn.com.hkt.pilot.entities.Enterprise;
import vn.com.hkt.basic.api.IEnterpriseBN;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.DateTickMarkPosition;
import org.jfree.chart.axis.DateTickUnit;
import org.jfree.chart.axis.DateTickUnitType;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.time.TimePeriodAnchor;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.time.Year;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.RectangleInsets;
import org.openide.util.Lookup;
import vn.com.hkt.bom.operation.dao.OperationBN;

/**
 *
 * @author longnt
 */
public class ChartYearThuChi extends javax.swing.JPanel implements ActionListener, ItemListener {

    private DefaultComboBoxModel modelNam1, modelNam2, modelEnterprise;
    private XYPlot plot;
    private Object item;
    private int year1 = 2000;
    private int year2 = 2004;
    private ChartPanel chartPanel;
    private Enterprise enterprise;
    private ICalculate calculate;
    private List<Enterprise> list = new ArrayList<Enterprise>();
    private IEnterpriseBN enterpriseBN;
    private OperationBN operationBN;

    /** Creates new form ChartYearThuChi */
    public ChartYearThuChi() {
        initComponents();

        
        this.calculate = Lookup.getDefault().lookup(ICalculate.class);
        this.enterpriseBN = Lookup.getDefault().lookup(IEnterpriseBN.class);
        operationBN = new OperationBN();
        // Create model nam1
        modelNam1 = new DefaultComboBoxModel();
        for (int i = 2007; i <= 2020; i++) {
            modelNam1.addElement(i);
        }

        // Create mode nam2
        modelNam2 = new DefaultComboBoxModel();
        for (int i = 2007; i <= 2020; i++) {
            modelNam2.addElement(i);
        }
        
        
        // Create model enterprise
        modelEnterprise = new DefaultComboBoxModel();

        list = operationBN.enterpriseHasOperation();
        for (Enterprise bean : list) {
            modelEnterprise.addElement(bean);
        }

        cboEnterprise.setModel(modelEnterprise);
        cboEnterprise.setSelectedItem(item);
        cboEnterprise.addItemListener(this);
        cboNam1.setModel(modelNam1);
        cboNam2.setModel(modelNam2);
        cboNam1.addItemListener(this);
        cboNam2.addItemListener(this);


        buttonXem.addActionListener(this);
        panelChart.setLayout(new GridLayout());

        chartPanel = new ChartPanel(createChart(year1, year2));
        chartPanel.setPreferredSize(new Dimension(1000, 300));
        chartPanel.setBorder(BorderFactory.createLineBorder(Color.red));
        panelChart.add(chartPanel);
        setSize(1000, 450);
        //RefineryUtilities.centerFrameOnScreen(this);
        setVisible(true);
        enterprise = new Enterprise();
    }

    // Create Dataset
    public XYDataset createDataset(int y1, int y2) {

        // Create dataset Thu
        TimeSeries series1 = new TimeSeries("Thu", Year.class);
        TimeSeries series2 = new TimeSeries("Chi", Year.class);

        for (int i = y1; i <= y2; i++) {
             String str = String.valueOf(i);
//            series1.add(new Year(i), calculate.calculateImportByYear(enterprise, str));
//            series2.add(new Year(i),  calculate.calculateExportByYear(enterprise, str));
        }


        TimeSeriesCollection dataset = new TimeSeriesCollection();
        dataset.addSeries(series1);
        dataset.addSeries(series2);
        dataset.setXPosition(TimePeriodAnchor.MIDDLE);

        return dataset;
    }

    // Create Chart
    public JFreeChart createChart(int y1, int y2) {
        XYDataset data = createDataset(y1, y2);
        JFreeChart chart = ChartFactory.createTimeSeriesChart(" ", "Năm", "Doanh Thu", data, true, true, false);
        plot = (XYPlot) chart.getXYPlot();
        plot.setBackgroundPaint(Color.white);
        plot.setAxisOffset(new RectangleInsets(5.0, 5.0, 10.0, 10.0));


        XYItemRenderer r = plot.getRenderer();
        if (r instanceof XYLineAndShapeRenderer) {
            XYLineAndShapeRenderer renderer = (XYLineAndShapeRenderer) r;
            renderer.setBaseShapesVisible(true);
            renderer.setBaseShapesFilled(true);
        }

        DateAxis axis = (DateAxis) plot.getDomainAxis();
        axis.setDateFormatOverride(new SimpleDateFormat("yyyy"));
        axis.setTickMarkPosition(DateTickMarkPosition.MIDDLE);
        axis.setTickUnit(new DateTickUnit(DateTickUnitType.YEAR, 1));
        return chart;
    }

    /**
     * @param comboBox
     * @return 
     */
    public Object chooseItem(JComboBox comboBox) {
        item = (Object) comboBox.getSelectedItem();
        return item;
    }

    /**
     * Gan gia tri cho cac bien duoc nhap vao tu combo box
     * @param e
     * 
     */
    @Override
    public void itemStateChanged(ItemEvent e) {
        JComboBox cb = (JComboBox) e.getSource();
        int size2 = modelNam1.getSize();

        if (cb == cboNam1) {
            chooseItem(cboNam1);

            for (int i = 0; i < size2; i++) {
                if (modelNam1.getElementAt(i).equals(item)) {
                    year1 = Integer.parseInt(modelNam1.getElementAt(i).toString());
                }
            }
        }

        if (cb == cboNam2) {
            chooseItem(cboNam2);
            for (int i = 0; i < size2; i++) {
                if (modelNam2.getElementAt(i).equals(item)) {
                    year2 = Integer.parseInt(modelNam2.getElementAt(i).toString());
                }
            }
        }
        if (cb == cboEnterprise) {
            chooseItem(cboEnterprise);
            for (int i = 0; i < list.size(); i++) {
                if (modelEnterprise.getElementAt(i).equals(item)) {
                    enterprise = (Enterprise) modelEnterprise.getElementAt(i);
                }
            }
        }

    }

    /**
     * Xu ly su kien nhan button va thiet lap lai dataset
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();
        if (button == buttonXem) {
            plot.setDataset(createDataset(year1, year2));
        }

    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelChart = new javax.swing.JPanel();
        panelDay = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        cboNam1 = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        cboNam2 = new javax.swing.JComboBox();
        buttonXem = new javax.swing.JButton();
        cboEnterprise = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();

        javax.swing.GroupLayout panelChartLayout = new javax.swing.GroupLayout(panelChart);
        panelChart.setLayout(panelChartLayout);
        panelChartLayout.setHorizontalGroup(
            panelChartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 682, Short.MAX_VALUE)
        );
        panelChartLayout.setVerticalGroup(
            panelChartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 294, Short.MAX_VALUE)
        );

        panelDay.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setText(org.openide.util.NbBundle.getMessage(ChartYearThuChi.class, "ChartYearThuChi.jLabel1.text")); // NOI18N

        jLabel2.setText(org.openide.util.NbBundle.getMessage(ChartYearThuChi.class, "ChartYearThuChi.jLabel2.text")); // NOI18N

        cboNam1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " " }));
        cboNam1.setPreferredSize(new java.awt.Dimension(56, 20));

        jLabel5.setText(org.openide.util.NbBundle.getMessage(ChartYearThuChi.class, "ChartYearThuChi.jLabel5.text")); // NOI18N

        cboNam2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " " }));
        cboNam2.setPreferredSize(new java.awt.Dimension(56, 20));

        buttonXem.setText(org.openide.util.NbBundle.getMessage(ChartYearThuChi.class, "ChartYearThuChi.buttonXem.text")); // NOI18N

        cboEnterprise.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " " }));
        cboEnterprise.setPreferredSize(new java.awt.Dimension(56, 20));

        jLabel3.setText(org.openide.util.NbBundle.getMessage(ChartYearThuChi.class, "ChartYearThuChi.jLabel3.text")); // NOI18N

        javax.swing.GroupLayout panelDayLayout = new javax.swing.GroupLayout(panelDay);
        panelDay.setLayout(panelDayLayout);
        panelDayLayout.setHorizontalGroup(
            panelDayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDayLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cboEnterprise, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelDayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelDayLayout.createSequentialGroup()
                        .addGroup(panelDayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel5))
                        .addGroup(panelDayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelDayLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(cboNam2, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(buttonXem))
                            .addGroup(panelDayLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(cboNam1, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(196, Short.MAX_VALUE))
        );
        panelDayLayout.setVerticalGroup(
            panelDayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDayLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelDayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cboEnterprise, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelDayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cboNam1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelDayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(cboNam2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonXem))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelDay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelChart, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelDay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelChart, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonXem;
    private javax.swing.JComboBox cboEnterprise;
    private javax.swing.JComboBox cboNam1;
    private javax.swing.JComboBox cboNam2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel panelChart;
    private javax.swing.JPanel panelDay;
    // End of variables declaration//GEN-END:variables
}

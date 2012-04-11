/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jfree.chart.demo3;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
import org.jfree.util.Rotation;
/**
 *
 * @author duong
 */
public class PieChart3DDemo3  extends ApplicationFrame {
 /**
     * Creates a new demo.
     *
     * @param title  the frame title.
     */
    public PieChart3DDemo3(final String title) {

        super(title);
        final PieDataset dataset = createSampleDataset();
        final JFreeChart chart = createChart(dataset);
        final ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        setContentPane(chartPanel);

    }
    
    /**
     * Creates a sample dataset for the demo.
     * 
     * @return A sample dataset.
     */
    private PieDataset createSampleDataset() {
        
        final DefaultPieDataset result = new DefaultPieDataset();
        result.setValue("Java", new Double(43.2));
        result.setValue("Visual Basic", new Double(10.0));
        result.setValue("C/C++", new Double(17.5));
        result.setValue("PHP", new Double(32.5));
        result.setValue("Perl", new Double(1.0));
        return result;
        
    }
    /**
     * Creates a sample chart.
     * 
     * @param dataset  the dataset.
     * 
     * @return A chart.
     */
    private JFreeChart createChart(final PieDataset dataset) {
        
        final JFreeChart chart = ChartFactory.createPieChart3D(
            "Pie Chart 3D Demo 3",  // chart title
            dataset,                // data
            true,                   // include legend
            true,
            false
        );

        final PiePlot3D plot = (PiePlot3D) chart.getPlot();
        plot.setStartAngle(290);
        plot.setDirection(Rotation.CLOCKWISE);
        plot.setForegroundAlpha(0.5f);
        plot.setNoDataMessage("No data to display");
        plot.setLabelGenerator(null);
        return chart;
        
    }
    
    /**
     * @param args the command line arguments
     */
     public static void main(final String[] args) {

        final PieChart3DDemo3 demo = new PieChart3DDemo3("Pie Chart 3D Demo 3");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);

    }

}

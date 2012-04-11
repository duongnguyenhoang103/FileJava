/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package piechart3d_demo2;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
import org.jfree.util.Rotation;

/**
 *
 * @author duong
 */
public class PieChart3DDemo2 extends ApplicationFrame {

    /**
     * @param args the command line arguments
     */
    public PieChart3DDemo2(final String title) {
        super(title);

        //create a dataset
        final DefaultPieDataset data = new DefaultPieDataset();
        data.setValue("Java", new Double(43.2));
        data.setValue("Visual Basic", new Double(10.0));
        data.setValue("C/C++", new Double(17.5));
        data.setValue("PHP", new Double(32.5));
        data.setValue("Perl", new Double(12.5));
        
// create the chart
        JFreeChart chart = ChartFactory.createPieChart3D(
                "Pie Chart 3D Demo 2", // chart title
                data, // data
                true, // include legend
                true,
                false);
        chart.setBackgroundPaint(Color.yellow);
        PiePlot3D plot = (PiePlot3D) chart.getPlot();
        plot.setStartAngle(270);
        plot.setDirection(Rotation.ANTICLOCKWISE);
        plot.setForegroundAlpha(0.5f);
        plot.setInteriorGap(0.33);
        plot.setBackgroundAlpha(0.2f);
         plot.setLabelGenerator(null);
        
        // add the chart to a panel...
        final ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(800, 600));
        setContentPane(chartPanel);

      final Rotator rotator = new Rotator(plot);
      rotator.start();
              
              

    }

    public static void main(final String[] args) {

        final PieChart3DDemo2 demo = new PieChart3DDemo2("Pie Chart 3D Demo 2");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}

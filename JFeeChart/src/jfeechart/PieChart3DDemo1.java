/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jfeechart;

import java.util.Locale;
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
public class PieChart3DDemo1 extends ApplicationFrame {

    /**
     * @param args the command line arguments
     */
    public PieChart3DDemo1(final String title) {
        super(title);
    }
    //create a dataset...
    final PieDataset dataset = createSampleDataSet();
    //create the chart..
    final JFreeChart chart = createChart(dataset);

    private PieDataset createSampleDataSet() {
        final DefaultPieDataset result = new DefaultPieDataset();
        result.setValue("Java", new Double(43.2));
        result.setValue("Visual Basic", new Double(10.0));
        result.setValue("C/C++", new Double(17.5));
        result.setValue("PHP", new Double(32.5));
        result.setValue("Perl", new Double(1.0));
        return result;
    }

    private JFreeChart createChart(final PieDataset dataset) {
        final JFreeChart chart = ChartFactory.createPieChart3D(
                "Pie Chart 3D Demo 1 ", // chart title
                dataset, //data
                true, //include legend
                true,
                false);
        final PiePlot3D plot = (PiePlot3D) chart.getPlot();
        plot.setStartAngle(290);
        plot.setDirection(Rotation.CLOCKWISE);
        plot.setForegroundAlpha(0.5f);
        plot.setNoDataMessage("No data to display");
        return chart;
    }

    public static void main( final String[] args) {
      final PieChart3DDemo1 demo = new PieChart3DDemo1("Pie Chart 3D Demo 1");
      demo.pack();
      RefineryUtilities.centerFrameOnScreen(demo);
      demo.setVisible(true);
    }
}

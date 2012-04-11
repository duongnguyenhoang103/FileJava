/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package piechartexample;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

// Import the JFreeChart classes
import org.jfree.chart.*;
import org.jfree.chart.plot.*;
import org.jfree.data.*;
import org.jfree.data.general.*;

/**
 *
 * @author duong
 */
public class PieChartExample extends JPanel {
    // Holds the data

    private DefaultPieDataset dataset = new DefaultPieDataset();
    // Create a set of charts
    private JFreeChart chart1;
    private JFreeChart chart2;
    private JFreeChart chart3;
    private JFreeChart chart4;
    // Create a set of panels that can show charts
    private ChartPanel panel1;
    private ChartPanel panel2;
    private ChartPanel panel3;
    private ChartPanel panel4;

    public PieChartExample() {
        // Initialize the dataset
        dataset.setValue("California", new Double(10.0));
        dataset.setValue("Arizona", new Double(8.0));
        dataset.setValue("New Mexico", new Double(8.0));
        dataset.setValue("Texas", new Double(40.0));
        dataset.setValue("Louisiana", new Double(8.0));
        dataset.setValue("Mississippi", new Double(4.0));
        dataset.setValue("Alabama", new Double(2.0));
        dataset.setValue("Florida", new Double(20.0));

        // Create the charts
        chart1 = ChartFactory.createPieChart(
                "Driving Time Spent Per State (Flat Pie Chart)", // The chart title
                dataset, // The dataset for the chart
                true, // Is a legend required?
                true, // Use tooltips
                false // Configure chart to generate URLs?
                );
        chart2 = ChartFactory.createPieChart(
                "Driving Time Spent Per State (Exploded Pie Chart)", // The chart title
                dataset, // The dataset for the chart
                true, // Is a legend required?
                true, // Use tooltips
                false // Configure chart to generate URLs?
                );
        PiePlot plot = (PiePlot) chart2.getPlot();
        plot.setExplodePercent(3, 0.25);

        chart3 = ChartFactory.createPieChart3D(
                "Driving Time Spent Per State (3D Pie Chart)", // The chart title
                dataset, // The dataset for the chart
                true, // Is a legend required?
                true, // Use tooltips
                false // Configure chart to generate URLs?
                );
        chart4 = ChartFactory.createPieChart3D(
                "Driving Time Spent Per State (3D with Transparency)", // The chart title
                dataset, // The dataset for the chart
                true, // Is a legend required?
                true, // Use tooltips
                false // Configure chart to generate URLs?
                );
        PiePlot3D plot4 = (PiePlot3D) chart4.getPlot();
        plot4.setForegroundAlpha(0.6f);
     plot4.setBackgroundAlpha(0.9f);

        // Create this panel
        this.setLayout(new GridLayout(2, 2));
        this.panel1 = new ChartPanel(chart1);
        this.panel2 = new ChartPanel(chart2);
        this.panel3 = new ChartPanel(chart3);
        this.panel4 = new ChartPanel(chart4);
        this.add(panel1);
        this.add(panel2);
        this.add(panel3);
        this.add(panel4);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        JFrame frame = new JFrame("My Trip Driving From CA to FL...");
        PieChartExample chart = new PieChartExample();
        frame.getContentPane().add(chart, BorderLayout.CENTER);
        frame.setSize(640, 480);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}

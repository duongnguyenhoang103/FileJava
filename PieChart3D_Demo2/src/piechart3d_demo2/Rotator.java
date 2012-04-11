/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package piechart3d_demo2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import org.jfree.chart.plot.PiePlot3D;

/**
 *
 * @author duong
 */
class Rotator  extends Timer implements ActionListener{
 /** The plot. */
    private PiePlot3D plot;

    /** The angle. */
    private int angle = 270;

    /**
     * Constructor.
     *
     * @param plot  the plot.
     */
    Rotator(final PiePlot3D plot) {
      // super(100, null);
       // super();
        this.plot = plot;
        addActionListener(this);
    }
    @Override
    public void actionPerformed ( final ActionEvent e) {
       this.plot.setStartAngle(this.angle);
        this.angle = this.angle + 1;
        if (this.angle == 360) {
            this.angle = 0;
        }
    }

    private void addActionListener(Rotator aThis) {
       
    }

    void start() {
        
    }
    
    
}

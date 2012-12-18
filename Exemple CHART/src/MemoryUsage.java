/**
 *
 * @author Houssem Eddine Lassoued
 */
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.DefaultXYItemRenderer;

import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.time.Millisecond;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;

/**
 * A demo application showing a dynamically updated chart that displays the current JVM memory
 * usage.
 *
 * @author Tony Bianchini
 * @author David Gilbert
 */
public class MemoryUsage extends JPanel {

    /** Time series for total memory used. */
    private TimeSeries total;

    /** Time series for free memory. */
 //   private TimeSeries free;

    /**
     * Creates a new application.
     */
    public MemoryUsage() {

        super(new BorderLayout());

        // create two series that automatically discard data more than 30 seconds old...
        this.total = new TimeSeries("Total", Millisecond.class);
        this.total.setHistoryCount(10000);
      //  this.free = new TimeSeries("Free", Millisecond.class);
      //  this.free.setHistoryCount(10000);
        TimeSeriesCollection dataset = new TimeSeriesCollection();
        dataset.addSeries(total);
      //  dataset.addSeries(free);

        DateAxis domain = new DateAxis("Time");
        NumberAxis range = new NumberAxis("RxLev");

        XYItemRenderer renderer = new DefaultXYItemRenderer();
        renderer.setSeriesPaint(0, Color.red);
        renderer.setSeriesPaint(1, Color.green);
        renderer.setBaseStroke(
            new BasicStroke(2f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL)
        );
        XYPlot xyplot = new XYPlot(dataset, domain, range, renderer);
        xyplot.setBackgroundPaint(Color.black);

        domain.setAutoRange(true);
        domain.setLowerMargin(0.0);
        domain.setUpperMargin(0.0);
        domain.setTickLabelsVisible(true);

        range.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

        JFreeChart chart = new JFreeChart(
            "Niveau RxLev",
            JFreeChart.DEFAULT_TITLE_FONT,
            xyplot,
            true
        );
        ChartPanel chartPanel = new ChartPanel(chart);
        add(chartPanel);

    }

    /**
     * Adds an observation to the 'total memory' time series.
     *
     * @param y  the total memory used.
     */
    private void addTotalObservation(double y) {
        total.add(new Millisecond(), y);
    }

    /**
     * Adds an observation to the 'free memory' time series.
     *
     * @param y  the free memory.
     */

    /**
     * The data generator.
     */
    class DataGenerator extends Timer implements ActionListener {

        /**
         * Constructor.
         */
        DataGenerator() {
            super(1000, null);
            addActionListener(this);
        }

        /**
         * Adds a new free/total memory reading to the dataset.
         *
         * @param event  the action event.
         */
        public void actionPerformed(ActionEvent event) {

            long f = Runtime.getRuntime().freeMemory();
            long t = Runtime.getRuntime().totalMemory();
      
            addTotalObservation(f);
      
        }

    }

    /**
     * Entry point for the sample application.
     *
     * @param args  ignored.
     */
    public static void main(String[] args) {

        JFrame frame = new JFrame("RxLev");
        MemoryUsage panel = new MemoryUsage();

        frame.getContentPane().add(panel, BorderLayout.CENTER);
        frame.setBounds(200, 120, 300, 280);
        frame.setVisible(true);
       panel.new DataGenerator().start();

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

}

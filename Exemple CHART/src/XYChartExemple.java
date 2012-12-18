/**
 *
 * @author Houssem Eddine Lassoued
 */
import java.awt.*;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Calendar;
import javax.swing.*;
import org.jfree.chart.*;
import org.jfree.chart.plot.*;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.time.Hour;
import org.jfree.data.time.Month;

class XYChartExemple extends JFrame {

    NumberAxis rangeAxis;
    ChartPanel chartPanel;
    JFreeChart chart;
    Statement st;
    TimeSeries series_ligne_1 = null;
    TimeSeries series_ligne_2 = null;

    public XYChartExemple() {
        this.setSize(400, 300);
        this.setTitle("Exemple");
        JPanel p = new JPanel();

        // Couleurs des lignes
        Color COULEURLIGNE1 = Color.DARK_GRAY;
        Color COULEURLIGNE2 = Color.red;

        // On ajoute les lignes dans le dataset
        TimeSeriesCollection dataSet = new TimeSeriesCollection();

        // Creation des lignes
        series_ligne_1 = new TimeSeries("qte", Hour.class);
        series_ligne_2 = new TimeSeries("produit", Hour.class);
        // Ajout des ligne dans le dataset
        dataSet.addSeries(series_ligne_1);
        dataSet.addSeries(series_ligne_2);
        // Creation du graphique
        chart = ChartFactory.createTimeSeriesChart("Titre", "Produit", "Qte", dataSet, true, true, false);
        chartPanel = new ChartPanel(chart, true);
        // Les dimmensions du graphique
        Dimension d = new Dimension(350, 250);
        chartPanel.setMaximumSize(d);
        chartPanel.setPreferredSize(d);
        chartPanel.setMinimumSize(d);
        this.add(chartPanel);

        /****************/

        XYPlot plot = (XYPlot) chart.getPlot();
        // On definie une couleur pour les lignes
        plot.getRenderer().setSeriesPaint(0, COULEURLIGNE1);
        plot.getRenderer().setSeriesPaint(1, COULEURLIGNE2);
        // On definie une couleur de fond pour le graphique
        plot.setBackgroundPaint(Color.WHITE);
        rangeAxis = (NumberAxis) plot.getRangeAxis();
        // On fixe une taille pour l'axe des ordonnÃ©es
        rangeAxis.setUpperBound(220.0);
        //this.getContentPane().add(p);

        //Valeurs statiques
        addData();
        //Depuis la base de données
        //addData1();
        this.setVisible(true);
    }
    // On ajoute des donnÃ©es

    public void addData() {
        // Désactivation de l'ajout dans le graphique
        series_ligne_1.setNotify(false);

        series_ligne_1.add(new Hour(1, 5, 2, 2005), 80.55);
        series_ligne_1.add(new Hour(2, 5, 2, 2005), 70.55);
        series_ligne_1.add(new Hour(3, 5, 2, 2005), 50.55);
        series_ligne_1.add(new Hour(4, 5, 2, 2005), 50.55);
        series_ligne_1.add(new Hour(5, 5, 2, 2005), 80.55);
        series_ligne_1.add(new Hour(6, 5, 2, 2005), 15.55);

        // Activation de l'ajout dans le graphique
        series_ligne_1.setNotify(true);

        series_ligne_2.setNotify(false);

        series_ligne_2.add(new Hour(1, 5, 2, 2005), 45.55);
        series_ligne_2.add(new Hour(2, 5, 2, 2005), 15.05);
        series_ligne_2.add(new Hour(3, 5, 2, 2005), 60.55);
        series_ligne_2.add(new Hour(4, 5, 2, 2005), 45.55);
        series_ligne_2.add(new Hour(5, 5, 2, 2005), 35.55);
        series_ligne_2.add(new Hour(6, 5, 2, 2005), 39.55);

        series_ligne_2.setNotify(true);

        // Titre du graphique
        chart.setTitle("Exemple de Graphique");

        chartPanel.setChart(chart);
    }

    public void addData1() {
        try {
            Connexion cb = new Connexion();

            ResultSet rs = Connexion.st.executeQuery("select * from marchandise ;");
            series_ligne_1.setNotify(false);
            int i = 1;
            float f;
            while (rs.next()) {

                f = (float) rs.getFloat("qte");
                series_ligne_1.add(new Hour(i, 5, 2, 2005), f);
                System.out.println("__________" + f);
                i++;
            }
            series_ligne_1.setNotify(true);


        } catch (Exception e) {
            System.out.println("Erreur d'exÃ©cution de la requÃªte");
        }
        chart.setTitle("Exemple de Graphique");
        chartPanel.setChart(chart);
    }

    public static void main(String args[]) {
        XYChartExemple xychart = new XYChartExemple();

        xychart.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        xychart.setVisible(true);
    }
}

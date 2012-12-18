
/**
 *
 * @author Houssem Eddine Lassoued
 */
import java.awt.Font;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

public class PieChartExample extends JFrame {

    private DefaultPieDataset dataset; //Objet qui va contenir les valeurs
    private JFreeChart graphe; // le graphe à dessiner

    public PieChartExample() {
        dataset = new DefaultPieDataset();
    }

    public void setValue(String title, Double numDouble) {
        dataset.setValue(title, numDouble);
    }

    public void setChart(String title) {
        graphe = ChartFactory.createPieChart3D(title, dataset, true, true, false);

        PiePlot pp = (PiePlot) graphe.getPlot();//l'Outil de dessin
        //pp.setSectionOutlinesVisible(false);
        pp.setLabelFont(new Font("SansSerif", Font.PLAIN, 12));
        pp.setNoDataMessage("Pas de données");
        pp.setCircular(true);
        pp.setLabelGap(0.02);
    }

    private JPanel createPanel() {
        return new ChartPanel(graphe);
    }

    public void Show() {
        setContentPane(createPanel());
        setVisible(true);
    }

    public static void main(String[] args) {
        Connexion con = new Connexion();
        PieChartExample j = new PieChartExample();

        //*Connexion récupération des données
        Double d;
        String ch;
        try {
            Connexion.rs = Connexion.st.executeQuery("select * from etudiant");
            while (Connexion.rs.next()) {

                d = Connexion.rs.getDouble(1);
                ch = Connexion.rs.getString(2);
                //System.out.println("" + d);
                //System.out.println("" + ch);
                
                j.setValue(ch, d);//remplissage dataset
            }

        } catch (SQLException ex) {
            System.out.println("...");
        }
        /**/
        j.setTitle("Application JFreeChart");
        j.setSize(640, 430);
        //*Valeurs Statiques
        j.setValue("UN", new Double(20.0));
        j.setValue("DEUX", new Double(10.0));
        j.setValue("TROIS", new Double(20.0));
        j.setValue("QUATRE", new Double(30.0));
        j.setValue("CINQUE", new Double(20.0));
        /**/
        j.setChart("Example Chart...");

        j.Show();
    }
}

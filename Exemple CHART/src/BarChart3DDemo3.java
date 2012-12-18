
import java.io.IOException;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

public class BarChart3DDemo3 extends ApplicationFrame {

    public BarChart3DDemo3(final String title) throws IOException {

        super(title);

        final CategoryDataset dataset = createDataset();
        final JFreeChart graphe = createChart(dataset);
        final ChartPanel chartPanel = new ChartPanel(graphe);
        chartPanel.setPreferredSize(new java.awt.Dimension(400, 400));
        setContentPane(chartPanel);

    }

    private CategoryDataset createDataset() throws IOException {

        // 0. Création d'un diagramme.
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        /* Connexion et récupération de la base de données
        Connexion con = new Connexion();
        Double d;
        String ch;
        try {
        try {
        Connexion.rs = Connexion.st.executeQuery("select * from table");
        } catch (SQLException ex) {
        }
        while (Connexion.rs.next()) {
        d = Connexion.rs.getDouble(2);
        ch = Connexion.rs.getString(1);
        System.out.println("" + d);
        System.out.println("" + ch);
        dataset.addValue(d, ch, "");
        }

        } catch (SQLException ex) {
        System.out.println("Erreur dans la base");
        }
         /**/
        //*Valeurs statiques
        dataset.addValue(10, "Taux de défaut de couverture", " ");
        dataset.addValue(12, " Taux de couverture Outdoor", " ");
        dataset.addValue(25, "Taux de couverture Indoor", " ");
        dataset.addValue(19, "Taux de couverture Incar", " ");
        /**/
        return dataset;

    }

    private JFreeChart createChart(final CategoryDataset dataset) {

        final JFreeChart chart = ChartFactory.createBarChart3D(
                " le taux des produits ", // chart title
                " ", // domain axis label
                "  Le nombre de produit ", // range axis label
                dataset, // data
                PlotOrientation.VERTICAL, // orientation
                true, // include legend
                true, // tooltips
                true // urls
                );

        final CategoryPlot plot = chart.getCategoryPlot();
        final CategoryAxis axis = plot.getDomainAxis();
        axis.setCategoryLabelPositions(
                CategoryLabelPositions.createUpRotationLabelPositions(Math.PI / 2.0));

        final CategoryItemRenderer renderer = plot.getRenderer();
        renderer.setItemLabelsVisible(true);
        

//        final BarRenderer r = (BarRenderer) renderer;
//        DecimalFormat decimalformat1 = new DecimalFormat("#.#");


        return chart;

    }

    public static void main(final String[] args) throws IOException {

        final BarChart3DDemo3 demo = new BarChart3DDemo3("Test de la couverture ");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);

    }
}

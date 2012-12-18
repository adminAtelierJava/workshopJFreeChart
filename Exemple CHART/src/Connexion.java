/**
 *
 * @author Houssem Eddine Lassoued
 */

import java.sql.*;
import java.util.Vector;

/**
 *
 * @author Houssem Eddine Lassoued
 */
public class Connexion {

    Connection con;
    static Statement st;
    public static ResultSet rs;
    static Connexion CON;

    public Connexion() {
        try {
            Class.forName("org.gjt.mm.mysql.Driver");

        } catch (Exception E) {
            System.out.println("Pas de pilote !");
        }
        try {
            String url = "jdbc:mysql://localhost/espritBD";
            con = DriverManager.getConnection(url, "root", "root");

            st = con.createStatement();
        } catch (SQLException E) {
            System.err.println(E.getMessage());
        }
    }
}

package DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author jara
 */
public class ConexionDB {

    public static Connection cn;
    private static final String driver = "com.mysql.jdbc.Driver";
    private static final String user = "grimbold";
    private static final String password = "asdf1212";
    private static final String url = "jdbc:mysql://localhost:3306/BANCO";
    // Funcion que va conectarse a mi bd de mysql

    public static Connection conector() {
        // Reseteamos a null la conexion a la bd
        try {
            Class.forName(driver);
            // Nos conectamos a la bd
            cn = (Connection) DriverManager.getConnection(url, user, password);
            // Si la conexion fue exitosa mostramos un mensaje de conexion exitosa
            if (cn != null) {
                System.out.println("Conexion establecida");
            }
            return cn;
        } // Si la conexion NO fue exitosa mostramos un mensaje de error
        catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error de conexion" + e);
        }
        return (null);
    }

    public static void cerrar() {
        try {
            if (cn != null) {
                cn.close();
            }
        } catch (Exception e) {
            System.out.println("Error: No se logro cerrar conexion: \n" + e);
        }
    }
}

package DataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author jara
 */
public class DBLimites {

    private Connection cn = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    public boolean numsPositivo(int numero1, int numero2) {
        if (numero1 > 0 && numero2 > 0) {
            System.out.println("Numeros postivos");
            return true;
        } else {
            System.out.println("Numeros Negativos");
            return false;
        }
    }

    public boolean verficarMayor(int numero1, int numero2) {
        if (numero1 < numero2) {
            System.out.println("Limite 2 mayor a limite 1");
            return true;
        } else {
            System.out.println("Limite 2 menor a limite 1");
            return false;
        }
    }

    public boolean fijarLimites(int gerente, int limit1, int limit2) {
        String sql = "INSERT INTO LIMITES VALUES(0,?,?,?);";
        try {
            cn = ConexionDB.conector();
            ps = cn.prepareStatement(sql);
            ps.setInt(1, gerente);
            ps.setInt(2, limit1);
            ps.setDouble(3, limit2);
            ps.execute();
            System.out.println("Limites Fijados!");
            return true;
        } catch (SQLException e) {
            System.out.println("Error al fijar limites: " + e);
        }
        return false;
    }

}

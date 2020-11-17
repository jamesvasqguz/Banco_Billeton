package Backend;

import DataBase.ConexionDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author jara
 */
public class GReporte4 {

    GReporte4 reporteHallado;
    Connection cn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    private int codigo;
    private String nombre;
    private String dpi;
    private int cuenta;
    private double saldo;

    public GReporte4() {
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDpi() {
        return dpi;
    }

    public void setDpi(String dpi) {
        this.dpi = dpi;
    }

    public int getCuenta() {
        return cuenta;
    }

    public void setCuenta(int cuenta) {
        this.cuenta = cuenta;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public ArrayList<GReporte4> cuentasMasDinero() {
        ArrayList<GReporte4> list = new ArrayList<GReporte4>();
        try {
            String sql = "SELECT distinct cl.codigo,cl.nombre,cl.dpi,c.codigo,c.saldo FROM CLIENTE cl INNER JOIN CUENTA c ON cl.codigo = c.cliente  ORDER BY saldo DESC LIMIT 10;";
            cn = ConexionDB.conector();
            ps = cn.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                System.out.println("Se encontró el registro");
                do {
                    GReporte4 g = new GReporte4();
                    g.setCodigo(rs.getInt("cl.codigo"));
                    g.setNombre(rs.getString("cl.nombre"));
                    g.setDpi(rs.getString("cl.dpi"));
                    g.setCuenta(rs.getInt("c.codigo"));
                    g.setSaldo(rs.getDouble("c.saldo"));
                    reporteHallado = g;
                    list.add(reporteHallado);
                } while (rs.next());
            } else {
                System.out.println(" No se encontro el registro");
            }
        } catch (Exception e) {
            System.out.println("Error en la base de datos.");
            e.printStackTrace();
            return list;
        }
        return list;
    }
}

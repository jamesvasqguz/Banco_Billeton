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
public class GReporte5 {
    GReporte5 reporteHallado;
    Connection cn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    private int codigo;
    private String nombre;
    private String dpi;
    private String sexo;

    public GReporte5() {
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

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }
    public ArrayList<GReporte5> clientesSinTransaccion(String fecha1, String fecha2) {
        ArrayList<GReporte5> list = new ArrayList<GReporte5>();
        
        System.out.println("fecha 1: "+fecha1+" fecha 2: "+fecha2);
        try {
            String sql = "SELECT cl.codigo,cl.nombre,cl.dpi,cl.sexo from CLIENTE cl INNER JOIN  (SELECT * FROM CUENTA c WHERE NOT EXISTS (SELECT * FROM TRANSACCION t WHERE c.codigo = t.cuenta AND t.fecha BETWEEN ? AND ? )) AS b ON b.cliente = cl.codigo;";
            cn = ConexionDB.conector();
            ps = cn.prepareStatement(sql);
            ps.setString(1, fecha1);
            ps.setString(2, fecha2);
            rs = ps.executeQuery();
            if (rs.next()) {
                System.out.println("Se encontró el registro");
                do {
                    GReporte5 g = new GReporte5();
                    g.setCodigo(rs.getInt("cl.codigo"));
                    g.setNombre(rs.getString("cl.nombre"));
                    g.setDpi(rs.getString("cl.dpi"));
                    g.setSexo(rs.getString("cl.sexo"));
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

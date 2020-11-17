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
public class GReporte7 {

    GReporte7 reporteHallado;
    Connection cn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    private int cajero;
    private int cantidad;

    public GReporte7() {
    }

    public int getCajero() {
        return cajero;
    }

    public void setCajero(int cajero) {
        this.cajero = cajero;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public ArrayList<GReporte7> cajeroMasTransacciones(String fecha1, String fecha2) {
        ArrayList<GReporte7> list = new ArrayList<GReporte7>();
        System.out.println("fecha 1: "+fecha1+" fecha 2: "+fecha2);
        try {
            String sql = "SELECT cajero,count(cajero) as cantidad FROM TRANSACCION WHERE fecha BETWEEN ? AND ? group by cajero having count(cajero)>=1 ORDER BY cantidad DESC;";
            cn = ConexionDB.conector();
            ps = cn.prepareStatement(sql);
            ps.setString(1, fecha1);
            ps.setString(2, fecha2);
            rs = ps.executeQuery();
            if (rs.next()) {
                System.out.println("Se encontró el registro");
                do {
                    GReporte7 g = new GReporte7();
                    g.setCajero(rs.getInt("cajero"));
                    g.setCantidad(rs.getInt("cantidad"));
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

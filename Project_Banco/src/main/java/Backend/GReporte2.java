package Backend;

import DataBase.ConexionDB;
import DataBase.DBRestrincciones;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author jara
 */
public class GReporte2 {

    GReporte2 reporteHallado;
    Connection cn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    private int codigo;
    private int cuenta;
    private String nombre;
    private String fecha;
    private double monto;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getCuenta() {
        return cuenta;
    }

    public void setCuenta(int cuenta) {
        this.cuenta = cuenta;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public GReporte2() {
    }

    public ArrayList<GReporte2> transLimit1(int gerente) {
        DBRestrincciones res = new DBRestrincciones();
        ArrayList<GReporte2> list = new ArrayList<GReporte2>();
        
        System.out.println("Gerente: " + gerente);
        int limite = res.limite1(gerente);
        
        try {
            String sql = "SELECT T.codigo,T.cuenta,I.nombre,T.fecha,T.monto FROM TRANSACCION T INNER JOIN CUENTA C ON T.cuenta=C.codigo AND T.monto>? INNER JOIN CLIENTE I ON I.codigo=C.cliente;";
            cn = ConexionDB.conector();
            ps = cn.prepareStatement(sql);
            ps.setInt(1, limite);
            rs = ps.executeQuery();
            if (rs.next()) {
                System.out.println("Se encontró el registro");
                do {
                    GReporte2 g = new GReporte2();
                    g.setCodigo(rs.getInt("T.codigo"));
                    g.setCuenta(rs.getInt("T.cuenta"));
                    g.setNombre(rs.getString("I.nombre"));
                    g.setFecha(rs.getString("T.fecha"));
                    g.setMonto(rs.getDouble("T.monto"));
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

package Backend;

import DataBase.ConexionDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jara
 */
public class CAReporte1 {

    Connection cn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    private int codigo;
    private int noCuenta;
    private String fecha;
    private String hora;
    private String tipo;
    private double monto;
    private int codigoCajero;

    public CAReporte1() {
    }

    public CAReporte1(int codigo, int noCuenta, String fecha, String hora, String tipo, double monto, int codigoCajero) {
        this.codigo = codigo;
        this.noCuenta = noCuenta;
        this.fecha = fecha;
        this.hora = hora;
        this.tipo = tipo;
        this.monto = monto;
        this.codigoCajero = codigoCajero;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getNoCuenta() {
        return noCuenta;
    }

    public void setNoCuenta(int noCuenta) {
        this.noCuenta = noCuenta;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public int getCodigoCajero() {
        return codigoCajero;
    }

    public void setCodigoCajero(int codigoCajero) {
        this.codigoCajero = codigoCajero;
    }

    public List<CAReporte1> reporte1Cajero(int codigoCajero, String horarioEntrada, String horarioSalida) {
        List<CAReporte1> busqueda = new ArrayList<>();
        busqueda.clear();
        try {
            String sql = "SELECT codigo, cuenta, fecha, hora, tipo, monto, cajero FROM TRANSACCION WHERE cajero = ? AND fecha = CURDATE() AND hora BETWEEN ? AND ? ORDER BY tipo ASC";
            cn = ConexionDB.conector();
            ps = cn.prepareStatement(sql);
            ps.setInt(1, codigoCajero);
            ps.setString(2, horarioEntrada);
            ps.setString(3, horarioSalida);
            rs = ps.executeQuery();
            while (rs.next()) {
                busqueda.add(new CAReporte1(
                        rs.getInt("codigo"),
                        rs.getInt("cuenta"),
                        rs.getString("fecha"),
                        rs.getString("hora"),
                        rs.getString("tipo"),
                        rs.getDouble("monto"),
                        rs.getInt("cajero")));
            }
        } catch (Exception e) {
        }
        return busqueda;
    }

    public double totalDepositos(int codigoCajero, String horarioEntrada, String horarioSalida) {
        double totalDepositos = 0;
        try {
            String sql = "SELECT SUM(monto) FROM TRANSACCION WHERE cajero = ? AND fecha = CURDATE() AND hora BETWEEN ? AND ? AND tipo = 'CREDITO'";
            cn = ConexionDB.conector();
            ps = cn.prepareStatement(sql);
            ps.setInt(1, codigoCajero);
            ps.setString(2, horarioEntrada);
            ps.setString(3, horarioSalida);
            rs = ps.executeQuery();
            while (rs.next()) {
                totalDepositos = rs.getDouble("SUM(monto)");
            }
        } catch (SQLException e) {
        }
        return totalDepositos;
    }

    public double totalRetiros(int codigoCajero, String horarioEntrada, String horarioSalida) {
        double totalRetiros = 0;
        try {
            String sql = "SELECT SUM(monto) FROM TRANSACCION WHERE cajero = ? AND fecha = CURDATE() AND hora BETWEEN ? AND ? AND tipo = 'DEBITO'";
            cn = ConexionDB.conector();
            ps = cn.prepareStatement(sql);
            ps.setInt(1, codigoCajero);
            ps.setString(2, horarioEntrada);
            ps.setString(3, horarioSalida);
            rs = ps.executeQuery();
            while (rs.next()) {
                totalRetiros = rs.getDouble("SUM(monto)");
            }
        } catch (SQLException e) {
        }
        return totalRetiros;
    }

    public double saldoCaja(int codigoCajero, String horarioEntrada, String horarioSalida) {
        double saldoCaja = totalDepositos(codigoCajero, horarioEntrada, horarioSalida) - totalRetiros(codigoCajero, horarioEntrada, horarioSalida);
        return saldoCaja;
    }
}

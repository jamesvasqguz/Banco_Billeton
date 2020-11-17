package DataBase;

import Backend.Cajero;
import Backend.Cliente;
import Backend.Gerente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author jara
 */
public class DBActualizaciones {

    private Gerente g;
    private Cajero ca;
    private Cliente cl;
    private Connection cn = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    public ArrayList<Gerente> datosGerente(int id) {
        ArrayList<Gerente> list = new ArrayList<Gerente>();
        System.out.println("Codigo: " + id);
        String sql = "SELECT * FROM GERENTE WHERE codigo=?;";
        try {
            cn = ConexionDB.conector();
            ps = cn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                g = new Gerente();
                g.setCodigo(rs.getInt("codigo"));
                g.setNombre(rs.getString("nombre"));
                g.setTurno(rs.getString("turno"));
                g.setDpi(rs.getString("dpi"));
                g.setDireccion(rs.getString("direccion"));
                g.setSexo(rs.getString("sexo"));
                list.add(g);
                System.out.println("Gerente: " + list);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener al Gerente: " + e);
            System.out.println(e.getMessage());
        } catch (Exception ex) {
            System.out.println("Error pues: " + ex);
            System.out.println(ex.getMessage());
        } finally {
            try {
                cn.close();
                ps.close();
                rs.close();
            } catch (Exception e) {
            }
        }
        return list;
    }

    public ArrayList<Cajero> datosCajero(int id) {
        ArrayList<Cajero> list = new ArrayList<Cajero>();
        System.out.println("Codigo: " + id);
        String sql = "SELECT * FROM CAJERO WHERE codigo=?;";
        try {
            cn = ConexionDB.conector();
            ps = cn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                ca = new Cajero();
                ca.setCodigo(rs.getInt("codigo"));
                ca.setNombre(rs.getString("nombre"));
                ca.setTurno(rs.getString("turno"));
                ca.setDpi(rs.getString("dpi"));
                ca.setDireccion(rs.getString("direccion"));
                ca.setSexo(rs.getString("sexo"));
                list.add(ca);
                System.out.println("Cajero: " + list);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener al Cajero: " + e);
            System.out.println(e.getMessage());
        } catch (Exception ex) {
            System.out.println("Error pues: " + ex);
            System.out.println(ex.getMessage());
        } finally {
            try {
                cn.close();
                ps.close();
                rs.close();
            } catch (Exception e) {
            }
        }
        return list;
    }

    public ArrayList<Cliente> datosCliente(int id) {
        ArrayList<Cliente> list = new ArrayList<Cliente>();
        System.out.println("Codigo de cliente: " + id);
        String sql = "SELECT * FROM CLIENTE WHERE codigo=?;";
        try {
            cn = ConexionDB.conector();
            ps = cn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                cl = new Cliente();
                cl.setCodigo(rs.getInt("codigo"));
                cl.setNombre(rs.getString("nombre"));
                cl.setDpi(rs.getString("dpi"));
                cl.setFecha(rs.getString("nacimiento"));
                cl.setDireccion(rs.getString("direccion"));
                cl.setSexo(rs.getString("sexo"));
                cl.setArchivo(rs.getBytes("pdf"));
                list.add(cl);
                System.out.println("Cliente: " + list);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener al Cliente: " + e);
            System.out.println(e.getMessage());
        } catch (Exception ex) {
            System.out.println("Error pues: " + ex);
            System.out.println(ex.getMessage());
        } finally {
            try {
                cn.close();
                ps.close();
                rs.close();
            } catch (SQLException e) {
            }
        }
        return list;
    }

    public boolean finalizarSolicitud(int codigo,String estado) {
        String sql = "UPDATE ASOCIACION SET estado=? WHERE codigo=?;";
        String stado = "";
        if (estado.equals("ACEPTAR")) {
            stado = "ACEPTADA";
        } else {
            stado = "RECHAZADA";
        }
        
        try {
            cn = ConexionDB.conector();
            ps = cn.prepareStatement(sql);
            ps.setString(1, stado);
            ps.setInt(2, codigo);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error al actualizar solicitud: " + e);
        }
        return false;
    }
}

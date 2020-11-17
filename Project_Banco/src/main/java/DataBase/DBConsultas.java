package DataBase;

import Backend.Asociacion;
import Backend.Cliente;
import Backend.Cuenta;
import Backend.Transaccion;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author jara
 */
public class DBConsultas {

    private Cliente cl;
    private Cuenta cu;
    private Transaccion t;
    private Asociacion aso;
    private Connection cn = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    //consulta de estado de cuentas
    public ArrayList<Cuenta> datosCuentas(int id) {
        ArrayList<Cuenta> list = new ArrayList<Cuenta>();
        System.out.println("Codigo: " + id);
        String sql = "SELECT * FROM CUENTA WHERE cliente=?;";
        try {
            cn = ConexionDB.conector();
            ps = cn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                cu = new Cuenta();
                cu.setCodigo(rs.getInt("codigo"));
                cu.setCliente(rs.getInt("cliente"));
                cu.setFecha(rs.getString("creacion"));
                cu.setSaldo(rs.getDouble("saldo"));
                list.add(cu);
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
            } catch (Exception e) {
            }
        }
        return list;
    }

    //consulta de movimientos cuenta
    public ArrayList<Transaccion> movimientos(int cuenta) {
        ArrayList<Transaccion> list = new ArrayList<Transaccion>();
        System.out.println("Codigo: " + cuenta);
        String sql = "SELECT * FROM TRANSACCION WHERE cuenta=?;";
        try {
            cn = ConexionDB.conector();
            ps = cn.prepareStatement(sql);
            ps.setInt(1, cuenta);
            rs = ps.executeQuery();
            while (rs.next()) {
                t = new Transaccion();
                t.setCodigo(rs.getInt("codigo"));
                t.setCuenta(rs.getInt("cuenta"));
                t.setFecha(rs.getString("fecha"));
                t.setHora(rs.getString("hora"));
                t.setTipo(rs.getString("tipo"));
                t.setMonto(rs.getDouble("monto"));
                t.setCajero(rs.getInt("cajero"));
                list.add(t);
                System.out.println("Transacciones: " + list);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener al transaccion: " + e);
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

    //asociaciones por aceptar
    public ArrayList<Asociacion> asociacionesPendientes(int cliente) {
        ArrayList<Asociacion> list = new ArrayList<Asociacion>();
        System.out.println("Cliente: " + cliente);
        String sql = "SELECT A.codigo,A.ccuenta,A.ccliente,A.estado FROM ASOCIACION A INNER JOIN CUENTA C ON A.ccuenta=C.codigo AND A.estado='PENDIENTE' AND C.cliente=?;";
        try {
            cn = ConexionDB.conector();
            ps = cn.prepareStatement(sql);
            ps.setInt(1, cliente);
            rs = ps.executeQuery();
            while (rs.next()) {
                aso = new Asociacion();
                aso.setCodigo(rs.getInt("codigo"));
                aso.setCuenta(rs.getInt("ccuenta"));
                aso.setCliente(rs.getInt("ccliente"));
                aso.setEstado(rs.getString("estado"));
                list.add(aso);
                System.out.println("Asociaciones: " + list);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener la asociacion: " + e);
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

    //asociaciones aceptadas o rechazadas
    public ArrayList<Asociacion> asociacionesFin(int cliente) {
        ArrayList<Asociacion> list = new ArrayList<Asociacion>();
        System.out.println("Cliente: " + cliente);
        String sql = "SELECT A.codigo,A.ccuenta,A.ccliente,A.estado FROM ASOCIACION A INNER JOIN CUENTA C ON A.ccuenta=C.codigo AND A.estado<>'PENDIENTE' AND C.cliente=?;";
        try {
            cn = ConexionDB.conector();
            ps = cn.prepareStatement(sql);
            ps.setInt(1, cliente);
            rs = ps.executeQuery();
            while (rs.next()) {
                aso = new Asociacion();
                aso.setCodigo(rs.getInt("codigo"));
                aso.setCuenta(rs.getInt("ccuenta"));
                aso.setCliente(rs.getInt("ccliente"));
                aso.setEstado(rs.getString("estado"));
                list.add(aso);
                System.out.println("Asociaciones: " + list);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener la asociacion: " + e);
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

    public boolean verificarAso(int cliente, int cuenta) {
        String sql = "SELECT * FROM ASOCIACION WHERE ccuenta=? AND ccliente=? AND estado='ACEPTADA';";
        try {
            cn = ConexionDB.conector();
            ps = cn.prepareStatement(sql);
            ps.setInt(1, cuenta);
            ps.setInt(2, cliente);
            rs = ps.executeQuery();
            if (rs.next()) {
                System.out.println("Asoacion existente y aceptada");
                return true;
            } else {
                System.out.println("Asoacion inexistente o no aceptada");
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Error al verificar la asociacion! " + e);
        }
        return false;
    }

    public boolean verificarFondos(int cliente, int cuenta, double monto) {
        String sql = "SELECT * FROM CUENTA WHERE codigo=? AND cliente=? AND saldo>=?;";
        try {
            cn = ConexionDB.conector();
            ps = cn.prepareStatement(sql);
            ps.setInt(1, cuenta);
            ps.setInt(2, cliente);
            ps.setDouble(3, monto);
            rs = ps.executeQuery();
            if (rs.next()) {
                System.out.println("Cuenta que envia con fondos suficientes!");
                return true;
            } else {
                System.out.println("Cuenta que envia sin fondos!");
                return false;
            }
        } catch (SQLException e) {
        }
        return false;
    }

    public int consultarCliente(int cuenta) {
        int cliente = 0;
        String sql = "SELECT * FROM CUENTA WHERE codigo=?;";
        try {
            cn = ConexionDB.conector();
            ps = cn.prepareStatement(sql);
            ps.setInt(1, cuenta);
            rs = ps.executeQuery();
            if (rs.next()) {
                cliente = rs.getInt("cliente");
                System.out.println("Dueño de la cuenta: " + cliente);
            } else {
                System.out.println("Esta cuenta no pertenece a nadie");
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener al cliente");
        }
        return cliente;
    }

    public boolean existenciaCliente(int cliente) {
        String sql = "SELECT * FROM CLIENTE WHERE codigo=?;";
        try {
            cn = ConexionDB.conector();
            ps = cn.prepareStatement(sql);
            ps.setInt(1, cliente);
            rs = ps.executeQuery();
            if (rs.next()) {
                System.out.println("Cliente: " + rs.getString("nombre"));
                return true;
            } else {
                System.out.println("Cliente Inexistente!");
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener al cliente: " + e);
        }
        return false;
    }

    public boolean numPositivo(double numero) {
        if (numero > 0) {
            System.out.println("si es postivo");
            return true;
        } else {
            System.out.println("No es positivo");
            return false;
        }
    }

    public int codigoCuenta(int cliente, double saldo) {
        int codigoCC = 0;
        String sql = "SELECT * FROM CUENTA WHERE cliente=? AND saldo=? AND creacion=CURDATE();";
        try {
            cn = ConexionDB.conector();
            ps = cn.prepareStatement(sql);
            ps.setInt(1, cliente);
            ps.setDouble(2, saldo);
            rs = ps.executeQuery();
            if (rs.next()) {
                codigoCC = rs.getInt("codigo");
                System.out.println("Codigo de la cuenta creada: " + codigoCC);
                return codigoCC;
            } else {
                System.out.println("Cliente Inexistente!");
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener al cliente: " + e);
        }
        return codigoCC;
    }

    public boolean primeraCuenta(int cliente) {
        String sql = "SELECT * FROM CUENTA WHERE cliente=?;";
        try {
            cn = ConexionDB.conector();
            ps = cn.prepareStatement(sql);
            ps.setInt(1, cliente);
            rs = ps.executeQuery();
            if (rs.next()) {
                System.out.println("Cliente ya cuenta con cuentas");
                return true;
            } else {
                System.out.println("Primer Cuenta!");
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener al cliente: " + e);
        }
        return false;
    }

    public boolean verificarDPI(String dpi, String fecha) {
        String sql = "SELECT dpi,nacimiento FROM CLIENTE WHERE dpi=? AND nacimiento=?;";
        String dpii;
        try {
            cn = ConexionDB.conector();
            ps = cn.prepareStatement(sql);
            ps.setString(1, dpi);
            ps.setString(2, fecha);
            rs = ps.executeQuery();
            if (rs.next()) {
                dpii = rs.getString("dpi");
                System.out.println("Cliente ya existente: " + dpii);
                return true;
            } else {
                System.out.println("Cliente Inexistente!");
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener al cliente: " + e);
        }
        return false;
    }

    public int solicitarCodigoCliente(String dpi, String fecha) {
        String sql = "SELECT codigo FROM CLIENTE WHERE dpi=? AND nacimiento=?;";
        int codigo = 0;
        try {
            cn = ConexionDB.conector();
            ps = cn.prepareStatement(sql);
            ps.setString(1, dpi);
            ps.setString(2, fecha);
            rs = ps.executeQuery();
            if (rs.next()) {
                codigo = rs.getInt("codigo");
                System.out.println("Codigo del cliente nuevo: " + codigo);
                return codigo;
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener al cliente: " + e);
        }
        return codigo;
    }

    public boolean verificarUser(int codigo, String rol) {
        String sql = "SELECT registro,roll FROM USUARIO WHERE registro=? AND roll=?;";
        try {
            cn = ConexionDB.conector();
            ps = cn.prepareStatement(sql);
            ps.setInt(1, codigo);
            ps.setString(2, rol);
            rs = ps.executeQuery();
            if (rs.next()) {
                System.out.println("Usuario ya existente: ");
                return true;
            } else {
                System.out.println("Usuario Inexistente!");
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener al Usuario: " + e);
        }
        return false;
    }

    public int solicitarCodigoU(int codigo, String rol) {
        String sql = "SELECT user FROM USUARIO WHERE registro=? AND roll=?;";
        int user = 0;
        try {
            cn = ConexionDB.conector();
            ps = cn.prepareStatement(sql);
            ps.setInt(1, codigo);
            ps.setString(2, rol);
            rs = ps.executeQuery();
            if (rs.next()) {
                user = rs.getInt("user");
                System.out.println("Codigo del USUARIO nuevo: " + user);
                return user;
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener al USUARIO: " + e);
        }
        return user;
    }

    public boolean verificarEmpleado(String dpi, String rol) {
        String sql = "SELECT dpi FROM " + rol + " WHERE dpi=?;";
        try {
            cn = ConexionDB.conector();
            ps = cn.prepareStatement(sql);
            ps.setString(1, dpi);
            rs = ps.executeQuery();
            if (rs.next()) {
                System.out.println("Empleado " + rol + " ya existente: ");
                return true;
            } else {
                System.out.println("Empleado " + rol + " Inexistente!");
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener al cliente: " + e);
        }
        return false;
    }

    public int solicitarCodigoEmpleado(String dpi, String rol) {
        String sql = "SELECT codigo FROM " + rol + " WHERE dpi=?;";
        int codigo = 0;
        try {
            cn = ConexionDB.conector();
            ps = cn.prepareStatement(sql);
            ps.setString(1, dpi);
            rs = ps.executeQuery();
            if (rs.next()) {
                codigo = rs.getInt("codigo");
                System.out.println("Codigo del Empleado " + rol + " nuevo: " + codigo);
                return codigo;
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener al Empleado " + rol + ": " + e);
        }
        return codigo;
    }
}

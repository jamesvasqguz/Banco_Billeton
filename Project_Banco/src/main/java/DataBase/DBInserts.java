package DataBase;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author jara
 */
public class DBInserts {

    private Connection cn = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    public boolean enviarSolicitud(int cuenta, int cliente) {
        String estado = "PENDIENTE";
        String sql = "INSERT INTO ASOCIACION (ccuenta,ccliente,estado) VALUES(?,?,?);";
        String sql1 = "SELECT estado,count(estado) FROM ASOCIACION WHERE estado='RECHAZADA' AND ccuenta=? AND ccliente=? group by estado having count(estado)>=1;";
        String sql2 = "SELECT * FROM ASOCIACION WHERE ccuenta=? AND ccliente=?;";
        int cantidad;
        cn = ConexionDB.conector();

        try {
            ps = cn.prepareStatement(sql2);
            ps.setInt(1, cuenta);
            ps.setInt(2, cliente);
            rs = ps.executeQuery();
            if (rs.next()) {
                ps = cn.prepareStatement(sql1);
                ps.setInt(1, cuenta);
                ps.setInt(2, cliente);
                rs = ps.executeQuery();
                if (rs.next()) {
                    cantidad = rs.getInt("count(estado)");
                    System.out.println("cantidad: " + cantidad);
                    if (cantidad < 3) {
                        ps = cn.prepareStatement(sql);
                        ps.setInt(1, cuenta);
                        ps.setInt(2, cliente);
                        ps.setString(3, estado);
                        ps.execute();
                        return true;
                    } else {
                        return false;
                    }
                }
            } else {
                ps = cn.prepareStatement(sql);
                ps.setInt(1, cuenta);
                ps.setInt(2, cliente);
                ps.setString(3, estado);
                ps.execute();
                return true;
            }
        } catch (SQLException e) {
        }
        return false;
    }

    public boolean debito(int cuenta, double monto, int cajero) {
        double saldo;
        double nuevoSaldo;
        String sql = "SELECT saldo FROM CUENTA WHERE codigo=?;";
        String sql1 = "UPDATE CUENTA SET saldo=? WHERE codigo=?;";
        String sql2 = "INSERT INTO TRANSACCION (cuenta,fecha,hora,tipo,monto,cajero) VALUES(?,CURDATE(),CURTIME(),'DEBITO',?,?);";
        try {
            cn = ConexionDB.conector();
            ps = cn.prepareStatement(sql);
            ps.setInt(1, cuenta);
            rs = ps.executeQuery();
            if (rs.next()) {
                saldo = rs.getDouble("saldo");
                System.out.println("saldo: " + saldo);
                nuevoSaldo = saldo - monto;
                System.out.println("nuevo saldo: " + nuevoSaldo);

                ps = cn.prepareStatement(sql1);
                ps.setDouble(1, nuevoSaldo);
                ps.setInt(2, cuenta);
                ps.executeUpdate();

                try {
                    ps = cn.prepareStatement(sql2);
                    ps.setInt(1, cuenta);
                    ps.setDouble(2, monto);
                    ps.setInt(3, cajero);
                    ps.execute();
                } catch (SQLException e) {
                    System.out.println("Error al ingresar la transaccion: " + e);
                }
                System.out.println("Se llevo a cabo todas las transacciones");
                return true;
            } else {
                System.out.println("No se llevo a cabo la transaccion");
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Error al realizar la transaccion: " + e);
        }
        return false;
    }

    public boolean credito(int cuenta, double monto, int cajero) {
        double saldo;
        double nuevoSaldo;
        String sql = "SELECT saldo FROM CUENTA WHERE codigo=?;";
        String sql1 = "UPDATE CUENTA SET saldo=? WHERE codigo=?;";
        String sql2 = "INSERT INTO TRANSACCION (cuenta,fecha,hora,tipo,monto,cajero) VALUES(?,CURDATE(),CURTIME(),'CREDITO',?,?);";
        try {
            cn = ConexionDB.conector();
            ps = cn.prepareStatement(sql);
            ps.setInt(1, cuenta);
            rs = ps.executeQuery();
            if (rs.next()) {
                saldo = rs.getDouble("saldo");
                System.out.println("saldo: " + saldo);
                nuevoSaldo = saldo + monto;
                System.out.println("nuevo saldo: " + nuevoSaldo);

                ps = cn.prepareStatement(sql1);
                ps.setDouble(1, nuevoSaldo);
                ps.setInt(2, cuenta);
                ps.executeUpdate();
                try {
                    ps = cn.prepareStatement(sql2);
                    ps.setInt(1, cuenta);
                    ps.setDouble(2, monto);
                    ps.setInt(3, cajero);
                    ps.execute();
                } catch (SQLException e) {
                    System.out.println("Error al ingresar la transaccion: " + e);
                }

                System.out.println("Se llevo a cabo todas las transacciones");
                return true;
            } else {
                System.out.println("No se llevo a cabo la transaccion");
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Error al realizar la transaccion: " + e);
        }
        return false;
    }

    public boolean crearCB(int cliente, double saldo) {
        String sql = "INSERT INTO CUENTA VALUES(?,?,CURDATE(),?);";
        try {
            cn = ConexionDB.conector();
            ps = cn.prepareStatement(sql);
            ps.setInt(1, 0);
            ps.setInt(2, cliente);
            ps.setDouble(3, saldo);
            ps.execute();
            System.out.println("Cuenta creada con exito!");
            return true;
        } catch (SQLException e) {
            System.out.println("Error al crear la cuenta: " + e);
        }
        return false;
    }

    public boolean nuevoCliente(String nombre, String dpi, String fecha, String direccion, String sexo, String arch) throws FileNotFoundException {
        FileInputStream input = null;
        String sql = "INSERT INTO CLIENTE(codigo,nombre,dpi,nacimiento,direccion,sexo,pdf) VALUES(0,?,?,?,?,?,?);";
        File pdf = new File(getClass().getResource("/" + arch + "").getFile());
        System.out.println("Archivo pdf: " + pdf.toString());
        input = new FileInputStream(pdf);
        Date dt = Date.valueOf(fecha);
        try {
            cn = ConexionDB.conector();
            if (input != null) {
                ps = cn.prepareStatement(sql);
                ps.setString(1, nombre);
                ps.setString(2, dpi);
                ps.setDate(3, dt);
                ps.setString(4, direccion);
                ps.setString(5, sexo);
                ps.setBlob(6, input);
                ps.executeUpdate();
                System.out.println("Cliente ingresado con exito");
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error al ingresar al Cliente: " + e);
            return false;
        }
        return false;
    }

    public boolean crearUser(int codigo, String rol, String pass) {
        String sql = "INSERT INTO USUARIO VALUES(0,?,?,?);";
        try {
            cn = ConexionDB.conector();
            ps = cn.prepareStatement(sql);
            ps.setInt(1, codigo);
            ps.setString(2, rol);
            ps.setString(3, pass);
            ps.execute();
            System.out.println("Usuario creado con exito!");
            return true;
        } catch (SQLException e) {
            System.out.println("Error al crear el USUARIO: " + e);
        }
        return false;
    }

    public boolean crearEmpleado(String nombre, String turno, String rol, String dpi, String direccion, String sexo) {
        String sql = "INSERT INTO " + rol + " VALUES(0,?,?,?,?,?);";
        System.out.println("query: " + sql);
        try {
            cn = ConexionDB.conector();
            ps = cn.prepareStatement(sql);
            ps.setString(1, nombre);
            ps.setString(2, turno);
            ps.setString(3, dpi);
            ps.setString(4, direccion);
            ps.setString(5, sexo);
            ps.execute();
            System.out.println("Empleado " + rol + " creado con exito!");
            return true;
        } catch (SQLException e) {
            System.out.println("Error al crear el " + rol + ": " + e);
        }
        return false;
    }
}

package DataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author jara
 */
public class DBRestrincciones {

    private Connection cn = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private String horario;
    String hora1 = "";
    String hora2 = "";

    //Registringir las operaciones del Usuario
    public boolean enHorario(int codigo, String rol) throws ParseException {
        DateFormat df = new SimpleDateFormat("HH:mm");
        Date date = new Date();
        System.out.println("Hora actual: " + df.format(date));

        String sql = "SELECT codigo,turno FROM " + rol + " WHERE codigo=?;";
        System.out.println("Rol: " + rol);
        System.out.println("Codigo: " + codigo);

        try {
            cn = ConexionDB.conector();
            ps = cn.prepareStatement(sql);
            ps.setInt(1, codigo);
            rs = ps.executeQuery();
            if (rs.next()) {
                horario = rs.getString("turno");
                System.out.println("Turno: " + horario);

                if (horario.equals("MATUTINO")) {
                    hora1 = "06:00";
                    hora2 = "14:30";
                } else if (horario.equals("VESPERTINO")) {
                    hora1 = "13:00";
                    hora2 = "22:00";
                } else if (horario.equals("Toda hora")) {
                    hora1 = "00:00";
                    hora2 = "11:59";
                }

                System.out.println("hora1: " + hora1 + " hora2: " + hora2);
                Date inicio = new SimpleDateFormat("HH:mm").parse(hora1.trim());
                System.out.println("Inicio: " + inicio);
                Date fin = new SimpleDateFormat("HH:mm").parse(hora2.trim());
                System.out.println("Fin: " + fin);
                Date actual = new SimpleDateFormat("HH:mm").parse(getHoraActual().trim());
                System.out.println("Actual: " + actual);
                if (actual.after(inicio) && actual.before(fin)) {
                    return true;
                } else {
                    return false;
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener el horario: " + e);
        }
        return false;
    }

    public static String getHoraActual() {
        Date ahora = new Date();
        SimpleDateFormat formateador = new SimpleDateFormat("HH:mm");
        return formateador.format(ahora);
    }

    public boolean existenLimites(int gerente) {
        String sql = "SELECT limite_1,limite_2 FROM LIMITES WHERE gerente=?;";
        try {
            cn = ConexionDB.conector();
            ps = cn.prepareStatement(sql);
            ps.setInt(1, gerente);
            rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Error al ver si hay limites existentes: " + e);
        }
        return false;
    }

    public int limite1(int gerente) {
        int limit1 = 0;
        String sql = "SELECT limite_1 FROM LIMITES WHERE gerente=?;";
        try {
            cn = ConexionDB.conector();
            ps = cn.prepareStatement(sql);
            ps.setInt(1, gerente);
            rs = ps.executeQuery();
            if (rs.next()) {
                limit1 = rs.getInt("limite_1");
                return limit1;
            } else {
                limit1 = 0;
                return limit1;
            }
        } catch (SQLException e) {
            System.out.println("Error al ver si hay limites existentes: " + e);
        }
        return 0;
    }

    public int limite2(int gerente) {
        int limit2 = 0;
        String sql = "SELECT limite_2 FROM LIMITES WHERE gerente=?;";
        try {
            cn = ConexionDB.conector();
            ps = cn.prepareStatement(sql);
            ps.setInt(1, gerente);
            rs = ps.executeQuery();
            if (rs.next()) {
                limit2 = rs.getInt("limite_2");
                return limit2;
            } else {
                limit2 = 0;
                return limit2;
            }
        } catch (SQLException e) {
            System.out.println("Error al ver si hay limites existentes: " + e);
        }
        return 0;
    }
}

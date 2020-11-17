package DataBase;

import java.io.File;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author jara
 */
public class Archivos {

    private DBGerente dBGerente;
    private Connection cn = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    public boolean registrosDB() {
        String sql = "SELECT * FROM ARCHIVO;";
        try {
            cn = ConexionDB.conector();
            ps = cn.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                System.out.println("Hay registros en la DB");
                return true;
            } else {
                System.out.println("ARCHIVO GUARDADO!");
                return false;
            }
        } catch (SQLException e) {
            System.out.println("ERROR AL INTENTAR VER REGISTROS EN LA DB: " + e);
        }
        return false;
    }

    public boolean insertarArchivo(String xml) {
        try {
            cn = ConexionDB.conector();
            ps = cn.prepareStatement("INSERT INTO ARCHIVO (registro,archivo) VALUES (?,?)");
            ps.setInt(1, 0);
            ps.setString(2, xml);
            ps.executeUpdate();
            System.out.println("ARCHIVO Insertado!");
            return true;
        } catch (SQLException e) {
            System.out.println("ERROR AL INTENTAR INSERTAR ARCHIVO: " + e);
        }
        return false;
    }

    public void importarRegistros(String path) {
        dBGerente = new DBGerente();
        dBGerente.aggGerente(path);
        for (int i = 0; i < dBGerente.getGerentes().size(); i++) {
            try {
                dBGerente.insertGerente(dBGerente.getGerentes().get(i));
                System.out.println("Ingresando gerentes al sistema");
            } catch (Exception e) {
            }

        }
        //INGRESANDO LOS PERFILES A LA BASE DE DATOS
        for (int i = 0; i < dBGerente.getUsuarios().size(); i++) {
            try {
                dBGerente.insertUGerente(dBGerente.getUsuarios().get(i));
                System.out.println("Ingresando usuarios al sistema");
            } catch (Exception e) {
            }

        }
    }
}

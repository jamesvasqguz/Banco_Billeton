package DataBase;

import Backend.Gerente;
import Backend.Usuario;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author jara
 */
public class DBGerente {

    private static final String rollG = "GERENTE";
    private ArrayList<Gerente> gerentes;
    private ArrayList<Usuario> usuarios;
    private Connection cn = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    public DBGerente() {
        gerentes = new ArrayList<>();
        usuarios = new ArrayList<>();

    }

    public void aggGerente(String path) {
        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse( new File(path));

            NodeList nodeList = document.getElementsByTagName("GERENTE");
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) node;
                    String codigo = eElement.getElementsByTagName("CODIGO").item(0).getTextContent();
                    String nombre = eElement.getElementsByTagName("NOMBRE").item(0).getTextContent();
                    String turno = eElement.getElementsByTagName("TURNO").item(0).getTextContent();
                    String dpi = eElement.getElementsByTagName("DPI").item(0).getTextContent();
                    String direccion = eElement.getElementsByTagName("DIRECCION").item(0).getTextContent();
                    String sexo = eElement.getElementsByTagName("SEXO").item(0).getTextContent();
                    String pass = eElement.getElementsByTagName("PASSWORD").item(0).getTextContent();
                    //CREANDO GERENTE Y USUARIO
                    int intCodigo = Integer.parseInt(codigo);
                    Gerente gerente = new Gerente(intCodigo, nombre, turno, dpi, direccion, sexo);
                    Usuario usuario = new Usuario(0, intCodigo, rollG, pass);
                    gerentes.add(gerente);
                    usuarios.add(usuario);
                }
            }

        } catch (IOException | ParserConfigurationException | SAXException e) {
        }
    }

    public void insertGerente(Gerente gerente) {
        try {
            String sql="INSERT INTO GERENTE VALUES(?,?,?,?,?,?);";
            cn = ConexionDB.conector();
            ps = cn.prepareStatement(sql);
            ps.setInt(0, gerente.getCodigo());
            ps.setString(1, gerente.getNombre());
            ps.setString(2, gerente.getTurno());
            ps.setString(3, gerente.getDpi());
            ps.setString(4, gerente.getDireccion());
            ps.setString(5, gerente.getSexo());
            ps.executeUpdate();
            System.out.println("Gerente Ingresado con Exito!");
        } catch (SQLException e) {
            System.out.println("Error al ingresar a la DB: "+e);
        }
    }

    public void insertUGerente(Usuario usuario) {
        try {
            String sql="INSERT INTO USUARIO (user,registro,roll,pass) VALUES(?,?,?,?);";
            cn = ConexionDB.conector();
            ps = cn.prepareStatement(sql);
            ps.setInt(0, usuario.getCodigo());
            ps.setInt(1,usuario.getUser());
            ps.setString(2, usuario.getRoll());
            ps.setString(3, usuario.getPass());
            ps.executeUpdate();
            System.out.println("Usuario Ingresado con Exito!");
        } catch (SQLException e) {
            System.out.println("Error al ingresar a la DB: "+e);
        }
    }

    public ArrayList<Gerente> getGerentes() {
        return gerentes;
    }

    public void setGerentes(ArrayList<Gerente> gerentes) {
        this.gerentes = gerentes;
    }

    public ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(ArrayList<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

}

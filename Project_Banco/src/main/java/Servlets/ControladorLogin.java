package Servlets;

import DataBase.ConexionDB;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author jara
 */
@WebServlet(name = "ControladorLogin", urlPatterns = {"/ControladorLogin"})
public class ControladorLogin extends HttpServlet {

    private Connection cn = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private int user;
    private String pass;
    private int registro;
    private String rol;
    private String name;
    private String turno;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);

        user = Integer.parseInt(request.getParameter("user"));
        System.out.println("user: " + user);
        pass = request.getParameter("pass");
        System.out.println("pass: " + pass);

        String sql = "SELECT user,registro,roll,pass FROM USUARIO WHERE user=? AND pass=?;";

        String sql1 = "SELECT codigo,nombre,turno FROM GERENTE WHERE codigo=?;";
        String sql2 = "SELECT codigo,nombre,turno FROM CAJERO WHERE codigo=?;";
        String sql3 = "SELECT codigo,nombre FROM CLIENTE WHERE codigo=?;";

        try {
            cn = ConexionDB.conector();
            ps = cn.prepareStatement(sql);
            ps.setInt(1, user);
            ps.setString(2, pass);
            rs = ps.executeQuery();

            if (rs.next()) {
                if ((rs.getString("pass")).equals(pass)) {
                    registro = rs.getInt("registro");
                    rol = rs.getString("roll");
                    System.out.println("registro y rol: " + registro + " " + rol);

                    if (rol.equals("GERENTE")) {
                        try {
                            ps = cn.prepareStatement(sql1);
                            ps.setInt(1, registro);
                            rs = ps.executeQuery();
                            if (rs.next()) {
                                HttpSession sesion = request.getSession();
                                name = rs.getString("nombre");
                                System.out.println("nombre: " + name);
                                sesion.setAttribute("Loggeado", "1");
                                sesion.setAttribute("username", name);
                                sesion.setAttribute("id", registro);
                                sesion.setAttribute("rol", rol);
                                response.sendRedirect("JSP/Gerente.jsp");
                            }
                        } catch (SQLException e) {
                            System.out.println("Error al obtener el nombre! " + e);
                        }
                    }
                    if (rol.equals("CAJERO")) {
                        try {
                            ps = cn.prepareStatement(sql2);
                            ps.setInt(1, registro);
                            rs = ps.executeQuery();
                            if (rs.next()) {
                                HttpSession sesion = request.getSession();
                                name = rs.getString("nombre");
                                turno = rs.getString("turno");
                                System.out.println("nombre: " + name+" Turno: "+turno);
                                sesion.setAttribute("Loggeado", "1");
                                sesion.setAttribute("username", name);
                                sesion.setAttribute("id", registro);
                                sesion.setAttribute("rol", rol);
                                sesion.setAttribute("turno", turno);
                                response.sendRedirect("JSP/Cajero.jsp");
                            }
                        } catch (SQLException e) {
                            System.out.println("Error al obtener el nombre! " + e);
                        }
                    }
                    if (rol.equals("CLIENTE")) {
                        try {
                            ps = cn.prepareStatement(sql3);
                            ps.setInt(1, registro);
                            rs = ps.executeQuery();
                            if (rs.next()) {
                                HttpSession sesion = request.getSession();
                                name = rs.getString("nombre");
                                sesion.setAttribute("Loggeado", "1");
                                sesion.setAttribute("username", name);
                                sesion.setAttribute("id", registro);
                                System.out.println("registro: " + registro);
                                response.sendRedirect("JSP/Cliente.jsp");
                            }
                        } catch (SQLException e) {
                            System.out.println("Error al obtener el nombre! " + e);
                        }
                    }
                } else {
                    response.sendRedirect("JSP/ErrorLogin.jsp");
                }
            } else {
                response.sendRedirect("JSP/ErrorLogin.jsp");
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener el Login");
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

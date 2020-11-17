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
@WebServlet(name = "ControladorVerificarS", urlPatterns = {"/ControladorVerificarS"})
public class ControladorVerificarS extends HttpServlet {

    private Connection cn = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    int cuenta;
    int cliente;
    String nombre;
    String dpi;

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
        cuenta = Integer.parseInt(request.getParameter("cuenta"));
        String sql = "SELECT cliente FROM CUENTA WHERE codigo=?;";
        String sql1 = "SELECT nombre,dpi FROM CLIENTE WHERE codigo=?;";
        try {
            cn = ConexionDB.conector();
            ps = cn.prepareStatement(sql);
            ps.setInt(1, cuenta);
            rs = ps.executeQuery();
            if (rs.next()) {
                cliente = rs.getInt("cliente");
                ps = cn.prepareStatement(sql1);
                ps.setInt(1, cliente);
                rs = ps.executeQuery();
                if (rs.next()) {
                    nombre=rs.getString("nombre");
                    dpi = rs.getString("dpi");
                    HttpSession sesion = request.getSession();
                    System.out.println("cuenta: " + cuenta);
                    System.out.println("cliente: " + cliente);
                    System.out.println("dpi: " + dpi);
                    System.out.println("nombre: " + nombre);
                    sesion.setAttribute("nombre", nombre);
                    sesion.setAttribute("dpi", dpi);
                    sesion.setAttribute("cliente", cliente);
                    sesion.setAttribute("cuenta", cuenta);
                    response.sendRedirect("JSP/EnviarSolicitud.jsp");
                }
            } else {
                response.sendRedirect("JSP/SolicitarAsociacion.jsp");
            }
        } catch (IOException | SQLException e) {
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

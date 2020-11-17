package Servlets;

import DataBase.DBActualizaciones;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author jara
 */
@WebServlet(name = "ControladorFinalizarAsociacion", urlPatterns = {"/ControladorFinalizarAsociacion"})
public class ControladorFinalizarAsociacion extends HttpServlet {

    int codigo;
    int cuenta;
    int cliente;
    String estado;

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
        codigo =  Integer.parseInt(request.getParameter("codigoM").trim());
        cuenta = Integer.parseInt(request.getParameter("cuentaM"));
        cliente = Integer.parseInt(request.getParameter("clienteM"));
        estado = request.getParameter("estadoM");
        DBActualizaciones ac = new DBActualizaciones();
        System.out.println("codigo: " + codigo + " cuenta: " + cuenta + " cliente: " + cliente + " estado: " + estado);
        if ((ac.finalizarSolicitud(codigo, estado))) {
            response.sendRedirect("JSP/Solicitudes.jsp");
        } else {
            response.sendRedirect("JSP/Cliente.jsp");
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

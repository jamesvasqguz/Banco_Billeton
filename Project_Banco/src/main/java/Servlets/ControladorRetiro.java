package Servlets;

import DataBase.DBConsultas;
import DataBase.DBInserts;
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
@WebServlet(name = "ControladorRetiro", urlPatterns = {"/ControladorRetiro"})
public class ControladorRetiro extends HttpServlet {

    private DBConsultas cons;
    private DBInserts ins;
    private int cuenta;
    private double monto;
    private int cajero;
    private int cliente;

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
        monto = Double.parseDouble(request.getParameter("dinero"));
        cajero = Integer.parseInt(request.getParameter("cajero"));
        cons = new DBConsultas();
        ins = new DBInserts();
        cliente = cons.consultarCliente(cuenta);
        System.out.println("cuenta: " + cuenta + " monto: " + monto + " cajero: " + cajero + " cliente dueño: " + cliente);
        if ((cons.verificarFondos(cliente, cuenta, monto))) {
            if ((ins.debito(cuenta, monto, cajero))) {
                System.out.println("Retiro realizado con exito");
                response.sendRedirect("JSP/Retiro.jsp");
            } else {
                System.out.println("Retiro realizada sin exito");
                response.sendRedirect("JSP/Cajero.jsp");
            }
        } else {
            System.out.println("Error en la verificacion de fondos");
            response.sendRedirect("JSP/Cajero.jsp");
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

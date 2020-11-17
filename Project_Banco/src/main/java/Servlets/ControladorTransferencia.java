package Servlets;

import DataBase.DBConsultas;
import DataBase.DBInserts;
import java.io.IOException;
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
@WebServlet(name = "ControladorTransferencia", urlPatterns = {"/ControladorTransferencia"})
public class ControladorTransferencia extends HttpServlet {

    private DBConsultas cons;
    private DBInserts ins;
    private int cuentaD;
    private int cuentaO;
    private double monto;
    private int cliente;
    private int cajero;

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
        HttpSession sesion = request.getSession();
        cuentaD = Integer.parseInt(request.getParameter("cuentaD"));
        cuentaO = Integer.parseInt(request.getParameter("cuentaO"));
        monto = Double.parseDouble(request.getParameter("dinero"));
        cajero = Integer.parseInt(request.getParameter("cajero"));
        String cl = String.valueOf(sesion.getAttribute("id"));
        cliente = Integer.parseInt(cl);
        System.out.println("cliente: "+cliente);
        cons = new DBConsultas();
        ins = new DBInserts();
        if ((cons.verificarAso(cliente, cuentaD))) {
            if ((cons.verificarFondos(cliente, cuentaO, monto))) {
                if ((ins.debito(cuentaO, monto, cajero)) && (ins.credito(cuentaD, monto, cajero))) {
                    System.out.println("Transaccion realizada con exito");
                    response.sendRedirect("JSP/Cliente.jsp");
                } else {
                    System.out.println("Error en la transaccion");
                    response.sendRedirect("JSP/Transferencias.jsp");
                }
            } else {
                System.out.println("Error al verificar los fondos");
                response.sendRedirect("JSP/Transferencias.jsp");
            }
        } else {
            System.out.println("Error al verificar la asociacion");
            response.sendRedirect("JSP/Transferencias.jsp");
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

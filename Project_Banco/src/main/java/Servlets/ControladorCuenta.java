package Servlets;

import DataBase.DBConsultas;
import DataBase.DBInserts;
import com.sun.org.apache.bcel.internal.generic.AALOAD;
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
@WebServlet(name = "ControladorCuenta", urlPatterns = {"/ControladorCuenta"})
public class ControladorCuenta extends HttpServlet {

    private DBConsultas cons;
    private DBInserts ins;
    private int cliente;
    private String fecha;
    private double saldo;
    private String error;

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
        cliente = Integer.parseInt(request.getParameter("cliente"));
        saldo = Double.parseDouble(request.getParameter("saldo"));
        System.out.println("Datos: " + cliente + " " + saldo);
        int codigo;
        cons = new DBConsultas();
        ins = new DBInserts();
        String msj = "Anota el codigo de tu Cuenta Bancaria";
        HttpSession sesion = request.getSession();
        sesion.setAttribute("CCB", null);
        sesion.setAttribute("msj", null);
        sesion.setAttribute("Error", null);
        sesion.setAttribute("PCB", null);
        sesion.setAttribute("codigoU", null);
        sesion.setAttribute("codigoG", null);
        sesion.setAttribute("codigoC", null);
        sesion.setAttribute("codigoCL", null);
        if ((cons.existenciaCliente(cliente))) {
            if (cons.numPositivo(saldo)) {
                if (cons.primeraCuenta(cliente)) {
                    if ((ins.crearCB(cliente, saldo))) {
                        codigo = cons.codigoCuenta(cliente, saldo);
                        System.out.println("CCB realizado con exito");
                        sesion.setAttribute("msj", msj);
                        sesion.setAttribute("CCB", codigo);
                        response.sendRedirect("JSP/OperacionExitosa.jsp");
                    } else {
                        error = "Error al insertar Cuenta Bancaria";
                        System.out.println("Error al insertar Cuenta Bancaria");
                        sesion.setAttribute("Error", error);
                        response.sendRedirect("JSP/OperacionFallida.jsp");
                    }
                } else {
                    if (ins.crearCB(cliente, saldo)) {
                        String pcb = "Primera Cuenta Bancaria";
                        codigo = cons.codigoCuenta(cliente, saldo);
                        System.out.println("CCB realizado con exito");
                        sesion.setAttribute("msj", msj);
                        sesion.setAttribute("PCB", pcb);
                        sesion.setAttribute("CCB", codigo);
                        response.sendRedirect("JSP/OperacionExitosa.jsp");
                    } else {
                        error = "Error al insertar Cuenta Bancaria";
                        System.out.println("Error al insertar Cuenta Bancaria");
                        sesion.setAttribute("Error", error);
                        response.sendRedirect("JSP/OperacionFallida.jsp");
                    }
                }
            } else {
                error = "El monto inicial es un negativo";
                System.out.println("El monto inicial es un negativo");
                sesion.setAttribute("Error", error);
                response.sendRedirect("JSP/OperacionFallida.jsp");
            }
        } else {
            error = "No hay registros del cliente";
            System.out.println("No hay registros del cliente");
            sesion.setAttribute("Error", error);
            response.sendRedirect("JSP/OperacionFallida.jsp");
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

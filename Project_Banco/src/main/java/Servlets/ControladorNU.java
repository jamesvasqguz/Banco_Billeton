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
@WebServlet(name = "ControladorNU", urlPatterns = {"/ControladorNU"})
public class ControladorNU extends HttpServlet {

    private DBInserts ins;
    private DBConsultas cons;
    private int codigo;
    private String roll;
    private String pass;

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
        ins = new DBInserts();
        cons = new DBConsultas();
        codigo = Integer.parseInt(request.getParameter("codigo").trim());
        roll = request.getParameter("rol");
        pass = new String(request.getParameter("pass").trim().getBytes("ISO-8859-1"), "UTF-8");
        HttpSession sesion = request.getSession();
        sesion.setAttribute("CCB", null);
        sesion.setAttribute("msj", null);
        sesion.setAttribute("Error", null);
        sesion.setAttribute("PCB", null);
        sesion.setAttribute("codigoU", null);
        sesion.setAttribute("codigoG", null);
        sesion.setAttribute("codigoC", null);
        sesion.setAttribute("codigoCL", null);
        if (cons.verificarUser(codigo, roll)) {
            String msj = "Ya existe un usuario con el mismo registro y rol";
            System.out.println("Usuario no se ingreso");
            sesion.setAttribute("Error", msj);
            response.sendRedirect("JSP/OperacionFallida.jsp");
        } else {
            if (ins.crearUser(codigo, roll, pass)) {
                String msj = "Tu codigo para ingresar al portal del banco es el siguiente: ";
                int user;
                user = cons.solicitarCodigoU(codigo, roll);
                System.out.println("Usuario ingresado correctamente");
                sesion.setAttribute("msj", msj);
                sesion.setAttribute("codigoU", user);
                response.sendRedirect("JSP/OperacionExitosa.jsp");
            } else {
                String msj = "Error al ingresar el Usuario, revisa los datos que ingresaste ";
                System.out.println("Usuario no se ingreso");
                sesion.setAttribute("Error", msj);
                response.sendRedirect("JSP/OperacionFallida.jsp");
            }
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

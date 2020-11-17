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
@WebServlet(name = "ControladorNCLIENTE", urlPatterns = {"/ControladorNCLIENTE"})
public class ControladorNCLIENTE extends HttpServlet {

    private DBInserts ins;
    private DBConsultas cons;
    private String nombre;
    private String dpi;
    private String fecha;
    private String direccion;
    private String sexo;
    private String filedpi;

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

        nombre = new String(request.getParameter("nombre").getBytes("ISO-8859-1"), "UTF-8");
        dpi = request.getParameter("dpi").trim();
        fecha = request.getParameter("fecha");
        direccion = new String(request.getParameter("direccion").getBytes("ISO-8859-1"), "UTF-8");
        sexo = request.getParameter("sexo");
        filedpi = new String(request.getParameter("filedpi").getBytes("ISO-8859-1"), "UTF-8");

        System.out.println("Datos: " + nombre + " " + dpi + " " + fecha + " " + direccion + " " + sexo + " " + filedpi);
        HttpSession sesion = request.getSession();
        sesion.setAttribute("CCB", null);
        sesion.setAttribute("msj", null);
        sesion.setAttribute("Error", null);
        sesion.setAttribute("PCB", null);
        sesion.setAttribute("codigoU", null);
        sesion.setAttribute("codigoG", null);
        sesion.setAttribute("codigoC", null);
        sesion.setAttribute("codigoCL", null);
        
        if (cons.verificarDPI(dpi, fecha)) {
            String msj = "Ya existe un cliente con el mismo dpi y fecha de nacimiento ";
            System.out.println("Cliente no se ingreso");
            sesion.setAttribute("Error", msj);
            response.sendRedirect("JSP/OperacionFallida.jsp");
        } else {

            if ((ins.nuevoCliente(nombre, dpi, fecha, direccion, sexo, filedpi))) {
                String msj = "Tu codigo unico en el sistama es el siguiente: ";
                int codigo;
                codigo = cons.solicitarCodigoCliente(dpi, fecha);
                System.out.println("Cliente ingresado correctamente");
                sesion.setAttribute("msj", msj);
                sesion.setAttribute("codigoC", codigo);
                response.sendRedirect("JSP/OperacionExitosa.jsp");
            } else {
                String msj = "Error al ingresar el cliente, revisa los datos que ingresaste ";
                System.out.println("Cliente no se ingreso");
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

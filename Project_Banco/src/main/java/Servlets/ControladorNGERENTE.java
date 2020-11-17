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
@WebServlet(name = "ControladorNGERENTE", urlPatterns = {"/ControladorNGERENTE"})
public class ControladorNGERENTE extends HttpServlet {

    private DBInserts ins;
    private DBConsultas cons;
    private String nombre;
    private String turno;
    private String dpi;
    private String direccion;
    private String sexo;

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
        turno = request.getParameter("turno");
        dpi = request.getParameter("dpi").trim();
        direccion = new String(request.getParameter("direccion").getBytes("ISO-8859-1"), "UTF-8");
        sexo = request.getParameter("sexo");
        String rol = "GERENTE";
        System.out.println("Datos: " + nombre + " " + dpi + " " + turno + " " + direccion + " " + sexo + " " + rol);
        HttpSession sesion = request.getSession();
        sesion.setAttribute("CCB", null);
        sesion.setAttribute("msj", null);
        sesion.setAttribute("Error", null);
        sesion.setAttribute("PCB", null);
        sesion.setAttribute("codigoU", null);
        sesion.setAttribute("codigoG", null);
        sesion.setAttribute("codigoC", null);
        sesion.setAttribute("codigoCL", null);

        if (cons.verificarEmpleado(dpi, rol)) {
            String msj = "Ya existe un GERENTE con el mismo dpi.";
            System.out.println("Gerente no se ingreso");
            sesion.setAttribute("Error", msj);
            response.sendRedirect("JSP/OperacionFallida.jsp");
        } else {
            if ((ins.crearEmpleado(nombre, turno, rol, dpi, direccion, sexo))) {
                String msj = "Tu codigo unico en el sistama es el siguiente: ";
                int codigo;
                codigo = cons.solicitarCodigoEmpleado(dpi, rol);
                System.out.println("Gerente ingresado correctamente");
                sesion.setAttribute("msj", msj);
                sesion.setAttribute("codigoG", codigo);
                response.sendRedirect("JSP/OperacionExitosa.jsp");
            } else {
                String msj = "Error al ingresar el Gerente, revisa los datos que ingresaste ";
                System.out.println("Gerente no se ingreso");
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

package Servlets;

import DataBase.DBLimites;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "ControladorFijarL", urlPatterns = {"/ControladorFijarL"})
public class ControladorFijarL extends HttpServlet {

    private DBLimites l;

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
        int limit1 = Integer.parseInt(request.getParameter("lim1"));
        int limit2 = Integer.parseInt(request.getParameter("lim2"));
        HttpSession sesion = request.getSession();
        String idS = String.valueOf(sesion.getAttribute("id"));
        int gerente = Integer.parseInt(idS);
        System.out.println("gerente: " + gerente + " limite1 " + limit1 + " limite2" + limit2);
        l = new DBLimites();
        String msj = "Fijar Limites";
        String mensaje = "Limites fijados correctamente";
        if ((l.numsPositivo(limit1, limit2)) && (l.verficarMayor(limit1, limit2))) {
            if (l.fijarLimites(gerente, limit1, limit2)) {
                System.out.println("Limites fijados exitosamente");
                sesion.setAttribute("msj", msj);
                sesion.setAttribute("msm", mensaje);
                response.sendRedirect("JSP/OperacionExitosa.jsp");
            } else {
                System.out.println("Error al fijar los limites");
                String Error = "Error al fijar Limites";
                sesion.setAttribute("Error", Error);
                response.sendRedirect("JSP/OperacionFallida.jsp");
            }
        } else {
            System.out.println("Invalidacion de los limites, menores a 0 , limite1 mayor a limite2");
            String Error = "Invalidacion de los limites, menores a 0 , limite1 mayor a limite2";
            sesion.setAttribute("Error", Error);
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

package ControladorReportes;

import Backend.CAReporte1;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;
import javax.ws.rs.core.HttpHeaders;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 *
 * @author jara
 */
@WebServlet(name = "ControladorReporteCA1", urlPatterns = {"/ControladorReporteCA1"})
public class ControladorReporteCA1 extends HttpServlet {

    private String hora1;
    private String hora2;

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

        try {
            int codigoUsuario = (int) request.getSession().getAttribute("id");
            String turno = (String) request.getSession().getAttribute("turno");
            if (turno.equals("MATUTINO")) {
                hora1 = "06:00";
                hora2 = "14:30";
            } else if (turno.equals("VESPERTINO")) {
                hora1 = "13:00";
                hora2 = "22:00";
            } else if (turno.equals("Toda hora")) {
                hora1 = "00:00";
                hora2 = "23:59";
            }
            String nombreUsuario = (String) request.getSession().getAttribute("username");
            System.out.println("Cogido: " + codigoUsuario + " turno: " + turno + " hora1: " + hora1 + " hora2: " + hora2 + " nombre: " + nombreUsuario);
            CAReporte1 report = new CAReporte1();

            List<CAReporte1> exportado = report.reporte1Cajero(codigoUsuario, hora1, hora2);

            double saldoCaja = report.saldoCaja(codigoUsuario, hora1, hora2);
            System.out.println("saldoCaja: "+saldoCaja);
            response.setContentType("application/pdf");
            response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=ReporteCajero1 " + nombreUsuario + ".pdf");

            //List<Cliente> Histo = gestor.reporte5(t, s);
            File file = new File(request.getServletContext().getRealPath("/Reports/CAReport1.jrxml"));
            JasperReport jasperReports = JasperCompileManager.compileReport(file.getAbsolutePath());
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(exportado);

            Map<String, Object> parameters = new HashMap<>();
            parameters.put("nombreCajero", nombreUsuario);
            parameters.put("saldoCaja", saldoCaja);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReports, parameters, dataSource);
            JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());

            response.getOutputStream().flush();
            response.getOutputStream().close();

        } catch (IOException | NumberFormatException e) {
            JOptionPane.showMessageDialog(null, e);
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        } catch (JRException ex) {
        }
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

package ControladorReportes;

import Backend.GReporte4;
import Backend.GReporte7;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
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
import net.sf.jasperreports.engine.util.JRLoader;

/**
 *
 * @author jara
 */
@WebServlet("/ControladorReporteG7")
public class ControladorReporteG7 extends HttpServlet {

    GReporte7 r7 = new GReporte7();
    GReporte7 rr7 = new GReporte7();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException {

        try {
            String fecha1 = String.valueOf(request.getParameter("f1"));
            String fecha2 = String.valueOf(request.getParameter("f2"));
            System.out.println(""+fecha1+" "+fecha2);
            response.setContentType("application/pdf");
            response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=ReporteGerent7.pdf");
            List<GReporte7> lista = r7.cajeroMasTransacciones(fecha1, fecha2);

            File file = new File(request.getServletContext().getRealPath("/Reports/GReport7.jrxml"));
            JasperReport jasperReports = JasperCompileManager.compileReport(file.getAbsolutePath());
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(lista);

            Map<String, Object> parameters = new HashMap<>();
            parameters.put("nombre", "James");
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReports, parameters, dataSource);
            JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());

            response.getOutputStream().flush();
            response.getOutputStream().close();

        } catch (IOException | NumberFormatException e) {
            JOptionPane.showMessageDialog(null, e);
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        } catch (JRException ex) {
            JOptionPane.showMessageDialog(null, ex);
            ex.printStackTrace();
        }
    }
}

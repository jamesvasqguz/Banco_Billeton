package Backend;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Paths;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

/**
 *
 * @author jara
 */
public class Constructor {

    private String pathGeneral;

    public File construirArchivo(String nombreParametro, HttpServletRequest request) {
        try {
            Part filePart = request.getPart(nombreParametro);
            String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
            InputStream inputStream = filePart.getInputStream();
            OutputStream outputStream = new FileOutputStream(fileName);
            inputStream.transferTo(outputStream);
            File file = Paths.get(filePart.getSubmittedFileName()).toFile();
            //AGREGANDO EL PATH DEL ARCHIVO EN EL SERVIDOR
            setPathGeneral(file.getAbsolutePath().replace("/" + file.getName(), ""));
            return file;
        } catch (IOException | ServletException e) {
            System.out.println("ERROR AL INTENTAR CONSTRIUR EL ARCHIVO");
            return null;
        }
    }

    public InputStream extraerArchivo(String nombreParametro, HttpServletRequest request) {
        try {
            Part filePart = request.getPart(nombreParametro);
            String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
            return filePart.getInputStream();
        } catch (IOException | ServletException e) {
            System.out.println("ERROR AL INTENTAR CONSTRIUR EL ARCHIVO");
            return null;
        }
    }

    public void escribirArchivo(Part filePart) {
        try {
            InputStream inputStream = filePart.getInputStream();
            OutputStream outputStream = new FileOutputStream(Paths.get(filePart.getSubmittedFileName()).getFileName().toString());
            inputStream.transferTo(outputStream);

            File file = Paths.get(filePart.getSubmittedFileName()).toFile();
            setPathGeneral(file.getAbsolutePath().replace("/" + file.getName(), "/"));

        } catch (IOException e) {
            System.out.println("ERROR AL INTENTAR CONSTRIUR EL ARCHIVO");
        }
    }

    public String getPathGeneral() {
        return pathGeneral;
    }

    public void setPathGeneral(String pathGeneral) {
        this.pathGeneral = pathGeneral;
    }
}

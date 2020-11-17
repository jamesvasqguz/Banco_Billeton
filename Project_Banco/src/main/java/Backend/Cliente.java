package Backend;

import java.io.InputStream;

/**
 *
 * @author jara
 */
public class Cliente {

    private int codigo;
    private String nombre;
    private String dpi;
    private String fecha;
    private String direccion;
    private String sexo;
    private InputStream inptArchivo;
    private byte[] archivo;

    public Cliente() {
    }

    public Cliente(int codigo, String nombre, String dpi, String fecha, String direccion, String sexo, byte[] archivo) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.dpi = dpi;
        this.fecha = fecha;
        this.direccion = direccion;
        this.sexo = sexo;
        this.archivo = archivo;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDpi() {
        return dpi;
    }

    public void setDpi(String dpi) {
        this.dpi = dpi;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public InputStream getInptArchivo() {
        return inptArchivo;
    }

    public void setInptArchivo(InputStream inptArchivo) {
        this.inptArchivo = inptArchivo;
    }

    public byte[] getArchivo() {
        return archivo;
    }

    public void setArchivo(byte[] archivo) {
        this.archivo = archivo;
    }

}

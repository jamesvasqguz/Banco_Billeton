package Backend;

/**
 *
 * @author jara
 */
public class Gerente {

    private int codigo;
    private String nombre;
    private String turno;
    private String dpi;
    private String direccion;
    private String sexo;
    
    public Gerente(){}
    
    public Gerente(int codigo, String nombre, String turno, String dpi, String direccion, String sexo) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.turno = turno;
        this.dpi = dpi;
        this.direccion = direccion;
        this.sexo = sexo;
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

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public String getDpi() {
        return dpi;
    }

    public void setDpi(String dpi) {
        this.dpi = dpi;
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

}

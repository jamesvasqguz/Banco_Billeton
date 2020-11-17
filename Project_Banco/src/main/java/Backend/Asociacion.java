package Backend;

/**
 *
 * @author jara
 */
public class Asociacion {
    private int codigo;
    private int cuenta;
    private int cliente;
    private String estado;

    public Asociacion(){}
    public Asociacion(int codigo, int cuenta, int cliente, String estado) {
        this.codigo = codigo;
        this.cuenta = cuenta;
        this.cliente = cliente;
        this.estado = estado;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getCuenta() {
        return cuenta;
    }

    public void setCuenta(int cuenta) {
        this.cuenta = cuenta;
    }

    public int getCliente() {
        return cliente;
    }

    public void setCliente(int cliente) {
        this.cliente = cliente;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
}

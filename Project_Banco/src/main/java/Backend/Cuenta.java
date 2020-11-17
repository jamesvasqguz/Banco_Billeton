package Backend;

/**
 *
 * @author jara
 */
public class Cuenta {
    private int codigo;
    private int cliente;
    private String fecha;
    private double saldo;
    
    public Cuenta(){}
    public Cuenta(int codigo, int cliente, String fecha, double saldo) {
        this.codigo = codigo;
        this.cliente = cliente;
        this.fecha = fecha;
        this.saldo = saldo;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getCliente() {
        return cliente;
    }

    public void setCliente(int cliente) {
        this.cliente = cliente;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
    
}

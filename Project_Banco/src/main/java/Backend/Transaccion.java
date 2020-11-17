package Backend;

/**
 *
 * @author jara
 */
public class Transaccion {
    private int codigo;
    private int cuenta;
    private String fecha;
    private String hora;
    private String tipo;
    private double monto;
    private int cajero;
    
    public Transaccion(){}
    public Transaccion(int codigo, int cuenta, String fecha, String hora, String tipo, double monto, int cajero) {
        this.codigo = codigo;
        this.cuenta = cuenta;
        this.fecha = fecha;
        this.hora = hora;
        this.tipo = tipo;
        this.monto = monto;
        this.cajero = cajero;
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

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public int getCajero() {
        return cajero;
    }

    public void setCajero(int cajero) {
        this.cajero = cajero;
    }
    
}

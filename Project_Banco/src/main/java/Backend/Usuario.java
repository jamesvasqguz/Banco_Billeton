package Backend;

/**
 *
 * @author jara
 */
public class Usuario {
    private int user;
    private int codigo;
    private String roll;
    private String pass;

    public Usuario(int user, int codigo, String roll, String pass) {
        this.user = user;
        this.codigo = codigo;
        this.roll = roll;
        this.pass = pass;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getRoll() {
        return roll;
    }

    public void setRoll(String roll) {
        this.roll = roll;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
    
}

/**
 * Credenziali è la classe che contiene le credenziali degli utenti che fanno il login.
 * <p>
 * Questa classe è {@link java.io.Serializable} e viene usa nella classe {@link Vettore}.
 */
public class Credenziali implements java.io.Serializable {
    private final String username;
    private final String indirizzo;
    private final String password;

    public Credenziali(String username, String password, String indirizzo) {
        this.username = username;
        this.password = password;
        this.indirizzo = indirizzo;
    }

    public String toString() {
        return username + " " + password + " " + indirizzo;
    }

    public String toUsername() {
        return username;
    }

    public String toPassword() {
        return password;
    }

    public String toIndirizzo() {
        return indirizzo;
    }
}
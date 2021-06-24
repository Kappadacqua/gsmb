public class Credenziali implements java.io.Serializable {
    private String username;
    private String indirizzo;
    private String password;

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
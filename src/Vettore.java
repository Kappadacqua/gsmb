import java.util.Vector;

public class Vettore implements java.io.Serializable {
    private Vector vettoregente;

    public Vettore() {
        vettoregente = new Vector(10);
    }

    public void aggiungi(Credenziali a) {
        vettoregente.add(a);
    }

    public void rimuovi(Credenziali p) {
        vettoregente.remove(p);
    }

    public Credenziali get(int index) {
        return (Credenziali) vettoregente.get(index);
    }

    public int size() {
        return vettoregente.size();
    }


}

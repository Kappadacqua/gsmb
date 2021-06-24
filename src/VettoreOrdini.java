import java.io.Serializable;
import java.util.Vector;

public class VettoreOrdini<E extends Serializable> implements java.io.Serializable {
    private Vector listaordini;

    public VettoreOrdini() {
        listaordini = new Vector(10);
    }

    public void aggiungi(E a) {
        listaordini.add(a);
    }

    public void rimuovi(E p) {
        listaordini.remove(p);
    }

    public E get(int index) {
        return (E) listaordini.get(index);
    }

    public int size() {
        return listaordini.size();
    }


}


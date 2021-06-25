import java.io.Serializable;
import java.util.Date;


/**
 *
 */
public class SpedizioneNormale implements Serializable, SpedizioneTabella {
    protected String codice, destinazione, stato;
    protected float peso;
    protected Date data;


    public SpedizioneNormale(String destinazione, String codice, String stato, float peso) {
        this.destinazione = destinazione;
        this.codice = codice;
        data = new Date();
        this.peso = peso;
        this.stato = stato;
    }

    @Override
    public String toCodice() {
        return codice;
    }

    @Override
    public String toDestinazione() {
        return destinazione;
    }

    @Override
    public String toData() {
        return data.toString();
    }

    @Override
    public String toStato() {
        return stato;
    }

    @Override
    public String toPeso() {
        return peso + "";
    }

    @Override
    public String toValoreAssicurato() {
        return "";
    }

    public String toString() {
        return toCodice() + " " + toDestinazione() + " " + toData() + " " + toPeso() + " " + toStato() + " " + toValoreAssicurato();
    }


    public void modificaStato(String nuovostato) {
        stato = nuovostato;
    }


}






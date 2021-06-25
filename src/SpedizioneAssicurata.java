import java.io.Serializable;


/**
 * SpedizioneNormale è la classe che contiene tutte le spedizioni assicurate create dagli utenti.
 * <p>
 * Questa classe è {@link java.io.Serializable}, implementa l'interfaccia {@link SpedizioneTabella} ed inoltre è figlia della classe {@link SpedizioneNormale}.
 */
public class SpedizioneAssicurata extends SpedizioneNormale implements Serializable, SpedizioneTabella {

    protected Float valoreassicurato;

    public SpedizioneAssicurata(String destinazione, String codice, String stato, float peso, float valoreassicurato) {
        super(destinazione, codice, stato, peso);
        this.valoreassicurato = valoreassicurato;

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
        return valoreassicurato + "";
    }

    public String toString() {
        return toCodice() + " " + toDestinazione() + " " + toData() + " " + toPeso() + " " + toStato() + " " + toValoreAssicurato();
    }


}

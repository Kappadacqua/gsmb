


/**
 *
 */
public interface SpedizioneTabella {

    String toCodice();

    String toDestinazione();

    String toData();

    String toStato();

    String toPeso();

    String toValoreAssicurato();

    void modificaStato(String nuovostato);

}

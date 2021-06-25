


/**
 * SpedizioneTabella è l'interfaccia che implementa i metodi che vengono usati nelle classi {@link SpedizioneNormale} e {@link SpedizioneAssicurata}.
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

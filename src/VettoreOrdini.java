/**
 * Vettore ordini è la classe che viene usata per creare il vettore dove vengono salvate le {@link SpedizioneNormale} (e {@link SpedizioneAssicurata}).
 * <p>
 * Questa classe è figlia di {@link VettoreFile}.
 */
public class VettoreOrdini extends VettoreFile<SpedizioneNormale> {


    public VettoreOrdini(String nomefile) {
        super(nomefile);
    }


}
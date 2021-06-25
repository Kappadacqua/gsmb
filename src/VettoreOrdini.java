/**
 * Vettore ordini è la classe che viene usata per creare il vettore dove vengono salvate le {@link SpedizioneNormale} (e {@link SpedizioneAssicurata}).
 * <p>
 * Questa classe è figlia di {@link VettoreFile}.
 */
public class VettoreOrdini extends VettoreFile<SpedizioneNormale> {

    private transient TableOrdini tableordiniprivato;

    public VettoreOrdini(String nomefile) {
        super(nomefile);
        tableordiniprivato = new TableOrdini(this);
    }


    public TableOrdini getTableOrdini() {
        return tableordiniprivato;
    }


    @Override
    public void save() {
        super.save();

        tableordiniprivato.fireTableDataChanged();
    }
}
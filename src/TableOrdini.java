import javax.swing.table.AbstractTableModel;

public class TableOrdini extends AbstractTableModel {

    private VettoreOrdini ordini;

    //vedo solo quello che ho caricato all'inizio

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return String.class;
    }

    @Override
    public int getRowCount() {
        return ordini.size();
    }

    @Override
    public int getColumnCount() {
        return 6;
    }


    @Override
    public String getColumnName(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return "Destinazione";
            case 1:
                return "Codice";
            case 2:
                return "Stato";
            case 3:
                return "Peso";
            case 4:
                return "Data";
            case 5:
                return "Valore assicurato";
        }
        return null;
    }


    public void setValueAt(Object value, int row, int
            col) {
        SpedizioneTabella spedizione = ordini.get(row);

        if (col == 2)  //questo codice mi permette di modificare le celle di quella colonna
            spedizione.modificaStato((String) value);

        fireTableDataChanged();  //salva le modifiche sul vettore
    }


    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        SpedizioneTabella spedizione = ordini.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return spedizione.toDestinazione();
            case 1:
                return spedizione.toCodice();
            case 2:
                return spedizione.toStato();
            case 3:
                return spedizione.toPeso();
            case 4:
                return spedizione.toData();
            case 5:
                return spedizione.toValoreAssicurato();
        }
        return "Unknown";
    }

    public void setOrdini(VettoreOrdini ordini) {
        this.ordini = ordini;
    }

}


import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;


/**
 * PanelOrdiniCliente è la classe che mostra e che permette di modificare al cliente la tabella contenente le spedizioni da lui effettuate.
 */
public class PanelOrdiniCliente extends JPanel {
    String filename = "OrdiniClientiCriptati.txt";  //il file dove vengono salvate tutte le credenziali
    private JLabel scritta_benvenuto; //scritta iniziale
    private String nome;
    private JTable table;
    private int selectedrow;
    private final ListSelectionModel selezioneModel;
    private PopupModificaCliente popModificaCliente;
    private Vector<SpedizioneNormale> ordini_del_cliente;
    private VettoreOrdini ordini_totali;

    public PanelOrdiniCliente(GSMBFrame frame_principale) {
        super();
        nome = "";
        VettoreOrdini ordini_totali = new VettoreOrdini(filename);
        scritta_benvenuto = new JLabel("");
        JButton aggiorna = new JButton("aggiorna");
        JButton pulsante_da_ordini_user_a_accesso = new JButton("logout");
        JButton pulsante_da_panel_ordini_cliente_a_panel_creazione_ordine_cliente = new JButton("crea un nuovo ordine");


        ordini_del_cliente = new Vector<>();
        TabelOrdiniUser dataModel = new TabelOrdiniUser(); //non penso funzioni
        table = new JTable(dataModel);
        selezioneModel = table.getSelectionModel();
        popModificaCliente = new PopupModificaCliente(table);

        pulsante_da_panel_ordini_cliente_a_panel_creazione_ordine_cliente.addActionListener(e -> frame_principale.toCard("Panel creazione ordine cliente"));
        pulsante_da_ordini_user_a_accesso.addActionListener(e -> {
            frame_principale.toCard("Panel accesso");
            ordini_del_cliente = new Vector<>();
     /*   for(int i=ordini_del_cliente.size();i>0;i--)
            {
                ordini_del_cliente.rimuovi(ordini_del_cliente.get(i));
            }
*/
        });

        aggiorna.addActionListener(e -> {

            ordini_del_cliente = new Vector<>();
            for (SpedizioneNormale spedizioneNormale : ordini_totali) {
                if (spedizioneNormale.codice.startsWith(nome))
                    ordini_del_cliente.add(spedizioneNormale);
            }
            table.updateUI();
        });


        //serve per stabilire dove compare il JPopupMenu
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                {
                    //determina se cliccato col mouse destro
                    if (SwingUtilities.isRightMouseButton(me)) {
                        popModificaCliente.show(me.getComponent(), me.getX(), me.getY());
                    }
                }
            }

        });

//determina se clicco su una riga della tabella
        selezioneModel.addListSelectionListener(e -> {
            if (!selezioneModel.isSelectionEmpty() && !e.getValueIsAdjusting()) {

                selectedrow = selezioneModel.getMinSelectionIndex();//selectdrow=indice della riga
                popModificaCliente.setRow(selectedrow, ordini_totali);//popmodifica ottiene l'indice della riga*/
                popModificaCliente.setTasti(table.getValueAt(selectedrow, 2).equals("FALLITA") && !(table.getValueAt(selectedrow, 5).equals("")));
            }
        });


        add(scritta_benvenuto);
        add(pulsante_da_panel_ordini_cliente_a_panel_creazione_ordine_cliente);
        add(pulsante_da_ordini_user_a_accesso);
        add(table);
        add(aggiorna);


    }


    public void setUsername(String username) {
        nome = username;
        scritta_benvenuto.setText("benvenuto" + " " + nome);
    }


    public class TabelOrdiniUser extends AbstractTableModel {

        @Override
        public Class<?> getColumnClass(int columnIndex) {
            return String.class;
        }

        @Override
        public int getRowCount() {
            return ordini_del_cliente.size();
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

        @Override
        public void setValueAt(Object value, int row, int
                col) {
            SpedizioneTabella spedizione = ordini_del_cliente.get(row);

            if (col == 2)
                spedizione.modificaStato((String) value);
        /*    if (col == 0)
                spedizione.modificaDestinazione((String)value);*/

            fireTableDataChanged();
        }


        @Override
        public boolean isCellEditable(int row, int col) {
            if (col == 2)
                return true;
            else
                return false;
        }

        /*@Override
        public Class<?> getColumnClass(int columnIndex) {
            return String.class;
        }*/


        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            SpedizioneTabella spedizione = ordini_del_cliente.get(rowIndex);
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
    }

}



import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

public class PanelOrdiniCliente extends JPanel {
    String filename = "OrdiniClientiCriptati.txt";  //il file dove vengono salvate tutte le credenziali
    private final JLabel s; //scritta iniziale
    private final JTextField textfield_username_nascosto;//prova
    private final String nome = "";
    private JTable table;
    private int selectedrow;
    private ListSelectionModel selezioneModel;
    private PopupModificaCliente popModificaCliente;
    private Vector<SpedizioneNormale> ordini_del_cliente;

    public PanelOrdiniCliente(GSMBFrame frame_principale) {
        super();

        JButton aggiorna = new JButton("aggiorna");
        JButton pulsante_da_ordini_user_a_accesso_user = new JButton("logout");
        textfield_username_nascosto = new JTextField(25);
        JButton pulsante_da_panel_ordini_cliente_a_panel_creazione_ordine_cliente = new JButton("crea un nuovo ordine");
        s = new JLabel("Benvenuto " + textfield_username_nascosto.getText());

        pulsante_da_panel_ordini_cliente_a_panel_creazione_ordine_cliente.addActionListener(e -> frame_principale.toCard("Panel creazione ordine cliente"));
        pulsante_da_ordini_user_a_accesso_user.addActionListener(e -> {
            frame_principale.toCard("Panel accesso");
            ordini_del_cliente = new Vector<>();
     /*   for(int i=ordini_del_cliente.size();i>0;i--)
            {
                ordini_del_cliente.rimuovi(ordini_del_cliente.get(i));
            }
*/
        });
        ordini_del_cliente = new Vector<>();
        add(s);
        add(pulsante_da_panel_ordini_cliente_a_panel_creazione_ordine_cliente);

        add(pulsante_da_ordini_user_a_accesso_user);
        aggiorna.addActionListener(e -> {

            VettoreOrdini ordini_totali = new VettoreOrdini(filename);

            // Deserialization

            ordini_del_cliente = new Vector<>();
            for (SpedizioneNormale spedizioneNormale : ordini_totali) {
                if (spedizioneNormale.codice.startsWith(textfield_username_nascosto.getText()))
                    ordini_del_cliente.add(spedizioneNormale);
            }
            table.updateUI();
        });
        TabelOrdiniUser dataModel = new TabelOrdiniUser(); //non penso funzioni


        table = new JTable(dataModel);
        selezioneModel = table.getSelectionModel();
        add(table);
        add(aggiorna);
        dataModel.addTableModelListener(e -> System.out.println(e.getColumn()));



        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                {
                    //determine id right clicked
                    if (SwingUtilities.isRightMouseButton(me)) {
                        popModificaCliente.show(me.getComponent(), me.getX(), me.getY());
                    }
                }
            }

        });


        selezioneModel.addListSelectionListener(e -> {
            if (!selezioneModel.isSelectionEmpty()) {

                selectedrow = selezioneModel.getMinSelectionIndex();//selectdrow=indice della riga
                popModificaCliente.setRow(selectedrow, (VettoreOrdini) ordini_del_cliente);//popmodifica ottiene l'indice della riga
              /*  System.out.println(table.getValueAt(selectedrow, 2));//PERCHE LO FA 2 VOLTE ??
                System.out.println("meme");*/
                // se la riga che evidenzio con tasto destro equivale a una di quelle condizioni, allora mostro solo il pulsante relativo a quelle condizioni
                /*     popmodifica.setTasti(table.getValueAt(selectedrow, 2).equals("RIMBORSO EROGATO") || (table.getValueAt(selectedrow, 2).equals("RICEVUTA")));//fancy*/
                /*  ordini_totali.save();*/
                table.updateUI();
            }
        });


    }


    public void setUsername(String username) {
        textfield_username_nascosto.setText(username);
        s.setText("Benvenuto " + textfield_username_nascosto.getText());
    }


    public class TabelOrdiniUser extends AbstractTableModel {
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



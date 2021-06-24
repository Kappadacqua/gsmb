import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class PanelOrdiniAdmin extends JPanel {
    private final JTable table;
    private final PopupModificaAdmin popmodifica;
    private final ListSelectionModel selezioneModel;
    String filename = "OrdiniClientiCriptati.txt";  //il file dove vengono salvati tutti gli ordini
    private int selectedrow;
    private VettoreOrdini ordini_totali;


    //il jtextfield username nascosto manca perchè il nome è scritto nel codice
    public PanelOrdiniAdmin(CardLayout cl, JPanel home) {
        super();
        ordini_totali = new VettoreOrdini(filename);
        JButton aggiorna2 = new JButton("aggiorna2");
        JButton aggiorna = new JButton("aggiorna");
        JButton pulsante_da_ordini_admin_a_accesso_admin = new JButton("logout");
        String nome = "Matteo Balugani";
        JLabel scritta_iniziale = new JLabel("Benvenuto Admin " + nome);
        TableOrdiniUser dataModel = new TableOrdiniUser();
        table = new JTable(dataModel);
        popmodifica = new PopupModificaAdmin(table);
        selezioneModel = table.getSelectionModel();

        pulsante_da_ordini_admin_a_accesso_admin.addActionListener(e -> {
            cl.show(home, "Panel accesso");

     /*   for(int i=ordini_del_cliente.size();i>0;i--)                      //bisogna trovare un metodo migliore per svuotare il vettore ordini_del_cliente
            {
                ordini_del_cliente.rimuovi(ordini_del_cliente.get(i));
            }
*/
        });

        aggiorna2.addActionListener(e -> {


            table.updateUI();
            for (SpedizioneNormale spedizioneNormale : ordini_totali) {
                System.out.println(spedizioneNormale.toStato()); //se cèè un solo elemento nel vettore va in errore, ma non dovrebbe essere davvero un problema
            }

        });
        aggiorna.addActionListener(e -> table.updateUI());

        //serve per stabilire dove compare il JPopupMenu
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                {
                    //determine id right clicked
                    if (SwingUtilities.isRightMouseButton(me)) {
                        popmodifica.show(me.getComponent(), me.getX(), me.getY());

                    }
                }
            }

        });
        //cliccando col tasto sinistro su una riga della tabella, manda
        selezioneModel.addListSelectionListener(e -> {
            if (!selezioneModel.isSelectionEmpty()) {

                selectedrow = selezioneModel.getMinSelectionIndex();//selectdrow=indice della riga
                popmodifica.setRow(selectedrow);//popmodifica ottiene l'indice della riga
              /*  System.out.println(table.getValueAt(selectedrow, 2));//PERCHE LO FA 2 VOLTE ??
                System.out.println("meme");*/
                // se la riga che evidenzio con tasto destro equivale a una di quelle condizioni, allora mostro solo il pulsante relativo a quelle condizioni
                popmodifica.setTasti(table.getValueAt(selectedrow, 2).equals("RIMBORSO EROGATO") || (table.getValueAt(selectedrow, 2).equals("RICEVUTA")));//fancy
                ordini_totali.save();
            }
        });


        add(scritta_iniziale);
        add(pulsante_da_ordini_admin_a_accesso_admin);
        add(table);
        add(aggiorna);
        add(aggiorna2);

    }


    public class TableOrdiniUser extends AbstractTableModel {






       /* List<Color> rowColours = Arrays.asList(
                Color.RED,
                Color.GREEN,
                Color.CYAN
        );*/

        @Override
        public int getRowCount() {
            return ordini_totali.size();
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



        /*@Override
        public Class<?> getColumnClass(int columnIndex) {   //a che serve ?
            return String.class;
        }*/

        public void setValueAt(Object value, int row, int
                col) {
            SpedizioneTabella spedizione = ordini_totali.get(row);

            if (col == 2)  //questo codice mi permette di modificare le celle di quella colonna
                spedizione.modificaStato((String) value);

            fireTableDataChanged();  //salva le modifiche sul vettore
        }




     /*   public void setRowColour(int row, Color c) {
            rowColours.set(row, c);
            fireTableRowsUpdated(row, row);
        }

        public Color getRowColour(int row) {
            return rowColours.get(row);
        }


        public boolean isCellEditable(int row, int col) {
            return col == 2;
        }
*/

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            SpedizioneTabella spedizione = ordini_totali.get(rowIndex);
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




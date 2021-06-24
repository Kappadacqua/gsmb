import javax.swing.*;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PanelCreazioneOrdineCliente extends JPanel implements ActionListener {

    String filename = "OrdiniClientiCriptati.txt";  //il file dove vengono salvate tutte le credenziali
    private JRadioButton spedizione_normale, spedizione_assicurata;
    private CardLayout cl;
    private JPanel home;
    private JButton pulsante_da_panel_creazione_ordine_cliente_a_panel_ordini_cliente;
    private JButton salva;
    private JRadioButton b1, b2;
    private JTextField destinazione;
    private JTextField peso;
    private JTextField rimborso;
    private JTextField codice;
    private JTextField stato;
    private JTextField f;//prova
    private boolean flagspedizione = false;//FALSE=SPEDIZIONE NORMALE /TRUE =SPEDIZIONE ASSICURATA
    private JLabel esito;


    public PanelCreazioneOrdineCliente(CardLayout cl, JPanel home) {


        super();
        this.cl = cl;
        this.home = home;
        esito = new JLabel("");
        b1 = new JRadioButton("spedizione normale");
        b2 = new JRadioButton("spedizione assicurata");
        destinazione = new JTextField("inserisci destinazione");
        peso = new JTextField("inserisci peso");
        rimborso = new JTextField("inserisci rimborso");
        codice = new JTextField("premi genera per generare il codice");
        stato = new JTextField("IN PREPARAZIONE");
        salva = new JButton("crea");
        f = new JTextField(25);


//https://stackoverflow.com/questions/20541230/allow-only-numbers-in-jtextfield
        ((AbstractDocument) peso.getDocument()).setDocumentFilter(new DocumentFilter() {
            Pattern regEx = Pattern.compile("\\d*");

            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                Matcher matcher = regEx.matcher(text);
                if (!matcher.matches()) {
                    return;
                }
                super.replace(fb, offset, length, text, attrs);
            }
        });
//https://stackoverflow.com/questions/20541230/allow-only-numbers-in-jtextfield
        ((AbstractDocument) rimborso.getDocument()).setDocumentFilter(new DocumentFilter() {
            Pattern regEx = Pattern.compile("\\d*");

            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                Matcher matcher = regEx.matcher(text);
                if (!matcher.matches()) {
                    return;
                }
                super.replace(fb, offset, length, text, attrs);
            }
        });


        salva.setEnabled(false);
        codice.setEditable(false);
        stato.setEditable(false);
        rimborso.setEditable(false);
        peso.setEditable(false);
        destinazione.setEditable(false);

        codice.setBackground(Color.darkGray);
        stato.setBackground(Color.darkGray);
        rimborso.setBackground(Color.darkGray);
        peso.setBackground(Color.darkGray);
        destinazione.setBackground(Color.darkGray);


        pulsante_da_panel_creazione_ordine_cliente_a_panel_ordini_cliente = new JButton("indietro");
        pulsante_da_panel_creazione_ordine_cliente_a_panel_ordini_cliente.addActionListener(e -> cl.show(home, "Panel ordini cliente"));
        add(pulsante_da_panel_creazione_ordine_cliente_a_panel_ordini_cliente);
        ButtonGroup grp = new ButtonGroup();
        grp.add(b1);
        grp.add(b2);
        b1.addActionListener(this);
        b2.addActionListener(this);
        salva.addActionListener(this);
        add(b1);
        add(b2);
        add(destinazione);
        add(peso);
        add(rimborso);
        add(codice);
        add(stato);
        add(salva);
        add(esito);


    }

    public void actionPerformed(ActionEvent e) {
        peso.setEditable(true);
        destinazione.setEditable(true);
        String scelta = e.getActionCommand();
        String codicegenerato = "";
        VettoreOrdini b = new VettoreOrdini(filename);


        {
            if (scelta.equals("spedizione normale")) {
                rimborso.setEditable(false);
                flagspedizione = false;
                rimborso.setBackground(Color.darkGray);
            }
            if (scelta.equals("spedizione assicurata")) {
                rimborso.setEditable(true);
                flagspedizione = true;
                rimborso.setBackground(Color.white);
            }

            codice.setBackground(Color.white);
            stato.setBackground(Color.white);
            peso.setBackground(Color.white);
            destinazione.setBackground(Color.white);
            codicegenerato = f.getText() + b.size();
            codice.setText(codicegenerato);


            salva.setEnabled(true);


        }


        if (scelta.equals("crea")) {

            //il vettore b viene usato per deserializzare i dati
            //se flagcorretto vale true, i nuovi dati sono accettati e vengono salvati nel file CredenzialiCriptate.txt, se vale false invece c'è un errore nei dati immessi
            if (!flagspedizione)

                b.addAndSave(new SpedizioneNormale(destinazione.getText(), codice.getText(), stato.getText(), Float.parseFloat(peso.getText())));

            else
                b.addAndSave(new SpedizioneAssicurata(destinazione.getText(), codice.getText(), stato.getText(), Float.parseFloat(peso.getText()), Float.parseFloat(rimborso.getText())));


            esito.setText("ordine creato");

            salva.setEnabled(false);
            new java.util.Timer().schedule(
                    new java.util.TimerTask() {
                        @Override
                        public void run() {
                            cl.show(home, "Panel ordini cliente");
                            esito.setText("");
                        }
                    }, 1000);
        }
    }


    public void setUsername(String username) {
        f.setText(username);

    }

}


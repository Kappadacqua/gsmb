import javax.swing.*;
import java.awt.*;

/**
 * PanelRegistrazioneCliente è il panel dove il cliente può creare un nuovo profilo per il programma.
 * <p>
 * Le sue credenziali {@link Credenziali} verranno salvate nel file CredenzialiCriptate.txt.
 */
public class PanelRegistrazioneCliente extends JPanel {

    String filename = "CredenzialiCriptate.txt";  //il file dove vengono salvate tutte le credenziali
    private final JTextField username_text; //JTextField dove viene immessa l'username
    private final JTextField indirizzo_text; //JTextField dove viene immessa l'indirizzo
    private final JTextField password_text;//JTextField dove viene immessa la password
    private final JTextField controllopassword_text;//JTextField dove viene immessa la password una seconda volta per controllare che ti vada bene
    private final JLabel esito; //esito dell'invio
    private GSMBFrame frame_principale;

    public PanelRegistrazioneCliente(GSMBFrame frame_principale) {
        super();

        this.frame_principale = frame_principale;
        setBackground(Color.green);

        username_text = new JTextField("", 25);
        indirizzo_text = new JTextField("", 25);
        password_text = new JTextField("", 25);
        esito = new JLabel("");
        controllopassword_text = new JTextField("", 25);
        JLabel username_label = new JLabel("inserisci nuovo username");
        JLabel indirizzo_label = new JLabel("inserisci indirizzo");
        JLabel password_label = new JLabel("inserisci password");
        JLabel controllopassword_label = new JLabel("rimetti la password");
        JButton conferma = new JButton("conferma"); //conferma dell'invio di quei dati
        JButton pulsante_da_registrazione_a_accesso_user = new JButton("torna indietro");
        conferma.addActionListener(e -> {
            //tasto ottiene il nome del tasto
            VettoreCredenziali vettore_credenziali = new VettoreCredenziali(filename);                            //il vettore b viene usato per deserializzare i dati
            boolean flagcorretto = true;                         //se flagcorretto vale true, i nuovi dati sono accettati e vengono salvati nel file CredenzialiCriptate.txt, se vale false invece c'è un errore nei dati immessi


            //se viene premuto il tasto conferma
            for (Credenziali credenziali : vettore_credenziali) {  //scorre tutti gli oggetti persona Credenziali contenuti nel vettore
                if (username_text.getText().equals(credenziali.toUsername()) | username_text.getText().equals(VettoreCredenziali.CREDENZIALI_ADMIN.toUsername())) {        //se uno di quegli oggetti ha lo stesso nome del nuovo nome che vuoi usare
                    esito.setText("username già preso");                     //errore: username gia preso
                    flagcorretto = false;
                    new java.util.Timer().schedule(
                            new java.util.TimerTask() {
                                @Override
                                public void run() {
                                    esito.setText("");
                                }
                            }, 3000
                    );
                }
            }

            if (!(password_text.getText().equals(controllopassword_text.getText()))) {  //se la password che hai immesso NON è uguale alla password che hai immesso la seconda volta
                esito.setText("password errata");//errore: password errata
                new java.util.Timer().schedule(
                        new java.util.TimerTask() {
                            @Override
                            public void run() {

                                esito.setText("");
                            }
                        }, 1000
                );
                flagcorretto = false;
            }
            if (flagcorretto) { //se non ci sono stati errori
                vettore_credenziali.addAndSave(new Credenziali(username_text.getText(), password_text.getText(), indirizzo_text.getText()));  //aggiungi le nuove Credenziali al vettore b e serializzalo


                esito.setText("creazione profilo creata");

                new java.util.Timer().schedule(
                        new java.util.TimerTask() {
                            @Override
                            public void run() {
                                username_text.setText("");
                                indirizzo_text.setText("");
                                password_text.setText("");
                                controllopassword_text.setText("");
                                frame_principale.toCard("Panel accesso"); //esce dal panel dopo 3 sec
                                esito.setText("");
                            }
                        }, 1000
                );
            }


        });
        pulsante_da_registrazione_a_accesso_user.addActionListener(e -> {
            frame_principale.toCard("Panel accesso");

            username_text.setText("");
            indirizzo_text.setText("");
            password_text.setText("");
            controllopassword_text.setText("");
        });

        add(username_text);
        add(indirizzo_text);
        add(password_text);
        add(controllopassword_text);
        add(conferma);
        add(esito);
        add(pulsante_da_registrazione_a_accesso_user);
        add(username_label);
        add(indirizzo_label);
        add(password_label);
        add(controllopassword_label);
    }


}

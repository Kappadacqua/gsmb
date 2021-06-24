import javax.swing.*;
import java.awt.*;

/**
 * Panel registrazione cliente è il panel dove il cliente può creare un nuovo profilo per il programma
 * <p>
 */
public class PanelRegistrazioneCliente extends JPanel {

    String filename = "CredenzialiCriptate.txt";  //il file dove vengono salvate tutte le credenziali
    private final JTextField username; //JTextField dove viene immessa l'username
    private final JTextField indirizzo; //JTextField dove viene immessa l'indirizzo
    private final JTextField password;//JTextField dove viene immessa la password
    private final JTextField controllopasword;//JTextField dove viene immessa la password una seconda volta per controllare che ti vada bene
    private final JLabel esito; //esito dell'invio
    private GSMBFrame frame_principale;

    public PanelRegistrazioneCliente(GSMBFrame frame_principale) {
        super();

        this.frame_principale = frame_principale;
        setBackground(Color.green);

        username = new JTextField("username", 25);
        indirizzo = new JTextField("indirizzo", 25);
        password = new JTextField("password", 25);
        controllopasword = new JTextField("rimetti password", 25);
        JButton conferma = new JButton("conferma"); //conferma dell'invio di quei dati
        esito = new JLabel("");
        JButton pulsante_da_registrazione_a_accesso_user = new JButton("torna indietro");
        conferma.addActionListener(e -> {
            String tasto = e.getActionCommand();                  //tasto ottiene il nome del tasto
            VettoreCredenziali vettore_credenziali = new VettoreCredenziali(filename);                            //il vettore b viene usato per deserializzare i dati
            boolean flagcorretto = true;                         //se flagcorretto vale true, i nuovi dati sono accettati e vengono salvati nel file CredenzialiCriptate.txt, se vale false invece c'è un errore nei dati immessi


            if (tasto.equals("conferma")) {             //se viene premuto il tasto conferma
                for (Credenziali credenziali : vettore_credenziali) {  //scorre tutti gli oggetti persona Credenziali contenuti nel vettore
                    if (username.getText().equals(credenziali.toUsername())) {        //se uno di quegli oggetti ha lo stesso nome del nuovo nome che vuoi usare
                        esito.setText("username già preso");                     //errore: username gia preso
                        flagcorretto = false;
                    }
                }

                if (!(password.getText().equals(controllopasword.getText()))) {  //se la password che hai immesso NON è uguale alla password che hai immesso la seconda volta
                    esito.setText("password errata");//errore: password errata
                    flagcorretto = false;
                }
                if (flagcorretto) { //se non ci sono stati errori
                    vettore_credenziali.addAndSave(new Credenziali(username.getText(), password.getText(), indirizzo.getText()));  //aggiungi le nuove Credenziali al vettore b e serializzalo


                    esito.setText("creazione profilo creata");

                    new java.util.Timer().schedule(
                            new java.util.TimerTask() {
                                @Override
                                public void run() {
                                    frame_principale.toCard("Panel accesso");
                                    //esce dal panel dopo 3 sec
                                }
                            }, 3000
                    );
                }


            }
        });
        pulsante_da_registrazione_a_accesso_user.addActionListener(e -> frame_principale.toCard("Panel accesso"));

        add(username);
        add(indirizzo);
        add(password);
        add(controllopasword);
        add(conferma);
        add(esito);
        add(pulsante_da_registrazione_a_accesso_user);
    }



}

import javax.swing.*;
import java.awt.event.ActionListener;

/**
 * Panel accesso è la classe che gestisce  il login, sia per l'user che per l'admin
 * <p>
 */
public class PanelAccesso extends JPanel {

    private final JLabel label_esito; //dice se il login è fallito o se ha avuto successo
    private final JTextField field_username; //campo utente
    private final JPasswordField field_password; //campo password criptato
    String nomefile = "CredenzialiCriptate.txt";
    private VettoreCredenziali vettore_credenziali;

    public PanelAccesso(GSMBFrame frame_principale, ActionListener Onpulsanteclick) {

        super();

        label_esito = new JLabel("");
        field_username = new JTextField("", 20);
        field_password = new JPasswordField("", 20);
        JLabel label_iniziale = new JLabel("Gestore spedizioni Matteo Balugani");//scritta iniziale
        JLabel label_user = new JLabel("Credenziali"); //scritta user
        JLabel label_pass = new JLabel("Password"); //scritta password
        JButton pulsante_login = new JButton("invio");//pulsante invio
        JButton pulsante_da_accesso_generale_a_registrazione = new JButton("registrati");//pulsante registrati
        JButton pulsante_da_accesso_generale_a_main = new JButton("da user a ingresso");//pulsante torna al panel ingresso
        pulsante_login.addActionListener(Onpulsanteclick);
        pulsante_login.addActionListener(e -> {

            vettore_credenziali = new VettoreCredenziali(nomefile);
            String nome_utente = provaLoginUtente();
            if (nome_utente == null)
                label_esito.setText("errore: username e/o password errata");
            else if (nome_utente.equals(VettoreCredenziali.CREDENZIALI_ADMIN.toUsername())) {
                label_esito.setText("login admin avvenuto con successo");
                new java.util.Timer().schedule(
                        new java.util.TimerTask() {
                            @Override
                            public void run() {
                                frame_principale.toCard("Panel ordini admin");
                                label_esito.setText("");
                                field_username.setText("");  //mette username vuoto subito dopo aver eseguito il login
                                field_password.setText("");  //mette password vuota subito dopo aver eseguito il login
                            }
                        }, 1000);
            } else {
                label_esito.setText("login user avvenuto con successo");
                new java.util.Timer().schedule(
                        new java.util.TimerTask() {
                            @Override
                            public void run() {
                                frame_principale.toCard("Panel ordini cliente");
                                label_esito.setText("");
                                field_username.setText("");  //mette username vuoto subito dopo aver eseguito il login
                                field_password.setText("");  //mette password vuota subito dopo aver eseguito il login
                            }
                        }, 1000);
            }


        });

        pulsante_da_accesso_generale_a_registrazione.addActionListener(e -> {
            frame_principale.toCard("Panel registrazione");
            field_username.setText("");
            field_password.setText("");
        });
        pulsante_da_accesso_generale_a_main.addActionListener(e -> {
            frame_principale.toCard("Panel ingresso");
            field_username.setText("");
            field_password.setText("");
        });

        add(label_user);
        add(label_pass);
        add(label_iniziale);
        add(field_username);
        add(field_password);
        add(pulsante_login);
        add(pulsante_da_accesso_generale_a_registrazione);
        add(pulsante_da_accesso_generale_a_main);
        add(label_esito);
    }


    public String getUsername() {
        return field_username.getText();
    }

    private String provaLoginUtente() {
        String username = field_username.getText();
        String password = field_password.getText();
        if (VettoreCredenziali.CREDENZIALI_ADMIN.verificaCredenziali(username, password))
            return username;

        for (Credenziali credenziale : vettore_credenziali) {

            if (credenziale.verificaCredenziali(username, password)) {
                return username;
            }

        }
        return null;

    }


}



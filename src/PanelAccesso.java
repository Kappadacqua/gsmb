import javax.swing.*;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;


public class PanelAccesso extends JPanel {

    String filename = "CredenzialiCriptate.txt";

    private JLabel label_iniziale; //scritta iniziale
    private JLabel label_user; //scritta user
    private JLabel label_pass; //scritta password
    private JLabel label_esito; //dice se il login è fallito o se ha avuto successo
    private JTextField field_username; //campo utente
    private JTextField field_password; //campo password criptato
    private GSMBFrame frame_principale;
    private JPanel home;
    private JButton pulsante_login; //pulsante invio
    private JButton pulsante_da_accesso_generale_a_registrazione;
    private JButton pulsante_da_accesso_generale_a_main;
    private VettoreCredenziali vettore_credenziali;

    public PanelAccesso(GSMBFrame frame_principale, ActionListener Onpulsanteclick) {
        super();
        this.frame_principale = frame_principale;

        label_iniziale = new JLabel("Gestore spedizioni Matteo Balugani");
        label_user = new JLabel("Credenziali");
        label_pass = new JLabel("Password");
        label_esito = new JLabel("");

        field_username = new JTextField("", 20);
        field_password = new JTextField("", 20);

        pulsante_login = new JButton("invio");
        pulsante_da_accesso_generale_a_registrazione = new JButton("registrati");
        pulsante_da_accesso_generale_a_main = new JButton("da user a ingresso");
        pulsante_login.addActionListener(Onpulsanteclick);
        pulsante_login.addActionListener(e -> {


            // Deserialization
            try {
                // Reading the object from a file
                FileInputStream file = new FileInputStream(filename);
                ObjectInputStream in = new ObjectInputStream(file);

                // Method for deserialization of object
                vettore_credenziali = (VettoreCredenziali) in.readObject();

                in.close();
                file.close();


            } catch (IOException ex) {
                System.out.println("IOException is caught");
            } catch (ClassNotFoundException ex) {
                System.out.println("ClassNotFoundException is caught");
            }


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



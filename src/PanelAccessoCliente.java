import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;


public class PanelAccessoCliente extends JPanel implements ActionListener {

    String filename = "CredenzialiCriptate.txt";
    private JLabel s; //scritta iniziale
    private JLabel user; //scritta user
    private JLabel pass; //scritta password
    private JLabel esito; //dice se il login è fallito o se ha avuto successo
    private JTextField username; //campo utente
    private JTextField password; //campo password criptato
    private CardLayout cl;
    private JPanel home;
    private JButton pulsante_da_accesso_user_a_ordini_user; //pulsante invio
    private JButton pulsante_da_accesso_user_a_registrazione;
    private JButton pulsante_da_accesso_user_a_main;


    public PanelAccessoCliente(CardLayout cl, JPanel home, ActionListener Onpulsanteclick) {
        super();
        this.cl = cl;
        this.home = home;
        s = new JLabel("Gestore spedizioni Matteo Balugani");
        JLabel user = new JLabel("user");
        JLabel pass = new JLabel("password");
        username = new JTextField("scrivi qui il nome utente", 20);
        password = new JTextField("scrivi  qui la password", 20);
        pulsante_da_accesso_user_a_ordini_user = new JButton("invio");
        pulsante_da_accesso_user_a_registrazione = new JButton("registrati");
        pulsante_da_accesso_user_a_main = new JButton("da user a ingresso");
        esito = new JLabel("");
        pulsante_da_accesso_user_a_ordini_user.addActionListener(this);
        pulsante_da_accesso_user_a_ordini_user.addActionListener(Onpulsanteclick);
        pulsante_da_accesso_user_a_registrazione.addActionListener(e -> cl.show(home, "Panel registrazione"));
        pulsante_da_accesso_user_a_main.addActionListener(e -> {
            cl.show(home, "Panel ingresso");
            username.setText("scrivi qui il nome utente");
            password.setText("scrivi  qui la password");
        });

        add(user);
        add(pass);
        add(s);
        add(username);
        add(password);
        add(pulsante_da_accesso_user_a_ordini_user);
        add(pulsante_da_accesso_user_a_registrazione);
        add(pulsante_da_accesso_user_a_main);
        add(esito);
    }


    public void actionPerformed(ActionEvent e) {
        String nome = e.getActionCommand();
        Vettore b = new Vettore();
        boolean flagcorretto = false;

        // Deserialization
        try {
            // Reading the object from a file
            FileInputStream file = new FileInputStream(filename);
            ObjectInputStream in = new ObjectInputStream(file);

            // Method for deserialization of object
            b = (Vettore) in.readObject();

            in.close();
            file.close();


        } catch (IOException ex) {
            System.out.println("IOException is caught");
        } catch (ClassNotFoundException ex) {
            System.out.println("ClassNotFoundException is caught");
        }

        if (nome.equals("invio")) {


            for (int i = 0; i < b.size(); i++) {
                if (username.getText().equals(b.get(i).toUsername()) && password.getText().equals(b.get(i).toPassword()))
                    flagcorretto = true;
            }
            if (flagcorretto) {
                esito.setText("login avvenuto con successo");
                new java.util.Timer().schedule(
                        new java.util.TimerTask() {
                            @Override
                            public void run() {
                                cl.show(home, "Panel ordini cliente");
                                esito.setText("");
                                username.setText("");  //mette username vuoto subito dopo aver eseguito il login
                                password.setText("");  //mette password vuota subito dopo aver eseguito il login
                            }
                        }, 1000);


            } else
                esito.setText("errore: username e/o password errata");

        }

    }


    public String getUsername() {
        return username.getText();
    }

}

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class PanelAccessoAdmin extends JPanel implements ActionListener {

    private JLabel s; //scritta iniziale
    private JLabel user; //scritta user
    private JLabel pass; //scritta password
    private JLabel esito; //dice se il login è fallito o se ha avuto successo
    private JTextField username; //campo utente
    private JTextField password; //campo password criptato
    private JButton pulsante_da_accesso_admin_a_ordini_admin; //pulsante invio
    private JButton pulsante_da_accesso_admin_a_main;
    private CardLayout cl;
    private JPanel home;

    public PanelAccessoAdmin(CardLayout cl, JPanel home) {
        super();
        this.cl = cl;
        this.home = home;
        pulsante_da_accesso_admin_a_main = new JButton("da admin a ingresso");
        s = new JLabel("Gestore spedizioni Matteo Balugani");
        JLabel user = new JLabel("user");
        JLabel pass = new JLabel("password");
        username = new JTextField("scrivi qui il nome admin", 20);
        password = new JTextField("scrivi  qui la password", 20);
        pulsante_da_accesso_admin_a_ordini_admin = new JButton("invio");
        esito = new JLabel("");
        pulsante_da_accesso_admin_a_ordini_admin.addActionListener(this);
        pulsante_da_accesso_admin_a_main.addActionListener(e -> {
            cl.show(home, "Panel ingresso");
            username.setText("scrivi qui il nome admin");
            password.setText("scrivi  qui la password");
        });
        pulsante_da_accesso_admin_a_ordini_admin.addActionListener(this);
        add(user);
        add(pass);
        add(s);
        add(username);
        add(password);
        add(pulsante_da_accesso_admin_a_ordini_admin);
        add(pulsante_da_accesso_admin_a_main);
        add(esito);
    }


    public void actionPerformed(ActionEvent e) {

        if (username.getText().equals("kappa") && password.getText().equals("admin")) {
            esito.setText("login avvenuto con successo");
            new java.util.Timer().schedule(
                    new java.util.TimerTask() {
                        @Override
                        public void run() {
                            cl.show(home, "Panel ordini admin");
                            esito.setText("");
                            username.setText("");  //mette username vuoto subito dopo aver eseguito il login
                            password.setText("");  //mette password vuota subito dopo aver eseguito il login
                        }
                    }, 1000);

        } else
            esito.setText("errore: username e/o password errata");
    }
}
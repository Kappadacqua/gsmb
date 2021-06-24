import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class PanelRegistrazioneCliente extends JPanel implements ActionListener {

    String filename = "CredenzialiCriptate.txt";  //il file dove vengono salvate tutte le credenziali
    private JTextField username; //JTextField dove viene immessa l'username
    private JTextField indirizzo; //JTextField dove viene immessa l'indirizzo
    private JTextField password;//JTextField dove viene immessa la password
    private JTextField controllopasword;//JTextField dove viene immessa la password una seconda volta per controllare che ti vada bene
    private JButton conferma;//conferma dell'invio di quei dati
    private JLabel esito; //esito dell'invio
    private JButton pulsante_da_registrazione_a_accesso_user;
    private CardLayout cl;
    private JPanel home;

    public PanelRegistrazioneCliente(CardLayout cl, JPanel home) {
        super();
        this.cl = cl;
        this.home = home;
        username = new JTextField("username", 25);
        indirizzo = new JTextField("indirizzo", 25);
        password = new JTextField("password", 25);
        controllopasword = new JTextField("rimetti password", 25);
        conferma = new JButton("conferma");
        esito = new JLabel("");
        pulsante_da_registrazione_a_accesso_user = new JButton("torna indietro");
        conferma.addActionListener(this);
        pulsante_da_registrazione_a_accesso_user.addActionListener(e -> cl.show(home, "Panel accesso user"));

        add(username);
        add(indirizzo);
        add(password);
        add(controllopasword);
        add(conferma);
        add(esito);
        add(pulsante_da_registrazione_a_accesso_user);
    }


    public void actionPerformed(ActionEvent e) {
        String tasto = e.getActionCommand();                  //tasto ottiene il nome del tasto
        Vettore b = new Vettore();                            //il vettore b viene usato per deserializzare i dati
        boolean flagcorretto = true;                         //se flagcorretto vale true, i nuovi dati sono accettati e vengono salvati nel file CredenzialiCriptate.txt, se vale false invece c'è un errore nei dati immessi

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


        if (tasto.equals("conferma")) {             //se viene premuto il tasto conferma
            for (int i = 0; i < b.size(); i++) {  //scorre tutti gli oggetti persona Credenziali contenuti nel vettore
                if (username.getText().equals(b.get(i).toUsername())) {        //se uno di quegli oggetti ha lo stesso nome del nuovo nome che vuoi usare
                    esito.setText("username già preso");                     //errore: username gia preso
                    flagcorretto = false;
                }
            }

            if (!(password.getText().equals(controllopasword.getText()))) {  //se la password che hai immesso NON è uguale alla password che hai immesso la seconda volta
                esito.setText("password errata");//errore: password errata
                flagcorretto = false;
            }
            if (flagcorretto) { //se non ci sono stati errori
                b.aggiungi(new Credenziali(username.getText(), password.getText(), indirizzo.getText()));  //aggiungi le nuove Credenziali al vettore b e serializzalo

                try {

                    FileOutputStream file = new FileOutputStream(filename);
                    ObjectOutputStream out = new ObjectOutputStream(file);

                    // Method for serialization of object
                    out.writeObject(b);

                    out.close();
                    file.close();

                    System.out.println("Object has been serialized");

                } catch (IOException ex) {
                    System.out.println("IOException is caught");
                }

                esito.setText("creazione profilo creata");

                new java.util.Timer().schedule(
                        new java.util.TimerTask() {
                            @Override
                            public void run() {
                                cl.show(home, "Panel accesso user");
                                //esce dal panel dopo 3 sec
                            }
                        }, 3000
                );
            }


        }
    }
}

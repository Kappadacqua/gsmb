import javax.swing.*;
import java.awt.*;

public class PanelIngresso extends JPanel {

    private JLabel titolo;
    private JButton pulsante_da_ingresso_a_accesso_user;
    private JButton pulsante_da_ingresso_ad_accesso_admin;
    private CardLayout cl;
    private JPanel home;


    public PanelIngresso(CardLayout cl, JPanel home) {
        super();
        this.cl = cl;
        this.home = home;

        setBackground(Color.yellow);

        titolo = new JLabel("");
        pulsante_da_ingresso_a_accesso_user = new JButton("da ingresso a user");
        pulsante_da_ingresso_ad_accesso_admin = new JButton("da ingresso ad admin");

        pulsante_da_ingresso_a_accesso_user.addActionListener(e -> cl.show(home, "Panel accesso user"));
        pulsante_da_ingresso_ad_accesso_admin.addActionListener(e -> cl.show(home, "Panel accesso admin"));

        add(pulsante_da_ingresso_a_accesso_user);
        add(pulsante_da_ingresso_ad_accesso_admin);
    }

}

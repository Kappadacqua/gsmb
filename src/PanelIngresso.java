import javax.swing.*;
import java.awt.*;

public class PanelIngresso extends JPanel {

    private JLabel titolo;
    private JButton pulsante_da_ingresso_a_accesso;
    private JPanel home;
    private GSMBFrame frame_principale;

    public PanelIngresso(GSMBFrame frame_principale) {
        super();
        this.frame_principale = frame_principale;
        this.home = home;

        setBackground(Color.yellow);

        titolo = new JLabel("");
        pulsante_da_ingresso_a_accesso = new JButton("da ingresso a user");


        pulsante_da_ingresso_a_accesso.addActionListener(e -> frame_principale.toCard("Panel accesso"));

        add(pulsante_da_ingresso_a_accesso);
    }

}

import javax.swing.*;
import java.awt.*;

/**
 * Card è il {@link JFrame} principale che contiene tutti gli altri sotto-{@link JPanel}.
 */
public class GestoreSpedizioniMatteoBaluganiFrame extends JFrame {
    private PanelAccessoCliente panelaccessocliente; // primo panel
    private PanelOrdiniCliente panelordinicliente;
    private PanelCreazioneOrdineCliente panelcreazioneordinecliente;

    public GestoreSpedizioniMatteoBaluganiFrame() {
        CardLayout cl = new CardLayout(5, 5); // margine

        JPanel home = new JPanel(cl);
        home.setBackground(Color.black);

        PanelIngresso panelingresso = new PanelIngresso(cl, home);
        home.add(panelingresso, "Panel ingresso");

        panelaccessocliente = new PanelAccessoCliente(cl, home, e -> {
            panelordinicliente.setUsername(panelaccessocliente.getUsername());
            panelcreazioneordinecliente.setUsername(panelaccessocliente.getUsername());
        });
        home.add(panelaccessocliente, "Panel accesso user");

        PanelRegistrazioneCliente panelregistrazionecliente = new PanelRegistrazioneCliente(cl, home);
        home.add(panelregistrazionecliente, "Panel registrazione");

        PanelAccessoAdmin panelaccessoadmin = new PanelAccessoAdmin(cl, home);
        home.add(panelaccessoadmin, "Panel accesso admin");

        panelordinicliente = new PanelOrdiniCliente(cl, home);
        home.add(panelordinicliente, "Panel ordini cliente");

        panelcreazioneordinecliente = new PanelCreazioneOrdineCliente(cl, home);
        home.add(panelcreazioneordinecliente, "Panel creazione ordine cliente");

        PanelOrdiniAdmin panelordiniadmin = new PanelOrdiniAdmin(cl, home);
        home.add(panelordiniadmin, "Panel ordini admin");

        add(home);
        cl.show(home, "Home");

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setTitle("Gestore spedizioni Matteo Balugani");
        pack();
        setVisible(true);
        setExtendedState(JFrame.MAXIMIZED_BOTH); // mette fullscreen
    }
}



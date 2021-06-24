import javax.swing.*;
import java.awt.*;

/**
 * Card è il {@link JFrame} principale che contiene tutti gli altri sotto-{@link JPanel}.
 */
public class GSMBFrame extends JFrame {
    private final CardLayout cl;  // TODO: Cambiare a private e scrivere un getter


    private PanelOrdiniCliente panelordinicliente;
    private PanelCreazioneOrdineCliente panelcreazioneordinecliente;
    private final JPanel home;
    private PanelAccesso panelaccesso;

    public GSMBFrame() {
        cl = new CardLayout(5, 5); // margine

        home = new JPanel(cl);
        home.setBackground(Color.black);

        PanelIngresso panelingresso = new PanelIngresso(this);
        home.add(panelingresso, "Panel ingresso");


        PanelRegistrazioneCliente panelregistrazionecliente = new PanelRegistrazioneCliente(this);
        home.add(panelregistrazionecliente, "Panel registrazione");


        panelaccesso = new PanelAccesso(this, e -> {
            panelcreazioneordinecliente.setUsername(panelaccesso.getUsername());
            panelordinicliente.setUsername(panelaccesso.getUsername());
        });
        home.add(panelaccesso, "Panel accesso");

        panelordinicliente = new PanelOrdiniCliente(this);
        home.add(panelordinicliente, "Panel ordini cliente");

        panelcreazioneordinecliente = new PanelCreazioneOrdineCliente(this);
        home.add(panelcreazioneordinecliente, "Panel creazione ordine cliente");

        PanelOrdiniAdmin panelordiniadmin = new PanelOrdiniAdmin(this);
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


    public CardLayout getCardLayout() {
        return cl;
    }

    public void toCard(String nome_panel) {
        getCardLayout().show(home, nome_panel);
    }
}



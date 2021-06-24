import javax.swing.*;
import java.awt.*;

/**
 * Card è il {@link JFrame} principale che contiene tutti gli altri sotto-{@link JPanel}.
 */
public class Card extends JFrame {
    private PanelAccessoCliente panelaccessocliente;      //primo panel
    private PanelOrdiniCliente panelordinicliente;
    private PanelCreazioneOrdineCliente panelcreazioneordinecliente;

    public Card() {
        CardLayout cl = new CardLayout(5, 5);

        JPanel homeContainer = new JPanel(cl);
        homeContainer.setBackground(Color.black);

        PanelIngresso panelingresso = new PanelIngresso(cl, homeContainer);
        panelingresso.setBackground(Color.yellow);
        homeContainer.add(panelingresso, "Panel ingresso");

        panelaccessocliente = new PanelAccessoCliente(cl, homeContainer, e -> {
            panelordinicliente.setUsername(panelaccessocliente.getUsername());
            panelcreazioneordinecliente.setUsername(panelaccessocliente.getUsername());
        });
        panelaccessocliente.setBackground(Color.gray);
        homeContainer.add(panelaccessocliente, "Panel accesso user");

        //secondo panel
        PanelRegistrazioneCliente panelregistrazionecliente = new PanelRegistrazioneCliente(cl, homeContainer);
        panelregistrazionecliente.setBackground(Color.green);
        homeContainer.add(panelregistrazionecliente, "Panel registrazione");

        PanelAccessoAdmin panelaccessoadmin = new PanelAccessoAdmin(cl, homeContainer);
        panelaccessoadmin.setBackground(Color.MAGENTA);
        homeContainer.add(panelaccessoadmin, "Panel accesso admin");

        panelordinicliente = new PanelOrdiniCliente(cl, homeContainer);
        panelordinicliente.setBackground(Color.WHITE);
        homeContainer.add(panelordinicliente, "Panel ordini cliente");

        panelcreazioneordinecliente = new PanelCreazioneOrdineCliente(cl, homeContainer);
        panelcreazioneordinecliente.setBackground(Color.BLUE);
        homeContainer.add(panelcreazioneordinecliente, "Panel creazione ordine cliente");

        PanelOrdiniAdmin panelordiniadmin = new PanelOrdiniAdmin(cl, homeContainer);
        panelcreazioneordinecliente.setBackground(Color.pink);
        homeContainer.add(panelordiniadmin, "Panel ordini admin");


        add(homeContainer);
        cl.show(homeContainer, "Home");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setTitle("Gestore spedizioni Matteo Balugani");
        pack();
        setVisible(true);
    }


}



import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class PopupModificaAdmin extends JPopupMenu implements ActionListener {
    private int selectedrow;
    private JTable table;
    private JMenu modifica;
    private JMenuItem elimina;

    public PopupModificaAdmin(JTable table) {
        //create poput items
        this.table = table;
        elimina = new JMenuItem("elimina");
        modifica = new JMenu("modifica");

        JMenuItem menuItem1 = new JMenuItem("IN PREPARAZIONE");
        JMenuItem menuItem2 = new JMenuItem("IN TRANSITO");
        JMenuItem menuItem3 = new JMenuItem("RICEVUTA");
        JMenuItem menuItem4 = new JMenuItem("FALLITA");
        JMenuItem menuItem5 = new JMenuItem("RIMBORSO RICHIESTO");
        JMenuItem menuItem6 = new JMenuItem("RIMBORSO EROGATO");

        menuItem1.addActionListener(this);
        menuItem2.addActionListener(this);
        menuItem3.addActionListener(this);
        menuItem4.addActionListener(this);
        menuItem5.addActionListener(this);
        menuItem6.addActionListener(this);

        modifica.add(menuItem1);
        modifica.add(new JSeparator());
        modifica.add(menuItem2);
        modifica.add(new JSeparator());
        modifica.add(menuItem3);
        modifica.add(new JSeparator());
        modifica.add(menuItem4);
        modifica.add(new JSeparator());
        modifica.add(menuItem5);
        modifica.add(new JSeparator());
        modifica.add(menuItem6);


        elimina.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                JOptionPane.showMessageDialog(elimina, "eliminato");
            }
        });

        /*modifica.setEnabled(false);
        elimina.setEnabled(false);
*/
        add(modifica);
        add(new JSeparator());//è una semplice riga che separa i tasti edit e delete
        add(elimina);


/*        if (table.getValueAt(selectedrow, 2).equals("RICEVUTA") || table.getValueAt(selectedrow, 2).equals("RIMBORSO EROGATO")) {

            modifica.setEnabled(false);
            elimina.setEnabled(true);
        }
        else
        {

            modifica.setEnabled(true);
            elimina.setEnabled(false);
        }*/
    }


    public void actionPerformed(ActionEvent e) {

        String nome = e.getActionCommand();
        if (nome.equals("IN PREPARAZIONE"))
            table.setValueAt("IN PREPARAZIONE", selectedrow, 2);
        else if (nome.equals("IN TRANSITO"))
            table.setValueAt("IN TRANSITO", selectedrow, 2);
        if (nome.equals("RICEVUTA"))
            table.setValueAt("RICEVUTA", selectedrow, 2);
        else if (nome.equals("FALLITA"))
            table.setValueAt("FALLITA", selectedrow, 2);
        if (nome.equals("RIMBORSO RICHIESTO"))
            table.setValueAt("RIMBORSO RICHIESTO", selectedrow, 2);
        else if (nome.equals("RIMBORSO EROGATO"))
            table.setValueAt("RIMBORSO EROGATO", selectedrow, 2);


    }

    public void setRow(int selectedrow) {
        this.selectedrow = selectedrow;
    }


    public void setTasti(boolean flag) {
        if (flag) {
            modifica.setEnabled(false);
            elimina.setEnabled(true);
        } else {
            modifica.setEnabled(true);
            elimina.setEnabled(false);
        }
    }
}
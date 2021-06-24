import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class PopupElimina extends JPopupMenu {
    private int selectedrow;
    private JTable table;
    private VettoreOrdini v;

    public PopupElimina(JTable table) {
        //create poput items
        this.table = table;


        JMenuItem elimina = new JMenuItem("elimina");

        //when edit clicked
        elimina.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {



                /*   v.rimuovi(v.get(selectedrow));*/
                table.updateUI();

                JOptionPane.showMessageDialog(elimina, "eliminato");
            }
        });

        add(elimina);
    }

    public void setrowandvet(int selectedrow, VettoreOrdini v) {
        this.selectedrow = selectedrow;
        this.v = v;
    }


}
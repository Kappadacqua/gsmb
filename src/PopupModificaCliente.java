import javax.swing.*;

class PopupModificaCliente extends JPopupMenu {
    private int selectedrow;
    private JTable table;

    public PopupModificaCliente(JTable table) {
        //create poput items
        this.table = table;
        JMenu modifica = new JMenu("modifica");
        JMenuItem menuItem = new JMenuItem("RIMBORSO RICHIESTO");
        modifica.add(menuItem);
        menuItem.addActionListener(e -> {

            if (table.getValueAt(selectedrow, 2).equals("FALLITA") && table.getValueAt(selectedrow, 5).equals(""))
                table.setValueAt("RIMBORSO RICHIESTO", selectedrow, 2);


        });
        add(modifica);

    }

    public void setrow(int selectedrow) {
        this.selectedrow = selectedrow;
    }


}
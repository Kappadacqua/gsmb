import javax.swing.*;


/**
 * PopupModificaCliente è la classe che fa apparire il menupopup dopo aver premuto il tasto destro sulla riga della tabella contenuta in {@link PanelOrdiniCliente}.
 * <p>
 * Essa è figlia di {@link JPopupMenu}.
 */
class PopupModificaCliente extends JPopupMenu {
    private int selectedrow;
    private JTable table;
    private JMenu modifica;
    private VettoreOrdini ordini_totali;

    public PopupModificaCliente(JTable table) {
        //create poput items
        this.table = table;
        modifica = new JMenu("modifica");
        JMenuItem menuItem = new JMenuItem("RIMBORSO RICHIESTO");
        modifica.add(menuItem);
        menuItem.addActionListener(e -> {

            if (table.getValueAt(selectedrow, 2).equals("FALLITA") && !table.getValueAt(selectedrow, 5).equals("")) {
                table.setValueAt("RIMBORSO RICHIESTO", selectedrow, 2);
                ordini_totali.save();
            }

        });
        add(modifica);

    }

    public void setRow(int selectedrow, VettoreOrdini ordini_totali) {
        this.selectedrow = selectedrow;
        this.ordini_totali = ordini_totali;
    }

    public void setTasti(boolean flag) {
        if (flag) {
            modifica.setEnabled(true);

        } else {
            modifica.setEnabled(false);

        }
    }


}
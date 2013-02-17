package de.fernuni_hagen.k01618.bootcamp.tictactoe;

import java.awt.Font;

import javax.swing.JTable;

public class TTTOnJTableTester {

    /**
     * @param args
     */
    public static void main(final String[] args) {
        TTTBrettspielstellung ttt_model = new TTTBrettspielstellung(3);
        ttt_model.setFeldZustand(1, 1, TTTZustand.X);
        TTTTableModel ttt_table_model = new TTTTableModel(ttt_model);
        JTable jtable = new JTable(ttt_table_model);
        jtable.setFont(new Font("Monaco", 0, 24));
        new TTTGuiTestStarter(jtable).setVisible(true);

        try {
            Thread.sleep(1000l);
            ttt_model.setFeldZustand(0, 0, TTTZustand.O);
            ttt_table_model.fireTableCellUpdated(0, 0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

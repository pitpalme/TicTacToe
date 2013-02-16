package de.fernuni_hagen.k01618.bootcamp.tictactoe;

import java.awt.Dimension;

import javax.swing.JComponent;
import javax.swing.JFrame;

import de.fernuni_hagen.k01618.IBrettspielstellung;

public class TTTStart {

    /**
     * @param args
     * @throws Throwable
     */
    public static void main(final String[] args) throws Throwable {
        new TTTStart().run(args);
    }

    private void run(final String[] args) throws Throwable {
        JFrame mainFrame = new JFrame("Tic, Trick und Track");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setMinimumSize(new Dimension(300, 300));
        IBrettspielstellung ttt = erzeugeSpielstellung();
        mainFrame.add(erzeugeSpielfeld(ttt));
        mainFrame.pack();

        mainFrame.setVisible(true);
        mainFrame.repaint();
    }

    private IBrettspielstellung erzeugeSpielstellung() {
        return new TTTBrettspielstellung(3);
    }

    private JComponent erzeugeSpielfeld(
            final IBrettspielstellung ttt) {
        JComponent comp = new TTTSpielfeld(ttt);
        return comp;
    }
}

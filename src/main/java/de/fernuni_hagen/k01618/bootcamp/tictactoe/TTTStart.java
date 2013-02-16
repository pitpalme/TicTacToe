package de.fernuni_hagen.k01618.bootcamp.tictactoe;

import java.awt.Dimension;

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

    public void run(final String[] args) throws Throwable {
        IBrettspielstellung ttt = erzeugeSpielstellung();
        TTTSpielfeld view = erzeugeSpielfeld(ttt);

        JFrame mainFrame = new JFrame("Tic, Trick und Track");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setMinimumSize(new Dimension(300, 300));
        mainFrame.add(view);
        mainFrame.pack();
        mainFrame.setVisible(true);
        mainFrame.repaint();

        new TTTController(ttt, view, view);
    }

    private TTTSpielfeld erzeugeSpielfeld(final IBrettspielstellung ttt) {
        TTTSpielfeld comp = new TTTSpielfeld(ttt);
        return comp;
    }

    private IBrettspielstellung erzeugeSpielstellung() {
        return new TTTBrettspielstellung(3);
    }
}

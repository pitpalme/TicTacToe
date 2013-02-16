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
        int dim = 4;
        int paintFactor = 100;

        IBrettspielstellung ttt = erzeugeSpielstellung(dim);
        TTTSpielfeld view = erzeugeSpielfeld(ttt, paintFactor);

        JFrame mainFrame = new JFrame("Tic, Trick und Track");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setMinimumSize(new Dimension(dim * paintFactor, dim
                * paintFactor));
        mainFrame.add(view);
        mainFrame.pack();
        mainFrame.setVisible(true);
        mainFrame.repaint();

        new TTTController(ttt, view, view);
    }

    private TTTSpielfeld erzeugeSpielfeld(
            final IBrettspielstellung ttt, final int paintFactor) {
        TTTSpielfeld comp = new TTTSpielfeld(ttt, paintFactor);
        return comp;
    }

    private IBrettspielstellung erzeugeSpielstellung(final int dimension) {
        return new TTTBrettspielstellung(dimension);
    }
}

package de.fernuni_hagen.k01618.bootcamp.tictactoe;

import java.awt.Dimension;

import javax.swing.JFrame;

import de.fernuni_hagen.k01618.IBrettspielstellung;
import de.fernuni_hagen.k01618.IMoveEventListener;
import de.fernuni_hagen.k01618.ISpieler;

public class TTTStart {
    private int dim = 3;
    private int paintFactor = 100;

    /**
     * @param args
     * @throws Throwable
     */
    public static void main(final String[] args) throws Throwable {
        new TTTStart().run(args);
    }


    public void run(final String[] args) throws Throwable {
        parseArgs(args);

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

        // new TTTController(ttt, view, view);
        ISpieler[] spieler = new ISpieler[] { new TTTMenschSpieler(),
                new TTTNetzwerkSpieler("localhost") };
        view.addMoveEventListener((IMoveEventListener) spieler[0]);

        new TTTPartieController(ttt, view, spieler).run();
    }

    private TTTSpielfeld erzeugeSpielfeld(
            final IBrettspielstellung ttt, final int paintFactor) {
        TTTSpielfeld comp = new TTTSpielfeld(ttt, paintFactor);
        return comp;
    }

    private IBrettspielstellung erzeugeSpielstellung(final int dimension) {
        return new TTTBrettspielstellung(dimension);
    }

    private void parseArgs(final String[] args) {
        if (null != args) {
            if (null != args[0]) {
                try {
                    dim = Integer.parseInt(args[0]);
                } catch (NumberFormatException e) {
                    System.err
                            .println("Erstes Argument muss eine Zahl (=Dimension) sein.");
                }
            }
        }
    }
}

package de.fernuni_hagen.k01618.bootcamp.tictactoe;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import de.fernuni_hagen.k01618.IBrettspielstellung;
import de.fernuni_hagen.k01618.ISpieler;

public class TTTButtonStart {
    private int dim = 3;
    private int paintFactor = 100;

    /**
     * @param args
     * @throws Throwable
     */
    public static void main(final String[] args) throws Throwable {
        new TTTButtonStart().run(args);
    }

    public void run(final String[] args) throws Throwable {
        parseArgs(args);

        final IBrettspielstellung ttt = erzeugeSpielstellung(dim);
        final TTTSpielfeld view = erzeugeSpielfeld(ttt, paintFactor);

        JFrame mainFrame = new JFrame("Tic, Trick und Track");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setPreferredSize(new Dimension(dim * paintFactor, dim
                * paintFactor));
        mainFrame.add(view);
        // new TTTController(ttt, view, view);
        // view.addMoveEventListener((IMoveEventListener) spieler[0]);

        JButton jb = new JButton("Start");
        jb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                ISpieler[] spieler = new ISpieler[] {
                        new TTTNetzwerkSpieler("localhost"),
                        new TTTNetzwerkSpieler("localhost") };
                new Thread(new TTTPartieController(ttt, view, spieler))
                        .start();
            }
        });
        mainFrame.add(jb, BorderLayout.SOUTH);

        mainFrame.pack();
        mainFrame.setVisible(true);
        mainFrame.repaint();
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

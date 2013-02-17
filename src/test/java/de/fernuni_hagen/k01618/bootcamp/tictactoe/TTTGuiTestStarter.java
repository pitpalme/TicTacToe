package de.fernuni_hagen.k01618.bootcamp.tictactoe;

import java.awt.Dimension;

import javax.swing.JComponent;
import javax.swing.JFrame;

import de.fernuni_hagen.k01618.IBrettspielstellung;

public class TTTGuiTestStarter extends JFrame {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public TTTGuiTestStarter(final JComponent testObjekt) {
        super();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setPreferredSize(new Dimension(300, 300));
        add(testObjekt);
        pack();
    }

    /**
     * @param args
     * @throws Throwable
     */
    public static void main(final String[] args) throws Throwable {
        IBrettspielstellung ttt = new TTTBrettspielstellung(3);
        JComponent comp = new TTTSpielfeld(ttt);

        new TTTGuiTestStarter(comp).setVisible(true);
        Thread.sleep(1000l);
        ttt.setFeldZustand(0, 0, TTTZustand.X);
        ttt.setFeldZustand(1, 1, TTTZustand.O);
        comp.repaint();
    }

}

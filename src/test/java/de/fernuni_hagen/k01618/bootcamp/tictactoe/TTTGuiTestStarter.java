package de.fernuni_hagen.k01618.bootcamp.tictactoe;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;

public class TTTGuiTestStarter extends JFrame {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public TTTGuiTestStarter(final JComponent testObjekt) {
        super();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(300, 300);
        setLocationRelativeTo(null);

        add(testObjekt);
    }

    /**
     * @param args
     */
    public static void main(final String[] args) {
        JButton jb = new JButton("TEST ME");

        new TTTGuiTestStarter(jb).setVisible(true);
    }

}

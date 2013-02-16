package de.fernuni_hagen.k01618.bootcamp.tictactoe;

public class TTTBrettspielstellungTest {

    /**
     * @param args
     */
    public static void main(final String[] args) {
        IBrettspielstellung testSpiel = new TTTBrettspielstellung(3);
        System.out.println(testSpiel.toString(true));
        System.out
                .println(testSpiel.setFeldZustand(1, 1, TTTZustand.X));
        System.out.println(testSpiel.toString(true));
        System.out
                .println(testSpiel.setFeldZustand(0, 0, TTTZustand.O));
        System.out.println(testSpiel.toString(true));
        System.out
                .println(testSpiel.setFeldZustand(2, 2, TTTZustand.X));
        System.out.println(testSpiel.toString(true));
        System.out
                .println(testSpiel.setFeldZustand(1, 2, TTTZustand.O));
        System.out.println(testSpiel.toString(true));
        System.out
                .println(testSpiel.setFeldZustand(1, 2, TTTZustand.N));
        System.out.println(testSpiel.toString(true));
    }
}

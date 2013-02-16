package de.fernuni_hagen.k01618;

public interface IBrettspielstellung {
    int getDimension();

    boolean setFeldZustand(int spalte, int zeile, IZustand z);

    IZustand getFeldZustand(int spalte, int zeile);

    String toString(boolean verbose);
    // IZustand[][] auslesen();
}

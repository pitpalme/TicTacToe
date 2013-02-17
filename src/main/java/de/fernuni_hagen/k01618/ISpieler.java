package de.fernuni_hagen.k01618;


public interface ISpieler {
    void leereZug();

    Point holeZug(String stellung) throws PlayerException;
}

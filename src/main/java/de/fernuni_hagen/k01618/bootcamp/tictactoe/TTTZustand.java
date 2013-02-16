package de.fernuni_hagen.k01618.bootcamp.tictactoe;

public enum TTTZustand implements IZustand {
    N("_"), X("x"), O("o");

    private String value;

    private TTTZustand(final String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}

package de.fernuni_hagen.k01618.bootcamp.tictactoe;

import de.fernuni_hagen.k01618.IZustand;

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

    @Override
    public IZustand parseValue(final String value) {
        for (TTTZustand v : values()) {
            if (v.value.equals(value))
                return v;
        }
        return null;
    }
}

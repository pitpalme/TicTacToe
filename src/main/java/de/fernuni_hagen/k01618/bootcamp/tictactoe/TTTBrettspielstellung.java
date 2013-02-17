package de.fernuni_hagen.k01618.bootcamp.tictactoe;

import de.fernuni_hagen.k01618.IBrettspielstellung;
import de.fernuni_hagen.k01618.IZustand;

public class TTTBrettspielstellung implements IBrettspielstellung {
    private final int dimension;
    private IZustand[][] spielfeld;

    public TTTBrettspielstellung(final int dimension) {
        super();
        this.dimension = dimension;
        spielfeld = new IZustand[dimension][dimension];
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                spielfeld[i][j] = TTTZustand.N;
            }
        }
    }

    @Override
    public int getDimension() {
        return dimension;
    }

    @Override
    public boolean setFeldZustand(final int spalte, final int zeile,
            final IZustand z) throws ArrayIndexOutOfBoundsException {
        if (null == z)
            throw new IllegalArgumentException(
                    "Keine Arme, keine Kekse!!!");

        if (TTTZustand.N == spielfeld[zeile][spalte]) {
            spielfeld[zeile][spalte] = z;
            return true;
        }
        return false;
    }

    @Override
    public IZustand getFeldZustand(final int spalte, final int zeile)
            throws ArrayIndexOutOfBoundsException {
        return spielfeld[zeile][spalte];
    }

    @Override
    public String toString(final boolean verbose) {
        StringBuilder sb = new StringBuilder();
        if (verbose) {
            sb.append(getClass().getSimpleName())
                    .append(": dimension=").append(dimension)
                    .append(", spielfeld=");
        }
        sb.append(toString()).append('\n');
        return sb.toString();
    }

    @Override
    public boolean fromString(final String in) {
        if (null != in) {
            String data = in.trim();
            if (data.length() == (dimension * dimension)) {
                for (int i = 0; i < dimension; ++i) {
                    for (int j = 0; j < dimension; ++j) {
                        int pos = ((i * dimension) + j);
                        IZustand v = TTTZustand.N.parseValue(data
                                .substring(pos, ++pos));
                        if (null == v) {
                            System.err
                                    .println("Eingabedatenfehler an Position "
                                            + pos);
                            return false;
                        }
                        spielfeld[i][j] = v;
                    }
                }
                return true;
            }
        }
        System.err.println("Ungültige Eingabedaten '" + in + "'");
        return false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (IZustand[] spalte : spielfeld) {
            for (IZustand feld : spalte) {
                sb.append(feld);
            }
        }
        return sb.toString();
    }
}

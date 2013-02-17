package de.fernuni_hagen.k01618.bootcamp.tictactoe;

import de.fernuni_hagen.k01618.Communicator;
import de.fernuni_hagen.k01618.CommunicatorException;
import de.fernuni_hagen.k01618.ISpieler;
import de.fernuni_hagen.k01618.PlayerException;
import de.fernuni_hagen.k01618.Point;

public class TTTNetzwerkSpieler implements ISpieler {
    private static final String ENCODING = "utf-8";
    Communicator comm;

    public TTTNetzwerkSpieler(final String server) {
        comm = new Communicator(server, 7890, ENCODING);
    }

    @Override
    public void leereZug() {
    }

    @Override
    public Point holeZug(final String stellung) throws PlayerException {
        int dim = (int) Math.sqrt(stellung.length());
        String response = null;
        try {
            response = comm.communicate(stellung + "\n");
            if (null == response)
                throw new PlayerException("Keine Antwort - kein Spiel!");
            try {
                int val = Integer.parseInt(response.trim()) - 1;
                return new Point(val % dim, val / dim);
            } catch (NumberFormatException e) {
                throw new PlayerException("Antwort keine Zahl: "
                        + response, e);
            }
        } catch (CommunicatorException e) {
            throw new PlayerException("Kommunikationsfehler", e);
        }
    }
}

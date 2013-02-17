package de.fernuni_hagen.k01618.bootcamp.tictactoe;

import de.fernuni_hagen.k01618.IMoveEventListener;
import de.fernuni_hagen.k01618.ISpieler;
import de.fernuni_hagen.k01618.Point;

public class TTTMenschSpieler implements ISpieler, IMoveEventListener {
    volatile Point letzterZug;

    public TTTMenschSpieler() {
    }

    @Override
    public void leereZug() {
        letzterZug = null;
    }

    @Override
    public synchronized Point holeZug() {
        return letzterZug;
    }

    @Override
    public synchronized void moved(final int x, final int y) {
        letzterZug = new Point(x, y);
    }

}

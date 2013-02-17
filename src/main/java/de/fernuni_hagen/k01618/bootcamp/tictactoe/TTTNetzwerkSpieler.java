package de.fernuni_hagen.k01618.bootcamp.tictactoe;

import de.fernuni_hagen.k01618.ISpieler;
import de.fernuni_hagen.k01618.Point;

public class TTTNetzwerkSpieler implements ISpieler {
    @Override
    public void leereZug() {
    }

    @Override
    public Point holeZug() {
        return new Point(2, 2);
    }

}

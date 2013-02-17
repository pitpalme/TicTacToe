package de.fernuni_hagen.k01618.bootcamp.tictactoe;

import de.fernuni_hagen.k01618.IBrettspielstellung;
import de.fernuni_hagen.k01618.ISpieler;
import de.fernuni_hagen.k01618.ISpielfeld;
import de.fernuni_hagen.k01618.IZustand;
import de.fernuni_hagen.k01618.PlayerException;
import de.fernuni_hagen.k01618.Point;

public class TTTPartieController {
    private IBrettspielstellung model;
    private ISpielfeld view;
    private ISpieler[] spieler;
    private IZustand nextPlayerVal;

    public TTTPartieController(final IBrettspielstellung bla,
            final ISpielfeld fasel, final ISpieler[] keks) {
        model = bla;
        view = fasel;
        spieler = keks;
        nextPlayerVal = TTTZustand.N;
    }

    public void run() {
        for (int i = 0; i < (model.getDimension() * model
                .getDimension()); ++i) {
            ISpieler aktuellerSpieler = spieler[i % spieler.length];
            nextPlayerVal = (nextPlayerVal == TTTZustand.X) ? TTTZustand.O
                    : TTTZustand.X;
            Point zug = null;
            aktuellerSpieler.leereZug();
            try {
                while (null == (zug = aktuellerSpieler.holeZug(model
                        .toString()))) {
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        throw new IllegalStateException(e);
                    }
                }
            } catch (PlayerException e) {
                throw new IllegalStateException(e);
            }
            model.setFeldZustand(zug.spalte, zug.zeile, nextPlayerVal);
            view.update();
        }
    }
}

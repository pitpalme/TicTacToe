package de.fernuni_hagen.k01618.bootcamp.tictactoe;

import de.fernuni_hagen.k01618.IBrettspielstellung;
import de.fernuni_hagen.k01618.bootcamp.IMoveEventListener;
import de.fernuni_hagen.k01618.bootcamp.IMoveEventSource;

public class TTTController implements IMoveEventListener {

    private IBrettspielstellung ttt;
    private TTTSpielfeld view;
    private IMoveEventSource eventSource;
    private TTTZustand z;

    public TTTController(final IBrettspielstellung bla,
            final TTTSpielfeld fasel, final IMoveEventSource blub) {
        ttt = bla;
        view = fasel;
        eventSource = blub;
        z = TTTZustand.N;
        eventSource.setMoveEventListener(this);
    }

    @Override
    public void moved(final int x, final int y) {
        System.out.println(x + ":" + y);
        ttt.setFeldZustand(x, y,
                (z = (z == TTTZustand.X) ? TTTZustand.O : TTTZustand.X));
        view.repaint();
    }

}

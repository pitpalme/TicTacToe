package de.fernuni_hagen.k01618.bootcamp.tictactoe;

import de.fernuni_hagen.k01618.IBrettspielstellung;
import de.fernuni_hagen.k01618.IMoveEventListener;
import de.fernuni_hagen.k01618.IMoveEventSource;
import de.fernuni_hagen.k01618.ISpielfeld;

public class TTTController implements IMoveEventListener {

    private IBrettspielstellung ttt;
    private ISpielfeld view;
    private IMoveEventSource eventSource;
    private TTTZustand z;

    public TTTController(final IBrettspielstellung bla,
            final ISpielfeld fasel, final IMoveEventSource blub) {
        ttt = bla;
        view = fasel;
        eventSource = blub;
        z = TTTZustand.N;
        eventSource.setMoveEventListener(this);
    }

    @Override
    public void moved(final int x, final int y) {
        ttt.setFeldZustand(x, y,
                (z = (z == TTTZustand.X) ? TTTZustand.O : TTTZustand.X));
        view.update();
    }

}

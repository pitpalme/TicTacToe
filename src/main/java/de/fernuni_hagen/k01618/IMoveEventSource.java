package de.fernuni_hagen.k01618;

public interface IMoveEventSource {
    void addMoveEventListener(IMoveEventListener listener);

    void removeMoveEventListener(IMoveEventListener listener);
}

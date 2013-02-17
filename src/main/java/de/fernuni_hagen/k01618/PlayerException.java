package de.fernuni_hagen.k01618;

public class PlayerException extends Exception {
    private static final long serialVersionUID = 1L;

    public PlayerException(final String message, final Throwable cause,
            final boolean enableSuppression,
            final boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public PlayerException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public PlayerException(final String message) {
        super(message);
    }

    public PlayerException(final Throwable cause) {
        super(cause);
    }
}

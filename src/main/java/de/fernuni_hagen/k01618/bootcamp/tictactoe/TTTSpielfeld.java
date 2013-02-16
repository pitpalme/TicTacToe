package de.fernuni_hagen.k01618.bootcamp.tictactoe;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;

import de.fernuni_hagen.k01618.IBrettspielstellung;
import de.fernuni_hagen.k01618.IMoveEventListener;
import de.fernuni_hagen.k01618.IMoveEventSource;

public class TTTSpielfeld extends JPanel implements IMoveEventSource {
    private static final long serialVersionUID = 1L;
    private static Map<RenderingHints.Key, Object> renderingHints;

    private IBrettspielstellung data;
    private int dimension;
    private int drawFactor;
    private double scale = 1.0;
    private int moveX;
    private int moveY;
    private IMoveEventListener moveListener;
    private final int offset10pc;
    private final int width80pc;
    private final int offset90pc;

    public TTTSpielfeld(final IBrettspielstellung ttt) {
        this(ttt, 100);
    }

    public TTTSpielfeld(final IBrettspielstellung ttt,
            final int paintFactor) {
        super();
        data = ttt;
        dimension = ttt.getDimension();

        drawFactor = paintFactor;
        offset10pc = (int) (drawFactor * 0.1f);
        width80pc = (int) (drawFactor * 0.8f);
        offset90pc = (int) (drawFactor * 0.9f);

        setBackground(Color.LIGHT_GRAY);
        setForeground(Color.BLACK);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(final MouseEvent e) {
                int x = (int) Math.floor((e.getPoint().getX() - moveX)
                        / scale / drawFactor);
                int y = (int) Math.floor((e.getPoint().getY() - moveY)
                        / scale / drawFactor);
                if ((0 <= x) && (dimension > x) && (0 <= y)
                        && (dimension > y)) {
                    if (null != moveListener) {
                        moveListener.moved(x, y);
                    }
                }
            }
        });
    }

    @Override
    public void setMoveEventListener(final IMoveEventListener listener) {
        moveListener = listener;
    }

    @Override
    protected void paintComponent(final Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHints(renderingHints);

        drawGrid(g2d);
        for (int i = 0; i < dimension; ++i) {
            for (int j = 0; j < dimension; ++j) {
                TTTZustand zustand = (TTTZustand) data.getFeldZustand(
                        i, j);
                switch (zustand) {
                case X:
                    drawCross(g2d, i, j);
                    break;
                case O:
                    drawCircle(g2d, i, j);
                case N:
                    break;
                default:
                    throw new IllegalStateException(
                            "Was'n das für'n komischer Wert???");
                }
            }
        }
    }

    private void drawGrid(final Graphics2D g2d) {
        setupTransformation(g2d);

        // draw grid lines
        for (int i = 1; i < dimension; ++i) {
            g2d.drawLine(0, i * drawFactor, dimension * drawFactor, i
                    * drawFactor);
            g2d.drawLine(i * drawFactor, 0, i * drawFactor, dimension
                    * drawFactor);
        }
        // draw outer frame line
        g2d.drawLine(0, 0, dimension * drawFactor, 0);
        g2d.drawLine(0, 0, 0, dimension * drawFactor);
        g2d.drawLine(dimension * drawFactor, dimension * drawFactor,
                dimension * drawFactor, 0);
        g2d.drawLine(dimension * drawFactor, dimension * drawFactor, 0,
                dimension * drawFactor);
    }

    private void setupTransformation(final Graphics2D g2d) {
        scale = Math.min(getWidth() / (float) (dimension * drawFactor),
                getHeight() / (float) (dimension * drawFactor));

        moveX = 0;
        moveY = 0;
        if (getWidth() > getHeight()) {
            moveX = (getWidth() - getHeight()) / 2;
        } else {
            moveY = (getHeight() - getWidth()) / 2;
        }
        AffineTransform transScale = AffineTransform.getScaleInstance(
                scale, scale);
        AffineTransform transMove = AffineTransform
                .getTranslateInstance(moveX, moveY);

        transMove.concatenate(transScale);
        g2d.transform(transMove);
    }

    private void drawCross(final Graphics2D g, final int spalte,
            final int zeile) {
        final int offsetX = spalte * drawFactor;
        final int offsetY = zeile * drawFactor;
        Color orig = g.getColor();
        g.setColor(Color.RED);
        g.drawLine(10 + offsetX, offset10pc + offsetY, offset90pc
                + offsetX, offset90pc + offsetY);
        g.drawLine(offset90pc + offsetX, offset10pc + offsetY,
                offset10pc + offsetX, offset90pc + offsetY);
        g.setColor(orig);
    }

    private void drawCircle(final Graphics2D g, final int spalte,
            final int zeile) {
        Color orig = g.getColor();
        g.setColor(Color.BLUE);
        g.drawOval(offset10pc + (spalte * drawFactor), offset10pc
                + (zeile * drawFactor), width80pc, width80pc);
        g.setColor(orig);
    }

    static {
        renderingHints = new HashMap<RenderingHints.Key, Object>(1);
        renderingHints.put(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
    }
}

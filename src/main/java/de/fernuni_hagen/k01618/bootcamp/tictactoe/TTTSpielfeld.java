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

public class TTTSpielfeld extends JPanel {
    private static final long serialVersionUID = 1L;
    private Map<RenderingHints.Key, Object> renderingHints;
    private IBrettspielstellung data;
    private int dimension;
    private double scale = 1.0;
    private double moveX;
    private double moveY;

    public TTTSpielfeld(final IBrettspielstellung ttt) {
        super();
        data = ttt;
        dimension = ttt.getDimension();
        setBackground(Color.LIGHT_GRAY);
        setForeground(Color.BLACK);
        renderingHints = new HashMap<RenderingHints.Key, Object>(2);
        renderingHints.put(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(final MouseEvent e) {

                int x = (int) Math
                        .floor(((e.getPoint().getX() / scale) - moveX) / 100);
                int y = (int) Math
                        .floor(((e.getPoint().getY() / scale) - moveY) / 100);
                if ((0 <= x) && (2 >= x) && (0 <= y) && (2 >= y)) {
                    System.out.println(x + ":" + y);
                }
            }
        });
    }

    @Override
    protected void paintComponent(final Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHints(renderingHints);

        drawGrid(g2d);
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
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
                            "Was'n das für'n Scheiss???");
                }
            }
        }
    }

    private void drawGrid(final Graphics2D g2d) {
        scale = Math.min(getWidth() / (dimension * 100.0), getHeight()
                / (dimension * 100.0));

        moveX = 0.0;
        moveY = 0.0;
        if (getWidth() > getHeight()) {
            moveX = (double) (getWidth() - getHeight()) / 2;
        } else {
            moveY = (double) (getHeight() - getWidth()) / 2;
        }
        AffineTransform transMove = AffineTransform
                .getTranslateInstance(moveX, moveY);
        AffineTransform transScale = AffineTransform.getScaleInstance(
                scale, scale);
        transMove.concatenate(transScale);

        g2d.transform(transMove);

        // TODO Dimension berücksichtigen
        // horizontal 1
        g2d.drawLine(0, 100, 300, 100);
        // horizontal 2
        g2d.drawLine(0, 200, 300, 200);
        // vertikal 1
        g2d.drawLine(100, 0, 100, 300);
        // vertikal 2
        g2d.drawLine(200, 0, 200, 300);
    }

    private void drawCross(final Graphics2D g, final int spalte,
            final int zeile) {
        // TODO Dimension berücksichtigen
        int offsetX = spalte * 100;
        int offsetY = zeile * 100;
        Color orig = g.getColor();
        g.setColor(Color.RED);
        g.drawLine(10 + offsetX, 10 + offsetY, 90 + offsetX,
                90 + offsetY);
        g.drawLine(90 + offsetX, 10 + offsetX, 10 + offsetX,
                90 + offsetY);
        g.setColor(orig);
    }

    private void drawCircle(final Graphics2D g, final int spalte,
            final int zeile) {
        // TODO Dimension berücksichtigen
        Color orig = g.getColor();
        g.setColor(Color.BLUE);
        g.drawOval(10 + (spalte * 100), 10 + (zeile * 100), 80, 80);
        g.setColor(orig);
    }

    private void transformGraphcisToUserCoordinateSystem() {
    }

}

package view;

import util.Config;

import javax.swing.*;

import model.FloorModel;

import java.awt.*;

public class FloorView extends JPanel {

    private final FloorModel model;
    private final Color gridColor = new Color(203, 203, 203, 100);

    public FloorView(FloorModel model) {
        this.model = model;
        setBackground(Color.WHITE);
        setFocusable(true);
        requestFocusInWindow();
    }

    private void drawLineGrid(Graphics g) {
        if (!model.isShowLineGrid()) return;
        g.setColor(gridColor);
        int gridSize = model.getGridSize();
        for (int x = 0; x < getWidth(); x += gridSize) {
            g.drawLine(x, 0, x, getHeight());
        }
        for (int y = 0; y < getHeight(); y += gridSize) {
            g.drawLine(0, y, getWidth(), y);
        }
    }

    private void drawDotGrid(Graphics g) {
        if (!model.isShowDotGrid()) return;
        g.setColor(gridColor);
        int gridSize = model.getGridSize();
        for (int x = 0; x < getWidth(); x += gridSize) {
            for (int y = 0; y < getHeight(); y += gridSize) {
                g.fillOval(x - Config.DOT_SIZE / 2, y - Config.DOT_SIZE / 2, Config.DOT_SIZE, Config.DOT_SIZE);
            }
        }
    }

    private void drawRooms(Graphics g) {
        for (RoomView room : model.getRoomViews()) {
            room.draw(g);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawLineGrid(g);
        drawDotGrid(g);
        drawRooms(g);
    }
}

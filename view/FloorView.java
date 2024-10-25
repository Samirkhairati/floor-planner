package view;

import util.Config;

import javax.swing.*;

import model.FloorModel;
import model.RoomModel;

import java.awt.*;

public class FloorView extends JPanel {

    private final FloorModel model;
    private Point mousePoint;
    private boolean isPlacingRoom;
    private Dimension previewRoomSize;

    public FloorView(FloorModel model) {
        this.model = model;
        setBackground(Color.WHITE);
        setFocusable(true);
        requestFocusInWindow();
    }

    public boolean isPlacingRoom() {
        return isPlacingRoom;
    }

    public void setPreviewRoomSize(Dimension previewRoomSize) {
        this.previewRoomSize = previewRoomSize;
    }

    public void setPlacingRoom(boolean isPlacingRoom, Point mousePoint) {
        this.isPlacingRoom = isPlacingRoom;
        this.mousePoint = mousePoint;
        repaint();
    }

    private void drawLineGrid(Graphics g) {
        if (!model.isShowLineGrid()) return;
        g.setColor(Color.LIGHT_GRAY);
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
        g.setColor(Color.LIGHT_GRAY);
        int gridSize = model.getGridSize();
        for (int x = 0; x < getWidth(); x += gridSize) {
            for (int y = 0; y < getHeight(); y += gridSize) {
                g.fillOval(x - Config.DOT_SIZE / 2, y - Config.DOT_SIZE / 2, Config.DOT_SIZE, Config.DOT_SIZE);
            }
        }
    }

    private void drawRooms(Graphics g) {
        g.setColor(Color.BLUE);
        for (RoomModel room : model.getRooms()) {
            g.fillRect(room.getX(), room.getY(), room.getWidth(), room.getHeight());
        }
    }

    private void drawPreviewRoom(Graphics g) {
        if (isPlacingRoom && mousePoint != null) {
            g.setColor(Color.RED);
            int gridSize = model.getGridSize();
            int snappedX = (mousePoint.x / gridSize) * gridSize;
            int snappedY = (mousePoint.y / gridSize) * gridSize;
            g.fillRect(snappedX, snappedY, previewRoomSize.width, previewRoomSize.height);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawLineGrid(g);
        drawDotGrid(g);
        drawRooms(g);
        drawPreviewRoom(g);
    }
}

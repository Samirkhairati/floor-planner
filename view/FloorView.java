package view;

import util.Config;
import util.Tools;

import javax.swing.*;

import model.FixtureModel;
import model.FloorModel;
import model.RoomModel;
import types.Orientation;
import types.Room;
import types.RoomType;

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
        if (!model.isShowLineGrid())
            return;
        g.setColor(gridColor);
        int gridSize = Config.SNAP;
        for (int x = 0; x < getWidth(); x += gridSize) {
            g.drawLine(x, 0, x, getHeight());
        }
        for (int y = 0; y < getHeight(); y += gridSize) {
            g.drawLine(0, y, getWidth(), y);
        }
    }

    private void drawDotGrid(Graphics g) {
        if (!model.isShowDotGrid())
            return;
        g.setColor(gridColor);
        int gridSize = Config.SNAP;
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

    private void drawFurnitures(Graphics g) {
        for (Room room : model.getRooms()) {
            for (FurnitureView furniture : room.getRoomModel().getFurnitureViews()) {
                furniture.draw(g, room.getRoomModel().getPosition());
            }
        }
    }

    private void drawTemporaryFurniture(Graphics g) {
        if (model.getTemporaryFurniture() != null) {
            model.getTemporaryFurniture().getView().draw(g, null);
        }
    }

    private void drawWalls(Graphics g) {
        for (RoomModel room : model.getRoomModels()) {
            if (room.isPlaced() && !room.isPlacing()) {
                g.setColor(Color.BLACK);
                Point position = room.getPreviewPosition();
                g.drawLine(position.x, position.y, position.x + room.getSize().width, position.y);
                g.drawLine(position.x, position.y, position.x, position.y + room.getSize().height);
                g.drawLine(position.x + room.getSize().width, position.y, position.x + room.getSize().width,
                        position.y + room.getSize().height);
                g.drawLine(position.x, position.y + room.getSize().height, position.x + room.getSize().width,
                        position.y + room.getSize().height);
            }
        }
    }

    private void drawFixtures(Graphics g) {
        for (FixtureModel fixture : model.getFixtureModels()) {
            if (fixture.getOrientation() == Orientation.HORIZONTAL) {
                if (fixture.getUpRoomType() != null && fixture.getUpTilePosition() != null) {
                    g.setColor(Tools.typeToColor(fixture.getUpRoomType()));
                    g.setColor(Color.BLACK);
                    Point upTilePosition = fixture.getUpTilePosition();
                    g.drawRect(upTilePosition.x - Config.SNAP / 2, upTilePosition.y + Config.SNAP, Config.SNAP, Config.SNAP);

                }
                if (fixture.getDownRoomType() != null && fixture.getDownTilePosition() != null) {
                    g.setColor(Tools.typeToColor(RoomType.HALL));
                    // g.setColor(Color.ORANGE);
                    Point downTilePosition = fixture.getDownTilePosition();
                    g.drawLine(downTilePosition.x - Config.SNAP / 2, downTilePosition.y - Config.SNAP/2, downTilePosition.x + Config.SNAP / 2, downTilePosition.y - Config.SNAP/2);
                }

                // g.drawLine(fixture.getPreviewPosition().x, fixture.getPreviewPosition().y,
                // fixture.getPreviewPosition().x + Config.SNAP,
                // fixture.getPreviewPosition().y);
            } else {
                g.setColor(Color.RED);
                // g.drawLine(fixture.getPreviewPosition().x, fixture.getPreviewPosition().y,
                // fixture.getPreviewPosition().x, fixture.getPreviewPosition().y +
                // Config.SNAP);
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawLineGrid(g);
        drawDotGrid(g);
        drawRooms(g);
        drawFurnitures(g);
        drawTemporaryFurniture(g);
        drawWalls(g);
        drawFixtures(g);
    }
}

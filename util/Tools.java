package util;

import java.awt.*;

import controller.FloorController;
import model.FixtureModel;
import model.FloorModel;
import model.FurnitureModel;
import model.RoomModel;
import types.Orientation;
import types.RoomType;

public class Tools {
    public static Point snap(Point point) {
        int gridSize = Config.SNAP;
        int snappedX = (point.x / gridSize) * gridSize;
        int snappedY = (point.y / gridSize) * gridSize;
        return new Point(snappedX, snappedY);
    }

    public static void snapEdge(Point point, FloorController floorController,
            FixtureModel fixtureModel) {

        for (RoomModel roomModel : floorController.getModel().getRoomModels()) {
            Rectangle upperStrip = new Rectangle(roomModel.getPosition().x + Config.SNAP,
                    roomModel.getPosition().y, roomModel.getSize().width - 2 * Config.SNAP, Config.SNAP);
            Rectangle lowerStrip = new Rectangle(roomModel.getPosition().x + Config.SNAP, roomModel.getPosition().y
                    + roomModel.getSize().height - Config.SNAP, roomModel.getSize().width - 2 * Config.SNAP,
                    Config.SNAP);
            Rectangle leftStrip = new Rectangle(roomModel.getPosition().x, roomModel.getPosition().y + Config.SNAP,
                    Config.SNAP,
                    roomModel.getSize().height - 2 * Config.SNAP);
            Rectangle rightStrip = new Rectangle(roomModel.getPosition().x + roomModel.getSize().width - Config.SNAP,
                    roomModel.getPosition().y + Config.SNAP, Config.SNAP,
                    roomModel.getSize().height - 2 * Config.SNAP);

            boolean isOnUpperStrip = upperStrip.contains(point);
            boolean isOnLowerStrip = lowerStrip.contains(point);
            boolean isOnLeftStrip = leftStrip.contains(point);
            boolean isOnRightStrip = rightStrip.contains(point);

            if (isOnUpperStrip) {
                RoomModel upRoomModel = roomModelContainingPoint(new Point(point.x, point.y - Config.SNAP),
                        floorController.getModel());
                RoomModel downRoomModel = roomModel;

                Point upTilePosition = snap(new Point(point.x, point.y - Config.SNAP));
                Point downTilePosition = snap(point);
                fixtureModel
                        .setUpRoomModel(upRoomModel)
                        .setDownRoomModel(downRoomModel)
                        .setUpTilePosition(upTilePosition)
                        .setDownTilePosition(downTilePosition)
                        .setOrientation(Orientation.HORIZONTAL);
                return;
            }

            else if (isOnLowerStrip) {
                RoomModel upRoomModel = roomModel;
                RoomModel downRoomModel = roomModelContainingPoint(new Point(point.x, point.y + Config.SNAP),
                        floorController.getModel());
                Point upTilePosition = snap(point);
                Point downTilePosition = snap(new Point(point.x, point.y + Config.SNAP));
                fixtureModel
                        .setUpRoomModel(upRoomModel)
                        .setDownRoomModel(downRoomModel)
                        .setUpTilePosition(upTilePosition)
                        .setDownTilePosition(downTilePosition)
                        .setOrientation(Orientation.HORIZONTAL);
                return;
            }

            else if (isOnLeftStrip) {
                RoomModel leftRoomModel = roomModelContainingPoint(new Point(point.x - Config.SNAP, point.y),
                        floorController.getModel());
                RoomModel rightRoomModel = roomModel;

                Point leftTilePosition = snap(new Point(point.x - Config.SNAP, point.y));
                Point rightTilePosition = snap(point);
                fixtureModel
                        .setLeftRoomModel(leftRoomModel)
                        .setRightRoomModel(rightRoomModel)
                        .setLeftTilePosition(leftTilePosition)
                        .setRightTilePosition(rightTilePosition)
                        .setOrientation(Orientation.VERTICAL);
                return;
            }

            else if (isOnRightStrip) {
                RoomModel leftRoomModel = roomModel;
                RoomModel rightRoomModel = roomModelContainingPoint(new Point(point.x + Config.SNAP, point.y),
                        floorController.getModel());
                Point leftTilePosition = snap(point);
                Point rightTilePosition = snap(new Point(point.x + Config.SNAP, point.y));
                fixtureModel
                        .setLeftRoomModel(leftRoomModel)
                        .setRightRoomModel(rightRoomModel)
                        .setLeftTilePosition(leftTilePosition)
                        .setRightTilePosition(rightTilePosition)
                        .setOrientation(Orientation.VERTICAL);

                return;
            }
        }
    }

    public static RoomType roomContainingPoint(Point point, FloorModel floorModel) {
        for (RoomModel roomModel : floorModel.getRoomModels()) {
            Rectangle bounds = new Rectangle(roomModel.getPosition(), roomModel.getSize());
            if (bounds.contains(point)) {
                return roomModel.getType();
            }
        }
        return null;
    }

    public static RoomModel roomModelContainingPoint(Point point, FloorModel floorModel) {
        for (RoomModel roomModel : floorModel.getRoomModels()) {
            Rectangle bounds = new Rectangle(roomModel.getPosition(), roomModel.getSize());
            if (bounds.contains(point)) {
                return roomModel;
            }
        }
        return null;
    }

    public static boolean isMouseOver(Point point, Point position, Dimension size) {
        Rectangle bounds = new Rectangle(position, size);
        return bounds.contains(point);
    }

    public static Color changeOpacity(Color color, int opacity) {
        return new Color(color.getRed(), color.getGreen(), color.getBlue(), opacity);
    }

    public static Point getAbsolutePosition(FurnitureModel furnitureModel, RoomModel roomModel) {
        return new Point(new Point(roomModel.getPosition().x + furnitureModel.getPosition().x,
                roomModel.getPosition().y + furnitureModel.getPosition().y));
    }

    public static Point getAbsolutePreviewPosition(FurnitureModel furnitureModel, RoomModel roomModel) {
        return new Point(new Point(roomModel.getPreviewPosition().x + furnitureModel.getPosition().x,
                roomModel.getPreviewPosition().y + furnitureModel.getPosition().y));
    }

    public static RoomModel getRoomContainingFurniture(FurnitureModel furnitureModel, FloorModel floorModel) {
        for (RoomModel roomModel : floorModel.getRoomModels()) {
            if (roomModel.getFurnitureModels().contains(furnitureModel)) {
                return roomModel;
            }
        }
        return null;
    }

    public static Color typeToColor(RoomType type) {
        switch (type) {
            case HALL:
                return new Color(255, 177, 84);
            case KITCHEN:
                return new Color(177, 228, 165);
            case BEDROOM:
                return new Color(206, 165, 228);
            case BATHROOM:
                return new Color(165, 217, 228);
            default:
                return Color.WHITE;
        }
    }
}

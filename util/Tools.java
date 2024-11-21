package util;

import java.awt.*;

import model.FloorModel;
import model.FurnitureModel;
import model.RoomModel;

public class Tools {
    public static Point snap(Point point) {
        int gridSize = Config.SNAP;
        int snappedX = (point.x / gridSize) * gridSize;
        int snappedY = (point.y / gridSize) * gridSize;
        return new Point(snappedX, snappedY);
    }
    public static boolean isMouseOver(Point point, Point position, Dimension size) {
        Rectangle bounds = new Rectangle(position, size);
        return bounds.contains(point);
    }
    public static Color changeOpacity(Color color, int opacity) {
        return new Color(color.getRed(), color.getGreen(), color.getBlue(), opacity);
    }

    public static Point getAbsolutePosition(FurnitureModel furnitureModel, RoomModel roomModel) {
        return new Point(new Point(roomModel.getPosition().x + furnitureModel.getPosition().x, roomModel.getPosition().y + furnitureModel.getPosition().y));
    }

    public static Point getAbsolutePreviewPosition(FurnitureModel furnitureModel, RoomModel roomModel) {
        return new Point(new Point(roomModel.getPreviewPosition().x + furnitureModel.getPosition().x, roomModel.getPreviewPosition().y + furnitureModel.getPosition().y));
    }

    public static RoomModel getRoomContainingFurniture(FurnitureModel furnitureModel, FloorModel floorModel) {
        for (RoomModel roomModel : floorModel.getRoomModels()) {
            if (roomModel.getFurnitureModels().contains(furnitureModel)) {
                return roomModel;
            }
        }
        return null;
    }
}

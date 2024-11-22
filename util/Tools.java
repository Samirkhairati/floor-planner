package util;

import java.awt.*;

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

    public static class EdgeSnapResult {
        public Point primaryEdge; // Snapped position of the nearest edge
        public Point secondaryEdge; // The second edge (for context)

        public EdgeSnapResult(Point primaryEdge, Point secondaryEdge) {
            this.primaryEdge = primaryEdge;
            this.secondaryEdge = secondaryEdge;
        }
    }

    public static EdgeSnapResult snapEdge(Point point, Orientation orientation) {
        int gridSize = Config.SNAP;

        // Determine the nearest grid tile's origin
        int snappedX = (point.x / gridSize) * gridSize;
        int snappedY = (point.y / gridSize) * gridSize;

        if (orientation == Orientation.HORIZONTAL) {
            // Determine which half of the tile the y-coordinate is in
            if (point.y % gridSize >= gridSize / 2) {
                // Bottom half: current tile is "top", next tile is "bottom"
                Point topEdge = new Point(snappedX + gridSize / 2, snappedY + gridSize + gridSize / 2); // Current tile
                                                                                                        // center
                Point bottomEdge = new Point(snappedX + gridSize / 2, snappedY + 2 * gridSize + gridSize / 2); // Next
                                                                                                               // tile
                                                                                                               // center
                return new EdgeSnapResult(topEdge, bottomEdge);
            } else {
                // Top half: current tile is "bottom", previous tile is "top"
                Point bottomEdge = new Point(snappedX + gridSize / 2, snappedY + gridSize / 2); // Current tile center
                Point topEdge = new Point(snappedX + gridSize / 2, snappedY - gridSize + gridSize / 2); // Previous tile
                                                                                                        // center
                return new EdgeSnapResult(topEdge, bottomEdge);
            }
        } else {
            // Determine which half of the tile the x-coordinate is in
            if (point.x % gridSize >= gridSize / 2) {
                // Right half: current tile is "left", next tile is "right"
                Point leftEdge = new Point(snappedX + gridSize + gridSize / 2, snappedY + gridSize / 2); // Current tile
                                                                                                         // center
                Point rightEdge = new Point(snappedX + 2 * gridSize + gridSize / 2, snappedY + gridSize / 2); // Next
                                                                                                              // tile
                                                                                                              // center
                return new EdgeSnapResult(leftEdge, rightEdge);
            } else {
                // Left half: current tile is "right", previous tile is "left"
                Point rightEdge = new Point(snappedX + gridSize / 2, snappedY + gridSize / 2); // Current tile center
                Point leftEdge = new Point(snappedX - gridSize + gridSize / 2, snappedY + gridSize / 2); // Previous
                                                                                                         // tile center
                return new EdgeSnapResult(leftEdge, rightEdge);
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

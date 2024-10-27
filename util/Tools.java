package util;

import java.awt.Point;

public class Tools {
    public static Point snap(Point point) {
        int gridSize = Config.SNAP;
        int snappedX = (point.x / gridSize) * gridSize;
        int snappedY = (point.y / gridSize) * gridSize;
        return new Point(snappedX, snappedY);
    }
}

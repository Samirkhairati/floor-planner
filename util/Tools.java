package util;

import java.awt.*;

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
}

package util;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Effects {
    public static void drawGlowEffect(Graphics g, int x, int y, int width, int height, Color glowColor) {
        int glowSize = 15; // Thickness of the glow
        int glowWidth = width + 2 * glowSize;
        int glowHeight = height + 2 * glowSize;
        
        // Create a new buffered image with room for the glow
        BufferedImage glowImage = new BufferedImage(glowWidth, glowHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = glowImage.createGraphics();
        
        // Enable antialiasing for smoother glow edges
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Create a radial gradient for the glow effect
        RadialGradientPaint gradient = new RadialGradientPaint(
                new Point(glowWidth / 2, glowHeight / 2),
                glowSize + Math.max(width, height) / 2f,
                new float[]{0f, 0.7f, 1f},
                new Color[]{new Color(glowColor.getRed(), glowColor.getGreen(), glowColor.getBlue(), 128), 
                            new Color(glowColor.getRed(), glowColor.getGreen(), glowColor.getBlue(), 64), 
                            new Color(glowColor.getRed(), glowColor.getGreen(), glowColor.getBlue(), 0)}
        );
        g2d.setPaint(gradient);
        g2d.fillRect(0, 0, glowWidth, glowHeight);
        
        g2d.dispose();

        // Draw the glow image onto the main graphics context, offset for the glow
        g.drawImage(glowImage, x - glowSize, y - glowSize, null);

        // Draw the main rectangle on top
        g.setColor(glowColor);
        g.fillRect(x, y, width, height);
    }
}

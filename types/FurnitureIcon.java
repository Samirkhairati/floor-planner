package types;

import java.awt.Image;

import javax.swing.*;

public class FurnitureIcon {
    private FurnitureType type;
    private ImageIcon image;

    // Constructor
    public FurnitureIcon(FurnitureType type) {
        this.type = type;

        // Load the original image
        ImageIcon originalIcon = new ImageIcon("assets/" + type.name().toLowerCase() + "/" + "DEGREES_0.png");
        Image originalImage = originalIcon.getImage();

        // Get original dimensions
        int originalWidth = originalImage.getWidth(null);
        int originalHeight = originalImage.getHeight(null);

        // Target dimensions
        int targetWidth = 100;
        int targetHeight = 100;

        // Maintain aspect ratio
        if (originalWidth > originalHeight) {
            targetHeight = (int) ((double) originalHeight / originalWidth * targetWidth);
        } else {
            targetWidth = (int) ((double) originalWidth / originalHeight * targetHeight);
        }

        // Scale the image
        Image scaledImage = originalImage.getScaledInstance(targetWidth, targetHeight, Image.SCALE_SMOOTH);

        // Set the scaled image as the icon
        this.image = new ImageIcon(scaledImage);
    }

    // Getters
    public FurnitureType getType() {
        return type;
    }

    public ImageIcon getImage() {
        return image;
    }

    // Static block to populate ICONS list
    public static void populate() {

    }
}

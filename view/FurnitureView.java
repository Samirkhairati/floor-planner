package view;

import java.awt.*;
import java.awt.image.BufferedImage;

import javax.swing.JComponent;

import model.FurnitureModel;

public class FurnitureView extends JComponent {

    private FurnitureModel model;

    public FurnitureView(FurnitureModel model) {
        this.model = model;
    }

    public void draw(Graphics g) {
        super.paintComponent(g);
        BufferedImage image = model.getImage();

        if (image == null) {
            return; // No image to draw
        }

        Graphics2D g2d = (Graphics2D) g;

        // Set transparency based on state
        if (model.isPlaced()) {
            if (model.isFocused()) {
                if (model.isPlacing()) {
                    // If it's being moved and overlapping
                    if (!model.isValid()) {
                        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4f)); // reddish
                    } else {
                        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.6f));
                    }
                } else {
                    g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.8f));
                }
            } else if (model.isHovering()) {
                g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.55f));
            } else {
                g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
            }
        } else if (model.isPlacing()) {
            if (!model.isValid()) {
                g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4f));
            } else {
                g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.6f));
            }
        }

        // Draw the furniture image
        g2d.drawImage(image, model.getPreviewPosition().x, model.getPreviewPosition().y, 
                      model.getSize().width, model.getSize().height, null);

        // Reset composite for future rendering
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
    }
}

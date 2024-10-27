package view;

import java.awt.*;

import javax.swing.JComponent;

import model.RoomModel;

public class RoomView extends JComponent {

    private RoomModel model;

    public RoomView(RoomModel model) {
        this.model = model;
        setOpaque(false);

    }

    public void draw(Graphics g) {
        super.paintComponent(g);
        if (model.isPlacing()) {
            if (model.isOverlapping()) {
                g.setColor(new Color(255, 65, 108, 128));
            } else {
                g.setColor(new Color(model.getColor().getRed(), model.getColor().getGreen(), model.getColor().getBlue(), 128));
            }
            g.fillRect(model.getPreviewPosition().x, model.getPreviewPosition().y, model.getSize().width,
                    model.getSize().height);
        }
        if (model.getPosition() != null) {
            Color color = new Color(model.getColor().getRed(), model.getColor().getGreen(), model.getColor().getBlue(), 180); // 255 is the alpha value for 100% opacity
            g.setColor(color);
            g.fillRect(model.getPosition().x, model.getPosition().y, model.getSize().width, model.getSize().height);
        }
    }

}

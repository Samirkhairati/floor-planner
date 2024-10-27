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
            Color color = new Color(model.getColor().getRed(), model.getColor().getGreen(), model.getColor().getBlue(), 128); // 128 is the alpha value for 50% opacity
            g.setColor(color);
            g.fillRect(model.getPreviewPosition().x, model.getPreviewPosition().y, model.getSize().width,
                    model.getSize().height);
        } 
        if (model.getPosition() != null) {
            Color color = new Color(model.getColor().getRed(), model.getColor().getGreen(), model.getColor().getBlue(), 255); // 255 is the alpha value for 100% opacity
            g.setColor(color);
            g.fillRect(model.getPosition().x, model.getPosition().y, model.getSize().width, model.getSize().height);
        }
    }

}

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
            g.setColor(Color.RED);
            g.fillRect(model.getPreviewPosition().x, model.getPreviewPosition().y, model.getSize().width,
                    model.getSize().height);
        } 
        if (model.getPosition() != null) {
            g.setColor(Color.BLUE);
            g.fillRect(model.getPosition().x, model.getPosition().y, model.getSize().width, model.getSize().height);
        }
    }

}

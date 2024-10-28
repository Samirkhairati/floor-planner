package view;

import java.awt.*;

import javax.swing.JComponent;
import model.RoomModel;
import util.Tools;

public class RoomView extends JComponent {

    private RoomModel model;

    public RoomView(RoomModel model) {
        this.model = model;
        setOpaque(false);
    }

    public void draw(Graphics g) {
        super.paintComponent(g);
        Color color = model.getColor();

        if (model.isPlaced()) {

            // if its placed and clicked (focussed)
            if (model.isFocused()) {
                // if its placed and being moved
                if (model.isPlacing()) {
                    if (model.isOverlapping()) {
                        g.setColor(new Color(255, 65, 108, 100)); // reddish
                    } else {
                        g.setColor(Tools.changeOpacity(color, 100));
                    }
                }
                // if its just placed and focussed
                else {
                    g.setColor(Tools.changeOpacity(color, 180));
                }
            }

            // if its placed and being hovered
            else if (model.isHovering()) {
                g.setColor(Tools.changeOpacity(color, 140));
            }

            // if its just placed and nothing is being done on it
            else {
                g.setColor(Tools.changeOpacity(color, 120));
            }
        }

        else if (model.isPlacing()) {

            if (model.isOverlapping()) {
                g.setColor(new Color(255, 65, 108, 100)); // reddish
            } else {
                g.setColor(Tools.changeOpacity(color, 100));
            }
        }

        fill(g);
    }

    private void fill(Graphics g) {
        g.fillRect(model.getPreviewPosition().x, model.getPreviewPosition().y, model.getSize().width,
                model.getSize().height);
    }

}

package views.Floor;

import javax.swing.*;
import views.StateManager;
import java.awt.*;

public class Floor extends JPanel {

    public Floor() {
        setBackground(Color.WHITE); // Set background color to differentiate
        setFocusable(true); // Needed to ensure the JPanel receives keyboard focus
        requestFocusInWindow(); // Request focus so it listens to key events

        // Add key listener to the panel

        StateManager.getInstance().keyCode.addObserver(new StateManager.Observer<Integer>() {
            @Override
            public void update(Integer state) {
                System.out.println("Key pressed: " + state);
            }
        });
    }
}

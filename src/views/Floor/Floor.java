package views.Floor;

import javax.swing.*;
import views.StateManager;
import java.awt.*;
import java.awt.event.KeyEvent;

public class Floor extends JPanel {

    private int keyCode;

    public Floor() {
        setBackground(Color.WHITE); // Set background color to differentiate
        setFocusable(true); // Needed to ensure the JPanel receives keyboard focus
        requestFocusInWindow(); // Request focus so it listens to key events

        // Add key listener to the panel

        StateManager.getInstance().keyCode.addObserver(new StateManager.Observer<Integer>() {
            @Override
            public void update(Integer state) {
                keyCode = state;

                switch (keyCode) {
                    case KeyEvent.VK_1:
                        addRoom();
                        break;
                    case KeyEvent.VK_2:
                        addDoor();
                        break;
                    case KeyEvent.VK_3:
                        addWindow();
                        break;
                    default:
                        // Handle other keys if necessary
                        break;
                }
            }
        });
    }

    private void addRoom() {
        System.out.println("Room added");
    }
    private void addDoor() {
        System.out.println("Door added");
    }
    private void addWindow() {
        System.out.println("Window added");
    }
}

package views.Floor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Floor extends JPanel implements KeyListener {

    public Floor() {
        setBackground(Color.WHITE); // Set background color to differentiate
        setFocusable(true); // Needed to ensure the JPanel receives keyboard focus
        requestFocusInWindow(); // Request focus so it listens to key events

        // Add key listener to the panel
        addKeyListener(this);
    }

    // Handle key pressed events
    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        System.out.println(keyCode);

        switch (keyCode) {
            case KeyEvent.VK_1:
                System.out.println("Key '1' pressed");
                break;
            case KeyEvent.VK_2:
                System.out.println("Key '2' pressed");
                break;
            case KeyEvent.VK_3:
                System.out.println("Key '3' pressed");
                break;
            case KeyEvent.VK_4:
                System.out.println("Key '4' pressed");
                break;
            case KeyEvent.VK_5:
                System.out.println("Key '5' pressed");
                break;
        }
    }

    // Handle key released events (not used here)
    @Override
    public void keyReleased(KeyEvent e) {
        // You can implement something here if needed
    }

    // Handle key typed events (not used here)
    @Override
    public void keyTyped(KeyEvent e) {
        // You can implement something here if needed
    }
}

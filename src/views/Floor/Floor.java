package views.Floor;

import javax.swing.*;
import views.StateManager;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.util.List;

public class Floor extends JPanel {

    private int keyCode;
    private Point mousePoint; // To track mouse location
    private boolean isPlacingRoom = false; // To track if we're in the process of placing a room
    private List<Rectangle> rooms; // To store placed rooms

    private final int DEFAULT_ROOM_WIDTH = 50;
    private final int DEFAULT_ROOM_HEIGHT = 50;

    public Floor() {
        setBackground(Color.WHITE); // Set background color to differentiate
        setFocusable(true); // Needed to ensure the JPanel receives keyboard focus
        requestFocusInWindow(); // Request focus so it listens to key events

        rooms = new ArrayList<>(); // Initialize the list of rooms
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

        // Add mouse motion listener for tracking mouse movement
        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                if (isPlacingRoom) {
                    mousePoint = e.getPoint(); // Track the mouse position when moving
                    repaint(); // Repaint to show the moving rectangle
                }
            }
        });

        // Add mouse listener for placing the room
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (isPlacingRoom) {
                    // Add the room to the list when clicked and stop placing
                    rooms.add(new Rectangle(e.getX() - DEFAULT_ROOM_WIDTH / 2, e.getY() - DEFAULT_ROOM_HEIGHT / 2,
                            DEFAULT_ROOM_WIDTH, DEFAULT_ROOM_HEIGHT)); // Center the room on click
                    isPlacingRoom = false; // Stop placing the room
                    mousePoint = null; // Reset mouse point
                    repaint(); // Repaint to show the placed room
                }
            }
        });
    }

    private void addRoom() {
        System.out.println("Room added");
        isPlacingRoom = true; // Set to true when we are adding a room
        Point mouseLocation = MouseInfo.getPointerInfo().getLocation(); // Get the current mouse position
        SwingUtilities.convertPointFromScreen(mouseLocation, this); // Convert screen coordinates to panel coordinates
        mousePoint = mouseLocation; // Set mousePoint to the current mouse position
        repaint(); // Request immediate repaint to show the rectangle
    }

    private void addDoor() {
        System.out.println("Door added");
        // Implement door addition logic here
    }

    private void addWindow() {
        System.out.println("Window added");
        // Implement window addition logic here
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw all placed rooms
        g.setColor(Color.BLUE); // Set room color
        for (Rectangle room : rooms) {
            g.fillRect(room.x, room.y, room.width, room.height);
        }

        // If we're placing a room, draw the preview room following the mouse
        if (isPlacingRoom && mousePoint != null) {
            g.setColor(Color.RED); // Set color for the moving room
            g.fillRect(mousePoint.x - DEFAULT_ROOM_WIDTH / 2, mousePoint.y - DEFAULT_ROOM_HEIGHT / 2,
                    DEFAULT_ROOM_WIDTH, DEFAULT_ROOM_HEIGHT); // Draw the 10x10 rectangle
        }
    }
}

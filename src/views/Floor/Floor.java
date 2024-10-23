package views.Floor;

import javax.swing.*;

import views.Config;
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
    private final int GRID_SIZE = Config.SNAP;
    private final int DOT_SIZE = Config.DOT_SIZE;

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
                    placeRoom(e.getX(), e.getY());
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

    private void placeRoom(int x, int y) {
        // Snap the coordinates to the nearest 50x50 grid when placing the room
        int snappedX = (x / GRID_SIZE) * GRID_SIZE;
        int snappedY = (y / GRID_SIZE) * GRID_SIZE;

        // Add the room to the list with snapped coordinates and stop placing
        rooms.add(new Rectangle(
                snappedX, snappedY,
                DEFAULT_ROOM_WIDTH, DEFAULT_ROOM_HEIGHT)); // Center the room on the snapped point

        isPlacingRoom = false; // Stop placing the room
        mousePoint = null; // Reset mouse point
        repaint(); // Repaint to show the placed room
    }

    private void drawLineGrid(Graphics g, boolean enable) {

        if (!enable)
            return;

        g.setColor(Color.LIGHT_GRAY); // Set a light gray color for the grid

        // Draw vertical lines
        for (int x = 0; x < getWidth(); x += GRID_SIZE) {
            g.drawLine(x, 0, x, getHeight());
        }

        // Draw horizontal lines
        for (int y = 0; y < getHeight(); y += GRID_SIZE) {
            g.drawLine(0, y, getWidth(), y);
        }
    }

    private void drawDotGrid(Graphics g, boolean enable) {

        if (!enable)
            return;

        g.setColor(Color.LIGHT_GRAY); // Set a light gray color for the grid

        // Draw dots at each grid intersection
        for (int x = 0; x < getWidth(); x += GRID_SIZE) {
            for (int y = 0; y < getHeight(); y += GRID_SIZE) {
                g.fillOval(x - DOT_SIZE / 2, y - DOT_SIZE / 2, DOT_SIZE, DOT_SIZE); // Draw dot centered at the
                                                                                    // intersection
            }
        }
    }

    private void drawRooms(Graphics g) {
        g.setColor(Color.BLUE); // Set room color
        for (Rectangle room : rooms) {
            g.fillRect(room.x, room.y, room.width, room.height);
        }
    }

    private void drawPreviewRoom(Graphics g) {
        if (isPlacingRoom && mousePoint != null) {
            g.setColor(Color.RED); // Set color for the moving room

            // Snap the mouse point to the nearest 50x50 grid
            int snappedX = (mousePoint.x / GRID_SIZE) * GRID_SIZE;
            int snappedY = (mousePoint.y / GRID_SIZE) * GRID_SIZE;

            // Draw the snapped room (centered)
            g.fillRect(
                    snappedX, snappedY,
                    DEFAULT_ROOM_WIDTH, DEFAULT_ROOM_HEIGHT);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        drawLineGrid(g, true);
        drawDotGrid(g, true);
        drawRooms(g);
        drawPreviewRoom(g);
    }
}

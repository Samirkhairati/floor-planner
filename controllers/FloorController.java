package controllers;

import models.FloorModel;
import util.StateManager;
import views.FloorView;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FloorController {

    private final FloorModel model;
    private final FloorView view;
    private StateManager stateManager;

    public FloorController(FloorModel model, FloorView view) {
        this.model = model;
        this.view = view;
        this.stateManager = StateManager.getInstance();

        view.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (view.isPlacingRoom()) {
                    placeRoom(e.getX(), e.getY());
                }
            }
        });

        view.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                if (view.isPlacingRoom()) {
                    view.setPlacingRoom(true, e.getPoint());
                }
            }
        });

        view.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                handleKeyPress(e.getKeyCode());
            }
        });

        stateManager.showLineGrid.addObserver(new StateManager.Observer<Boolean>() {
            @Override
            public void update(Boolean state) {
                model.setShowLineGrid(state);
                view.repaint();
            }
        });
        stateManager.showDotGrid.addObserver(new StateManager.Observer<Boolean>() {
            @Override
            public void update(Boolean state) {
                model.setShowDotGrid(state);
                view.repaint();
            }
        });
    }

    private void handleKeyPress(int keyCode) {
        switch (keyCode) {
            case KeyEvent.VK_1:
                startPlacingRoom();
                break;
            case KeyEvent.VK_2:
                // addDoor logic
                break;
            case KeyEvent.VK_3:
                // addWindow logic
                break;
        }
    }

    private void startPlacingRoom() {
        view.setPlacingRoom(true, MouseInfo.getPointerInfo().getLocation());
    }

    private void placeRoom(int x, int y) {
        int gridSize = model.getGridSize();
        int snappedX = (x / gridSize) * gridSize;
        int snappedY = (y / gridSize) * gridSize;

        model.addRoom(new Rectangle(snappedX, snappedY, model.getDefaultRoomWidth(), model.getDefaultRoomHeight()));
        view.setPlacingRoom(false, null); // stop placing room
    }
}

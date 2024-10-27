package controller;

import ui.AddRoomOptions;
import util.StateManager;
import util.Tools;
import view.FloorView;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import model.FloorModel;
import types.RoomType;

public class FloorController {

    private final FloorModel model;
    private final FloorView view;
    private StateManager stateManager;
    private RoomController focussedRoom;


    public FloorController(FloorModel model, FloorView view) {
        this.model = model;
        this.view = view;
        this.stateManager = StateManager.getInstance();


        view.addMouseMotionListener(new MouseAdapter() {
            // For showing preview room
            @Override
            public void mouseMoved(MouseEvent e) {
                if (focussedRoom != null && focussedRoom.roomModel.isPlacing()) {
                    focussedRoom.roomModel.setPreviewPosition(Tools.snap(e.getPoint()));
                    view.repaint();
                }
            }
        });
        stateManager.keyCode.addObserver(new StateManager.Observer<Integer>() {
            @Override
            public void update(Integer keyCode) {
                handleKeyPress(keyCode);
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

    public void startPlacingRoom() {
        AddRoomOptions options = new AddRoomOptions(model);
        Dimension initialRoomSize = new Dimension(options.roomWidth, options.roomHeight);
        RoomType selectedRoomType = options.selectedRoomType;
        focussedRoom = new RoomController(view, model, initialRoomSize, selectedRoomType);
        focussedRoom.startPlacingRoom();
    }
}

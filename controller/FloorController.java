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
import model.RoomModel;
import types.Room;
import types.RoomType;

public class FloorController {

    private final FloorModel model;
    private final FloorView view;
    private StateManager stateManager;
    private boolean busy;

    public FloorController(FloorModel model, FloorView view) {
        this.model = model;
        this.view = view;
        this.stateManager = StateManager.getInstance();

        view.addMouseMotionListener(new MouseAdapter() {
            // For showing preview room
            @Override
            public void mouseMoved(MouseEvent e) {
                checkHover(e);
                movePreviewRoom(e);
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

    public boolean getBusy() {
        return busy;
    }

    public void setBusy(boolean busy) {
        this.busy = busy;
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
            case KeyEvent.VK_ESCAPE:
                drop();
                break;
        }
    }

    public void startPlacingRoom() {
        System.out.println(busy);
        if (busy) return;
        AddRoomOptions options = new AddRoomOptions(model);
        Dimension initialRoomSize = new Dimension(options.roomWidth, options.roomHeight);
        RoomType selectedRoomType = options.selectedRoomType;
        RoomController newRoomController = new RoomController(view, model, this, initialRoomSize, selectedRoomType);
        newRoomController.startPlacingRoom();
    }

    public void movePreviewRoom(MouseEvent e) {
        for (Room room : model.getRooms()) {
            if (room.getRoomModel().isPlacing()) {
                room.getRoomModel().setPreviewPosition(Tools.snap(e.getPoint()));
                room.getRoomController().checkOverlap();
                view.repaint();
            }
        }
    }

    public void startPlacingFurniture() {
        // to prevent multiple rooms being placed at the same time
        for (RoomModel room : model.getRoomModels()) {
            if (room.isPlacing()) {
                return;
            }
        }

        AddRoomOptions options = new AddRoomOptions(model);
        Dimension initialRoomSize = new Dimension(options.roomWidth, options.roomHeight);
        RoomType selectedRoomType = options.selectedRoomType;
        RoomController newRoomController = new RoomController(view, model, this, initialRoomSize, selectedRoomType);
        newRoomController.startPlacingRoom();
    }

    private void checkHover(MouseEvent e) {
        // dont do hover check if some room is being placed
        for (RoomModel room : model.getRoomModels()) {
            if (room.isPlacing()) return;
        }
        // otherwise do it
        for (RoomModel room : model.getRoomModels()) {
            if (room.isPlaced()) {
                boolean isMouseOver = Tools.isMouseOver(e.getPoint(), room.getPosition(), room.getSize());
                room.setHovering(isMouseOver);
                view.repaint();
            }
        }
    }

    public void drop() {
        for (Room room : model.getRooms()) {
            if (room.getRoomModel().isPlacing()) {
                if (room.getRoomModel().isPlaced()) {
                    room.getRoomModel().setPlacing(false);
                } else {
                    model.removeRoomByModel(room.getRoomModel());
                    view.remove(room.getRoomView());
                }
                view.repaint();
            }
        }
    }
}

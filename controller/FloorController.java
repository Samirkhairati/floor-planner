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
import model.FurnitureModel;
import model.RoomModel;
import types.Furniture;
import types.FurnitureType;
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
                moveTemporaryFurniture(e);
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
        if (busy)
            return;
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
                
                for (FurnitureModel furniture : room.getRoomModel().getFurnitureModels()) {
                    furniture.setPreviewPosition(Tools.getAbsolutePreviewPosition(furniture, room.getRoomModel()));
                }

                room.getRoomController().checkOverlap();
                view.repaint();
            }
        }
    }

    public void startPlacingFurniture(FurnitureType selectedFurnitureType) {

        if (busy)
            return;

        FurnitureController newFurnitureController = new FurnitureController(view, model, this, selectedFurnitureType);
        newFurnitureController.startPlacingFurniture();
    }

    public void moveTemporaryFurniture(MouseEvent e) {

        Furniture temporaryFurniture = model.getTemporaryFurniture();
        if (temporaryFurniture == null)
            return;
        temporaryFurniture.getModel().setPreviewPosition(Tools.snap(e.getPoint()));
        temporaryFurniture.getController().checkValidity();
        view.repaint();
    }

    private void checkHover(MouseEvent e) {
        if (busy)
            return;

        for (RoomModel room : model.getRoomModels()) {
            if (room.isPlaced()) {

                // Check if mouse is hovering over any furniture on priority
                for (FurnitureModel furniture : room.getFurnitureModels()) {
                    if (Tools.isMouseOver(e.getPoint(), Tools.getAbsolutePosition(furniture, room), furniture.getSize())) {

                        // Un-hover all other rooms: for the case when you are hovering
                        // over a room and move the mouse to a furniture
                        // and both of them become in the hovered state instead of un-hovering the room
                        for (RoomModel r : model.getRoomModels()) {
                            r.setHovering(false);
                        }

                        furniture.setHovering(true);
                        view.repaint();
                        return;
                    } else {
                        furniture.setHovering(false);
                    }
                }

                // then check if room is being hovered
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

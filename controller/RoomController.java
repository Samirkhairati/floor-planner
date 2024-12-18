package controller;

import java.awt.*;
import java.awt.event.*;
import java.io.Serializable;

import javax.swing.SwingUtilities;

import model.FixtureModel;
import model.FloorModel;
import model.FurnitureModel;
import model.RoomModel;
import types.Room;
import types.RoomType;
import util.Tools;
import view.FloorView;
import view.RoomView;

public class RoomController implements Serializable {

    private final RoomModel roomModel;
    private final RoomView roomView;
    private final FloorView floorView;
    private final FloorModel floorModel;
    private final FloorController floorController;

    public RoomController(FloorView floorView, FloorModel floorModel, FloorController floorController,
            Dimension initialRoomSize,
            RoomType selectedRoomType) {
        this.roomModel = new RoomModel();
        this.roomView = new RoomView(roomModel);
        this.floorView = floorView;
        this.floorModel = floorModel;
        this.floorController = floorController;
        roomModel.setSize(initialRoomSize);
        roomModel.setType(selectedRoomType);
        listen();
    }

    public RoomController(FloorView floorView, FloorModel floorModel, FloorController floorController,
            RoomModel roomModel, RoomView roomView) {
        this.roomModel = roomModel;
        this.roomView = roomView;
        this.floorView = floorView;
        this.floorModel = floorModel;
        this.floorController = floorController;
        floorView.add(roomView);
        listen();
    }

    private void listen() {
        floorView.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {

                if (SwingUtilities.isLeftMouseButton(e)) {
                    // Click to place room when a new room is created
                    if (roomModel.isPlacing()) {
                        placeRoom();
                    }
                    // Click on room to focus or unfocus
                    else if (roomModel.isHovering()) {
                        focus();
                    }
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    // to drop the moving room
                    if (roomModel.isPlaced() && roomModel.isFocused() && roomModel.isPlacing()) {
                        placeRoom();
                    }
                }
            }
        });

        floorView.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    // For the case when mouse is clicked while moving while placing a new room
                    if (roomModel.isPlacing() && !roomModel.isFocused()) {
                        placeRoom();
                    }
                    // to move room
                    if (roomModel.isPlaced() && roomModel.isHovering()) {
                        startMovingRoom(e);
                    }
                }
            }
        });

    }

    public void startPlacingRoom() {
        Point locationOnScreen = MouseInfo.getPointerInfo().getLocation();
        SwingUtilities.convertPointFromScreen(locationOnScreen, floorView);
        roomModel.setPlaced(false);
        roomModel.setPlacing(true);
        roomModel.setFocused(true);
        roomModel.setPreviewPosition(Tools.snap(locationOnScreen));
        floorView.add(roomView);
        floorModel.addRoom(new Room(roomModel, roomView, this));
        floorController.setBusy(true);
        floorView.repaint();
    }

    private void placeRoom() {

        if (roomModel.isOverlapping()) {
            // snap back to original position
            if (roomModel.isPlaced()) {
                roomModel.setPlacing(false);
                roomModel.setFocused(false);
                roomModel.setPreviewPosition(roomModel.getPosition());

                for (FurnitureModel furniture : roomModel.getFurnitureModels()) {
                    furniture.setPreviewPosition(Tools.getAbsolutePreviewPosition(furniture, roomModel));
                }
                for (FixtureModel fixture : floorModel.getFixtureModels()) {
                    fixture.setPlacing(false);
                }

                floorView.repaint();
                floorController.setBusy(false);
                return;
            }
            // delete room if no original position (new room)
            else {
                roomModel.setPlacing(false);
                roomModel.setFocused(false);
                floorModel.removeRoomByModel(roomModel);
                floorView.remove(roomView);
                floorView.repaint();
                floorController.setBusy(false);
                return;
            }
        }
        floorController.setBusy(false);
        roomModel.setPlaced(true);
        roomModel.setFocused(false);
        roomModel.setPlacing(false);
        roomModel.setPosition(Tools.snap(roomModel.getPreviewPosition()));

        for (FixtureModel fixture : floorModel.getFixtureModels()) {
            if (fixture.isPlacing()) {
                FixtureController fixtureController = new FixtureController(floorView, floorModel, floorController,
                        fixture.getType());
                fixtureController.placeFixture(fixture.getPreviewPosition());
                floorModel.removeFixtureByModel(fixture);
            }
        }

        floorView.repaint();
    }

    private void startMovingRoom(MouseEvent e) {
        floorController.setBusy(true);
        roomModel.setFocused(true);
        roomModel.setPlacing(true);
        checkOverlap();
        floorController.movePreviewRoom(e);
    }

    public void checkOverlap() {
        Rectangle previewRoom = new Rectangle(roomModel.getPreviewPosition(), roomModel.getSize());
        for (RoomModel room : floorModel.getRoomModels()) {
            if (room != roomModel) {
                Rectangle existingRoom = new Rectangle(room.getPosition(), room.getSize());
                if (previewRoom.intersects(existingRoom)) {
                    roomModel.setOverlapping(true);
                    return;
                }
            }
        }
        roomModel.setOverlapping(false);
    }

    private void focus() {

        if (floorController.getBusy()) {
            return;
        }

        // if room is focused, unfocus it
        if (roomModel.isFocused()) {
            roomModel.setFocused(false);
        } else {
            // Unfocus any other focused room
            for (RoomModel room : floorModel.getRoomModels()) {
                if (room.isFocused()) {
                    room.setFocused(false);
                }
            }
            // Focus this room
            roomModel.setFocused(true);
        }
        floorView.repaint();
    }
}

package controller;

import java.awt.*;
import java.awt.event.*;

import javax.swing.SwingUtilities;

import model.FloorModel;
import model.RoomModel;
import types.Room;
import types.RoomType;
import util.Tools;
import view.FloorView;
import view.RoomView;

public class RoomController {

    private final RoomModel roomModel;
    private final RoomView roomView;
    private final FloorView floorView;
    private final FloorModel floorModel;

    public RoomController(FloorView floorView, FloorModel floorModel, Dimension initialRoomSize, RoomType selectedRoomType) {
        this.roomModel = new RoomModel();
        this.roomView = new RoomView(roomModel);
        this.floorView = floorView;
        this.floorModel = floorModel;
        roomModel.setSize(initialRoomSize);
        roomModel.setType(selectedRoomType);

        floorView.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                // Click to place room when a new room is created
                if (roomModel.isPlacing()) {
                    placeRoom();
                }
                // Click on room to focus
                // TODO: glow on focus
                else if (roomModel.isHovering()) {
                    roomModel.setFocused(true);
                    floorView.repaint();
                }
            }
        });

        floorView.addMouseMotionListener(new MouseAdapter() {
            // For the case when mouse is clicked while moving
            @Override
            public void mouseDragged(MouseEvent e) {
                if (roomModel.isPlacing()) {
                    placeRoom();
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
        floorView.repaint();
    }

    private void placeRoom() {
        if (roomModel.isOverlapping()) return;
        roomModel.setPlaced(true);
        roomModel.setFocused(false);
        roomModel.setPlacing(false);
        roomModel.setPosition(Tools.snap(roomModel.getPreviewPosition()));
        floorView.repaint();
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
}

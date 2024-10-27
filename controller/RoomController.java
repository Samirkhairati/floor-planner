package controller;

import java.awt.*;
import java.awt.event.*;

import javax.swing.SwingUtilities;

import model.FloorModel;
import model.RoomModel;
import types.RoomType;
import util.Tools;
import view.FloorView;
import view.RoomView;

public class RoomController {

    public final RoomModel roomModel;
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
            // Click to place room when a new room is created
            @Override
            public void mousePressed(MouseEvent e) {
                if (roomModel.isPlacing()) {
                    placeRoom();
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
        roomModel.setPlacing(true);
        roomModel.setPreviewPosition(Tools.snap(locationOnScreen));
        floorView.add(roomView);
        floorModel.addRoomView(roomView);
        floorView.repaint();
    }

    private void placeRoom() {
        if (roomModel.isOverlapping()) return;
        roomModel.setPlacing(false);
        roomModel.setPosition(Tools.snap(roomModel.getPreviewPosition()));
        floorModel.addRoomModel(roomModel);
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

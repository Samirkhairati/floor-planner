package controller;

import model.FloorModel;
import model.FurnitureModel;
import model.RoomModel;
import types.Furniture;
import types.FurnitureType;
import util.FurnitureFactory;
import util.Tools;
import view.FloorView;
import view.FurnitureView;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.SwingUtilities;

public class FurnitureController {

    private final FurnitureModel furnitureModel;
    private final FurnitureView furnitureView;
    private final FloorView floorView;
    private final FloorModel floorModel;
    private final FloorController floorController;

    public FurnitureController(FloorView floorView, FloorModel floorModel, FloorController floorController,
            FurnitureType selectedFurnitureType) {
        this.furnitureModel = FurnitureFactory.createModel(selectedFurnitureType);
        this.furnitureView = new FurnitureView(furnitureModel);
        this.floorView = floorView;
        this.floorModel = floorModel;
        this.floorController = floorController;
        furnitureModel.setType(selectedFurnitureType);

        floorView.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                // Click to place room when a new room is created
                if (furnitureModel.isPlacing()) {
                    placeFurniture();
                }
                // Click on room to focus or unfocus
                else if (furnitureModel.isHovering()) {
                    focus();
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                // to drop the moving room
                if (furnitureModel.isPlaced() && furnitureModel.isFocused() && furnitureModel.isPlacing()) {
                    placeFurniture();
                }
            }
        });

        floorView.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                // For the case when mouse is clicked while moving while placing a new room
                if (furnitureModel.isPlacing() && !furnitureModel.isFocused()) {
                    placeFurniture();
                }
                // to move room
                if (furnitureModel.isPlaced() && furnitureModel.isHovering()) {
                    startMovingFurniture(e);
                }
            }
        });

    }

    public void startPlacingFurniture() {
        Point locationOnScreen = MouseInfo.getPointerInfo().getLocation();
        SwingUtilities.convertPointFromScreen(locationOnScreen, floorView);
        furnitureModel
                .setPlaced(false)
                .setPlacing(true)
                .setFocused(true)
                .setPreviewPosition(Tools.snap(locationOnScreen))
                .setValidity(false);

        floorView.add(furnitureView);
        floorModel.setTemporaryFurniture(new Furniture(furnitureModel, furnitureView, this));
        // floorController.setBusy(true);
        floorView.repaint();
    }

    private void placeFurniture() {

        if (!furnitureModel.isValid()) {
            // snap back to original position
            if (furnitureModel.isPlaced()) {
                furnitureModel
                        .setPlacing(false)
                        .setFocused(false)
                        .setPreviewPosition(furnitureModel.getPosition());
                floorController.setBusy(false);
                floorView.repaint();
                return;
            }
            // delete room if no original position (new room)
            else {
                furnitureModel.setPlacing(false);
                furnitureModel.setFocused(false);
                floorView.remove(floorModel.getTemporaryFurniture().getView());
                floorModel.removeTemporaryFurniture();
                floorController.setBusy(false);
                floorView.repaint();
                return;
            }
        }
        floorController.setBusy(false);
        furnitureModel
                .setPlaced(true)
                .setFocused(false)
                .setPlacing(false)
                .setPosition(Tools.snap(furnitureModel.getPreviewPosition()));

        floorView.repaint();
    }

    private void startMovingFurniture(MouseEvent e) {
        floorController.setBusy(true);
        furnitureModel.setFocused(true).setPlacing(true);
        checkValidity();
        floorController.movePreviewRoom(e);
    }

    public void checkValidity() {
        Rectangle temporaryFurniture = new Rectangle(furnitureModel.getPreviewPosition(), furnitureModel.getSize());
        for (RoomModel room : floorModel.getRoomModels()) {
            Rectangle existingRoom = new Rectangle(room.getPosition(), room.getSize());
            if (existingRoom.contains(temporaryFurniture)) {
                furnitureModel.setValidity(true);
                return;
            }
        }
        furnitureModel.setValidity(false);
    }

    private void focus() {
        // if room is focused, unfocus it
        if (furnitureModel.isFocused()) {
            furnitureModel.setFocused(false);
        } else {
            // Unfocus any other focused room
            for (RoomModel room : floorModel.getRoomModels()) {
                if (room.isFocused()) {
                    room.setFocused(false);
                }
            }
            // Focus this room
            furnitureModel.setFocused(true);
        }
        floorView.repaint();
    }

}

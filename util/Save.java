package util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import controller.FloorController;
import controller.FurnitureController;
import controller.RoomController;
import model.FloorModel;
import model.FurnitureModel;
import model.RoomModel;
import types.Furniture;
import types.Room;
import ui.Screen;
import view.FloorView;
import view.FurnitureView;
import view.RoomView;

public class Save {

    public static void saveFile(FloorController floorController, Screen screen) {
        List<RoomRecord> roomRecords = new ArrayList<>();

        floorController.getModel().getRoomModels()
                .forEach(roomModel -> roomRecords.add(new RoomRecord(roomModel, roomModel.getFurnitureModels())));

        JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showSaveDialog(floorController.getView()) == JFileChooser.APPROVE_OPTION) {
            try {
                try (ObjectOutputStream oos = new ObjectOutputStream(
                        new FileOutputStream(fileChooser.getSelectedFile()))) {
                    oos.writeObject(roomRecords); // Ensure FloorModel implements Serializable
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(screen, "Failed to save file!" + ex, "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    @SuppressWarnings("unchecked")
    public static void loadFile(FloorController floorController, Screen screen) {

        JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showOpenDialog(floorController.getView()) == JFileChooser.APPROVE_OPTION) {
            try {
                List<RoomRecord> roomRecords = null;
                try (ObjectInputStream ois = new ObjectInputStream(
                        new FileInputStream(fileChooser.getSelectedFile()))) {
                    roomRecords = (List<RoomRecord>) ois.readObject();
                }

                initialize(roomRecords, floorController, screen);

                // screen.resetWithController(newController);

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(screen, "Failed to load file!", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private static void initialize(List<RoomRecord> roomRecords, FloorController floorController, Screen screen) {

        FloorModel model = new FloorModel();
        FloorView view = new FloorView(model);
        FloorController controller = new FloorController(model, view);

        for (RoomRecord roomRecord : roomRecords) {
            // Create a new room model
            RoomModel oldRoomModel = roomRecord.roomModel;
            RoomModel newRoomModel = new RoomModel();
            newRoomModel
                    .setPosition(oldRoomModel.getPosition())
                    .setPreviewPosition(oldRoomModel.getPreviewPosition())
                    .setSize(oldRoomModel.getSize())
                    .setType(oldRoomModel.getType())
                    .setPlaced(oldRoomModel.isPlaced())
                    .setPlacing(oldRoomModel.isPlacing())
                    .setOverlapping(oldRoomModel.isOverlapping())
                    .setHovering(oldRoomModel.isHovering())
                    .setFocused(oldRoomModel.isFocused());
            // Add furniture models to the new room model
            for (FurnitureModel furnitureModel : roomRecord.furnitureModels) {
                FurnitureModel oldFurnitureModel = furnitureModel;
                FurnitureModel newFurnitureModel = new FurnitureModel();
                newFurnitureModel
                        .setPosition(oldFurnitureModel.getPosition())
                        .setPreviewPosition(oldFurnitureModel.getPreviewPosition())
                        .setSize(oldFurnitureModel.getSize())
                        .setType(oldFurnitureModel.getType())
                        .setPlaced(oldFurnitureModel.isPlaced())
                        .setRotation(oldFurnitureModel.getRotation())
                        .setPlacing(oldFurnitureModel.isPlacing())
                        .setValidity(oldFurnitureModel.isValid())
                        .setHovering(oldFurnitureModel.isHovering())
                        .setFocused(oldFurnitureModel.isFocused());
                FurnitureView newFurnitureView = new FurnitureView(newFurnitureModel);
                FurnitureController newFurnitureController = new FurnitureController(
                        view,
                        model,
                        controller,
                        newFurnitureModel,
                        newFurnitureView);
                Furniture newFurniture = new Furniture(newFurnitureModel, newFurnitureView, newFurnitureController);
                newRoomModel.addFurniture(newFurniture);
            }

            RoomView newRoomView = new RoomView(newRoomModel);
            RoomController newRoomController = new RoomController(
                    view,
                    model,
                    controller,
                    newRoomModel,
                    newRoomView);
            Room newRoom = new Room(newRoomModel, newRoomView, newRoomController);
            model.addRoom(newRoom);
        }


        screen.resetWithController(controller);
    }

    private static class RoomRecord implements Serializable {

        public RoomModel roomModel;
        public List<FurnitureModel> furnitureModels;

        public RoomRecord(RoomModel roomModel, List<FurnitureModel> furnitureModels) {
            this.furnitureModels = furnitureModels;
            this.roomModel = roomModel;
        }
    }

}

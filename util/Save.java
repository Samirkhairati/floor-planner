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

import controller.FixtureController;
import controller.FloorController;
import controller.FurnitureController;
import controller.RoomController;
import model.FixtureModel;
import model.FloorModel;
import model.FurnitureModel;
import model.RoomModel;
import types.Fixture;
import types.Furniture;
import types.Room;
import ui.Screen;
import view.FixtureView;
import view.FloorView;
import view.FurnitureView;
import view.RoomView;

public class Save {

    public static void saveFile(FloorController floorController, Screen screen) {
        List<RoomRecord> roomRecords = new ArrayList<>();

        floorController.getModel().getRoomModels()
                .forEach(roomModel -> roomRecords.add(new RoomRecord(roomModel, roomModel.getFurnitureModels())));
        Memory memory = new Memory(roomRecords, floorController.getModel().getFixtureModels());

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Floor files", "floor"));
        if (fileChooser.showSaveDialog(floorController.getView()) == JFileChooser.APPROVE_OPTION) {
            try {
                String filePath = fileChooser.getSelectedFile().getAbsolutePath();
                if (!filePath.endsWith(".floor")) {
                    filePath += ".floor";
                }
                try (ObjectOutputStream oos = new ObjectOutputStream(
                        new FileOutputStream(filePath))) {
                    oos.writeObject(memory); // Ensure FloorModel implements Serializable
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(screen, "Failed to save file!" + ex, "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void loadFile(FloorController floorController, Screen screen) {

        JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showOpenDialog(floorController.getView()) == JFileChooser.APPROVE_OPTION) {
            try {
                Memory memory;
                try (ObjectInputStream ois = new ObjectInputStream(
                        new FileInputStream(fileChooser.getSelectedFile()))) {
                    memory = (Memory) ois.readObject();
                }

                initialize(memory, floorController, screen);

                // screen.resetWithController(newController);

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(screen, "Failed to load file!", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private static void initialize(Memory memory, FloorController floorController, Screen screen) {

        FloorModel model = new FloorModel();
        FloorView view = new FloorView(model);
        FloorController controller = new FloorController(model, view);

        for (RoomRecord roomRecord : memory.roomRecords) {
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
                        .setPreviewSize(oldFurnitureModel.getPreviewSize())
                        .setType(oldFurnitureModel.getType())
                        .setPlaced(oldFurnitureModel.isPlaced())
                        .setRotation(oldFurnitureModel.getRotation())
                        .setPreviewRotation(oldFurnitureModel.getPreviewRotation())
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

        for (FixtureModel fixtureModel : memory.fixtures) {
            FixtureModel newFixtureModel = new FixtureModel();
            newFixtureModel
                    .setUpTilePosition(fixtureModel.getUpTilePosition())
                    .setDownTilePosition(fixtureModel.getDownTilePosition())
                    .setLeftTilePosition(fixtureModel.getLeftTilePosition())
                    .setRightTilePosition(fixtureModel.getRightTilePosition())
                    .setUpRoomModel(fixtureModel.getUpRoomModel())
                    .setDownRoomModel(fixtureModel.getDownRoomModel())
                    .setLeftRoomModel(fixtureModel.getLeftRoomModel())
                    .setRightRoomModel(fixtureModel.getRightRoomModel())
                    .setPreviewPosition(fixtureModel.getPreviewPosition())
                    .setOrientation(fixtureModel.getOrientation())
                    .setPlacing(fixtureModel.isPlacing())
                    .setPreviewSide(fixtureModel.getPreviewSide())
                    .setType(fixtureModel.getType());

            FixtureView newFixtureView = new FixtureView();
            FixtureController newFixtureController = new FixtureController(
                    view,
                    model,
                    controller,
                    newFixtureModel,
                    newFixtureView);
            controller.getModel().addFixture(new Fixture(newFixtureModel, newFixtureView, newFixtureController));
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

    private static class Memory implements Serializable {

        List<RoomRecord> roomRecords;
        List<FixtureModel> fixtures;

        public Memory(List<RoomRecord> roomRecords, List<FixtureModel> fixtures) {
            this.roomRecords = roomRecords;
            this.fixtures = fixtures;
        }
    }

}

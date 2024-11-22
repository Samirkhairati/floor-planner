package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import controller.RoomController;
import types.Fixture;
import types.Furniture;
import types.Room;
import view.RoomView;

public class FloorModel implements Serializable {
    private List<Room> rooms;
    private List<Fixture> fixtures;
    private boolean showLineGrid;
    private boolean showDotGrid;
    private boolean doingAction = false;
    private Furniture temporaryFurniture;

    public FloorModel() {
        this.showLineGrid = true;
        this.showDotGrid = false;
        this.rooms = new ArrayList<>();
        this.fixtures = new ArrayList<>();
    }

    public void addRoom(Room room) {
        rooms.add(room);
    }

    public void addFixture(Fixture fixture) {
        fixtures.add(fixture);
    }

    public void removeRoomModel(Room room) {
        rooms.remove(room);
    }

    public void removeTemporaryFurniture() {
        temporaryFurniture = null;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void removeRoomByModel(RoomModel roomModel) {
        rooms.removeIf(room -> room.getRoomModel().equals(roomModel));
    }

    public List<RoomModel> getRoomModels() {
        List<RoomModel> roomModels = new ArrayList<>();
        for (Room room : rooms) {
            roomModels.add(room.getRoomModel());
        }
        return roomModels;
    }

    public List<RoomView> getRoomViews() {
        List<RoomView> roomViews = new ArrayList<>();
        for (Room room : rooms) {
            roomViews.add(room.getRoomView());
        }
        return roomViews;
    }

    public List<RoomController> getRoomControllers() {
        List<RoomController> roomControllers = new ArrayList<>();
        for (Room room : rooms) {
            roomControllers.add(room.getRoomController());
        }
        return roomControllers;
    }

    public List<FixtureModel> getFixtureModels() {
        List<FixtureModel> fixtureModels = new ArrayList<>();
        for (Fixture fixture : fixtures) {
            fixtureModels.add(fixture.getModel());
        }
        return fixtureModels;
    }

    public boolean isDoingAction() {
        return doingAction;
    }

    public void setDoingAction(boolean doingAction) {
        this.doingAction = doingAction;
    }

    public boolean isShowLineGrid() {
        return showLineGrid;
    }

    public void setShowLineGrid(boolean showLineGrid) {
        this.showLineGrid = showLineGrid;
    }

    public boolean isShowDotGrid() {
        return showDotGrid;
    }

    public void setShowDotGrid(boolean showDotGrid) {
        this.showDotGrid = showDotGrid;
    }

    public Furniture getTemporaryFurniture() {
        return temporaryFurniture;
    }

    public void setTemporaryFurniture(Furniture temporaryFurniture) {
        this.temporaryFurniture = temporaryFurniture;
    }
}

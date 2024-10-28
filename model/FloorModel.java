package model;

import java.util.ArrayList;
import java.util.List;

import controller.RoomController;
import types.Room;
import view.RoomView;

public class FloorModel {
    private List<Room> rooms;
    private boolean showLineGrid;
    private boolean showDotGrid;
    private final int GRID_SIZE;
    private boolean doingAction = false;

    public FloorModel(int gridSize) {
        this.GRID_SIZE = gridSize;
        this.showLineGrid = true;
        this.showDotGrid = false;
        this.rooms = new ArrayList<>();
    }

    public void addRoom(Room room) {
        rooms.add(room);
    }

    public void removeRoomModel(Room room) {
        rooms.remove(room);
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

    public int getGridSize() {
        return GRID_SIZE;
    }
}

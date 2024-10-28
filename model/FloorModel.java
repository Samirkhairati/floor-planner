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
    private final int DEFAULT_ROOM_WIDTH;
    private final int DEFAULT_ROOM_HEIGHT;

    public FloorModel(int gridSize, int roomWidth, int roomHeight) {
        this.GRID_SIZE = gridSize;
        this.DEFAULT_ROOM_WIDTH = roomWidth;
        this.DEFAULT_ROOM_HEIGHT = roomHeight;
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

    public int getDefaultRoomWidth() {
        return DEFAULT_ROOM_WIDTH;
    }

    public int getDefaultRoomHeight() {
        return DEFAULT_ROOM_HEIGHT;
    }
}

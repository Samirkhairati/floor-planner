package model;

import java.util.ArrayList;
import java.util.List;

import view.RoomView;

public class FloorModel {
    private List<RoomModel> roomModels;
    private List<RoomView> roomViews;
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
        this.roomModels = new ArrayList<>();
        this.roomViews = new ArrayList<>();
    }

    public void addRoomModel(RoomModel room) {
        roomModels.add(room);
    }

    public void addRoomView(RoomView room) {
        roomViews.add(room);
    }

    public void removeRoomModel(RoomModel room) {
        roomModels.remove(room);
    }

    public void removeRoomView(RoomView room) {
        roomViews.remove(room);
    }

    public List<RoomModel> getRoomModels() {
        return roomModels;
    }

    public List<RoomView> getRoomViews() {
        return roomViews;
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

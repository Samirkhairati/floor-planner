package models;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class FloorModel {
    private List<Rectangle> rooms;
    private boolean showLineGrid;
    private boolean showDotGrid;
    private final int GRID_SIZE;
    private final int DEFAULT_ROOM_WIDTH;
    private final int DEFAULT_ROOM_HEIGHT;

    public FloorModel(int gridSize, int roomWidth, int roomHeight) {
        this.GRID_SIZE = gridSize;
        this.DEFAULT_ROOM_WIDTH = roomWidth;
        this.DEFAULT_ROOM_HEIGHT = roomHeight;
        this.rooms = new ArrayList<>();
    }

    public void addRoom(Rectangle room) {
        rooms.add(room);
    }

    public List<Rectangle> getRooms() {
        return rooms;
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

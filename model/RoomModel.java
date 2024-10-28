package model;

import java.awt.*;
import java.util.List;

import types.Furniture;
import types.RoomType;

import java.util.ArrayList;

public class RoomModel {
    
    private List<Furniture> furnitures = new ArrayList<>();
    private Point position;
    private Point previewPosition;
    private Dimension size;
    private Dimension previewSize;
    private RoomType type;
    private Color color;
    private boolean isPlaced;
    private boolean isPlacing;
    private boolean isOverlapping;
    private boolean isHovering;
    private boolean isFocused;

    public RoomModel() {
        this.position = null; // No default position exists (don't snap back to anywhere)
        this.isPlaced = false;
    }

    public void addFurniture(Furniture furniture) {
        furnitures.add(furniture);
    }

    public List<Furniture> getFurnitures() {
        return furnitures;
    }

    public Point getPosition() {
        return position;
    }

    public Dimension getSize() {
        return size;
    }

    public Point getPreviewPosition() {
        return previewPosition;
    }

    public Dimension getPreviewSize() {
        return previewSize;
    }

    public RoomType getType() {
        return type;
    }

    public Color getColor() {
        return color;
    }

    public boolean isPlaced() {
        return isPlaced;
    }

    public boolean isPlacing() {
        return isPlacing;
    }

    public boolean isOverlapping() {
        return isOverlapping;
    }

    public boolean isHovering() {
        return isHovering;
    }

    public boolean isFocused() {
        return isFocused;
    }

    public RoomModel setPosition(Point position) {
        this.position = position;
        return this;
    }

    public RoomModel setPreviewPosition(Point previewPosition) {
        this.previewPosition = previewPosition;
        return this;
    }

    public RoomModel setSize(Dimension size) {
        this.size = size;
        return this;
    }

    public RoomModel setPreviewSize(Dimension previewSize) {
        this.previewSize = previewSize;
        return this;
    }

    public RoomModel setType(RoomType type) {
        this.type = type;
        switch (type) {
            case RoomType.HALL:
                setColor(new Color(255, 177, 84, 1));
                break;
            case RoomType.KITCHEN:
                setColor(new Color(177, 228, 165));
                break;
            case RoomType.BEDROOM:
                setColor(new Color(206, 165, 228));
                break;
            case RoomType.BATHROOM:
                setColor(new Color(165, 217, 228));
                break;
            default:
                break;
        }
        return this;
    }

    private void setColor(Color color) {
        this.color = color;
    }

    public RoomModel setPlaced(boolean isPlaced) {
        this.isPlaced = isPlaced;
        return this;
    }

    public RoomModel setPlacing(boolean isPlacing) {
        this.isPlacing = isPlacing;
        return this;
    }

    public RoomModel setOverlapping(boolean isOverlapping) {
        this.isOverlapping = isOverlapping;
        return this;
    }

    public RoomModel setHovering(boolean isHovering) {
        this.isHovering = isHovering;
        return this;
    }

    public RoomModel setFocused(boolean isFocused) {
        this.isFocused = isFocused;
        return this;
    }

}

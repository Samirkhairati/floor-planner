package model;

import java.awt.Dimension;
import java.awt.Point;

import types.FurnitureType;
import types.Rotation;

public class FurnitureModel {
    // TODO: add flyweight pattern
    private FurnitureType type;
    private Point position;
    private Point previewPosition;
    private Dimension size;
    private Rotation rotation;
    private boolean isPlaced;
    private boolean isPlacing;
    private boolean isOverlapping;
    private boolean isHovering;
    private boolean isFocused;

    public FurnitureType getType() {
        return type;
    }

    public Point getPosition() {
        return position;
    }

    public Point getPreviewPosition() {
        return previewPosition;
    }

    public Dimension getSize() {
        return size;
    }

    public Rotation getRotation() {
        return rotation;
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

    public FurnitureModel setType(FurnitureType type) {
        this.type = type;
        return this;
    }

    public FurnitureModel setPosition(Point position) {
        this.position = position;
        return this;
    }

    public FurnitureModel setPreviewPosition(Point previewPosition) {
        this.previewPosition = previewPosition;
        return this;
    }   

    public FurnitureModel setSize(Dimension size) {
        this.size = size;
        return this;
    }

    public FurnitureModel setRotation(Rotation rotation) {
        this.rotation = rotation;
        return this;
    }

    public FurnitureModel setPlaced(boolean isPlaced) {
        this.isPlaced = isPlaced;
        return this;
    }

    public FurnitureModel setPlacing(boolean isPlacing) {
        this.isPlacing = isPlacing;
        return this;
    }

    public FurnitureModel setOverlapping(boolean isOverlapping) {
        this.isOverlapping = isOverlapping;
        return this;
    }

    public FurnitureModel setHovering(boolean isHovering) {
        this.isHovering = isHovering;
        return this;
    }

    public FurnitureModel setFocused(boolean isFocused) {
        this.isFocused = isFocused;
        return this;
    }
}

package model;

import java.awt.*;
import java.util.List;

import types.RoomType;

import java.util.ArrayList;

public class RoomModel {
    
    private List<Rectangle> furnitures = new ArrayList<>();
    private Point position;
    private Point previewPosition;
    private Dimension size;
    private Dimension previewSize;
    private RoomType type;
    private String name;
    private Color color;
    private boolean isPlacing;

    public RoomModel() {
        this.position = null; // No default position exists (don't snap back to anywhere)
    }

    public void addFurniture(Rectangle furniture) {
        furnitures.add(furniture);
    }

    public List<Rectangle> getFurnitures() {
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

    public String getName() {
        return name;
    }

    public Color getColor() {
        return color;
    }

    public boolean isPlacing() {
        return isPlacing;
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
        return this;
    }

    public RoomModel setName(String name) {
        this.name = name;
        return this;
    }

    public RoomModel setColor(Color color) {
        this.color = color;
        return this;
    }

    public RoomModel setPlacing(boolean isPlacing) {
        this.isPlacing = isPlacing;
        return this;
    }

}

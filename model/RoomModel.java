package model;

import java.awt.*;
import java.io.Serializable;
import java.util.List;

import controller.FurnitureController;
import types.Furniture;
import types.RoomType;
import types.Rotation;
import util.Tools;
import view.FurnitureView;

import java.util.ArrayList;

public class RoomModel implements Serializable {

    private List<Furniture> furnitures = new ArrayList<>();
    private Point position;
    private Point previewPosition;
    private Dimension size;
    private Dimension previewSize = size;
    private RoomType type;
    private Color color;
    private boolean isPlaced;
    private boolean isPlacing;
    private boolean isOverlapping;
    private boolean isHovering;
    private boolean isFocused;
    private Rotation previewRotation;

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

    public void removeFurnitureByModel(FurnitureModel furnitureModel) {
        furnitures.removeIf(furniture -> furniture.getModel().equals(furnitureModel));
    }

    public List<FurnitureModel> getFurnitureModels() {
        List<FurnitureModel> furnitureModels = new ArrayList<>();
        for (Furniture furniture : furnitures) {
            furnitureModels.add(furniture.getModel());
        }
        return furnitureModels;
    }

    public List<FurnitureView> getFurnitureViews() {
        List<FurnitureView> furnitureViews = new ArrayList<>();
        for (Furniture furniture : furnitures) {
            furnitureViews.add(furniture.getView());
        }
        return furnitureViews;
    }

    public List<FurnitureController> getFurnitureControllers() {
        List<FurnitureController> furnitureControllers = new ArrayList<>();
        for (Furniture furniture : furnitures) {
            furnitureControllers.add(furniture.getController());
        }
        return furnitureControllers;
    }

    public Point getPosition() {
        return position;
    }

    public Dimension getSize() {
        return size;
    }

    public Dimension getPreviewSize() {
        return previewSize;
    }

    public Point getPreviewPosition() {
        return previewPosition;
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

    public Rotation getPreviewRotation() {
        return previewRotation;
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
        setColor(Tools.typeToColor(type));
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

    public RoomModel setPreviewRotation(Rotation previewRotation) {
        this.previewRotation = previewRotation;
        return this;
    }

    public RoomModel rotate() {
        setPreviewSize(new Dimension(previewSize.height, previewSize.width));
        if (previewRotation == Rotation.DEGREES_0) {
            setPreviewRotation(Rotation.DEGREES_90);
        } else if (previewRotation == Rotation.DEGREES_90) {
            setPreviewRotation(Rotation.DEGREES_180);
        } else if (previewRotation == Rotation.DEGREES_180) {
            setPreviewRotation(Rotation.DEGREES_270);
        } else {
            setPreviewRotation(Rotation.DEGREES_0);
        }
        return this;
    }

}

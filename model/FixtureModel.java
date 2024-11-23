package model;

import java.awt.Point;
import java.io.Serializable;

import types.FixtureType;
import types.Orientation;

public class FixtureModel implements Serializable {

    private Point upTilePosition;
    private Point downTilePosition;
    private Point leftTilePosition;
    private Point rightTilePosition;

    private RoomModel upRoomModel;
    private RoomModel downRoomModel;
    private RoomModel leftRoomModel;
    private RoomModel rightRoomModel;

    private FixtureType type;
    private Point previewPosition;
    private Orientation orientation;
    private boolean isPlacing;
    private PreviewSide previewSide;

    public static enum PreviewSide {
        UP, DOWN, LEFT, RIGHT
    }

    public FixtureType getType() {
        return type;
    }

    public Point getUpTilePosition() {
        return upTilePosition;
    }

    public Point getDownTilePosition() {
        return downTilePosition;
    }

    public Point getLeftTilePosition() {
        return leftTilePosition;
    }

    public Point getRightTilePosition() {
        return rightTilePosition;
    }

    public RoomModel getUpRoomModel() {
        return upRoomModel;
    }

    public RoomModel getDownRoomModel() {
        return downRoomModel;
    }

    public RoomModel getLeftRoomModel() {
        return leftRoomModel;
    }

    public RoomModel getRightRoomModel() {
        return rightRoomModel;
    }

    public Point getPreviewPosition() {
        return previewPosition;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public boolean isPlacing() {
        return isPlacing;
    }

    public PreviewSide getPreviewSide() {
        return previewSide;
    }

    public FixtureModel setType(FixtureType type) {
        this.type = type;
        return this;
    }

    public FixtureModel setUpTilePosition(Point upTilePosition) {
        this.upTilePosition = upTilePosition;
        return this;
    }

    public FixtureModel setDownTilePosition(Point downTilePosition) {
        this.downTilePosition = downTilePosition;
        return this;
    }

    public FixtureModel setLeftTilePosition(Point leftTilePosition) {
        this.leftTilePosition = leftTilePosition;
        return this;
    }

    public FixtureModel setRightTilePosition(Point rightTilePosition) {
        this.rightTilePosition = rightTilePosition;
        return this;
    }

    public FixtureModel setUpRoomModel(RoomModel upRoomModel) {
        this.upRoomModel = upRoomModel;
        return this;
    }

    public FixtureModel setDownRoomModel(RoomModel downRoomModel) {
        this.downRoomModel = downRoomModel;
        return this;
    }

    public FixtureModel setLeftRoomModel(RoomModel leftRoomModel) {
        this.leftRoomModel = leftRoomModel;
        return this;
    }

    public FixtureModel setRightRoomModel(RoomModel rightRoomModel) {
        this.rightRoomModel = rightRoomModel;
        return this;
    }

    public FixtureModel setPreviewPosition(Point previewPosition) {
        this.previewPosition = previewPosition;
        return this;
    }

    public FixtureModel setOrientation(Orientation orientation) {
        this.orientation = orientation;
        return this;
    }

    public FixtureModel setPlacing(boolean isPlacing) {
        this.isPlacing = isPlacing;
        return this;
    }

    public FixtureModel setPreviewSide(PreviewSide previewSide) {
        this.previewSide = previewSide;
        return this;
    }

}

enum PreviewSide {
    UP, DOWN, LEFT, RIGHT
}
package model;

import java.awt.Point;

import types.FixtureType;
import types.Orientation;
import types.RoomType;

public class FixtureModel {
    private FixtureType type;

    private Point upTilePosition;
    private Point downTilePosition;
    private Point leftTilePosition;
    private Point rightTilePosition;

    private RoomType upRoomType;
    private RoomType downRoomType;
    private RoomType leftRoomType;
    private RoomType rightRoomType;

    private Point previewPosition;
    private Orientation orientation;
    private boolean isPlacing;

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

    public RoomType getUpRoomType() {
        return upRoomType;
    }

    public RoomType getDownRoomType() {
        return downRoomType;
    }

    public RoomType getLeftRoomType() {
        return leftRoomType;
    }

    public RoomType getRightRoomType() {
        return rightRoomType;
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

    public FixtureModel setUpRoomType(RoomType upRoomType) {
        this.upRoomType = upRoomType;
        return this;
    }

    public FixtureModel setDownRoomType(RoomType downRoomType) {
        this.downRoomType = downRoomType;
        return this;
    }

    public FixtureModel setLeftRoomType(RoomType leftRoomType) {
        this.leftRoomType = leftRoomType;
        return this;
    }

    public FixtureModel setRightRoomType(RoomType rightRoomType) {
        this.rightRoomType = rightRoomType;
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

}

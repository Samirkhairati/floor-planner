package model;

import java.awt.Point;

import types.FixtureType;
import types.Orientation;

public class FixtureModel {
    private FixtureType type;
    private Point position;
    private Point previewPosition;
    private Orientation orientation;
    private boolean isPlacing;

    public FixtureType getType() {
        return type;
    }

    public Point getPosition() {
        return position;
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

    public FixtureModel setPosition(Point position) {
        this.position = position;
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

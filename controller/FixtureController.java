package controller;

import java.awt.Point;

import model.FixtureModel;
import model.FloorModel;
import model.RoomModel;
import types.Fixture;
import types.FixtureType;
import types.Orientation;
import types.RoomType;
import util.Tools;
import view.FixtureView;
import view.FloorView;

public class FixtureController {
    private final FixtureModel fixtureModel;
    private final FixtureView fixtureView;
    private final FloorView floorView;
    private final FloorModel floorModel;
    private final FloorController floorController;

    public FixtureController(FloorView floorView, FloorModel floorModel, FloorController floorController,
            FixtureType selectedFixtureType) {
        this.fixtureModel = new FixtureModel().setType(selectedFixtureType);
        this.fixtureView = new FixtureView(fixtureModel);
        this.floorView = floorView;
        this.floorModel = floorModel;
        this.floorController = floorController;
    }

    public void placeFixture(Point location) {

        Tools.snapEdge(location, floorController, fixtureModel);

        FixtureType fixtureType = fixtureModel.getType();
        RoomModel upRoomModel = fixtureModel.getUpRoomModel();
        RoomModel downRoomModel = fixtureModel.getDownRoomModel();
        RoomModel leftRoomModel = fixtureModel.getLeftRoomModel();
        RoomModel rightRoomModel = fixtureModel.getRightRoomModel();
        // Horizontal fixture
        if (fixtureModel.getOrientation() == Orientation.HORIZONTAL) {

            // bedrooms and bathrooms shouldn't have doors to the outside
            if (fixtureType == FixtureType.DOOR && (upRoomModel == null
                    && (downRoomModel.getType() == RoomType.BEDROOM || downRoomModel.getType() == RoomType.BATHROOM)
                    || (downRoomModel == null && (upRoomModel.getType() == RoomType.BEDROOM
                            || upRoomModel.getType() == RoomType.BATHROOM)))) {
                return;
            }

            // windows cannot be between rooms
            if (fixtureType == FixtureType.WINDOW && upRoomModel != null && downRoomModel != null) {
                return;
            }

        }
        // Vertical fixture
        else if (fixtureModel.getOrientation() == Orientation.VERTICAL) {
            // bedrooms and bathrooms shouldn't have doors to the outside
            if (fixtureType == FixtureType.DOOR &&
                    ((leftRoomModel == null
                            && (rightRoomModel.getType() == RoomType.BEDROOM
                                    || rightRoomModel.getType() == RoomType.BATHROOM))
                            || (rightRoomModel == null && (leftRoomModel.getType() == RoomType.BEDROOM
                                    || leftRoomModel.getType() == RoomType.BATHROOM)))) {
                return;
            }

            // windows cannot be between rooms
            if (fixtureType == FixtureType.WINDOW && leftRoomModel != null && rightRoomModel != null) {
                return;
            }
        }

        // in some invalid area
        else {
            return;
        }

        floorView.add(fixtureView);
        floorModel.addFixture(new Fixture(fixtureModel, fixtureView, this));
        floorView.repaint();
    }
}

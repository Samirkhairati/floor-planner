package controller;

import java.awt.Point;

import model.FixtureModel;
import model.FloorModel;
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
            FixtureType selectedFixtureType, Orientation orientation) {
        this.fixtureModel = new FixtureModel().setType(selectedFixtureType).setOrientation(orientation);
        this.fixtureView = new FixtureView(fixtureModel);
        this.floorView = floorView;
        this.floorModel = floorModel;
        this.floorController = floorController;
    }

    public void placeFixture(Point location) {
        if (fixtureModel.getOrientation() == Orientation.HORIZONTAL) {

            Tools.EdgeSnapResult snapResult = Tools.snapEdge(location, Orientation.HORIZONTAL);
            Point upTilePosition = snapResult.primaryEdge;
            Point downTilePosition = snapResult.secondaryEdge;
            RoomType upRoomType = Tools.roomContainingPoint(upTilePosition, floorModel);
            RoomType downRoomType = Tools.roomContainingPoint(downTilePosition, floorModel);

            // Case A: both sides are outside or same
            if (upRoomType == downRoomType) {
                return;
            }

            if (fixtureModel.getType() == FixtureType.DOOR) {
                // Case B: one side is outside other side is bedroom/bathroom
                if ((upRoomType == null && (downRoomType == RoomType.BEDROOM || downRoomType == RoomType.KITCHEN))
                        || ((upRoomType == RoomType.BEDROOM || upRoomType == RoomType.KITCHEN)
                                && downRoomType == null)) {
                    return;
                }
            } else {
                // Case C: window between rooms
                if (upRoomType != null && downRoomType != null) {
                    return;
                }
            }

            fixtureModel
                    .setUpRoomType(upRoomType)
                    .setDownRoomType(downRoomType)
                    .setUpTilePosition(upTilePosition)
                    .setDownTilePosition(downTilePosition);

        } else {
            Tools.EdgeSnapResult snapResult = Tools.snapEdge(location, Orientation.VERTICAL);
            Point leftTilePosition = snapResult.primaryEdge;
            Point rightTilePosition = snapResult.secondaryEdge;
            RoomType leftRoomType = Tools.roomContainingPoint(leftTilePosition, floorModel);
            RoomType rightRoomType = Tools.roomContainingPoint(rightTilePosition, floorModel);

            // Case A: both sides are outside
            if (leftRoomType == rightRoomType) {
                return;
            }

            if (fixtureModel.getType() == FixtureType.DOOR) {
                // Case B: one side is outside other side is bedroom/bathroom
                if ((leftRoomType == null && (rightRoomType == RoomType.BEDROOM || rightRoomType == RoomType.KITCHEN))
                        || ((leftRoomType == RoomType.BEDROOM || leftRoomType == RoomType.KITCHEN)
                                && rightRoomType == null)) {
                    return;
                }
            } else {
                // Case C: window between rooms
                if (leftRoomType != null && rightRoomType != null) {
                    return;
                }
            }

            fixtureModel
                    .setLeftRoomType(leftRoomType)
                    .setRightRoomType(rightRoomType)
                    .setLeftTilePosition(leftTilePosition)
                    .setRightTilePosition(rightTilePosition);
        }

        floorView.add(fixtureView);
        floorModel.addFixture(new Fixture(fixtureModel, fixtureView, this));
        floorView.repaint();
    }
}

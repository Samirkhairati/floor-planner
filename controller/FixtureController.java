package controller;

import java.awt.Point;
import java.awt.event.MouseEvent;

import model.FixtureModel;
import model.FloorModel;
import model.RoomModel;
import model.FixtureModel.PreviewSide;
import types.Fixture;
import types.FixtureType;
import types.Orientation;
import types.Room;
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
        this.fixtureView = new FixtureView();
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

    public static void previewFixtures(FloorModel model, MouseEvent e, Room room) {
        for (Fixture fixture : model.getFixtures()) {

            FixtureModel fixtureModel = fixture.getModel();

            if (fixtureModel.getOrientation() == Orientation.HORIZONTAL) {
                RoomModel upRoomModel = fixtureModel.getUpRoomModel();
                RoomModel downRoomModel = fixtureModel.getDownRoomModel();

                if (upRoomModel != room.getRoomModel() && downRoomModel != room.getRoomModel()) {
                    continue;
                }

                if (upRoomModel == null) {
                    fixtureModel
                            .setPreviewSide(PreviewSide.DOWN)
                            .setPreviewPosition(
                                    Tools.snap(new Point(
                                            fixtureModel.getDownTilePosition().x + e.getPoint().x
                                                    - room.getRoomModel().getPosition().x,
                                            fixtureModel.getDownTilePosition().y + e.getPoint().y
                                                    - room.getRoomModel().getPosition().y)));
                } else if (downRoomModel == null) {
                    fixtureModel
                            .setPreviewSide(PreviewSide.UP)
                            .setPreviewPosition(
                                    Tools.snap(new Point(
                                            fixtureModel.getUpTilePosition().x + e.getPoint().x
                                                    - room.getRoomModel().getPosition().x,
                                            fixtureModel.getUpTilePosition().y + e.getPoint().y
                                                    - room.getRoomModel().getPosition().y)));
                } else {

                    if (upRoomModel == room.getRoomModel()) {
                        fixtureModel
                                .setPreviewSide(PreviewSide.UP)
                                .setPreviewPosition(Tools
                                        .snap(new Point(
                                                fixtureModel.getUpTilePosition().x + e.getPoint().x
                                                        - room.getRoomModel().getPosition().x,
                                                fixtureModel.getUpTilePosition().y + e.getPoint().y
                                                        - room.getRoomModel().getPosition().y)));
                    } else {
                        fixtureModel
                                .setPreviewSide(PreviewSide.DOWN)
                                .setPreviewPosition(Tools
                                        .snap(new Point(
                                                fixtureModel.getDownTilePosition().x + e.getPoint().x
                                                        - room.getRoomModel().getPosition().x,
                                                fixtureModel.getDownTilePosition().y + e.getPoint().y
                                                        - room.getRoomModel().getPosition().y)));
                    }

                }

                fixtureModel.setPlacing(true);

            } else {
                RoomModel leftRoomModel = fixtureModel.getLeftRoomModel();
                RoomModel rightRoomModel = fixtureModel.getRightRoomModel();

                if (leftRoomModel != room.getRoomModel() && rightRoomModel != room.getRoomModel()) {
                    continue;
                }

                if (leftRoomModel == null) {
                    fixtureModel
                            .setPreviewSide(PreviewSide.RIGHT)
                            .setPreviewPosition(
                                    Tools.snap(new Point(
                                            fixtureModel.getRightTilePosition().x + e.getPoint().x
                                                    - room.getRoomModel().getPosition().x,
                                            fixtureModel.getRightTilePosition().y + e.getPoint().y
                                                    - room.getRoomModel().getPosition().y)));
                    ;
                } else if (rightRoomModel == null) {
                    fixtureModel
                            .setPreviewSide(PreviewSide.LEFT)
                            .setPreviewPosition(
                                    Tools.snap(new Point(
                                            fixtureModel.getLeftTilePosition().x + e.getPoint().x
                                                    - room.getRoomModel().getPosition().x,
                                            fixtureModel.getLeftTilePosition().y + e.getPoint().y
                                                    - room.getRoomModel().getPosition().y)));
                } else {

                    if (leftRoomModel == room.getRoomModel()) {
                        fixtureModel
                                .setPreviewSide(PreviewSide.LEFT)
                                .setPreviewPosition(Tools
                                        .snap(new Point(
                                                fixtureModel.getLeftTilePosition().x + e.getPoint().x
                                                        - room.getRoomModel().getPosition().x,
                                                fixtureModel.getLeftTilePosition().y + e.getPoint().y
                                                        - room.getRoomModel().getPosition().y)));
                    } else {
                        fixtureModel
                                .setPreviewSide(PreviewSide.RIGHT)
                                .setPreviewPosition(Tools
                                        .snap(new Point(
                                                fixtureModel.getRightTilePosition().x + e.getPoint().x
                                                        - room.getRoomModel().getPosition().x,
                                                fixtureModel.getRightTilePosition().y + e.getPoint().y
                                                        - room.getRoomModel().getPosition().y)));
                    }
                }
                fixtureModel.setPlacing(true);
            }
        }

    }
}

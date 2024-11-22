package view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

import javax.swing.*;

import model.FixtureModel;
import model.FixtureModel.PreviewSide;
import types.FixtureType;
import types.Orientation;
import types.RoomType;
import util.Config;
import util.Tools;

import java.awt.*;

public class FixtureView extends JComponent {
    private final FixtureModel fixtureModel;

    public FixtureView(FixtureModel fixtureModel) {
        this.fixtureModel = fixtureModel;
    }

    public static void draw(FixtureModel fixture, Graphics g) {

        if (fixture.isPlacing()) {

            if (fixture.getPreviewSide() == PreviewSide.UP) {
                drawFixtureLines(g, fixture.getType(), fixture.getUpRoomModel().getType(),
                        new Point(fixture.getPreviewPosition().x, fixture.getPreviewPosition().y + Config.SNAP),
                        new Point(fixture.getPreviewPosition().x + Config.SNAP,
                                fixture.getPreviewPosition().y + Config.SNAP));

            }

            if (fixture.getPreviewSide() == PreviewSide.DOWN) {
                drawFixtureLines(g, fixture.getType(), fixture.getDownRoomModel().getType(),
                        new Point(fixture.getPreviewPosition().x, fixture.getPreviewPosition().y),
                        new Point(fixture.getPreviewPosition().x + Config.SNAP,
                                fixture.getPreviewPosition().y));
            }

            if (fixture.getPreviewSide() == PreviewSide.LEFT) {
                drawFixtureLines(g, fixture.getType(), fixture.getLeftRoomModel().getType(),
                        new Point(fixture.getPreviewPosition().x + Config.SNAP, fixture.getPreviewPosition().y),
                        new Point(fixture.getPreviewPosition().x + Config.SNAP,
                                fixture.getPreviewPosition().y + Config.SNAP));
            }

            if (fixture.getPreviewSide() == PreviewSide.RIGHT) {
                drawFixtureLines(g, fixture.getType(), fixture.getRightRoomModel().getType(),
                        new Point(fixture.getPreviewPosition().x, fixture.getPreviewPosition().y),
                        new Point(fixture.getPreviewPosition().x,
                                fixture.getPreviewPosition().y + Config.SNAP));

            }

            // g.setColor(Color.GRAY);
            // g.fillRect(fixture.getPreviewPosition().x, fixture.getPreviewPosition().y,
            // Config.SNAP, Config.SNAP);
        } else {
            if (fixture.getOrientation() == Orientation.HORIZONTAL) {
                if (fixture.getUpRoomModel() == null) {
                    drawFixtureLines(g, fixture.getType(), fixture.getDownRoomModel().getType(),
                            new Point(fixture.getDownTilePosition().x, fixture.getDownTilePosition().y),
                            new Point(fixture.getDownTilePosition().x + Config.SNAP,
                                    fixture.getDownTilePosition().y));
                } else if (fixture.getDownRoomModel() == null) {
                    drawFixtureLines(g, fixture.getType(), fixture.getUpRoomModel().getType(),
                            new Point(fixture.getUpTilePosition().x, fixture.getUpTilePosition().y + Config.SNAP),
                            new Point(fixture.getUpTilePosition().x + Config.SNAP,
                                    fixture.getUpTilePosition().y + Config.SNAP));
                } else {
                    drawFixtureLines(g, fixture.getType(), fixture.getDownRoomModel().getType(),
                            new Point(fixture.getDownTilePosition().x, fixture.getDownTilePosition().y),
                            new Point(fixture.getDownTilePosition().x + Config.SNAP,
                                    fixture.getDownTilePosition().y));
                    drawFixtureLines(g, fixture.getType(), fixture.getUpRoomModel().getType(),
                            new Point(fixture.getUpTilePosition().x, fixture.getUpTilePosition().y + Config.SNAP),
                            new Point(fixture.getUpTilePosition().x + Config.SNAP,
                                    fixture.getUpTilePosition().y + Config.SNAP));
                }

            } else if (fixture.getOrientation() == Orientation.VERTICAL) {
                if (fixture.getLeftRoomModel() == null) {
                    drawFixtureLines(g, fixture.getType(), fixture.getRightRoomModel().getType(),
                            new Point(fixture.getRightTilePosition().x, fixture.getRightTilePosition().y),
                            new Point(fixture.getRightTilePosition().x,
                                    fixture.getRightTilePosition().y + Config.SNAP));
                } else if (fixture.getRightRoomModel() == null) {
                    drawFixtureLines(g, fixture.getType(), fixture.getLeftRoomModel().getType(),
                            new Point(fixture.getLeftTilePosition().x + Config.SNAP,
                                    fixture.getLeftTilePosition().y),
                            new Point(fixture.getLeftTilePosition().x + Config.SNAP,
                                    fixture.getLeftTilePosition().y + Config.SNAP));
                } else {
                    drawFixtureLines(g, fixture.getType(), fixture.getRightRoomModel().getType(),
                            new Point(fixture.getRightTilePosition().x, fixture.getRightTilePosition().y),
                            new Point(fixture.getRightTilePosition().x,
                                    fixture.getRightTilePosition().y + Config.SNAP));
                }
            }
        }

    }

    private static void drawFixtureLines(Graphics g, FixtureType fixtureType, RoomType roomType, Point from, Point to) {
        g.setColor(Tools.typeToColor(roomType));
        if (fixtureType == FixtureType.WINDOW) {
            Graphics2D g2d = (Graphics2D) g;
            float[] dashPattern = { 5, 5, 5, 5, 5, 5 };
            g2d.setStroke(new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10, dashPattern, 0));
            g2d.drawLine(from.x, from.y, to.x, to.y);
            g2d.setStroke(new BasicStroke()); // Reset to default stroke
        } else {
            g.drawLine(from.x, from.y, to.x, to.y);
        }
    }
}

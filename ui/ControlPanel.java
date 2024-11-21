package ui;

import javax.swing.*;

import controller.FloorController;

import java.awt.*;

public class ControlPanel extends JPanel {

    FloorController floorController;

    public ControlPanel(FloorController floorController) {

        this.floorController = floorController;

        setBackground(Color.DARK_GRAY); // Set background color to differentiate

        setLayout(new BorderLayout());

        JPanel actionPanel = new ActionPanel(floorController);
        add(actionPanel, BorderLayout.NORTH);

        FurniturePanel furniturePanel = new FurniturePanel(floorController);
        add(furniturePanel, BorderLayout.CENTER);

    }
}

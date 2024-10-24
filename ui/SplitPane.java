package ui;

import javax.swing.*;
import java.awt.*;

import controllers.FloorController;
import models.FloorModel;
import views.FloorView;
import util.Config;
import util.KeyBinder;

public class SplitPane extends JFrame {

    @SuppressWarnings("unused")
    public SplitPane() {
        setTitle("Fixed UI Example");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Make the window full screen

        JPanel controlPanel = new ControlPanel();
        controlPanel.setPreferredSize(new Dimension(450, getHeight()));
        controlPanel.setBackground(Color.LIGHT_GRAY);

        FloorModel model = new FloorModel(Config.SNAP, Config.DEFAULT_ROOM_WIDTH, Config.DEFAULT_ROOM_HEIGHT);
        FloorView view = new FloorView(model);
        FloorController controller = new FloorController(model, view);

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, controlPanel, view);
        splitPane.setDividerLocation(450);
        getContentPane().add(splitPane);

        KeyBinder keyBinder = new KeyBinder(this);
        setVisible(true);
    }
}

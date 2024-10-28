package ui;

import javax.swing.*;

import controller.FloorController;

import java.awt.*;

import model.FloorModel;
import util.Config;
import util.KeyBinder;
import view.FloorView;

public class SplitPane extends JFrame {

    public SplitPane() {
        setTitle("Fixed UI Example");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Make the window full screen

        FloorModel model = new FloorModel(Config.SNAP);
        FloorView view = new FloorView(model);
        FloorController controller = new FloorController(model, view);

        JPanel controlPanel = new ControlPanel(controller);
        controlPanel.setPreferredSize(new Dimension(450, getHeight()));
        controlPanel.setBackground(Color.LIGHT_GRAY);

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, controlPanel, view);
        splitPane.setDividerLocation(450);
        getContentPane().add(splitPane);

        new KeyBinder(this);
        setVisible(true);
    }
}

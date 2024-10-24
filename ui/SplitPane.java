package ui;

import javax.swing.*;

import controllers.FloorController;
import models.FloorModel;
import util.Config;
import util.StateManager;

import java.awt.*;
import java.awt.event.KeyEvent;

import views.ControlPanel;
import views.FloorView;
import views.Floor.Floor;

public class SplitPane extends JFrame {

    public SplitPane() {
        // Set the title of the window
        setTitle("Fixed UI Example");
        setSize(800, 600); // Initial size of the window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Make the window full screen

        // Create the two panels with fixed sizes
        JPanel controlPanel = new ControlPanel();
        controlPanel.setPreferredSize(new Dimension(450, getHeight())); // 1/4th width (450px)
        controlPanel.setBackground(Color.LIGHT_GRAY);

        JPanel floor = new Floor(); // Assume this is your custom Floor panel

        FloorModel model = new FloorModel(Config.SNAP, Config.DEFAULT_ROOM_WIDTH, Config.DEFAULT_ROOM_HEIGHT); // grid size, room width, room height
        FloorView view = new FloorView(model);
        FloorController controller = new FloorController(model, view);

        // Create a JSplitPane to hold the control panel and the floor panel1
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, controlPanel, view);
        splitPane.setDividerLocation(450); // Set the initial divider location

        // Add the split pane to the frame
        getContentPane().add(splitPane);

        // Setup key bindings
        setupKeyBindings();

        // Make the window visible
        setVisible(true);
    }

    private void setupKeyBindings() {
        // Get the InputMap and ActionMap from the JFrame's root pane
        InputMap inputMap = getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = getRootPane().getActionMap();

        // Define the key bindings
        inputMap.put(KeyStroke.getKeyStroke("1"), "key1");
        inputMap.put(KeyStroke.getKeyStroke("2"), "key2");
        inputMap.put(KeyStroke.getKeyStroke("3"), "key3");
        inputMap.put(KeyStroke.getKeyStroke("4"), "key4");
        inputMap.put(KeyStroke.getKeyStroke("5"), "key5");

        // Define the actions for each key
        actionMap.put("key1", new AbstractAction() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                StateManager.getInstance().keyCode.setState(KeyEvent.VK_1);
            }
        });
        actionMap.put("key2", new AbstractAction() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                StateManager.getInstance().keyCode.setState(KeyEvent.VK_2);
            }
        });
        actionMap.put("key3", new AbstractAction() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                StateManager.getInstance().keyCode.setState(KeyEvent.VK_3);
            }
        });
        actionMap.put("key4", new AbstractAction() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                StateManager.getInstance().keyCode.setState(KeyEvent.VK_4);
            }
        });
        actionMap.put("key5", new AbstractAction() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                StateManager.getInstance().keyCode.setState(KeyEvent.VK_5);
            }
        });
    }
}

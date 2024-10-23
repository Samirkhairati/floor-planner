package views;

import javax.swing.*;

import views.Floor.Floor;

import java.awt.*;

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
        controlPanel.setPreferredSize(new Dimension(450, getHeight())); // 1/4th width (200px)
        controlPanel.setBackground(Color.LIGHT_GRAY);

        JPanel floor = new Floor();
        // Create a JSplitPane to hold the control panel and the floor panel
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, controlPanel, floor);
        splitPane.setDividerLocation(450); // Set the initial divider location

        // Add the split pane to the frame
        getContentPane().add(splitPane);

        // Make the window visible
        setVisible(true);

        // Add key listener to the splitPane
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                System.out.println("Key pressed: " + evt.getKeyCode());
            }
        });

        // Make sure the splitPane is focusable to receive key events
        setFocusable(true);
        requestFocusInWindow();
    }
}

package views;
import javax.swing.*;

import java.awt.*;

public class SplitPane extends JFrame {

    public SplitPane() {
        // Set the title of the window
        setTitle("Split UI Example");
        // Prevent the split pane from reloading its components when resized
        setIgnoreRepaint(true);
        setTitle("Split UI Example");
        setSize(800, 600); // Initial size of the window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window

        // Create the two panels to be added to the split pane
        JPanel controlPanel = new ControlPanel();
        

        JPanel rightPanel = new JPanel();
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Make the window take the full screen size
        rightPanel.setBackground(Color.WHITE); // Set background color to differentiate
        rightPanel.add(new JLabel("Right Panel - 3/4th"));

        // Create a split pane with a 1/4 and 3/4 ratio
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, controlPanel, rightPanel);
        splitPane.setDividerLocation(400); // Set divider location at 1/4th (200px for 800px width)

        // Allow the divider to be adjusted and give the right panel more flexibility
        splitPane.setResizeWeight(0.25); // Ratio of 1/4 on the left, 3/4 on the right

        // Add the split pane to the frame
        add(splitPane);

        // Make the window visible
        setVisible(true);
    }
}
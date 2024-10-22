package views;
import javax.swing.*;
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

        JPanel rightPanel = new JPanel();
        rightPanel.setBackground(Color.WHITE); // Set background color
        rightPanel.add(new JLabel("Right Panel - 3/4th"));

        // Use BorderLayout to position the panels
        setLayout(new BorderLayout());

        // Add the control panel on the left (WEST) and the right panel on the center (CENTER)
        add(controlPanel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.CENTER);

        // Make the window visible
        setVisible(true);
    }
}

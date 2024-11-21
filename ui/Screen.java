package ui;

import javax.swing.*;

import controller.FloorController;

import java.awt.*;

import model.FloorModel;
import util.KeyBinder;
import view.FloorView;

public class Screen extends JFrame {

    // Normal constructor when program is opened
    public Screen() {
        FloorModel model = new FloorModel();
        FloorView view = new FloorView(model);
        FloorController controller = new FloorController(model, view);

        setup(controller, view);
    }

    // Constructor when program is opened with a file
    public Screen(FloorController controller) {
        setup(controller, controller.getView());
    }

    public void resetWithController(FloorController controller) {
        dispose(); // Close the current screen
        new Screen(controller); // Create a new Screen with the loaded controller
    }

    private void setup(FloorController controller, FloorView view) {
        setTitle("Fixed UI Example");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Make the window full screen
        
        JPanel controlPanel = new ControlPanel(controller, this);
        controlPanel.setPreferredSize(new Dimension(450, getHeight()));
        controlPanel.setBackground(Color.LIGHT_GRAY);

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, controlPanel, view);
        splitPane.setDividerLocation(450);
        splitPane.setContinuousLayout(true);
        splitPane.setDividerSize(5);
        getContentPane().add(splitPane);

        new KeyBinder(this);
        setVisible(true);
    }

}

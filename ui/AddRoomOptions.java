package ui;

import javax.swing.*;
import model.FloorModel;
import types.RoomType;

import java.awt.*;

public class AddRoomOptions extends JPanel {

    public int roomWidth;
    public int roomHeight;
    public String roomName;
    public RoomType selectedRoomType;

    public AddRoomOptions(FloorModel model) {
        // Create a JPanel for the input fields
        JPanel panel = new JPanel(new GridLayout(4, 2));

        // Create components
        JLabel widthLabel = new JLabel("Width (Tiles):");
        JLabel heightLabel = new JLabel("Height (Tiles):");
        JLabel typeLabel = new JLabel("Room Type:");

        JTextField widthField = new JTextField();
        JTextField heightField = new JTextField();

        // Create a combo box populated with enum values
        JComboBox<RoomType> typeComboBox = new JComboBox<>(RoomType.values());

        // Add components to the panel
        panel.add(widthLabel);
        panel.add(widthField);
        panel.add(heightLabel);
        panel.add(heightField);
        panel.add(typeLabel);
        panel.add(typeComboBox);

        int result = JOptionPane.showConfirmDialog(null, panel,
                "Enter Room Dimensions", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            try {
                int widthTiles = Integer.parseInt(widthField.getText());
                int heightTiles = Integer.parseInt(heightField.getText());

                if (widthTiles > 0 && heightTiles > 0) {
                    this.roomWidth = widthTiles * model.getGridSize();
                    this.roomHeight = heightTiles * model.getGridSize();
                    this.selectedRoomType = (RoomType) typeComboBox.getSelectedItem();
                } else {
                    JOptionPane.showMessageDialog(null, "Dimensions must be positive integers!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Please enter valid integer values!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}

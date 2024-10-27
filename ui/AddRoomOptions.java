package ui;

import javax.swing.*;

import model.FloorModel;

import java.awt.*;

public class AddRoomOptions extends JPanel {

    public int roomWidth;
    public int roomHeight;

    public AddRoomOptions (FloorModel model) {
        
        JPanel panel = new JPanel(new GridLayout(2, 2));
        JLabel widthLabel = new JLabel("Width (Tiles):");
        JLabel heightLabel = new JLabel("Height (Tiles):");
        JTextField widthField = new JTextField();
        JTextField heightField = new JTextField();

        panel.add(widthLabel);
        panel.add(widthField);
        panel.add(heightLabel);
        panel.add(heightField);

        int result = JOptionPane.showConfirmDialog(null, panel, 
            "Enter Room Dimensions", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            try {
                int widthTiles = Integer.parseInt(widthField.getText());
                int heightTiles = Integer.parseInt(heightField.getText());

                if (widthTiles > 0 && heightTiles > 0) {
                    // Multiply the tile count by the grid size to get the pixel size
                    int roomWidth = widthTiles * model.getGridSize();
                    int roomHeight = heightTiles * model.getGridSize();
                    this.roomWidth = roomWidth;
                    this.roomHeight = roomHeight;
                } else {
                    JOptionPane.showMessageDialog(null, "Dimensions must be positive integers!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Please enter valid integer values!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

}

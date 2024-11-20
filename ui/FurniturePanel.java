package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FurniturePanel extends JPanel {
    public FurniturePanel() {
        // Set up the panel with GridLayout
        JPanel gridPanel = new JPanel(new WrapLayout()); // 3 columns, dynamic rows
        gridPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Add image buttons to the grid panel
        for (int i = 1; i <= 10; i++) { // Example: 20 items
            ImageIcon icon = new ImageIcon(new ImageIcon("path/to/image" + i + ".png")
                    .getImage()
                    .getScaledInstance(100, 100, Image.SCALE_SMOOTH)); // Scale image
            JButton button = new JButton(icon);
            button.setText("Item " + i); // Add text for identification
            button.setPreferredSize(new Dimension(125, 125)); // Set button size
            button.setVerticalTextPosition(SwingConstants.BOTTOM);
            button.setHorizontalTextPosition(SwingConstants.CENTER);

            // Add action listener to handle click events
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println(button.getText() + " clicked!");
                }
            });

            gridPanel.add(button);
        }

        // Make the grid panel scrollable
        JScrollPane scrollPane = new JScrollPane(gridPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        // Set layout and add the scroll pane to this panel
        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);

    }
}

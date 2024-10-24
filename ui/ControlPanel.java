package ui;
import javax.swing.*;

import java.awt.*;

public class ControlPanel extends JPanel {
    public ControlPanel() {

        setBackground(Color.DARK_GRAY); // Set background color to differentiate

        setLayout(new BorderLayout());

        JPanel actionPanel = new ActionPanel();
        add(actionPanel, BorderLayout.NORTH);

        JPanel gridPanel = new JPanel();
        gridPanel.setLayout(new WrapLayout()); // 3 columns, rows will be determined by the number of items
        for (int i = 1; i <= 13; i++) {
            gridPanel.add(new JLabel("Item " + i));
        }

        JScrollPane scrollPane = new JScrollPane(gridPanel);
        add(scrollPane, BorderLayout.CENTER);

    }
}

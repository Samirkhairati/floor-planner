package views;
import javax.swing.*;
import java.awt.*;

public class ControlPanel extends JPanel {
    public ControlPanel() {

        setBackground(Color.LIGHT_GRAY); // Set background color to differentiate

        setLayout(new BorderLayout());

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 5));
        for (int i = 1; i <= 5; i++) {
            buttonPanel.add(new JButton("Button " + i));
        }
        add(buttonPanel, BorderLayout.NORTH);

        JPanel gridPanel = new JPanel();
        gridPanel.setLayout(new GridLayout(0, 3)); // 3 columns, rows will be determined by the number of items
        for (int i = 1; i <= 13; i++) {
            gridPanel.add(new JLabel("Item " + i));
        }

        JScrollPane scrollPane = new JScrollPane(gridPanel);
        add(scrollPane, BorderLayout.CENTER);

    }
}

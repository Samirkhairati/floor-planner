package views.ActionPanel;
import javax.swing.*;

import views.WrapLayout;

import java.awt.*;

public class ActionPanel extends JPanel {

    public ActionPanel() {

        setBackground(Color.LIGHT_GRAY); // Set background color to differentiate

        setLayout(new WrapLayout());

        JButton button1 = new JButton("📦");
        JButton button2 = new JButton("🚪");
        JButton button3 = new JButton("🪟");
        JButton button4 = new JButton("💾");
        JButton button5 = new JButton("📁");

        Font font = new Font("Arial", Font.PLAIN, 30); // Increase text size

        button1.setFont(font);
        button2.setFont(font);
        button3.setFont(font);
        button4.setFont(font);
        button5.setFont(font);

        Dimension buttonSize = new Dimension(80, 50); // Set button size to 80x80 pixels
        button1.setPreferredSize(buttonSize);
        button2.setPreferredSize(buttonSize);
        button3.setPreferredSize(buttonSize);
        button4.setPreferredSize(buttonSize);
        button5.setPreferredSize(buttonSize);

        add(button1);
        add(button2);
        add(button3);
        add(button4);
        add(button5);
    }
    
}

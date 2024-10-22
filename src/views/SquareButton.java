package views;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SquareButton extends JButton {

    public SquareButton(String text, Icon icon) {
        super(icon);
        setPreferredSize(new Dimension(50, 50)); // Make it square


        // Add action listener to show a popup when clicked
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "You clicked: " + text);
            }
        });
    }
}

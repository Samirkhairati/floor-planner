import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }

    private static void createAndShowGUI() {
        // Create the frame
        JFrame frame = new JFrame("Floor Planner");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        // Add a label
        JLabel label = new JLabel("Welcome to Floor Planner!");
        frame.getContentPane().add(label);

        // Display the frame
        frame.setVisible(true);
    }
}
import javax.swing.*;

import views.SplitPane;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }

    private static void createAndShowGUI() {
        
        // Create an instance of the SplitPaneExample class
        JFrame frame = new SplitPane();


    }
}
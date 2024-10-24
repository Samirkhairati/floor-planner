import javax.swing.*;

import ui.SplitPane;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
    @SuppressWarnings("unused")
    private static void createAndShowGUI() {
        // Create an instance of the SplitPaneExample class
        SplitPane splitPane = new SplitPane();

    }
}
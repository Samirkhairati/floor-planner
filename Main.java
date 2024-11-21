import javax.swing.*;

import ui.Screen;

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
        Screen splitPane = new Screen();
    }
}
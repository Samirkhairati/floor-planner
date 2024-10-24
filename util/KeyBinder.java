package util;
import java.awt.event.KeyEvent;
import javax.swing.*;
public class KeyBinder {
    public KeyBinder(JFrame frame) {
        // Get the InputMap and ActionMap from the JFrame's root pane
        InputMap inputMap = frame.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = frame.getRootPane().getActionMap();

        // Define the key bindings
        inputMap.put(KeyStroke.getKeyStroke("1"), "key1");
        inputMap.put(KeyStroke.getKeyStroke("2"), "key2");
        inputMap.put(KeyStroke.getKeyStroke("3"), "key3");
        inputMap.put(KeyStroke.getKeyStroke("4"), "key4");
        inputMap.put(KeyStroke.getKeyStroke("5"), "key5");

        // Define the actions for each key
        actionMap.put("key1", new AbstractAction() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                StateManager.getInstance().keyCode.setState(KeyEvent.VK_1);
            }
        });
        actionMap.put("key2", new AbstractAction() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                StateManager.getInstance().keyCode.setState(KeyEvent.VK_2);
            }
        });
        actionMap.put("key3", new AbstractAction() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                StateManager.getInstance().keyCode.setState(KeyEvent.VK_3);
            }
        });
        actionMap.put("key4", new AbstractAction() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                StateManager.getInstance().keyCode.setState(KeyEvent.VK_4);
            }
        });
        actionMap.put("key5", new AbstractAction() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                StateManager.getInstance().keyCode.setState(KeyEvent.VK_5);
            }
        });
    }
}


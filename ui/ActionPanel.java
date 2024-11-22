package ui;

import javax.swing.*;

import controller.FloorController;

import java.awt.*;

import util.Save;
import util.StateManager;

public class ActionPanel extends JPanel {

    private FloorController floorController;

    @SuppressWarnings("unused")
    public ActionPanel(FloorController floorController, Screen screen) {

        this.floorController = floorController;

        setBackground(Color.LIGHT_GRAY);
        setLayout(new WrapLayout());

        JButton button1 = new JButton("📦");
        JButton button4 = new JButton("💾");
        JButton button5 = new JButton("📁");
        Font font = new Font("Arial", Font.PLAIN, 30);
        button1.setFont(font);
        button4.setFont(font);
        button5.setFont(font);
        Dimension buttonSize = new Dimension(80, 50);
        button1.setPreferredSize(buttonSize);
        button4.setPreferredSize(buttonSize);
        button5.setPreferredSize(buttonSize);
        add(button1);
        add(button4);
        add(button5);

        JPanel checkboxPanel = new JPanel();
        checkboxPanel.setLayout(new GridLayout(2, 1));
        checkboxPanel.setBackground(getBackground());

        JCheckBox showLineGrid = new JCheckBox("Show Line Grid");
        JCheckBox showDotGrid = new JCheckBox("Show Dot Grid");

        showLineGrid.setSelected(StateManager.getInstance().showLineGrid.getState());
        showDotGrid.setSelected(StateManager.getInstance().showDotGrid.getState());

        checkboxPanel.add(showLineGrid);
        checkboxPanel.add(showDotGrid);

        checkboxPanel.setPreferredSize(new Dimension(130, 40));

        add(checkboxPanel);

        showLineGrid.addActionListener(e -> StateManager.getInstance().showLineGrid.setState(showLineGrid.isSelected()));
        showDotGrid.addActionListener(e -> StateManager.getInstance().showDotGrid.setState(showDotGrid.isSelected()));

        //NEW ROOM BUTTON
        button1.addActionListener(e -> this.floorController.startPlacingRoom());

        //SAVE FILE BUTTON
        button4.addActionListener(e -> {
            Save.saveFile(floorController, screen);
        });

        //LOAD FILE BUTTON
        button5.addActionListener(e -> {
            Save.loadFile(floorController, screen);
        });
    }
    
}

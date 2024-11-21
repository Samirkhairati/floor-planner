package ui;

import javax.swing.*;

import controller.FloorController;
import types.FurnitureIcon;
import types.FurnitureType;

import java.util.ArrayList;
import java.util.List;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FurniturePanel extends JPanel {

    FloorController floorController;

    public FurniturePanel(FloorController floorController) {

        this.floorController = floorController;

        JPanel gridPanel = new JPanel(new WrapLayout()); 
        gridPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        List<FurnitureIcon> ICONS = new ArrayList<>();
        for (FurnitureType furnitureType : FurnitureType.values()) {
            ICONS.add(new FurnitureIcon(furnitureType));
        }

        for (FurnitureIcon furnitureIcon : ICONS) { 
            JButton button = new JButton(furnitureIcon.getImage());
            button.setPreferredSize(new Dimension(125, 125));
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    floorController.startPlacingFurniture(furnitureIcon.getType());
                }
            });
            gridPanel.add(button);
        }

        JScrollPane scrollPane = new JScrollPane(gridPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);

    }
}

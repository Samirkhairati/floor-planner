public class SplitPaneExample extends JFrame {

    public SplitPaneExample() {
        // Set the title of the window
        setTitle("Split UI Example");
        setSize(800, 600); // Initial size of the window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window

        // Create the two panels to be added to the split pane
        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(Color.LIGHT_GRAY); // Set background color to differentiate
        leftPanel.add(new JLabel("Left Panel - 1/4th"));

        JPanel rightPanel = new JPanel();
        rightPanel.setBackground(Color.WHITE); // Set background color to differentiate
        rightPanel.add(new JLabel("Right Panel - 3/4th"));

        // Create a split pane with a 1/4 and 3/4 ratio
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel, rightPanel);
        splitPane.setDividerLocation(200); // Set divider location at 1/4th (200px for 800px width)

        // Allow the divider to be adjusted and give the right panel more flexibility
        splitPane.setResizeWeight(0.25); // Ratio of 1/4 on the left, 3/4 on the right

        // Add the split pane to the frame
        add(splitPane);

        // Make the window visible
        setVisible(true);
    }
}
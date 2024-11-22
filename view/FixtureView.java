package view;

import javax.swing.JComponent;

import model.FixtureModel;

public class FixtureView extends JComponent {
    private final FixtureModel fixtureModel;

    public FixtureView(FixtureModel fixtureModel) {
        this.fixtureModel = fixtureModel;
    }
}

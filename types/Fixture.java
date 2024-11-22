package types;


import java.io.Serializable;

import controller.FixtureController;
import model.FixtureModel;
import view.FixtureView;

public class Fixture implements Serializable {
    FixtureModel model;
    FixtureView view;
    FixtureController controller;

    public Fixture(FixtureModel model, FixtureView view, FixtureController controller) {
        this.model = model;
        this.view = view;
        this.controller = controller;
    }

    public FixtureModel getModel() {
        return model;
    }

    public FixtureView getView() {
        return view;
    }

    public FixtureController getController() {
        return controller;
    }
}

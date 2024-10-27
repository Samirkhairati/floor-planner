package types;

import controller.FloorController;
import model.FloorModel;
import view.FloorView;

public class Floor {
    public FloorModel model;
    public FloorView view;
    public FloorController controller;

    public Floor(FloorModel model, FloorView view, FloorController controller) {
        this.model = model;
        this.view = view;
        this.controller = controller;
    }
}

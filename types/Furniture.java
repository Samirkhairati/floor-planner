package types;


import controller.FurnitureController;
import model.FurnitureModel;
import view.FurnitureView;

public class Furniture {
    FurnitureModel model;
    FurnitureView view;
    FurnitureController controller;

    public Furniture(FurnitureModel model, FurnitureView view, FurnitureController controller) {
        this.model = model;
        this.view = view;
        this.controller = controller;
    }

    public FurnitureModel getModel() {
        return model;
    }

    public FurnitureView getView() {
        return view;
    }

    public FurnitureController getController() {
        return controller;
    }
}

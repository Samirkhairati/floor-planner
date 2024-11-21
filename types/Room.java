package types;

import controller.RoomController;
import model.RoomModel;
import view.RoomView;

public class Room  {
    private final RoomModel roomModel;
    private final RoomView roomView;
    private final RoomController roomController;

    public Room(RoomModel roomModel, RoomView roomView, RoomController roomController) {
        this.roomModel = roomModel;
        this.roomView = roomView;
        this.roomController = roomController;
    }

    public RoomModel getRoomModel() {
        return roomModel;
    }

    public RoomView getRoomView() {
        return roomView;
    }

    public RoomController getRoomController() {
        return roomController;
    }
}

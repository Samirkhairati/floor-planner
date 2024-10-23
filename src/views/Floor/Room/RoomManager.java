    // package views.Floor.Room;

    // import javax.swing.*;

    // import views.Config;
    // import views.Floor.Floor;

    // import java.awt.*;

    // public class RoomManager {
    //     private Floor floor; // Reference to the Floor component
    //     private Point mousePoint; // To track mouse location

    //     public RoomManager(Floor floor) {
    //         this.floor = floor;
    //     }

    //     public void addRoom() {
    //         System.out.println("Room added");

    //         Point mouseLocation = MouseInfo.getPointerInfo().getLocation(); // Get the current mouse position
    //         SwingUtilities.convertPointFromScreen(mouseLocation, floor); // Convert screen coordinates to panel coordinates
    //         Rectangle newRoom = new Rectangle(
    //                 mouseLocation.x - Config.DEFAULT_ROOM_WIDTH / 2,
    //                 mouseLocation.y - Config.DEFAULT_ROOM_HEIGHT / 2,
    //                 Config.DEFAULT_ROOM_WIDTH,
    //                 Config.DEFAULT_ROOM_HEIGHT);

    //         floor.addRoom(newRoom); 
    //         floor.repaint();
    //     }
    // }

package models;

import java.awt.*;
import java.util.List;
import java.util.ArrayList;

public class RoomModel {
    
    private List<Rectangle> furnitures;
    private int x;
    private int y;
    private int width;
    private int height;

    public RoomModel(int x, int y, int width, int height) {
        this.furnitures = new ArrayList<>();
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void addFurniture(Rectangle furniture) {
        furnitures.add(furniture);
    }

    public List<Rectangle> getFurnitures() {
        return furnitures;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }    

}

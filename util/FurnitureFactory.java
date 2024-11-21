package util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import model.FurnitureModel;
import types.FurnitureType;

public class FurnitureFactory {
    public static FurnitureModel createModel(FurnitureType type) {

        FurnitureModel model = new FurnitureModel();
        model.setType(type);

        BufferedImage image = loadImage(type);
        model.setImage(image);
        
        switch (type) {
            case CHAIR:
                model.setSize(1, 1);
                break;

            case SINGLE_BED:
                model.setSize(2, 4);
                break;

            case DOUBLE_BED:
                model.setSize(4, 4);
                break;

            case STUDY:
                model.setSize(3, 2);
                break;

            default:
                throw new IllegalArgumentException("Unknown FurnitureType: " + type);
        }

        return model;
    }

    public static BufferedImage loadImage(FurnitureType type) {
        try {
            return ImageIO.read(new File("assets/" + type.toString().toLowerCase() + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
            return null; // Return null if the image fails to load
        }
    }
}

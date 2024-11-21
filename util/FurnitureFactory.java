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

        switch (type) {
            case BATHTUB:
                model.setSize(2, 4);
                break;

            case CHAIR:
                model.setSize(1, 1);
                break;

            case CIRCULAR_TABLE:
                model.setSize(3, 3);
                break;

            case COUCH:
                model.setSize(2, 2);
                break;

            case SIX_SEATER_DINING_TABLE:
                model.setSize(5, 3);
                break;

            case DOUBLE_BED:
                model.setSize(4, 4);
                break;

            case DOUBLE_BED_STAND:
                model.setSize(5, 4);
                break;

            case DOUBLE_CUPBOARD:
                model.setSize(4, 1);
                break;

            case DOUBLE_SOFA:
                model.setSize(4, 2);
                break;

            case FOUR_SEATER_DINING_TABLE:
                model.setSize(3, 3);
                break;

            case KITCHEN_COUNTER:
                model.setSize(3, 1);
                break;

            case SINGLE_BED:
                model.setSize(2, 4);
                break;

            case SINGLE_BED_STAND:
                model.setSize(3, 4);
                break;

            case SINGLE_CUPBOARD:
                model.setSize(2, 1);
                break;

            case SINGLE_SOFA:
                model.setSize(2, 2);
                break;

            case SINK:
                model.setSize(1, 1);
                break;

            case STOVE:
                model.setSize(1, 1);
                break;

            case STUDY:
                model.setSize(3, 2);
                break;

            case TOILET:
                model.setSize(1, 1);
                break;

            case TRIPLE_CUPBOARD:
                model.setSize(6, 1);
                break;

            case TRIPLE_SOFA:
                model.setSize(6, 2);
                break;

            case TV:
                model.setSize(3, 1);
                break;

            case WASH_BASIN:
                model.setSize(1, 1);
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

package Maze;

import java.util.ArrayList;

/**
 * Class responsible for setting up a logo in a maze
 */
public class Logo {

    private int dimensions;
    private String image;

    /**
     * Gets the dimensions of the logo
     * @return logo dimensions
     */
    public int getDimensions() {
        return dimensions;
    }

    /**
     * Sets the dimensions for the logo
     * @param dimensions
     */
    public void setDimensions(int dimensions) {
        this.dimensions = dimensions;
    }

    /**
     * Gets the image of the logo from file path
     * @return getImage
     */
    public String getImage() {
        return image;
    }

    /**
     * Sets the image file path of the image
     * @param image
     */
    public void setImage(String image) {
        this.image = image;
    }
}

package maze;
import java.io.Serializable;
/**
 * Class responsible for setting up a logo in a maze
 */
public class Logo extends ImageStamp implements Serializable{

    private int dimensions;

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


}

package maze;
import java.io.Serializable;
public class ImageStamp implements Serializable{
    private String image;

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

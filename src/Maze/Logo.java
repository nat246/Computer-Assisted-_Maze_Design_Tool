package Maze;

import java.util.ArrayList;

public class Logo {

    private int dimensions;
    private ArrayList<Object> shape;
    private ArrayList<Object> image;

    public ArrayList<Object> GetImage(){
        return image;
    }

    public ArrayList<Object>  GetShape(){
       return shape;
    }

    public void SetImage(ArrayList<Object> image){
        this.image = image;
    }

    public void SetShape(ArrayList<Object> shape){
        this.shape = shape;
    }

    public int GetSize() {
        return dimensions;
    }

    public void SetSize(int dimensions){
       this.dimensions = dimensions;
    }
}

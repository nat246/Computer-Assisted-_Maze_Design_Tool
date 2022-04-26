package Maze;

import java.awt.*;
import javax.swing.*;



public class Tile extends Grid {

    private int a, b;

    public Tile(int x, int y) {
        super(x, y);
    }

    public boolean ArrayWallTop(boolean top) {
        return top;

    }

    public boolean ArrayWallBottom(boolean bottom) {
        return bottom;
    }

    public boolean ArrayWallLeft(boolean left) {
        return left;
    }

    public boolean ArrayWallRight(boolean right) {
        return right;
    }

    public void CoordinateA(int a) {
        this.a = a;
    }

    public void CoordinateB(int b) {
        this.b = b;
    }


    public double setCoordinateA() {
        return a;

    }

    public double setCoordinateB() {
        return b;
    }
}

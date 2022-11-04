package agh.ics.oop;

import java.util.ArrayList;
import java.util.LinkedList;


public class RectangularMap extends AbstractWorldMap {
    private int height;
    private int width;
    public ArrayList<IMapElement> elementList;
    private MapVisualizer visualizer;

    public RectangularMap(int width, int height){
        this.width = width;
        this.height = height;
        this.elementList = new ArrayList<>();
        this.visualizer = new MapVisualizer(this);
    }

    public int mapIndex(int y, int x){
        return y * width + x;
    }

    public int mapIndex(Vector2d position){
        return position.getY() * width + position.getX();
    }

    private boolean isPositionValid(Vector2d position){
        int x = position.getX();
        int y = position.getY();
        return 0 <= Math.min(x, y) && x < this.width && y < this.height;
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return this.isPositionValid(position) && !this.isOccupied(position);
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        if (!this.isPositionValid(position)){
            return false;
        }
        return super.isOccupied(position);
    }

    @Override
    protected Vector2d upperRightMapCorner() {
        return new Vector2d(width-1, height-1);
    }

    @Override
    protected Vector2d lowerLeftMapCorner() {
        return new Vector2d(0,0);
    }
}

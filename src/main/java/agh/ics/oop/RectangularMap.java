package agh.ics.oop;

import java.util.ArrayList;
import java.util.LinkedList;

public class RectangularMap implements IWorldMap {
    private int height;
    private int width;
    private ArrayList<Animal> animalMap;
    private MapVisualizer visualizer;

    public RectangularMap(int width, int height){
        this.width = width;
        this.height = height;
        for(int i = 0; i < height * width; i++){
            animalMap.set(i, null);
        };
        this.visualizer = new MapVisualizer(this);
    }

    public int mapIndex(int y, int x){
        return y * width + x;
    }

    public int mapIndex(Vector2d position){
        return position.getY() * width + position.getX();
    }

    private boolean  isPositionValid(Vector2d position){
        int x = position.getX();
        int y = position.getY();
        return 0 <= Math.min(x, y) && x < this.width && y < this.height;
    }
    @Override
    public boolean canMoveTo(Vector2d position) {
        return this.isPositionValid(position) && this.objectAt(position) == null;
    }

    @Override
    public boolean place(Animal animal) {
        Vector2d currPosition = animal.getPosition();
        if (canMoveTo(currPosition)){
            this.animalMap.get(mapIndex(currPosition));
            return true;
        }
        return false;
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        if (this.isPositionValid(position)){
            return false;
        }
        return this.objectAt(position) == null;
    }

    @Override
    public Object objectAt(Vector2d position) {
        if (isOccupied(position)){
            return animalMap.get(mapIndex(position));
        }
        return null;
    }

    @Override
    public String toString() {
        Vector2d lowerLeft = new Vector2d(0,0);
        Vector2d upperRight = new Vector2d(width, height);
        return visualizer.draw(lowerLeft, upperRight);
    }
}

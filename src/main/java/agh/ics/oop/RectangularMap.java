package agh.ics.oop;

import java.util.ArrayList;
import java.util.LinkedList;

public class RectangularMap implements IWorldMap {
    private int height;
    private int width;
    public ArrayList<Animal> animalList;
    private MapVisualizer visualizer;

    public RectangularMap(int width, int height){
        this.width = width;
        this.height = height;
        this.animalList = new ArrayList<>();
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
    public ArrayList<Animal> getAnimalList() {
        return this.animalList;
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return this.isPositionValid(position) && !this.isOccupied(position);
    }

    @Override
    public boolean place(Animal animal) {
        Vector2d currPosition = animal.getPosition();
        if (canMoveTo(currPosition)){
            this.animalList.add(animal);
            return true;
        }
        return false;
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        if (!this.isPositionValid(position)){
            return false;
        }
        for(int i = 0; i < animalList.size(); i++){
            Animal animal = animalList.get(i);
            if (animal.arePositionsEquals(position)){
                return true;
            }
        }
        return false;
    }

    @Override
    public Object objectAt(Vector2d position) {
        if (isPositionValid(position)){
            for(int i = 0; i < animalList.size(); i++){
                Animal animal = animalList.get(i);
                if (animal.arePositionsEquals(position)){
                    return animal;
                }
            }
            return null;
        }
        return null;
    }

    @Override
    public String toString() {
        Vector2d lowerLeft = new Vector2d(0,0);
        Vector2d upperRight = new Vector2d(width-1, height-1);
        return visualizer.draw(lowerLeft, upperRight);
    }
}

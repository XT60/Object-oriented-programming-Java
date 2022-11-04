package agh.ics.oop;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

abstract class AbstractWorldMap implements IWorldMap{
    List<Animal> animalList = new LinkedList<Animal>();
    MapVisualizer visualizer;

    @Override
    public boolean place(Animal animal) {
        Vector2d currPosition = animal.getPosition();
        if (canMoveTo(currPosition)){
            animalList.add(animal);
            return true;
        }
        return false;
    }
    @Override
    public boolean isOccupied(Vector2d position) {
        if (objectAt(position) == null){
            return false;
        }
        return true;
    }

    @Override
    public Object objectAt(Vector2d position) {
        for(int i = 0; i < animalList.size(); i++){
            Animal animal = animalList.get(i);
            if (animal.arePositionsEquals(position)){
                return animal;
            }
        }
        return null;
    }

    protected abstract Vector2d upperRightMapCorner();

    protected abstract Vector2d lowerLeftMapCorner();

    public String toString() {
        Vector2d lowerLeft = lowerLeftMapCorner();
        Vector2d upperRight = upperRightMapCorner();
        return visualizer.draw(lowerLeft, upperRight);
    }
}

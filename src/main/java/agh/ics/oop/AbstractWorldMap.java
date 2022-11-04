package agh.ics.oop;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

abstract class AbstractWorldMap implements IWorldMap{
    List<IMapElement> elementList = new ArrayList<IMapElement>();
    MapVisualizer visualizer;
    public AbstractWorldMap(){
        visualizer = new MapVisualizer(this);
    }

    @Override
    public boolean place(Animal animal) {
        Vector2d currPosition = animal.getPosition();
        if (canMoveTo(currPosition)){
            elementList.add(animal);
            handleAnimalMovement(animal);
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
        Object found = null;
        for(int i = 0; i < elementList.size(); i++){
            IMapElement element = elementList.get(i);
            if (element.getPosition().equals(position)){
                if (element instanceof Animal){
                    return element;
                }
                else{
                    if (found == null){
                        found = element;
                    }
                }
            }
        }
        return found;
    }

    protected abstract Vector2d upperRightMapCorner();

    protected abstract Vector2d lowerLeftMapCorner();

    public String toString() {
        Vector2d lowerLeft = lowerLeftMapCorner();
        Vector2d upperRight = upperRightMapCorner();
        return visualizer.draw(lowerLeft, upperRight);
    }
}

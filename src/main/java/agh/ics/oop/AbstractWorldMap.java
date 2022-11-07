package agh.ics.oop;

import java.util.*;

abstract class AbstractWorldMap implements IWorldMap, IPositionChangeObserver{
    Map<Vector2d, IMapElement> elementList = new HashMap<Vector2d, IMapElement>();
    MapVisualizer visualizer;
    public AbstractWorldMap(){
        visualizer = new MapVisualizer(this);
    }

    public void positionChanged(Vector2d oldPosition, Vector2d newPosition){
        IMapElement movedElement = elementList.get(oldPosition);
        elementList.remove(oldPosition);
        elementList.put(newPosition, movedElement);
    }

    @Override
    public boolean place(Animal animal) {
        Vector2d currPosition = animal.getPosition();
        if (canMoveTo(currPosition)){
            elementList.put(animal.getPosition(), animal);
            return true;
        }
        return false;
    }
    @Override
    public boolean isOccupied(Vector2d position) {
        if(objectAt(position) == null){
            return false;
        }
        return true;
    }

    @Override
    public Object objectAt(Vector2d position) {
        return elementList.get(position);
    }

    protected abstract Vector2d upperRightMapCorner();

    protected abstract Vector2d lowerLeftMapCorner();

    public String toString() {
        Vector2d lowerLeft = lowerLeftMapCorner();
        Vector2d upperRight = upperRightMapCorner();
        return visualizer.draw(lowerLeft, upperRight);
    }
}

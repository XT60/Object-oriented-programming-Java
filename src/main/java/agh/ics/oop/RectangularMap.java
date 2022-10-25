package agh.ics.oop;

import java.util.ArrayList;
import java.util.LinkedList;

public class RectangularMap implements IWorldMap {
    private int height;
    private int width;
    private ArrayList<Animal> animalMap;

    public RectangularMap(int width, int height){
        this.width = width;
        this.height = height;
        for(int i = 0; i < height * width; i++){
            animalMap.set(i, null);
        };
    }

    public int mapIndex(int y, int x){
        return y * width + x;
    }

    public int mapIndex(Vector2d position){
        return position.getY() * width + position.getX();
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
//        what if position beyond map borders is given
        if (this.objectAt(position) == null){
            return true;
        }
        return false;
    }

    @Override
    public boolean place(Animal animal) {

        return false;
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        //        what if position beyond map borders is given
        if (this.objectAt(position) == null) {
            return true;
        }
        return false;
    }

    @Override
    public Object objectAt(Vector2d position) {
        return animalMap.get(mapIndex(position));
    }


}

package agh.ics.oop;

import java.util.*;

public class GrassField extends AbstractWorldMap{
    MapBoundary mapBoundery;
    public GrassField(int grassCount){
        mapBoundery = new MapBoundary();
        int range = (int) Math.floor(Math.sqrt(10 * grassCount));
        Random rand = new Random();
        HashSet<Vector2d> set = new HashSet<Vector2d>();
        int i = grassCount;
        while (i > 0){
            int x = rand.nextInt(range);
            int y = rand.nextInt(range);
            Vector2d newVec = new Vector2d(x ,y);
            if (!set.contains(newVec)){
                set.add(newVec);
                i -= 1;
            }
        }
        Iterator<Vector2d> setIterator = set.iterator();
        while(setIterator.hasNext()) {
            Vector2d pos = setIterator.next();
            Grass grass = new Grass(pos);
            elementList.put(pos, grass);
            mapBoundery.placeElement(grass);
        }

    }


    @Override
    public boolean place(Animal animal) throws IllegalArgumentException{
        boolean res = super.place(animal);
        animal.addObserver(mapBoundery);
        mapBoundery.placeElement(animal);
        return res;
    }


    @Override
    public boolean canMoveTo(Vector2d position) {
        Object atObject = objectAt(position);
        if (atObject == null || atObject instanceof Grass){
            return true;
        }
        return false;
    }

    @Override
    public Vector2d upperRightMapCorner() {
        return mapBoundery.upperRightMapCorner();
    }

    @Override
    public Vector2d lowerLeftMapCorner() {
        return mapBoundery.lowerLeftMapCorner();
    }


}

package agh.ics.oop;

import java.util.*;

public class GrassField extends AbstractWorldMap{

    public GrassField(int grassCount){
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
            elementList.add(new Grass(setIterator.next()));
        }
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
    protected Vector2d upperRightMapCorner() {
        Vector2d upperRight = new Vector2d(0,0);
        for(int i = 0; i < elementList.size(); i ++){
            IMapElement animal = elementList.get(i);
            Vector2d pos = animal.getPosition();
            upperRight.x = Math.max(pos.x, upperRight.x);
            upperRight.y = Math.max(pos.y, upperRight.y);
        }
        return upperRight;
    }

    @Override
    protected Vector2d lowerLeftMapCorner() {
        Vector2d lowerLeft = new Vector2d(0,0);
        for(int i = 0; i < elementList.size(); i ++){
            IMapElement animal = elementList.get(i);
            Vector2d pos = animal.getPosition();
            lowerLeft.x = Math.min(pos.x, lowerLeft.x);
            lowerLeft.y = Math.min(pos.y, lowerLeft.y);
        }
        return lowerLeft;
    }
}

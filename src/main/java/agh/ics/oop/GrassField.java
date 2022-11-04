package agh.ics.oop;

import java.util.*;

public class GrassField implements IWorldMap{
    private List<Grass> grassList = new LinkedList<Grass>();
    private List<Animal> animalList = new LinkedList<Animal>();
    private MapVisualizer visualizer = new MapVisualizer(this);

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
            grassList.add(new Grass(setIterator.next()));
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
        for(int i = 0; i < grassList.size(); i++){
            Grass grass = grassList.get(i);
            if (grass.getPosition().equals(position)){
                return grass;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        Vector2d lowerLeft = new Vector2d(0,0);
        Vector2d upperRight = new Vector2d(0,0);
        for(int i = 0; i < animalList.size(); i ++){
            Animal animal = animalList.get(i);
            Vector2d pos = animal.getPosition();
            lowerLeft.x = Math.min(pos.x, lowerLeft.x);
            lowerLeft.y = Math.min(pos.y, lowerLeft.y);
            upperRight.x = Math.max(pos.x, upperRight.x);
            upperRight.y = Math.max(pos.y, upperRight.y);
        }
        for(int i = 0; i < grassList.size(); i ++){
            Grass grass = grassList.get(i);
            Vector2d pos = grass.getPosition();
            lowerLeft.x = Math.min(pos.x, lowerLeft.x);
            lowerLeft.y = Math.min(pos.y, lowerLeft.y);
            upperRight.x = Math.max(pos.x, upperRight.x);
            upperRight.y = Math.max(pos.y, upperRight.y);
        }
        return visualizer.draw(lowerLeft, upperRight);
    }

}

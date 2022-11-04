package agh.ics.oop;

import java.util.*;

public class GrassField extends AbstractWorldMap{
    private int[] grassRect;

    public GrassField(int grassCount){
        super();
        int range = (int) Math.ceil(Math.sqrt(10 * grassCount));
        grassRect = new int[]{0, 0, range, range};
        HashSet<Vector2d> set = new HashSet<Vector2d>();
        int i = grassCount;
        while (i > 0){
            Vector2d newVec = getRandomGrassPosition();
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
            IMapElement element = elementList.get(i);
            Vector2d pos = element.getPosition();
            upperRight.x = Math.max(pos.x, upperRight.x);
            upperRight.y = Math.max(pos.y, upperRight.y);
        }
        return upperRight;
    }

    @Override
    protected Vector2d lowerLeftMapCorner() {
        Vector2d lowerLeft = new Vector2d(0,0);
        for(int i = 0; i < elementList.size(); i ++){
            IMapElement element = elementList.get(i);
            Vector2d pos = element.getPosition();
            lowerLeft.x = Math.min(pos.x, lowerLeft.x);
            lowerLeft.y = Math.min(pos.y, lowerLeft.y);
        }
        return lowerLeft;
    }

    private Vector2d getRandomGrassPosition(){
        Random rand = new Random();
        int x = rand.nextInt(grassRect[2]) + grassRect[0];
        int y = rand.nextInt(grassRect[3]) + grassRect[1];
        return new Vector2d(x ,y);
    }
    @Override
    public void handleAnimalMovement(Animal movedAnimal){
        Vector2d newPosition = movedAnimal.getPosition();
        for(int i = 0; i < elementList.size(); i++){
            IMapElement element = elementList.get(i);
            if (element.getPosition().equals(newPosition)){
                if (movedAnimal != element){
                    if (element instanceof Grass){
                        Vector2d foundPos = getRandomGrassPosition();
                        while(isOccupied(foundPos)){
                            foundPos = getRandomGrassPosition();
                        }
                        element.setPosition(foundPos);
                        return;
                    }
                    else{
                        // assumig that movement was done in agreement with movement roles
                        // this cannot be animal -> it should be grass object
                        System.out.println("sth wrong with the map elements");
                    }
                }
            }
        }

    }
}

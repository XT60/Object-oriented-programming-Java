package agh.ics.oop;

import javax.naming.directory.InvalidAttributesException;
import java.util.ArrayList;

public class MapBoundary implements IPositionChangeObserver{
    ArrayList<IMapElement> xAxisElements;
    ArrayList<IMapElement> yAxisElements;

    MapBoundary(){
        xAxisElements = new ArrayList<IMapElement>();
        yAxisElements = new ArrayList<IMapElement>();
    }

    private Animal popAnimal(ArrayList<IMapElement> list, Vector2d position){
        int len = list.size();
        for(int i = 0; i < len; i++){
            IMapElement currElement = list.get(i);
            if (currElement.getPosition().equals(position) && currElement instanceof Animal){
                list.remove(i);
                return (Animal)currElement;
            }
        }
        throw new IllegalArgumentException("haven't found animal at " + position.toString());
    }

    private boolean cmp(IMapElement fst, IMapElement sec, int axisValue){
        Vector2d fstPos = fst.getPosition();
        Vector2d secPos = sec.getPosition();
        if (axisValue == 0){
            if (fstPos.x < secPos.x){
                return true;
            } else if (fstPos.x > secPos.x) {
                return false;
            }
            if (fstPos.y < secPos.y){
                return true;
            } else if (fstPos.y > secPos.y) {
                return false;
            }
            if (fst instanceof Animal){
                return false;
            }
            return true;
        }
        if (fstPos.y < secPos.y){
            return true;
        } else if (fstPos.y > secPos.y) {
            return false;
        }
        if (fstPos.x < secPos.x){
            return true;
        } else if (fstPos.x > secPos.x) {
            return false;
        }
        if (fst instanceof Animal){
            return false;
        }
        return true;
    }

    private void addElement(ArrayList<IMapElement> list, IMapElement element, int axisValue){
        int len = list.size();
        int i = 0;
        for(; i < len; i++){
            IMapElement currElement = list.get(i);
            if (cmp(element, currElement, axisValue)){
                break;
            }
        }
        list.add(i, element);
    }

    public void placeElement(IMapElement element){
        addElement(xAxisElements, element, 0);
        addElement(yAxisElements, element, 1);
    }

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        Animal animal = popAnimal(xAxisElements, oldPosition);
        popAnimal(yAxisElements, oldPosition);
        addElement(xAxisElements, animal, 0);
        addElement(yAxisElements, animal, 1);
    }

    public Vector2d upperRightMapCorner(){
        int x = xAxisElements.get(xAxisElements.size() - 1).getPosition().x;
        int y = yAxisElements.get(yAxisElements.size() - 1).getPosition().y;
        return new Vector2d(x, y);
    }

    public Vector2d lowerLeftMapCorner(){
        int x = xAxisElements.get(0).getPosition().x;
        int y = yAxisElements.get(0).getPosition().y;
        return new Vector2d(x, y);
    }
}

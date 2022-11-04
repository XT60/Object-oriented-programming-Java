package agh.ics.oop;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class RectangularIWorldMapTest{
    @Test
    public void canMoveToTest() {
        RectangularMap map = new RectangularMap(10, 5);
        Vector2d[] positions = {new Vector2d(5, 4)};
        MoveDirection[] directions = {};
        IEngine engine = new SimulationEngine(directions, map, positions);
        assertFalse(map.canMoveTo(new Vector2d(-1, 0)));
        assertFalse(map.canMoveTo(new Vector2d(0, -1)));
        assertFalse(map.canMoveTo(new Vector2d(5, 4)));
        assertFalse(map.canMoveTo(new Vector2d(10, 0)));
        assertFalse(map.canMoveTo(new Vector2d(0, 5)));
    }

    @Test
    public void placeTest() {
        RectangularMap map = new RectangularMap(10, 5);
        Vector2d[] positions = {new Vector2d(5, 4)};
        MoveDirection[] directions = {};
        IEngine engine = new SimulationEngine(directions, map, positions);
        Vector2d[] testPositions = {new Vector2d(-1, 0), new Vector2d(-1, 0), new Vector2d(0, -1),
                new Vector2d(5, 4), new Vector2d(10, 0), new Vector2d(0, 5)};
        for(int i = 0; i < testPositions.length; i++){
            Animal animal = new Animal(testPositions[i]);
            assertFalse(map.place(animal));
        }
    }

    @Test
    public void isOccupiedTest() {
        RectangularMap map = new RectangularMap(10, 5);
        Vector2d[] positions = {new Vector2d(5, 3)};
        MoveDirection[] directions = {};
        IEngine engine = new SimulationEngine(directions, map, positions);
        Vector2d[] testPositions = {new Vector2d(-1, 0), new Vector2d(0, -1),
                new Vector2d(2, 6), new Vector2d(10, 0), new Vector2d(0, 5)};
        for(int i = 0; i < testPositions.length; i++){
            assertFalse(map.isOccupied(testPositions[i]));
        }
        assertTrue(map.isOccupied(new Vector2d(5, 3)));
    }

    @Test
    public void objectAt() {
        RectangularMap map = new RectangularMap(10, 5);
        Vector2d[] positions = {new Vector2d(5, 3)};
        MoveDirection[] directions = {};
        IEngine engine = new SimulationEngine(directions, map, positions);
        Vector2d[] testPositions = {new Vector2d(-1, 0), new Vector2d(-1, 0), new Vector2d(0, -1),
                new Vector2d(2, 6), new Vector2d(10, 0), new Vector2d(0, 5)};
        for(int i = 0; i < testPositions.length; i++){
            assertNull(map.objectAt(testPositions[i]));
        }
        Object found = map.objectAt(new Vector2d(5, 3));
        assertTrue(found instanceof Animal);
        Animal foundAnimal = (Animal)found;
        assertTrue(foundAnimal.getPosition().equals(new Vector2d(5, 3)));
    }
}

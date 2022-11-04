package agh.ics.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GrassFieldIWorldMapTest {
    @Test
    public void canMoveToTest() {
        GrassField map = new GrassField(10);
        Vector2d[] positions = {new Vector2d(5, 4)};
        MoveDirection[] directions = {};
        IEngine engine = new SimulationEngine(directions, map, positions);
        Vector2d[] trueTestPositions = {
                new Vector2d(-1, 0),
                new Vector2d(0, -1),
                new Vector2d(10, 0),
                new Vector2d(0, 5),
                new Vector2d(2, 3)
        };
        Vector2d[] falseTestPositions = {
                new Vector2d(5, 4)
        };
        for(int i = 0; i < trueTestPositions.length; i++){
            assertTrue(map.canMoveTo(trueTestPositions[i]));
        }
        for(int i = 0; i < falseTestPositions.length; i++){
            assertFalse(map.canMoveTo(falseTestPositions[i]));
        }
    }

    @Test
    public void placeTest() {
        GrassField map = new GrassField(10);
        Vector2d[] positions = {new Vector2d(5, 4), new Vector2d(2, 3)};
        MoveDirection[] directions = {};
        IEngine engine = new SimulationEngine(directions, map, positions);
        Vector2d[] falseTestPositions = {
            new Vector2d(5, 4),
            new Vector2d(2, 3),
        };
        Vector2d[] trueTestPositions = {
            new Vector2d(-1, 0),
            new Vector2d(0, -1),
            new Vector2d(10, 0),
            new Vector2d(0, 5)
        };

        for(int i = 0; i < trueTestPositions.length; i++){
            Animal animal = new Animal(trueTestPositions[i]);
            assertTrue(map.place(animal));
        }
        for(int i = 0; i < falseTestPositions.length; i++){
            Animal animal = new Animal(falseTestPositions[i]);
            assertFalse(map.place(animal));
        }
    }

    @Test
    public void isOccupiedTest() {
        GrassField map = new GrassField(10);
        Vector2d[] positions = {new Vector2d(5, 3), new Vector2d(2, 7)};
        MoveDirection[] directions = {};
        IEngine engine = new SimulationEngine(directions, map, positions);
        Vector2d[] trueTestPositions = {
                new Vector2d(5, 3),
                new Vector2d(2, 7)
        };
        Vector2d[] falseTestPositions = {
                new Vector2d(-1, 0),
                new Vector2d(-1, 0),
                new Vector2d(0, -1),
                new Vector2d(10, 0),
                new Vector2d(0, 5)
        };
        for(int i = 0; i < trueTestPositions.length; i++){
            assertTrue(map.isOccupied(trueTestPositions[i]));
        }
        for(int i = 0; i < falseTestPositions.length; i++){
            Object found = map.objectAt(falseTestPositions[i]);
            if (found == null){
                assertFalse(map.isOccupied(falseTestPositions[i]));
            }
            else if (found instanceof Grass){
                assertTrue(map.isOccupied(falseTestPositions[i]));
            }
        }
    }

    @Test
    public void objectAt() {
        GrassField map = new GrassField(10);
        Vector2d[] positions = {new Vector2d(5, 3), new Vector2d(2, 7)};
        MoveDirection[] directions = {};
        IEngine engine = new SimulationEngine(directions, map, positions);
        Vector2d[] trueTestPositions = {
                new Vector2d(5, 3),
                new Vector2d(2, 7)
        };
        Vector2d[] falseTestPositions = {
                new Vector2d(-1, 0),
                new Vector2d(0, -1),
                new Vector2d(10, 0),
                new Vector2d(0, 5)
        };
        for(int i = 0; i < trueTestPositions.length; i++){
            assertTrue(map.objectAt(trueTestPositions[i]) instanceof Animal);
        }
        for(int i = 0; i < falseTestPositions.length; i++){
            Object found = map.objectAt(falseTestPositions[i]);
            assertTrue(found == null || map.objectAt(falseTestPositions[i]) instanceof Grass);
        }
    }
}


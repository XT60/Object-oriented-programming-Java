package agh.ics.oop;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static java.lang.System.out;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AnimalMovementTest {
    @Test
    public void collisionTest(){
        String[] args = {"r", "l",
                         "f", "f",
                         "f", "f",
                         "f", "f",
                         "f", "f",
                         "f", "f",
                         "f", "f"};
        MoveDirection[] directions = new OptionsParser().parse(args);
        IWorldMap map = new RectangularMap(10, 5);
        Vector2d[] positions = { new Vector2d(0,0), new Vector2d(4,0) };
        IEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();

        ArrayList<Animal> animals = map.getAnimalList();
        Vector2d[] endPositions = {new Vector2d(2,0), new Vector2d(3,0)};
        checkResults(animals, endPositions);
    }

    @Test
    public void mapBorderTest(){
        String[] args = {"l", "r", "r", "f",
                         "f", "f", "r", "f",
                         "f", "f", "f", "f",
                         "f", "f", "f", "f",
                         "f", "f", "f", "f"};
        MoveDirection[] directions = new OptionsParser().parse(args);
        IWorldMap map = new RectangularMap(10, 5);
        Vector2d[] positions = { new Vector2d(3,2), new Vector2d(8,3), new Vector2d(5,2), new Vector2d(5,3) };
        IEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();

        ArrayList<Animal> animals = map.getAnimalList();
        Vector2d[] endPositions = { new Vector2d(0,2), new Vector2d(9,3), new Vector2d(5,0), new Vector2d(5,4) };
        checkResults(animals, endPositions);
    }

    @Test
    public void mapCornerTest(){
        String[] args = {"l", "r", "f", "f",
                         "f", "f", "f", "f",
                         "f", "f", "r", "l",
                         "l", "r", "f", "f",
                         "f", "f", "f", "f",
                         "f", "f", "f", "f"};
        MoveDirection[] directions = new OptionsParser().parse(args);
        IWorldMap map = new RectangularMap(10, 5);
        Vector2d[] positions = { new Vector2d(0,0), new Vector2d(9,0), new Vector2d(9,4), new Vector2d(0,4) };
        IEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();

        ArrayList<Animal> animals = map.getAnimalList();
        Vector2d[] endPositions = { new Vector2d(0,0), new Vector2d(9,0), new Vector2d(9,4), new Vector2d(0,4) };
        checkResults(animals, endPositions);
    }

    @Test
    public void movementTest(){
        String[] args = {"f", "b", "r", "l", "f", "f", "r", "r", "f", "f", "f", "f", "f", "f", "f", "f"};
        MoveDirection[] directions = new OptionsParser().parse(args);
        IWorldMap map = new RectangularMap(10, 5);
        Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4) };
        IEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();

        ArrayList<Animal> animals = map.getAnimalList();
        Vector2d[] endPositions = { new Vector2d(2,0), new Vector2d(3,4) };
        checkResults(animals, endPositions);
    }

    public void checkResults(ArrayList<Animal> animals,  Vector2d[] positions){
        for(int i = 0; i < animals.size(); i++){
            Animal animal = animals.get(i);
            Vector2d pos = positions[i];
            assertTrue(animal.arePositionsEquals(pos));
        }
    }
}

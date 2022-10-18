package agh.ics.oop;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import static java.lang.System.out;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AnimalParseTest {
    @Test
    void interpretationTest(){
        String[][] inputs = {
                {"f", "r", "l", "b"},
                {"forw", "f", "kds", "l", "backward"},
                {"b", "forward", "let", "r"},
                {"right", "h", "d", "left", "c"}
        };

        MoveDirection[][] correctData = {
                {MoveDirection.FORWARD, MoveDirection.RIGHT, MoveDirection.LEFT, MoveDirection.BACKWARD},
                {MoveDirection.FORWARD, MoveDirection.LEFT, MoveDirection.BACKWARD},
                {MoveDirection.BACKWARD, MoveDirection.FORWARD, MoveDirection.RIGHT},
                {MoveDirection.RIGHT, MoveDirection.LEFT}
        };

        OptionsParser myParser = new OptionsParser();
        for(int i = 0; i < inputs.length; i++){
            MoveDirection[] output = myParser.parse(inputs[i]);
            assertEquals(output.length, correctData[i].length);
            for(int j = 0; j < output.length; j++){
                assertEquals(output[j], correctData[i][j]);
            }
        }
    }

    @Test
    public void movementTest() throws FullWorldException{
        String[][] inputs = {
                {"left", "left", "backward", "left", "left", "right", "forward"},
                {"forward", "backward", "forward", "left", "forward", "right"},
                {"left", "left", "forward", "forward"},
                {"forward", "forward", "left", "forward", "backward", "backward"},

        };
        Vector2d[] positions = {
                new Vector2d(3, 3),
                new Vector2d(1, 3),
                new Vector2d(2, 0),
                new Vector2d(3, 4)
        };

        MapDirection[] directions = {
                MapDirection.EAST,
                MapDirection.NORTH,
                MapDirection.SOUTH,
                MapDirection.WEST
        };

        for(int i = 0; i < inputs.length; i++){
            Animal output = handleInput(inputs[i]);
            assertTrue(output.arePositionsEquals(positions[i]));
            assertTrue(output.areDirectionsEquals(directions[i]));
            output.handleDelete();
        }
    }

    // ----------------------- Directions
    @Test
    public void eastDirectionTest() throws FullWorldException{
        String[] input = {"right"};
        Animal output = handleInput(input);
        assertTrue(output.areDirectionsEquals(MapDirection.EAST));
        output.handleDelete();
    }

    @Test
    public void westDirectionTest() throws FullWorldException{
        String[] input = {"left"};
        Animal output = handleInput(input);
        assertTrue(output.areDirectionsEquals(MapDirection.WEST));
        output.handleDelete();
    }

    @Test
    public void northDirectionTest() throws FullWorldException{
        String[] input = {"left", "right"};
        Animal output = handleInput(input);
        assertTrue(output.areDirectionsEquals(MapDirection.NORTH));
        output.handleDelete();
    }

    @Test
    public void southDirectionTest() throws FullWorldException{
        String[] input = {"left", "left"};
        Animal output = handleInput(input);
        assertTrue(output.areDirectionsEquals(MapDirection.SOUTH));
        output.handleDelete();
    }

    // ----------------------- Borders
    @Test
    public void eastBorderTest() throws FullWorldException{
        String[] input = {"right", "forward", "forward", "forward"};
        Animal output = handleInput(input);
        assertTrue(output.arePositionsEquals(new Vector2d(4, 2)));
        output.handleDelete();
    }

    @Test
    public void westBorderTest() throws FullWorldException{
        String[] input = {"left", "forward", "forward", "forward"};
        Animal output = handleInput(input);
        assertTrue(output.arePositionsEquals(new Vector2d(0, 2)));
        output.handleDelete();
    }

    @Test
    public void northBorderTest() throws FullWorldException{
        String[] input = {"forward", "forward", "forward"};
        Animal output = handleInput(input);
        assertTrue(output.arePositionsEquals(new Vector2d(2, 4)));
        output.handleDelete();
    }

    @Test
    public void southBorderTest() throws FullWorldException{
        String[] input = {"right", "right", "forward", "forward", "forward"};
        Animal output = handleInput(input);

        assertTrue(output.arePositionsEquals(new Vector2d(2, 0)));
    }

    Animal handleInput(String [] input) throws FullWorldException{
        Animal myAnimal;
        myAnimal = new Animal();
        OptionsParser myParser = new OptionsParser();
        MoveDirection[] myInput = myParser.parse(input);
        for( int i = 0; i < myInput.length; i++){
            myAnimal.move(myInput[i]);
        }

        return myAnimal;
    }
}

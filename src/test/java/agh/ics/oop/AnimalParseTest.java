package agh.ics.oop;
import org.junit.jupiter.api.Test;

import static java.lang.System.out;
import static org.junit.jupiter.api.Assertions.assertEquals;
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
    public void movementTest(){
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
        }
    }

    // ----------------------- Directions
    @Test
    public void eastDirectionTest(){
        String[] input = {"right"};
        Animal output = handleInput(input);
        assertTrue(output.areDirectionsEquals(MapDirection.EAST));
    }

    @Test
    public void westDirectionTest(){
        String[] input = {"left"};
        Animal output = handleInput(input);
        assertTrue(output.areDirectionsEquals(MapDirection.WEST));
    }

    @Test
    public void northDirectionTest(){
        String[] input = {"left", "right"};
        Animal output = handleInput(input);
        assertTrue(output.areDirectionsEquals(MapDirection.NORTH));
    }

    @Test
    public void southDirectionTest(){
        String[] input = {"left", "left"};
        Animal output = handleInput(input);
        assertTrue(output.areDirectionsEquals(MapDirection.SOUTH));
    }

    // ----------------------- Borders
    @Test
    public void eastBorderTest(){
        String[] input = {"right", "forward", "forward", "forward"};
        Animal output = handleInput(input);
        assertTrue(output.arePositionsEquals(new Vector2d(4, 2)));
    }

    @Test
    public void westBorderTest(){
        String[] input = {"left", "forward", "forward", "forward"};
        Animal output = handleInput(input);
        assertTrue(output.arePositionsEquals(new Vector2d(0, 2)));
    }

    @Test
    public void northBorderTest(){
        String[] input = {"forward", "forward", "forward"};
        Animal output = handleInput(input);
        assertTrue(output.arePositionsEquals(new Vector2d(2, 4)));
    }

    @Test
    public void southBorderTest(){
        String[] input = {"right", "right", "forward", "forward", "forward"};
        Animal output = handleInput(input);
        assertTrue(output.arePositionsEquals(new Vector2d(2, 0)));
    }

    Animal handleInput(String [] input){
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

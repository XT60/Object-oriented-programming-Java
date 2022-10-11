package agh.ics.oop;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.HashMap;

public class MapDirectionTest {
    @Test
    public void nextTest(){
        HashMap<MapDirection, MapDirection> goodResults = new HashMap<MapDirection, MapDirection>(4);
        goodResults.put(MapDirection.NORTH, MapDirection.EAST);
        goodResults.put(MapDirection.EAST, MapDirection.SOUTH);
        goodResults.put(MapDirection.SOUTH, MapDirection.WEST);
        goodResults.put(MapDirection.WEST, MapDirection.NORTH);
        for (HashMap.Entry<MapDirection,MapDirection> entry : goodResults.entrySet()){
            MapDirection key = entry.getKey(),
                value = entry.getValue(),
                result = key.next();
            if (result != value){
                assertEquals(value, result.next());
            }
        }
    }

    @Test
    public void nextPrevious(){
        HashMap<MapDirection, MapDirection> goodResults = new HashMap<MapDirection, MapDirection>(4);
        goodResults.put(MapDirection.NORTH, MapDirection.WEST);
        goodResults.put(MapDirection.WEST, MapDirection.SOUTH);
        goodResults.put(MapDirection.SOUTH, MapDirection.EAST);
        goodResults.put(MapDirection.EAST, MapDirection.NORTH);
        for (HashMap.Entry<MapDirection,MapDirection> entry : goodResults.entrySet()){
            MapDirection key = entry.getKey(),
                    value = entry.getValue(),
                    result = key.previous();
            if (result != value){
                assertEquals(value, result);
            }
        }
    }
}

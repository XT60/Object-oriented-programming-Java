package agh.ics.oop;

import org.junit.jupiter.api.Test;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;
public class Vector2dTest {
    @Test
    public void equalsTest(){
        var a = new Vector2d(0, 0);
        var b = new Vector2d(0, 0);
        int c = 1;
        var d = new Vector2d(1, 10);
        assertTrue(a.equals(a));
        assertFalse(a.equals(c));
        assertTrue(a.equals(b));
        assertFalse(a.equals(d));
    }
    @Test
    public void toStringTest(){
        Vector2d a = new Vector2d(0, 0),
                 b = new Vector2d(-1, 15);
        assertEquals("(0,0)", a.toString());
        assertEquals("(-1,15)", b.toString());

    }

    @Test
    public void precedesTest(){
        Vector2d a = new Vector2d(0, 0),
                b = new Vector2d(1, 1),
                c = new Vector2d(-1, 1);
        assertTrue(a.precedes(a));
        assertTrue(a.precedes(b));
        assertFalse(a.precedes(c));
    }

    @Test
    public void followsTest(){
        Vector2d a = new Vector2d(0, 0),
                 b = new Vector2d(1, 1),
                 c = new Vector2d(-1, 1);
        assertTrue(a.follows(a));
        assertTrue(b.follows(a));
        assertFalse(c.follows(a));
    }

    @Test
    public void upperRightTest(){
        Vector2d a = new Vector2d(0, 0),
                 b = new Vector2d(1, 1),
                 c = new Vector2d(-1, 1),
                 d = new Vector2d(-1, -1 );
        assertEquals(new Vector2d(1, 1), a.upperRight(b));
        assertEquals(new Vector2d(0, 1), a.upperRight(c));
        assertEquals(new Vector2d(0, 0), a.upperRight(d));
    }

    @Test
    public void lowerLeftTest(){
        Vector2d a = new Vector2d(0, 0),
                b = new Vector2d(1, 1),
                c = new Vector2d(-1, 1),
                d = new Vector2d(-1, -1 );
        assertEquals(new Vector2d(0, 0), a.lowerLeft(b));
        assertEquals(new Vector2d(-1, 0), a.lowerLeft((c)));
        assertEquals(new Vector2d(-1, -1), c.lowerLeft((d)));
    }

    @Test
    public void addTest(){
        Vector2d a = new Vector2d(0, 0),
                b = new Vector2d(1, 1),
                c = new Vector2d(-1, 1),
                d = new Vector2d(-1, -1 );
        assertEquals(new Vector2d(1, 1), a.add(b));
        assertEquals(new Vector2d(0, 2), b.add(c));
        assertEquals(new Vector2d(-2, 0), c.add(d));
    }

    @Test
    public void subtractTest(){
        Vector2d a = new Vector2d(0, 0),
                 b = new Vector2d(1, 1),
                 c = new Vector2d(-1, 1),
                 d = new Vector2d(-1, -1 );
        assertEquals(new Vector2d(-1, -1), a.subtract(b));
        assertEquals(new Vector2d(2, 0), b.subtract(c));
        assertEquals(new Vector2d(0, 2), c.subtract(d));
    }

    @Test
    public void oppositeTest(){
        Vector2d b = new Vector2d(1, 1),
                 c = new Vector2d(-1, 1),
                 d = new Vector2d(-1, 0 );
        assertEquals(new Vector2d(1, 1), b.opposite());
        assertEquals(new Vector2d(1, -1), c.opposite());
        assertEquals(new Vector2d(0, -1), d.opposite());
    }

}

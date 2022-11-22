package agh.ics.oop;

import java.util.List;

public class ThreadedSimulationEngine extends SimulationEngine{
    public ThreadedSimulationEngine(AbstractWorldMap map, Vector2d[] initialPositions){
        super(null, map, initialPositions);
    }

    public void setDirections(MoveDirection[] directions) {
        this.directions = directions;
    }
}

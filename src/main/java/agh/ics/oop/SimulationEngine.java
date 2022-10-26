package agh.ics.oop;

import java.util.ArrayList;
import java.util.LinkedList;

public class SimulationEngine implements IEngine{
    private MoveDirection[] moves;
    private RectangularMap map;
    private Vector2d[] initialPositions;

    public SimulationEngine(MoveDirection[] moves, IWorldMap map, Vector2d[] initialPositions){
        this.moves = moves;
        this.map = (RectangularMap) map;
        this.initialPositions = initialPositions;

        for (int i = 0; i < initialPositions.length; i ++){
            Vector2d initialPos = initialPositions[i];
            Animal animal = new Animal(map, initialPos);
        }
    }

    @Override
    public void run() {
        int i = 0;
        if (i >= moves.length){
            return;
        }
        ArrayList<Animal> animalList = map.getAnimalList();
        while(animalList.size() > 0){
            for(int a = 0; a < animalList.size(); a++){
                Animal animal = animalList.get(a);
                animal.move(moves[i]);
                i++;
                if (i >= moves.length){
                    return;
                }
            }
        }
    }
}

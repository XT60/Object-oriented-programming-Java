package agh.ics.oop;

import java.util.LinkedList;

public class SimulationEngine implements IEngine{
    private MoveDirection[] moves;
    private IWorldMap map;
    private Vector2d[] initialPositions;

    public SimulationEngine(MoveDirection[] moves, IWorldMap map, Vector2d[] initialPositions){
        this.moves = moves;
        this.map = map;
        this.initialPositions = initialPositions;

        for (int i = 0; i < initialPositions.length; i ++){
            Vector2d initialPos = initialPositions[i];
            Animal animal = new Animal(map, initialPos);
            if (!map.place(animal)){
                //throw exception ??
            };
        }
    }

    @Override
    public void run() {
        int i = 0;
        LinkedList<Animal> animalList = map.getAnimalList();
        while(true){
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

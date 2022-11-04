package agh.ics.oop;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class SimulationEngine implements IEngine{
    private MoveDirection[] moves;
    private IWorldMap map;
    private Vector2d[] initialPositions;

    private List<Animal> animalList = new ArrayList<Animal>();

    public SimulationEngine(MoveDirection[] moves, IWorldMap map, Vector2d[] initialPositions){
        this.moves = moves;
        this.map = map;
        this.initialPositions = initialPositions;

        for (int i = 0; i < initialPositions.length; i ++){
            Vector2d initialPos = initialPositions[i];
            Animal animal = new Animal(map, initialPos);
            this.animalList.add(animal);
        }
    }
    public List<Animal> getAnimalList(){
        return animalList;
    }

    @Override
    public void run() {
        int i = 0;
        if (i >= moves.length){
            return;
        }
        while(this.animalList.size() > 0){
            for(int a = 0; a < this.animalList.size(); a++){
                Animal animal = this.animalList.get(a);
                animal.move(moves[i]);
                map.handleAnimalMovement(animal);
                i++;
                if (i >= moves.length){
                    return;
                }
            }
//            System.out.println(map.toString());
        }
    }
}

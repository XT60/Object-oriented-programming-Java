package agh.ics.oop;
import static java.lang.System.out;


public class World {
    public static void main(String[] args){
        try {
            MoveDirection[] directions = new OptionsParser().parse(args);
            AbstractWorldMap map = new GrassField(10);
            Vector2d[] positions = {new Vector2d(2, 2), new Vector2d(2, 2)};
            IEngine engine = new SimulationEngine(directions, map, positions);
            engine.run();
            out.print(map.toString());
        }
        catch (IllegalArgumentException exc){
            System.out.println(exc.toString());
        }
    }

    static void run(Direction[] arr){
        int len = arr.length;
        out.println("Start");
        for(int i = 0; i < len; i++){
            switch(arr[i]){
                case FRONT:
                    out.println("Zwierzak idzie do przodu");
                    break;
                case BACK:
                    out.println("Zwierzak idzie do tyłu");
                    break;
                case RIGHT:
                    out.println("Zwierzak skręca w prawo");
                    break;
                case LEFT:
                    out.println("Zwierzak skręca w lewo");
                    break;
            }
        }
        out.println("Stop");
    }
    static Direction[] convertToDirection(String[] arr){
        int len = arr.length;
        Direction[] res = new Direction[len];
        for(int i = 0; i < len; i++){
            switch(arr[i]){
                case "f":
                    res[i] = Direction.FRONT;
                    break;
                case "b":
                    res[i] = Direction.BACK;
                    break;
                case "r":
                    res[i] = Direction.RIGHT;
                    break;
                case "l":
                    res[i] = Direction.LEFT;
                    break;
                default:
                    res[i] = Direction.ERROR;
            }
        }
        return res;
    }
}
